    app.service('contentCateService',function($http){
    	//查询全部
    	this.findAll=function(){
    		return  $http.get('../contentCate/findAll.do');
    	}
    	//分页
    	this.findPage=function(page,rows){
    	   return $http.get('../contentCate/findPage.do?page='+page+'&rows='+rows)
    	} 
    	//条件查询
    	this.findSearch=function(page,rows,searchEntity){
    		return $http.post('../contentCate/findSearch.do?page='+page+"&rows="+rows,searchEntity)
    	}
    	//保存
    	this.update =function(entity){
    		return $http.post('../contentCate/update.do',entity)
    	}
    	this.addBrand =function(entity){
    		return $http.post('../contentCate/addBrand.do',entity)
    	}
    	//查询实体
    	this.findOne=function(id){
    		return $http.get('../contentCate/findOne.do?id='+id)
    	}
    	//删除
    	this.dele=function(selectIds) {
    		return $http.get('../contentCate/delete.do?ids='+selectIds)
    	}
    	
    })