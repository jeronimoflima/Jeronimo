package br.com.GoTrip.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.GoTrip.objetos.Excursao;
import br.com.GoTrip.objetos.Participante;
import br.com.GoTrip.service.ExcursaoService;
import br.com.GoTrip.service.ParticipanteService;

@Path("excursaoRest")
// Caminho URI da classe Rest utilizada.
public class ExcursaoRest extends UtilRest {

	public ExcursaoRest() {
	}

	@POST
	// Processará as requisições enviadas pelo método post.
	@Path("/addExcursao")
	/*
	 * Caminho URI do método da classe que receberá os dados do formulário que
	 * os armazenará em sua respectiva classe e os incluirá no banco de dados.
	 */
	@Consumes("application/*")
	/*
	 * Caminho URI que identifica o tipo de mídia enviado pelo lado cliente, no
	 * caso, informações do formulário no formato de aplicação.
	 */
	public Response addExcursao(String excursaoParam){
		try {
			Excursao excursao = new ObjectMapper().readValue(
					excursaoParam, Excursao.class);

			ExcursaoService service = new ExcursaoService();
			excursao.setUsuario(getUsuario());
			service.addExcursao(excursao);
			
			return this.buildResponse("Excursão cadastrada com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse("Excursão NÃO cadastrado. "	+ e.getMessage());

		}
	}

	// Busca por nome

	@GET
	@Path("/buscarExcursaoPorNome/{nome}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscarExcursaoPorNome(@PathParam("nome") String nome){
		try {
			List<Excursao> excursoes = new ArrayList<Excursao>();

			ExcursaoService service = new ExcursaoService();
			int idUsuario = getUsuario().getId();
			excursoes= service.buscarExcursaoPorNome(nome, idUsuario);

			return this.buildResponse(excursoes);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@DELETE
	@Path("/deletarExcursao/{id}")
	@Consumes("application/*")
	public Response deletarExcursao(@PathParam("id") int id){
		try {
			ExcursaoService service = new ExcursaoService();
			service.deletarExcursao(id);

			return this.buildResponse("Excursão deletada com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}

	@POST
	@Path("/buscarExcursaoPeloId/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscarExcursaoPeloId(@PathParam("id") int id){
		try {
			Excursao excursao = new Excursao();
			ExcursaoService service = new ExcursaoService();
			excursao = service.buscarExcursaoPeloId(id);
			
			return this.buildResponse(excursao);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());

		}
	}
	
	@GET
	@Path("/buscarExcursaoIdParticipanteNome")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscarExcursaoIdParticipanteNome(@QueryParam("nome") String nome,@QueryParam("id") int idExcursao){
		try {
			List<Participante> participante = new ArrayList<Participante>();
			ParticipanteService service = new ParticipanteService();
			participante = service.buscarParticipantePelaExcursao(idExcursao, nome);
			
			return this.buildResponse(participante);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());

		}
	}

	@POST
	@Path("/editarExcursao")
	@Consumes("application/*")
	public Response editarExcursao(String excursaoParam){
		try {
			Excursao excursao = new ObjectMapper().readValue(
					excursaoParam, Excursao.class);
			ExcursaoService service = new ExcursaoService();
			service.editarExcursao(excursao);
		
			return this.buildResponse("Excursão editada com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse("Erro ao editar Excursão. " + e.getMessage());
		}
	}

}// Finalizar a classe
