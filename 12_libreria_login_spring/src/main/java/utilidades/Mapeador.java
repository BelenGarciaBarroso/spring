package utilidades;

import java.util.Optional;

import org.springframework.stereotype.Component;

import dao.TemasDao;
import entities.Cliente;
import entities.Libro;
import entities.Tema;
import model.ClienteDto;
import model.LibroDto;
import model.TemaDto;

@Component
public class Mapeador {
	TemasDao TemaDao;
	public Mapeador(TemasDao temaDao) {
		super();
		TemaDao = temaDao;
	}
	public  TemaDto temaEntityToDto(Tema tema) {
		return new TemaDto(tema.getIdTema(),tema.getTema());
	}
	public  LibroDto libroEntityToDto(Libro libro) {
		Optional<Tema> optTema=TemaDao.findById(libro.getIdTema());
		return new LibroDto(libro.getIsbn(),
						libro.getTitulo(),
						libro.getAutor(),
						libro.getPrecio(),
						libro.getPaginas(),
						temaEntityToDto(optTema.isPresent()?optTema.get():new Tema())
						);
	}
	public  Libro libroDtoToEntity(LibroDto libro) {
		return new Libro(libro.getIsbn(),
				libro.getTitulo(),
				libro.getAutor(),
				libro.getPrecio(),
				libro.getPaginas(),
				libro.getTemaDto().getIdTema()
				);
	}
	
	public Cliente clienteDtoToEntity(ClienteDto cliente) {
		return new Cliente(0,
				cliente.getUsuario(),
				cliente.getPassword(),
				cliente.getEmail(),
				cliente.getTelefono());
	}
}
