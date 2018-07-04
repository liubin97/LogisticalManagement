

//隐藏产品列表
$(function() {

    $(document).ready(function () {
        $("#pro_list").hide();//隐藏二级联动
        $('#products').hide();//隐藏产品列表
        $('#products_btn').hide();//隐藏产品列表提交button

    });
});

//显示
function showData() {
    $("#pro_list").show();
    $('#products').show();
    $('#products_btn').show();
}

//获取一级级目录
$("#one").cxSelect({
    selects: ['onetitle'],
    jsonName: 'n',
    jsonValue: 'v',
    required: true,
    emptyStyle: "none",
    url:'NewOrderServlet?action=order&flag=one'
});

//获取二级目录
$('#onetitle').change(function () {
    var title =  $("#onetitle option:selected").val();
    $("#two").cxSelect({
        selects: ['twotitle'],
        jsonName: 'n',
        jsonValue: 'v',
        required: true,
        emptyStyle: "none",
        url:'NewOrderServlet?action=order&flag=two&title='+title

    });

});

//获取产品目录
$('#twotitle').change(function () {
    var titile = $("#twotitle option:selected").val();
    $.ajax({
        type:"POST",
        dataType:"json",
        url:'NewOrderServlet?action=order&flag=product&title='+titile,
        success:function(data){

            //找到tbody
            var $tab = $('#products tbody');
            //遍历product数据集
            $tab.empty();//每次的ajax后吧tbody给清空一次
            for(var i = 0;i<data.length;i++){

                var trhtml = "<tr>";//组装字符串
                trhtml += "<td><input type=\"radio\" name=\"chose\" value=\""+data[i].id+"\" id=\"chose"+(i+1)+"\"></td>";
                trhtml += "<td>"+data[i].name+"</td>";
                trhtml += "<td>"+data[i].unit+"</td>";
                trhtml += "<td>"+data[i].price+"<input type=\"hidden\" value=\""+data[i].price+"\" id=\"price"+(i+1)+"\"></td>";
                trhtml += "<td><input type=\"hidden\" value=\""+data[i].discount+"\" id=\"rate"+(i+1)+"\">"+data[i].discount+"</td>";
                trhtml += "<td>"+data[i].notes+"</td>";
                trhtml += "<td><input type=\"number\" id=\"num"+(i+1)+"\" onchange=\"Count("+(i+1)+")\"></td>";
                trhtml += "<td id= \"total"+(i+1)+"\">"+0+ "</td>";
                trhtml += " </tr>";
                $tab.append(trhtml);
            }

        }
    });


});


/*计算总价格的价格*/
function Count(id) {

    var quantity = $("#num"+id).val();
    var price = $("#price"+id).val();
    var rate = $("#rate"+id).val();
    var smallTotal = quantity*price*rate;
    $("#total"+id).html(smallTotal);
    console.log("价格"+smallTotal);

}
