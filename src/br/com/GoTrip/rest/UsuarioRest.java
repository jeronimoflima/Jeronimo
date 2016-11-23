package br.com.GoTrip.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.GoTrip.objetos.Usuario;
import br.com.GoTrip.service.UsuarioService;

@Path("usuarioRest")
// Caminho URI da classe Rest utilizada.
public class UsuarioRest extends UtilRest {

	public UsuarioRest() {
	}

	@POST
	@Path("/addUsuario")
	@Consumes("application/*")
	/*
	 * Caminho URI que identifica o tipo de mídia enviado pelo lado cliente, no
	 * caso, informações do formulário no formato de aplicação.
	 */
	public Response addUsuario(String usuarioParam){
		try {
			Usuario usuario = new ObjectMapper().readValue(
					usuarioParam, Usuario.class);

			UsuarioService service = new UsuarioService();
				service.addUsuario(usuario);
			
			return this.buildResponse("Usuário cadastrado com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse("Usuário NÃO cadastrado. "	+ e.getMessage());

		}
	}

	// Busca por nome

	@GET
	@Path("/buscarUsuarioPorNome")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscarUsuarioPorNome(@QueryParam("nome") String nome,@QueryParam("tipo") int tipo){
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();

			UsuarioService service = new UsuarioService();
			usuarios= service.buscarPorNome(nome, tipo);

			return this.buildResponse(usuarios);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@DELETE
	@Path("/deletarUsuario")
	@Consumes("application/*")
	public Response deletarUsuario(@QueryParam("id") int id,@QueryParam("tipo") int tipo){
		
		try {
			UsuarioService service = new UsuarioService();
			service.deletar(id, tipo);

			return this.buildResponse("Usuário deletado com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}

	@POST
	@Path("/buscarUsuarioPeloId")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscarUsuarioPeloId(@QueryParam("id") int id,@QueryParam("tipo") int tipo){
		try {
			Usuario usuario = new Usuario();
			UsuarioService service = new UsuarioService();
			usuario = service.buscarPeloId(id, tipo);
			
			return this.buildResponse(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());

		}
	}

	@POST
	@Path("/editarUsuario")
	@Consumes("application/*")
	public Response editarUsuario(String usuarioParam){
		try {
			Usuario usuario = new ObjectMapper().readValue(
					usuarioParam, Usuario.class);
			UsuarioService service = new UsuarioService();
			service.editar(usuario);
		
			return this.buildResponse("Usuário editado com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse("Erro ao editar Usuário. " + e.getMessage());
		}
	}

}// Finalizar a classe
