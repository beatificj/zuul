package com.skcc.oops;

import java.io.IOException;
import java.net.URI;

import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.FeignLoadBalancer;

import com.netflix.client.ClientException;
import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;

import feign.Client;
import feign.Request;
import feign.Response;

public class IstioClient implements Client {
	static final Request.Options DEFAULT_OPTIONS = new Request.Options();

	private final Client delegate;

	private CachingSpringLoadBalancerFactory lbClientFactory;

	private SpringClientFactory clientFactory;

	public IstioClient(Client delegate,
			CachingSpringLoadBalancerFactory lbClientFactory,
			SpringClientFactory clientFactory) {
		this.delegate = delegate;
		this.lbClientFactory = lbClientFactory;
		this.clientFactory = clientFactory;
	}

	static URI cleanUrl(String originalUrl, String host) {
		String newUrl = originalUrl;
		if (originalUrl.startsWith("https://")) {
			newUrl = originalUrl.substring(0, 8)
					+ originalUrl.substring(8 + host.length());
		}
		else if (originalUrl.startsWith("http")) {
			newUrl = originalUrl.substring(0, 7)
					+ originalUrl.substring(7 + host.length());
		}
		StringBuffer buffer = new StringBuffer(newUrl);
		if ((newUrl.startsWith("https://") && newUrl.length() == 8)
				|| (newUrl.startsWith("http://") && newUrl.length() == 7)) {
			buffer.append("/");
		}
		return URI.create(buffer.toString());
	}

	@Override
	public Response execute(Request request, Request.Options options) throws IOException {
		try {
			URI asUri = URI.create(request.url());
			String clientName = asUri.getHost();
			URI uriWithoutHost = cleanUrl(request.url(), clientName);
			FeignLoadBalancer.RibbonRequest ribbonRequest = new FeignLoadBalancer.RibbonRequest(
					this.delegate, request, uriWithoutHost);

			IClientConfig requestConfig = getClientConfig(options, clientName);
			return lbClient(clientName)
					.executeWithLoadBalancer(ribbonRequest, requestConfig).toResponse();
		}
		catch (ClientException e) {
			IOException io = findIOException(e);
			if (io != null) {
				throw io;
			}
			throw new RuntimeException(e);
		}
	}

	IClientConfig getClientConfig(Request.Options options, String clientName) {
		IClientConfig requestConfig;
		if (options == DEFAULT_OPTIONS) {
			requestConfig = this.clientFactory.getClientConfig(clientName);
		}
		else {
			requestConfig = new FeignOptionsClientConfig(options);
		}
		return requestConfig;
	}

	protected IOException findIOException(Throwable t) {
		if (t == null) {
			return null;
		}
		if (t instanceof IOException) {
			return (IOException) t;
		}
		return findIOException(t.getCause());
	}

	public Client getDelegate() {
		return this.delegate;
	}

	private FeignLoadBalancer lbClient(String clientName) {
		return this.lbClientFactory.create(clientName);
	}

	static class FeignOptionsClientConfig extends DefaultClientConfigImpl {

		FeignOptionsClientConfig(Request.Options options) {
			setProperty(CommonClientConfigKey.ConnectTimeout,
					options.connectTimeoutMillis());
			setProperty(CommonClientConfigKey.ReadTimeout, options.readTimeoutMillis());
		}

		@Override
		public void loadProperties(String clientName) {

		}

		@Override
		public void loadDefaultValues() {

		}

	}
}
