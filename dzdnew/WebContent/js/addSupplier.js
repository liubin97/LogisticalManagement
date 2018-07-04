$("#supplierNameEntry").bind("input propertychange", function(){
	var a = $("#supplierNameEntry").val();
	if(a != null && "" != a){
	//查重输入的供应商名
    $.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=checkSupplierName",
        dataType: "text",
        data: "supplierNameEntry="+$("#supplierNameEntry").val(),
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
	
	
	//校验供应商名合法性
	$.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=validateSupplierName",
        dataType: "text",
        data: "supplierNameEntry="+$("#supplierNameEntry").val(),
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
$("#supplierAddressEntry").bind("input propertychange", function(){
	$.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=validateSupplierAddress",
        dataType: "text",
        data: "supplierAddressEntry="+$("#supplierAddressEntry").val(),
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
$("#contactNameEntry").bind("input propertychange", function(){
	$.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=validateContactName",
        dataType: "text",
        data: "contactNameEntry="+$("#contactNameEntry").val(),
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
$("#contactPhoneEntry").bind("input propertychange", function(){
	$.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=validateContactPhone",
        dataType: "text",
        data: "contactPhoneEntry="+$("#contactPhoneEntry").val(),
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
$("#bankOfRegistrationEntry").bind("input propertychange", function(){
	$.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=validateBankOfRegistration",
        dataType: "text",
        data: "bankOfRegistrationEntry="+$("#bankOfRegistrationEntry").val(),
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
$("#bankAccountEntry").bind("input propertychange", function(){
	$.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=validateBankAccount",
        dataType: "text",
        data: "bankAccountEntry="+$("#bankAccountEntry").val(),
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
$("#faxNoEntry").bind("input propertychange", function(){
	$.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=validateFaxNo",
        dataType: "text",
        data: "faxNoEntry="+$("#faxNoEntry").val(),
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
$("#zipCodeEntry").bind("input propertychange", function(){
	$.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=validateZipCode",
        dataType: "text",
        data: "zipCodeEntry="+$("#zipCodeEntry").val(),
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
$("#legalPersonEntry").bind("input propertychange", function(){
	$.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=validateLegalPerson",
        dataType: "text",
        data: "legalPersonEntry="+$("#legalPersonEntry").val(),
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
$("#remarkEntry").bind("input propertychange", function(){
	$.ajax({
		async:true,
        type: "POST",
        url: "SupplierManagementServlet?action=validateRemark",
        dataType: "text",
        data: "remarkEntry="+$("#remarkEntry").val(),
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