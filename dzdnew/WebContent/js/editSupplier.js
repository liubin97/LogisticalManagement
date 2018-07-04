$("#sNameModify").bind("input propertychange", function(){
	var a = $("#sNameModify").val();
	if(a != null && "" != a){
	//查重输入的供应商名
    $.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=checkSupplierNameModify",
        dataType: "text",
        data: "sNameModify="+$("#sNameModify").val(),
        success: function(result){
            if(result=="false"){
			$("#span2").html("=ok=");
            }else if(result=="true"){
            $("#span2").html("重复");
            }
        },
        error: function(){
            $("#span2").html("Error XMLHttpRequest");
        }
    });
    
    $("#span2").click(function(){
    	
    });
	
	
	//校验供应商名合法性
	$.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=validateSupplierNameModify",
        dataType: "text",
        data: "sNameModify="+$("#sNameModify").val(),
        success: function(result){
            if(result=="false"){
			$("#span1").html("=ok=");
            }else if(result=="true"){
            $("#span1").html("=invalid=");
            }
        },
        error: function(){
            $("#span1").html("Error XMLHttpRequest");
        }
    });
	
	}
}
);

//校验供应商地址合法性
$("#sAddressModify").bind("input propertychange", function(){
	$.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=validateSupplierAddressModify",
        dataType: "text",
        data: "sAddressModify="+$("#sAddressModify").val(),
        success: function(result){
            if(result=="false"){
			$("#span3").html("=ok=");
            }else if(result=="true"){
            $("#span3").html("=invalid=");
            }
        },
        error: function(){
            $("#span3").html("Error XMLHttpRequest");
        }
    });
});

//校验联系人合法性
$("#cNameModify").bind("input propertychange", function(){
	$.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=validateContactNameModify",
        dataType: "text",
        data: "cNameModify="+$("#cNameModify").val(),
        success: function(result){
            if(result=="false"){
			$("#span4").html("=ok=");
            }else if(result=="true"){
            $("#span4").html("=invalid=");
            }
        },
        error: function(){
            $("#span4").html("Error XMLHttpRequest");
        }
    });
});


//校验联系人电话合法性
$("#cPhoneModify").bind("input propertychange", function(){
	$.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=validateContactPhoneModify",
        dataType: "text",
        data: "cPhoneModify="+$("#cPhoneModify").val(),
        success: function(result){
            if(result=="false"){
			$("#span5").html("=ok=");
            }else if(result=="true"){
            $("#span5").html("=invalid=");
            }
        },
        error: function(){
            $("#span5").html("Error XMLHttpRequest");
        }
    });
});

//校验开户行合法性
$("#bORModify").bind("input propertychange", function(){
	$.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=validateBankOfRegistrationModify",
        dataType: "text",
        data: "bORModify="+$("#bORModify").val(),
        success: function(result){
            if(result=="false"){
			$("#span6").html("=ok=");
            }else if(result=="true"){
            $("#span6").html("=invalid=");
            }
        },
        error: function(){
            $("#span6").html("Error XMLHttpRequest");
        }
    });
});

//校验银行账号合法性
$("#bAModify").bind("input propertychange", function(){
	$.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=validateBankAccountModify",
        dataType: "text",
        data: "bAModify="+$("#bAModify").val(),
        success: function(result){
            if(result=="false"){
			$("#span7").html("=ok=");
            }else if(result=="true"){
            $("#span7").html("=invalid=");
            }
        },
        error: function(){
            $("#span7").html("Error XMLHttpRequest");
        }
    });
});

//校验fax合法性
$("#fNModify").bind("input propertychange", function(){
	$.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=validateFaxNoModify",
        dataType: "text",
        data: "fNModify="+$("#fNModify").val(),
        success: function(result){
            if(result=="false"){
			$("#span8").html("=ok=");
            }else if(result=="true"){
            $("#span8").html("=invalid=");
            }
        },
        error: function(){
            $("#span8").html("Error XMLHttpRequest");
        }
    });
});

//校验邮编合法性
$("#zCModify").bind("input propertychange", function(){
	$.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=validateZipCodeModify",
        dataType: "text",
        data: "zCModify="+$("#zCModify").val(),
        success: function(result){
            if(result=="false"){
			$("#span9").html("=ok=");
            }else if(result=="true"){
            $("#span9").html("=invalid=");
            }
        },
        error: function(){
            $("#span9").html("Error XMLHttpRequest");
        }
    });
});

//校验法人合法性
$("#lPModify").bind("input propertychange", function(){
	$.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=validateLegalPersonModify",
        dataType: "text",
        data: "lPModify="+$("#lPModify").val(),
        success: function(result){
            if(result=="false"){
			$("#span10").html("=ok=");
            }else if(result=="true"){
            $("#span10").html("=invalid=");
            }
        },
        error: function(){
            $("#span10").html("Error XMLHttpRequest");
        }
    });
});


//校验备注合法性
$("#remarkModify").bind("input propertychange", function(){
	$.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=validateRemarkModify",
        dataType: "text",
        data: "remarkModify="+$("#remarkModify").val(),
        success: function(result){
            if(result=="false"){
			$("#span11").html("=ok=");
            }else if(result=="true"){
            $("#span11").html("=invalid=");
            }
        },
        error: function(){
            $("#span11").html("Error XMLHttpRequest");
        }
    });
});