package com.ssm.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.pojo.Order;
import com.ssm.pojo.User;
import com.ssm.service.OrderService;

import net.sf.json.JSONArray;
/**
 * 
 * @ClassName: OrderController  
 * @Description: 用户详情信息
 * @author yang hai ji
 * @date 2018年5月4日
 */
@RequestMapping("/order/")
@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	/**
	 * 
	 * @Title: toWelcomePage  
	 * @Description: 显示用户
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	@RequestMapping("orderPage")
	public String toWelcomePage(){
		return "orderPage";
	}
	
	/**
	 * 
	 * @Title: selectOrder  
	 * @Description: 查询操作  
	 * @param @param req
	 * @param @param res
	 * @param @param order
	 * @param @throws Exception    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	@RequestMapping("selectOrder")
	@ResponseBody
	public void selectOrder(HttpServletRequest req,HttpServletResponse res,Order order)throws Exception{
		List<User> user = orderService.selectOrder(order);
		JSONArray jsonArr = JSONArray.fromObject(user);
		res.setCharacterEncoding("utf-8");
		PrintWriter writer = res.getWriter();
		writer.write(jsonArr.toString());
		writer.close();
	}
	
}
