package dao;

import java.util.List;

import model.Resultado;

public interface ResultadosDao {
	
	// Aquí se describen los metodos que necesita el dao
	
	List<Resultado> findByTematica (String tematica);
	
	void save(Resultado resultado);
	
	Resultado findByUrl(String url);
	
	void deleteByUrl(String url);
	
	

}
