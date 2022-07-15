package com.offcn.question.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.offcn.question.entity.QuestionEntity;
import com.offcn.question.service.QuestionService;
import com.offcn.common.utils.PageUtils;
import com.offcn.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author lishubo
 * @email lishubogx@163.com
 * @date 2022-07-13 22:47:29
 */
@RestController
@RequestMapping("question/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    //题目上传导入
    @PostMapping("/upload")
    public R upload(MultipartFile file){
        Map result = questionService.importExcel(file);
        return R.ok().put("result",result.get("result")).put("msg",result.get("msg")).put("num",result.get("num"));
    }

    //导出excel
    @GetMapping("exportExcel")
    public void export(String tableName, HttpServletResponse response){
        System.out.println("导出excele");
        Workbook workbook = questionService.exportExcel();

        if (workbook != null) {
            String fileName = "uxue_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
//            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName  );
//            response.setContentType("application/octet-stream;charset=GB2312");
            response.setContentType("application/binary;charset=ISO8859_1");
            response.setCharacterEncoding("GB2312");
            OutputStream outputStream;
            try {
                outputStream = response.getOutputStream();
                workbook.write(outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                response.getWriter().print("error");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("question:question:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = questionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("question:question:info")
    public R info(@PathVariable("id") Long id){
		QuestionEntity question = questionService.getById(id);

        return R.ok().put("question", question);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("question:question:save")
    public R save(@RequestBody QuestionEntity question){
		questionService.save(question);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("question:question:update")
    public R update(@RequestBody QuestionEntity question){
		questionService.updateById(question);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("question:question:delete")
    public R delete(@RequestBody Long[] ids){
		questionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
