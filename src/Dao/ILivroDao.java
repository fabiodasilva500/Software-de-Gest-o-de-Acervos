package Dao;

import java.util.List;

import Entidades.Livro;

public interface ILivroDao {
	public boolean InsereLivro(Livro livro) throws LivroDaoException;
	public boolean AtualizarLivroID(Livro livro) throws LivroDaoException;
	public boolean AtualizarLivroNome(Livro livro) throws LivroDaoException;
	public boolean excluirLivro(Livro livro) throws LivroDaoException;
	public Livro consultaLivroNome(Livro livro) throws LivroDaoException;
	public Livro consultaIDQuantidadeSubtrai(int l) throws LivroDaoException;
	public Livro consultaIDQuantidadeSoma(int l) throws LivroDaoException;
	public void AtualizaQuantidadeDisponivelSubtrai(Livro livro) throws LivroDaoException;
	public void AtualizaQuantidadeDisponivelSoma(Livro livro) throws LivroDaoException;
	public boolean buscaQuantidade(int l) throws LivroDaoException;
	public List<Livro> ListaDeLivros() throws LivroDaoException;
}
