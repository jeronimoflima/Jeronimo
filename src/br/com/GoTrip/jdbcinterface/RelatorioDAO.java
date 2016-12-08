package br.com.GoTrip.jdbcinterface;

import java.util.List;

import br.com.GoTrip.exception.GoTripException;
import br.com.GoTrip.objetos.Relatorio;

public interface RelatorioDAO {
	
	public List<Relatorio> buscar(String excursao) throws GoTripException;
}
