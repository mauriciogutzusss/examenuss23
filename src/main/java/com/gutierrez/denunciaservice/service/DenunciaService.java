package com.gutierrez.denunciaservice.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.gutierrez.denunciaservice.entity.Denuncias;

public interface DenunciaService {

	public List<Denuncias>findAll(Pageable page);
	public List<Denuncias> findByDni(String dni, Pageable page);
	public Denuncias findById(int id);
	public Denuncias save(Denuncias denuncia);
	public Denuncias update(Denuncias denuncias);
	public void delete(int id);
}


	