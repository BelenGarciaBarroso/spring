package dao;

import java.util.List;

import entity.Curso;

public interface CursosDao {
	
	public void save(Curso curso);
	
	public List<Curso> findByPrecio(double precio);
	
	public void delete (String nombre);
	
	public Curso findByCurso (String nombre);
	

}
