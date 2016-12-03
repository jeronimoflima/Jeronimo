package br.com.GoTrip.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.GoTrip.exception.GoTripException;
import br.com.GoTrip.jdbcinterface.ExcursaoDAO;
import br.com.GoTrip.objetos.Cidade;
import br.com.GoTrip.objetos.Excursao;
import br.com.GoTrip.objetos.Usuario;

public class JDBCExcursaoDAO implements ExcursaoDAO {
	private Connection conexao;

	public JDBCExcursaoDAO(Connection conexao) throws GoTripException {
		this.conexao = conexao;
	}

	public Excursao inserir(Excursao excursao) throws GoTripException {
		String comando = "insert into excursao "
				+ "(nome,categoria,local_de_partida,total_participantes,data_partida,min_participantes,valor,descricao,imagem1,imagem2,status,id_cidade,id_usuario) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement p;
		try {

			java.sql.Date date = new java.sql.Date(excursao.getData().getTime());

			p = this.conexao.prepareStatement(comando,
					PreparedStatement.RETURN_GENERATED_KEYS);
			p.setString(1, excursao.getNome());
			p.setString(2, excursao.getCategoria());
			p.setString(3, excursao.getLocal());
			p.setInt(4, excursao.getTotalparti());
			p.setDate(5, date);
			p.setInt(6, excursao.getMinimoparti());
			p.setDouble(7, excursao.getValor());
			p.setString(8, excursao.getDescricao());
			p.setString(9, excursao.getImagem1());
			p.setString(10, excursao.getImagem2());
			p.setString(11, excursao.getStatus());
			p.setInt(12, excursao.getCidade().getId());
			p.setInt(13, excursao.getUsuario().getId());
			p.execute();

			ResultSet rs = p.getGeneratedKeys();
			while (rs.next()) {
				excursao.setId(rs.getInt(1));
			}

		} catch (SQLException e) {
			throw new GoTripException(e);
		}

		return excursao;
	}

	public List<Excursao> buscarPorNome(String nome, int id)
			throws GoTripException {
		
		/*
		 * String comando = "select * from excursao where id_usuario = " + id;
		 * if(!nome.equals("null") && !nome.equals("")){ comando +=
		 * " and nome like'" + nome + "%'";
		 */

		String comando = "SELECT e.*, p.id_participantes, p.id_excursao, count(id_participantes) AS total FROM excursao e "
				+ " LEFT JOIN participante_excursao p ON e.id_excursao = p.id_excursao WHERE 1=1 ";
		if(id != 0)		
			comando += " and e.id_usuario = " + id;
		
		if (!nome.equals("null") && !nome.equals("")) 
			comando += " and nome like'" + nome + "%'";
		else
			comando += " group by p.id_excursao ";
	

		List<Excursao> listExcursao = new ArrayList<Excursao>();
		Excursao excursao = null;

		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				excursao = new Excursao();
				excursao.setCidade(new Cidade());

				excursao.setId(rs.getInt("id_excursao"));
				excursao.setNome(rs.getString("nome"));
				excursao.setCategoria(rs.getString("categoria"));
				excursao.setLocal(rs.getString("local_de_partida"));
				excursao.setTotalparti(rs.getInt("total_participantes"));
				excursao.setData(rs.getDate("data_partida"));
				excursao.setMinimoparti(rs.getInt("min_participantes"));
				excursao.setValor(rs.getDouble("valor"));
				excursao.setDescricao(rs.getString("descricao"));
				excursao.setImagem1(rs.getString("imagem1"));
				excursao.setImagem2(rs.getString("imagem2"));
				excursao.setStatus(rs.getString("status"));
				excursao.setContParticipante(rs.getInt("total"));
				listExcursao.add(excursao);
			}
		} catch (Exception e) {
			throw new GoTripException(e);
		}
		return listExcursao;

	}

	public boolean deletar(int id) throws GoTripException {

		String comando = "delete from excursao where id_excursao = " + id;
		Statement p;

		try {
			p = this.conexao.createStatement();
			p.execute(comando);

		} catch (SQLException e) {
			throw new GoTripException(e);
		}
		return true;
	}

	public Excursao buscarPorId(int cod) throws GoTripException {
		String comando = " select e.id_excursao,e.nome,e.categoria,e.local_de_partida,e.total_participantes,e.data_partida,e.min_participantes,e.valor,e.descricao,e.imagem1, "
				+ " e.imagem2,e.status,e.id_cidade,e.id_usuario,c.nome,c.sigla_estado,u.id,u.nome,u.telefone,u.email from excursao e inner join cidade c "
				+ " on e.id_cidade = c.id_cidade inner join usuario u on u.id = e.id_usuario where id_excursao = " + cod;
		Excursao excursao = null;

		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);

			if (rs.next()) {

				excursao = new Excursao();
				excursao.setCidade(new Cidade());
				excursao.setUsuario(new Usuario());

				excursao.setId(rs.getInt("id_excursao"));
				excursao.setNome(rs.getString("nome"));
				excursao.setCategoria(rs.getString("categoria"));
				excursao.setLocal(rs.getString("local_de_partida"));
				excursao.setTotalparti(rs.getInt("total_participantes"));
				excursao.setData(rs.getDate("data_partida"));
				excursao.setMinimoparti(rs.getInt("min_participantes"));
				excursao.setValor(rs.getDouble("valor"));
				excursao.setDescricao(rs.getString("descricao"));
				excursao.setImagem1(rs.getString("imagem1"));
				excursao.setImagem2(rs.getString("imagem2"));
				excursao.setStatus(rs.getString("status"));
				excursao.getCidade().setId(rs.getInt("id_cidade"));
				excursao.getCidade().setNome(rs.getString("c.nome"));
				excursao.getCidade().setEstado(rs.getString("sigla_estado"));
				excursao.getUsuario().setId(rs.getInt("id"));
				excursao.getUsuario().setNome(rs.getString("u.nome"));
				excursao.getUsuario().setTelefone(rs.getString("telefone"));
				excursao.getUsuario().setEmail(rs.getString("email"));

			}
		} catch (Exception e) {
			throw new GoTripException(e);
		}
		return excursao;
	}

	public Excursao atualizar(Excursao excursao) throws GoTripException {

		String comando = "Update excursao set nome=?, categoria=?, local_de_partida=?, total_participantes=?, data_partida=?, "
				+ " min_participantes=?, valor=?, descricao=?, imagem1=?, imagem2=?, status=?  where id_excursao = "
				+ excursao.getId();

		PreparedStatement p;

		try {

			java.sql.Date date = new java.sql.Date(excursao.getData().getTime());

			p = this.conexao.prepareStatement(comando);
			p.setString(1, excursao.getNome());
			p.setString(2, excursao.getCategoria());
			p.setString(3, excursao.getLocal());
			p.setInt(4, excursao.getTotalparti());
			p.setDate(5, date);
			p.setInt(6, excursao.getMinimoparti());
			p.setDouble(7, excursao.getValor());
			p.setString(8, excursao.getDescricao());
			p.setString(9, excursao.getImagem1());
			p.setString(10, excursao.getImagem2());
			p.setString(11, excursao.getStatus());

			p.executeUpdate();

		} catch (SQLException e) {
			throw new GoTripException(e);
		}
		return excursao;
	}

}
