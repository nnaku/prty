package fi.thepaardihub.dao.games.repository;

import org.springframework.data.repository.CrudRepository;

import fi.thepaardihub.dao.games.tables.Questions;

public interface QuestionsRepository extends CrudRepository<Questions, Integer>  {

}
