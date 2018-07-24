package com.aisile.content.service;

import java.util.List;
import java.util.Map;

import com.aisile.pojo.TbContentCategory;
import com.aisile.pojo.entity.pageResult;


public interface ContentCateService {
	//查询全部
	public List<TbContentCategory> findAll();
	//分页
	public pageResult findPage(int pageNum,int pageSize);
	//增加
	public void add(TbContentCategory tbContentCategory);
	//修改
	public void update(TbContentCategory tbContentCategory);
	//根据id找值
	public TbContentCategory findOne(Long id);
	//批删
	public void delete(Long [] ids);
	/*
	 * 条件查询
	 * */
	public pageResult findSearch(TbContentCategory tbContentCategory, int pageNum,int pageSize);

}
