package com.teja.kanban.Entity.Response;

import com.teja.kanban.Entity.Stage;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TaskResponse {
    private int id;
	private String name;
	private Stage stage; 
	private String deadline; 
}
