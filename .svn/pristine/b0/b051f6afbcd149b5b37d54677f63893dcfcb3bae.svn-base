    app.controller('itemcatController',function($scope,itemcatService,$controller){
    	
    	//继承basecontroller
    	$controller('baseController',{$scope:$scope});
   	 $scope.list=[];
   	 $scope.parentId=0;
   	 $scope.grade=1;
   	 $scope.setGrade=function(value){
   		 $scope.grade=value;
   	 }
   	 
   	 $scope.findAllList=function(p_entity){
   	    if($scope.grade==1){
   	    	$scope.entity_1=null;
   	    	$scope.entity_2=null;
   	    }
   	    if($scope.grade==2){
   	    	$scope.entity_1=p_entity;
   	    	$scope.entity_2=null;
   	    }
   	    if($scope.grade==3){
   	    	$scope.entity_2=p_entity;
   	    }
   		$scope.findAllByparentId(p_entity.id);
   	 }
   	 
   	 $scope.findAllByparentId=function(parentid){
   		$scope.parentId=parentid;
		itemcatService.findAllByparentId(parentid).success(
			function(response){
				$scope.list=response;
			}	
		  
		);
   	 }
    	
    	 
   	 
   	 
  	//保存 
   	 $scope.save=function(){
   		
   		 var methodName="";
   			if($scope.entity.id!=null){//如果有ID
   				methodName=itemcatService.update($scope.entity);
   			}else{
   				$scope.entity.parentId=$scope.parentId;
   				alert($scope.entity.parentId)
   				methodName=itemcatService.addBrand($scope.entity);
   				
   			}	
   		 
   			methodName.success(
   	 		function(response){
   	 			if(response.success){
   	 				//重新查询 
   	 				 $scope.findAllByparentId($scope.parentId);//重新加载
   	 			 }else{
   	 				 alert(response.message);
   	 			 }
   	 		}		
   	 	);				
   	 }
   	
   	 
   	 $scope.findAll=function(){
   		 itemcatService.findAll.success(
   		  function(response){
   			  $scope.list=response;
   		  }
   		 );
   	 }
   	 
   	 $scope.findPage=function(page,rows){
   		 itemcatService.findPage(page,rows,$scope.searchEntity).success(
   	    		  function(response){
   	    			$scope.list=response.rows;	
   	  				$scope.paginationConf.totalItems=response.total;//更新总记录数
   	    		  }
   	    		 
   	    		 );
   	 }
   
  
   	//查询实体 
   	 $scope.findOne=function(id){
   		itemcatService.findOne(id).success(
   	 			function(response){
   	 				$scope.entity= response;					
   	 		     }
   	 	);				
   	 }
   	
   	 
   
   	 
    	//批量删除 
    	 $scope.dele=function(){
    		 
    		 swal({
 				title : '确定删除吗？',
 				text : '你将无法恢复它！',
 				type : 'warning',
 				showCancelButton : true,
 				confirmButtonColor : '#3085d6',
 				cancelButtonColor : '#d33',
 				confirmButtonText : '确定！',
 				cancelButtonText : '取消！',
 				confirmButtonClass : 'btn btn-success',
 				cancelButtonClass : 'btn btn-danger'
 			}).then(function(isConfirm) {
 				if (isConfirm.value == true) {
 					itemcatService.dele($scope.selectIds).success(
 							function(response){
 								if(response.success){
 										 //刷新列表
 									$scope.findAllByparentId($scope.parentId);//重新加载
 								}else{
 									alert("删除失败！")
 								}						
 							}		
 					);				 
 				}else{
 									swal('删除失败！', '请稍后再试。', 'error');
 			}
 			})		
    	 } 
   	
   	
   	//条件查询
   	$scope.searchEntity={};//定义搜索对象
   	$scope.findSearch=function(page,rows){
   		itemcatService.findSearch(page,rows,$scope.searchEntity).success(
   			function(response){
   					$scope.paginationConf.totalItems=response.total;//总记录数 
   					$scope.list=response.rows;//给列表变量赋值 
   			}		
   		);				
   	}
   	
    });
	
	
	
