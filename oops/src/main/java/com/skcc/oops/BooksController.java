package com.skcc.oops;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="books", url="http://books:8080")
public interface BooksController {
	
	@RequestMapping("/harry")
    public Book get();
}