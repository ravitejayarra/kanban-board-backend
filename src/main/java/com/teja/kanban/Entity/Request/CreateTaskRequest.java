package com.teja.kanban.Entity.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskRequest {
	private String name;
	
	private String deadline;
}
