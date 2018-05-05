package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ssm.pojo.User;
@Component
public interface UserMapper {
	public User selectByPrimaryKey(int userId);

    public List<User> selectAllUser();

    public void insertUser(User user);

    public void deleteUser(int id);

    public List<User> findUsers(String keyWords);

    public void editUser(User user);

	//public User toLongin(@Param("name")String name,@Param("pws")String pws);
    public User toLongin(User user);


}
