GOTRIP.excursao = new Object();
GOTRIP.excursaoRest = new Object();

$(document).ready(function() {
	GOTRIP.excursao.buscar2 = function() {
						
						
						var valorBusca = $("#consultarExcursao").val();
						GOTRIP.excursao.exibirExcursoes2(undefined, valorBusca);
					};// Fecha método GOTRIP.excursao.buscar()

					

					GOTRIP.excursao.exibirExcursoes2 = function(
							listaDeExcursoes, valorBusca) {
						
						var html = "";

						if (listaDeExcursoes != undefined
								&& listaDeExcursoes.length > 0
								&& listaDeExcursoes[0].id != undefined) {
							for (var i = 0; i < listaDeExcursoes.length; i++) {
								var d = GOTRIP.dataFormatada(listaDeExcursoes[i].data);
								html += "<tr data-target='#resultado' aria-expanded='false' aria-controls='resultado' onclick='GOTRIP.excursao.exibirResultado2("
										+ listaDeExcursoes[i].id+")'>" +

								"<td>"
										
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
										+ "<td>"
										+ listaDeExcursoes[i].valor+",00"
										+ "</td>"
										+ "</td>" + "</tr>";
										
							}
						} else {
							if (listaDeExcursoes == undefined
									|| (listaDeExcursoes != undefined && listaDeExcursoes.length > 0)) {
								
								if (valorBusca == "") {
									valorBusca = null;
								}
								
								GOTRIP.excursaoRest.buscarExcursaoPorNome2({
									data : valorBusca,
									success : function(listaDeExcursoes) {
										GOTRIP.excursao.exibirExcursoes2(listaDeExcursoes);
									},
									error : function(err) {
										bootbox.alert("Erro ao consultar os participantes: " + err.responseText);
									}
							  });
								
							} else {
								html += "<tr><td colspan='3'>Nenhum registro encontrado</td></tr>";
							}
						}
						$("#contentExcur").html(html);
						
					};// Fecha a declaração do método exibirContatos()
						
					GOTRIP.excursao.exibirExcursoes2(undefined, "");
					
					GOTRIP.excursao.exibirResultado2 = function(id) {
						//$("#resultado").collapse('show');
						
						//$("#principal").load('/gotrip/public/resultado_busca_excursao.html');
											
											GOTRIP.excursaoRest.buscarExcursaoPeloId2({
												data : id,
												success : function(excur) {
													$("#nome").html(excur.nome);
													
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
});

					
					
					GOTRIP.excursaoRest.buscarExcursaoPorNome2 = function(config){
						GOTRIP.ajax.get({
								url : "/gotrip/rest/excursaoRest/buscarExcursaoPorNome/" + config.data,
								success : config.success,
								error : config.error
							});
						}
					GOTRIP.excursaoRest.buscarExcursaoPeloId2 = function(config){
						GOTRIP.ajax.post({
								url : "/gotrip/rest/excursaoRest/buscarExcursaoPeloId/" + config.data,
								success : config.success,
								error : config.error
							});
						}
					
