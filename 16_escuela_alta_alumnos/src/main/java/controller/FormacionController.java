package controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.AlumnoDto;
import model.CursoDto;
import service.FormacionService;

@Controller
public class FormacionController {
	FormacionService formacionService;

	public FormacionController(FormacionService formacionService) {
		this.formacionService = formacionService;
	}
	@GetMapping(value="consulta")
	public String inicial(Model model) {
		model.addAttribute("cursos", formacionService.cursos());
		return "cursos";
	}
	
	@GetMapping(value="buscarAlumnosPorCurso",produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AlumnoDto> alumnosPorCurso(@RequestParam("idCurso") int idCurso){
		
		return formacionService.buscarAlumnosMatriculados(idCurso);
	} 
	
	@PostMapping(value="altaCurso",produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String altaCurso(@ModelAttribute CursoDto curso){
		return String.valueOf(formacionService.altaCurso(curso));
	}
	@PostMapping(value="matricularAlumnoExistente",produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String matricularAlumno(@RequestParam("usuario") String usuario, 
												@RequestParam("idCurso") int idCurso) {
		AlumnoDto alumno=formacionService.alumnoPorUsuario(usuario);
		return String.valueOf(formacionService.matricularAlumno(alumno, idCurso));
	}
	
	@GetMapping(value="paginaMatriculas")
	public String paginaMatriculas(Model model) {
		model.addAttribute("alumnos", formacionService.alumnos());
		model.addAttribute("cursos", formacionService.cursos());
		return "matriculacion";
		
	}
	@PostMapping(value="matricularNuevoAlumno",produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String matricularAlumno(@ModelAttribute AlumnoDto alumno, 
												@RequestParam("idCurso") int idCurso) {
		return String.valueOf(formacionService.altaAlumno(alumno, idCurso));
	}
	@GetMapping(value="buscarCursosNoAlumno",produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CursoDto> alumnosPorCurso(@RequestParam("usuario") String usuario){
		
		return formacionService.cursosNoAlumno(usuario);
	} 
	
}
