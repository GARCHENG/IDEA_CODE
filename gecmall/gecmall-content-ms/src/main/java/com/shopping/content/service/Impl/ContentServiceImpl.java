package com.shopping.content.service.Impl;

import com.github.pagehelper.PageHelper;
import com.online.shopping.entity.PageResult;
import com.online.shopping.pojo.TbContent;
import com.online.shopping.pojo.TbContentExample;
import com.shopping.content.mapper.TbContentMapper;
import com.shopping.content.service.ContentCategoryService;
import com.shopping.content.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageResult search(Integer page, Integer rows) {
        PageResult pageResult = new PageResult();

        PageHelper.startPage(page,rows);
        List<TbContent> tbContents = tbContentMapper.selectByExample(null);
        pageResult.setRows(tbContents);

        int i = tbContentMapper.countByExample(null);
        pageResult.setTotal(i);

        return pageResult;
    }

    @Override
    public TbContent findOne(String id) {
        TbContent tbContent = tbContentMapper.selectByPrimaryKey(Long.parseLong(id));
        return tbContent;
    }

    @Override
    public void update(TbContent tbContent) {
        tbContentMapper.updateByPrimaryKey(tbContent);
    }

    @Override
    public void add(TbContent tbContent) {
        tbContentMapper.insert(tbContent);
    }

    @Override
    public List<TbContent> findByCategoryId(String categoryId) {

        List<TbContent> contentList = (List<TbContent>) redisTemplate.boundHashOps("picList").get("contentList");
        if (contentList==null){
            System.out.println("走数据库~~~~~");
            TbContentExample example  = new TbContentExample();
            TbContentExample.Criteria criteria = example.createCriteria();
            criteria.andCategoryIdEqualTo(Long.parseLong(categoryId));
            List<TbContent> contents = tbContentMapper.selectByExample(example);

            redisTemplate.boundHashOps("picList").put("contentList",contents);

            contentList = contents;

        }else {
            System.out.println("走springdata_redis~~~");

        }
        return contentList;



    }
}
