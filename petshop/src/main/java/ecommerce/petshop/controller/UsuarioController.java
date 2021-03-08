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

import ecommerce.petshop.model.UsuarioModel;
import ecommerce.petshop.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController 
{
	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping
	public ResponseEntity<List<UsuarioModel>> GetAll()
	{
		return ResponseEntity.ok(this.repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModel> GetById(@PathVariable Long id)
	{
		return this.repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<UsuarioModel>> GetByNome(@PathVariable String nome)
	{
		return ResponseEntity.ok(this.repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<UsuarioModel> Post(@RequestBody UsuarioModel usuario)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(this.repository.save(usuario));
	}
	
	@PutMapping
	public ResponseEntity<UsuarioModel> Put(@RequestBody UsuarioModel usuario)
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.repository.save(usuario));
	}
}
