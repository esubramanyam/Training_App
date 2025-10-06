package com.fisemployeeservice.service;

import java.sql.BatchUpdateException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;

import com.fisemployeeservice.constants.SQLConstant;
import com.fisemployeeservice.dto.PlanInfoDto;
import com.fisemployeeservice.model.PlanInfoModel;
import com.fisemployeeservice.model.PlanType;
import com.fisemployeeservice.model.ShortPlanYear;
import com.fisemployeeservice.repository.PlanInfoRepository;
import com.linecorp.armeria.server.annotation.Trace;


@Service
@ComponentScan(basePackages = "com.fisemployeeservice.repository")
public class PlanInfoServiceImpl implements PlanInfoService {
	
	@Autowired
	public PlanInfoRepository planInfoRepo;
	
	@Autowired
	public TransactionManager transactionManager;
	
	@Autowired
	public org.springframework.transaction.support.TransactionTemplate transactionTemplate;
	
	public final JdbcTemplate jdbcTemplate;
	
	public PlanInfoServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	/**
	 * Can be used instead of Autowired
	 */
//	public PlanInfoServiceImpl(PlanInfoRepository planInfoRepo) {
//		//super();
//		this.planInfoRepo = planInfoRepo;
//	}

	@Override
	public PlanInfoModel addPlanInfo(PlanInfoDto planInfoDto) {
		PlanInfoModel planInfoEntity = mapEntityAndDto(planInfoDto);
		PlanInfoModel model = null;
		try {
			model = planInfoRepo.save(planInfoEntity);
			return model;
		}
		catch(Exception ex) {
			System.out.println("The error: "+ex.getMessage()+"\n"+ ex);
			return null;
		}
	}
	@Override
	public boolean addPlanInfoFromFile(PlanInfoModel entity) {
		//PlanInfoModel entity = mapEntityAndDto(planInfoDto);
		boolean isSaved = false;
		PlanInfoModel model = null;
		String sql = SQLConstant.BATCH_UPPDATE_QUERY;
		try {
			jdbcTemplate.update(
					entity.getEmpIdentNum(), 
					entity.getPlanYearEnd(), 
					entity.getFiscalYearEnd(),
					entity.getPlanYear().toString(), 
					entity.getPlanType().name(), 
					entity.getPlanYearEnd(),
					entity.getPlaneNameLine1(),
					entity.getIrsNum(), 
					entity.getPlanContactNum(), 
					entity.getProTempPlan(), 
					entity.getProductId());
			return isSaved=true;
		}
		catch(Exception ex) {
			System.out.println("The error: "+ex.getMessage()+"\n"+ ex);
			return isSaved=false;
		}
	}

	@Override
	public PlanInfoModel getEmployyeDetailsByPlanId(int planId) {
		Optional<PlanInfoModel> requestModel = planInfoRepo.findById(planId);
		if(requestModel.isPresent()) {
			return requestModel.get();
		}
		return null;
	}

	@Override
	public PlanInfoModel updatePlanInfo(int planId, PlanInfoDto planInfoDto) {
		PlanInfoModel planInfoEntity = mapEntityAndDto(planInfoDto);
		PlanInfoModel model = null;
		model = planInfoRepo.save(planInfoEntity);
		if(model != null) {
			return model;
		}
		return null;
	}
	
