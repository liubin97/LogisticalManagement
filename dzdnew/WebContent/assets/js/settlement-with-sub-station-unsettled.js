function getInfor(){

	var flag = false;
	$(".subchk").each(function(){
		if($(this).is(':checked')){
			flag = true;
		}
	})
	if(flag){
		//期缴请求
		$(".subchk").each(function(){
			if(!$(this).is(':checked')){
				$(this).siblings().attr("name","none");
			}
		})
		$("#confirm").submit();
	}	else{
		//提示
		alert("请至少选择一个进行结算");
	}
	
}
$(document).ready(function(){
	//获取分站信息
	$.ajax({
		 type:"POST", //请求方式  
        url:"substationServlet?action=initialize_unsettle", //请求路径  
        cache: false,     
        dataType: 'json',   //返回值类型  
        success:function(json){        
       	 for(var i=0;i<json.substation.length; i++){
	             $(".substation").append("<option value="+json.substation[i].substation+">"+json.substation[i].substation+"</option>")  
       	 }
    	 for(var i=0;i<json.date.length; i++){

             $(".date").append("<option value="+json.date[i].date+">"+json.date[i].date+"</option>")  
    	 }
        }  
    });  
    //全选
	$("#allcheck").click(function(){
		if($("#allcheck").is(':checked')){
			$(".subchk").prop('checked',true);
		}
		else{
			$(".subchk").prop('checked',false);
		}
	});
	$(".subchk").click(function(){
		var allchk = false;
		$(".subchk").each(function(){
			if(!$(this).is(':checked')){
				allchk = true;
			}
		})
		if(allchk){
			$("#allcheck").prop('checked',false);
		}
		else{
			$("#allcheck").prop('checked',true);
		}
	})

});