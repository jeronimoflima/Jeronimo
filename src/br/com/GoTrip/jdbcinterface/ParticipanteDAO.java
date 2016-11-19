package br.com.GoTrip.jdbcinterface;

import java.util.List;

import br.com.GoTrip.exception.GoTripException;
import br.com.GoTrip.objetos.Participante;

public interface ParticipanteDAO {
	
	public Participante inserir(Participante participante) throws GoTripException;
	public List<Participante> buscarPorNome(String nome) throws GoTripException;
	public boolean deletarParticipante(int id) throws GoTripException;
	public Participante buscarPorId(int cod) throws GoTripException;
	public Participante atualizar(Participante participante) throws GoTripException;
}
