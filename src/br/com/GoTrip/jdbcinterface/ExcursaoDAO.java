package br.com.GoTrip.jdbcinterface;

import java.util.List;

import br.com.GoTrip.exception.GoTripException;
import br.com.GoTrip.objetos.Excursao;

public interface ExcursaoDAO {
	
	public Excursao inserir(Excursao excursao) throws GoTripException;
	public List<Excursao> buscarPorNome(String nome, int id) throws GoTripException;
	public boolean deletar(int id) throws GoTripException;
	public Excursao buscarPorId(int cod) throws GoTripException;
	public Excursao atualizar(Excursao excursao) throws GoTripException;
}
