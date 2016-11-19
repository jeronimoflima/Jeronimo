package br.com.GoTrip.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.GoTrip.exception.GoTripException;
import br.com.GoTrip.jdbcinterface.EnderecoDAO;
import br.com.GoTrip.objetos.Cidade;
import br.com.GoTrip.objetos.Endereco;



public class JDBCEnderecoDAO implements EnderecoDAO {
	private Connection conexao;
	
	public JDBCEnderecoDAO(Connection conexao){
		this.conexao = conexao;
	}
	public Endereco inserir(Endereco endereco) throws GoTripException{
		String comando = "insert into endereco "+
					"(nome,numero,bairro,cep,id_cidade,complemento) "+
					"values(?,?,?,?,?,?)";
		PreparedStatement p;
		int lastId = 0;
		
		try{
			
			p = this.conexao.prepareStatement(comando,PreparedStatement.RETURN_GENERATED_KEYS);
			p.setString(1, endereco.getNome());
			p.setInt(2, endereco.getNumero());
			p.setString(3, endereco.getBairro());
			p.setInt(4, endereco.getCep());
			p.setInt(5, endereco.getCidade().getId());
			p.setString(6, endereco.getComplemento());
			p.execute();
			
			ResultSet rs = p.getGeneratedKeys();
			
				while(rs.next()){
					lastId = rs.getInt(1);
				}
			
		}catch (SQLException e){
			throw new GoTripException(e);
			
		}
		endereco.setId(lastId);
		return endereco;
	}
	
		
	
	public boolean deletar(int id) throws GoTripException{
		
		String comando = "delete from endereco where id_endereco = " + id ;
		Statement p;
		
		try {
			p = this.conexao.createStatement();
			p.execute(comando);
			
			} catch (SQLException e){
				throw new GoTripException(e);
			}
			return true;
		}	
	
	public Endereco buscarPorId(int cod) throws GoTripException{
		String comando = " select * from endereco where id_endereco = "+cod;
		Endereco endereco = new Endereco();
		Cidade cidade = new Cidade();
		
		
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			
			if (rs.next()){
				
				
				cidade.setId(rs.getInt("id_cidade"));
				
				
				
				endereco.setId(rs.getInt("id_endereco"));
				endereco.setNome(rs.getString("nome"));
				endereco.setNumero(rs.getInt("numero"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setCep(rs.getInt("cep"));
				endereco.setCidade(cidade);
				endereco.setComplemento(rs.getString("complemento"));
			}
		}catch (Exception e){
			throw new GoTripException(e);
		}
		return endereco;
	}
	
	public Endereco atualizar (Endereco endereco) throws GoTripException{
		String comando = "Update endereco set nome=?, numero=?, bairro=?, "
				+ "cep=?, id_cidade=?, complemento=? where id_endereco = " +endereco.getId();
			
			
			
			PreparedStatement p;
			try{
				p = this.conexao.prepareStatement(comando);
				p.setString(1, endereco.getNome());
				p.setInt(2, endereco.getNumero());
				p.setString(3, endereco.getBairro());
				p.setInt(4, endereco.getCep());
				p.setInt(5, endereco.getCidade().getId());
				p.setString(6, endereco.getComplemento());
				
				p.executeUpdate();
			}catch (SQLException e){
				throw new GoTripException(e);
				
			}
			return endereco;
	
	}
		
	

	
}
	
	
