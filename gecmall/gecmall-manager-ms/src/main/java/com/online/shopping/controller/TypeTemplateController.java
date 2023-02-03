package com.online.shopping.controller;

import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.pojo.TbTypeTemplate;
import com.online.shopping.service.TypeTemplateService;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("typeTemplate-Ms/")
public class TypeTemplateController {

    @Autowired
    private TypeTemplateService typeTemplateService;



    @RequestMapping("search")
    public PageResult search(Integer page,Integer rows){
        PageResult pageResult = typeTemplateService.findPage(page,rows);
        return pageResult;

    }

    @RequestMapping("findOne")
    public TbTypeTemplate findOne(String id){
        TbTypeTemplate tbTypeTemplate = typeTemplateService.findOne(id);
        return tbTypeTemplate;

    }

    @RequestMapping("update")
    public Result update(@RequestBody TbTypeTemplate tbTypeTemplate){
        Result result = new Result();
        try {
            typeTemplateService.update(tbTypeTemplate);
            result.setSuccess(true);
            result.setMessage("修改成功!");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("修改失敗!");

        }
        return result;


    }


    @RequestMapping("add")
    public Result add(@RequestBody TbTypeTemplate tbTypeTemplate){
        Result result = new Result();
        try {
            typeTemplateService.add(tbTypeTemplate);
            result.setSuccess(true);
            result.setMessage("添加成功!");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("添加失敗!");

        }
        return result;


    }

    @RequestMapping("delete")
    public Result delete(String [] ids){
        Result result = new Result();
        try {
            typeTemplateService.deleteByIds(ids);
            result.setSuccess(true);
            result.setMessage("刪除成功!");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("刪除失敗!");
        }

        return result;



    }

    @RequestMapping("selectTypeTemplateList")
    public List<Map> selectTypeTemplateList(){
        List<Map> maps = typeTemplateService.findTypeTemplateList();
        return maps;
    }


//    findBySpecList?id=undefined
    @RequestMapping("findBySpecList")
    public List<Map> findBySpecList(String id){
        List<Map> specList;
        try {
            specList = typeTemplateService.findBySpecList(id);
        }catch (Exception e){
            return null;
        }

        return specList;

    }






}
