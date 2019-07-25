package com.skcc.oops;

import org.springframework.web.bind.annotation.RequestMapping;

import com.skcc.oops.kubernetes.OopsClient;

@OopsClient(name="oops", method="mcg")
public interface BooksController {
	
	@RequestMapping("/harry")
    public Book get();
}