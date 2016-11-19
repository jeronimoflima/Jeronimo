package br.com.GoTrip.bd.conexao;

import java.sql.Connection;

import br.com.GoTrip.exception.GoTripException;

public class Conexao {

	private Connection conexao;
	
	public Connection abrirConexao(){
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
			conexao = java.sql.DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/gotrip","root","");
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return conexao;
	}
	
	public void fecharConexao() throws GoTripException{
		try{
			conexao.close();
		}catch (Exception e){
			throw new GoTripException("Erro de Conexão", e);
		}
	}
}
