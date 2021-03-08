package ecommerce.petshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
