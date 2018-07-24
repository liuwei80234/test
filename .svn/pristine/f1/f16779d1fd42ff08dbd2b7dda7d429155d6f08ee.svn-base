    app.service('sellerService',function($http){
    	//查询全部
    	this.findAll=function(){
    		return  $http.get('../seller/findAll.do');
    	}
    	//分页
    	this.findPage=function(page,rows){
    	   return $http.get('../seller/findPage.do?page='+page+'&rows='+rows)
    	} 
    	//条件查询
    	this.findSearch=function(page,rows,searchEntity){
    		return $http.post('../seller/findSearch.do?page='+page+"&rows="+rows,searchEntity)
    	}
    	//审核
    	this.updateStatus =function(entity,status){
    		return $http.post('../seller/updateStatus.do?status='+status,entity)
    	}
    	//保存
    	this.update =function(entity){
    		return $http.post('../seller/update.do',entity)
    	}
    	
    	//查询实体
    	this.findOne=function(id){
    		return $http.get('../seller/findOne.do?id='+id)
    	}
    	//删除
    	this.dele=function(selectIds) {
    		return $http.get('../seller/delete.do?ids='+selectIds)
    	}
    	//增加
    	this.add=function(entity) {
    		
    		return $http.get('../seller/add.do',entity)
    	}
    	
    })