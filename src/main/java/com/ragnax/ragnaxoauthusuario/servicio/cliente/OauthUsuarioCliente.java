//package com.ragnax.ragnaxoauthusuario.servicio.cliente;
//
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import com.ragnax.domain.oauthusuario.entidad.Usuario;
//
//
//@FeignClient(name="ragnax-oauthusuario" , url = "${server.port.oauthusuario}")
////@FeignClient(name = "ragnax-oauthusuario")
//public interface OauthUsuarioCliente {
//	/****
//	@GetMapping("/usuarios/search/buscar-username")
//	public Usuario findByUsername(@RequestParam String username);
//	
//	@PutMapping("/usuarios/{id}")
//	public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id);
//	***/
//	
//	@PostMapping("/buscar-usuario")
//	public Usuario buscarUsuario(Usuario usuario);
//	
//	
//	
//}
