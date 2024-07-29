package service;

import java.util.List;

import entities.Curso;
import model.AlumnoDto;
import model.CursoDto;

public interface FormacionService {
	
	List<CursoDto> cursos();
	
	List<AlumnoDto> bucarAlumnoMatriculado(int idCurso);
	
	boolean altaCurso(CursoDto curso);

}
