package com.aisile.sellergoods.service;

import java.util.List;
import java.util.Map;



import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbItemCat;
import com.aisile.pojo.TbSeller;
import com.aisile.pojo.entity.Result;
import com.aisile.pojo.entity.pageResult;


public interface TbItemCatService {
	//查询全部
	public List<TbItemCat> findAll();
	//分页
	public pageResult findPage(int pageNum,int pageSize);
	//增加
	public void add(TbItemCat tbItemCat);
	//修改
	public void update(TbItemCat tbItemCat);
	//根据id找值
	public TbItemCat findOne(Long id);
	//批删
	public void delete(Long id);
	/*
	 * 条件查询
	 * */
	public pageResult findSearch(TbItemCat bItemCat, int pageNum,int pageSize);
	public List<TbItemCat> findAllByparentId(Long parent_id);
	public int count(Long id);
	
	

	
}
