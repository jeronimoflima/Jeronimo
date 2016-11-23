GOTRIP.excursao = new Object();

$('input[type=file]').change(function(){
	upload(this);
});
	
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
							newExcur.imagem1 = $("#foto1").val();
							newExcur.imagem2 = $("#foto2").val();
							
							
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
								//var d = GOTRIP.dataFormatada(listaDeExcursoes[i].data);
								html += "<tr>" +

								"<td>"
										+ listaDeExcursoes[i].id
										+ "</td>"
										+ "<td>"
										+ "<img src ='/gotrip/rest/download?file="+listaDeExcursoes[i].imagem1+"' height ='35' width ='40'>"
										+ "</td>"
										+ "<td>"
										+ listaDeExcursoes[i].nome
										+ "</td>"
										+ "<td>"
										+ listaDeExcursoes[i].categoria
										+ "</td>"
										+ "<td>"
										+ GOTRIP.dataFormatada(listaDeExcursoes[i].data)
										+ "</td>"
										+ "<td>"
										+ listaDeExcursoes[i].contParticipante
										+ "</td>"
										+ "<td class='actions'>"
										+ "<a id='nova_excursao' class='btn btn-primary' onclick='GOTRIP.excursao.editarExcursao("
										+ listaDeExcursoes[i].id
										+ ")' ><span class='glyphicon glyphicon-pencil' aria-hidden='true'></a>"
										+ "<a class='btn btn-danger' onclick='GOTRIP.excursao.deletarExcursao("
										+ listaDeExcursoes[i].id
										+ ")' ><span class='glyphicon glyphicon-trash' aria-hidden='true'></span></a>" 
										+ "<a id='lista' class='btn btn-warning' onclick='GOTRIP.excursao.passarId("
										+ listaDeExcursoes[i].id
										+ ")' ><span class='glyphicon glyphicon-user' aria-hidden='true'></span></a>" 
										+ "<a id='resultado' class='btn btn-default' onclick='GOTRIP.excursao.exibirResultado("
										+ listaDeExcursoes[i].id
										+ ")' ><span class='glyphicon glyphicon-eye-open' aria-hidden='true'></a>" + "</td>" + "</tr>";
										
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
								$("#foto1").val(excur.imagem1);
								$("#foto2").val(excur.imagem2);
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
						newExcur.imagem1 = $("#foto1").val();
						newExcur.imagem2 = $("#foto2").val();
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
					
					
				GOTRIP.excursao.passarId = function(id) {
					
				
						GOTRIP.idExcursao = id;
						GOTRIP.excursaoRest.buscarExcursaoIdParticipanteNome({
							data :{'valor1' : "", 'valor2' : id },
							success : function(listaDeParticipantes) {
							$("body").load('/gotrip/private/participante/lista_participantes.html',function(){
									$("#id_excursao").val(id);
									GOTRIP.participante.exibirParticipantes(listaDeParticipantes, "", id);
									GOTRIP.excursao.exibirDadosParti(id);
								});
							},
							error : function(err) {
								bootbox.alert("Erro ao consultar os participantes: " + err.responseText);
							}
					  });

					};
					
					
					GOTRIP.excursao.exibirDadosParti = function(id) {
						
						
											GOTRIP.excursaoRest.buscarExcursaoPeloId({
												data : id,
												success : function(excur) {
																								
													var o = excur.usuario.nome;
													$("#nome_usuario").html("Organizador: " + o);
													var d = GOTRIP.dataFormatada(excur.data);
													$("#data").html("Data de Partida: " + d);
													var email = excur.usuario.email;
													$("#email_usuario").html("Contato: " + email);
													$("#fone_usuario").html(excur.usuario.telefone);
													
												},
												
												error : function(err) {
													bootbox.alert("Erro ao exibir excursão: " + err.responseText);
												}
										  });
											

										}; 					
					
					
GOTRIP.excursao.exibirResultado = function(id) {
	
	//$("#principal").load('/gotrip/public/resultado_busca_excursao.html');
						
						GOTRIP.excursaoRest.buscarExcursaoPeloId({
							data : id,
							success : function(excur) {
								$("#nome").html(excur.nome);
								debugger;
								
								var c = excur.categoria;
								$("#categoria").html("Categoria: " + c);
								$("#cidade").html(excur.cidade.nome);
								$("#estado").html(excur.cidade.estado);
								
								var l = excur.local;
								$("#local").html("Local de Saída: " + l);
								
								var d = GOTRIP.dataFormatada(excur.data);
								
								$("#data").html("Data de Partida: " + d);
								$("#totalparti").html(excur.totalparti);
								$("#minimoparti").html(excur.minimoparti);
								
								var v = excur.valor;
								$("#valor").html("Valor por Pessoa: R$" + v);
								$("#status").html("Status :" +excur.status);
								$("#descricao").html(excur.descricao);
								
								
								$("#img1").html("");
								$("<img height ='270' width ='350' >").attr("src","/gotrip/rest/download?file="+excur.imagem1).appendTo("#img1")
								
								
								$("#img2").html("");
								$("<img height ='270' width ='350' >").attr("src","/gotrip/rest/download?file="+excur.imagem2).appendTo("#img2")
								
								
								$("#id_excursao").html(excur.id);
								$("#id_cidade").html(excur.cidade.id);
								$("#id_usuario").html(excur.usuario.id);
								
								var o = excur.usuario.nome;
								$("#nome_usuario").html("Organizador: " + o);
								var email = excur.usuario.email;
								$("#email_usuario").html("Contato: " + email);
								$("#fone_usuario").html(excur.usuario.telefone);
								

							},
							
							error : function(err) {
								bootbox.alert("Erro ao exibir excursão: " + err.responseText);
							}
					  });

						$("#principal").load('/gotrip/public/resultado_busca_excursao.html');
						
												

					}; 



GOTRIP.excursao.limpar = function() {
	$('#cadastrarExcursao').each(function() {
		$(this).val('');
	});
};

