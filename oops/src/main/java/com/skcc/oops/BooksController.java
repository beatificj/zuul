package com.skcc.oops;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="books")
public interface BooksController {
	
	@RequestMapping("/harry")
    public Book get();
}