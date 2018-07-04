$(document).ready(function(){
	$.ajax({
		 type:"POST", //请求方式  
       url:"substationServlet?action=initialize_settled", //请求路径  
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
})