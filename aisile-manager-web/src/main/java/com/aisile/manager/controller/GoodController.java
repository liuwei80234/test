package com.aisile.manager.controller;

import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    		String seller_id = SecurityContextHolder.getContext().getAuthentication().getName();
    		goods.getGoods().setSellerId(seller_id);//设置商家ID
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
    	
    	 return goodsService.findSearch(goods, page, rows);
     }
    
 	@RequestMapping("/updateStatus")
 	public Result updateStatus(Long[] ids, String status){	
 		
 		try {
 			goodsService.updateStatus(ids, status);
 			return new Result(true, "成功");
 		} catch (Exception e) {
 			e.printStackTrace();
 			return new Result(false, "失败");
 		}
 	}
}
