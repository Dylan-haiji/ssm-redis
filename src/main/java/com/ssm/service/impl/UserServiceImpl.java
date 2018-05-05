package com.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ssm.dao.UserMapper;
import com.ssm.pojo.User;
import com.ssm.service.UserService;

/**
 * 
 * @ClassName: UserServiceImpl  
 * @Description: 缓存机制说明：所有的查询结果都放进了缓存，也就是把MySQL查询的结果放到了redis中去，
 * 然后第二次发起该条查询时就可以从redis中去读取查询的结果，从而不与MySQL交互，从而达到优化的效果，
 * redis的查询速度之于MySQL的查询速度相当于 内存读写速度 /硬盘读写速度 
 * @Cacheable("a")注解的意义就是把该方法的查询结果放到redis中去，下一次再发起查询就去redis中去取，存在redis中的数据的key就是a；
 * @CacheEvict(value={"a","b"},allEntries=true) 的意思就是执行该方法后要清除redis中key名称为a,b的数据；  
 * @author yang hai ji
 * @date 2018年4月19日
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Cacheable("getUserById") //标注该方法查询的结果进入缓存，再次访问时直接读取缓存中的数据
	

	@Override
	public User getUserById(int userId) {
		return this.userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> findUsers(String keyWords) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getAllUser() {
		return this.userMapper.selectAllUser();
	}

	@Override
	public User toLongin(User user) {
//		User user = userMapper.toLongin(name,pws);
//		 if (user != null && user.getName().equals(name) && user.getPws().equals(pws)) {
//	            return user;
//	   }
//	   return null;
		return userMapper.toLongin(user);
	}

	/**/

}
