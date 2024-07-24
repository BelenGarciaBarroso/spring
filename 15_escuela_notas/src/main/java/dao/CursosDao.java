package dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.Curso;

public interface CursosDao extends JpaRepository<Curso, Integer> {
	
	//@Query ("select c from Curso c join c.matriuclas m where m.curso.usuario=?1")
	@Query ("select c from Curso c join c.matriuclas m where m.matriculaPk.usuario=?1")
	List<Curso> findByUsuario(String usuario);
	
	@Query("select c from Curso c where c not in (select c from Curso c join c.alumnos a where a.usuario=?1)")
	List<Curso> findByNoMatriuclado(String usuario);
	
	//@Query ("select c from Curso c  where c.nombre=?1 and c.fechaInicio=?2")
	// No hace falta poner la Query porque los dos campos de la búsqueda existen en la bbdd
	// y los hemos unido con And
	Curso findByNombreAndFechaInicio(String nombre, LocalDate fecha);
	

}
