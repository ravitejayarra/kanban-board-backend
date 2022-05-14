package com.teja.kanban.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teja.kanban.Entity.Task;
import com.teja.kanban.Entity.TaskReq;
import com.teja.kanban.Entity.User;
import com.teja.kanban.Entity.UserRequest;
import com.teja.kanban.Entity.UserResponse;
import com.teja.kanban.Entity.Request.CreateTaskRequest;
import com.teja.kanban.Entity.Request.CreateUserRequest;
import com.teja.kanban.Entity.Request.MoveTaskRequest;
import com.teja.kanban.Entity.Response.TaskResponse;
import com.teja.kanban.Entity.Response.projectResponse;
import com.teja.kanban.Service.UserService;
import com.teja.kanban.UserRepo.userRepo;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {
	
@Autowired	
private UserService service;
	


  @GetMapping("/")
   public UserResponse say() {
    UserResponse ur = service.say();
	return ur;

      }

@GetMapping("/Tasks/{projectName}")
  public List<TaskResponse> loadTasks(@PathVariable String projectName) {
	List<TaskResponse> tasks = service.loadTasks(projectName);
	return tasks;
}

  @PostMapping("/Users/addUser")
  public UserResponse addProjectName(@RequestBody CreateUserRequest user) {
	  UserResponse response = service.addProjectName(user);
	  return response;
  }
  
  @GetMapping("/Projects")
  public List<projectResponse> loadprojects() {
	  List<projectResponse> loadProjects = service.loadProjects();
	  return loadProjects;
  }

  @PostMapping("/Tasks/addTask/{projectName}")
  public TaskResponse addTask(@PathVariable String projectName,@RequestBody CreateTaskRequest task) {
	  TaskResponse Tr = service.addTaskName(projectName,task);
	  return Tr;
  }

  
  @DeleteMapping("/Tasks/deleteTask/{id}")
  public ResponseEntity<Void> deleteTask(@PathVariable int id) {
	  return service.deleteTask(id);
	  
  }
  
  @DeleteMapping("/Projects/deleteProject/{id}")
  public ResponseEntity<Void> deleteProject(@PathVariable int id) {
	  return service.deleteProject(id);
  }
  
  
  @PutMapping("/Tasks/updateTask")
  public TaskResponse moveTask(@RequestBody MoveTaskRequest movetask) {
	  TaskResponse task = service.moveTask(movetask);
	  return task;
	  
  }
  
  @GetMapping("/Tasks/loadTask/{id}")
  public TaskResponse loadTask(@PathVariable int id) {
	  TaskResponse tr = service.loadTasks(id);
	  return tr;
  }
  

	
	
	@PostMapping("/create")
	public void createUser(@RequestBody UserRequest user) {
		
	/*	System.out.println(user.getName());
		List<TaskReq> tasks = user.getTasks();
		tasks.forEach(task->{
			System.out.println("----------");
			System.out.println(task.getDeadline());
		});
		 */
		service.create(user);
	
		
	}
	
	
}
