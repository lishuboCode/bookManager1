package com.offcn.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.offcn.common.utils.PageUtils;
import com.offcn.common.utils.Query;

import com.offcn.member.dao.MemberDao;
import com.offcn.member.entity.MemberEntity;
import com.offcn.member.service.MemberService;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {
    @Autowired
    private MemberDao memberDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                new QueryWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> countByDateTime(String beginTime, String endTime) {
        QueryWrapper<MemberEntity> queryWrapper = new QueryWrapper<MemberEntity>().select("DISTINCT(DATE_FORMAT(create_time,'%Y-%m-%d')) AS NAME ,COUNT(id) AS VALUE")
                .between("create_time", beginTime+" 00:00:00", endTime+" 23:59:59").groupBy("NAME");

        List<Map<String, Object>> mapList = memberDao.selectMaps(queryWrapper);

        return mapList;
    }
    @Override
    public MemberEntity login(String username, String password) {
        MemberEntity memberEntity = this.getOne(new QueryWrapper<MemberEntity>().eq("user_name", username).eq("PASSWORD", password));
        return memberEntity;
    }
}