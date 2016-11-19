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
	// Processar� as requisi��es enviadas pelo m�todo post.
	@Path("/addExcursao")
	/*
	 * Caminho URI do m�todo da classe que receber� os dados do formul�rio que
	 * os armazenar� em sua respectiva classe e os incluir� no banco de dados.
	 */
	@Consumes("application/*")
	/*
	 * Caminho URI que identifica o tipo de m�dia enviado pelo lado cliente, no
	 * caso, informa��es do formul�rio no formato de aplica��o.
	 */
	public Response addExcursao(String excursaoParam){
		try {
			Excursao excursao = new ObjectMapper().readValue(
					excursaoParam, Excursao.class);

			ExcursaoService service = new ExcursaoService();
			excursao.setUsuario(getUsuario());
			service.addExcursao(excursao);
			
			return this.buildResponse("Excurs�o cadastrada com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse("Excurs�o N�O cadastrado. "	+ e.getMessage());

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

			return this.buildResponse("Excurs�o deletada com sucesso.");
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
		
			return this.buildResponse("Excurs�o editada com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse("Erro ao editar Excurs�o. " + e.getMessage());
		}
	}

}// Finalizar a classe
