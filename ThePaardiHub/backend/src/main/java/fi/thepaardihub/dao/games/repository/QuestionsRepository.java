package fi.thepaardihub.dao.games.repository;

import org.springframework.data.repository.CrudRepository;

import fi.thepaardihub.dao.games.tables.Questions;
import fi.thepaardihub.rest.jsonobject.Question;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;

public interface QuestionsRepository extends CrudRepository<Questions, Integer>  {
   @Query("from Questions q where q.author=:email")
   public ArrayList<Question> findByAuthor(String email);
   
   @Query("from Questions q where q.id=:id")
   public Question findByID(String id);
}
