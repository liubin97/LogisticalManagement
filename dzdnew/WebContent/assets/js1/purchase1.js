var req;
function show(prod_id,order_id){
	var pnum=document.getElementById("pnum"+prod_id).value;
	var do_date=document.getElementById("do_date"+prod_id).value;	
	alert(order_id);
	$.ajax({
        url: "purchaseReturnServlet?action=acps1",
        data: {
        	pnum:pnum,
        	do_date:do_date,
        	prod_id:prod_id,
        	order_id:order_id
        	
        },
        datatype: "text",
        type: "POST",
        contentType: "application/x-www-form-urlencoded",
        success: function (data) {//这里的data是由请求页面返回的数据
            var dataobj = JSON.parse(data); // 使用parse方法将data转换成json格式
           $("#prod_id").html("商品id："+dataobj.prod_id);
           $("#prod_name").html("商品名字："+dataobj.prod_name);
           $("#order_id").html("订单编号："+dataobj.order_id);
   		 $("#pnum").html("进货数量："+dataobj.pur_num);
   		 $("#price").html("价格："+dataobj.price);
   		 $("#sum_price").html("总价："+dataobj.sum_price);
   		 $("#do_date").html("进货日期："+dataobj.date);
   	    $("#hprodid").val(dataobj.prod_id);
		 $("#hpnum").val(dataobj.pur_num);
		 $("#hprice").val(dataobj.price);
		 $("#hsumprice").val(dataobj.sum_price);
		 $("#hdate").val(dataobj.date);
		 $("#hsupid").val(dataobj.sup_id);
		 $("#horderid").val(dataobj.order_id);
   		 $("#my-confirm").modal("toggle");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("error");
        }
    });

	
}

function doandprint(){
	var pnum=document.getElementById("hpnum").value;
	var do_date=document.getElementById("hdate").value;	
	var prod_id=document.getElementById("hprodid").value;	
	var sup_id=document.getElementById("hsupid").value;	
	var price=document.getElementById("hprice").value;	
	var sum_price=document.getElementById("hsumprice").value;	
	var order_id=document.getElementById("horderid").value;	
	$.ajax({
        url: "purchaseReturnServlet?action=newpsstock",
        data: {
        	pnum:pnum,
        	do_date:do_date,
        	sum_price:sum_price,
        	sup_id:sup_id,
        	price:price,
        	prod_id:prod_id,
        	order_id:order_id
        	
        },
        datatype: "text",
        type: "POST",
        contentType: "application/x-www-form-urlencoded",
        success: function (data) {//这里的data是由请求页面返回的数据
        	$("#ps_id").html("购货单id："+data);
        	  doPrint();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("error");
        }
    });
	

	
}