package com.online.shopping.service.Impl;

import com.github.pagehelper.PageHelper;
import com.online.shopping.entity.PageResult;
import com.online.shopping.mapper.TbSpecificationMapper;
import com.online.shopping.mapper.TbSpecificationOptionMapper;
import com.online.shopping.pojo.TbSpecification;
import com.online.shopping.pojo.TbSpecificationExample;
import com.online.shopping.pojo.TbSpecificationOption;
import com.online.shopping.pojo.TbSpecificationOptionExample;
import com.online.shopping.pojogroup.Specification;
import com.online.shopping.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.LongFunction;

@Service
public class SpecificationServiceImpl implements SpecificationService {
    @Autowired
    private TbSpecificationMapper tbSpecificationMapper;
    @Autowired
    private TbSpecificationOptionMapper tbSpecificationOptionMapper;


    @Override
    public PageResult findPage(Integer page, Integer rows) {

        PageResult pageResult = new PageResult();

        PageHelper.startPage(page,rows);
        List<TbSpecification> tbSpecifications = tbSpecificationMapper.selectByExample(null);
        pageResult.setRows(tbSpecifications);

        int i = tbSpecificationMapper.countByExample(null);
        pageResult.setTotal(i);

        return pageResult;
    }

    @Override
    public Specification findOneWithOptions(String id) {
        Specification specification = new Specification();

        TbSpecification tbSpecification = tbSpecificationMapper.selectByPrimaryKey(Long.parseLong(id));
        specification.setSpecification(tbSpecification);

        TbSpecificationOptionExample so = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = so.createCriteria();
        criteria.andSpecIdEqualTo(Long.parseLong(id));
        List<TbSpecificationOption> tbSpecificationOptions = tbSpecificationOptionMapper.selectByExample(so);
        specification.setSpecificationOptionList(tbSpecificationOptions);

        return specification;
    }

    @Override
    public void update(Specification specification) {
        TbSpecification tbSpecification = specification.getSpecification();
        tbSpecificationMapper.updateByPrimaryKey(tbSpecification);

        List<TbSpecificationOption> specificationOptionList = specification.getSpecificationOptionList();
        //删除原有的
        TbSpecificationOptionExample soe = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = soe.createCriteria();
        criteria.andSpecIdEqualTo(tbSpecification.getId());
        tbSpecificationOptionMapper.deleteByExample(soe);
        //插入修改后的所有
        for (TbSpecificationOption tbSpecificationOption : specificationOptionList) {
            tbSpecificationOption.setSpecId(tbSpecification.getId());
            tbSpecificationOptionMapper.insert(tbSpecificationOption);
        }

    }

    @Override
    public void add(Specification specification) {
        TbSpecification tbSpecification = specification.getSpecification();

        tbSpecificationMapper.insert(tbSpecification);

        List<TbSpecificationOption> specificationOptionList = specification.getSpecificationOptionList();
        if (specificationOptionList != null && specificationOptionList.size()!=0 ){
            for (TbSpecificationOption tbSpecificationOption : specificationOptionList) {
                tbSpecificationOption.setSpecId(tbSpecification.getId());
                tbSpecificationOptionMapper.insert(tbSpecificationOption);
            }
        }



    }

    @Override
    public void delete(String[] ids) {
        if (ids != null && ids.length!=0) {
            for (String id : ids) {
                tbSpecificationMapper.deleteByPrimaryKey(Long.parseLong(id));

                TbSpecificationOptionExample soe = new TbSpecificationOptionExample();
                TbSpecificationOptionExample.Criteria criteria = soe.createCriteria();
                criteria.andSpecIdEqualTo(Long.parseLong(id));
                tbSpecificationOptionMapper.deleteByExample(soe);
            }
        }

    }

    @Override
    public List<Map> selectOptionList() {
        List<Map> maps = tbSpecificationMapper.selectOptionList();
        return maps;
    }
}
