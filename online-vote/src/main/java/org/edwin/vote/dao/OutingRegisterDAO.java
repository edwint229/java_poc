package org.edwin.vote.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.edwin.vote.mvc.to.OutingRegisterTO;

public interface OutingRegisterDAO {
	@Insert("insert into t_outing_register(user_id, name, gender, inditify_no, phone_no, remark, created_dt, update_dt) values(#{userId}, #{name}, #{gender}, #{inditifyNo}, #{phoneNo}, #{remark}, #{createdDt}, #{updateDt})")
	public void add(OutingRegisterTO outingRegister) throws Exception;

	@Select("select count(1) from (select 1 from t_outing_register where user_id=#{userId} limit 1) tmp")
	public int getCount(@Param("userId") String userId) throws Exception;

	@Select("select * from t_outing_register where user_id=#{userId}")
	@Results(value = { @Result(property = "userId", column = "user_id"), @Result(property = "name", column = "name"), @Result(property = "inditifyNo", column = "inditify_no"),
			@Result(property = "phoneNo", column = "phone_no"), @Result(property = "remark", column = "remark"), @Result(property = "createdDt", column = "created_dt"),
			@Result(property = "updateDt", column = "update_dt") })
	public OutingRegisterTO getPersonalOutingRegister(@Param("userId") String userId);

	@Select("update t_outing_register set name=#{name}, gender=#{gender}, inditify_no=#{inditifyNo}, phone_no=#{phoneNo}, remark=#{remark}, update_dt=#{updateDt} where user_id = #{userId}")
	public void update(OutingRegisterTO outingRegister);

}
