package br.com.springboot.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.model.Usuario;
import br.com.springboot.repository.UsuarioRepository;

@RestController
public class GreetingsController {

	@Autowired
	private UsuarioRepository usuarioRepository;	

	@GetMapping("/listatodos")
	public ResponseEntity<List<Usuario>> listaUsuario() {

		List<Usuario> usuarios = usuarioRepository.findAll();

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
		Usuario user = usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> apagar(@RequestParam Long usuario) {
		
		usuarioRepository.deleteById(usuario); 
		
		return new ResponseEntity<String>("Usuário deletado com sucesso", HttpStatus.OK);
	}
	
	@GetMapping("/buscaruserpeloid")
	@ResponseBody
	public ResponseEntity<Usuario> buscarporid(@RequestParam(name="buscar")Long buscar) {

		Usuario usuarios = usuarioRepository.findById(buscar).get();	
		
		return new ResponseEntity<Usuario>(usuarios, HttpStatus.OK);
	}
	
	@PutMapping(value = "/atualizar")
	@ResponseBody
	public ResponseEntity<?> atualizar(@RequestBody Usuario user) { 
		if(user.getId() == null) {
			return new ResponseEntity<String>("É necessário informar o ID para atualização", HttpStatus.OK);

			
		}else {
		Usuario usuarios = usuarioRepository.saveAndFlush(user);

		return new ResponseEntity<Usuario>(usuarios, HttpStatus.OK);
	}
	}
	
	@GetMapping(value="/buscarPorNome")
	@ResponseBody
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name="name")String name) {

		List<Usuario> usuarios = usuarioRepository.buscarPorNome(name.trim().toUpperCase());

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}	

}
