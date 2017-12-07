package fi.thepaardihub.dao.games.repository;

import org.springframework.data.repository.CrudRepository;

import fi.thepaardihub.dao.games.tables.Games;
import fi.thepaardihub.rest.jsonobject.GameSet;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;

public interface GamesRepository extends CrudRepository<Games,Integer>{
   @Query("from Games q where q.author=:email")
   public ArrayList<GameSet> findByAuthor(String email);
   
   @Query("from Games q where q.id=:id")
   public GameSet findByID(String id);

}
