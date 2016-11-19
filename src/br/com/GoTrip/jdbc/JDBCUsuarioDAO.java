package br.com.GoTrip.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.GoTrip.exception.GoTripException;
import br.com.GoTrip.objetos.Cidade;
import br.com.GoTrip.objetos.Endereco;
import br.com.GoTrip.objetos.Usuario;


public class JDBCUsuarioDAO{
	private Connection conexao;
	
	public JDBCUsuarioDAO(Connection conexao) throws GoTripException{
		this.conexao = conexao;
	}
	public Usuario inserirAdm(Usuario administrador) throws GoTripException{
		String comando = "insert into usuario "+
					"(tipo,nome,cpf,telefone,email,senha) "+
					"values(?,?,?,?,?,?)";
		PreparedStatement p;
		try{
			
			p = this.conexao.prepareStatement(comando,PreparedStatement.RETURN_GENERATED_KEYS);
			p.setInt(1,administrador.getTipo());
			p.setString(2, administrador.getNome());
			p.setString(3, administrador.getCpf());
			p.setString(4, administrador.getTelefone());
			p.setString(5, administrador.getEmail());
			p.setString(6, administrador.getSenha());
			p.execute();
			
			ResultSet rs = p.getGeneratedKeys();
				while(rs.next()){
					administrador.setId(rs.getInt(1));
				}
			
			
		}catch (SQLException e){
			throw new GoTripException(e);
		}
		
		return administrador;
	}
	
	public Usuario inserirOrga(Usuario organizador) throws GoTripException {
		String comando = "insert into usuario "+
				"(tipo,nome,cpf,telefone,email,senha,data_nascimento,id_endereco) "+
				"values(?,?,?,?,?,?,?,?)";
		PreparedStatement p;
		try{
			
			java.sql.Date date = new java.sql.Date(organizador.getData().getTime());
			
			
			p = this.conexao.prepareStatement(comando,PreparedStatement.RETURN_GENERATED_KEYS);
			p.setInt(1,organizador.getTipo());
			p.setString(2, organizador.getNome());
			p.setString(3, organizador.getCpf());
			p.setString(4, organizador.getTelefone());
			p.setString(5, organizador.getEmail());
			p.setString(6, organizador.getSenha());
			p.setDate(7, date);
			p.setInt(8, organizador.getEndereco().getId());
			p.execute();
			
			ResultSet rs = p.getGeneratedKeys();
				while(rs.next()){
					organizador.setId(rs.getInt(1));
				}
			
			
		}catch (SQLException e){
			throw new GoTripException(e);
		}
		
		return organizador;
	}
	
	public List<Usuario> buscarPorNome(String nome, int tipo) throws GoTripException{
		//System.out.println(tipo);
		String comando = "select * from usuario where tipo = " + tipo;
		if(!nome.equals("null") && !nome.equals("")){
			comando += " and nome like '" + nome + "%'";
		}
		
		List<Usuario> listUsuario = new ArrayList<Usuario>();
		Usuario usuario = null;
		
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()){
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				if(usuario.getTipo()==2){
					usuario.setData(rs.getDate("data_nascimento"));
				}
				listUsuario.add(usuario);
			    	}
		}catch (Exception e){
			throw new GoTripException(e);
		}
		return listUsuario;

		}
	
	
	public boolean deletar(int id) throws GoTripException{
		
		String comando = "delete from usuario where id = " + id ;
		Statement p;
		
		try {
			p = this.conexao.createStatement();
			p.execute(comando);
			
			} catch (SQLException e){
				throw new GoTripException(e);
			}
			return true;
		}	
	
	public Usuario buscarAdmPorId(int cod) throws GoTripException{
		
		String comando = " select * from usuario where id = "+cod;
		Usuario usuario = null;
		
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			
			if (rs.next()){
				
				usuario = new Usuario();	
				
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
			}
		}catch (Exception e){
			throw new GoTripException(e);
		}
		return usuario;
		
		}
	
	public Usuario buscarOrgaPorId(int cod) throws GoTripException{
			
			String comando = " select u.id,u.nome,u.cpf,u.telefone,u.email,u.senha,u.data_nascimento,u.id_endereco, "+
					" e.nome,e.numero,e.bairro,e.cep,e.complemento,e.id_cidade,c.nome,c.sigla_estado from usuario u inner join endereco e "+
					" on u.id_endereco = e.id_endereco inner join cidade c on e.id_cidade = c.id_cidade where id = "+cod;
			Usuario usuario = null;
			
			try{
				java.sql.Statement stmt = conexao.createStatement();
				ResultSet rs = stmt.executeQuery(comando);
				
				if (rs.next()){
					
					usuario = new Usuario();	
					usuario.setEndereco(new Endereco());
					usuario.getEndereco().setCidade(new Cidade());
					
					usuario.setId(rs.getInt("id"));
					usuario.setNome(rs.getString("nome"));
					usuario.setCpf(rs.getString("cpf"));
					usuario.setTelefone(rs.getString("telefone"));
					usuario.setEmail(rs.getString("email"));
					usuario.setSenha(rs.getString("senha"));
					usuario.setData(rs.getDate("data_nascimento"));
					usuario.getEndereco().setId(rs.getInt("id_endereco"));
					usuario.getEndereco().setNome(rs.getString("e.nome"));
					usuario.getEndereco().setNumero(rs.getInt("numero"));
					usuario.getEndereco().setBairro(rs.getNString("bairro"));
					usuario.getEndereco().setCep(rs.getInt("cep"));
					usuario.getEndereco().setComplemento(rs.getString("complemento"));
					usuario.getEndereco().getCidade().setId(rs.getInt("id_cidade"));
					usuario.getEndereco().getCidade().setNome(rs.getString("c.nome"));
					usuario.getEndereco().getCidade().setEstado(rs.getString("sigla_estado"));
				}
			}catch (Exception e){
				throw new GoTripException(e);
			}
			return usuario;
			
		}
	
	
	public Usuario atualizar (Usuario usuario) throws GoTripException{
		
		String comando = "Update usuario set nome=?, cpf=?, "
				+ "telefone=?, email=?, senha=? where id = " +usuario.getId();
			
			
			PreparedStatement p;
			
			try{
				
				p = this.conexao.prepareStatement(comando);
				p.setString(1, usuario.getNome());
				p.setString(2, usuario.getCpf());
				p.setString(3, usuario.getTelefone());
				p.setString(4, usuario.getEmail());
				p.setString(5, usuario.getSenha());
				
				p.executeUpdate();
				
			}catch (SQLException e){
				throw new GoTripException(e);
			}
			return usuario;
	}
	
	
}
