package com.teja.kanban.UserRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teja.kanban.Entity.Task;
@Repository
public interface taskRepo extends JpaRepository<Task, Integer> {

	public List<Task> findByUserName(String name);
	
}
