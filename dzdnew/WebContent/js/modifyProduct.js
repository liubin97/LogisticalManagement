var flag1 = 1;
var flag2 = 1;
var flag3 =1;
var flag4 = 1;
var flag5 = 1;
var flag6 =1;
window.onload=function oneTitleList(){
	$.ajax({
		type:"post",
		url:"manageTitleServlet?action=oneTitleList",
		data:{},
		dataType:"json",
		success:function(data){
			//alert(data);
			var result = eval(data);
			for(var i in result){
				//alert(data[i].onetitle_name);
				$("#oneTitleList").append("<option value='"+data[i].onetitle_id+"'>"+data[i].onetitle_name+"</option>");
			}
			var selectOneValue = $("#selectOneValue").val();
			$("#oneTitleList option[value='"+selectOneValue+"']").attr("selected","selected");
			
			
		},
		error:function(data){
			alert("fail");
		},
		
	});
	$.ajax({
		type:"post",
		url:"manageProductServlet?action=supplierList",
		data:{},
		dataType:"json",
		success:function(data){
			//alert(data);
			var result = eval(data);
			for(var i in result){
				
				$("#supplier_id").append("<option value='"+data[i].supplier_id+"'>"+data[i].supplier_name+"</option>");
			}
			var selectSupplier = $("#selectSupplier").val();
			$("#supplier_id option[value='"+selectSupplier+"']").attr("selected","selected");
		},
		error:function(data){
			alert("fail");
		},
		
	});
}

$(document).ready(function(){$("#oneTitleList").change(function(){
	
	var oneTitle_id = $(this).val();
	var selOpt = $("#twotitle_id option");  
    selOpt.remove(); 
	$.ajax({
		type:"post",
		url:"manageTitleServlet?action=twoTitleList",
		data:{oneTitle_id : oneTitle_id},
		dataType:"json",
		success:function(data){
			//alert(data);
			var result = eval(data);
			for(var i in result){
				//alert(data[i].onetitle_name);
				$("#twotitle_id").append("<option value='"+data[i].twotitle_id+"'>"+data[i].twotitle_name+"</option>");
			}
			var selectTwoValue = $("#selectTwoValue").val();
			$("#twotitle_id option[value='"+selectTwoValue+"']").attr("selected","selected");
		},
		error:function(data){
			alert("fail");
		},
		
	});
})
$("#editProduct").click(function(){
	if(flag1==1&&flag2==1&&flag3==1&&flag4==1&&flag5==1&&flag6==1){
		$("#updateProduct").submit();
	}else if(flag1==0){
		alert("请重新输入商品名");
	}else if(flag2==0){
		alert("请重新输入商品折扣");
	}else if(flag3==0){
		alert("请重新输入成本价");
	}else if(flag4==0){
		alert("请重新输入原价");
	}else if(flag5==0){
		alert("请输入计量单位");
	}else if(flag6==0){
		alert("请输入厂商");
	}
	
})

		});
$(function(){
	$("#product_name").change(
		function(){
			
			var product_name = $(this).val();
			if($(this).val() == "" || $.trim($(this).val()).length == 0){
				alert("请输入商品名");
				//$("#submitProduct").attr("disabled","disabled");
				flag1=0;
			}else{
				
				$.ajax({
					url:"manageProductServlet?action=validateProduct",
					data:{product_name : product_name},
					type:"post",
					dataType:"text",
					success : function(data){
						
						if(data == "true"){
							//$("#submitProduct").attr("disabled",false); 
							flag1=1;
						}else{
							alert("商品名称重名，请重新输入");
							
							flag1=0;
							//$("#submitProduct").attr("disabled","disabled");
						}
					},
					error:function(data){
						alert("fail");
					}
					
				});
			}
		}		
		);

	$("#discount").change(
		function(){
			var re = /^[0-9]+(.[0-9]{1,3})?$/;
			var discount = $(this).val();
			
			if($(this).val() == "" || $.trim($(this).val()).length == 0){
				alert("请输入折扣");
				flag2=0;
				//$("#submitProduct").attr("disabled","disabled");
			}else if(re.test(discount)){
				if(discount>0&&discount<=1){
					flag2=1;
				}else{
					alert("请输入0-1之间的数（不包括0,小数点后小于3位）");
					flag2=0;
				}
			}else{
				alert("请输入0-1之间的数（不包括0,小数点后小于3位）");
				flag2=0;
			}
		}		
		);
	$("#cost_price").change(
			function(){
				var re = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
				var cost_price = $(this).val();
				
				if($(this).val() == "" || $.trim($(this).val()).length == 0){
					alert("请输入成本价");
					flag3=0;
					//$("#ubmitProduct").attr("disabled","disabled");
				}else if(re.test(cost_price)){
					if(cost_price>0){
						flag3=1;
					}else{
						alert("请输入大于0的数");
						flag3=0;
					}
				}else{
					alert("请输入大于0的数");
					flag3=0;
				}
			}		
			);
	$("#original_price").change(
			function(){
				var re = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
				var original_price = $(this).val();
				
				if($(this).val() == "" || $.trim($(this).val()).length == 0){
					alert("请输入原价");
					flag4=0;
					//$("#submitProduct").attr("disabled","disabled");
				}else if(re.test(original_price)){
					if(original_price>0){
						flag4=1
					}else{
						alert("请输入大于0的数");
						flag4=0;
					}
				}else{
					alert("请输入大于0的数，小数点后小于3位");
					flag4=0;
				}
			}		
			);
	$("#measurement").change(
			function(){
				
				
				if($(this).val() == "" || $.trim($(this).val()).length == 0){
					alert("请输入计量单位");
					flag5=0;
					}
				else{
					flag5=1;
				}
			}
			)
			$("#vendor").change(
			function(){
				
				
				if($(this).val() == "" || $.trim($(this).val()).length == 0){
					alert("请输入厂商");
					flag6=0;
					}
				else{
					flag6=1;
				}
			}
			)
			
}
);