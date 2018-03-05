package fi.prty.dao.users.repository;

import org.springframework.data.repository.CrudRepository;

import fi.prty.dao.users.tables.UserRoles;

public interface UserRolesRepository extends CrudRepository<UserRoles, Integer>{

}
