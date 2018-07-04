var flag = false;
$(document).ready(function(){
	$.ajax({
		 type:"POST", //请求方式  
       url:"invoiceServlet?action=initialize", //请求路径  
       cache: false,     
       dataType: 'json',   //返回值类型  
       success:function(json){        
      	 for(var i=0;i<json.length; i++){
	             $(".substation").append("<option value="+json[i].substation+">"+json[i].substation+"</option>")  
      	 }

       }  
   });  
})
function validateInvoice(){

	$.ajax({
		 type:"POST", //请求方式  
      url:"invoiceServlet?action=validateUse&", //请求路径  
      data: "invoiceNumber="+$("#invoiceNumber").val(),
      dataType: 'text',   //返回值类型  
      success:function(text){        
        	if(text=="true"){
        		flag = true;
        		$("#img_invoiceNumber").attr("src","assets/img/tick.png");
        		$("#res_invoiceNumber").html("");
        	}
        	else{
        		$("#img_invoiceNumber").attr("src","assets/img/error.png");
        		$("#res_invoiceNumber").html("发票号码格式不对或该发票无法领用");
        	}
      }  
  });  
	
	
}
function test(){
	if(flag){
		$("#use").submit();
	}
	else{
		alert("输入有误");
	}
}