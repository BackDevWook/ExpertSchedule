package com.sprta.expertschedule.schedule.repository;

import com.sprta.expertschedule.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Override
    Optional<Schedule> findById(Long id);
}
