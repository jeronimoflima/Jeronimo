function carregar(caminho,container){
	
	var id = $("#id_excursao").val();
	if(+id == 0)
		$(container||"#principal").load(caminho);
	else{
		$(container||"#principal").load(caminho, function(){
			$("#id_excursao").val(id);
		});
	}
}
function carregar1(caminho,container){
	window.location.href=caminho+"#"+$("#id_excursao").val();
	
}
	

$(document).ready(function(){
	
	var parameters = getParameters();
			if (parameters.login == "invalid") {
				bootbox.alert("Usuário e/ou senha inválidos");
				console.log("AQUI");
			}

			function getParameters() {
				var parameters = {};
				if (window.location.search != "") {
					//debugger;
					var arr = window.location.search.substring(1).split("&");
					arr.forEach(function(item) {
						var arr2 = item.split("=");
						parameters[arr2[0]] = arr2[1];
					});
				}
				return parameters;
			}
			
			$("#telefone").mask('(99)9999-9999');
			 $("#cpf").mask('999.999.999-99');
			 $("#cep").mask('99999-999');
			 $("#telefoneEdit").mask('(99)9999-9999');
			 $("#cpfEdit").mask('999.999.999-99');
			 $("#cepEdit").mask('99999-999');
			 $("#fone_usuario").mask('(99)9999-9999');
	})
	
	
	
