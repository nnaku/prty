package fi.thepaardihub.dao.games.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fi.thepaardihub.dao.games.tables.Questions;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

public interface QuestionsRepository extends CrudRepository<Questions, Integer>  {
	
    @Query(value = "FROM Questions WHERE author = :author")
    List<Questions> findByAuthor(@Param("author") String author);
    
    @Query(value = "FROM Questions WHERE id = :id AND author = :author")
    Questions findById(@Param("id") int id, @Param("author") String author);
}
