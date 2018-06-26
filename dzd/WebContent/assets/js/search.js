

//通过ID查询购货单
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
                $("#psid").val(dataJson.ps_id);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("error");
            }
        });
    } else {
        alert("购货单号不能为空");
    }
}
//查询要分站入库的任务单
function doSearchInByTaskId() {
    if ($("#search").val() != "") {
        var searchval = $("#search").val();
        $.ajax({
            url: "subWarehouseServlet?action=searchTaskIn",
            data: "taskid=" + $("#search").val(),
            datatype: "text",
            type: "POST",
            contentType: "application/x-www-form-urlencoded",
            success: function (data) {//这里的data是由请求页面返回的数据
                var dataJson = JSON.parse(data); // 使用parse方法将data转换成json格式
                $("#productname").val(dataJson.product_name);
                $("#productnum").val(dataJson.product_num);
              //  $("#acnum").val(dataJson.product_num);
                $("#taskid").val(dataJson.task_list_id);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("error");
            }
        });
    } else {
        alert("单号不能为空");
    }
}

//查询要领货的任务单
function doSearchOutByTaskId() {
    if ($("#search").val() != "") {
        var searchval = $("#search").val();
        $.ajax({
            url: "subWarehouseServlet?action=searchTaskOut",
            data: "taskid=" + $("#search").val(),
            datatype: "text",
            type: "POST",
            contentType: "application/x-www-form-urlencoded",
            success: function (data) {//这里的data是由请求页面返回的数据
                var dataJson = JSON.parse(data); // 使用parse方法将data转换成json格式
                $("#productname").val(dataJson.product_name);
                $("#productnum").val(dataJson.product_num);
                //  $("#acnum").val(dataJson.product_num);
                $("#taskid").val(dataJson.task_list_id);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("error");
            }
        });
    } else {
        alert("单号不能为空");
    }
}

//查询要退货登记的任务单
function doSearchReturnRegisterByTaskId() {
    if ($("#search").val() != "") {
        var searchval = $("#search").val();
        $.ajax({
            url: "subWarehouseServlet?action=searchReturnRegister",
            data: "taskid=" + $("#search").val(),
            datatype: "text",
            type: "POST",
            contentType: "application/x-www-form-urlencoded",
            success: function (data) {//这里的data是由请求页面返回的数据
                var dataJson = JSON.parse(data); // 使用parse方法将data转换成json格式
                $("#productname").val(dataJson.product_name);
                $("#productnum").val(dataJson.product_num);
                $("#acnum").val(dataJson.product_num);
                $("#taskid").val(dataJson.task_list_id);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("error");
            }
        });
    } else {
        alert("单号不能为空");
    }
}

