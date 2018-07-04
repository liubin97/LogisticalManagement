var req;
function tuihuo(count,supplier_id,prod_id,product_name,price,rs_num,criid){
	$.ajax({
        url: "purchaseReturnServlet?action=returnsupplier",
        data: {
        	supplier_id:supplier_id,
        	prod_id:prod_id,
        	product_name:product_name,
        	rs_num:rs_num,
        	price:price,
        	criid:criid
        },
        datatype: "text",
        type: "POST",
        contentType: "application/x-www-form-urlencoded",
        success: function (data) {//这里的data是由请求页面返回的数据
            var dataobj = JSON.parse(data); // 使用parse方法将data转换成json格式
            alert(dataobj.msg);
            updatePage(count);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("error");
        }
    });
	

}
function updatePage(count){
		$("#abc"+count).remove();

}