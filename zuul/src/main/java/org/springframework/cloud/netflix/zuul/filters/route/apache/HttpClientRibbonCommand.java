/*
 * Copyright 2013-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.netflix.zuul.filters.route.apache;

import com.netflix.client.config.IClientConfig;

import org.springframework.cloud.netflix.ribbon.apache.RibbonApacheHttpRequest;
import org.springframework.cloud.netflix.ribbon.apache.RibbonApacheHttpResponse;
import org.springframework.cloud.netflix.ribbon.apache.RibbonLoadBalancingHttpClient;
import org.springframework.cloud.netflix.ribbon.support.RibbonCommandContext;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.cloud.netflix.zuul.filters.route.support.AbstractRibbonCommand;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Spencer Gibb
 * @author Ryan Baxter
 */
@Slf4j
public class HttpClientRibbonCommand extends
		AbstractRibbonCommand<RibbonLoadBalancingHttpClient, RibbonApacheHttpRequest, RibbonApacheHttpResponse> {

	public HttpClientRibbonCommand(final String commandKey,
			final RibbonLoadBalancingHttpClient client,
			final RibbonCommandContext context, final ZuulProperties zuulProperties) {
		super(commandKey, client, context, zuulProperties);
	}

	public HttpClientRibbonCommand(final String commandKey,
			final RibbonLoadBalancingHttpClient client,
			final RibbonCommandContext context, final ZuulProperties zuulProperties,
			final FallbackProvider zuulFallbackProvider) {
		super(commandKey, client, context, zuulProperties, zuulFallbackProvider);
	}

	public HttpClientRibbonCommand(final String commandKey,
			final RibbonLoadBalancingHttpClient client,
			final RibbonCommandContext context, final ZuulProperties zuulProperties,
			final FallbackProvider zuulFallbackProvider, final IClientConfig config) {
		super(commandKey, client, context, zuulProperties, zuulFallbackProvider, config);
	}

	@Override
	protected RibbonApacheHttpRequest createRequest() throws Exception {
		
		log.error("HttpClientRibbonCommand createRequest");
		return new RibbonApacheHttpRequest(this.context);
	}

}
