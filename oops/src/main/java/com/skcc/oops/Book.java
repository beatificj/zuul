package com.skcc.oops;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
	
	private String name;
	private Long price;
	private String author;

}
