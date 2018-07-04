var req;
function deleteWarehouse(wh_id){
	/*
	 * XMLHTTPRequest
	 * 1.创建对象
	 * 2.建立要连接的URL
	 * 3.打开到服务器的连接
	 * 4.设置回调函数
	 * 5.发送请求
	 */
	if(window.XMLHttpRequest){
		//非IE浏览器
		req=new XMLHttpRequest();
		
	}else if(widow.ActiveXObject){
		req=new ActiveXObject("Microsoft.XMLHTTP");
	}
	//打开到服务器的连接
	req.open("post","manageWhServlet?action=deletewh",false);
	//设置请求头信息
	req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	//回调函数
	req.onreadystatechange=function(){
		updatePage(wh_id);
	};
	//发送请求
	req.send("wh_id="+wh_id);
	
	

}
function updatePage(wh_id){
	if(req.readyState==4){
		if(req.status==200){
			var result=req.responseText;
			if(result=="true"){
				alert("删除成功！");
				/* */
				$("#searchwh"+wh_id).remove();
			}else{
				alert("删除失败，库房中有商品！");
				
			}
		}
		
	}
}