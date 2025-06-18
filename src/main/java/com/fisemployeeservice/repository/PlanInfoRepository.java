package com.fisemployeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fisemployeeservice.model.PlanInfoModel;

@Repository
public interface PlanInfoRepository extends JpaRepository<PlanInfoModel, Integer>{
}
