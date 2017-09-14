package fi.thepaardihub.dao.games.repository;

import org.springframework.data.repository.CrudRepository;

import fi.thepaardihub.dao.games.tables.Games;

public interface GamesRepository extends CrudRepository<Games,Integer>{

}
