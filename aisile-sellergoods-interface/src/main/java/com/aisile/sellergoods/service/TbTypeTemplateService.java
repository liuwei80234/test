package com.aisile.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbTypeTemplate;
import com.aisile.pojo.entity.pageResult;

public interface TbTypeTemplateService {

	public List<TbTypeTemplate> findAll();

	public pageResult findPage(int pageNum,int pageSize );

	public pageResult findSearch(TbTypeTemplate tbTypeTemplate,int pageNum,int pageSize);
	
	//增加
	public void add(TbTypeTemplate tbTypeTemplate);
	//修改
	public void update(TbTypeTemplate tbTypeTemplate);
	//根据id找值
	public TbTypeTemplate findOne(Long id);
	//批删
	public void delete(Long [] ids);
	 
	public List<Map> findSpecList(Long id);
    
}
