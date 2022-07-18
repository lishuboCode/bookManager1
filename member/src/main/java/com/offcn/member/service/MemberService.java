package com.offcn.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.offcn.common.utils.PageUtils;
import com.offcn.member.entity.MemberEntity;

import java.util.List;
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

    public List<Map<String, Object>> countByDateTime(String beginTime, String endTime);
    public MemberEntity login(String username,String password);
}

