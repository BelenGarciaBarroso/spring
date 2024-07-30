package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.Matricula;
import entities.MatriculaPK;


public interface MatriculasDao extends JpaRepository<Matricula,MatriculaPK>{
	@Query("select avg(m.nota) from Matricula m where m.curso.idCurso=?1")
	double avgByIdCurso(int idCurso);
	@Query("select avg(m.nota) from Matricula m where m.alumno.usuario=?1")
	double avgByUsuario(String usuario);
}
