GOTRIP.organizador = new Object();
GOTRIP.usuarioRest = {};

$(document).ready(function() {
	
					GOTRIP.organizador.cadastrar2 = function() {
											
						valido = GOTRIP.validaOrga2();
						
						if(valido){

							var newOrga = new Object();
							newOrga.endereco = new Object();
							newOrga.endereco.cidade = new Object();

							newOrga.id = $("#id").val();
							newOrga.nome = $("#nome").val();
							newOrga.data = $("#data").val();
							newOrga.cpf = $("#cpf").val().replace(/[^\d]+/g,'');
							newOrga.email = $("#emailOrg").val();
							newOrga.telefone = $("#telefone").val().replace(/[^\d]+/g,'');
							newOrga.endereco.cep = $("#cep").val().replace(/[^\d]+/g,'');
							newOrga.endereco.nome = $("#endereco").val();
							newOrga.endereco.numero = $("#numero").val();
							newOrga.endereco.complemento = $("#complemento").val();
							newOrga.endereco.bairro = $("#bairro").val();
							newOrga.endereco.cidade.nome = $("#cidade").val();
							newOrga.endereco.cidade.estado = $("#estado").val();
							newOrga.senha = btoa ($("#senhaOrg").val());
							newOrga.confirmasenha = btoa ($("#confirmasenhaOrg").val());
							newOrga.tipo = 2;
							
							
							GOTRIP.usuarioRest.addUsuario2({
									data : newOrga,
									success : function(msg) {
										bootbox.alert(msg);
										
										
									},
									error : function(err) {
										bootbox.alert("Erro ao cadastrar um novo organizador: " + err.responseText);
									}
							  });
							}// fecha o else
						};// Fecha a function GOTRIP.organizador.cadastrar()
					

					
					
					GOTRIP.usuarioRest.addUsuario2 = function(config){
						GOTRIP.ajax.post({
								url : "/gotrip/rest/usuarioRest/addUsuario/",
								data:config.data,
								success : config.success,
								error : config.error
							});
						}
					
					});

GOTRIP.validaOrga2 = function() {
	
	debugger;
	// Validações formulário
	if (document.getElementById("nome").value == ""
			|| document.getElementById("data").value == ""
					|| document.getElementById("cpf").value == ""
					|| document.getElementById("emailOrg").value == ""
					|| document.getElementById("telefone").value == ""
				    || document.getElementById("cep").value == ""
					|| document.getElementById("endereco").value == ""
					|| document.getElementById("numero").value == ""
					|| document.getElementById("bairro").value == ""
					|| document.getElementById("cidade").value == ""
					|| document.getElementById("estado").value == ""
					|| document.getElementById("senhaOrg").value == ""
					|| document.getElementById("confirmasenhaOrg").value == "") {

		bootbox.alert("Todos os campos são obrigatórios de preenchimento!");

		return false;

	} else {

		var email = document.getElementById("emailOrg").value;

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
			document.getElementById("emailOrg").focus();
			return false;

		} else {
			
			senha1 = document.getElementById("senhaOrg").value;
			senha2 = document.getElementById("confirmasenhaOrg").value;
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