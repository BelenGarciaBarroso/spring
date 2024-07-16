package service;

import java.util.List;

import entities.Resultado;
import model.ResultadoDto;

public interface BuscadorService {
	List<ResultadoDto> buscar(String tematica);
	boolean agregar(ResultadoDto resultado);
	ResultadoDto buscarPorUrl(String url);
	void eliminar(String url);
}
