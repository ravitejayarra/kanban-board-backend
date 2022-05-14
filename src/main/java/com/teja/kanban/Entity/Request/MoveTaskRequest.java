package com.teja.kanban.Entity.Request;

import com.teja.kanban.Entity.Stage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoveTaskRequest {
    
	private int id;
    private String name;
	private Stage stage;
	private String deadline;
	
}
