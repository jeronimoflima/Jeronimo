GOTRIP.organizador = new Object();

$(document).ready(function() {
	
					GOTRIP.organizador.cadastrar = function() {
						
						valido = GOTRIP.validaOrga();
						
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
							
							
							GOTRIP.usuarioRest.addUsuario({
									data : newOrga,
									success : function(msg) {
										bootbox.alert(msg);
										
										GOTRIP.organizador.buscar()
									},
									error : function(err) {
										bootbox.alert("Erro ao cadastrar um novo organizador: " + err.responseText);
									}
							  });
							}// fecha o else
						};// Fecha a function GOTRIP.organizador.cadastrar()
					

					GOTRIP.organizador.buscar = function() {
						
						var valorBusca = $("#consultarOrganizador").val();
						GOTRIP.organizador.exibirOrganizador(undefined, valorBusca);
					};// Fecha método GOTRIP.organizador.buscar()

					

					GOTRIP.organizador.exibirOrganizador = function(listaDeOrganizadores, valorBusca) {
						
						var html = "";

						if (listaDeOrganizadores != undefined
								&& listaDeOrganizadores.length > 0
								&& listaDeOrganizadores[0].id != undefined) {
							for (var i = 0; i < listaDeOrganizadores.length; i++) {
								html += "<tr>" +

								"<td>"
										+ listaDeOrganizadores[i].id
										+ "</td>"
										+ "<td>"
										+ listaDeOrganizadores[i].nome
										+ "</td>"
										+ "<td>"
										+ listaDeOrganizadores[i].cpf.replace(/^(\d{3})(\d{3})(\d{3})(\d{2})$/,'$1.$2.$3-$4')
										+ "</td>"
										+ "<td>"
										+ listaDeOrganizadores[i].email
										+ "</td>"
										+ "<td>"
										+ listaDeOrganizadores[i].telefone.replace(/^(\d{2})(\d{4,5})(\d{4})$/, '($1)$2-$3')
										+ "</td>"
										+ "<td class='actions'>"
										+ "<a id='novo_organizador' class='btn btn-primary' onclick='GOTRIP.organizador.editarOrganizador("
										+ listaDeOrganizadores[i].id
										+ ")' > <span class='glyphicon glyphicon-pencil'</a>"
										+ "<a class='btn btn-danger' onclick='GOTRIP.organizador.deletarOrganizador("
										+ listaDeOrganizadores[i].id
										+ ")' > <span class='glyphicon glyphicon-trash' aria-hidden='true'></a>" + "</td>" + "</tr>";
							}
						} else {
							if (listaDeOrganizadores == undefined
									|| (listaDeOrganizadores != undefined && listaDeOrganizadores.length > 0)) {
								if (valorBusca == "") {
									valorBusca = null;
								}
								
								GOTRIP.usuarioRest.buscarUsuarioPorNome({
									data :{'valor1' : valorBusca, 'valor2' : "2" },
									success : function(listaDeOrganizadores) {
										console.log(listaDeOrganizadores);
										GOTRIP.organizador.exibirOrganizador(listaDeOrganizadores);
									}, 
									error : function(err) {
										bootbox.alert("Erro ao consultar os organizadores: " + err.responseText);
									}
							  });
								
							} else {
								html += "<tr><td colspan='3'>Nenhum registro encontrado</td></tr>";
							}
						}
						
						$("#content").html(html);
						
					};// Fecha a declaração do método exibirContatos()

					GOTRIP.organizador.exibirOrganizador(undefined, "");

					GOTRIP.organizador.deletarOrganizador = function(id) {

						bootbox.dialog({
									message : "Deseja deletar o organizador?",
									buttons : {
										success : {
											label : "Sim",
											className : "btn-success",
											callback : function(result) {
												if (result) {
													GOTRIP.usuarioRest.deletar({
														data :{'valor1' : id, 'valor2' : "2" },
														success : function(data) {
															GOTRIP.organizador.buscar();
															
														},
														error : function(err) {
															bootbox.alert("Erro ao deletar organizador: " + err.responseText);
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

					GOTRIP.organizador.editarOrganizador = function(id) {
						
						GOTRIP.usuarioRest.buscarUsuarioPeloId({
							data :{'valor1' : id, 'valor2' : "2" },
							success : function(orga) {
								$("#principal").load('/gotrip/public/cadastrar_organizador.html', function(){
								$("#nome").val(orga.nome);
								$("#data").val(orga.data);
								$("#cpf").val(orga.cpf);
								$("#emailOrg").val(orga.email);
								$("#telefone").val(orga.telefone);
								$("#cep").val(orga.endereco.cep);
								$("#endereco").val(orga.endereco.nome);
								$("#numero").val(orga.endereco.numero);
								$("#complemento").val(orga.endereco.complemento);
								$("#bairro").val(orga.endereco.bairro);
								$("#cidade").val(orga.endereco.cidade.nome);
								$("#estado").val(orga.endereco.cidade.estado);
								//$("#senhaOrg").val(orga.senha);
								//$("#confirmasenhaOrg").val(orga.confirmasenha);
								$("#id").val(orga.id);
								$("#id_endereco").val(orga.endereco.id);
								$("#id_cidade").val(orga.endereco.cidade.id);
								
								$("#bt1").attr("onclick", "GOTRIP.organizador.exibirEdicao('"+orga.id+"')");
								$("#titulo").text("Editar Organizador");
								$("#bt1").text("Editar");
								$("#term").hide();
								
								});
								
							},
							
							error : function(err) {
								bootbox.alert("Erro ao editar organizador: " + err.responseText);
							}
					  });

					};

					GOTRIP.organizador.exibirEdicao = function() {
						
						valido = GOTRIP.validaOrga();
						
						if(valido){

							var newOrga = new Object();
							newOrga.endereco = new Object();
							newOrga.endereco.cidade = new Object();
	
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
							newOrga.id = $("#id").val();
							newOrga.endereco.id = $("#id_endereco").val();
							newOrga.endereco.cidade.id = $("#id_cidade").val();
							newOrga.tipo = 2;
							
							GOTRIP.usuarioRest.editarUsuario({
								data : newOrga,
								success : function(msg) {
									bootbox.alert(msg);
									GOTRIP.organizador.buscar();
								},
								error : function(err) {
									bootbox.alert(err.responseText);
								}
						  });
	
						};
						}
					
					GOTRIP.organizador.visualizarOrganizador = function(id) {
						debugger;
						GOTRIP.usuarioRest.buscarUsuarioPeloId({
							data :{'valor1' : id, 'valor2' : "2" },
							success : function(orga) {
								$("#nome").val(orga.nome);
								$("#data").val(orga.data);
								$("#cpf").val(orga.cpf);
								$("#emailOrg").val(orga.email);
								$("#telefone").val(orga.telefone);
								$("#cep").val(orga.endereco.cep);
								$("#endereco").val(orga.endereco.nome);
								$("#numero").val(orga.endereco.numero);
								$("#complemento").val(orga.endereco.complemento);
								$("#bairro").val(orga.endereco.bairro);
								$("#cidade").val(orga.endereco.cidade.nome);
								$("#estado").val(orga.endereco.cidade.estado);
								//$("#senhaOrg").val(orga.senha);
								//$("#confirmasenhaOrg").val(orga.confirmasenha);
								$("#id").val(orga.id);
								$("#id_endereco").val(orga.endereco.id);
								$("#id_cidade").val(orga.endereco.cidade.id);
								
								$("#bt1").attr("onclick", "GOTRIP.organizador.exibirEdicao('"+orga.id+"')");
								$("#titulo").text("Editar Organizador");
								$("#bt1").text("Editar");
								$("#term").hide();
								
								
								
							},
							
							error : function(err) {
								bootbox.alert("Erro ao editar organizador: " + err.responseText);
							}
					  });
						$("#principal").load('/gotrip/private/usuario/visualizar_organizador.html')
						

					};
					
					
					});

GOTRIP.organizador.limpar = function() {
	$('#cadastraradministrador').each(function() {
		$(this).val('');
	});
};

