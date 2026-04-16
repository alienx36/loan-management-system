package com.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loan.entity.EmiSchedule;

public interface EmiScheduleRepository extends JpaRepository<EmiSchedule, Long> {
}