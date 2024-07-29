package service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AlumnosDao;
import dao.CursosDao;
import entities.Alumno;
import model.AlumnoDto;
import model.CursoDto;
import utilidades.Mapeador;
@Service
public class FormacionServiceImpl implements FormacionService {
	
	CursosDao cursosDao;
	AlumnosDao alumnosDao;
	Mapeador mapeador;	
	public FormacionServiceImpl(CursosDao cursosDao, AlumnosDao alumnosDao, Mapeador mapeador) {
		this.cursosDao = cursosDao;
		this.alumnosDao = alumnosDao;
		this.mapeador=mapeador;
	}
	//esta anotación se pone para evitar que hibernate cierre la sesión al obtener el objeto principal
	//y así no falle cuando se recuperen los objetos relacionados 
	@Transactional
	@Override
	public List<CursoDto> cursos() {
		return cursosDao.findAll().stream()
				.map(c->mapeador.cursoEntityToDto(c))
				.toList(); 
	}

	@Override
	public List<AlumnoDto> buscarAlumnosMatriculados(int idCurso) {
		return alumnosDao.findByIdCurso(idCurso).stream()
				.map(a->mapeador.alumnoEntityToDto(a))
				.toList(); 
	}
	@Override
	public boolean altaCurso(CursoDto curso) {
		if(cursosDao.findByNombreAndFechaInicio(curso.getNombre(), curso.getFechaInicio())==null) {
			cursosDao.save(mapeador.cursoDtoToEntity(curso));
			return true;
		}
		return false;
	}
	@Override
	public AlumnoDto alumnoPorUsuario(String usuario) {
		return alumnosDao.findById(usuario)
				.map(a->mapeador.alumnoEntityToDto(a))
				.orElse(null);
	}
	@Override
	public boolean altaAlumno(AlumnoDto alumno, int idCurso) {
		if(alumnoPorUsuario(alumno.getUsuario())==null) {
			Alumno al=mapeador.alumnoDtoToEntity(alumno);
			al.setCursos(Set.of(cursosDao.findById(idCurso).get()));
			alumnosDao.save(al);
			return true;
		}
		return false;
	}
	@Transactional
	@Override
	public CursoDto cursoPorId(int idCurso) {
		return cursosDao.findById(idCurso)
				.map(c->mapeador.cursoEntityToDto(c))
				.orElse(null);
	}
	@Override
	public List<AlumnoDto> alumnos() {
		return alumnosDao.findAll().stream()
				.map(a->mapeador.alumnoEntityToDto(a))
				.toList();
				
	}
	@Transactional
	@Override
	public List<CursoDto> cursosNoAlumno(String usuario) {
		return cursosDao.findByNoMatriculado(usuario).stream()
				.map(c->mapeador.cursoEntityToDto(c))
				.toList();
	}
	@Transactional
	@Override
	public boolean matricularAlumno(AlumnoDto alumno, int idCurso) {
		if(alumnoPorUsuario(alumno.getUsuario())!=null) {
			Alumno al=alumnosDao.findById(alumno.getUsuario()).get();
			al.getCursos().add(cursosDao.findById(idCurso).get());
			alumnosDao.save(al);
			return true;
		}
		return false;
		
	}

}
