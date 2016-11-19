$(document).ready(function(){
	
	$("#login").on("click",function(){
		
		//var salt ='dm';
		
		$("#senha").val(btoa($("#senha").val()));
		
		var base64 =$("#senha").val();
		
		console.log (base64);
		
		$("#login-nav").submit();

	});
});