package br.com.GoTrip.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.GoTrip.exception.GoTripException;
import br.com.GoTrip.jdbcinterface.CidadeDAO;
import br.com.GoTrip.objetos.Cidade;



public class JDBCCidadeDAO implements CidadeDAO {
	private Connection conexao;
	
	public JDBCCidadeDAO(Connection conexao){
		this.conexao = conexao;
	}
	public Cidade inserir(Cidade cidade) throws GoTripException{
		String comando = "insert into cidade "+
					"(nome,sigla_estado) "+
					"values(?,?)";
		PreparedStatement p;
		int lastId = 0;
		try{
			
			
			p = this.conexao.prepareStatement(comando,PreparedStatement.RETURN_GENERATED_KEYS);
			p.setString(1, cidade.getNome());
			p.setString(2, cidade.getEstado());
			p.execute();
			
			ResultSet rs = p.getGeneratedKeys();
			
			while(rs.next()){
				lastId= rs.getInt(1);
				System.out.print(lastId);
				
			}
			
		}catch (SQLException e){
			throw new GoTripException(e);
			
		}
		cidade.setId(lastId);
		return cidade;
	}
	
	
	public List<Cidade> buscarPorNome(String nome) throws GoTripException{
		String comando = "select * from cidade ";
		if(!nome.equals("null") && !nome.equals("")){
			comando += "where nome like'" + nome + "%'";
		}
		
		List<Cidade> listCidade = new ArrayList<Cidade>();
		Cidade cidade = null;
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()){
				cidade = new Cidade();	
				
				cidade.setId(rs.getInt("id_cidade"));
				cidade.setNome(rs.getString("nome"));
				cidade.setEstado(rs.getString("sigla_estado"));
				
				listCidade.add(cidade);
				}
		}catch (Exception e){
			throw new GoTripException(e);
		}
		return listCidade;

		}public Cidade buscaCidadeEstado(Cidade  cidade) throws GoTripException{
			String comando = "select id_cidade from cidade where nome = '"+cidade.getNome()
					+ "' and sigla_estado = '"+cidade.getEstado()+"'";
						
			try{
				java.sql.Statement stmt = conexao.createStatement();
				ResultSet rs = stmt.executeQuery(comando);
				while (rs.next()){
										
					cidade.setId(rs.getInt("id_cidade"));
					
					}
			}catch (Exception e){
				throw new GoTripException(e);
			}
			return cidade;

			}
	
	
	
	
	public Cidade buscarPorId(int cod) throws GoTripException{
		String comando = " select * from cidade where id_cidade = "+cod;
		Cidade cidade = new Cidade();
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			
			if (rs.next()){
				
				cidade.setId(rs.getInt("id_cidade"));
				cidade.setNome(rs.getString("nome"));
				cidade.setEstado(rs.getString("sigla_estado"));
			}
		}catch (Exception e){
			throw new GoTripException(e);
		}
		return cidade;
	}
	
		
	
}
