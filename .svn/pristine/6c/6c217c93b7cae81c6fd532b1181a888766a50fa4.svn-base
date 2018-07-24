package com.aisile.sellergoods.service;

import java.util.List;
import java.util.Map;



import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbSeller;
import com.aisile.pojo.entity.Result;
import com.aisile.pojo.entity.pageResult;


public interface SellerService {
	//查询全部
	public List<TbSeller> findAll();
	//分页
	public pageResult findPage(int pageNum,int pageSize);
	//增加
	public void add(TbSeller tbSeller);
	//修改
	public void update(TbSeller tbSeller);
	//根据id找值
	public TbSeller findOne(String id);
	//批删
	public void delete(Long [] ids);
	/*
	 * 条件查询
	 * */
	public pageResult findSearch(TbSeller tbSeller, int pageNum,int pageSize);
	
	public void updateStatus(TbSeller tbSeller,String status);
	
}
