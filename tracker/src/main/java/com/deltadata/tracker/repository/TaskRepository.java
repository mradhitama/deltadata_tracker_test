package com.deltadata.tracker.repository;

import com.deltadata.tracker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    public Optional<Task> findByTitle(String title);
}
