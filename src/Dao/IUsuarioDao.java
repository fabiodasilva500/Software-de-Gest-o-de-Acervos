package Dao;

import Entidades.Usuario;

public interface IUsuarioDao {
	public boolean InsereUsuario(Usuario usuario) throws UsuarioDaoException;
	public boolean AtualizarUsuario(Usuario usuario) throws UsuarioDaoException;
	public boolean excluirUsuario(Usuario usuario) throws UsuarioDaoException;
	public Usuario consultaUsuario(Usuario usuario) throws UsuarioDaoException;
	public Usuario consultaIDQuantidadeSubtrai(String u) throws UsuarioDaoException;
	public Usuario consultaIDQuantidadeSoma(String u) throws UsuarioDaoException;
	public void AtualizaQuantidadeDisponivelSubtrai(Usuario usuario) throws UsuarioDaoException;
	public void AtualizaQuantidadeDisponivelSoma(Usuario usuario) throws UsuarioDaoException;
	public boolean buscaQuantidade(String u, String reserva) throws UsuarioDaoException;

}
