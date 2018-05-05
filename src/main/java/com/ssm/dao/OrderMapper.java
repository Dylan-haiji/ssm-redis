package com.ssm.dao;

import java.util.List;

import com.ssm.pojo.Order;
import com.ssm.pojo.User;

public interface OrderMapper {

	List<User> selectOrder(Order order);

}
