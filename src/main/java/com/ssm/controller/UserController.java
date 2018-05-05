package com.ssm.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.pojo.User;
import com.ssm.service.UserService;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("UserPage")
	public String longin(){
		return "UserPage";
	}
	
	@RequestMapping("toLongin")
	@ResponseBody
	public void toLongin(HttpServletRequest req,HttpServletResponse res)throws Exception{
		String uname = req.getParameter("name");
		String upws  = req.getParameter("pws");
		PrintWriter writer ;
		User user = new User();
		User users = userService.toLongin(user);
		//登录验证还没有做完
		if(users != null){
			System.out.println("ertyui");
		}else if(uname.equals(user.getName()) && upws.equals(user.getPws())){
			JSONArray jsonArr = JSONArray.fromObject(users);
			res.setCharacterEncoding("utf-8");
			writer= res.getWriter();
			writer.write(jsonArr.toString());
			writer.close();
		}
		
	}
	/**
	 * 
	 * @Title: showUser  
	 * @Description: 查询用户 
	 * @param @param model
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	@RequestMapping("showUser")
	public void showUser(Model model,HttpServletRequest req,HttpServletResponse res)throws Exception{
		System.out.println("----showUser----");
		List<User> userList = new ArrayList<User>();
		userList = userService.getAllUser();
		Jedis jedis = new Jedis("localhost");
       
		model.addAttribute("userList",userList);
		JSONArray jsonArr = JSONArray.fromObject(userList);
		 //设置 redis 字符串数据
        jedis.set("redis", userList.toString());
        //获取存储的数据并输出
		System.out.println("redis 存储的字符串为: "+ jedis.get("redis"));
		res.setCharacterEncoding("utf-8");
		PrintWriter writer = res.getWriter();
		writer.write(jsonArr.toString());
		writer.close();
		//return "showUser";
	}
	
	 /**
     * 增加一个用户
     * 
     * @param userName
     * @param sex
     * @param age
     * @return
     */
    @RequestMapping(value = "/addUser")
    @ResponseBody
    public ModelMap addUser(String name,HttpServletRequest req,HttpServletResponse res)throws Exception{
        System.out.println("******addUser********");
        
        User user = new User();
        user.setName(name);
        userService.insertUser(user);
        Jedis jedis = new Jedis("localhost");
        //设置 redis 字符串数据
        jedis.set("addRedis", user.toString());
        //获取存储的数据并输出
		System.out.println("redis 存储的字符串为: "+ jedis.get("addRedis"));
        ModelMap model = new ModelMap();
        model.addAttribute("result", "添加成功");
        JSONArray jsonArr = JSONArray.fromObject(user);
		res.setCharacterEncoding("utf-8");
		PrintWriter writer = res.getWriter();
		writer.write(jsonArr.toString());
		writer.close();
        return model;
    }

    /**
     * 通过userID删除用户
     * 
     * @param userID
     */
    @RequestMapping(value = "/delUser/{userID}", method = RequestMethod.GET)
    public ModelAndView delUser(@PathVariable int userID,HttpServletRequest req,HttpServletResponse res)throws Exception{
        System.out.println(userID);
        userService.deleteUser(userID);
        ModelAndView mv = new ModelAndView();
        List<User> userList = new ArrayList<User>();
        userList = userService.getAllUser();
        mv.addObject("userList", userList); // 填充数据到model
        mv.setViewName("showUser");
        return mv;
    }

    /**
     * 查询用户
     * 
     * @param model
     * @param keyWords
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String findUsers(Model model, String keyWords) {
        System.out.println(keyWords);
        List<User> userList = new ArrayList<User>();
        userList = userService.findUsers(keyWords);
        model.addAttribute("userList", userList); // 填充数据到model
        return "showUser";
    }
    
    /**
     * 更新用户信息
     * @param userName
     * @param sex
     * @param age
     * @param id
     * @return
     */
    @RequestMapping(value="editUser",method=RequestMethod.POST)
    public ModelAndView editUser(String name, int id) {
        System.out.println("---------");
        User user = new User();
        user.setName(name);
        user.setId(id);
        userService.editUser(user);
        ModelAndView mv = new ModelAndView();
        List<User> userList = new ArrayList<User>();
        userList = userService.getAllUser();
        mv.addObject("userList", userList); // 填充数据到model
        mv.setViewName("redirect:/UserCRUD/showUser");
        return mv;
    }
	
}
