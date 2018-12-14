package com.nus.iss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nus.iss.model.CapsUser;


@Repository
public interface UserRepository extends JpaRepository<CapsUser, String> {

	@Query("SELECT u FROM CapsUser u WHERE u.id=:uid AND u.password=:pwd")
	CapsUser findUserByNamePwd(@Param("uid") String uid, @Param("pwd") String pwd);
}
