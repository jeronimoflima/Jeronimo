GOTRIP.excursaoRest = {};


	GOTRIP.excursaoRest.addExcursao = function(config){
		GOTRIP.ajax.post({
				url : "/gotrip/rest/excursaoRest/addExcursao/",
				data:config.data,
				success : config.success,
				error : config.error
			});
		}
	
	GOTRIP.excursaoRest.buscarExcursaoPorNome = function(config){
		GOTRIP.ajax.get({
				url : "/gotrip/rest/excursaoRest/buscarExcursaoPorNome/" + config.data,
				success : config.success,
				error : config.error
			});
		}
	
	GOTRIP.excursaoRest.deletar = function(config){
		GOTRIP.ajax.del({
				url : "/gotrip/rest/excursaoRest/deletarExcursao/" + config.data,
				success : config.success,
				error : config.error
			});
		}
	
	GOTRIP.excursaoRest.buscarExcursaoPeloId = function(config){
		GOTRIP.ajax.post({
				url : "/gotrip/rest/excursaoRest/buscarExcursaoPeloId/" + config.data,
				success : config.success,
				error : config.error
			});
		}
	
	GOTRIP.excursaoRest.buscarExcursaoIdParticipanteNome = function(config){
		GOTRIP.ajax.get({
				url : "/gotrip/rest/excursaoRest/buscarExcursaoIdParticipanteNome?nome=" + config.data.valor1 + "&id=" + config.data.valor2,
				success : config.success,
				error : config.error
			});
		}
	
		
	GOTRIP.excursaoRest.editarExcursao = function(config){
		GOTRIP.ajax.post({
				url : "/gotrip/rest/excursaoRest/editarExcursao/",
				data:config.data,
				success : config.success,
				error : config.error
			});
		}

