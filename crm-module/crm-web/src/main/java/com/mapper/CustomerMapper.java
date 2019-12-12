package com.mapper;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.apache.ibatis.annotations.Param;

import com.domain.Customer;

public interface CustomerMapper {
	
	public List<Customer> findNoAassociaCustomer();
	
	public List<Customer> findHasAassociaCustomerByFixedAreaId(Integer fixed_area_id);
	
	public void assoctiateCutomerByFxiedArea(@Param("customerId") Integer customerId,@Param("FixedAreaId") Integer FixedAreaId);
	
	public void cleanFexedArea(Integer fxiedAreaId);
	
	public void insert(Customer customer);
	
	public void updateTypeByTelephone(@QueryParam("telephone") String telephone);
}
