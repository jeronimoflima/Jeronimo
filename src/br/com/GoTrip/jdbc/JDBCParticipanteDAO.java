package br.com.GoTrip.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.GoTrip.exception.GoTripException;
import br.com.GoTrip.jdbcinterface.ParticipanteDAO;
import br.com.GoTrip.objetos.Cidade;
import br.com.GoTrip.objetos.Endereco;
import br.com.GoTrip.objetos.Participante;


public class JDBCParticipanteDAO implements ParticipanteDAO {
	private Connection conexao;
	
	public JDBCParticipanteDAO(Connection conexao) throws GoTripException{
		this.conexao = conexao;
	}
	public Participante inserir(Participante participante) throws GoTripException{
		String comando = "insert into participante "+
					"(nome,data_nascimento,cpf,email,telefone,sexo,rg,status,id_endereco) "+
					"values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement p;
		try{
			
			java.sql.Date date = new java.sql.Date(participante.getData().getTime());
			
			
			p = this.conexao.prepareStatement(comando,PreparedStatement.RETURN_GENERATED_KEYS);
			p.setString(1, participante.getNome());
			p.setDate(2, date);
			p.setString(3, participante.getCpf());
			p.setString(4, participante.getEmail());
			p.setString(5, participante.getTelefone());
			p.setString(6, participante.getSexo());
			p.setString(7, participante.getRg());
			p.setString(8, participante.getStatus());
			p.setInt(9, participante.getEndereco().getId());
			p.execute();
			
			ResultSet rs = p.getGeneratedKeys();
				while(rs.next()){
					participante.setId(rs.getInt(1));
				}
			
			
		}catch (SQLException e){
			throw new GoTripException(e);
		}
		
		return participante;
	}
	
