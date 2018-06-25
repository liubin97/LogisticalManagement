
function selectCol(){
    var tb = document.getElementById('tbody');
    var rowNum=tb.rows.length;
    for (var i=0;i<rowNum;i++)
    {
        tb.deleteRow(i);
        rowNum=rowNum-1;
        i=i-1;
    }
    var check=$("input[name='chk']:checked");//选中的复选框
    check.each(function(){
        var row=$(this).parent("td").parent("tr");
        var productname=row.find("[name='productname']").html();//注意html()和val()
        var substation=row.find("[name='substation']").html();
        var productnum=row.find("[name='productnum']").html();
        var finishdate=row.find("[name='finishdate']").html();
        var taskid = row.find("[name='chk']").val();
        console.info(productname+" "+substation+" "+productnum+" "+finishdate+" "+taskid);
        //向表格插入数据
        var table = document.getElementById("tbody");
        var oneRow = table.insertRow();//插入一行
        var substationname= oneRow.insertCell();//单单插入一行是不管用的，需要插入单元格
        var product=oneRow.insertCell();
        var prodnum=oneRow.insertCell();
        substationname.innerHTML = substation;
        product.innerHTML=productname;
        prodnum.innerHTML=productnum;
    });

    $(function() {
        $('#my-confirm').modal({
            relatedTarget: this,
            onConfirm: function(options) {

                var msg = '出库成功';
                alert(msg);
                document.forms[1].submit();
            },
            // closeOnConfirm: false,
            onCancel: function() {
                alert('调拨失败');
                return false;
            }
        });
    });



}