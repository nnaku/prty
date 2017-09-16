package fi.thepaardihub.dao.users.repository;

import org.springframework.data.repository.CrudRepository;

import fi.thepaardihub.dao.users.tables.UserRoles;

public interface UserRolesRepository extends CrudRepository<UserRoles, Integer>{

}
