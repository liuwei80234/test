package com.aisile.shop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aisile.pojo.TbGoods;
import com.aisile.pojo.TbGoods;
import com.aisile.pojo.entity.Result;
import com.aisile.pojo.entity.pageResult;
import com.aisile.pojo.entity.group.Goods;
import com.aisile.sellergoods.service.GoodsService;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/goods")
public class GoodController {
  
	@Reference
	private GoodsService goodsService;
	
	@RequestMapping("/findAll")
	public List<TbGoods> findAll(){		
		
		return goodsService.findAll();
	}
   @RequestMapping("/findPage")
	public pageResult findPage(int page,int rows){
		return goodsService.findPage(page, rows); 
	}
     @RequestMapping("/addgoods")
    public Result add(@RequestBody Goods goods){
    	 try {
    		String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
    		goods.getGoods().setSellerId(sellerId);//设置商家ID
			goodsService.add(goods);
			return new Result(true, "成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   return new Result(false, "失败");
		}
    	
     }
     //修改
     @RequestMapping("/update")
     public Result update(@RequestBody Goods goods){
    	
    	 try {
    			//校验是否是当前商家的id		
    	 		Goods goods2 = goodsService.findOne(goods.getGoods().getId());
    	 		//获取当前登录的商家ID
    	 		String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
    	 		//如果传递过来的商家ID并不是当前登录的用户的ID,则属于非法操作
    	 		if(!goods2.getGoods().getSellerId().equals(sellerId) || !goods.getGoods().getSellerId().equals(sellerId) ){
    	 			return new Result(false, "操作非法");		
    	 		}		
 			goodsService.update(goods);
 			return new Result(true, "修改成功");
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		   return new Result(false, "修改失败");
 		}
     }
     //获取实体
     @RequestMapping("/findOne")
     public Goods findOne(Long id){
    	
 		return goodsService.findOne(id);		
 	}
     //批删
     @RequestMapping("/delete")
     public Result delete(Long[] ids){
    	 try {
			goodsService.delete(ids);
			return new Result(true, "删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
     }
     @RequestMapping("/findSearch")
     public pageResult findSearch(@RequestBody TbGoods goods, int page, int rows ){
    	//获取商家ID
 		String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
 		//添加查询条件 
 		goods.setSellerId(sellerId);
    	 
    	 return goodsService.findSearch(goods,page, rows);	
     }
    
     @RequestMapping("/updateMake")
   	public Result updateStatus(Long[] ids, String isMarketable){	
   		
   		try {
   			System.out.println(ids);
   			System.out.println(isMarketable);
   			goodsService.updateMake(ids, isMarketable);
   			return new Result(true, "成功");
   		} catch (Exception e) {
   			e.printStackTrace();
   			return new Result(false, "失败");
   		}
   	}
     @RequestMapping("/ChangeStatus")
     public Result ChangeStatus(Long[] ids, String AuditStatus){	
    	 
    	 try {
    		
    		 goodsService.ChangeStatus(ids, AuditStatus);
    		 return new Result(true, "成功");
    	 } catch (Exception e) {
    		 e.printStackTrace();
    		 return new Result(false, "失败");
    	 }
     }
}
