package com.aisile.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbSpecification;
import com.aisile.pojo.entity.pageResult;
import com.aisile.pojo.entity.group.Specification;

public interface SpecService {
     //查询全部
	public List<TbSpecification> findAll();
	
	//分页
    public pageResult findPage(int pageNum,int pageSize);
    /*
	 * 条件查询
	 * */
	public pageResult findSearch(TbSpecification specification, int pageNum,int pageSize);
	/*
	 * 添加
	 * **/
	public void add(Specification specification);
	/**
	 * 根据id获得实体
	 * */
	public Specification findOne(Long id);
	//修改
	public void update(Specification specification);
	/**
	 * 删除
	 * */
	
	public void delete(Long[] ids);
	
	//品牌下拉框
		List<Map> selectOptionList();
}
