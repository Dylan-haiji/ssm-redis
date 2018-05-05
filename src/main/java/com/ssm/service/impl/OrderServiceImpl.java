package com.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.dao.OrderMapper;
import com.ssm.pojo.Order;
import com.ssm.pojo.User;
import com.ssm.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public List<User> selectOrder(Order order) {
		return orderMapper.selectOrder(order);
	}

}
