package com.aisile.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.aisile.pojo.TbBrand;
import com.aisile.pojo.entity.pageResult;


public interface BrandService {
	//查询全部
	public List<TbBrand> findAll();
	//分页
	public pageResult findPage(int pageNum,int pageSize);
	//增加
	public void add(TbBrand tbBrand);
	//修改
	public void update(TbBrand brand);
	//根据id找值
	public TbBrand findOne(Long id);
	//批删
	public void delete(Long [] ids);
	/*
	 * 条件查询
	 * */
	public pageResult findSearch(TbBrand brand, int pageNum,int pageSize);
	//品牌下拉框
	List<Map> selectOptionList();
}
