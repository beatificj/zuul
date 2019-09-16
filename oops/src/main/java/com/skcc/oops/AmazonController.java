package com.skcc.oops;

import org.springframework.web.bind.annotation.RequestMapping;

import com.sktelecom.swing.httpclient.core.extern.ExternalClient;

@ExternalClient(name="amazon", method="hub")
public interface AmazonController {
	
	@RequestMapping("/harry")
    public Book get();
}

