package br.com.GoTrip.jdbcinterface;

import br.com.GoTrip.exception.GoTripException;
import br.com.GoTrip.objetos.Endereco;

public interface EnderecoDAO {
	
	public Endereco inserir(Endereco endereco) throws GoTripException;
	public boolean deletar(int id) throws GoTripException;
	public Endereco buscarPorId(int cod) throws GoTripException;
	public Endereco atualizar(Endereco endereco) throws GoTripException;
}
