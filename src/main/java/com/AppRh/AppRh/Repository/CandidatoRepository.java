package com.AppRh.AppRh.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.AppRh.AppRh.models.Candidato;
import com.AppRh.AppRh.models.Vaga;


public interface CandidatoRepository extends CrudRepository<Candidato, String>{

	Iterable<Candidato>findByVaga(Vaga vaga);
	
	Candidato findByRg(String rg);
	
	Candidato findById(long id);
	
	@Query
	List<Candidato>findByNomeCandidato(String nomeCandidato);
	
}
