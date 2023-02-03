package com.online.shopping.controller;

import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.pojogroup.Specification;
import com.online.shopping.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("specification-Ms/")
public class SpecificationController {
    @Autowired
    private SpecificationService specificationService;

    @RequestMapping("search")
    public PageResult search(Integer page,Integer rows){
        PageResult pageResult = specificationService.findPage(page,rows);
        return pageResult;
    }

    @RequestMapping("findOne")
    public Specification findOne(String id){
        Specification specification = specificationService.findOneWithOptions(id);
        return specification;
    }

    @RequestMapping("update")
    public Result update(@RequestBody Specification specification){
        Result result = new Result();
         try {
             specificationService.update(specification);
             result.setSuccess(true);
             result.setMessage("修改成功!");
         }catch (Exception e){
             result.setSuccess(false);
             result.setMessage("修改失败!");
         }

        return result;
    }


    @RequestMapping("add")
    public Result add(@RequestBody Specification specification){
        Result result = new Result();
        try {
            specificationService.add(specification);
            result.setSuccess(true);
            result.setMessage("添加成功!");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("添加失败!");
        }

        return result;
    }


    @RequestMapping("delete")
    public Result delete(String [] ids){
        Result result = new Result();
        try {
            specificationService.delete(ids);
            result.setSuccess(true);
            result.setMessage("刪除成功!");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("刪除失敗!");
        }

        return result;
    }

    @RequestMapping("selectOptionList")
    public List<Map> selectOptionList(){
        List<Map> maps = specificationService.selectOptionList();
        return maps;
    }

}
