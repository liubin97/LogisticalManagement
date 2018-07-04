//订单信息pop
function getOrderPop(orderid){
    var order_id = orderid; //保存订单id
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"UnsubscribServlet?action=Info&flag=order&orderid="+orderid,
        success:function(data){

            var $tab = $("#doc-modal-1 tbody");//找到数据插入点
            //遍历product数据集
            $tab.empty();//每次的ajax后吧tbody给清空一次
            //打印订单信息

            var trhtml ="<tr><td>订单号</td>";
            trhtml += "<td>"+data.order_id+"</td></tr>";
            trhtml += "<tr><td>商品名称</td>";
            trhtml += "<td>"+data.prod_name+"</td></tr>";
            trhtml += "<tr><td>订购数量</td>";
            trhtml += "<td>"+data.amount+"</td></tr>";
            trhtml += "<tr><td>计量单位</td>";
            trhtml += "<td>"+data.unit+"</td></tr>";
            trhtml += "<tr><td>单价</td>";
            trhtml += "<td>"+data.price+"</td></tr>";
            trhtml += "<tr><td>折扣</td>";
            trhtml += "<td>"+data.discount+"</td></tr>";
            trhtml += "<tr><td>订单总金额</td>";
            trhtml += "<td>"+data.sum_monney+"</td></tr>";
            trhtml += "<tr><td>要求完成日期</td>";
            trhtml += "<td>"+data.finish_date+"</td></tr>";
            trhtml += "<tr><td>订单生成日期</td>";
            trhtml += "<td>"+data.create_date+"</td></tr>";
            trhtml += "<tr><td>订单类型</td>";
            trhtml += "<td>"+getType(data.type)+"</td></tr>";
            trhtml += "<tr><td>订单状态</td>";
            trhtml += "<td>"+getStatus(data.status)+"</td></tr>";
            trhtml += "<tr><td>送货地址</td>";
            trhtml += "<td>"+data.deliver_addr+"</td></tr>";
            trhtml += "<tr><td>收货人</td>";
            trhtml += "<td>"+data.consignee+"</td></tr>";
            trhtml += "<tr><td>收货人电话</td>";
            trhtml += "<td>"+data.consignee_tel+"</td></tr>";
            trhtml += "<tr><td>收货人邮编</td>";
            trhtml += "<td>"+data.consignee_zip_cod+"</td></tr>";
            trhtml += "<tr><td>是否要发票</td>";
            trhtml += "<td>"+(data.invoice==1?"否":"是")+"</td></tr>";

            //添加进html
            $tab.append(trhtml);
            //弹出弹窗
            $('#doc-modal-1').modal('toggle');

        },
        error:function(){
            alert("获取失败！");
        }

    });

}
//获取订单type类型
function getType(type) {
    var x;
    switch(type) {
        case 1:
            x="新订";
            break;
        case 2:
            x="退订";
            break;
        case 3:
            x="退货";
            break;
    }
    return x;
}

//获取订单状态
function getStatus(status) {
    var x;
    switch(status) {

        case 1:x="缺货";
            break;
        case 2:x="可调度";
            break;
        case 3:x="已调度";
            break;
        case 4:x="完成";
            break;
        case 5:x="失败";
            break;
    }
    return x;
}

//获取用户信息
function getUserPop(Userid){

    var user_id = Userid; //保存用户id
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"UnsubscribServlet?action=Info&flag=user&userId="+user_id,
        success:function(data){

            var $tab = $("#doc-modal-2 tbody");//找到数据插入点
            //遍历product数据集
            $tab.empty();//每次的ajax后吧tbody给清空一次
            //打印订单信息

            var trhtml ="<tr><td>客户编号</td>";
            trhtml += "<td>"+data.user_id+"</td></tr>";
            trhtml += "<tr><td>客户姓名</td>";
            trhtml += "<td>"+data.name+"</td></tr>";
            trhtml += "<tr><td>身份证编号</td>";
            trhtml += "<td>"+data.id_card_num+"</td></tr>";
            trhtml += "<tr><td>工作单位</td>";
            trhtml += "<td>"+data.organization+"</td></tr>";
            trhtml += "<tr><td>座机</td>";
            trhtml += "<td>"+data.landline_tel+"</td></tr>";
            trhtml += "<tr><td>移动电话</td>";
            trhtml += "<td>"+data.tel+"</td></tr>";
            trhtml += "<tr><td>联系地址</td>";
            trhtml += "<td>"+data.address+"</td></tr>";
            trhtml += "<tr><td>邮编</td>";
            trhtml += "<td>"+data.zip_code+"</td></tr>";
            trhtml += "<tr><td>E-mail</td>";
            trhtml += "<td>"+data.email+"</td></tr>";


            //添加进html
            $tab.append(trhtml);
            //弹出弹窗
            $('#doc-modal-2').modal('toggle');

        },
        error:function(){
            alert("获取失败！");
        }

    });


}

