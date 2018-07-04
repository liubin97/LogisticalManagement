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
			var select_value = $("#select_value").val();
			$("#oneTitleList option[value='"+select_value+"']").attr("selected","selected");
			
		},
		error:function(data){
			alert("fail");
		},
		
	});
	
}
$(function(){$("#twotitle_name").blur(
		function(){
			
			var twoTitle_name = $(this).val();
			$.ajax({
				url:"manageTitleServlet?action=validateTwoTitle",
				data:{twoTitle_name : twoTitle_name},
				type:"post",
				dataType:"text",
				success : function(data){
					
					if(data == "true"){
						$("#submitTwoTitle").attr("disabled",false); 
					}else{
						alert("二级标题名称重名，请重新输入");
						$("#submitTwoTitle").attr("disabled","disabled");
					}
				},
				error:function(data){
					alert("fail");
				}
				
			});
		}		
		);
		});