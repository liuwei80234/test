    app.service('specService',function($http){
    	//查询全部
    	this.findAll=function(){
    		return  $http.get('../spec/findAll.do');
    	}
    	//分页
    	this.findPage=function(page,rows){
    	   return $http.get('../spec/findPage.do?page='+page+'&rows='+rows)
    	} 
    	//条件查询
    	this.findSearch=function(page,rows,searchEntity){
    		return $http.post('../spec/findSearch.do?page='+page+"&rows="+rows,searchEntity)
    	}
    	//保存
    	this.update =function(entity){
    		return $http.post('../spec/update.do',entity)
    	}
    	this.addBrand =function(entity){
    		return $http.post('../spec/addBrand.do',entity)
    	}
    	//查询实体
    	this.findOne=function(id){
    		return $http.get('../spec/findOne.do?id='+id)
    	}
    	//删除
    	this.dele=function(selectIds) {
    		return $http.get('../spec/delete.do?ids='+selectIds)
    	}
    	//品牌规格下拉框
    	
    	this.selectOptionList=function(){
    		return $http.get('../spec/selectOptionList.do');
    	}
    })