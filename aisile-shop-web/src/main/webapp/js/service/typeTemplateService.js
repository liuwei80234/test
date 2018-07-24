    app.service('typeTemplateService',function($http){
    	//查询全部
    	this.findAll=function(){
    		return  $http.get('../typeTemplate/findAll.do');
    	}
    	//分页
    	this.findPage=function(page,rows){
    	   return $http.get('../typeTemplate/findPage.do?page='+page+'&rows='+rows)
    	} 
    	//条件查询
    	this.findSearch=function(page,rows,searchEntity){
    		return $http.post('../typeTemplate/findSearch.do?page='+page+"&rows="+rows,searchEntity)
    	}
    	//保存
    	this.update =function(entity){
    		return $http.post('../typeTemplate/update.do',entity)
    	}
    	this.addBrand =function(entity){
    		return $http.post('../typeTemplate/addBrand.do',entity)
    	}
    	//查询实体
    	this.findOne=function(id){
    		return $http.get('../typeTemplate/findOne.do?id='+id)
    	}
    	
    	//规格列表
    	this.findSpecList=function(id){
    		return $http.get('../typeTemplate/findSpecList.do?id='+id)
    	}
    	//删除
    	this.dele=function(selectIds) {
    		return $http.get('../typeTemplate/delete.do?ids='+selectIds)
    	}
    	
    	
    })