package br.com.GoTrip.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;

import br.com.GoTrip.bd.conexao.Conexao;
import br.com.GoTrip.exception.GoTripException;
import br.com.GoTrip.jdbc.JDBCCidadeDAO;
import br.com.GoTrip.jdbc.JDBCExcursaoDAO;
import br.com.GoTrip.objetos.Cidade;
import br.com.GoTrip.objetos.Excursao;
import br.com.GoTrip.util.FileUtil;
import br.com.GoTrip.validacao.ValidaGoTrip;

public class ExcursaoService {

	public void addExcursao(Excursao excursao) throws GoTripException {
			
		ValidaGoTrip.validarExcur(excursao);

		Conexao conec = new Conexao();
		FileUtil util = new FileUtil();
		Connection conexao = null;
		try {

			conexao = conec.abrirConexao();
			conexao.setAutoCommit(false);
			JDBCCidadeDAO jdbcCidade = new JDBCCidadeDAO(conexao);
			JDBCExcursaoDAO jdbcExcursao = new JDBCExcursaoDAO(conexao);

			Cidade cidade = jdbcCidade.buscaCidadeEstado(excursao.getCidade());
			if (cidade.getId() == 0) {
				cidade = jdbcCidade.inserir(excursao.getCidade());
			}
			excursao.setCidade(cidade);
			
			util.moverImagem(excursao.getImagem1());
			util.moverImagem(excursao.getImagem2());
			jdbcExcursao.inserir(excursao);
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

	// Busca por nome
	public List<Excursao> buscarExcursaoPorNome(@PathParam("nome") String nome, int id) throws GoTripException {
			

		List<Excursao> excursoes = new ArrayList<Excursao>();
		Conexao conec = new Conexao();
		Connection conexao = null;
		try {

			conexao = conec.abrirConexao();
			JDBCExcursaoDAO jdbcExcursao = new JDBCExcursaoDAO(conexao);
			excursoes = jdbcExcursao.buscarPorNome(nome, id);

		} catch (Exception e) {
			throw new GoTripException(e);

		} finally {
			conec.fecharConexao();
		}

		return excursoes;

	}

	public void deletarExcursao(int id) throws GoTripException {

		Conexao conec = new Conexao();
		Connection conexao = null;
		try{
			
			conexao = conec.abrirConexao();
			JDBCExcursaoDAO jdbcExcursao = new JDBCExcursaoDAO(conexao);
	
			jdbcExcursao.deletar(id);
			
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
			

	public Excursao buscarExcursaoPeloId(int id) throws GoTripException {
		
		Excursao excursao = null;
		try{
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCExcursaoDAO jdbcExcursao = new JDBCExcursaoDAO(conexao);
			excursao = jdbcExcursao.buscarPorId(id);
		}catch(Exception e){
			throw new GoTripException(e);
		}
		return excursao;
	}

	public void editarExcursao(Excursao excursao) throws GoTripException {
		
		ValidaGoTrip.validarExcur(excursao);

		Conexao conec = new Conexao();
		Connection conexao = null;
		try{
		
			conexao = conec.abrirConexao();
			FileUtil util = new FileUtil();
			JDBCCidadeDAO jdbcCidade = new JDBCCidadeDAO(conexao);
			JDBCExcursaoDAO jdbcExcursao = new JDBCExcursaoDAO(conexao);
	
			excursao.getCidade().setId(0);
			
			Cidade cidade = jdbcCidade.buscaCidadeEstado(excursao.getCidade());
			if (cidade.getId() == 0) {
				cidade = jdbcCidade.inserir(excursao.getCidade());
			}
			excursao.setCidade(cidade);
			
			util.moverImagem(excursao.getImagem1());
			util.moverImagem(excursao.getImagem2());
			jdbcExcursao.atualizar(excursao);
			
			
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

