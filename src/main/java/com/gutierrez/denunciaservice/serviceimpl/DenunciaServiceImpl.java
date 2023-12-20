package com.gutierrez.denunciaservice.serviceimpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gutierrez.denunciaservice.entity.Denuncias;
import com.gutierrez.denunciaservice.repository.DenunciasRepository;
import com.gutierrez.denunciaservice.service.DenunciaService;

@Service
public class DenunciaServiceImpl implements DenunciaService {

	@Autowired
	private DenunciasRepository repository;
	@Override
	@Transactional(readOnly = true)
	public List<Denuncias> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Denuncias> findByDni(String dni, Pageable page) {
		try {
			return repository.findByDniContaining(dni,page);
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Denuncias findById(int id) {
		try {
			Denuncias registro =repository.findById(id).orElseThrow();
			return registro;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Denuncias save(Denuncias denuncia) {
		try {
			Denuncias registro=repository.save(denuncia);
			return registro;
		}catch(Exception e) {
			return null;
		}
		
	}
	@Override
	public Denuncias update(Denuncias denuncia) {
		try {
			Denuncias registro=repository.findById(denuncia.getId()).orElseThrow();
			registro.setDni(denuncia.getDni());
			registro.setFecha(denuncia.getFecha());
			registro.setTitulo(denuncia.getTitulo());
			registro.setDireccion(denuncia.getDireccion());
			registro.setDescripcion(denuncia.getDescripcion());
			repository.save(registro);
			return registro;
		}catch(Exception e){
			return null;
		}
	}
	
	@Override
	public void delete(int id) {
		try {
			Denuncias registro=repository.findById(null).orElseThrow();
			repository.delete(registro);
		}catch(Exception e){
			
		}
	}

	
}












