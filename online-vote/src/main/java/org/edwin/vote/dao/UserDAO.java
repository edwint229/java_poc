package org.edwin.vote.dao;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.edwin.vote.mvc.to.UserTO;

@CacheNamespace(implementation = org.mybatis.caches.ehcache.EhcacheCache.class)
public interface UserDAO {

	@Insert("insert into t_vote_user(user_id, password, name, role, created_dt) values(#{userId}, #{password}, #{name}, #{role}, #{createdDt})")
	public void create(UserTO userTO) throws Exception;

	@Select("select * from t_vote_user where user_id = #{userId}")
	@Results(value = { @Result(property = "userId", column = "user_id"), @Result(property = "password", column = "password"), @Result(property = "name", column = "name"),
			@Result(property = "role", column = "role"), @Result(property = "createdDt", column = "created_dt") })
	public UserTO retrieveUserById(@Param("userId") String userId) throws Exception;

	@Select("select * from t_vote_user")
	@Results(value = { @Result(property = "userId", column = "user_id"), @Result(property = "password", column = "password"), @Result(property = "name", column = "name"),
			@Result(property = "role", column = "role"), @Result(property = "createdDt", column = "created_dt") })
	public List<UserTO> retrieveAllUsers() throws Exception;

	@Update("update t_vote_user set password= #{password} where user_id=#{userId}")
	public void updatePassword(UserTO userTO) throws Exception;

	@Select("select count(1) from (select 1 from t_vote_user where user_id = #{userId} limit 1) tmp")
	public int getCount(String userId) throws Exception;

}
