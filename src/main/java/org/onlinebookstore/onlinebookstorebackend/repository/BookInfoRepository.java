package org.onlinebookstore.onlinebookstorebackend.repository;
import org.onlinebookstore.onlinebookstorebackend.entity.BookInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookInfoRepository extends MongoRepository<BookInfo, Integer> {
    BookInfo findBookInfoById (Integer id);
}
