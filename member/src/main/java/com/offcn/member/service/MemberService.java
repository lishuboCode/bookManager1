package com.offcn.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.offcn.common.utils.PageUtils;
import com.offcn.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员-会员表
 *
 * @author lishubo
 * @email lishubogx@163.com
 * @date 2022-07-13 22:49:58
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

