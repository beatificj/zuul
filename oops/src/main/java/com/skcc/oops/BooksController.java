package com.skcc.oops;

import org.springframework.web.bind.annotation.RequestMapping;

import com.skcc.oops.kubernetes.OopsClient;

@OopsClient(name="books")
public interface BooksController {
	
	@RequestMapping("/harry")
    public Book get();
}