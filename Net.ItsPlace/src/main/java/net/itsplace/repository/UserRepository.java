package net.itsplace.repository;

import net.itsplace.domain.Bascd;
import net.itsplace.user.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

	User findByMobile(String mobile);
	User findByPasswordLink(String passwordLink);
}
