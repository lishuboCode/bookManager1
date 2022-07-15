package com.offcn.question.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.offcn.common.utils.PageUtils;
import com.offcn.question.entity.TypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 题目-题目类型表
 *
 * @author lishubo
 * @email lishubogx@163.com
 * @date 2022-07-13 22:47:29
 */
public interface TypeService extends IService<TypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
    List<TypeEntity> findAll();
}

