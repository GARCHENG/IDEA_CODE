package com.online.shopping.service.Impl;

import com.online.shopping.mapper.TbItemCatMapper;
import com.online.shopping.pojo.TbItemCat;
import com.online.shopping.pojo.TbItemCatExample;
import com.online.shopping.service.TBItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TBItemCatServiceImpl implements TBItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TbItemCat> findByParentId(Integer parentId) {
        TbItemCatExample tce = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tce.createCriteria();
        criteria.andParentIdEqualTo(Long.parseLong(parentId+""));
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(tce);
        return tbItemCats;
    }

    @Override
    public TbItemCat findOneById(String id) {
        TbItemCat tbItemCat = tbItemCatMapper.selectByPrimaryKey(Long.parseLong(id));
        return tbItemCat;
    }

    @Override
    public void update(TbItemCat tbItemCat) {
        tbItemCatMapper.updateByPrimaryKey(tbItemCat);
    }

    @Override
    public void add(TbItemCat tbItemCat) {
        tbItemCatMapper.insert(tbItemCat);
    }

    @Override
    public void delByIds(String[] ids) {
        if (ids != null && ids.length!=0) {
            for (String id : ids) {
                tbItemCatMapper.deleteByPrimaryKey(Long.parseLong(id));
            }
        }
    }

    @Override
    public List<TbItemCat> findAll() {
        return this.tbItemCatMapper.selectByExample(null);
    }
}
