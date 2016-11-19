package br.com.GoTrip.jdbcinterface;

import java.util.List;

import br.com.GoTrip.exception.GoTripException;
import br.com.GoTrip.objetos.Cidade;

public interface CidadeDAO {
	
	public Cidade inserir(Cidade cidade) throws GoTripException;
	public List<Cidade> buscarPorNome(String nome) throws GoTripException;
	public Cidade buscarPorId(int cod) throws GoTripException;
}
