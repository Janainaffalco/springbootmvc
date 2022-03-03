package com.AppRh.AppRh.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.AppRh.AppRh.models.Empresa;


public interface EmpresaRepository extends CrudRepository<Empresa, Long>{
	
	Empresa findById(long id);
	Empresa findByNome(String nome);
	
	@Query
	List<Empresa>findByNomeEmpresa(String nomeEmpresa);
}
