package service;

import java.util.List;
import java.util.Optional;

import dao.LibrosDao;
import dao.TemasDao;
import entities.Libro;
import entities.Tema;
import model.LibroDto;
import model.TemaDto;
import utilidades.Mapeador;

public class LibrosServiceImpl implements LibrosService {
	LibrosDao librosDao;
	TemasDao temasDao;
	Mapeador mapeador;
	
	public LibrosServiceImpl(LibrosDao librosDao, TemasDao temasDao, Mapeador mapeador) {
		super();
		this.librosDao = librosDao;
		this.temasDao = temasDao;
		this.mapeador = mapeador;
	}

	public List<TemaDto> getTemas(){
		return temasDao.findAll().stream()
				.map(t->mapeador.temaEntityToDto(t))//Stream<TemaDto>
				.toList();
	}
	
	@Override
	public List<LibroDto> librosTema(int idTema){
		
		if(idTema!=0) {
			return librosDao.findByIdTema(idTema).stream()
					.map(l->mapeador.libroEntityToDto(l))
					.toList();
		}else{
			return librosDao.findAll().stream()
					.map(l->mapeador.libroEntityToDto(l))
					.toList();
		}
			
	}
	@Override
	public LibroDto getLibro(int isbn) {
		Optional<Libro> opLibro= librosDao.findById(isbn);
		return mapeador.libroEntityToDto(opLibro.isPresent()?opLibro.get(): new Libro());
	}
	
	@Override
	public TemaDto getTema(int idTema) {
		Optional<Tema> opTema= temasDao.findById(idTema);
		return mapeador.temaEntityToDto(opTema.isPresent()?opTema.get():new Tema());
	}
	
	@Override
	public boolean guardarLibro(LibroDto libro) {
		//Optional<Libro> opLibro= librosDao.findById(libro.getIsbn());
		if(librosDao.findById(libro.getIsbn())!=null) {
			return false;
		}
		librosDao.save(mapeador.libroDtoToEntity(libro));
		return true;
	}
}
