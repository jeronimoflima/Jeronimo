$(document).ready(function() {
	
	GOTRIP.validaEmail = function() {
		

			var email = document.getElementById("email").value;

			if (email.indexOf("@") == -1 || // valida se existe
			// o @
			email.indexOf(".") == -1 || // valida se existe o .
			email.indexOf("@") == 0 || // valida se tem texto
			// antes do @
			email.lastIndexOf(".") + 1 == email.length || // valida
			// se
			// tem
			// texto
			// depois do ponto
			(email.indexOf("@") + 1 == email.indexOf("."))) { // valida
				// se
				// tem
				// texto entre o @ e
				// o .{
				bootbox.alert("email incorreto");
				document.getElementById("email").focus();
				return false;
			} else {
				return true;
			}
	};
	

	GOTRIP.validaPart = function() {
		
						// Validações formulário
						if (document.getElementById("nome").value == ""
								|| document.getElementById("data").value == ""
								|| document.getElementById("cpf").value == ""
								|| document.getElementById("email").value == ""
								|| document.getElementById("telefone").value == ""
								|| document.getElementById("cep").value == ""
								|| document.getElementById("endereco").value == ""
								|| document.getElementById("numero").value == ""
								|| document.getElementById("bairro").value == ""
								|| document.getElementById("cidade").value == ""
								|| document.getElementById("estado").value == ""
								|| document.getElementById("rg").value == "") {
							
							bootbox.alert("Todos os campos são obrigatórios de preenchimento!");
							
							return false;
							

						} else {

							var email = document.getElementById("email").value;

							if (email.indexOf("@") == -1 || // valida se existe
							// o @
							email.indexOf(".") == -1 || // valida se existe o .
							email.indexOf("@") == 0 || // valida se tem texto
							// antes do @
							email.lastIndexOf(".") + 1 == email.length || // valida
							// se
							// tem
							// texto
							// depois do ponto
							(email.indexOf("@") + 1 == email.indexOf("."))) { // valida
								// se
								// tem
								// texto entre o @ e
								// o .{
								bootbox.alert("email incorreto");
								document.getElementById("email").focus();
								return false;
							} else {
								return true;
							}
						}
					};

					GOTRIP.validaAdm = function() {
						
						// Validações formulário
						if (document.getElementById("nome").value == ""
								|| document.getElementById("cpf").value == ""
								|| document.getElementById("email").value == ""
								|| document.getElementById("telefone").value == ""
								|| document.getElementById("senha").value == ""
								|| document.getElementById("confirmasenha").value == "") {

							bootbox.alert("Todos os campos são obrigatórios de preenchimento!");

							return false;

						} else {

							var email = document.getElementById("email").value;

							if (email.indexOf("@") == -1 || // valida se existe
							// o @
							email.indexOf(".") == -1 || // valida se existe o .
							email.indexOf("@") == 0 || // valida se tem texto
							// antes do @
							email.lastIndexOf(".") + 1 == email.length || // valida
							// se
							// tem
							// texto
							// depois do ponto
							(email.indexOf("@") + 1 == email.indexOf("."))) { // valida
								// se
								// tem
								// texto entre o @ e
								// o .{
								bootbox.alert("email incorreto");
								document.getElementById("email").focus();
								return false;

							} else {

								senha1 = document.getElementById("senha").value;
								senha2 = document.getElementById("confirmasenha").value;
								if (senha1 != senha2) {

									bootbox.alert("As senhas digitadas não conferem!");
									document.getElementById("senha").focus();
									return false;
								} else {
									return true;
								}

							}
						}
					};
					
						GOTRIP.validaOrga = function() {
						
						// Validações formulário
						if (document.getElementById("nome").value == ""
								|| document.getElementById("data").value == ""
								|| document.getElementById("cpf").value == ""
								|| document.getElementById("email").value == ""
								|| document.getElementById("telefone").value == ""
								|| document.getElementById("cep").value == ""
								|| document.getElementById("endereco").value == ""
								|| document.getElementById("numero").value == ""
								|| document.getElementById("bairro").value == ""
								|| document.getElementById("cidade").value == ""
								|| document.getElementById("estado").value == ""
								|| document.getElementById("senha").value == ""
								|| document.getElementById("confirmasenha").value == "") {

							bootbox.alert("Todos os campos são obrigatórios de preenchimento!");

							return false;

						} else {

							var email = document.getElementById("email").value;

							if (email.indexOf("@") == -1 || // valida se existe
							// o @
							email.indexOf(".") == -1 || // valida se existe o .
							email.indexOf("@") == 0 || // valida se tem texto
							// antes do @
							email.lastIndexOf(".") + 1 == email.length || // valida
							// se
							// tem
							// texto
							// depois do ponto
							(email.indexOf("@") + 1 == email.indexOf("."))) { // valida
								// se
								// tem
								// texto entre o @ e
								// o .{
								bootbox.alert("email incorreto");
								document.getElementById("email").focus();
								return false;

							} else {
								
								senha1 = document.getElementById("senha").value;
								senha2 = document.getElementById("confirmasenha").value;
								if (senha1 != senha2) {

									bootbox.alert("As senhas digitadas não conferem!");
									document.getElementById("senha").focus();
									return false;
								} else if 
									($("#id").val() == ""  && $("input[name='termo']:checked").length<=0) {
							        bootbox.alert("Concorda com o Termo de Uso?");
							        return false;
							    }else{
							    	return true;
							    }
									

							}
						}
					};
					
					GOTRIP.validaExcur = function() {
						

						// Validações formulário
						if (document.getElementById("nome").value == ""
								|| document.getElementById("categoria").value == ""
								|| document.getElementById("cidade").value == ""
								|| document.getElementById("estado").value == ""
								|| document.getElementById("local").value == ""
								|| document.getElementById("data").value == ""
								|| document.getElementById("totalparti").value == ""
								|| document.getElementById("minimoparti").value == ""
								|| document.getElementById("valor").value == ""
								|| document.getElementById("status").value == ""
								
							|| document.getElementById("descricao").value == "") {

							bootbox.alert("Todos os campos são obrigatórios de preenchimento!");

							return false;
						} else if 
							( document.getElementById("imagem1").value == ""
								|| document.getElementById("imagem2").value == ""){
									bootbox.alert("Selecione as imagens!");
							 return false;
						}else{
							
							return true;
						}
					};

				});
