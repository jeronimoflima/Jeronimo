
GOTRIP.relatorio = new Object();
GOTRIP.relatorioRest = new Object();

$(document).ready(function() {
	

var tipoRelatorio = undefined;	

		GOTRIP.relatorioRest.buscar({
			data: tipoRelatorio,
			success: function (dados){
						console.log(dados)
							GOTRIP.relatorio.excursao(dados);
						
			},
									
			error: function (err){
				alert(err.responseText);
			}
	});


GOTRIP.relatorio.excursao = function(dados){
	
	
	var ctx = document.getElementById("myChart");
	
	var data = {
		    labels: [],
		    datasets: [
		        {
		            label: "Excursões Cadastradas X Confirmadas",
		            backgroundColor: [

										"#36A2EB",
										"#FF6600"
		                          ],
		                          borderColor: [
										"#36A2EB",
										"#FF6600"
		                          ],
		                          borderWidth: 1,
		                          data: [65, 59, 80, 81, 56, 55, 40],
		            data: []
		        }
		    ]
		};
	
	for (var i = 0; i < dados.length; i++) {
		data.labels.push(dados[i].status);
		data.datasets[0].data.push(dados[i].total);
	}
			
	var myBarChart = new Chart(ctx, {
	    type: 'doughnut',
	    data: data,
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    min: 0
	                 
	                }
	            }]
	        }
	    }
	});
}

});

GOTRIP.relatorioRest.buscar = function(config){
	GOTRIP.ajax.get({
			url : "/gotrip/rest/relatorioRest/buscar/" + config.data,
			success : config.success,
			error : config.error
		});
	}



	
