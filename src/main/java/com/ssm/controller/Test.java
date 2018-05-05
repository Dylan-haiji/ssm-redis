package com.ssm.controller;

import redis.clients.jedis.Jedis;

public class Test {
	public static void main(String[] args) {
		//连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        //设置 redis 字符串数据Exception in thread "main" redis.clients.jedis.exceptions.JedisConnectionException: java.net.ConnectException: Connection refused: connect

        jedis.get("redis");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("redis"));
	}
}
