package com.aisile.shop.controller;

import java.util.List;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.aisile.pojo.TbItemCat;
import com.aisile.pojo.entity.Result;
import com.aisile.pojo.entity.pageResult;
import com.aisile.sellergoods.service.TbItemCatService;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/itemcat")
public class itemcatController {
  
	@Reference
	private TbItemCatService tbItemCatService;
	@RequestMapping("/findAllByparentId")
	public List<TbItemCat> findAllByparentId(Long parentid){
		return tbItemCatService.findAllByparentId(parentid);
	}

	
	
	@RequestMapping("/findAll")
	public List<TbItemCat> findAll(){		
		
		return tbItemCatService.findAll();
	}
   @RequestMapping("/findPage")
	public pageResult findPage(int page,int rows){
		return tbItemCatService.findPage(page, rows); 
	}
     @RequestMapping("/addBrand")
    public Result add(@RequestBody TbItemCat tbItemCat){
    	 try {
			tbItemCatService.add(tbItemCat);
			return new Result(true, "成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   return new Result(false, "失败");
		}
    	
     }
     //修改
     @RequestMapping("/update")
     public Result update(@RequestBody TbItemCat tbItemCat){
    	 try {
 			tbItemCatService.update( tbItemCat);
 			return new Result(true, "修改成功");
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		   return new Result(false, "修改失败");
 		}
     }
   //获取实体
     @RequestMapping("/findOne")
     public TbItemCat findOne(Long id){
 		return tbItemCatService.findOne(id);		
 	}
     //批删
     @RequestMapping("/delete")
     public Result delete(Long[] ids){
    		for (Long id : ids) {
    			int count=	tbItemCatService.count(id);
    			System.out.println(count);
    			if (count==0) {	
    				tbItemCatService.delete(id);
    				return new Result(true, "删除成功"); 
    			}
    		}
    		 
    		return new Result(false, "删除失败"); 
     }
     @RequestMapping("/findSearch")
     public pageResult findSearch(@RequestBody TbItemCat tbItemCat, int page, int rows ){
    	 return tbItemCatService.findSearch(tbItemCat, page, rows);
     }
      
   
}
