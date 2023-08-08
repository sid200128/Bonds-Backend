package com.bonds.backend.repository;

import com.bonds.backend.models.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Integer> {

}
