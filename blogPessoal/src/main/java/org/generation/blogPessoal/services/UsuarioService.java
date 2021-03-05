package org.generation.blogPessoal.services;

import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import java.util.Optional;
import org.generation.blogPessoal.model.UsuarioLoginModel;
import org.generation.blogPessoal.model.UsuarioModel;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	@Autowired UsuarioRepository usuarioRepository;
	
	public UsuarioModel cadastrarUsuario(UsuarioModel usuario) {
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		String senhaEnconder = enconder.encode(usuario.getSenha());
		usuario.setSenha(senhaEnconder);
		return usuarioRepository.save(usuario);
		
	}
	
	public Optional<UsuarioLoginModel> Logar(Optional<UsuarioLoginModel> user){
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		Optional<UsuarioModel> usuario = usuarioRepository.findByUsuario(user.get().getUsuario());
		if(usuario.isPresent()) {
			if(enconder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				String auth = user.get().getUsuario() + ":" +user.get().getSenha();
				byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic "+ new String(encodeAuth);
				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				return user;
			}
		}
		return null;
	}

}
