package dao;

import java.util.List;

import model.Curso;

public interface CursosDao {
	
	void save(Curso curso);
	
	List<Curso> findByPrecioMax (Double precioMax);
	
	Curso findByCurso (String nombre);
	
	void deleteByCurso (String nombre);
	
	double averageByTematica(String tematica);

}
