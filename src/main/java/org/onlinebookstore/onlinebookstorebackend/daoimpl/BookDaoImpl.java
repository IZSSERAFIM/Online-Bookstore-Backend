package org.onlinebookstore.onlinebookstorebackend.daoimpl;

import org.onlinebookstore.onlinebookstorebackend.dao.BookDao;
import org.onlinebookstore.onlinebookstorebackend.entity.Book;
import org.onlinebookstore.onlinebookstorebackend.entity.BookInfo;
import org.onlinebookstore.onlinebookstorebackend.entity.BookTag;
import org.onlinebookstore.onlinebookstorebackend.repository.BookRepository;
import org.onlinebookstore.onlinebookstorebackend.repository.BookInfoRepository;
import org.onlinebookstore.onlinebookstorebackend.repository.BookTagRepository;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BookDaoImpl implements BookDao {
    @Resource
    private BookRepository bookRepository;

    @Resource
    private BookInfoRepository bookInfoRepository;

    @Resource
    private BookTagRepository bookTagRepository;

    @Override
    public List<Book> getAllBooks(){
        List<Book> bookList = bookRepository.findAll();
        return bookList;
    }

    @Override
    public Book getBookById(Integer id){
        Book book = bookRepository.findById(id).orElse(null);
        BookInfo bookInfo = bookInfoRepository.findBookInfoById(id);
        if(bookInfo != null){
            book.setTitle(bookInfo.getTitle());
            book.setAuthor(bookInfo.getAuthor());
            book.setDescription(bookInfo.getDescription());
            book.setPrice(bookInfo.getPrice());
            book.setCover(bookInfo.getCover());
            book.setSales(bookInfo.getSales());
            book.setStock(bookInfo.getStock());
        }
        return book;
    }

    @Override
    public void save(Book book){
        bookRepository.save(book);
        BookInfo bookInfo = new BookInfo(book.getId(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getPrice(), book.getCover(), book.getSales(), book.getStock());
        bookInfoRepository.save(bookInfo);
    }

    @Override
    public void addBook(Book book){
        bookRepository.save(book);
        BookInfo bookInfo = new BookInfo(book.getId(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getPrice(), book.getCover(), book.getSales(), book.getStock());
        bookInfoRepository.save(bookInfo);
    }

    @Override
    public List<Book> findBooksByTagRelation(String tagName){
        // 先根据标签的名字取找对应的节点
        List<BookTag> list0 = bookTagRepository.findBookTagsByTagNameLike(tagName);
        HashMap<Integer, Integer> result = new HashMap<>();
        List<Book> resultBook = new ArrayList<>();
        // 对于上面找到的节点，把所有相关的BookID放入HashSet
        for (BookTag bookTag : list0) {
            for (int j = 0; j < bookTag.getBookIDs().size(); j++) {
                int id = bookTag.getBookIDs().get(j);
                result.put(id, 1);
            }
        }
        // 再查找一跳之内的list1保存，两跳的用list2保存，然后手动合并
        for (BookTag bookTag : list0) {
            String keyName = bookTag.getTagName();
            List<BookTag> list1 = bookTagRepository.findNodeRelatedBookTagsDistance1(keyName);
            List<BookTag> list2 = bookTagRepository.findNodeRelatedBookTagsDistance2(keyName);
            for (BookTag bookType : list1) {
                for (int j = 0; j < bookType.getBookIDs().size(); j++) {
                    int id = bookType.getBookIDs().get(j);
                    result.put(id, 1);
                }
            }
            for (BookTag bookType : list2) {
                for (int j = 0; j < bookType.getBookIDs().size(); j++) {
                    int id = bookType.getBookIDs().get(j);
                    result.put(id, 1);
                }
            }
        }
        // 合并之后根据bookid把所有的书返回到上层
        for(int id: result.keySet()){
            System.out.println("Processing book with ID: " + id);
            resultBook.add(this.getBookById(id));
        }
        return resultBook;
    }

    @Override
    public Book findByTitle(String title){
        return bookRepository.findByTitle(title);
    }
}