$(function(){$("#onetitle_name").blur(
function(){
	
	var oneTitle_name = $(this).val();
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
);
});