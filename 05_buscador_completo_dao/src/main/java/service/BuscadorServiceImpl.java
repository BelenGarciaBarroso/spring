package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import dao.ResultadosDao;
import model.Resultado;
@Service("buscadorEstandar")
@Scope("singleton")
public class BuscadorServiceImpl implements BuscadorService {
	@Autowired
	ResultadosDao dao;
	
	@Override
	public List<Resultado> buscar(String tematica) {
		return dao.findByTematica(tematica);
	}

	@Override
	public boolean agregar(Resultado resultado) {
		if(dao.findByUrl(resultado.getUrl())==null) {
			dao.save(resultado);
			return true;
		}
		return false;
	}

	@Override
	public Resultado buscarPorUrl(String url) {
		return dao.findByUrl(url);
	}

	@Override
	public void eliminar(String url) {
		dao.deleteByUrl(url);
		
	}

}
