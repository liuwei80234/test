package com.aisile.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.aisile.mapper.TbSpecificationMapper;
import com.aisile.mapper.TbSpecificationOptionMapper;
import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbBrandExample;
import com.aisile.pojo.TbSpecification;
import com.aisile.pojo.TbSpecificationExample;
import com.aisile.pojo.TbSpecificationExample.Criteria;
import com.aisile.pojo.TbSpecificationOption;
import com.aisile.pojo.TbSpecificationOptionExample;
import com.aisile.pojo.entity.pageResult;
import com.aisile.pojo.entity.group.Specification;
import com.aisile.sellergoods.service.SpecService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class SpecServiceImpl implements SpecService {
    @Autowired
	private TbSpecificationMapper tbSpecificationMapper;
    @Autowired
    private TbSpecificationOptionMapper tbSpecificationOptionMapper;
	@Override
	public List<TbSpecification> findAll() {
		// TODO Auto-generated method stub
		return tbSpecificationMapper.selectByExample(null);
	}
	
	
	public pageResult findPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		Page<TbSpecification>  pageinfo= (Page<TbSpecification>)tbSpecificationMapper.selectByExample(null);
		return new pageResult(pageinfo.getTotal(), pageinfo.getResult());
	}
	
	public pageResult findSearch(TbSpecification specification, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		TbSpecificationExample tbSpecificationExample = new TbSpecificationExample();
		Criteria criteria = tbSpecificationExample.createCriteria();
		if(specification.getSpecName()!=null && !specification.getSpecName().equals("")){
			criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
		}
		Page<TbSpecification>  pageinfo= (Page<TbSpecification>)tbSpecificationMapper.selectByExample(tbSpecificationExample);
		return new pageResult(pageinfo.getTotal(), pageinfo.getResult());
	}


	@Override
	public void add(Specification specification) {
		// TODO Auto-generated method stub
		TbSpecification tbsp = specification.getSpecification();
		//插入规格
		
		tbSpecificationMapper.insert(tbsp);
		
		for (TbSpecificationOption specificationOption : specification.getSpecificationOptionList()) {
			//设置规格ID
			specificationOption.setSpecId(tbsp.getId());
			tbSpecificationOptionMapper.insert(specificationOption);
		}
	}


	@Override
	public Specification findOne(Long id) {
		// TODO Auto-generated method stub
		TbSpecification tbprimaryKey = tbSpecificationMapper.selectByPrimaryKey(id);
		TbSpecificationOptionExample tbSpecificationOptionExample = new TbSpecificationOptionExample();
		com.aisile.pojo.TbSpecificationOptionExample.Criteria criteria = tbSpecificationOptionExample.createCriteria();
		criteria.andSpecIdEqualTo(id);//根据id规格查询
		List<TbSpecificationOption> optionList  = tbSpecificationOptionMapper.selectByExample(tbSpecificationOptionExample);
		//构建组合实体类返回结果
		Specification specification = new Specification();
		specification.setSpecification(tbprimaryKey);
		specification.setSpecificationOptionList(optionList);
		return specification;
	}


	@Override
	public void update(Specification specification) {
		// TODO Auto-generated method stub
		TbSpecification tbspc = specification.getSpecification();
		//修改规格
		tbSpecificationMapper.updateByPrimaryKey(tbspc);
		//删除原来的规格明细
		TbSpecificationOptionExample tbSpecificationOptionExample = new TbSpecificationOptionExample();
		com.aisile.pojo.TbSpecificationOptionExample.Criteria criteria = tbSpecificationOptionExample.createCriteria();
		criteria.andSpecIdEqualTo(tbspc.getId());//根据id规格查询
		tbSpecificationOptionMapper.deleteByExample(tbSpecificationOptionExample);
		for (TbSpecificationOption specificationOption : specification.getSpecificationOptionList()) {
			//设置规格ID
			specificationOption.setSpecId(tbspc.getId());
			tbSpecificationOptionMapper.insert(specificationOption);
		}
	}


	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub
		for (Long id : ids) {
			tbSpecificationMapper.deleteByPrimaryKey(id);
			TbSpecificationOptionExample tbSpecificationOptionExample = new TbSpecificationOptionExample();
			com.aisile.pojo.TbSpecificationOptionExample.Criteria criteria = tbSpecificationOptionExample.createCriteria();
			criteria.andSpecIdEqualTo(id);
			tbSpecificationOptionMapper.deleteByExample(tbSpecificationOptionExample);
		}
	}


	@Override
	public List<Map> selectOptionList() {
		// TODO Auto-generated method stub
		return tbSpecificationMapper.selectOptionList();
	}

}
