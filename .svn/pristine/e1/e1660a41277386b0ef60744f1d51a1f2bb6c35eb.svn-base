package com.aisile.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.aisile.mapper.TbBrandMapper;
import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbBrandExample;
import com.aisile.pojo.TbBrandExample.Criteria;
import com.aisile.pojo.entity.pageResult;
import com.aisile.sellergoods.service.BrandService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Service 
public class BrandServiceImpl implements BrandService {

	@Autowired
	private TbBrandMapper tbBrandMapper;
	@Override
	public List<TbBrand> findAll() {
		// TODO Auto-generated method stub
		//条件为空 查询全部 
		return tbBrandMapper.selectByExample(null);
	}
	@Override
	public pageResult findPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		Page<TbBrand>  pageinfo= (Page<TbBrand>)tbBrandMapper.selectByExample(null);
		return new pageResult(pageinfo.getTotal(), pageinfo.getResult());
	}
	@Override
	public void add(TbBrand tbBrand) {
		// TODO Auto-generated method stub
		tbBrandMapper.insert(tbBrand);
	}
	@Override
	public void update(TbBrand brand) {
		// TODO Auto-generated method stub
		tbBrandMapper.updateByPrimaryKey(brand);
	}
	@Override
	public TbBrand findOne(Long id) {
		// TODO Auto-generated method stub
		return tbBrandMapper.selectByPrimaryKey(id);
	}
	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub
		for(Long id:ids){
			tbBrandMapper.deleteByPrimaryKey(id);
		}		
	}
	@Override
	public pageResult findSearch(TbBrand brand, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		TbBrandExample brandExample = new TbBrandExample();
		Criteria criteria = brandExample.createCriteria();
		if(brand.getName()!=null && !brand.getName().equals("")){
			criteria.andNameLike("%"+brand.getName()+"%");
		}
		if(brand.getFirstChar()!=null && !brand.getFirstChar().equals("")){
			criteria.andFirstCharEqualTo(brand.getFirstChar());
		}
		Page<TbBrand>  pageinfo= (Page<TbBrand>)tbBrandMapper.selectByExample(brandExample);
		return new pageResult(pageinfo.getTotal(), pageinfo.getResult());
	}
	@Override
	public List<Map> selectOptionList() {
		// TODO Auto-generated method stub
		return tbBrandMapper.selectOptionList();
	}

}
