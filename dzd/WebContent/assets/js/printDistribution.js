window.onload=function getSubstation(){
    $.ajax({
        type:"post",
        url:"cenWarehouseServlet?action=getSubstation",
        data:{},
        dataType:"text",
        contentType: "application/x-www-form-urlencoded",
        success:function(data){
            var dataJson = JSON.parse(data);
            for(var i in dataJson){
                //alert(data[i].onetitle_name);
                $("#substation").append("<option value='"+dataJson[i].substation_id+"'>"+dataJson[i].substation_name+"</option>");
            }

        },
        error:function(data){
            alert("error");
        },
    });
}

function printDis(task_id) {
    $.ajax({
        type:"post",
        url:"cenWarehouseServlet?action=searchPrintDis",
        data:"taskid="+task_id,
        dataType:"text",
        contentType: "application/x-www-form-urlencoded",
        success:function(data){
            var dataJson = JSON.parse(data);
            $("#printmodal").html("");
            $("#printmodal").append("<tr><td>任务单号:</td><td>"+dataJson.task_id+"</td></tr>");
            $("#printmodal").append("<tr><td>商品编号:</td><td>"+dataJson.product_id+"</td></tr>");
            $("#printmodal").append("<tr><td>商品名称:</td><td>"+dataJson.product_name+"</td></tr>");
            $("#printmodal").append("<tr><td>商品数量:</td><td>"+dataJson.product_num+"</td></tr>");
            $("#printmodal").append("<tr><td>商品价格:</td><td>"+dataJson.product_price+"</td></tr>");
            $("#printmodal").append("<tr><td>商品折扣:</td><td>"+dataJson.discount+"</td></tr>");
            $("#printmodal").append("<tr><td>商品总价:</td><td>"+dataJson.sum_money+"</td></tr>");
            $("#printmodal").append("<tr><td>分站编号:</td><td>"+dataJson.substation_id+"</td></tr>");
            $("#printmodal").append("<tr><td>分站名称:</td><td>"+dataJson.substation+"</td></tr>");
            $("#printmodal").append("<tr><td>出库时间:</td><td>"+dataJson.out_date+"</td></tr>");
            $("#printmodal").append("<tr><td>签收人:</td><td></td></tr>");
            $('#my-modal').modal();
        },
        error:function(data){
            alert("error");
        },

    });
}