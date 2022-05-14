package com.teja.kanban.Entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class UserRequest {

	private String name;
 	private List<TaskReq> tasks;
	
	
}
