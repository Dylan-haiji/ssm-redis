package com.ssm.service;

import java.util.List;

import com.ssm.pojo.Order;
import com.ssm.pojo.User;

public interface OrderService {

	List<User> selectOrder(Order order);

}
