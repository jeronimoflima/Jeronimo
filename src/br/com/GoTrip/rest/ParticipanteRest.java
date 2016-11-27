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

import br.com.GoTrip.objetos.Participante;
import br.com.GoTrip.service.ParticipanteService;

@Path("participanteRest")
// Caminho URI da classe Rest utilizada.
public class ParticipanteRest extends UtilRest {

	public ParticipanteRest() {
	}

	@POST
	// Processará as requisições enviadas pelo método post.
	@Path("/addParticipante")
	/*
	 * Caminho URI do método da classe que receberá os dados do formulário que
	 * os armazenará em sua respectiva classe e os incluirá no banco de dados.
	 */
	@Consumes("application/*")
	/*
	 * Caminho URI que identifica o tipo de mídia enviado pelo lado cliente, no
	 * caso, informações do formulário no formato de aplicação.
	 */
	public Response addParticipante(String participanteParam){
		try {
			Participante participante = new ObjectMapper().readValue(
					participanteParam, Participante.class);

			ParticipanteService service = new ParticipanteService();
			service.addParticipante(participante);
			
			return this.buildResponse("Participante cadastrado com sucesso.");

		} catch (Exception e) {
			return this.buildErrorResponse("Participante NÃO cadastrado. "	+ e.getMessage());

		}
	}

	// Busca por nome

	@GET
	@Path("/buscarParticipantePorNome/{nome}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscarParticipantePorNome(@PathParam("nome") String nome){
		try {
			List<Participante> participantes = new ArrayList<Participante>();

			ParticipanteService service = new ParticipanteService();
			participantes= service.buscarParticipantePorNome(nome);

			return this.buildResponse(participantes);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	
	@GET
	@Path("/buscarParticipantePorEmail/{email}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscarParticipantePorEmail(@PathParam("email") String email){
		try {
			Participante participante = new Participante();

			ParticipanteService service = new ParticipanteService();
			participante = service.buscarParticipantePorEmail(email);

			return this.buildResponse(participante);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	

	@DELETE
	@Path("/deletarParticipante")
	@Consumes("application/*")
	public Response deletarParticipante(@QueryParam("id") int id,@QueryParam("idExcur") int idExcursao){
		try {
			ParticipanteService service = new ParticipanteService();
			service.deletarParticipante(id, idExcursao);

			return this.buildResponse("Participante deletado com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}

	@POST
	@Path("/buscarParticipantePeloId/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscarParticipantePeloId(@PathParam("id") int id){
		try {
			Participante participante = new Participante();
			ParticipanteService service = new ParticipanteService();
			participante = service.buscarParticipantePeloId(id);
			
			return this.buildResponse(participante);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());

		}
	}

	@POST
	@Path("/editarParticipante")
	@Consumes("application/*")
	public Response editarParticipante(String participanteParam){
		try {
			Participante participante = new ObjectMapper().readValue(
					participanteParam, Participante.class);
			ParticipanteService service = new ParticipanteService();
			service.editarParticipante(participante);
		
			return this.buildResponse("Participante editado com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse("Erro ao editar Participante. " + e.getMessage());
		}
	}

}// Finalizar a classe
