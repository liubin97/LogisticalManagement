$(document).ready(function(){
	$.ajax({
		 type:"POST", //请求方式  
        url:"suppliersServlet?action=initialize_settled", //请求路径  
        cache: false,     
        dataType: 'json',   //返回值类型  
        success:function(json){        
       	 for(var i=0;i<json.supplier.length; i++){
	             $(".supplier").append("<option value="+json.supplier[i].supplierName+">"+json.supplier[i].supplierName+"</option>")  
       	 }
       	 for(var i=0;i<json.date.length; i++){

	             $(".date").append("<option value="+json.date[i].date+">"+json.date[i].date+"</option>")  
       	 }
//弹出返回过来的List对象  
            //alert(json[1].username+" " + json[1].password);    //弹出返回过来的List对象
        }  
    });  
})