	@Override
	public boolean deletePlanById(int planId) {
		Optional<PlanInfoModel> requestModel = planInfoRepo.findById(planId);
		if(requestModel.isPresent()) {
			planInfoRepo.deleteById(planId);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public List<PlanInfoModel> fetchAllRecords(){
		List<PlanInfoModel> model = planInfoRepo.findAll();
		if(model != null) {
			return model;
		}
		return null;
	}
	
	public PlanInfoModel mapEntityAndDto(PlanInfoDto planInfoDto) {
		PlanInfoModel entity = new PlanInfoModel();
		
		entity.setEmpIdentNum(planInfoDto.getEmpIdentNum());
		entity.setFiscalYearEnd(planInfoDto.getFiscalYearEnd());
		entity.setPlanYearEnd(planInfoDto.getPlanYearEnd());
		entity.setIrsNum(planInfoDto.getIrsNum());
		entity.setPlanContactNum(planInfoDto.getPlanContactNum());
		entity.setPlaneNameLine1(planInfoDto.getPlaneNameLine1());
		entity.setPlanName(planInfoDto.getPlanName());
		

		entity.setPlanType(PlanType.fromPlan(planInfoDto.getPlanType()));
		entity.setPlanYear(ShortPlanYear.fromShortYearPlan(planInfoDto.getPlanYear()));
		
		entity.setProductId(planInfoDto.getProductId());
		entity.setProTempPlan(planInfoDto.getProTempPlan());
		
		return entity;
	}
	

	public void insertDataIntoRequestTable(List<PlanInfoModel> request) {
		String sql = SQLConstant.BATCH_UPPDATE_QUERY;
		int[] batchSize = null;

		try {
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setString(1, request.get(i).getEmpIdentNum());
					ps.setString(2, request.get(i).getPlanYearEnd());
					ps.setString(3, request.get(i).getFiscalYearEnd());
					ps.setString(4, request.get(i).getPlanYear().toString());
					ps.setString(5, request.get(i).getPlanType().name().toString());
					ps.setString(6, request.get(i).getPlanYearEnd());
					ps.setString(7, request.get(i).getPlaneNameLine1());
					ps.setString(8, request.get(i).getIrsNum());
					ps.setString(9, request.get(i).getPlanContactNum());
					ps.setString(10, request.get(i).getProTempPlan());
					ps.setString(11, request.get(i).getProductId());
				}

				@Override
				public int getBatchSize() {
					return request.size();
				}
			});
		} catch (DataAccessException ex) {
			Throwable cause = ex.getCause();
			if(cause instanceof BatchUpdateException) {
				int[] updateCount = ((BatchUpdateException) cause).getUpdateCounts();
				//Logger
				for(int i=0; i < updateCount.length; i++ ) {
					if(updateCount[i] == Statement.EXECUTE_FAILED) {
						System.out.println("Statement " + (i+1)+ ": FAILED");
					}
				}
			}
		}
		catch(Exception e) {
			//logger
		}
	}
	
	@Transactional
	public void insertDataIntoRequestTableTransactional(List<PlanInfoModel> request) {
		String sql = SQLConstant.BATCH_UPPDATE_QUERY;
		int[] batchSize = null;

		try {
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setString(1, request.get(i).getEmpIdentNum());
					ps.setString(2, request.get(i).getPlanYearEnd());
					ps.setString(3, request.get(i).getFiscalYearEnd());
					ps.setString(4, request.get(i).getPlanYear().toString());
					ps.setString(5, request.get(i).getPlanType().name().toString());
					ps.setString(6, request.get(i).getPlanYearEnd());
					ps.setString(7, request.get(i).getPlaneNameLine1());
					ps.setString(8, request.get(i).getIrsNum());
					ps.setString(9, request.get(i).getPlanContactNum());
					ps.setString(10, request.get(i).getProTempPlan());
					ps.setString(11, request.get(i).getProductId());
				}

				@Override
				public int getBatchSize() {
					return request.size();
				}
			});
		} catch (DataAccessException ex) {
			Throwable cause = ex.getCause();
			if(cause instanceof BatchUpdateException) {
				int[] updateCount = ((BatchUpdateException) cause).getUpdateCounts();
				//Logger
				for(int i=0; i < updateCount.length; i++ ) {
					if(updateCount[i] == Statement.EXECUTE_FAILED) {
						System.out.println("Statement " + (i+1)+ ": FAILED");
					}
				}
			}
			throw ex;
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public String insertDataIntoRequestTableTransactionTemplate(List<PlanInfoModel> request) {
	    String sql = SQLConstant.BATCH_UPPDATE_QUERY;

	    return transactionTemplate.execute(new TransactionCallback<String>() {
	        @Override
	        public String doInTransaction(TransactionStatus action) {
	            try {
	                jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
	                    @Override
	                    public void setValues(PreparedStatement ps, int i) throws SQLException {
	                        PlanInfoModel model = request.get(i);
	                        ps.setString(1, model.getEmpIdentNum());
	                        ps.setString(2, model.getPlanYearEnd());
	                        ps.setString(3, model.getFiscalYearEnd());
	                        ps.setString(4, model.getPlanYear().toString());
	                        ps.setString(5, model.getPlanType().name());
	                        ps.setString(6, model.getPlanYearEnd());
	                        ps.setString(7, model.getPlaneNameLine1());
	                        ps.setString(8, model.getIrsNum());
	                        ps.setString(9, model.getPlanContactNum());
	                        ps.setString(10, model.getProTempPlan());
	                        ps.setString(11, model.getProductId());
	                    }

	                    @Override
	                    public int getBatchSize() {
	                        return request.size();
	                    }
	                });
	                return "Insertion successful";
	            } catch (Exception ex) {
	            	action.setRollbackOnly();

	    			Throwable cause = ex.getCause();
	    			if(cause instanceof BatchUpdateException) {
	    				int[] updateCount = ((BatchUpdateException) cause).getUpdateCounts();
	    				//Logger
	    				for(int i=0; i < updateCount.length; i++ ) {
	    					if(updateCount[i] == Statement.EXECUTE_FAILED) {
	    						System.out.println("Statement " + (i+1)+ ": FAILED");
	    					}
	    				}
	    			}
	    		
	                return "Insertion failed: " + ex.getMessage();
	            }
	        }
	    });
	}

	
	//*******************************************************
	@Transactional
	public boolean insertDataFromFile(List<PlanInfoModel> request) {
		String sql = SQLConstant.BATCH_UPPDATE_QUERY;
		int[] batchSize = null;
		boolean isSaved = false;
		try {
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setString(1, request.get(i).getEmpIdentNum());
					ps.setString(2, request.get(i).getPlanYearEnd());
					ps.setString(3, request.get(i).getFiscalYearEnd());
					ps.setString(4, request.get(i).getPlanYear().toString());
					ps.setString(5, request.get(i).getPlanType().name().toString());
					ps.setString(6, request.get(i).getPlanYearEnd());
					ps.setString(7, request.get(i).getPlaneNameLine1());
					ps.setString(8, request.get(i).getIrsNum());
					ps.setString(9, request.get(i).getPlanContactNum());
					ps.setString(10, request.get(i).getProTempPlan());
					ps.setString(11, request.get(i).getProductId());
				}

				@Override
				public int getBatchSize() {
					return request.size();
				}
			});
			isSaved = true;
		} catch (DataAccessException ex) {
			Throwable cause = ex.getCause();
			if(cause instanceof BatchUpdateException) {
				int[] updateCount = ((BatchUpdateException) cause).getUpdateCounts();
				//Logger
				for(int i=0; i < updateCount.length; i++ ) {
					if(updateCount[i] == Statement.EXECUTE_FAILED) {
						System.out.println("Statement " + (i+1)+ ": FAILED");
					}
				}
			}
			throw ex;
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		return isSaved;
	}

}
