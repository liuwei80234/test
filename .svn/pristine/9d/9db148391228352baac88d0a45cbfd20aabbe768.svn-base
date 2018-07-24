package com.aisile.sellergoods.service.impl;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.aisile.mapper.TbSellerMapper;
import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbSeller;
import com.aisile.pojo.TbSellerExample;
import com.aisile.pojo.TbSellerExample.Criteria;
import com.aisile.pojo.entity.Result;
import com.aisile.pojo.entity.pageResult;

import com.aisile.sellergoods.service.SellerService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Service 
public class SellerServiceImpl implements SellerService {

	@Autowired
	private TbSellerMapper tbSellerMapper;
	@Override
	public List<TbSeller> findAll() {
		// TODO Auto-generated method stub
		//条件为空 查询全部 
		return tbSellerMapper.selectByExample(null);
	}
	@Override
	public pageResult findPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		Page<TbSeller>  pageinfo= (Page<TbSeller>)tbSellerMapper.selectByExample(null);
		return new pageResult(pageinfo.getTotal(), pageinfo.getResult());
	}
	@Override
	public void add(TbSeller tbSeller) {
		// TODO Auto-generated method stub
		tbSeller.setStatus("0");
		tbSeller.setCreateTime(new Date());
		System.out.println(tbSeller);
		tbSellerMapper.insert(tbSeller);
	}
	@Override
	public void update(TbSeller tbSeller) {
		// TODO Auto-generated method stub
		tbSellerMapper.updateByPrimaryKey(tbSeller);
	}
	@Override
	public TbSeller findOne(String id) {
		// TODO Auto-generated method stub
		return tbSellerMapper.selectByPrimaryKey(id);
	}
	/*@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub
		for(Long id:ids){
			tbSellerMapper.deleteByPrimaryKey(sellerId);
		}		
	}*/
	@Override
	public pageResult findSearch(TbSeller tbSeller, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		 TbSellerExample tbSellerExample = new TbSellerExample();
		 Criteria criteria = tbSellerExample.createCriteria();
		if(tbSeller.getName()!=null && !tbSeller.getName().equals("")){
			criteria.andNameLike("%"+tbSeller.getName()+"%");
		}
		if(tbSeller.getNickName()!=null && !tbSeller.getNickName().equals("")){
			criteria.andNickNameLike("%"+tbSeller.getNickName()+"%");
		}
		if(tbSeller.getStatus()!=null && !tbSeller.getStatus().equals("")){
			criteria.andStatusEqualTo(tbSeller.getStatus());
		}
		Page<TbSeller>  pageinfo= (Page<TbSeller>)tbSellerMapper.selectByExample(tbSellerExample);
		return new pageResult(pageinfo.getTotal(), pageinfo.getResult());
	}
	
	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateStatus(TbSeller tbSeller,String status) {
		// TODO Auto-generated method stub
		tbSeller.setStatus(status);
		
		tbSellerMapper.updateByPrimaryKey(tbSeller);
	}
	

}
