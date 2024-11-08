package org.onlinebookstore.microservice.repository;

import org.onlinebookstore.microservice.entity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

     Book findBookByTitle(String BookTitle);

    Book findBookByAuthor(String str);

}
