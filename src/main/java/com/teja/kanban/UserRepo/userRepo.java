package com.teja.kanban.UserRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teja.kanban.Entity.User;

public interface userRepo extends JpaRepository<User, Integer> {

	
	public User findByName(String name);
}
