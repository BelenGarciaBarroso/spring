package entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="matriculas")
public class Matricula {
	
	@EmbeddedId
	private MatriculaPK pk;
	private double nota;
	
	@ManyToOne
	@JoinColumn(name="IdCurso", referencedColumnName="idCurso")
	private Curso curso;
	
	@ManyToOne
	@JoinColumn(name="usuario", referencedColumnName="usuario", updatable = false, insertable = false)
	private Alumno alumno;
	
	public Matricula(MatriculaPK pk, double nota, Curso cursos, Alumno alumnos) {
		super();
		this.pk = pk;
		this.nota = nota;
		this.curso = curso;
		this.alumno = alumno;
	}
	
	

	public Matricula() {
		
	}



	public MatriculaPK getPk() {
		return pk;
	}

	public void setPk(MatriculaPK pk) {
		this.pk = pk;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public Curso getCursos() {
		return curso;
	}

	public void setCursos(Curso cursos) {
		this.curso = curso;
	}

	public Alumno getAlumnos() {
		return alumno;
	}

	public void setAlumnos(Alumno alumnos) {
		this.alumno = alumno;
	}
	
}
