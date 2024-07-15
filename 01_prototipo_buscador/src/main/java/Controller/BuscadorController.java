package Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import model.Resultado;
import service.BuscadorService;

@Controller
public class BuscadorController {
	@Autowired // para utilizar clases del service sin instanciar.
	BuscadorService service;
	@GetMapping(value="buscar")
	public String buscarResultados(@RequestParam("tematica") String tematica, HttpServletRequest request) {
		// creamos la lista con los resultados de la búsqueda, llamado al método resultados
		List<Resultado> resultados=service.resultados(tematica);
		//Se genera el atributo para guardar los datos.
		request.setAttribute("resultados", resultados);
		// Se devuelve el resultado en forma de cadena con la dirección
		return "resultados";
	}
	
	@GetMapping(value="alta")
	public String agregar (@RequestParam("url") String url,
						   @RequestParam("tematica") String tematica,
						   @RequestParam("descripcion") String descripcion) {
		Resultado resultado=new Resultado(url, tematica,descripcion);
		return service.agregar(resultado)?"inicio":"error";
	}

}
