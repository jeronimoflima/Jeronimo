package br.com.GoTrip.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.GoTrip.exception.GoTripException;
import br.com.GoTrip.jdbcinterface.RelatorioDAO;
import br.com.GoTrip.objetos.Relatorio;


public class JDBCRelatorioDAO implements RelatorioDAO {
	private Connection conexao;
	
	public JDBCRelatorioDAO(Connection conexao) throws GoTripException{
		this.conexao = conexao;
	}
	
	
	
	public List<Relatorio> buscar(String excursao) throws GoTripException{
		
		String comando = " SELECT count(nome) AS total, status FROM excursao WHERE "
				+ "status = 'Excursão Cadastrada' OR status = 'Excursão Garantida' group by status";
		
		
		List<Relatorio> listExcursao = new ArrayList<Relatorio>();
		Relatorio relatorio = null;
		
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()){
				relatorio = new Relatorio();	
				
				
				relatorio.setTotal(rs.getInt("total"));
				relatorio.setStatus(rs.getString("status"));
				
				listExcursao.add(relatorio);
				}
		}catch (Exception e){
			throw new GoTripException(e);
		}
		return listExcursao;

		}




	
	
}
