function comfirmSettle(){


}
$(document).ready(function(){
	
	//计算实收额，退款额，应缴额
	var inAmount=0,outAmount=0;
	$("input[name='amountIn']").each(function(){
		inAmount += Number($(this).attr("value"));
	})
	$("input[name='amountOut']").each(function(){
		outAmount += Number($(this).attr("value"));
	})

	$("#outAll").attr("placeholder",outAmount);
	$("#inAll").attr("placeholder",inAmount);
	$("#allAmount").attr("placeholder",inAmount-outAmount);
	

});