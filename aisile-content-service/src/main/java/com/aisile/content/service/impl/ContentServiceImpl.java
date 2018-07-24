package com.aisile.content.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.aisile.content.service.ContentCateService;
import com.aisile.content.service.ContentService;
import com.aisile.mapper.TbContentCategoryMapper;
import com.aisile.mapper.TbContentMapper;
import com.aisile.pojo.TbContent;
import com.aisile.pojo.TbContentCategory;
import com.aisile.pojo.TbContentExample;
import com.aisile.pojo.TbContentExample.Criteria;
import com.aisile.pojo.entity.pageResult;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Service 
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper tbContentMapper;
	
	@Autowired
	private RedisTemplate redisTemplate;	
	@Override
	public List<TbContent> findAll() {
		// TODO Auto-generated method stub
		//条件为空 查询全部 
		return tbContentMapper.selectByExample(null);
	}
	@Override
	public pageResult findPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		Page<TbContent>  pageinfo= (Page<TbContent>)tbContentMapper.selectByExample(null);
		return new pageResult(pageinfo.getTotal(), pageinfo.getResult());
	}
	@Override
	public void add(TbContent tbContent) {
		// TODO Auto-generated method stub
		tbContentMapper.insert(tbContent);
		//清除缓存
	    redisTemplate.boundHashOps("content").delete(tbContent.getCategoryId());
	}
	@Override
	public void update(TbContent tbContent) {
		// TODO Auto-generated method stub
		//查询修改前的分类Id
		Long categoryId = tbContentMapper.selectByPrimaryKey(tbContent.getId()).getCategoryId();
		redisTemplate.boundHashOps("content").delete(categoryId);
		tbContentMapper.updateByPrimaryKey(tbContent);
		//如果分类ID发生了修改,清除修改后的分类ID的缓存
		if(categoryId.longValue()!=tbContent.getCategoryId().longValue()){
			redisTemplate.boundHashOps("content").delete(tbContent.getCategoryId());
		}	
	}
	@Override
	public TbContent findOne(Long id) {
		// TODO Auto-generated method stub
		return tbContentMapper.selectByPrimaryKey(id);
	}
	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub
		for(Long id:ids){
			//清除缓存
			Long categoryId = tbContentMapper.selectByPrimaryKey(id).getCategoryId();//广告分类ID
			redisTemplate.boundHashOps("content").delete(categoryId);
			tbContentMapper.deleteByPrimaryKey(id);
		}		
	}
	@Override
	public pageResult findSearch(TbContent tbContent, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		Page<TbContent>  pageinfo= (Page<TbContent>)tbContentMapper.selectByExample(null);
		return new pageResult(pageinfo.getTotal(), pageinfo.getResult());
	}
	@Override
	public List<TbContent> findByCategoryId(Long categoryId) {
		// TODO Auto-generated method stub
		List<TbContent> contentList= (List<TbContent>) redisTemplate.boundHashOps("content").get(categoryId);
		if(contentList==null){
			System.out.println("进来了");
			TbContentExample contentexample = new TbContentExample();
			Criteria criteria = contentexample.createCriteria();
			criteria.andCategoryIdEqualTo(categoryId);
			criteria.andStatusEqualTo("1");
			contentexample.setOrderByClause("sort_order");
			contentList = tbContentMapper.selectByExample(contentexample);//获取广告列表
			redisTemplate.boundHashOps("content").put(categoryId, contentList);//存入缓存 
		}else {
			System.out.println("从缓存读取数据");
		}
		
		return  contentList;
		
	}
	public void updateStatus(Long[] ids, String status) {
		// TODO Auto-generated method stub
		for(Long id:ids){
			Long categoryId = tbContentMapper.selectByPrimaryKey(id).getCategoryId();
			redisTemplate.boundHashOps("content").delete(categoryId);
			 TbContent tbContent = tbContentMapper.selectByPrimaryKey(id);
			 tbContent.setStatus(status);
			 tbContentMapper.updateByPrimaryKey(tbContent);
		}
	}

	

}
