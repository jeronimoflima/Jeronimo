function upload(el){
	
	var aux	="";
			var formData = new FormData();
			formData.append("file",el.files[0],el.value);
			
			if(el.id == "imagem1"){
				aux = "#foto1";
			}else{
				aux = "#foto2";
			}
			
			$.ajax({
				url: "../../rest/upload",
				type: "POST",
				data: formData,
				contentType: false,
				processData: false,
				success: function(data){
					$(aux).val(data);
					
					
				}
			});
		}