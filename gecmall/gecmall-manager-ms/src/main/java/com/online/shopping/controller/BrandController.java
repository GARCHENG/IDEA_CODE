package com.online.shopping.controller;

import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.pojo.TbBrand;
import com.online.shopping.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("brand-Ms/")

public class BrandController {

    @Autowired
    private BrandService brandService;


    @RequestMapping("findAll")
    public List<TbBrand> findAll(){
        List <TbBrand> list = brandService.findAll();
        return list;
    }

//    $scope.findPage = function(pageNum,pageSize) {
//        $http.get(URL+'brand-Ms/findPage?pageNum='+pageNum+'&pageSize='+pageSize).success(
//                function(response) {
//            $scope.list = response.rows;  // jquery dom编程
//            $scope.paginationConf.totalItems=response.total;
//        }
//			);
    @RequestMapping("findPage")
    public PageResult findPage(Integer pageNum,Integer pageSize){
        PageResult pageResult  = brandService.findPage(pageNum,pageSize);
        return pageResult;
    }

    @RequestMapping("findBrandDetail")
    public TbBrand findBrandDetail(Integer id){
        TbBrand tbBrand = brandService.findOne(id);
        return tbBrand;

    }
    @RequestMapping("updateBrand")
    public Result updateBrand(@RequestBody TbBrand tbBrand){
        Result result = new Result();
        brandService.updateBrand(tbBrand);
        result.setSuccess(true);
        result.setMessage("修改成功!");

        return result;

    }
    @RequestMapping("add")
    public Result add(@RequestBody TbBrand tbBrand){
        Result result = new Result();
        brandService.addBrand(tbBrand);
        result.setSuccess(true);
        result.setMessage("添加成功!");

        return result;
    }

    @RequestMapping("delete")
    public Result delete(String [] ids){
        Result result = new Result();
        brandService.deleteByIds(ids);
        result.setSuccess(true);
        result.setMessage("添加成功!");

        return result;
    }

    @RequestMapping("selectOptionList")
    public List<Map> selectOptionList(){
        List<Map> maps = brandService.selectOptionList();
        return maps;
    }
    
}
