    app.service('itemcatService',function($http){
    	//查询全部
    	this.findAll=function(){
    		return  $http.get('../itemcat/findAll.do');
    	}
    	this.findAllByparentId=function(parentid){
    		return  $http.get('../itemcat/findAllByparentId.do?parentid='+parentid);
    	}
    	
    	
    	
    	//分页
    	this.findPage=function(page,rows){
    	   return $http.get('../itemcat/findPage.do?page='+page+'&rows='+rows)
    	} 
    	//条件查询
    	this.findSearch=function(page,rows,searchEntity){
    		return $http.post('../itemcat/findSearch.do?page='+page+"&rows="+rows,searchEntity)
    	}
    	//保存
    	this.update =function(entity){
    		return $http.post('../itemcat/update.do',entity)
    	}
    	this.addBrand =function(entity){
    		return $http.post('../itemcat/addBrand.do',entity)
    	}
    	//查询实体
    	this.findOne=function(id){
    		return $http.get('../itemcat/findOne.do?id='+id)
    	}
    	//删除
    	this.dele=function(ids) {
    		return $http.get('../itemcat/delete.do?ids='+ids)
    	}
    	
    })