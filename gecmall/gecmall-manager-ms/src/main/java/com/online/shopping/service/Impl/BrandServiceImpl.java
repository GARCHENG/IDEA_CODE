package com.online.shopping.service.Impl;

import com.github.pagehelper.PageHelper;
import com.online.shopping.entity.PageResult;
import com.online.shopping.mapper.TbBrandMapper;
import com.online.shopping.pojo.TbBrand;
import com.online.shopping.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Override
    public List<TbBrand> findAll() {
        List<TbBrand> tbBrands = tbBrandMapper.selectByExample(null);
        return tbBrands;
    }

    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        PageResult pageResult = new PageResult();

        PageHelper.startPage(pageNum,pageSize);
        List<TbBrand> tbBrands = tbBrandMapper.selectByExample(null);
        pageResult.setRows(tbBrands);

        int i = tbBrandMapper.countByExample(null);
        pageResult.setTotal(i);

        return pageResult;
    }

    @Override
    public TbBrand findOne(Integer id) {
        TbBrand tbBrand = tbBrandMapper.selectByPrimaryKey(Long.parseLong(id + ""));
        return tbBrand;
    }

    @Override
    public void updateBrand(TbBrand tbBrand) {
        tbBrandMapper.updateByPrimaryKey(tbBrand);
    }

    @Override
    public void addBrand(TbBrand tbBrand) {
        tbBrandMapper.insert(tbBrand);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            tbBrandMapper.deleteByPrimaryKey(Long.parseLong(id));
        }
    }

    @Override
    public List<Map> selectOptionList() {
        List<Map> maps = tbBrandMapper.selectOptionList();
        return maps;
    }
}
