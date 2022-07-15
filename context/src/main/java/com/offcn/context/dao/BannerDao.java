package com.offcn.context.dao;

import com.offcn.context.entity.BannerEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 内容-横幅广告表
 * 
 * @author lishubo
 * @email lishubogx@163.com
 * @date 2022-07-13 22:31:27
 */
@Mapper
public interface BannerDao extends BaseMapper<BannerEntity> {
	
}
