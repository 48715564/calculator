package com.cn.common.persistence;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by bozhou on 2017/11/20.
 */
public interface CrudMapper<Example,Entity,T>{
    int countByExample(Example example);

    int deleteByExample(Example example);

    int deleteByPrimaryKey(T id);

    int insert(Entity record);

    int insertSelective(Entity record);

    List<Entity> selectByExample(Example example);

    Entity selectByPrimaryKey(T id);

    int updateByExampleSelective(@Param("record") Entity record, @Param("example") Example example);

    int updateByExample(@Param("record") Entity record, @Param("example") Example example);

    int updateByPrimaryKeySelective(Entity record);

    int updateByPrimaryKey(Entity record);
}
