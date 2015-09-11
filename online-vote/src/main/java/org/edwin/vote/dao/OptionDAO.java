package org.edwin.vote.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.edwin.vote.mvc.to.OptionTO;

public interface OptionDAO {

    @Select("select * from t_option where select_id=#{selectId}")
    @Results(value = {
            @Result(property = "id", column = "id"), @Result(property = "selectId", column = "select_id"),
            @Result(property = "fullName", column = "full_name"), @Result(property = "shortName", column = "short_name")
    })
    public List<OptionTO> getOptions(int selectId) throws Exception;

}
