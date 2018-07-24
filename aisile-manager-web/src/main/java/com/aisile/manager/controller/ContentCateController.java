package com.aisile.manager.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aisile.content.service.ContentCateService;
import com.aisile.pojo.TbContentCategory;
import com.aisile.pojo.entity.Result;
import com.aisile.pojo.entity.pageResult;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/contentCate")
public class ContentCateController {
  
	@Reference
	private ContentCateService contentCateService;
	
	@RequestMapping("/findAll")
	public List<TbContentCategory> findAll(){		
		
		return contentCateService.findAll();
	}
   @RequestMapping("/findPage")
	public pageResult findPage(int page,int rows){
		return contentCateService.findPage(page, rows); 
	}
     @RequestMapping("/addBrand")
    public Result add(@RequestBody TbContentCategory contentCategory){
    	 try {
			contentCateService.add(contentCategory);
			return new Result(true, "成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   return new Result(false, "失败");
		}
    	
     }
     //修改
     @RequestMapping("/update")
     public Result update(@RequestBody TbContentCategory contentCategory){
    	 try {
 			contentCateService.update(contentCategory);
 			return new Result(true, "修改成功");
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		   return new Result(false, "修改失败");
 		}
     }
     //获取实体
     @RequestMapping("/findOne")
     public TbContentCategory findOne(Long id){
 		return contentCateService.findOne(id);		
 	}
     //批删
     @RequestMapping("/delete")
     public Result delete(Long[] ids){
    	 try {
			contentCateService.delete(ids);
			return new Result(true, "删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
     }
     @RequestMapping("/findSearch")
     public pageResult findSearch(@RequestBody TbContentCategory contentCategory, int page, int rows ){
    	 return contentCateService.findSearch(contentCategory, page, rows);
     }
     
   
}
