package com.emilpiekos.tasksapplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t ORDER BY t.expireDate")
    List<Task> findAllOrderByExpireDate();

    List<Task> findAllByCompletedIsTrueOrderByExpireDate();

    List<Task> findAllByCompletedIsFalseOrderByExpireDate();
}
