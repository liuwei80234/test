package com.aisile.shop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.aisile.pojo.TbTypeTemplate;
import com.aisile.pojo.entity.Result;
import com.aisile.pojo.entity.pageResult;
import com.aisile.sellergoods.service.TbTypeTemplateService;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/typeTemplate")
public class typeTemplateController {
	@Reference
	private TbTypeTemplateService tbTypeTemplateService;
	//列表
	@RequestMapping("/findAll")
	public List<TbTypeTemplate> findAll(){
		System.out.println(tbTypeTemplateService.findAll());
		return tbTypeTemplateService.findAll();
	}
	 @RequestMapping("/findPage")
		public pageResult findPage(int page,int rows){
			return tbTypeTemplateService.findPage(page, rows); 
		}
	 
	 @RequestMapping("/findSearch")
     public pageResult findSearch(@RequestBody TbTypeTemplate tbTypeTemplate, int page, int rows ){
    	 return tbTypeTemplateService.findSearch(tbTypeTemplate, page, rows);
     }
	 
	  @RequestMapping("/addBrand")
	    public Result add(@RequestBody TbTypeTemplate tbTypeTemplate){
	    	 try {
				tbTypeTemplateService.add(tbTypeTemplate);
				return new Result(true, "成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			   return new Result(false, "失败");
			}
	    	
	     }
	  
	  //获取实体
	     @RequestMapping("/findOne")
	     public TbTypeTemplate findOne(Long id){
	 		return tbTypeTemplateService.findOne(id);		
	 	}
	   //修改
	     @RequestMapping("/update")
	     public Result update(@RequestBody TbTypeTemplate tbTypeTemplate){
	    	 try {
	 			tbTypeTemplateService.update( tbTypeTemplate);
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
				tbTypeTemplateService.delete(ids);
				return new Result(true, "删除成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new Result(false, "删除失败");
			}
	     }
	     
	     @RequestMapping("findSpecList")
	     public List<Map> findSpecList(Long id){
	    	 return tbTypeTemplateService.findSpecList(id);
	     }
	     
}
