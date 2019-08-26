package com.skcc.oops;

import org.springframework.web.bind.annotation.RequestMapping;

import com.sktelecom.swing.httpclient.core.inner.InternalClient;

@InternalClient(name="books")
public interface BooksController {
	
	@RequestMapping("/harry")
    public Book get();
}