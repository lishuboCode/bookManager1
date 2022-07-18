package com.offcn.member.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.offcn.common.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.offcn.member.entity.MemberEntity;
import com.offcn.member.service.MemberService;
import com.offcn.common.utils.PageUtils;
import com.offcn.common.utils.R;



/**
 * 会员-会员表
 *
 * @author lishubo
 * @email lishubogx@163.com
 * @date 2022-07-13 22:49:58
 */
@RestController
@RequestMapping("member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //根据开始时间，结束时间，统计每日注册账号
    @RequestMapping("countAccountCreate")
    public R countAccountCreate(String beginTime,String endTime){
            List<Map<String, Object>> mapList = memberService.countByDateTime(beginTime, endTime);
        return R.ok().put("mapList",mapList);
    }

    //登录
    @PostMapping("/login")
    public R login(String username,String password){
        MemberEntity memberEntity = memberService.login(username, password);

        if(memberEntity!=null) {
            String token = JWTUtil.generateToken(memberEntity.getUserName());
            //生成refreshToken
            String refreshToken = UUID.randomUUID().toString().replace("-", "");
            stringRedisTemplate.opsForHash().put(refreshToken, "token", token);
            stringRedisTemplate.opsForHash().put(refreshToken, "username", username);
            //设置token的过期时间
            stringRedisTemplate.expire(refreshToken, JWTUtil.REFRESH_TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);

            return R.ok().put("token",token).put("refreshToken",refreshToken);

        }else {
            return R.error("账号密码错误");

        }


    }

    //刷新token
    @PostMapping("/refreshtoken")
    public R refreshToken(String refreshToken){
        //根据refreshToken从redis读取用户名
        String username= (String) stringRedisTemplate.boundHashOps(refreshToken).get("username");

        if(StringUtils.isEmpty(username)){
            return R.error("刷新token失败");
        }
        //根据用户名生成新的token
        String token = JWTUtil.generateToken(username);
        //更新token到redis
        stringRedisTemplate.boundHashOps(refreshToken).put("token",token);
        return R.ok().put("token",token).put("refreshToken",refreshToken);
    }
    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:member:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:member:info")
    public R info(@PathVariable("id") Long id){
		MemberEntity member = memberService.getById(id);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:member:save")
    public R save(@RequestBody MemberEntity member){
		memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:member:update")
    public R update(@RequestBody MemberEntity member){
		memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:member:delete")
    public R delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
