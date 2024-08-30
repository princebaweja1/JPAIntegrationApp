package com.prince.JPAIntegrationApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prince.JPAIntegrationApp.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}
