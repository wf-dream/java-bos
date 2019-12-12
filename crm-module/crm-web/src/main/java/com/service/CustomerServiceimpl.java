package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.Customer;
import com.mapper.CustomerMapper;
@Service
@Transactional
public class CustomerServiceimpl implements CustomerService {
	
	@Resource
	private CustomerMapper customerMapper;
	
	@Override
	public List<Customer> findNoAassociaCustomer() {
		//System.out.println(customerMapper.findNoAassociaCustomer());
		return customerMapper.findNoAassociaCustomer();
	}

	@Override
	public List<Customer> findHasAassociaCustomerByFixedAreaId(Integer fixed_area_id) {			
		return customerMapper.findHasAassociaCustomerByFixedAreaId(fixed_area_id);
	}

	@Override
	public void assoctiateCutomerByFxiedArea(String customerIds, String FixedAreaId) {
		//清除定区客户
		customerMapper.cleanFexedArea(Integer.parseInt(FixedAreaId));
		String[] customerArry=customerIds.split(",");
		for (String customerId : customerArry) {
			customerMapper.assoctiateCutomerByFxiedArea(Integer.parseInt(customerId), Integer.parseInt(FixedAreaId));
		}
	}

	@Override
	public void insert(Customer customer) {
		customerMapper.insert(customer);		
	}

	@Override
	public void updateTypeByTelephone(String telephone) {
		customerMapper.updateTypeByTelephone(telephone);		
	}

}
