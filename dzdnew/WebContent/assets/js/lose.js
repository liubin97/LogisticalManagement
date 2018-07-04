var flag = false;
function validateInvoice(){

	$.ajax({
		 type:"POST", //请求方式  
      url:"invoiceServlet?action=validateInvalid&", //请求路径  
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
        		$("#res_invoiceNumber").html("发票号码格式不对或不存在");
        	}
      }  
  });  
	
	
}
function test(){
	if(flag){
		$("#lose").submit();
	}
	else{
		alert("输入有误");
	}
}