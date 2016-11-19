GOTRIP.usuarioRest = {};

$(document).ready(function() {	
	
	GOTRIP.usuarioRest.addUsuario = function(config){
		GOTRIP.ajax.post({
				url : "/gotrip/rest/usuarioRest/addUsuario/",
				data:config.data,
				success : config.success,
				error : config.error
			});
		}
	
	GOTRIP.usuarioRest.buscarUsuarioPorNome = function(config){
		GOTRIP.ajax.get({
				url : "/gotrip/rest/usuarioRest/buscarUsuarioPorNome?nome=" + config.data.valor1 +  "&tipo=" + config.data.valor2,
				success : config.success,
				error : config.error
			});
		}
	
	GOTRIP.usuarioRest.deletar = function(config){
		GOTRIP.ajax.del({
				url : "/gotrip/rest/usuarioRest/deletarUsuario?id=" + config.data.valor1 +  "&tipo=" + config.data.valor2,
				success : config.success,
				error : config.error
			});
		}
	
	GOTRIP.usuarioRest.buscarUsuarioPeloId = function(config){
		GOTRIP.ajax.post({
				url : "/gotrip/rest/usuarioRest/buscarUsuarioPeloId?id=" + config.data.valor1 +  "&tipo=" + config.data.valor2,
				success : config.success,
				error : config.error
			});
		}
	
	GOTRIP.usuarioRest.editarUsuario = function(config){
		GOTRIP.ajax.post({
				url : "/gotrip/rest/usuarioRest/editarUsuario/",
				data:config.data,
				success : config.success,
				error : config.error
			});
		}

	});
