package com.aisile.shop.controller;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.aisile.pojo.TbSeller;
import com.aisile.pojo.entity.Result;
import com.aisile.pojo.entity.pageResult;
import com.aisile.sellergoods.service.SellerService;

import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/seller")
public class SellerController {
  
	@Reference
	private SellerService tbSellerService;
	
	@RequestMapping("/findAll")
	public List<TbSeller> findAll(){		
		
		return tbSellerService.findAll();
	}
   @RequestMapping("/findPage")
	public pageResult findPage(int page,int rows){
		return tbSellerService.findPage(page, rows); 
	}
   
    @RequestMapping("/addBrand")
    public Result add(@RequestBody TbSeller tbSeller){
    	 try {
    		 //盐值加密
    		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    		String password = passwordEncoder.encode(tbSeller.getPassword());
			tbSeller.setPassword(password);
    		tbSellerService.add(tbSeller);
			
			return new Result(true, "成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   return new Result(false, "失败");
		}
    	
     }
     //修改
     @RequestMapping("/update")
     public Result update(@RequestBody TbSeller tbSeller){
    	 try {
 			tbSellerService.update( tbSeller);
 			return new Result(true, "修改成功");
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		   return new Result(false, "修改失败");
 		}
     }
     //获取实体
     @RequestMapping("/findOne")
     public TbSeller findOne(String id){
 		return tbSellerService.findOne(id);		
 	}
     //批删
     @RequestMapping("/delete")
     public Result delete(Long[] ids){
    	 try {
			tbSellerService.delete(ids);
			return new Result(true, "删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
     }
     @RequestMapping("/findSearch")
     public pageResult findSearch(@RequestBody TbSeller tbSeller, int page, int rows ){
    	 return tbSellerService.findSearch(tbSeller, page, rows);
     }
    
}
