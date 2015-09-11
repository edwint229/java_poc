package org.edwin.vote.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.edwin.vote.mvc.to.VoteTO;

public interface VoteDAO {

	@Insert("insert into t_vote(user_id, select_id, option_id, created_dt) values(#{userId}, #{selectId}, #{optionId}, #{createdDt})")
	public void vote(VoteTO voteTO) throws Exception;

	@Select("select count(1) from (select 1 from t_vote where user_id=#{userId} and select_id=#{selectId} limit 1) tmp")
	public int getCount(@Param("userId") String userId, @Param("selectId") int selectId) throws Exception;

	@Select("select count(1) from t_vote where option_id=#{optionId}")
	public int getVoteOptionCount(int optionId) throws Exception;

	@Select("select * from t_vote where user_id=#{userId} and select_id=#{selectId}")
	@Results(value = { @Result(property = "id", column = "id"), @Result(property = "user_id", column = "userId"), @Result(property = "selectId", column = "select_id"),
			@Result(property = "optionId", column = "option_id"), @Result(property = "createdDt", column = "created_dt") })
	public List<VoteTO> getPersonalVoteResult(@Param("userId") String userId, @Param("selectId") int selectId);

}
