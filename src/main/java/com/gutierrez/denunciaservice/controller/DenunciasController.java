package com.gutierrez.denunciaservice.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.data.domain.Pageable;

import com.gutierrez.denunciaservice.entity.Denuncias;
import com.gutierrez.denunciaservice.service.DenunciaService;

@RestController
@RequestMapping("/denuncias")
	public class DenunciasController {

		@Autowired
		private DenunciaService service;
		@GetMapping()
		public ResponseEntity<List<Denuncias>>findAll(
				@RequestParam(value="dni", required=false,defaultValue="")String dni,
				@RequestParam(value="offset", required=false,defaultValue="0")int pageNumber,
				@RequestParam(value="limit", required=false,defaultValue="5")int pageSize
				){
			Pageable page=PageRequest.of(pageNumber, pageSize);
			List<Denuncias> denuncias;
			if(dni==null) {
				denuncias=service.findAll(page);
			}else {
				denuncias=service.findByDni(dni, page);
			}
			if(denuncias.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(denuncias);
		}
		
		@GetMapping(value="/{id}")
		public ResponseEntity<Denuncias>findById(@PathVariable("id")int id){
			Denuncias denuncia=service.findById(id);
			if(denuncia==null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(denuncia);
		}
		@PostMapping()
		public ResponseEntity<Denuncias> create(@RequestBody Denuncias denuncia){
			Denuncias registro=service.save(denuncia);
			return ResponseEntity.status(HttpStatus.CREATED).body(denuncia);
		}
		@PutMapping(value="/{id}")
		public ResponseEntity<Denuncias>update(@PathVariable("id")int id,@RequestBody Denuncias denuncia){
			Denuncias registro=service.update(denuncia);
			if(registro==null){
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(registro);
		}
		@DeleteMapping(value="/{id}")
		public ResponseEntity<Denuncias>delete(@PathVariable("id")int id){
			service.delete(id);
			return ResponseEntity.ok(null);
		}
	}