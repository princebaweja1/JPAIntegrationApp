package com.prince.JPAIntegrationApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.prince.JPAIntegrationApp.Repository.BookRepository;
import com.prince.JPAIntegrationApp.entity.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	public List<Book> getAllBooks(){
		return this.bookRepository.findAll();
		
	}
	
	public Book getBook(int id) {
		Optional<Book> resultBook = this.bookRepository.findById(id);
		if(resultBook.isEmpty()) {
			return null;
		}else {
			return resultBook.get();
		}
	}	
	
	public Book createBook(Book b) {
		if (this.bookRepository.existsById(b.getId())){
			return null;
		}
		return this.bookRepository.save(b);
	}
	
	
	public boolean updateBook(Book b, int id) {
		boolean updatedFlag = false;
		if (this.bookRepository.existsById(b.getId())){
			bookRepository.save(b);
			updatedFlag = true;
		}
		return updatedFlag;
	}

}
