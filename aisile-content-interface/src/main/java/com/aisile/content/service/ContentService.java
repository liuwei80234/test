package com.aisile.content.service;

import java.util.List;


import com.aisile.pojo.TbContent;

import com.aisile.pojo.entity.pageResult;


public interface ContentService {
	//查询全部
	public List<TbContent> findAll();
	//分页
	public pageResult findPage(int pageNum,int pageSize);
	//增加
	public void add(TbContent tbContent);
	//修改
	public void update(TbContent tbContent);
	//根据id找值
	public TbContent findOne(Long id);
	//批删
	public void delete(Long [] ids);
	/*
	 * 条件查询
	 * */
	public pageResult findSearch(TbContent tbContent, int pageNum,int pageSize);
	
	public List<TbContent> findByCategoryId(Long categoryId);
	public void updateStatus(Long[] ids, String status);
 
}
