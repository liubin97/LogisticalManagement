//
// function doSearchByPsId() {
//     var req;
//     /* 	XMLHTTPRequest
// 		1.创建对象
// 		2.建立要连接的URL
// 		3.打开到服务器的连接
// 		4.设置回调函数
// 		5.发送请求
// 	*/
//
//     if(window.XMLHttpRequest){
//         //非IE浏览器
//         req = new XMLHttpRequest();
//     }
//     else if(window.ActiveXObject){
//         //IE浏览器
//         req = new ActiveXObject("Microsoft.XMLHTTP");
//     }
//
//     if(document.getElementById("search").value==""){
//         alert("购货单号不能为空");
//     }else{
//         //打开到服务器的连接
//         req.open("post","cenWarehouseServlet?action=searchPs",false);
//         //设置请求头信息
//         req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
//         //设定回调函数
//         req.onreadystatechange = function () {
//             if(req.readyState == 4){
//                 if(req.status == 200){
//                     var rs = req.responseText;
//                     var obj = JSON.parse(rs);
//                     var pd = document.getElementById("productname") ;
//                     pd.value = obj.productname;
//                     var pn = document.getElementById("productnum") ;
//                     pn.value = obj.productnum;
//                     var ac = document.getElementById("acnum");
//                     ac.value = obj.productnum;
//                 }
//             }
//         };
//         //发送请求
//         req.send("psid="+document.getElementById("search").value);
//     }
// }

//使用jQuery实现ajax
function doSearchByPsId() {
    if ($("#search").val() != "") {
        var searchval = $("#search").val();
        $.ajax({
            url: "cenWarehouseServlet?action=searchPs",
            data: "psid=" + $("#search").val(),
            datatype: "text",
            type: "POST",
            contentType: "application/x-www-form-urlencoded",
            success: function (data) {//这里的data是由请求页面返回的数据
                var dataJson = JSON.parse(data); // 使用parse方法将data转换成json格式
                $("#productname").val(dataJson.productname);
                $("#productnum").val(dataJson.productnum);
                $("#acnum").val(dataJson.productnum);
                $("#psid").val(searchval);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("error");
            }
        });
    } else {
        alert("购货单号不能为空");
    }
}


