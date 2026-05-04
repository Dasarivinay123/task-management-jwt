package com.vinay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinay.entity.Task;
import com.vinay.entity.Users;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findAllByUsersId(long userId);

}
