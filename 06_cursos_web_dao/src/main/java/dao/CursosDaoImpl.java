package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import model.Curso;


@Repository
public class CursosDaoImpl implements CursosDao {

	//@Autowired ->obsoleta
	JdbcTemplate template;
	
	public CursosDaoImpl(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public void save(Curso curso) {
		String sql="insert into cursos (nombre,duracion,precio) value(?,?,?)";
		template.update(sql, curso.getNombre(),curso.getDuracion(),curso.getPrecio());

	}

	@Override
	public List<Curso> findByPrecioMax(Double precio) {
		String sql="select * from cursos where precio<=?";
		RowMapper<Curso> mapper=(c,f)->new Curso(c.getString("nombre"),
												c.getString("tematica"), 
												c.getInt("duracion"),
												c.getDouble("precio"));
		return template.query(sql,mapper,precio);
	}

	@Override
	public Curso findByCurso(String nombre) {
//		String sql="select * from cursos where nombre=?";
//		RowMapper<Curso> mapper=(c,f)->new Curso(c.getString("nombre"),
//												c.getString("tematica"),
//												c.getInt("duracion"),
//												c.getDouble("precio"));
//		List<Curso>cursos=template.query(sql,mapper,nombre);
//		return cursos.size()>0?cursos.get(0):null;
		
		String sql="select * from cursos where nombre=?";
		try {
			return template.queryForObject(sql, (c, f)->new Curso(c.getString("nombre"),
											c.getString("tematica"), 
											c.getInt("duracion"),
											c.getDouble("precio")),nombre);
		}catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public void deleteByCurso(String nombre) {
		template.update("delete from cursos where nombre=?", nombre);

	}
	
	@Override
	public double averageByTematica(String tematica) {
		String sql="select avg(precio) from cursos wher tematica=?";  
		return template.queryForObject(sql, Double.class, tematica); //(sql=url, Double.class=tipo del resultado que queremos, tematica=la busqueda o lista de argumentos
	}

}
