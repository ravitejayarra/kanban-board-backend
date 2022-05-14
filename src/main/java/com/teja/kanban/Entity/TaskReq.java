package com.teja.kanban.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskReq {

	
	
	private String name;
	private Stage stage; 
	private String deadline; 
}
