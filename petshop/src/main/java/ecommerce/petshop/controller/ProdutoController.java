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

import ecommerce.petshop.model.ProdutoModel;
import ecommerce.petshop.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController 
{
	@Autowired
	private ProdutoRepository repository;
	
    @GetMapping
    public ResponseEntity<List<ProdutoModel>> GetAll()
    {
    	return ResponseEntity.ok(this.repository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModel> GetById(@PathVariable Long id)
    {
    	return this.repository.findById(id)
    			.map(resp -> ResponseEntity.ok(resp))
    			.orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ProdutoModel>> GetByNome(@PathVariable String nome)
    {
    	return ResponseEntity.ok(this.repository.findAllByNomeContainingIgnoreCase(nome));
    }
    
    @PostMapping
    public ResponseEntity<ProdutoModel> Post(@RequestBody ProdutoModel produto)
    {
    	return ResponseEntity.status(HttpStatus.CREATED).body(this.repository.save(produto));
    }
    
    @PutMapping
    public ResponseEntity<ProdutoModel> Put(@RequestBody ProdutoModel produto)
    {
    	return ResponseEntity.status(HttpStatus.OK).body(this.repository.save(produto));
    }
}
