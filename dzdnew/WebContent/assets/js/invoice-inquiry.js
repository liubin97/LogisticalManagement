function change(){
	var infor = $(".inquiry").val();
	$("#inputInfor").attr("placeholder",infor);
}
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