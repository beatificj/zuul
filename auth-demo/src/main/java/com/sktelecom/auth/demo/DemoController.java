/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sktelecom.auth.demo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author P067880
 */

@Controller
@RequestMapping("/api/v1/demo")
public class DemoController {

	@RequestMapping("/success")
	public @ResponseBody String auth() {
		return "success";
	}

	@RequestMapping("/unauthorized")
	public String unauthorized() {
		return "redirect:http://www.naver.com";
	}

	@RequestMapping("/forbidden")
	public String forbidden() {
		throw new ForbiddenException();
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	public class ForbiddenException extends RuntimeException {
	}
}
