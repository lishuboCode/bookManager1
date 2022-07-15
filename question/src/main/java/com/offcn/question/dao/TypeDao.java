package com.offcn.question.dao;

import com.offcn.question.entity.TypeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 题目-题目类型表
 * 
 * @author lishubo
 * @email lishubogx@163.com
 * @date 2022-07-13 22:47:29
 */
@Mapper
public interface TypeDao extends BaseMapper<TypeEntity> {
	
}
