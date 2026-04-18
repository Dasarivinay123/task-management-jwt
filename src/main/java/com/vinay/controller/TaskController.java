package com.vinay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinay.dto.TaskDTO;
import com.vinay.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping("/{userId}/tasks")
	public ResponseEntity<TaskDTO> saveTask(@PathVariable("userId") long id, @RequestBody TaskDTO taskDTO) {
		TaskDTO saveTask = taskService.saveTask(id, taskDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveTask);
	}
	@GetMapping("/{userId}/tasks")
	public ResponseEntity<List<TaskDTO>> getAllTasks(@PathVariable(name = "userId") long userId){
		List<TaskDTO> allTasks = taskService.getAllTasks(userId);
		return ResponseEntity.ok(allTasks);
	}
	
	@GetMapping("/{userId}/tasks/{taskId}")
	public ResponseEntity<TaskDTO> getTask(
			@PathVariable(name = "userId") long userId,
			@PathVariable(name = "taskId") long taskid){
		TaskDTO taskDTO = taskService.getTask(userId,taskid);
		return  ResponseEntity.ok(taskDTO);
		
	}
	@DeleteMapping("/{userId}/tasks/{taskId}")
	public ResponseEntity<String> deleteTask(
			@PathVariable(name = "userId") long userId,
			@PathVariable(name = "taskId") long taskid
			) {
		taskService.deleteTask(userId, taskid);
		
	    return ResponseEntity.ok("Task deleted successfully");
	}
}
