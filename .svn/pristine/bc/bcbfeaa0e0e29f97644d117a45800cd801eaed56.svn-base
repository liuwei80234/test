package com.aisile.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.aisile.mapper.TbSpecificationMapper;
import com.aisile.mapper.TbSpecificationOptionMapper;
import com.aisile.mapper.TbTypeTemplateMapper;
import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbSpecification;
import com.aisile.pojo.TbSpecificationOption;
import com.aisile.pojo.TbSpecificationOptionExample;
import com.aisile.pojo.TbTypeTemplate;
import com.aisile.pojo.TbTypeTemplateExample;
import com.aisile.pojo.TbTypeTemplateExample.Criteria;
import com.aisile.pojo.entity.pageResult;
import com.aisile.sellergoods.service.TbTypeTemplateService;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class TbTypeTemplateServiceImpl implements TbTypeTemplateService{
     @Autowired
	private TbTypeTemplateMapper tbTypeTemplateMapper;
     
     @Autowired
     private TbSpecificationOptionMapper specOptionMapper;
	@Override
	public List<TbTypeTemplate> findAll() {
		// TODO Auto-generated method stub
		return tbTypeTemplateMapper.selectByExample(null);
	}

	@Override
	public pageResult findPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		Page<TbTypeTemplate>  pageinfo= (Page<TbTypeTemplate>)tbTypeTemplateMapper.selectByExample(null);
		return new pageResult(pageinfo.getTotal(), pageinfo.getResult());
	}

	

	@Override
	public pageResult findSearch(TbTypeTemplate tbTypeTemplate, int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		TbTypeTemplateExample templateExample = new TbTypeTemplateExample();
	 Criteria criteria = templateExample.createCriteria();
	   if(tbTypeTemplate.getName()!=null && !tbTypeTemplate.getName().equals("")){
			criteria.andNameLike("%"+tbTypeTemplate.getName()+"%");
		   
		}
		Page<TbTypeTemplate>  pageinfo= (Page<TbTypeTemplate>)tbTypeTemplateMapper.selectByExample(templateExample);
		return new pageResult(pageinfo.getTotal(), pageinfo.getResult());
	}

	@Override
	public void add(TbTypeTemplate tbTypeTemplate) {
		// TODO Auto-generated method stub
		tbTypeTemplateMapper.insert(tbTypeTemplate);
	}

	@Override
	public void update(TbTypeTemplate tbTypeTemplate) {
		// TODO Auto-generated method stub
		tbTypeTemplateMapper.updateByPrimaryKey(tbTypeTemplate);
	}

	@Override
	public TbTypeTemplate findOne(Long id) {
		// TODO Auto-generated method stub
		return tbTypeTemplateMapper.selectByPrimaryKey(id);
	}

	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub
		for(Long id:ids){
			tbTypeTemplateMapper.deleteByPrimaryKey(id);
		}		
	}
	//返回规格列表
       public List<Map> findSpecList(Long id){
    	   
    	   TbTypeTemplate template = tbTypeTemplateMapper.selectByPrimaryKey(id);
    	   List<Map> list = JSON.parseArray(template.getSpecIds(), Map.class);
    	   for (Map map : list) {
			TbSpecificationOptionExample optionExample = new TbSpecificationOptionExample();
		  optionExample.createCriteria().andSpecIdEqualTo(new Long((Integer)map.get("id")));
		  List<TbSpecificationOption> lists=specOptionMapper.selectByExample(optionExample);
		  map.put("options", lists);
		 
		}
    	   
    	   return list;
       }
}
