package service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AlumnosDao;
import dao.CursosDao;
import model.AlumnoDto;
import model.CursoDto;
import utilidades.Mapeador;
@Service
public class FormacionServiceImpl implements FormacionService {
	
	AlumnosDao alumnosDao;
	CursosDao cursosDao;
	Mapeador mapeador;
	public FormacionServiceImpl(AlumnosDao alumnosDao, CursosDao cursosDao, Mapeador mapeador) {
		super();
		this.alumnosDao = alumnosDao;
		this.cursosDao = cursosDao;
		this.mapeador = mapeador;
	}

	@Transactional
	@Override
	public List<CursoDto> cursos() {
		return cursosDao.findAll().stream()
				.map(c->mapeador.cursoEntityToDto(c))
				.toList();
	}

	@Override
	public List<AlumnoDto> bucarAlumnoMatriculado(int idCurso) {
		return alumnosDao.findByIdCurso(idCurso).stream()
				.map(a->mapeador.alumnoEntityToDto(a))
				.toList();
	}

}
