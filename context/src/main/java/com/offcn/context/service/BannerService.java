package com.offcn.context.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.offcn.common.utils.PageUtils;
import com.offcn.context.entity.BannerEntity;

import java.util.Map;

/**
 * 内容-横幅广告表
 *
 * @author lishubo
 * @email lishubogx@163.com
 * @date 2022-07-13 22:31:27
 */
public interface BannerService extends IService<BannerEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

