package com.aisile.pojo.entity.group;

import java.io.Serializable;
import java.util.List;

import com.aisile.pojo.TbSpecification;
import com.aisile.pojo.TbSpecificationOption;

public class Specification implements Serializable{
	private TbSpecification specification;
	private List<TbSpecificationOption> specificationOptionList;
	public TbSpecification getSpecification() {
		return specification;
	}
	public void setSpecification(TbSpecification specification) {
		this.specification = specification;
	}
	public List<TbSpecificationOption> getSpecificationOptionList() {
		return specificationOptionList;
	}
	public void setSpecificationOptionList(List<TbSpecificationOption> specificationOptionList) {
		this.specificationOptionList = specificationOptionList;
	}
	
	
}
