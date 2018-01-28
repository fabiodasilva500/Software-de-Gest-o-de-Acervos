package Controller;

import Entidades.Usuario;

public interface ControllerUsuario {
	public boolean inserirUsuario(Usuario usuario);
	public boolean atualizarUsuario(Usuario usuario);
	public Usuario consultarUsuario (Usuario usuario);
	public boolean excluirUsuario (Usuario usuario);
}
