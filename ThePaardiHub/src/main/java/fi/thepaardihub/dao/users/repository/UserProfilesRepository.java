package fi.thepaardihub.dao.users.repository;

import org.springframework.data.repository.CrudRepository;

import fi.thepaardihub.dao.users.tables.UserProfiles;

public interface UserProfilesRepository extends CrudRepository<UserProfiles, Integer>{

}
