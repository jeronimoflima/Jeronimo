package br.com.GoTrip.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;

import br.com.GoTrip.bd.conexao.Conexao;
import br.com.GoTrip.exception.GoTripException;
import br.com.GoTrip.jdbc.JDBCCidadeDAO;
import br.com.GoTrip.jdbc.JDBCEnderecoDAO;
import br.com.GoTrip.jdbc.JDBCParticipanteDAO;
import br.com.GoTrip.objetos.Cidade;
import br.com.GoTrip.objetos.Endereco;
import br.com.GoTrip.objetos.Participante;
import br.com.GoTrip.validacao.ValidaGoTrip;

public class ParticipanteService {

	public void addParticipante(Participante participante) throws GoTripException {
			
		ValidaGoTrip.validarParticipante(participante);

		Conexao conec = new Conexao();
		Connection conexao = null;
		try {

			conexao = conec.abrirConexao();
			JDBCCidadeDAO jdbcCidade = new JDBCCidadeDAO(conexao);
			JDBCEnderecoDAO jdbcEndereco = new JDBCEnderecoDAO(conexao);
			JDBCParticipanteDAO jdbcParticipante = new JDBCParticipanteDAO(
					conexao);

			Cidade cidade = jdbcCidade.buscaCidadeEstado(participante
					.getEndereco().getCidade());
			if (cidade.getId() == 0) {
				cidade = jdbcCidade.inserir(participante.getEndereco()
						.getCidade());
			}
			participante.getEndereco().setCidade(cidade);

			Endereco endereco = jdbcEndereco
					.inserir(participante.getEndereco());
			participante.setEndereco(endereco);

			
			participante = jdbcParticipante.inserir(participante);
			jdbcParticipante.inseriPartiNaExcursao(participante);
			

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
	public List<Participante> buscarParticipantePorNome(@PathParam("nome") String nome) throws GoTripException {
			

		List<Participante> participantes = new ArrayList<Participante>();
		Conexao conec = new Conexao();
		Connection conexao = null;
		try {

			conexao = conec.abrirConexao();
			JDBCParticipanteDAO jdbcParticipante = new JDBCParticipanteDAO(
					conexao);
			participantes = jdbcParticipante.buscarPorNome(nome);

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

		return participantes;

	}
	
	public List<Participante> buscarParticipantePelaExcursao(int idExcursao, String nome) throws GoTripException {
		
		List<Participante> participante = new ArrayList<Participante>();
		Conexao conec = new Conexao();
		Connection conexao = null;
		try {

			conexao = conec.abrirConexao();
			JDBCParticipanteDAO jdbcParticipante = new JDBCParticipanteDAO(conexao);
			participante = jdbcParticipante.buscarParticipantePelaExcursao(idExcursao, nome);

		} catch (Exception e) {
				throw new GoTripException(e);
				
		} finally {
			conec.fecharConexao();
		}

		return participante;

	}

	public void deletarParticipante(int id, int idExcursao) throws GoTripException {

		Conexao conec = new Conexao();
		Connection conexao = null;
		try{
			
			conexao = conec.abrirConexao();
			conexao.setAutoCommit(false);
			JDBCParticipanteDAO jdbcParticipante = new JDBCParticipanteDAO(conexao);
			//JDBCEnderecoDAO jdbcEndereco = new JDBCEnderecoDAO(conexao);
			//int idEnd = jdbcParticipante.buscarPorId(id).getEndereco().getId();
	
			//jdbcEndereco.deletar(idEnd);
			jdbcParticipante.deletarParticipante(id, idExcursao);
			conexao.commit();
			
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
			

	public Participante buscarParticipantePeloId(int id) throws GoTripException {
		
		Participante participante = null;
		try{
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCParticipanteDAO jdbcParticipante = new JDBCParticipanteDAO(conexao);
			participante = jdbcParticipante.buscarPorId(id);
		}catch(Exception e){
			throw new GoTripException(e);
		}
		return participante;
	}

	public void editarParticipante(Participante participante) throws GoTripException {
		ValidaGoTrip.validarParticipante(participante);

		Conexao conec = new Conexao();
		Connection conexao = null;
		try{
		
			conexao = conec.abrirConexao();
			JDBCCidadeDAO jdbcCidade = new JDBCCidadeDAO(conexao);
			JDBCEnderecoDAO jdbcEndereco = new JDBCEnderecoDAO(conexao);
			JDBCParticipanteDAO jdbcParticipante = new JDBCParticipanteDAO(conexao);
	
			participante.getEndereco().getCidade().setId(0);
			Cidade cidade = jdbcCidade.buscaCidadeEstado(participante.getEndereco().getCidade());
			if (cidade.getId() == 0) {
				cidade = jdbcCidade.inserir(participante.getEndereco().getCidade());
			}
			participante.getEndereco().setCidade(cidade);
	
			Endereco endereco = jdbcEndereco.atualizar(participante.getEndereco());
			participante.setEndereco(endereco);
	
			jdbcParticipante.atualizar(participante);
		}catch (Exception e){
			if(conexao != null){
				try{
					conexao.rollback();
				}catch(SQLException e1){
					throw new GoTripException(e1);
				}
			}
			throw new GoTripException(e);
		}finally{
	
			conec.fecharConexao();
		}
	}
	



}// Finalizar a classe

