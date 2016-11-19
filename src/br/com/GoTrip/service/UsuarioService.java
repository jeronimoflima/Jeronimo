package br.com.GoTrip.service;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import br.com.GoTrip.bd.conexao.Conexao;
import br.com.GoTrip.criptografia.ConverteMd5;
import br.com.GoTrip.exception.GoTripException;
import br.com.GoTrip.jdbc.JDBCUsuarioDAO;
import br.com.GoTrip.jdbc.JDBCCidadeDAO;
import br.com.GoTrip.jdbc.JDBCEnderecoDAO;
import br.com.GoTrip.objetos.Usuario;
import br.com.GoTrip.objetos.Cidade;
import br.com.GoTrip.objetos.Endereco;
import br.com.GoTrip.validacao.ValidaGoTrip;

public class UsuarioService {
	
	public void addUsuario(Usuario usuario) throws GoTripException{
		if(usuario.getTipo()==1){
			addAdmninistrador(usuario);
			}else{
				addOrganizador(usuario);
				
			}
	}

	private void addAdmninistrador(Usuario usuario) throws GoTripException {
			
		ValidaGoTrip.validarAdm(usuario);
		
		String senha = convertePassword(usuario.getSenha());
		usuario.setSenha(senha);
		
		Conexao conec = new Conexao();
		Connection conexao = null;
		try {

			conexao = conec.abrirConexao();
			JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
			
			jdbcUsuario.inserirAdm(usuario);

		} catch (Exception e) {
			throw new GoTripException(e);
		} finally {

			conec.fecharConexao();
		}
	}
	
	private void addOrganizador(Usuario usuario) throws GoTripException {
		
		ValidaGoTrip.validarOrga(usuario);
		
		String senha = convertePassword(usuario.getSenha());
		usuario.setSenha(senha);

		Conexao conec = new Conexao();
		Connection conexao = null;
		try {

			conexao = conec.abrirConexao();
			JDBCCidadeDAO jdbcCidade = new JDBCCidadeDAO(conexao);
			JDBCEnderecoDAO jdbcEndereco = new JDBCEnderecoDAO(conexao);
			JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);

			Cidade cidade = jdbcCidade.buscaCidadeEstado(usuario
					.getEndereco().getCidade());
			if (cidade.getId() == 0) {
				cidade = jdbcCidade.inserir(usuario.getEndereco()
						.getCidade());
			}
			usuario.getEndereco().setCidade(cidade);

			Endereco endereco = jdbcEndereco
					.inserir(usuario.getEndereco());
			usuario.setEndereco(endereco);

			jdbcUsuario.inserirOrga(usuario);

		} catch (Exception e) {
			if (conexao != null) {
				try {
					conexao.rollback();
				} catch (SQLException e1) {
					throw new GoTripException(e1);
				}
			}
			throw new GoTripException(e);

		} finally {

			conec.fecharConexao();
		}
	}

	// Busca por nome
	public List<Usuario> buscarPorNome(String nome, int tipo) throws GoTripException {
			

		List<Usuario> usuarios = new ArrayList<Usuario>();
		Conexao conec = new Conexao();
		Connection conexao = null;
		try {

			conexao = conec.abrirConexao();
			JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
			usuarios = jdbcUsuario.buscarPorNome(nome, tipo);

		} catch (Exception e) {
				throw new GoTripException(e);

		} finally {
			conec.fecharConexao();
		}

		return usuarios;
	}
	
	public void deletar(int id, int tipo) throws GoTripException{
		if(tipo==1){
			deletarAdministrador(id);
			}else{
				deletarOrganizador(id);
				
			}
	}
	

	private void deletarAdministrador(int id) throws GoTripException {

		Conexao conec = new Conexao();
		Connection conexao = null;
		try{
			
			conexao = conec.abrirConexao();
			JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);

			jdbcUsuario.deletar(id);
			
		} catch (Exception e) {
			throw new GoTripException(e);
		} finally {

			conec.fecharConexao();
		}
	}
	
	private void deletarOrganizador(int id) throws GoTripException {

		Conexao conec = new Conexao();
		Connection conexao = null;
		try{
			
			conexao = conec.abrirConexao();
			JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
			JDBCEnderecoDAO jdbcEndereco = new JDBCEnderecoDAO(conexao);
			int idEnd = jdbcUsuario.buscarOrgaPorId(id).getEndereco().getId();

			jdbcUsuario.deletar(id);
			jdbcEndereco.deletar(idEnd);			
			
			
			
		} catch (Exception e) {
			if (conexao != null) {
				try {
					conexao.rollback();
				} catch (SQLException e1) {
					throw new GoTripException(e1);
				}
			}
			throw new GoTripException(e);

		} finally {

			conec.fecharConexao();
		}
	}
		

	public Usuario buscarPeloId(int id, int tipo) throws GoTripException {
		
		Usuario usuario = null;
		try{
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
			if(tipo==1){
				usuario = jdbcUsuario.buscarAdmPorId(id);
			}else{
				usuario = jdbcUsuario.buscarOrgaPorId(id);
			}
			
		}catch(Exception e){
			throw new GoTripException(e);
		}
		return usuario;
	}
	
	public void editar(Usuario usuario) throws GoTripException{
		if(usuario.getTipo()==1){
			editarAdministrador(usuario);
			}else{
				editarOrganizador(usuario);
				
			}
	}

	private void editarAdministrador(Usuario usuario) throws GoTripException {
		
		ValidaGoTrip.validarAdm(usuario);
		
		String senha = convertePassword(usuario.getSenha());
		usuario.setSenha(senha);
		
		Conexao conec = new Conexao();
		Connection conexao = null;
		try{
		
			conexao = conec.abrirConexao();
			JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
	
			jdbcUsuario.atualizar(usuario);
			}catch (Exception e){
				throw new GoTripException(e);
			}finally{
		
				conec.fecharConexao();
			}
		}
	
