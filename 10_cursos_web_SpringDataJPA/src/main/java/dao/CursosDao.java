package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import entity.Curso;

public interface CursosDao extends JpaRepository<Curso,Integer> {
	
	@Query ("Select c from Curso c where c.precio<=?1")	
	public List<Curso> findByPrecioMax(double precio);
	
	@Transactional
	@Modifying
	public void deleteByNombre (String nombre);
	
	public Curso findByNombre (String nombre);
	

}
