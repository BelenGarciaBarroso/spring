package entities;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class MatriculaPK {
	
	private String usuario;
	private int idCurso;
	
	@Override
	public int hashCode() {
		return Objects.hash(idCurso, usuario);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatriculaPK other = (MatriculaPK) obj;
		return idCurso == other.idCurso && Objects.equals(usuario, other.usuario);
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public int getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	
	

}
