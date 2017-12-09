package fi.thepaardihub.dao.games.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fi.thepaardihub.dao.games.tables.Games;

import java.util.List;


public interface GamesRepository extends CrudRepository<Games, Integer> {
	
    @Query(value = "FROM Games WHERE author = :author")
    List<Games> findByAuthor(@Param("author") String author);
    
    @Query(value = "FROM Games WHERE id = :id AND author = :author")
    Games findById(@Param("id") int id, @Param("author") String author);
	
}
