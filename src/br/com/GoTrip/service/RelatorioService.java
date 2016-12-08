package br.com.GoTrip.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;

import br.com.GoTrip.bd.conexao.Conexao;
import br.com.GoTrip.exception.GoTripException;
import br.com.GoTrip.jdbc.JDBCRelatorioDAO;
import br.com.GoTrip.objetos.Relatorio;

public class RelatorioService {

	
	public List<Relatorio> buscar(@PathParam("excursao") String excursao) throws GoTripException {
			

		List<Relatorio> relatorio = new ArrayList<Relatorio>();
		Conexao conec = new Conexao();
		Connection conexao = null;
		try {

			conexao = conec.abrirConexao();
			JDBCRelatorioDAO jdbcRelatorio = new JDBCRelatorioDAO(
					conexao);
			relatorio = jdbcRelatorio.buscar(excursao);

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

		return relatorio;

	}
	

}// Finalizar a classe

