package com.vinay.service;

import java.util.List;

import com.vinay.dto.TaskDTO;

public interface TaskService {
		
	public TaskDTO saveTask(long userId, TaskDTO taskDTO);
	public List<TaskDTO> getAllTasks(long userId);
	public TaskDTO getTask(long userId, long taskid);
	public void deleteTask(long userId, long taskid);
}
