package service;

import java.util.List;

import model.AlumnoDto;
import model.CursoDto;

public interface FormacionService {
	List<CursoDto> cursos();
	List<AlumnoDto> buscarAlumnosMatriculados(int idCurso);
	boolean altaCurso(CursoDto curso);
	AlumnoDto alumnoPorUsuario(String usuario);
	boolean altaAlumno(AlumnoDto alumno, int idCurso);
	boolean matricularAlumno(AlumnoDto alumno,int idCurso);
	CursoDto cursoPorId(int idCurso);
	List<AlumnoDto> alumnos();
	List<CursoDto> cursosNoAlumno(String usuario);
}
