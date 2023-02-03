package com.online.shopping.controller;

import com.online.shopping.entity.Result;
import com.online.shopping.pojo.TbItemCat;
import com.online.shopping.service.TBItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("itemCat-ms/")
public class TBItemCatController {

    @Autowired
    private TBItemCatService tbItemCatService;

    @RequestMapping("findByParentId")
    public List<TbItemCat> findByParentId(Integer parentId){
        List<TbItemCat> tbItemCats = tbItemCatService.findByParentId(parentId);
        return tbItemCats;


    }
    @RequestMapping("findOne")
    public TbItemCat  findOne(String id){
        TbItemCat tbItemCat= tbItemCatService.findOneById(id);
        return tbItemCat;
    }


    @RequestMapping("update")
    public Result update(@RequestBody TbItemCat tbItemCat){
        Result result = new Result();
//        System.out.println(tbItemCat);
        try {
            tbItemCatService.update(tbItemCat);
            result.setSuccess(true);
            result.setMessage("修改成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("修改失败");
        }

        return result;

    }

    @RequestMapping("add")
    public Result add(@RequestBody TbItemCat tbItemCat){
        Result result = new Result();
//        System.out.println(tbItemCat);
        try {
            tbItemCatService.add(tbItemCat);
            result.setSuccess(true);
            result.setMessage("添加成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("添加失败");
        }

        return result;

    }

    @RequestMapping("delete")
    public Result delete(String [] ids){
        Result result = new Result();
        try {
            tbItemCatService.delByIds(ids);
            result.setSuccess(true);
            result.setMessage("删除成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("删除失败");
        }
        return result;
    }

    @RequestMapping("findAll")
    public List<TbItemCat> findAll(){
        return tbItemCatService.findAll();
    }

}
