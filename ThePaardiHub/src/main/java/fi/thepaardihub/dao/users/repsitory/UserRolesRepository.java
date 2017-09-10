package fi.thepaardihub.dao.users.repsitory;

import org.springframework.data.repository.CrudRepository;

import fi.thepaardihub.dao.users.tables.UserRoles;

public interface UserRolesRepository extends CrudRepository<UserRoles, Integer>{

}
