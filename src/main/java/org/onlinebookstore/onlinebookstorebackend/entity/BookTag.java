package org.onlinebookstore.onlinebookstorebackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Node
public class BookTag {
    @Id
    @GeneratedValue
    private Long id;

    private String tagName;

    private List<Integer> bookIDs;

    private BookTag() {
    }

    public BookTag(String tagName) {
        this.tagName = tagName;
    }

    @Relationship(type = "RELATED_TO")
    public Set<BookTag> relateBookTags;


    public void addRelateBookTag(BookTag bookTag) {
        if (relateBookTags == null)
            relateBookTags = new HashSet<>();
        relateBookTags.add(bookTag);
    }

    public void addBookID(int id) {
        if (bookIDs == null)
            bookIDs = new ArrayList<>();
        for (Integer bookID : bookIDs) {
            if (bookID == id)
                return;
        }
        bookIDs.add(id);
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setBookIDs(List<Integer> bookIDs) {
        this.bookIDs = bookIDs;
    }

    public List<Integer> getBookIDs() {
        return bookIDs;
    }

    //为了避免死循环无限递归，比如A关联B，B关联了A，这样JSON就会出现死循环
    @JsonBackReference
    public Set<BookTag> getRelateBookTags() {
        return relateBookTags;
    }

    @JsonBackReference
    public void setRelateBookTags(Set<BookTag> relateBookTags) {
        this.relateBookTags = relateBookTags;
    }
}
