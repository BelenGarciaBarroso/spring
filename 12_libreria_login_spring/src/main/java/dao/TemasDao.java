package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Tema;

public interface TemasDao extends JpaRepository<Tema, Integer> {
	
	//List<Tema> findAll(); -> se quita pq ya viene con la interface

	// Tema findById(int idTema);  este también está en la interface pero hemos cambiado Integer idTema (original) por int idTema. Sería sobrecarga.

}
