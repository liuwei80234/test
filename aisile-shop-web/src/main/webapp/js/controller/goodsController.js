    app.controller('goodsController',function($scope,$controller,$location,goodsService,uploadService,itemcatService,typeTemplateService){
    	
    	//继承basecontroller
    	$controller('baseController',{$scope:$scope});
    	
     $scope.entity={goodsDesc:{itemImages:[],specificationItems:[]}}
     
    	
   	 $scope.list=[];
   	 $scope.findAll=function(){
   		goodsService.findAll.success(
   		  function(response){
   			  $scope.list=response;
   		  }
   		 );
   	 }
   	 
   	 $scope.findPage=function(page,rows){
   		 goodsService.findPage(page,rows,$scope.searchEntity).success(
   	    		  function(response){
   	    			$scope.list=response.rows;	
   	  				$scope.paginationConf.totalItems=response.total;//更新总记录数
   	    		  }
   	    		 
   	    		 );
   	 }
   
   	//保存 
   	 $scope.save=function(){
   	    //提取文本编辑器的值
   		$scope.entity.goodsDesc.introduction=editor.html();	
   		 var methodName="";
   			if($scope.entity.goods.id!=null){//如果有ID
   				methodName=goodsService.update($scope.entity);
   			}else{
   				methodName=goodsService.add($scope.entity);
   				
   			}	
			methodName.success(
   	 		function(response){
   	 			if(response.success){
   	 				alert("成功")
   	 				 location.href="goods.html";//跳转到商品列表页
   	 			 }else{
   	 				 alert(response.message);
   	 			 }
   	 		}		
   	 	);				
   	 }
   	
   	//查询实体 
   	 $scope.findOne=function(id){
   		var id= $location.search()['id'];//获取参数值
		if(id==null){
			return ;
		}
   		goodsService.findOne(id).success(function(response){
   			$scope.entity= response;
   			
   			//显示图片列表
   			$scope.entity.goodsDesc.itemImages=JSON.parse($scope.entity.goodsDesc.itemImages);
   			//显示扩展属性
			$scope.entity.goodsDesc.customAttributeItems=JSON.parse($scope.entity.goodsDesc.customAttributeItems);	
			//规格				
			$scope.entity.goodsDesc.specificationItems=JSON.parse($scope.entity.goodsDesc.specificationItems);
			//SKU列表规格列转换				
			for( var i=0;i<$scope.entity.itemList.length;i++ ){
				
					$scope.entity.itemList[i].spec = JSON.parse($scope.entity.itemList[i].spec);		
			}		
   	   }
   	 	);				
   	 }
   //根据规格名称和选项名称返回是否被勾选
   	$scope.checkAttributeValue=function(specName,optionName){
   		var items= $scope.entity.goodsDesc.specificationItems;
   		var object= $scope.searchObjectByKey(items,'attributeName',specName);
   		if(object==null){
   			return false;
   		}else{
   			if(object.attributeValue.indexOf(optionName)>=0){
   				return true;
   			}else{
   				return false;
   			}
   		}			
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
					goodsService.dele($scope.selectIds).success(
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
   		goodsService.findSearch(page,rows,$scope.searchEntity).success(
   			function(response){
   					$scope.paginationConf.totalItems=response.total;//总记录数 
   					$scope.list=response.rows;//给列表变量赋值 
   			}		
   		);				
   	}
   	
   	
	//保存 
	$scope.add=function(){							
		goodsService.add($scope.entity).success(
			function(response){
				if(response.success){
					alert('保存成功');					
					$scope.entity={};
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//上传
	$scope.uploadFile=function(){	  
		uploadService.uploadFile().success(function(response) {        	
        	if(response.success){//如果上传成功，取出url
        		$scope.image_entity.url=response.message;//设置文件地址
        	}else{
        		alert(response.message);
        	}
        }).error(function() {           
        	     alert("上传发生错误");
        });        
    };  
    
    //$scope.entity={goods:{},goodsDesc:{itemImages:[]}};//定义页面实体结构.
    //添加图片列表
    $scope.add_image_entity=function(){    	
        $scope.entity.goodsDesc.itemImages.push($scope.image_entity);
    	
    }
    
    //列表中移除图片
    $scope.remove_image_entity=function(index){
    	    $scope.entity.goodsDesc.itemImages.splice(index,1);
    }
    
    //获取一级分类
    $scope.selectItemCatList=function(){
    	itemcatService.findAllByparentId(0).success(function(response){
    		$scope.selectItemCatList=response;
    		
    	})
    	
    }
   
 //二级分类
    
    $scope.$watch("entity.goods.category1Id",function(newVal,oldVal){
    	
    	
    	itemcatService.findAllByparentId(newVal).success(function(response){
    		$scope.selectItemCat1List=response;
    	})	
    })
    //三级分类
       
    $scope.$watch("entity.goods.category2Id",function(newVal,oldVal){
    	
    	
    	itemcatService.findAllByparentId(newVal).success(function(response){
    		$scope.selectItemCat2List=response;
    	})	
    })
    
    //监控三级分类 并获取id
    $scope.$watch("entity.goods.category3Id",function(newVal,oldVal){
    	itemcatService.findOne(newVal).success(
    		function(response){
    			$scope.entity.goods.typeTemplateId=response.typeId;
    		}	
    	  
    	)
    	
    })
   //获取品牌列表
    $scope.$watch("entity.goods.typeTemplateId",function(newVal,oldVal){
    	typeTemplateService.findOne(newVal).success(
    	  function(response){
    		  $scope.typeTemplate=response;
    		  $scope.typeTemplate.brandIds=JSON.parse(response.brandIds);
				if($location.search()['id']==null){
					$scope.entity.goodsDesc.customAttributeItems = JSON.parse($scope.typeTemplate.customAttributeItems);//扩展属性	
				}				
    		  
    	  })
    	  //获取规格列表
    	  typeTemplateService.findSpecList(newVal).success(function(response){
    		  $scope.specList=response;
    	  })
    	
    })
 
    $scope.updateSpecAttribute=function($event,name,value){
    var object= $scope.searchObjectByKey( $scope.entity.goodsDesc.specificationItems ,'attributeName', name);		
        if(object!=null){	
    			if($event.target.checked ){
    			 
    				object.attributeValue.push(value);		
    			}else{
    				//取消勾选	
    				object.attributeValue.splice( object.attributeValue.indexOf(value ) ,1);//移除选项
    				//如果选项都取消了，将此条记录移除
    				console.log(object.attributeValue.length)
    				if(object.attributeValue.length==0){
    			$scope.entity.goodsDesc.specificationItems.splice($scope.entity.goodsDesc.specificationItems.indexOf(object),1);
    			
    				}				
    			}
    		}else{				
    				$scope.entity.goodsDesc.specificationItems.push(
    				{"attributeName":name,"attributeValue":[value]});
    		}		
    	}
    
       //创建sku
    $scope.createItemList=function(){	
    	$scope.entity.itemList=[{spec:{},price:0,num:99999,status:'0',isDefault:'0' } ];//初始
    	var items=  $scope.entity.goodsDesc.specificationItems;	
    	for(var i=0;i< items.length;i++){
    		$scope.entity.itemList = addColumn( $scope.entity.itemList,items[i].attributeName,items[i].attributeValue );    
    	}	
    }
  //添加列值 
    addColumn=function(list,columnName,conlumnValues){
    	var newList=[];//新的集合
    	for(var i=0;i<list.length;i++){
    		var oldRow= list[i];
    		for(var j=0;j<conlumnValues.length;j++){
    			var newRow= JSON.parse( JSON.stringify( oldRow )  );//深克隆
    			newRow.spec[columnName]=conlumnValues[j];
    			newList.push(newRow);
    		}    		 
    	} 		
    	return newList;
    }
    
    $scope.status=['未审核','已审核','审核未通过','关闭','审核中'];//商品状态
    $scope.itemCatList=[];//商品分类列表
    //加载商品分类列表
      $scope.findItemCatList=function(){		
    	  itemcatService.findAll().success(
      			function(response){		
      				for(var i=0;i<response.length;i++){
      					$scope.itemCatList[response[i].id]=response[i].name;
      				}
      			}
      	);
      }
//上下架
      $scope.updateMake=function(isMarketable){
    	  alert(isMarketable)
    		goodsService.updateMake($scope.selectIds,isMarketable).success(
    			function(response){
    				if(response.success){//成功
    					$scope.reloadList();//刷新列表
    					$scope.selectIds=[];//清空ID集合
    				}else{
    					alert(response.message);
    				}
    			}
    		);	
      }
      //提交审核
      $scope.ChangeStatus=function(AuditStatus){
    	  alert(AuditStatus)
    		goodsService.ChangeStatus($scope.selectIds,AuditStatus).success(
    			function(response){
    				if(response.success){//成功
    					$scope.reloadList();//刷新列表
    					$scope.selectIds=[];//清空ID集合
    				}else{
    					alert(response.message);
    				}
    			}
    		);	
      }
    
      
      
    });
	
	
	
