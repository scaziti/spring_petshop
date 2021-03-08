package ecommerce.petshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.petshop.model.CategoriaModel;
import ecommerce.petshop.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController 
{
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<CategoriaModel>> GetAll()
	{
		return ResponseEntity.ok(this.repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaModel> GetById(@PathVariable Long id)
	{
		return this.repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nomeCategoria}")
	public ResponseEntity<List<CategoriaModel>> GetByNome(@PathVariable String nomeCategoria)
	{
		return ResponseEntity.ok(this.repository.findAllByNomeCategoriaContainingIgnoreCase(nomeCategoria));
	}
	
	@PostMapping
	public ResponseEntity<CategoriaModel> Post(@RequestBody CategoriaModel categoria)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(this.repository.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity<CategoriaModel> Put(@RequestBody CategoriaModel categoria)
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.repository.save(categoria));
	}
}
