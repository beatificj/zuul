package com.skcc.books;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {
	
	@RequestMapping("/harry")
    public Book get() {
		
		return new Book("Harry Potter", 10000L, "J. K. Rowling");
    }
	
	@RequestMapping("/hub/harry")
    public Book getHarry() {
		
		return new Book("Harry Potter", 8000L, "J. K. Rowling");
    }
}