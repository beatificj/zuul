package com.skcc.oops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import feign.Client;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OopsController {

	@Autowired
	Client client;
	
	@Autowired
	BooksController books;
	
	@Autowired
	AmazonController amazon;
	
	@RequestMapping("/harry")
    public Book get() {
		log.error("client [{}]", client.getClass());
		log.error("book[{}]", books.getClass());
		return books.get();
    }
	
	@RequestMapping("/amazon/harry")
    public Book getHarry() {
		log.error("client [{}]", client.getClass());
		log.error("book[{}]", books.getClass());
		return amazon.get();
    }
}
