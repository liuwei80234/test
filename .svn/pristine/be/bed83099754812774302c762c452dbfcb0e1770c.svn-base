    app.service('brandService',function($http){
    	//查询全部
    	this.findAll=function(){
    		return  $http.get('../brand/findAll.do');
    	}
    	//分页
    	this.findPage=function(page,rows){
    	   return $http.get('../brand/findPage.do?page='+page+'&rows='+rows)
    	} 
    	//条件查询
    	this.findSearch=function(page,rows,searchEntity){
    		return $http.post('../brand/findSearch.do?page='+page+"&rows="+rows,searchEntity)
    	}
    	//保存
    	this.update =function(entity){
    		return $http.post('../brand/update.do',entity)
    	}
    	this.addBrand =function(entity){
    		return $http.post('../brand/addBrand.do',entity)
    	}
    	//查询实体
    	this.findOne=function(id){
    		return $http.get('../brand/findOne.do?id='+id)
    	}
    	//删除
    	this.dele=function(selectIds) {
    		return $http.get('../brand/delete.do?ids='+selectIds)
    	}
    	
    })