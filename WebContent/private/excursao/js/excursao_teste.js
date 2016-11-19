GOTRIP.excursao = new Object();

$(document).ready(function() {	
	
					GOTRIP.excursao.cadastrar = function() {

															
						valido = GOTRIP.validaExcur();
						
						if(valido){

							var newExcur = new Object();
							newExcur.cidade = new Object();

							newExcur.id = $("#id_excurcao").val();
							newExcur.nome = $("#nome").val();
							newExcur.categoria = $("#categoria").val();
							newExcur.cidade.nome = $("#cidade").val();
							newExcur.cidade.estado = $("#estado").val();
							newExcur.local = $("#local").val();
							newExcur.data = $("#data").val();
							newExcur.totalparti = $("#totalparti").val();
							newExcur.minimoparti = $("#minimoparti").val();
							newExcur.valor = $("#valor").val();
							newExcur.status = $("#status").val();
							newExcur.descricao = $("#descricao").val();
							newExcur.imagem1 = $("#imagem1").val();
							newExcur.imagem2 = $("#imagem2").val();
							
							
							GOTRIP.excursaoRest.addExcursao({
									data : newExcur,
									success : function(msg) {
										bootbox.alert(msg);
										
										GOTRIP.excursao.buscar()
									},
									error : function(err) {
										bootbox.alert("Erro ao cadastrar uma nova excursão: " + err.responseText);
									}
							  });
							}// fecha o else
						};// Fecha a function GOTRIP.excursao.cadastrar()
					

					GOTRIP.excursao.buscar = function() {
						var valorBusca = $("#consultarExcursao").val();
						GOTRIP.excursao.exibirExcursoes(undefined, valorBusca);
					};// Fecha método GOTRIP.excursao.buscar()

					

					GOTRIP.excursao.exibirExcursoes = function(
							listaDeExcursoes, valorBusca) {
						
						var html = "";

						if (listaDeExcursoes != undefined
								&& listaDeExcursoes.length > 0
								&& listaDeExcursoes[0].id != undefined) {
							for (var i = 0; i < listaDeExcursoes.length; i++) {
								html += "<tr>" +

								"<td>"
										+ listaDeExcursoes[i].id
										+ "</td>"
										+ "<td>"
										+ listaDeExcursoes[i].nome
										+ "</td>"
										+ "<td>"
										+ listaDeExcursoes[i].data
										+ "</td>"
										+ "<td>"
										+ listaDeExcursoes[i].quantidade
										+ "</td>"
										+ "<td>"
										+ listaDeExcursoes[i].status
										+ "</td>"
										+ "<td class='actions'>"
										+ "<a id='nova_excursao' class='btn btn-default btn-xs' onclick='GOTRIP.excursao.editarExcursao("
										+ listaDeExcursoes[i].id
										+ ")' >Editar</a>"
										+ "<a class='btn btn-default btn-xs' onclick='GOTRIP.excursao.deletarExcursao("
										+ listaDeExcursoes[i].id
										+ ")' >Deletar</a>" 
										+ "<a id='novo_participante' class='btn btn-default btn-xs' onclick='GOTRIP.participante.exibirParticipantes("
										+ listaDeExcursoes[i].id
										+ ")' >List <span class='glyphicon glyphicon-user' aria-hidden='true'></span></a>"
										+ "</td>" + "</tr>";
							}
						} else {
							if (listaDeExcursoes == undefined
									|| (listaDeExcursoes != undefined && listaDeExcursoes.length > 0)) {
								if (valorBusca == "") {
									valorBusca = null;
								}
								
								GOTRIP.excursaoRest.buscarExcursaoPorNome({
									data : valorBusca,
									success : function(listaDeExcursoes) {
										GOTRIP.excursao.exibirExcursoes(listaDeExcursoes);
									},
									error : function(err) {
										bootbox.alert("Erro ao consultar os participantes: " + err.responseText);
									}
							  });
								
							} else {
								html += "<tr><td colspan='3'>Nenhum registro encontrado</td></tr>";
							}
						}
						$("#content").html(html);
						
					};// Fecha a declaração do método exibirContatos()

					GOTRIP.excursao.exibirExcursoes(undefined, "");

					GOTRIP.excursao.deletarExcursao = function(id) {
						

						bootbox.dialog({
									message : "Deseja deletar a Excursão?",
									buttons : {
										success : {
											label : "Sim",
											className : "btn-success",
											callback : function(result) {
												if (result) {
													
													GOTRIP.excursaoRest.deletar({
														data : id,
														success : function(data) {
															GOTRIP.excursao.buscar();
														},
														error : function(err) {
															bootbox.alert("Erro ao deletar excursão: " + err.responseText);
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

					GOTRIP.excursao.editarExcursao = function(id) {
						
						GOTRIP.excursaoRest.buscarExcursaoPeloId({
							data : id,
							success : function(excur) {
								$("#nome").val(excur.nome);
								$("#categoria").val(excur.categoria);
								$("#cidade").val(excur.cidade.nome);
								$("#estado").val(excur.cidade.estado);
								$("#local").val(excur.local);
								$("#data").val(excur.data);
								$("#totalparti").val(excur.totalparti);
								$("#minimoparti").val(excur.minimoparti);
								$("#valor").val(excur.valor);
								$("#status").val(excur.status);
								$("#descricao").val(excur.descricao);
								$("#imagem1").val(excur.imagem1);
								$("#imagem2").val(excur.imagem2);
								$("#id_excursao").val(excur.id);
								$("#id_cidade").val(excur.cidade.id);
								
								$("#bt1").attr("onclick", "GOTRIP.excursao.exibirEdicao('"+excur.id+"')");
								$("#titulo").text("Editar Excursão");
								$("#bt1").text("Editar");
								

							},
							
							error : function(err) {
								bootbox.alert("Erro ao editar excursão: " + err.responseText);
							}
					  });

						$("#principal").load('/gotrip/private/excursao/cadastrar_excursao.html');
						
												

					};

					GOTRIP.excursao.exibirEdicao = function(parti) {
						
						valido = GOTRIP.validaExcur();
						
						if(valido){

						var newExcur = new Object();
						newExcur.cidade = new Object();
						
						newExcur.id = $("#id_excursao").val();
						newExcur.nome = $("#nome").val();
						newExcur.categoria = $("#categoria").val();
						newExcur.cidade.nome = $("#cidade").val();
						newExcur.cidade.estado = $("#estado").val();
						newExcur.local = $("#local").val();
						newExcur.data = $("#data").val();
						newExcur.totalparti = $("#totalparti").val();
						newExcur.minimoparti = $("#minimoparti").val();
						newExcur.valor = $("#valor").val();
						newExcur.status = $("#status").val();
						newExcur.descricao = $("#descricao").val();
						newExcur.imagem1 = $("#imagem1").val();
						newExcur.imagem2 = $("#imagem2").val();
						newExcur.cidade.id = $("#id_cidade").val();
						
						GOTRIP.excursaoRest.editarExcursao({
							data : newExcur,
							success : function(msg) {
								bootbox.alert(msg);
								GOTRIP.excursao.buscar();
							},
							error : function(err) {
								bootbox.alert(err.responseText);
							}
					  });

					};
					}
				});

GOTRIP.excursao.limpar = function() {
	$('#cadastrarExcursao').each(function() {
		$(this).val('');
	});
};

