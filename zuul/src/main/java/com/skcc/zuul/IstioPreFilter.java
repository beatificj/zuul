package com.skcc.zuul;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVICE_ID_KEY;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class IstioPreFilter extends ZuulFilter {

	@Autowired
	DiscoveryClient discoveryClient;
	
	@Override
	public String filterType() {
		return PRE_TYPE;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		log.error("==============================IstioPreFilter======================");
		RequestContext context = RequestContext.getCurrentContext();
			String serviceId = (String)context.get(SERVICE_ID_KEY);
			log.error("serviceId [{}]]", serviceId);
			List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
			log.error("instances.size [{}]]", instances.size());
			if(instances.size() != 1) throw new RuntimeException("the number of ports of service must be only one! ServiceId[" + serviceId + "]");
			Integer port = instances.get(0).getPort();
			log.error("serviceId [{}], port[{}]", serviceId, port);
			context.addZuulRequestHeader("Host", serviceId + ":" + Integer.toString(port));
		
		return null;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return PRE_DECORATION_FILTER_ORDER + 1;
	}
}
