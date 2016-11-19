package br.com.GoTrip.jdbcinterface;

import java.util.List;

import br.com.GoTrip.exception.GoTripException;
import br.com.GoTrip.objetos.Usuario;

public interface UsuarioDAO {
	
	public Usuario inserirAdm(Usuario administrador) throws GoTripException;
	public Usuario inserirOrga(Usuario organizador) throws GoTripException;
	public List<Usuario> buscarPorNome(String nome) throws GoTripException;
	public boolean deletar(int id) throws GoTripException;
	public Usuario buscarAdmPorId(int cod) throws GoTripException;
	public Usuario buscarOrgaPorId(int cod) throws GoTripException;
	public Usuario atualizar(Usuario usuario) throws GoTripException;
}
