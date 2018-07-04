var flag=false,flag2=false,flag2=false;
function validateInvoice(){

	$.ajax({
		 type:"POST", //请求方式  
      url:"invoiceServlet?action=validateRegister&", //请求路径  
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
        		$("#res_invoiceNumber").html("发票号码格式不对或已存在");
        	}
      }  
  });  
	
	
}
function validateAmount(){

	$.ajax({
		 type:"POST", //请求方式  
      url:"invoiceServlet?action=validateRegister&", //请求路径  
      data: "amount="+$("#amount").val(),
      dataType: 'text',   //返回值类型  
      success:function(text){  
    	 
        	if(text=="true"){
        		flag2 = true;
        		$("#img_amount").attr("src","assets/img/tick.png");
        		$("#res_amount").html("");
        	}
        	else{
        		$("#img_amount").attr("src","assets/img/error.png");
        		$("#res_amount").html("请输入正确金额，小数最多两位");
        	}
      }  
  });  
	
	
}
function validateOrderNumber(){

	$.ajax({
		 type:"POST", //请求方式  
      url:"invoiceServlet?action=validateRegister&", //请求路径  
      data: "orderNumber="+$("#orderNumber").val(),
      dataType: 'text',   //返回值类型  
      success:function(text){      
        	if(text=="true"){
        		flag3 = true;
        		$("#img_orderNumber").attr("src","assets/img/tick.png");
        		$("#res_orderNumber").html("");
        	}
        	else{
        		$("#img_orderNumber").attr("src","assets/img/error.png");
        		$("#res_orderNumber").html("请输入正确未被使用的订单号");
        	}
      }  
  }); 
}
function test(){
	if(flag&&flag2&&flag3){
		$("#register").submit();
	}
	else{
		alert("输入有误");
	}
}