package fi.thepaardihub.dao.users.repository;

import org.springframework.data.repository.CrudRepository;

import fi.thepaardihub.dao.users.tables.UserAccounts;

public interface UserAccountsRepository extends CrudRepository<UserAccounts, String> {

}
