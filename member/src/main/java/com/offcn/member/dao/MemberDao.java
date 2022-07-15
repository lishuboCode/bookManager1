package com.offcn.member.dao;

import com.offcn.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员-会员表
 * 
 * @author lishubo
 * @email lishubogx@163.com
 * @date 2022-07-13 22:49:58
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
