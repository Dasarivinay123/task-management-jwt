package com.vinay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinay.dto.TaskDTO;
import com.vinay.payload.ApiResponse;
import com.vinay.service.TaskService;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping("/{userId}/tasks")
	public ResponseEntity<ApiResponse<TaskDTO>> saveTask(
			@PathVariable(name = "userId") long userId,
	        @RequestBody TaskDTO taskDTO) {

	    TaskDTO savedTask = taskService.saveTask(userId, taskDTO);

	    ApiResponse<TaskDTO> response = new ApiResponse<>(
	            true,
	            201,
	            "Task created successfully",
	            savedTask
	    );

	    return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	@GetMapping("/{userId}/tasks")
	public ResponseEntity<ApiResponse<List<TaskDTO>>> getAllTasks(
			 @PathVariable(name = "userId") long userId) {
		 System.out.println("API HIT SUCCESS");
	    List<TaskDTO> allTasks = taskService.getAllTasks(userId);

	    ApiResponse<List<TaskDTO>> response = new ApiResponse<>(
	            true,
	            200,
	            "Tasks fetched successfully",
	            allTasks
	    );

	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{userId}/tasks/{taskId}")
	public ResponseEntity<ApiResponse<TaskDTO>> getTask(
	        @PathVariable(name = "userId") long userId,
	        @PathVariable(name = "taskId") long taskId) {

	    TaskDTO taskDTO = taskService.getTask(userId, taskId);

	    ApiResponse<TaskDTO> response = new ApiResponse<>(
	            true,
	            200,
	            "Task fetched successfully",
	            taskDTO
	    );

	    return ResponseEntity.ok(response);
	}
	@PutMapping("/{userId}/tasks/{taskId}")
	public ResponseEntity<ApiResponse<TaskDTO>> updateTask(
	        @PathVariable("userId") long userId,
	        @PathVariable("taskId") long taskId,
	        @RequestBody TaskDTO taskDTO) {

	    TaskDTO updatedTask = taskService.updateTask(userId, taskId, taskDTO);

	    ApiResponse<TaskDTO> response = new ApiResponse<>(
	            true,
	            200,
	            "Task updated successfully",
	            updatedTask
	    );

	    return ResponseEntity.ok(response);
	}
	@DeleteMapping("/{userId}/tasks/{taskId}")
	public ResponseEntity<ApiResponse<String>> deleteTask(
	        @PathVariable("userId") long userId,
	        @PathVariable("taskId") long taskId) {
	    taskService.deleteTask(userId, taskId);

	    ApiResponse<String> response = new ApiResponse<>(
	            true,
	            200,
	            "Task deleted successfully",
	            null
	    );

	    return ResponseEntity.ok(response);
	}
}
