package com.teja.kanban.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teja.kanban.Entity.Stage;
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
import com.teja.kanban.UserRepo.taskRepo;
import com.teja.kanban.UserRepo.userRepo;

@Service
public class UserService {
    @Autowired
	private userRepo userRepo;
    @Autowired
    private taskRepo taskRepo;
    @Autowired
    private ModelMapper modelMapper;
    
	
	
	


	public void create(UserRequest user) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		//List<Task> tasks = new ArrayList<Task>();
		
		User u = new User();
		 u = modelMapper.map(user, User.class);
		     User myuser = userRepo.save(u);
		
	         
	      List<TaskReq> tasks = user.getTasks();   
		  tasks.forEach(task->{
         	Task t = new Task();
			 t = modelMapper.map(task, Task.class);
			t.setUser(myuser);
			Task tt = taskRepo.save(t);
			
			//System.out.println(t.getUser());
		});
		
		
		
		
//		tasks.forEach(task->{
//			System.out.println("----------------");
//		Task t = modelMapper.map(task, Task.class);
//		
//		});
		
		
		
		
	
	
		
		//return user;
		
	}


	public UserResponse say() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		User user = userRepo.findById(2).get();
		UserResponse ur = modelMapper.map(user, UserResponse.class);
		return ur;
		
	}


	public List<TaskResponse> loadTasks(String projectName) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<Task> Tasks = taskRepo.findByUserName(projectName);
		List<TaskResponse> taskResponse  = new ArrayList<TaskResponse>();
		Tasks.forEach(task->{
			TaskResponse tr = modelMapper.map(task, TaskResponse.class);
			taskResponse.add(tr);
		});
		return taskResponse;
	}


	public UserResponse addProjectName(CreateUserRequest user) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		User u = new User();
		 u = modelMapper.map(user, User.class);
		  User myuser = userRepo.save(u);
		     UserResponse response = modelMapper.map(myuser, UserResponse.class);
		return response;
	}


	public TaskResponse addTaskName(String name, CreateTaskRequest task) {
	modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        
	 User user = userRepo.findByName(name);
	 Task t = modelMapper.map(task,Task.class);
	 t.setStage(Stage.PIPELINE);
	 t.setUser(user);
	 Task tt = taskRepo.save(t);
	
	 TaskResponse Tr = modelMapper.map(tt, TaskResponse.class);
	 return Tr;
	}


	public List<projectResponse> loadProjects() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		      List<User> projectList = userRepo.findAll();
		      List<projectResponse> projects= new ArrayList<projectResponse>();
		      projectList.forEach(project->{
		    	 
		    	  projectResponse pr = modelMapper.map(project, projectResponse.class);
		    	  pr.setLength(project.getTaskList().size());
		    	  projects.add(pr);
		      });
		      return projects;
	}


	public ResponseEntity<Void> deleteTask(int id) {
       Task task = taskRepo.findById(id).get();
		User user = task.getUser();
		user.getTaskList().remove(task);
		userRepo.save(user);
		System.out.println("----------"+ id + " --------");
		return ResponseEntity.noContent().build();
	}


	public ResponseEntity<Void> deleteProject(int id) {
		userRepo.deleteById(id);
		return ResponseEntity.noContent().build();
	}


	public TaskResponse moveTask(MoveTaskRequest movetask) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Task task2 = taskRepo.findById(movetask.getId()).get();
		Task task = modelMapper.map(movetask, Task.class);
		task.setUser(task2.getUser());
		taskRepo.save(task);
		TaskResponse tr = modelMapper.map(task, TaskResponse.class);
		return tr;
	}


	public TaskResponse loadTasks(int id) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Task task = taskRepo.findById(id).get();
		TaskResponse tr = modelMapper.map(task, TaskResponse.class);
		return tr;
	}

}
