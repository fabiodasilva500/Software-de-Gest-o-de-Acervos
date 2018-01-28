package Controller;

import Entidades.Livro;

public interface ControllerLivro {
	public boolean inserirLivro(Livro livro);
	public boolean atualizarLivroID(Livro livro);
	public boolean atualizarLivroNome(Livro livro);
	public Livro consultarLivroID (Livro livro);
	public Livro consultarLivroNome (Livro livro);
	public boolean excluirLivro (Livro livro);
}
