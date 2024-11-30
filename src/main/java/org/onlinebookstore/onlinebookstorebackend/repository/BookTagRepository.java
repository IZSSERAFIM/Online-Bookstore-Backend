package org.onlinebookstore.onlinebookstorebackend.repository;

import org.onlinebookstore.onlinebookstorebackend.entity.BookTag;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookTagRepository extends Neo4jRepository<BookTag, Long>{
    @Query("MATCH (a:BookTag)-[:RELATED_TO]->(b) " +
            "WHERE a.tagName = $name " +
            "RETURN b "
    )
    List<BookTag> findNodeRelatedBookTagsDistance1(@Param("name") String name);

    @Query("MATCH (a:BookTag)-[:RELATED_TO]->(b)-[:RELATED_TO]->(c) " +
            "WHERE a.tagName = $name " +
            "RETURN c "
    )
    List<BookTag> findNodeRelatedBookTagsDistance2(@Param("name") String name);

    List<BookTag> findBookTagsByTagNameLike(String name);
}
