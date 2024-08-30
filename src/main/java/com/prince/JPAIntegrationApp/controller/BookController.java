package com.prince.JPAIntegrationApp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prince.JPAIntegrationApp.entity.Book;
import com.prince.JPAIntegrationApp.service.BookService;



@RestController
public class BookController { 

	@Autowired
	public BookService bookService;

	@GetMapping("/books")
	public ResponseEntity<?> getBooks() {
		System.out.println("getBooks() method is handling GET request to fetch all the books");
		List<Book> resultBooksList = this.bookService.getAllBooks();	
		if(resultBooksList.size()<=0) {
			System.out.println("size is less than or equal to 0");
			return new ResponseEntity<>(resultBooksList , HttpStatus.NO_CONTENT);
		}
		else {
			System.out.println("size is greater than 0");
			return new ResponseEntity<>(resultBooksList , HttpStatus.OK);
		}
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<?> getBook(@PathVariable int id) {
		System.out.println("getBook() method is handling GET request with given id");
		Book book =  this.bookService.getBook(id);
		if(book!=null) {
			return new ResponseEntity<>(book , HttpStatus.OK);
		}else {
			String messageString = "Book not found with the given id " + id;
			return new ResponseEntity<>(messageString , HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/books/add")
	public ResponseEntity<String> addBook(@RequestBody Book b) {
		System.out.println("addBook() method is handling Post request");
		Book addedBook =  this.bookService.createBook(b); 
		if(addedBook == null) {
			String messageString = "Record with given id " + b.getId() + " is already exists.";
			// HttpStatus.CONFLICT
			return new ResponseEntity<>(messageString , HttpStatus.CONFLICT);
		}else{
			String messageString = "Record with given id " + b.getId() + " added successfully.";
			return new ResponseEntity<>(messageString , HttpStatus.CREATED);
		}
	}
	
	
	@PutMapping("/books/update/{id}")
	public ResponseEntity<?> updateBook(@RequestBody Book b, @PathVariable int id) {
		
		if(b.getId()!=id) {
			String messageString = "Resource id {" + id + "} does not match with the request body id {" + b.getId() + "}";
			return new ResponseEntity<>(messageString, HttpStatus.BAD_REQUEST);
		}
			
		System.out.println("updateBook() method is handling PUT request");
		boolean isUpdated = this.bookService.updateBook(b, id);
		if( isUpdated ){
			return new ResponseEntity<>(b,HttpStatus.OK);
		} else {
			String messageString = "Book not found with the given id " + id;
	        return new ResponseEntity<>(messageString, HttpStatus.NOT_FOUND);
	    }
	}
}

