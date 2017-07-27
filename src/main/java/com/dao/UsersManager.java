package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;






import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

import com.Util.RedisUtil;
import com.domain.User;

/** 
 * @author yanghd 
 * @date 2017年7月18日 下午2:15:29 
 * @description 类说明 
 */
@Component
public class UsersManager {
	
	 @Autowired
	 private DataSource dataSource;
	 
	 
	 public List<User> getAllUsers(){
		 JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		 String sql = "SELECT id, name, age, password from userInfo ";
		 return jdbcTemplate.query(sql, new String[]{}, new BeanPropertyRowMapper<User>(User.class));
	 }
	 
	 public int updateUsers(User user){
		 JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		 String sql = " UPDATE userInfo SET name = ?, age = ?, password = ? WHERE id = ? ";
		 List<String> args = new ArrayList<String>();
		 args.add(user.getName());
		 args.add(user.getAge());
		 args.add(user.getPassword());
		 args.add(user.getId());
		 Integer flag = jdbcTemplate.update(sql, args.toArray());
		 return flag;
	 }
	 
	 public int insertUser(User user) {
		 JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		 UUID uuid = UUID.randomUUID();
		 String id = uuid.toString();
		 String sql = " INSERT INTO userInfo VALUES (?, ?, ?, ? ) ";
		 List<String> args = new ArrayList<String>();
		 args.add(user.getName());
		 args.add(user.getAge());
		 args.add(user.getPassword());
		 args.add(id);
		 int flag = jdbcTemplate.update(sql, args.toArray());
		 return flag;
	}
	//返回admin的用户名和密码
    @SuppressWarnings("finally")
	public List<String> getRedisAdmin(){
        Jedis jedis = null;
        List<String> result=new ArrayList<String>();
        try{
            jedis = RedisUtil.getJedis();
            result = jedis.hmget("admin", "name", "pwd");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            RedisUtil.returnResource(jedis);
            return result;
        }
    }
}
