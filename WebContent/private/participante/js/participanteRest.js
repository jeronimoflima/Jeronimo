GOTRIP.participanteRest = {};


	
	GOTRIP.participanteRest.addParticipante = function(config){
		GOTRIP.ajax.post({
				url : "/gotrip/rest/participanteRest/addParticipante/",
				data:config.data,
				success : config.success,
				error : config.error
			});
		}
	
	GOTRIP.participanteRest.buscarParticipantePorNome = function(config){
		GOTRIP.ajax.get({
				url : "/gotrip/rest/participanteRest/buscarParticipantePorNome/" + config.data,
				success : config.success,
				error : config.error
			});
		}
	
	GOTRIP.participanteRest.deletar = function(config){
		GOTRIP.ajax.del({
				url : "/gotrip/rest/participanteRest/deletarParticipante/" + config.data,
				success : config.success,
				error : config.error
			});
		}
	
	GOTRIP.participanteRest.buscarParticipantePeloId = function(config){
		GOTRIP.ajax.post({
				url : "/gotrip/rest/participanteRest/buscarParticipantePeloId/" + config.data,
				success : config.success,
				error : config.error
			});
		}
	
	GOTRIP.participanteRest.editarParticipante = function(config){
		GOTRIP.ajax.post({
				url : "/gotrip/rest/participanteRest/editarParticipante/",
				data:config.data,
				success : config.success,
				error : config.error
			});
		}

