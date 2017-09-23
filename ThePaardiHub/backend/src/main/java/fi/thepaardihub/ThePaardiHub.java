package fi.thepaardihub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fi.thepaardihub.dao.users.UsersDao;
import fi.thepaardihub.dao.users.repository.UserAccountsRepository;
import fi.thepaardihub.dao.users.tables.UserAccounts;
import fi.thepaardihub.dao.users.tables.UserRoles;

@SpringBootApplication
public class ThePaardiHub {

	public static void main(String[] args) {
		SpringApplication.run(ThePaardiHub.class, args);

	}

}
