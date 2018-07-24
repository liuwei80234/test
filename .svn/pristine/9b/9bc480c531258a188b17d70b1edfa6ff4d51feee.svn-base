package com.aisile.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aisile.pojo.TbBrand;
import com.aisile.pojo.entity.Result;
import com.aisile.pojo.entity.pageResult;
import com.aisile.sellergoods.service.BrandService;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/brand")
public class BrandController {
  
	@Reference
	private BrandService brandService;
	
	@RequestMapping("/findAll")
	public List<TbBrand> findAll(){		
		
		return brandService.findAll();
	}
   @RequestMapping("/findPage")
	public pageResult findPage(int page,int rows){
		return brandService.findPage(page, rows); 
	}
     @RequestMapping("/addBrand")
    public Result add(@RequestBody TbBrand brand){
    	 try {
			brandService.add(brand);
			return new Result(true, "成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   return new Result(false, "失败");
		}
    	
     }
     //修改
     @RequestMapping("/update")
     public Result update(@RequestBody TbBrand brand){
    	 try {
 			brandService.update( brand);
 			return new Result(true, "修改成功");
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		   return new Result(false, "修改失败");
 		}
     }
     //获取实体
     @RequestMapping("/findOne")
     public TbBrand findOne(Long id){
 		return brandService.findOne(id);		
 	}
     //批删
     @RequestMapping("/delete")
     public Result delete(Long[] ids){
    	 try {
			brandService.delete(ids);
			return new Result(true, "删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
     }
     @RequestMapping("/findSearch")
     public pageResult findSearch(@RequestBody TbBrand brand, int page, int rows ){
    	 return brandService.findSearch(brand, page, rows);
     }
     
     //下拉框回显
     @RequestMapping("/selectOptionList")
 	public List<Map> selectOptionList(){
 		return brandService.selectOptionList();
 	}
}
