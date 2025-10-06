package com.fisemployeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fisemployeeservice.model.PlanInfoModel;
import com.fisemployeeservice.model.PlanType;
import com.fisemployeeservice.model.ShortPlanYear;

@Repository
public interface PlanInfoRepository extends JpaRepository<PlanInfoModel, Integer>, JpaSpecificationExecutor<PlanInfoModel>{
	
	@Query("SELECT P FROM PlanInfoModel AS P WHERE P.empIdentNum = ?1")
	PlanInfoModel findByEmpIdentNumJPQL(String empIdentNum);
	
	@Query("SELECT P FROM PlanInfoModel AS P WHERE P.empIdentNum =:empIdentNum")
	PlanInfoModel findByJPQLNamedParams(@Param("empIdentNum") String empIdentNum);
	
	@Query(value = "select * from plan_dtls_tbl as p where p.employee_identification_number = ?1", nativeQuery = true)
	PlanInfoModel findByNativeSQL(String empIdentNum);
	
	@Query(value = "select * from plan_dtls_tbl as p where p.employee_identification_number =:empIdentNum", nativeQuery = true)
	PlanInfoModel findByNativeSQLNamedParams(@Param("empIdentNum") String empIdentNum);
	
	
	@Query(value = "INSERT INTO plan_dtls_tbl (employee_identification_number, "
			+ "plan_year_end, fiscal_year_end, "
			+ "short_plan_year, plan_type, "
			+ "plane_name, plan_name_line, "
			+ "irs_number, "
			+ "plan_contact_number, "
			+ "product_template_plan, "
			+ "product_id) "
			+ "VALUES "
			+ "(:empIdentNum, "
			+ ":planYearEnd, "
			+ ":fiscalYearEnd, "
			+ ":planYear, "
			+ ":planType, "
			+ ":planName, "
			+ ":planeNameLine1, "
			+ ":irsNum, "
			+ ":planContactNum, "
			+ ":proTempPlan, "
			+ ":productId)", nativeQuery = true)
	void insertPlanInfo(
	    @Param("empIdentNum") String empIdentNum,
	    @Param("planYearEnd") String planYearEnd,
	    @Param("fiscalYearEnd") String fiscalYearEnd,
	    @Param("planYear") String shortPlanYear,
	    @Param("planType") String planType,
	    @Param("planName") String planName,
	    @Param("planeNameLine1") String planeNameLine1,
	    @Param("irsNum") String irsNum,
	    @Param("planContactNum") String planContactNum,
	    @Param("proTempPlan") String proTempPlan,
	    @Param("productId") String productId
	);

	
}