	public void inseriPartiNaExcursao(Participante participante) throws GoTripException{
		String comando = "insert into participante_excursao "+
					"(id_participantes,id_excursao) "+
					"values(?,?)";
		PreparedStatement p;
		try{
			
			p = this.conexao.prepareStatement(comando);
			p.setInt(1, participante.getId());
			p.setInt(2, participante.getExcursao().getId());
			
			p.execute();
			
		}catch (SQLException e){
			throw new GoTripException(e);
		}
		
	}
	
	
	public List<Participante> buscarPorNome(String nome) throws GoTripException{
		String comando = "select * from participante ";
		if(!nome.equals("null") && !nome.equals("")){
			comando += "where nome like'" + nome + "%'";
		}
		
		List<Participante> listParticipante = new ArrayList<Participante>();
		Participante participante = null;
		
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()){
				participante = new Participante();	
				participante.setEndereco(new Endereco());
				participante.getEndereco().setCidade(new Cidade());
				
				
				participante.setId(rs.getInt("id"));
				participante.setNome(rs.getString("nome"));
				participante.setData(rs.getDate("data_nascimento"));
				participante.setCpf(rs.getString("cpf"));
				participante.setEmail(rs.getString("email"));
				participante.setTelefone(rs.getString("telefone"));
				participante.setSexo(rs.getString("sexo"));
				participante.setRg(rs.getString("rg"));
				participante.setStatus(rs.getString("status"));
				listParticipante.add(participante);
				}
		}catch (Exception e){
			throw new GoTripException(e);
		}
		return listParticipante;

		}
	
	
	public Participante buscarPorEmail(String email) throws GoTripException{
		String comando = "select * from participante where email= " +email;
		
		
		Participante participante = new Participante();
		
		
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()){
				
				participante.setEndereco(new Endereco());
				participante.getEndereco().setCidade(new Cidade());
				
				
				participante.setId(rs.getInt("id"));
				participante.setNome(rs.getString("nome"));
				participante.setData(rs.getDate("data_nascimento"));
				participante.setCpf(rs.getString("cpf"));
				participante.setEmail(rs.getString("email"));
				participante.setTelefone(rs.getString("telefone"));
				participante.setSexo(rs.getString("sexo"));
				participante.setRg(rs.getString("rg"));
				participante.setStatus(rs.getString("status"));
				
				}
		}catch (Exception e){
			throw new GoTripException(e);
		}
		return participante;

		}
	
	public List<Participante> buscarParticipantePelaExcursao(int idExcursao, String nome) throws GoTripException {
		/*String comando = "select * from participante where id IN (SELECT id_participantes FROM participante_excursao WHERE id_excursao = " + idExcursao+")";
		if(!nome.equals("null") && !nome.equals("")){
			comando += " and nome like'" + nome + "%'";
		}*/
		
		String comando= "select * from participante "
				+" inner join participante_excursao on participante_excursao.id_participantes = participante.id "
				+" where participante_excursao.id_excursao = " + idExcursao 
				+" and participante.status = 'Ativo'";
				if(!nome.equals("null") && !nome.equals("")){
					comando += " and nome like'" + nome + "%'";
				
				}
		
		List<Participante> listParticipante = new ArrayList<Participante>();
		Participante participante = null;
		
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()){
				participante = new Participante();	
				participante.setEndereco(new Endereco());
				participante.getEndereco().setCidade(new Cidade());
				
				
				participante.setId(rs.getInt("id"));
				participante.setNome(rs.getString("nome"));
				participante.setData(rs.getDate("data_nascimento"));
				participante.setCpf(rs.getString("cpf"));
				participante.setEmail(rs.getString("email"));
				participante.setTelefone(rs.getString("telefone"));
				participante.setSexo(rs.getString("sexo"));
				participante.setRg(rs.getString("rg"));
				participante.setStatus(rs.getString("status"));
				listParticipante.add(participante);
				}
		}catch (Exception e){
			throw new GoTripException(e);
		}
		return listParticipante;

		}
	
	public boolean deletarParticipante(int id, int idExcursao) throws GoTripException{
		
		
		String comando = "delete from participante_excursao where id_participantes = " + id
							+ " and id_excursao = " + idExcursao;
		Statement p;
		try {
			p = this.conexao.createStatement();
			p.execute(comando);
			
			} catch (SQLException e){
				throw new GoTripException(e);
			}
			return true;
		}	
	
	public Participante buscarPorId(int cod) throws GoTripException{
		String comando = " select p.id,p.nome,p.data_nascimento,p.cpf,p.email,p.telefone,p.sexo,p.rg,p.status, "+
				" p.id_endereco,e.nome,e.numero,e.bairro,e.cep,e.complemento,e.id_cidade,c.nome,c.sigla_estado from participante p inner join endereco e "+
				" on p.id_endereco = e.id_endereco inner join cidade c on e.id_cidade = c.id_cidade where id = "+cod;
		Participante participante = null;
		
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			
			if (rs.next()){
				
				participante = new Participante();	
				participante.setEndereco(new Endereco());
				participante.getEndereco().setCidade(new Cidade());
				
				participante.setId(rs.getInt("id"));
				participante.setNome(rs.getString("nome"));
				participante.setData(rs.getDate("data_nascimento"));
				participante.setCpf(rs.getString("cpf"));
				participante.setEmail(rs.getString("email"));
				participante.setTelefone(rs.getString("telefone"));
				participante.setSexo(rs.getString("sexo"));
				participante.setRg(rs.getString("rg"));
				participante.setStatus(rs.getString("status"));
				participante.getEndereco().setId(rs.getInt("id_endereco"));
				participante.getEndereco().setNome(rs.getString("e.nome"));
				participante.getEndereco().setNumero(rs.getInt("numero"));
				participante.getEndereco().setBairro(rs.getNString("bairro"));
				participante.getEndereco().setCep(rs.getInt("cep"));
				participante.getEndereco().setComplemento(rs.getString("complemento"));
				participante.getEndereco().getCidade().setId(rs.getInt("id_cidade"));
				participante.getEndereco().getCidade().setNome(rs.getString("c.nome"));
				participante.getEndereco().getCidade().setEstado(rs.getString("sigla_estado"));
			}
		}catch (Exception e){
			throw new GoTripException(e);
		}
		return participante;
	}
	
	public Participante atualizar (Participante participante) throws GoTripException{
		String comando = "Update participante set nome=?, data_nascimento=?, cpf=?, "
				+ "email=?, telefone=?, sexo=?, rg=?, status=?  where id = " +participante.getId();
			
			
			
			PreparedStatement p;
			
			try{
				
				java.sql.Date date = new java.sql.Date(participante.getData().getTime());
				
				p = this.conexao.prepareStatement(comando);
				p.setString(1, participante.getNome());
				p.setDate(2, date);
				p.setString(3, participante.getCpf());
				p.setString(4, participante.getEmail());
				p.setString(5, participante.getTelefone());
				p.setString(6, participante.getSexo());
				p.setString(7, participante.getRg());
				p.setString(8, participante.getStatus());
				
				p.executeUpdate();
				
			}catch (SQLException e){
				throw new GoTripException(e);
			}
			return participante;
	}
	
}
