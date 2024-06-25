package org.onlinebookstore.onlinebookstorebackend.repository;
import org.onlinebookstore.onlinebookstorebackend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Page<Book> findByTitleContaining(String keyword, Pageable pageable);
    List<Book> findTop5ByOrderBySalesDesc();
    Book findTopByOrderByIdDesc();
    Optional<Book> findById(Integer id);
}
