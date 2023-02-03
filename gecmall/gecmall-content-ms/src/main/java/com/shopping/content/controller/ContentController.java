package com.shopping.content.controller;

import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.pojo.TbContent;
import com.shopping.content.service.ContentService;
import org.bouncycastle.asn1.mozilla.PublicKeyAndChallenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.util.List;

@RestController
@RequestMapping("content-ms/")
public class ContentController {


    @Autowired
    private ContentService contentService;


    @RequestMapping("search")
    public PageResult search(Integer page,Integer rows)
    {
        PageResult pageResult = contentService.search(page,rows);
        return pageResult;
    }

    @RequestMapping("findOne")
    public TbContent findOne(String id){
        TbContent tbContent = contentService.findOne(id);
        return tbContent;
    }

    @RequestMapping("update")
    public Result update(@RequestBody TbContent tbContent){
        Result result = new Result();
        try {
            contentService.update(tbContent);
            result.setSuccess(true);
            result.setMessage("修改成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("修改失败");
        }
        return result;



    }


    @RequestMapping("add")
    public Result add(@RequestBody TbContent tbContent){
        Result result = new Result();
        try {
            contentService.add(tbContent);
            result.setSuccess(true);
            result.setMessage("添加成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("添加失败");
        }
        return result;

    }

    @RequestMapping("findByCategoryId")
    public List<TbContent> findByCategoryId(String categoryId){
        List<TbContent> contents = contentService.findByCategoryId(categoryId);
        return contents;

    }

}
