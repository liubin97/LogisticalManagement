$(document).ready(function(){
	var allAmount = 0;
	$(".amount").each(function(){
		allAmount += Number($(this).attr("value"));
	})
	$("#all-mount").attr("placeholder",allAmount);
})