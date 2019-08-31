package com.skcc.oops;

import org.springframework.stereotype.Component;

import com.skcc.oops.Book;

@Component
public class BooksFallback implements BooksController {

	@Override
	public Book get() {
		return new Book("Potter", 10000L, "J. K. Rowling");
	}

}
