package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import entities.Cliente;

public interface ClientesDao extends JpaRepository<Cliente, Integer> {

	Cliente findByUsuario (String usuario);
	
	
	
	
}
