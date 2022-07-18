package com.offcn.question.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.offcn.common.utils.PageUtils;
import com.offcn.question.entity.QuestionEntity;
import com.offcn.question.entity.TypeEntity;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lishubo
 * @email lishubogx@163.com
 * @date 2022-07-13 22:47:29
 */
public interface QuestionService extends IService<QuestionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //导入
    public Map importExcel(MultipartFile file);
    //导出
    public Workbook exportExcel();

    //    查询条数
    List<Map<String, Object>> countTypeNum();
}

