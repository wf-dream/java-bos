package com.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.ibatis.annotations.Param;

import com.domain.Customer;

public interface CustomerService {

	//查询未关联定区的客户
	@GET
	@Path("findNoAassociaCustomer")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> findNoAassociaCustomer();
	
	//查询已经关联定区的客户
	@GET
	@Path("findHasAassociaCustomerByFixedAreaId/{fixed_area_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> findHasAassociaCustomerByFixedAreaId(@PathParam("fixed_area_id")Integer fixed_area_id);
	
	@PUT
	@Path("assoctiateCutomerByFxiedArea")
	public void assoctiateCutomerByFxiedArea(@QueryParam("customerIds") String customerIds,@QueryParam("FixedAreaId") String FixedAreaId);
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void insert(Customer customer);
	
	@PUT
	@Path("updateTypeByTelephone")
	public void updateTypeByTelephone(String telephone);
			
}
