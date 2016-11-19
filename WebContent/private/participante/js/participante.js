GOTRIP.participante = new Object();
	

	$(document).ready(function() {	
	GOTRIP.participante.exibirParticipantes = function(
			listaDeParticipantes, valorBusca, idExcursao ) {
		
		var html = "";

		if (listaDeParticipantes != undefined
				&& listaDeParticipantes.length > 0
				&& listaDeParticipantes[0].id != undefined) {
			for (var i = 0; i < listaDeParticipantes.length; i++) {
				html += "<tr>" +

				"<td>"
						+ listaDeParticipantes[i].id
						+ "</td>"
						+ "<td>"
						+ listaDeParticipantes[i].nome
						+ "</td>"
						+ "<td>"
						+ listaDeParticipantes[i].rg
						+ "</td>"
						+ "<td>"
						+ listaDeParticipantes[i].cpf
						+ "</td>"
						+ "<td>"
						+ listaDeParticipantes[i].telefone
						+ "</td>"
						+ "<td class='actions'>"
						+ "<a id='novo_participante' class='btn btn-primary' onclick='GOTRIP.participante.editarParticipante("
						+ listaDeParticipantes[i].id
						+ ")' ><span class='glyphicon glyphicon-pencil'</a>"
						+ "<a class='btn btn-danger' onclick='GOTRIP.participante.deletarParticipante("
						+ listaDeParticipantes[i].id
						+ ")' ><span class='glyphicon glyphicon-trash' aria-hidden='true'></a>" + "</td>" + "</tr>";
			}
		} else {
			if (listaDeParticipantes == undefined
					|| (listaDeParticipantes != undefined && listaDeParticipantes.length > 0)) {
				if (valorBusca == "") {
					valorBusca = null;
				}
				
				
				GOTRIP.excursaoRest.buscarExcursaoIdParticipanteNome({
					data :{'valor1' : valorBusca, 'valor2' : idExcursao },
					success : function(listaDeParticipantes) {
						GOTRIP.participante.exibirParticipantes(listaDeParticipantes,valorBusca,idExcursao);
					},
					error : function(err) {
						bootbox.alert("Erro ao consultar os participantes: " + err.responseText);
					}
			  });
				
			} else {
				html += "<tr><td colspan='3'>Nenhum registro encontrado</td></tr>";
			}
		}
		$("#contentList").html(html);
		
	};// Fecha a declaração do método exibirParticipantes()
					
					GOTRIP.participante.cadastrar = function() {					

					var valido = GOTRIP.validaPart();
						
						if(valido){
							
							var newPart = new Object();	
							newPart.endereco = new Object();
							newPart.endereco.cidade = new Object();
							newPart.excursao = new Object();
							/*var string = document.URL;
							if(string.split('#')[1] > 0){
								newPart.excursao.id = string.split('#')[1];
							}; */
							newPart.id = $("#id").val();
							newPart.nome = $("#nome").val();
							newPart.data = $("#data").val();
							newPart.cpf = $("#cpf").val().replace(/[^\d]+/g,'');
							newPart.email = $("#email").val();
							newPart.telefone = $("#telefone").val().replace(/[^\d]+/g,'');
							newPart.endereco.cep = $("#cep").val().replace(/[^\d]+/g,'');
							newPart.endereco.nome = $("#endereco").val();
							newPart.endereco.numero = $("#numero").val();
							newPart.endereco.complemento = $("#complemento").val();
							newPart.endereco.bairro = $("#bairro").val();
							newPart.endereco.cidade.nome = $("#cidade").val();
							newPart.endereco.cidade.estado = $("#estado").val();
							newPart.sexo = $("#sexo").val();
							newPart.rg = $("#rg").val();
							newPart.status = $("#status").val();
							newPart.excursao.id = $("#id_excursao").val();
							
							
							GOTRIP.participanteRest.addParticipante({
									data : newPart,
									success : function(msg) {
										bootbox.alert(msg);
										
										GOTRIP.participante.buscar()
									},
									error : function(err) {
										bootbox.alert("Erro ao cadastrar um novo participante: " + err.responseText);
									}
							  });
							}// fecha o else
						};// Fecha a function GOTRIP.participante.cadastrar()
					

					GOTRIP.participante.buscar = function() {
						
						var valorBusca = $("#consultarParticipante").val(),
							idExcursao = $("#id_excursao").val();
						GOTRIP.participante.exibirParticipantes(undefined, valorBusca, idExcursao);
						
					};// Fecha método GOTRIP.participante.buscar()				

					GOTRIP.participante.deletarParticipante = function(id) {

						bootbox.dialog({
									message : "Deseja deletar o Participante?",
									buttons : {
										success : {
											label : "Sim",
											className : "btn-success",
											callback : function(result) {
												if (result) {
													
													GOTRIP.participanteRest.deletar({
														data : id,
														success : function(data) {
															GOTRIP.participante.buscar();
														},
														error : function(err) {
															bootbox.alert("Erro ao deletar participante: " + err.responseText);
														}
												  });
													
												}
											}
										},
										danger : {
											label : "Não",
											className : "btn-danger",
											callback : function() {
											}
										},
									}
								});
					};

					GOTRIP.participante.editarParticipante = function(id) {
						
						
						GOTRIP.participanteRest.buscarParticipantePeloId({
							data : id,
							success : function(parti) {
								$("#nome").val(parti.nome);
								$("#data").val(parti.data);
								$("#cpf").val(parti.cpf);
								$("#email").val(parti.email);
								$("#telefone").val(parti.telefone);
								$("#cep").val(parti.endereco.cep);
								$("#endereco").val(parti.endereco.nome);
								$("#numero").val(parti.endereco.numero);
								$("#complemento").val(parti.endereco.complemento);
								$("#bairro").val(parti.endereco.bairro);
								$("#cidade").val(parti.endereco.cidade.nome);
								$("#estado").val(parti.endereco.cidade.estado);
								$("#sexo").val(parti.sexo);
								$("#rg").val(parti.rg);
								$("#status").val(parti.status);
								$("#id").val(parti.id);
								$("#id_endereco").val(parti.endereco.id);
								$("#id_cidade").val(parti.endereco.cidade.id);
								//$("id_excursao").val(parti.excursao.id);
								
								$("#bt1").attr("onclick", "GOTRIP.participante.exibirEdicao('"+parti.id+"')");
								$("#titulo").text("Editar Participante");
								$("#bt1").text("Editar");
								

							},
							
							error : function(err) {
								bootbox.alert("Erro ao editar participante: " + err.responseText);
							}
					  });

						$("#principal").load('/gotrip/private/participante/cadastrar_participantes.html');
						
												

					};

					GOTRIP.participante.exibirEdicao = function(parti) {
						

						var newPart = new Object();
						newPart.endereco = new Object();
						newPart.endereco.cidade = new Object();
						newPart.excursao = new Object();

						newPart.nome = $("#nome").val();
						newPart.data = $("#data").val();
						newPart.cpf = $("#cpf").val().replace(/[^\d]+/g,'');
						newPart.email = $("#email").val();
						newPart.telefone = $("#telefone").val().replace(/[^\d]+/g,'');
						newPart.endereco.cep = $("#cep").val().replace(/[^\d]+/g,'');
						newPart.endereco.nome = $("#endereco").val();
						newPart.endereco.numero = $("#numero").val();
						newPart.endereco.complemento = $("#complemento").val();
						newPart.endereco.bairro = $("#bairro").val();
						newPart.endereco.cidade.nome = $("#cidade").val();
						newPart.endereco.cidade.estado = $("#estado").val();
						newPart.sexo = $("#sexo").val();
						newPart.rg = $("#rg").val();
						newPart.status = $("#status").val();
						newPart.id = $("#id").val();
						newPart.endereco.id = $("#id_endereco").val();
						newPart.endereco.cidade.id = $("#id_cidade").val();
						newPart.excursao.id = $("#id_excursao").val();
						
						GOTRIP.participanteRest.editarParticipante({
							data : newPart,
							success : function(msg) {
								bootbox.alert(msg);
								GOTRIP.participante.buscar();
							},
							error : function(err) {
								bootbox.alert(err.responseText);
							}
					  });

					};
					GOTRIP.participante.exibirParticipantes(null, "", window.idExcursao);
					

					//GOTRIP.participante.exibirParticipantes(undefined, "",$("#id_excursao").val());
					
				});


GOTRIP.participante.limpar = function() {
	$('#cadastrarParticipante').each(function() {
		$(this).val('');
	});
};
//# sourceURL = participante.js
