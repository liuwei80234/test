package com.aisile.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.aisile.pojo.TbSpecification;
import com.aisile.pojo.entity.Result;
import com.aisile.pojo.entity.pageResult;
import com.aisile.pojo.entity.group.Specification;
import com.aisile.sellergoods.service.SpecService;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/spec")
public class SpecController {
	@Reference
	private SpecService specService;
	//列表
	@RequestMapping("/findAll")
	public List<TbSpecification> findAll(){
		return specService.findAll();
	}
	 @RequestMapping("/findPage")
		public pageResult findPage(int page,int rows){
			return specService.findPage(page, rows); 
		}
	 
	 @RequestMapping("/findSearch")
     public pageResult findSearch(@RequestBody TbSpecification specification, int page, int rows ){
    	 return specService.findSearch(specification, page, rows);
     }
	 
	  @RequestMapping("/addBrand")
	    public Result add(@RequestBody Specification specification){
	    	 try {
				specService.add(specification);
				return new Result(true, "成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			   return new Result(false, "失败");
			}
	    	
	     }
	  
	  //获取实体
	     @RequestMapping("/findOne")
	     public Specification findOne(Long id){
	 		return specService.findOne(id);		
	 	}
	   //修改
	     @RequestMapping("/update")
	     public Result update(@RequestBody Specification specification){
	    	 try {
	 			specService.update( specification);
	 			return new Result(true, "修改成功");
	 		} catch (Exception e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		   return new Result(false, "修改失败");
	 		}
	     }
	    
	       //批删
	     @RequestMapping("/delete")
	     public Result delete(Long[] ids){	
	    	 try {
				specService.delete(ids);
				return new Result(true, "删除成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new Result(false, "修改失败");
			}
	     }
	     
	     //下拉框回显
	     @RequestMapping("/selectOptionList")
	 	public List<Map> selectOptionList(){
	 		return specService.selectOptionList();
	 	}
}
