package com.offcn.context.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.offcn.context.entity.NewsEntity;
import com.offcn.context.service.NewsService;
import com.offcn.common.utils.PageUtils;
import com.offcn.common.utils.R;



/**
 * 内容-资讯表
 *
 * @author lishubo
 * @email lishubogx@163.com
 * @date 2022-07-13 22:31:27
 */
@RestController
@RequestMapping("context/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("context:news:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = newsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("context:news:info")
    public R info(@PathVariable("id") Long id){
		NewsEntity news = newsService.getById(id);

        return R.ok().put("news", news);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("context:news:save")
    public R save(@RequestBody NewsEntity news){
		newsService.save(news);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("context:news:update")
    public R update(@RequestBody NewsEntity news){
		newsService.updateById(news);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("context:news:delete")
    public R delete(@RequestBody Long[] ids){
		newsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
