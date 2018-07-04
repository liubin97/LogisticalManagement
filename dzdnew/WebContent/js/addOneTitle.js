$(function(){$("#oneTitle_name").blur(
function(){
	
	var oneTitle_name = $(this).val();
	if($(this).val() == "" || $.trim($(this).val()).length == 0){
		alert("请输入一级标题");
		$("#submitOneTitle").attr("disabled","disabled");
	}else{
	$.ajax({
		url:"manageTitleServlet?action=validateOneTitle",
		data:{oneTitle_name : oneTitle_name},
		type:"post",
		dataType:"text",
		success : function(data){
			
			if(data == "true"){
				$("#submitOneTitle").attr("disabled",false); 
			}else{
				alert("一级标题名称重名，请重新输入");
				$("#submitOneTitle").attr("disabled","disabled");
			}
		},
		error:function(data){
			alert("fail");
		}
		
	});
	}
}		
);
});