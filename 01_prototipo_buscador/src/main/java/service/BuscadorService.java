package service;

import java.util.ArrayList;
import java.util.List;

import model.Resultado;

public interface BuscadorService {
	List<Resultado> resultados(String tematica);
	
	boolean agregar(Resultado resultado);
}
