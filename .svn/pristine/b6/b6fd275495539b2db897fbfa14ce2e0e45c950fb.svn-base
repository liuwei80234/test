    app.controller('typeTemplateController',function($scope,typeTemplateService,$controller,brandService,specService){
    	
    	//继承basecontroller
    	$controller('baseController',{$scope:$scope});
   	 $scope.list=[];
   	 $scope.findAll=function(){
   		 typeTemplateService.findAll.success(
   		  function(response){
   			  $scope.list=response;
   		  }
   		 );
   	 }
   	 
   	 $scope.findPage=function(page,rows){
   		 typeTemplateService.findPage(page,rows,$scope.searchEntity).success(
   	    		  function(response){
   	    			$scope.list=response.rows;	
   	  				$scope.paginationConf.totalItems=response.total;//更新总记录数
   	    		  }
   	    		 
   	    		 );
   	 }
   
   	//保存 
   	 $scope.save=function(){
   		 var methodName="";
   			if($scope.entity.id!=null){//如果有ID
   				methodName=typeTemplateService.update($scope.entity);
   			}else{
   				methodName=typeTemplateService.addBrand($scope.entity);
   				
   			}	
   		 
   			methodName.success(
   	 		function(response){
   	 			if(response.success){
   	 				//重新查询 
   	 				 $scope.reloadList();//重新加载
   	 			 }else{
   	 				 alert(response.message);
   	 			 }
   	 		}		
   	 	);				
   	 }
   	
   	//查询实体 
   	 $scope.findOne=function(id){
   		typeTemplateService.findOne(id).success(
   	 			function(response){
   	 				$scope.entity= response;
   	 				$scope.entity.brandIds=JSON.parse($scope.entity.brandIds);
   	 				$scope.entity.specIds=JSON.parse($scope.entity.specIds);
   	 				$scope.entity.customAttributeItems=JSON.parse($scope.entity.customAttributeItems);
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
					typeTemplateService.dele($scope.selectIds).success(
							function(response){
								if(response.success){ 
										$scope.reloadList();//刷新列表
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
   		typeTemplateService.findSearch(page,rows,$scope.searchEntity).success(
   			function(response){
   					$scope.paginationConf.totalItems=response.total;//总记录数 
   					$scope.list=response.rows;//给列表变量赋值 
   			}		
   		);				
   	}
   	
   	//品牌数据
   	$scope.brandList={data:[]};//品牌列表
   	$scope.findBrandList=function(){
		brandService.selectOptionList().success(
			function(response){
				$scope.brandList={data:response};	
			}
		);		
	}
   	
   	//品牌规格
	$scope.SpecList={data:[]};//品牌列表
   	$scope.findSpecList=function(){
   		specService.selectOptionList().success(
			function(response){
				$scope.SpecList={data:response};	
			}
		);		
	}
   	
	//新增扩展属性行
	$scope.addTableRow=function(){	
		$scope.entity.customAttributeItems.push({});		
	}
	
	//删除扩展属性行
	$scope.deleTableRow=function(index){			
		$scope.entity.customAttributeItems.splice(index,1);//删除			
	} 
    });
	
	
	
