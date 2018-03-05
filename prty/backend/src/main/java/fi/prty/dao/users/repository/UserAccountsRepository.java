package fi.prty.dao.users.repository;

import org.springframework.data.repository.CrudRepository;

import fi.prty.dao.users.tables.UserAccounts;

public interface UserAccountsRepository extends CrudRepository<UserAccounts, String> {

}
