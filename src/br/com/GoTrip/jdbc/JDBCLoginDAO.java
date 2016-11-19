package br.com.GoTrip.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.GoTrip.exception.GoTripException;
import br.com.GoTrip.objetos.Usuario;

public class JDBCLoginDAO {

private Connection conexao;
	
	public JDBCLoginDAO(Connection conexao){
		this.conexao = conexao;
	}
	
		
	
	public Usuario login(Usuario usuario) throws GoTripException {
		Usuario user = null;
		String sqls = "SELECT email, senha, id, tipo " + "FROM usuario "
				+ "where email='" + usuario.getEmail() + "' and senha='"
				+ usuario.getSenha() + "'";
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(sqls);
			while (rs.next()) {
				user = new Usuario();
				user.setEmail(rs.getString("email"));
				user.setSenha(rs.getString("senha"));
				user.setId(rs.getInt("id"));
				user.setTipo(rs.getInt("tipo"));
				return user;
			}
			return user;
		} catch (SQLException e) {
			throw new GoTripException(e);
		}

	}
}
