package br.com.GoTrip.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.com.GoTrip.objetos.Relatorio;
import br.com.GoTrip.service.RelatorioService;

@Path("relatorioRest")

public class RelatorioRest extends UtilRest {

	public RelatorioRest() {
	}

	

	@GET
	@Path("/buscar/{excursao}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public javax.ws.rs.core.Response buscar(@PathParam("excursao") String excursao){
		try {
			List<Relatorio> lista = new ArrayList<Relatorio>();

			RelatorioService service = new RelatorioService();
			lista= service.buscar(excursao);

			return this.buildResponse(lista);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}


}// Finalizar a classe
