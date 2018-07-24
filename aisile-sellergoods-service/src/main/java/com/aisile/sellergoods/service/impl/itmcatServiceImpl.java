package com.aisile.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.aisile.mapper.TbItemCatMapper;

import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbBrandExample;
import com.aisile.pojo.TbBrandExample.Criteria;
import com.aisile.pojo.TbItemCat;
import com.aisile.pojo.TbItemCatExample;
import com.aisile.pojo.TbSeller;
import com.aisile.pojo.entity.pageResult;
import com.aisile.sellergoods.service.BrandService;
import com.aisile.sellergoods.service.TbItemCatService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Service 
public class itmcatServiceImpl implements TbItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	@Override
	public List<TbItemCat> findAll() {
		// TODO Auto-generated method stub
		//条件为空 查询全部 
		return tbItemCatMapper.selectByExample(null);
	}
	@Override
	public pageResult findPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		Page<TbItemCat>  pageinfo= (Page<TbItemCat>)tbItemCatMapper.selectByExample(null);
		return new pageResult(pageinfo.getTotal(), pageinfo.getResult());
	}
	@Override
	public void add(TbItemCat tbItemCat) {
		// TODO Auto-generated method stub
		
		tbItemCatMapper.insert(tbItemCat);
	}
	@Override
	public void update(TbItemCat tbItemCat) {
		// TODO Auto-generated method stub
		tbItemCatMapper.updateByPrimaryKey(tbItemCat);
	}

	@Override
	public void delete(Long id) {
		
		
		    
			tbItemCatMapper.deleteByPrimaryKey(id);
				
		
	}
	@Override
	public List<TbItemCat> findAllByparentId(Long parent_id) {
		// TODO Auto-generated method stub
		

            TbItemCatExample example = new TbItemCatExample();
		  example.createCriteria().andParentIdEqualTo(parent_id);
		    return tbItemCatMapper.selectByExample(example);
	}
	
	@Override
	public pageResult findSearch(TbItemCat bItemCat, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public TbItemCat findOne(Long id) {
		// TODO Auto-generated method stub
		return tbItemCatMapper.selectByPrimaryKey(id);
	}
	@Override
	public int count(Long id) {
		// TODO Auto-generated method stub
		TbItemCatExample tbItemCatExample = new TbItemCatExample();
		tbItemCatExample.createCriteria().andParentIdEqualTo(id);
		return  tbItemCatMapper.countByExample(tbItemCatExample);
	}
	
	

}
