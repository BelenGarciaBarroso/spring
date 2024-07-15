package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CursosDao;
import model.Curso;

@Service
public class CursosServiceImpl implements CursosService{
	

	CursosDao dao;
	public CursosServiceImpl(CursosDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean nuevo(Curso curso) {		
		if(dao.findByCurso(curso.getNombre())==null) {
			dao.save(curso);
			return false;
		}
		return false;
		
	}
	
	@Override
	public List<Curso> preciosCursoMax(double precioMax){		
		return dao.findByPrecioMax(precioMax);
	}
	
	@Override
	public void eliminarCurso(String nombre) {		
		if(dao.findByCurso(nombre)!=null) {
			dao.deleteByCurso(nombre);
		}
		
	}
	
	
}
