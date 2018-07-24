package com.aisile.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.aisile.pojo.TbGoods;

import com.aisile.pojo.entity.pageResult;
import com.aisile.pojo.entity.group.Goods;


public interface GoodsService {
	//查询全部
	public List<TbGoods> findAll();
	//分页
	public pageResult findPage(int pageNum,int pageSize);
	
	//修改
	public void update(Goods goods);
	//根据id找值
	public Goods findOne(Long id);
	//批删
	public void delete(Long [] ids);
	/*
	 * 条件查询
	 * */
	public pageResult findSearch(TbGoods goods, int pageNum,int pageSize);
	public void add(Goods goods);
	//批量修改状态
	public void updateStatus(Long []ids,String status);
	public void updateMake(Long[] ids, String isMarketable);
	public void ChangeStatus(Long[] ids, String auditStatus);
}
