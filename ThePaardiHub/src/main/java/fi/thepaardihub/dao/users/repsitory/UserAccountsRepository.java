package fi.thepaardihub.dao.users.repsitory;

import org.springframework.data.repository.CrudRepository;

import fi.thepaardihub.dao.users.tables.UserAccounts;

public interface UserAccountsRepository extends CrudRepository<UserAccounts, Integer> {

}
