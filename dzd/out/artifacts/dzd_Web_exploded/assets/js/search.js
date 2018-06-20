
function doSearchByPsId() {
    var req;
    /* 	XMLHTTPRequest
		1.创建对象
		2.建立要连接的URL
		3.打开到服务器的连接
		4.设置回调函数
		5.发送请求
	*/

    if(window.XMLHttpRequest){
        //非IE浏览器
        req = new XMLHttpRequest();
    }
    else if(window.ActiveXObject){
        //IE浏览器
        req = new ActiveXObject("Microsoft.XMLHTTP");
    }

    if(document.getElementById("search").value==""){
        alert("购货单号不能为空");
    }else{
        //打开到服务器的连接
        req.open("post","cenWarehouseServlet?action=searchPs",false);
        //设置请求头信息
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        //设定回调函数
        req.onreadystatechange = function () {
            if(req.readyState == 4){
                if(req.status == 200){
                    var rs = req.responseText;
                    document.getElementById("productname").innerHTML = rs.prod_id;
                    document.getElementById("productnum").innerHTML = rs.pur_num;
                    document.getElementById("acnum").innerHTML = rs.pur_num;
                }
            }
        };
        //发送请求
        req.send("psid="+document.getElementById("search").value);
    }



}