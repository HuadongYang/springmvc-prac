package com.Util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.controller.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/** 
 * @author yanghd 
 * @date 2017年7月26日 上午9:39:54 
 * @description 类说明 
 */
public class RedisUtil {
	private static JedisPool jedisPool = null;
	static JedisPoolConfig  jedisPoolConfig(){
		JedisPoolConfig config = new JedisPoolConfig();
		//最大连接数 
		config.setMaxTotal(100);  
		
		//最大空闲连接数 
//		config.setMaxIdle(10); 
		
		//每次释放连接的最大数目
		config.setNumTestsPerEvictionRun(1024);
		
		// 释放连接的扫描间隔（毫秒）
		config.setTimeBetweenEvictionRunsMillis(30000); 
		
		//连接最小空闲时间
		config.setMinEvictableIdleTimeMillis(1800000); 
		
		//连接空闲多久后释放, 当空闲时间>该值 且 空闲连接>最大空闲连接数 时直接释放
		config.setSoftMinEvictableIdleTimeMillis(10000); 
		
		//获取连接时的最大等待毫秒数,小于零:阻塞不确定的时间,默认-1
		config.setMaxWaitMillis(1500);
		
		//在获取连接的时候检查有效性, 默认false
		config.setTestOnBorrow(true);
		
		//在空闲时检查有效性, 默认false
		config.setTestWhileIdle(true);
		
		//连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
		config.setBlockWhenExhausted(false);
		return config ; 
	}
	static void jedisPool(){
		Properties prop =  new Properties();  
		try {
			prop.load(Test.class.getResourceAsStream("/redis.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String host = prop.getProperty("redis.host"); 
		String port = prop.getProperty("redis.port");
		jedisPool  = new JedisPool(jedisPoolConfig(), host, Integer.valueOf(port));
	}
	public synchronized static Jedis getJedis() {
        try {
        	jedisPool();	
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }
}