private void editarOrganizador(Usuario usuario) throws GoTripException {
		
		ValidaGoTrip.validarOrga(usuario);
		
		String senha = convertePassword(usuario.getSenha());
		usuario.setSenha(senha);

		Conexao conec = new Conexao();
		Connection conexao = null;
		try {

			conexao = conec.abrirConexao();
			JDBCCidadeDAO jdbcCidade = new JDBCCidadeDAO(conexao);
			JDBCEnderecoDAO jdbcEndereco = new JDBCEnderecoDAO(conexao);
			JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);

			Cidade cidade = jdbcCidade.buscaCidadeEstado(usuario.getEndereco().getCidade());
			if (cidade.getId() == 0) {
				cidade = jdbcCidade.inserir(usuario.getEndereco().getCidade());
			}
			usuario.getEndereco().setCidade(cidade);

			Endereco endereco = jdbcEndereco.atualizar(usuario.getEndereco());
			usuario.setEndereco(endereco);

			jdbcUsuario.atualizar(usuario);

		} catch (Exception e) {
			if (conexao != null) {
				try {
					conexao.rollback();
				} catch (SQLException e1) {
					throw new GoTripException(e1);
				}
			}
			throw new GoTripException(e);

		} finally {

			conec.fecharConexao();
		}
	}

private String convertePassword(String senha){

	System.out.println("Senha base64: " + senha);
	
	
	byte[] decoded = Base64.getDecoder().decode(senha);
	senha= new String(decoded, StandardCharsets.UTF_8);
	System.out.println("Senha base64 Decod: "+ senha);
	
	String senhaMD5 = "";
	try {
		senhaMD5 = ConverteMd5.converteMd5(senha);
		System.out.println("Senha MD5 : " + senhaMD5);
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	return senhaMD5;
}

}// Finalizar a classe

