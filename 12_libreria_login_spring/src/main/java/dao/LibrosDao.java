package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Libro;

public interface LibrosDao extends JpaRepository<Libro, Integer> {
	
	List<Libro> findByIdTema(int idTema);

	// Listado de precios entre dos cifras
	List<Libro> findByPrecioBetween (int pmin, int pmax);

}
