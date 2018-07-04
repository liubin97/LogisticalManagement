$(document).ready(function(){$("#selectType").change(function(){
	var select_type = $(this).val();
	//alert(select_type);
	if(select_type=='productCode'){
			$("#selectText").keyup(function(){ 
			$(this).val($(this).val().replace(/\D|^0/g,'')); 
			}).bind("paste",function(){
			$(this).val($(this).val().replace(/\D|^0/g,'')); 
			}).css("ime-mode", "disabled"); 	
	}else{
		$("#selectText").unbind();
			
	}
})
});