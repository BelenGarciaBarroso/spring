package service;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import dao.CursosDao;
import entity.Curso;
import model.CursoDto;
import utilidades.Mapeador;
@Service
public class CursosServiceImpl implements CursosService{
	CursosDao cursoDao;
	Mapeador mapeador;
	public CursosServiceImpl(CursosDao cursoDao, Mapeador mapeador) {
		super();
		this.cursoDao = cursoDao;
		this.mapeador = mapeador;
	}

	
	@Override
	public boolean nuevo(CursoDto curso) {		
		if(cursoDao.findByNombre(curso.getNombre())==null) {
			Curso c=mapeador.cursoDtoToEntity(curso);
			cursoDao.save(c);
			return true;
		}
		return false;
	}
	
	@Override
	public List<CursoDto> preciosCursoMax(double precioMax){		
		return cursoDao.findByPrecioMax(precioMax).stream()
				.map(c->mapeador.cursoEntityToDto(c))
				.toList();
	}
	
	
	@Override
	public void eliminarCurso(String nombre) {		
		if(cursoDao.findByNombre(nombre)!=null) {
			cursoDao.deleteByNombre(nombre);
		}
		
		
	}
	
	
}
