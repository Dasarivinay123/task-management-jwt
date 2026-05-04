package com.vinay.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vinay.dto.TaskDTO;
import com.vinay.entity.Task;
import com.vinay.entity.Users;
import com.vinay.exception.APIException;
import com.vinay.exception.TaskNotFoundException;
import com.vinay.exception.UserNotFoundException;
import com.vinay.repository.TaskRepository;
import com.vinay.repository.UserRepository;
import com.vinay.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public TaskDTO saveTask(long userId, TaskDTO taskDTO) {
		// TODO Auto-generated method stub
		Users users = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(String.format("User Id %d not found", userId)));
		Task task = modelMapper.map(taskDTO, Task.class);
		task.setUsers(users);
		Task savedTask = taskRepository.save(task);
		TaskDTO taskDTO2 = modelMapper.map(savedTask, TaskDTO.class);
		return taskDTO2;
	}

	@Override
	public List<TaskDTO> getAllTasks(long userId) {
		// TODO Auto-generated method stub
		Users users = userRepository.findById(userId)
		        .orElseThrow(() -> new UserNotFoundException(
		                String.format("User Id %d not found", userId)));
		
		List<Task> lisOfUsers = taskRepository.findAllByUsersId(userId);

		List<TaskDTO> listOfTaskDTOs = lisOfUsers.stream().map(
				task -> modelMapper.map(task, TaskDTO.class))
		.collect(Collectors.toList());
		
		return listOfTaskDTOs;
	}

	@Override
	public TaskDTO getTask(long userId, long taskid) {
		// TODO Auto-generated method stub

	    String email = SecurityContextHolder
	            .getContext()
	            .getAuthentication()
	            .getName();

		
		Users users = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException(String.format("User Id %d not found", userId)));
		Task tasks = taskRepository.findById(taskid)
		.orElseThrow(() -> new TaskNotFoundException(String.format("Task Id %d not found", taskid)));
		
		if(users.getId() != tasks.getUsers().getId()) {
			throw new APIException(String.format("Task Id %d not belongs to User Id %d",taskid, userId));
		}
		return modelMapper.map(tasks,TaskDTO.class);
		
	}

	@Override
	public void deleteTask(long userId, long taskId) {

	    String email = SecurityContextHolder
	            .getContext()
	            .getAuthentication()
	            .getName();

	    Users user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new UserNotFoundException("User not found"));

	    Task task = taskRepository.findById(taskId)
	            .orElseThrow(() -> new TaskNotFoundException(
	                    String.format("Task Id %d not found", taskId)));

	    if (!task.getUsers().getId().equals(user.getId())) {
	        throw new APIException("Unauthorized access");
	    }

	    taskRepository.delete(task);
	}

	@Override
	public TaskDTO updateTask(long userId, long taskId, TaskDTO taskDTO) {


		
		
	    //Get logged-in user from JWT (BEST PRACTICE)
	    String email = SecurityContextHolder
	            .getContext()
	            .getAuthentication()
	            .getName();
		System.out.println("JWT USER EMAIL: " + email);
		
	    Users user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new UserNotFoundException("User not found"));
	    
	    System.out.println("REQUEST USER ID: " + userId + "Autherized uderId: "+user.getId());
	    
	    //Get task
	    Task task = taskRepository.findById(taskId)
	            .orElseThrow(() -> new TaskNotFoundException(
	                    String.format("Task Id %d not found", taskId)));
	    
	    System.out.println("TASK OWNER ID: " + task.getUsers().getId());
	    //SECURITY CHECK (VERY IMPORTANT 🔥)
	    if (!task.getUsers().getId().equals(user.getId())) {
	        throw new APIException(
	                String.format("Task Id %d does not belong to logged-in user", taskId));
	    }

	    //Update fields
	    task.setTaskname(taskDTO.getTaskname());

	    //Save
	    Task updatedTask = taskRepository.save(task);

	    // Return DTO
	    return modelMapper.map(updatedTask, TaskDTO.class);
	}

}
