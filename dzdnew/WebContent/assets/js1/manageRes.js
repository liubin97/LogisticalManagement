var req;
function Whname(obj){
	/*
	 * XMLHTTPRequest
	 * 1.创建对象
	 * 2.建立要连接的URL
	 * 3.打开到服务器的连接
	 * 4.设置回调函数
	 * 5.发送请求
	 */
	var wh_id=obj.value;
	if(window.XMLHttpRequest){
		//非IE浏览器
		req=new XMLHttpRequest();
		
	}else if(widow.ActiveXObject){
		req=new ActiveXObject("Microsoft.XMLHTTP");
	}
	//打开到服务器的连接
	req.open("post","manageResServlet?action=acWhname",false);
	//设置请求头信息
	req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	//回调函数
	req.onreadystatechange=function(){
		updateWhname(wh_id);
	};
	//发送请求
	req.send("wh_id="+wh_id);
	
	

}
function updateWhname(){
	if(req.readyState==4){
		if(req.status==200){
			var result=req.responseText;
			if(result!=null){
				$("#msg2").html(result);
			}
		}
		
	}
}



function Proname(obj){
	/*
	 * XMLHTTPRequest
	 * 1.创建对象
	 * 2.建立要连接的URL
	 * 3.打开到服务器的连接
	 * 4.设置回调函数
	 * 5.发送请求
	 */
	var pro_id=obj.value;
	if(window.XMLHttpRequest){
		//非IE浏览器
		req=new XMLHttpRequest();
		
	}else if(widow.ActiveXObject){
		req=new ActiveXObject("Microsoft.XMLHTTP");
	}
	//打开到服务器的连接
	req.open("post","manageResServlet?action=acProname",false);
	//设置请求头信息
	req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	//回调函数
	req.onreadystatechange=function(){
		updateProname(pro_id);
	};
	//发送请求
	req.send("pro_id="+pro_id);
	
	

}
function updateProname(pro_id){
	if(req.readyState==4){
		if(req.status==200){
			var result=req.responseText;
			if(result!=null){
				$("#msg1").html(result);
			}
		}
		
	}
}