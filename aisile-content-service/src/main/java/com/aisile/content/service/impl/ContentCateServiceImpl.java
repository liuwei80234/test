package com.aisile.content.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.aisile.content.service.ContentCateService;
import com.aisile.mapper.TbContentCategoryMapper;
import com.aisile.pojo.TbContentCategory;
import com.aisile.pojo.TbContentCategoryExample;
import com.aisile.pojo.TbContentCategoryExample.Criteria;
import com.aisile.pojo.entity.pageResult;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Service 
public class ContentCateServiceImpl implements ContentCateService {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	@Override
	public List<TbContentCategory> findAll() {
		// TODO Auto-generated method stub
		//条件为空 查询全部 
		return tbContentCategoryMapper.selectByExample(null);
	}
	@Override
	public pageResult findPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		Page<TbContentCategory>  pageinfo= (Page<TbContentCategory>)tbContentCategoryMapper.selectByExample(null);
		return new pageResult(pageinfo.getTotal(), pageinfo.getResult());
	}
	@Override
	public void add(TbContentCategory tbContentCategory) {
		// TODO Auto-generated method stub
		tbContentCategoryMapper.insert(tbContentCategory);
	}
	@Override
	public void update(TbContentCategory tbContentCategory) {
		// TODO Auto-generated method stub
		tbContentCategoryMapper.updateByPrimaryKey(tbContentCategory);
	}
	@Override
	public TbContentCategory findOne(Long id) {
		// TODO Auto-generated method stub
		return tbContentCategoryMapper.selectByPrimaryKey(id);
	}
	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub
		for(Long id:ids){
			tbContentCategoryMapper.deleteByPrimaryKey(id);
		}		
	}
	@Override
	public pageResult findSearch(TbContentCategory tbContentCategory, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		TbContentCategoryExample contentExample = new TbContentCategoryExample();
		Criteria criteria = contentExample.createCriteria();
		if(tbContentCategory.getName()!=null && !tbContentCategory.getName().equals("")){
			criteria.andNameLike("%"+tbContentCategory.getName()+"%");
		}
		
		Page<TbContentCategory>  pageinfo= (Page<TbContentCategory>)tbContentCategoryMapper.selectByExample(contentExample);
		return  new pageResult(pageinfo.getTotal(), pageinfo.getResult());
	}

	

}
