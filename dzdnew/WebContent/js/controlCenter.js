/**
 * 
 */
function distributeByhand() {

}

window.onload = function() {
	distribute();
	$.ajax({
		url : 'distributeservlet?action=getSubstation',
		type : 'post',
		data : {},
		dataType : 'json',
		contentType : "application/x-www-form-urlencoded",

		success : function(data) {
			jsondata = eval(data);
		
			for (var i in jsondata) {
				$("#substationSelectAll").append(
						'<option value="' + data[i].id + '">'
								+ data[i].name + '</option>');
				$("#substationSelect").append(
						'<option value="' + data[i].id + '">'
								+ data[i].name + '</option>');
			}
		}
	});
}

function getJsonLength(json) {
    var jsonLength = 0;
    for (var i in json) {
        jsonLength++;
    }
    return jsonLength;
}
function distributeOneByhand(orderId) {
	var form = document.getElementById("distributeByhand");
	form.orderIdOneByhand.value = orderId;
	$("#tempOne").modal("toggle");
}

function distributeByhandSubmit() {
	var form = document.getElementById("distributeByhand");
	var substationId = document.getElementById("substationSelect");
	form.substationIdOneByhand.value = substationId.value;
	// alert(substationId.value);
	form.submit();
}

function distributeOneAuto(orderId) {
	// var form = document.getElementById("distributeAuto");
	// form.orderIdOneAuto.value=orderId;
	// alert(orderId);
	// form.submit();

	$.ajax({
		url : 'distributeservlet?action=autoOne',
		type : 'post',
		data : {
			orderIdOneAuto : orderId
		},
		dataType : 'text',
		contentType : "application/x-www-form-urlencoded",

		success : function(data) {
			var temp = "系统经过计算...自动将该订单分配至 " + data + " 号分站";
			$("#finalSubstationId").html(temp);
		}
	});

	$("#manageresult").modal("toggle");

}

function distribute() {
	$.ajax({
		url : 'distributeservlet?action=distribute',
		type : 'post',
		data : '',
		dataType : 'text',
		contentType : "application/x-www-form-urlencoded",
	});
}

function distributeAllByhand() {
	var substationId = document.getElementById("substationSelectAll");
	var substation = document.getElementById("substationIdAllByhand");
	substation.value = substationId.value;
	var chks = document.getElementsByName("chk");
	var flag = false;
	for (var i = 0; i < chks.length; i++) {
		if (chks[i].checked == true) {
			flag = true;
			break;
		}
	}
	if (flag) {
		// 提交请求
		var form = document.getElementById("ditributeForm");
		var action = document.getElementById("action");
		action.value = "distributeAllByhand";
		form.submit();
		$("#tempAll").modal("toggle");
	} else {
		// 提示
		alert("请至少选择一个用户进行调度");
	}

}

function distributeAllAuto() {
	var chks = document.getElementsByName("chk");
	var flag = false;
	for (var i = 0; i < chks.length; i++) {
		if (chks[i].checked == true) {
			flag = true;
			break;
		}
	}
	if (flag) {
		// 提交请求
		var form = document.getElementById("ditributeForm");
		var action = document.getElementById("action");
		action.value = "distributeAllAuto";

		form.submit();
		$("#manageresultAll").modal("toggle");

	} else {
		// 提示
		alert("请至少选择一个用户进行调度");
	}


}
function jump1(pageNum) {
	window.location = "searchorderservlet?pageNum=" + pageNum;
}
