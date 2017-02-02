GOTRIP.administrador = new Object();

$(document).ready(function() {
	
					GOTRIP.administrador.cadastrar = function() {
						

						valido = GOTRIP.validaAdm();
						
						if(valido){

							var newAdm = new Object();

							newAdm.id = $("#id").val();
							newAdm.nome = $("#nome").val();
							newAdm.cpf = $("#cpf").val().replace(/[^\d]+/g,'');
							newAdm.email = $("#email").val();
							newAdm.telefone = $("#telefone").val().replace(/[^\d]+/g,'');
							newAdm.senha = btoa ($("#senha").val());
							newAdm.confirmasenha = btoa ($("#confirmasenha").val());
							newAdm.tipo = 1;
							
							
							GOTRIP.usuarioRest.addUsuario({
									data : newAdm,
									success : function(msg) {
										bootbox.alert(msg);
										
										GOTRIP.administrador.buscar()
									},
									error : function(err) {
										bootbox.alert("Erro ao cadastrar um novo administrador: " + err.responseText);
									}
							  });
							}// fecha o else
						};// Fecha a function GOTRIP.administrador.cadastrar()
					

					GOTRIP.administrador.buscar = function() {
						
						var valorBusca = $("#consultarAdministrador").val();
						GOTRIP.administrador.exibirAdministrador(undefined, "");
					};// Fecha método GOTRIP.administrador.buscar()

					

					GOTRIP.administrador.exibirAdministrador = function(listaDeAdministradores, valorBusca) {
						
						var html = "";

						if (listaDeAdministradores != undefined
								&& listaDeAdministradores.length > 0
								&& listaDeAdministradores[0].id != undefined) {
							for (var i = 0; i < listaDeAdministradores.length; i++) {
								html += "<tr>" +

								"<td>"
										+ listaDeAdministradores[i].id
										+ "</td>"
										+ "<td>"
										+ listaDeAdministradores[i].nome
										+ "</td>"
										+ "<td>"
										+ listaDeAdministradores[i].cpf.replace(/^(\d{3})(\d{3})(\d{3})(\d{2})$/,'$1.$2.$3-$4')
										+ "</td>"
										+ "<td>"
										+ listaDeAdministradores[i].email
										+ "</td>"
										+ "<td>"
										+ listaDeAdministradores[i].telefone.replace(/^(\d{2})(\d{4,5})(\d{4})$/, '($1)$2-$3')
										+ "</td>"
										+ "<td class='actions'>"
										+ "<a id='novo_administrador' class='btn btn-primary' onclick='GOTRIP.administrador.editarAdministrador("
										+ listaDeAdministradores[i].id
										+ ")' > <span class='glyphicon glyphicon-pencil'</a>"
										+ "<a class='btn btn-danger' onclick='GOTRIP.administrador.deletarAdministrador("
										+ listaDeAdministradores[i].id
										+ ")' > <span class='glyphicon glyphicon-trash' aria-hidden='true'></a>" + "</td>" + "</tr>";
							}
						} else {
							if (listaDeAdministradores == undefined
									|| (listaDeAdministradores != undefined && listaDeAdministradores.length > 0)) {
								if (valorBusca == "") {
									valorBusca = null;
								}
								
								GOTRIP.usuarioRest.buscarUsuarioPorNome({
									data :{'valor1' : valorBusca, 'valor2' : "1" },
									success : function(listaDeAdministradores) {
										GOTRIP.administrador.exibirAdministrador(listaDeAdministradores);
									}, 
									error : function(err) {
										bootbox.alert("Erro ao consultar os administradores: " + err.responseText);
									}
							  });
								
							} else {
								html += "<tr><td colspan='3'>Nenhum registro encontrado</td></tr>";
							}
						}
						
						$("#contentADM").html(html);
						
					};// Fecha a declaração do método exibirContatos()

					GOTRIP.administrador.exibirAdministrador(undefined, "");

					GOTRIP.administrador.deletarAdministrador = function(id) {

						bootbox.dialog({
									message : "Deseja deletar o administrador?",
									buttons : {
										success : {
											label : "Sim",
											className : "btn-success",
											callback : function(result) {
												if (result) {
													GOTRIP.usuarioRest.deletar({
														data :{'valor1' : id, 'valor2' : "1" },
														success : function(data) {
															GOTRIP.administrador.buscar();
															
														},
														error : function(err) {
															bootbox.alert("Erro ao deletar administrador: " + err.responseText);
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

					GOTRIP.administrador.editarAdministrador = function(id) {
						
						GOTRIP.usuarioRest.buscarUsuarioPeloId({
							data :{'valor1' : id, 'valor2' : "1" },
							success : function(adm) {
								$("#principal").load('/gotrip/private/usuario/cadastrar_administrador.html', function(){

									$("#nome").val(adm.nome);
									$("#cpf").val(adm.cpf);
									$("#email").val(adm.email);
									$("#telefone").val(adm.telefone);
									//$("#senha").val(adm.senha);
									//$("#confirmasenha").val(adm.confirmasenha);
									$("#id").val(adm.id);
									
									$("#bt1").attr("onclick", "GOTRIP.administrador.exibirEdicao('"+adm.id+"')");
									$("#titulo").text("Editar Administrador");
									$("#bt1").text("Editar");
									
								});
								
								
								
							},
							
							error : function(err) {
								bootbox.alert("Erro ao editar administrador: " + err.responseText);
							}
					  });
						
						

					};

					GOTRIP.administrador.exibirEdicao = function(adm) {
						
						valido = GOTRIP.validaAdm();
						
						if(valido){
							var newAdm = new Object();
	
							newAdm.nome = $("#nome").val();
							newAdm.cpf = $("#cpf").val().replace(/[^\d]+/g,'');
							newAdm.email = $("#email").val();
							newAdm.telefone = $("#telefone").val().replace(/[^\d]+/g,'');
							newAdm.senha = btoa ($("#senha").val());
							newAdm.confirmasenha = btoa ($("#confirmasenha").val());
							newAdm.id = $("#id").val();
							newAdm.tipo = 1;
							
							GOTRIP.usuarioRest.editarUsuario({
								data : newAdm,
								success : function(msg) {
									bootbox.alert(msg);
									GOTRIP.administrador.buscar();
								},
								error : function(err) {
									bootbox.alert(err.responseText);
								}
						  });
	
						};
						}
					});

GOTRIP.administrador.limpar = function() {
	$('#cadastraradministrador').each(function() {
		$(this).val('');
	});
};

