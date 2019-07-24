package com.skcc.oops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OopsController {

	@Autowired
	BooksController books;
	
	@RequestMapping("/harry")
    public Book get() {
		
		return books.get();
    }
}
