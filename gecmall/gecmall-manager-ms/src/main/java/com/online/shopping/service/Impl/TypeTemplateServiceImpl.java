package com.online.shopping.service.Impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.online.shopping.entity.PageResult;
import com.online.shopping.mapper.TbSpecificationOptionMapper;
import com.online.shopping.mapper.TbTypeTemplateMapper;
import com.online.shopping.pojo.TbSpecificationOption;
import com.online.shopping.pojo.TbSpecificationOptionExample;
import com.online.shopping.pojo.TbTypeTemplate;
import com.online.shopping.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {

    @Autowired
    private TbTypeTemplateMapper tbTypeTemplateMapper;
    @Autowired
    private TbSpecificationOptionMapper tbSpecificationOptionMapper;

    @Override
    public PageResult findPage(Integer page, Integer rows) {
        PageResult pageResult = new PageResult();

        PageHelper.startPage(page,rows);
        List<TbTypeTemplate> tbTypeTemplates = tbTypeTemplateMapper.selectByExample(null);
        pageResult.setRows(tbTypeTemplates);

        int i = tbTypeTemplateMapper.countByExample(null);
        pageResult.setTotal(i);

        return pageResult;
    }

    @Override
    public TbTypeTemplate findOne(String id) {
        TbTypeTemplate tbTypeTemplate = tbTypeTemplateMapper.selectByPrimaryKey(Long.parseLong(id));

        return tbTypeTemplate;
    }

    @Override
    public void update(TbTypeTemplate tbTypeTemplate) {
        tbTypeTemplateMapper.updateByPrimaryKey(tbTypeTemplate);
    }

    @Override
    public void add(TbTypeTemplate tbTypeTemplate) {
        tbTypeTemplateMapper.insert(tbTypeTemplate);
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids != null) {
            for (String id : ids) {
                tbTypeTemplateMapper.deleteByPrimaryKey(Long.parseLong(id));
            }
        }

    }

    @Override
    public List<Map> findTypeTemplateList() {
        return tbTypeTemplateMapper.FindTypeTemplateList();
    }

    @Override
    public List<Map> findBySpecList(String id) {

        TbTypeTemplate tbTypeTemplate = tbTypeTemplateMapper.selectByPrimaryKey(Long.parseLong(id));
        String specIds = tbTypeTemplate.getSpecIds();

        List<Map> maps = JSON.parseArray(specIds, Map.class);
        for (Map map : maps) {

            TbSpecificationOptionExample oe = new TbSpecificationOptionExample();
            TbSpecificationOptionExample.Criteria criteria = oe.createCriteria();

            criteria.andSpecIdEqualTo(Long.parseLong(map.get("id").toString()));
            List<TbSpecificationOption> tbSpecificationOptions = tbSpecificationOptionMapper.selectByExample(oe);

            map.put("options",tbSpecificationOptions);
        }

        return maps;


    }
}
