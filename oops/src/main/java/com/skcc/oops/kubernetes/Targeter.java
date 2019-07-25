package com.skcc.oops.kubernetes;

import org.springframework.cloud.openfeign.FeignContext;

import feign.Feign;
import feign.Target;

interface Targeter {

	<T> T target(OopsClientFactoryBean factory, Feign.Builder feign,
			FeignContext context, Target.HardCodedTarget<T> target);

}