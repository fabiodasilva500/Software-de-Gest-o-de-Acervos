package Dao;

import java.util.List;

import Entidades.Acervo;
import Entidades.Autor;
import Entidades.Editora;
import Entidades.Livro;

public interface IAcervoDao {
	public boolean InsereLivroEditoraAutor(Acervo acervo) throws AcervoDaoException;
	public boolean ExcluirLivro(Acervo acervo) throws AcervoDaoException;
	public boolean ExcluirEditora(Acervo acervo) throws AcervoDaoException;
	public boolean ExcluirAutor(Acervo acervo) throws AcervoDaoException;
	public List<Livro> ListaDeLivros() throws AcervoDaoException;
	public List<Editora> ListaDeEditoras() throws AcervoDaoException;
	public List<Autor> ListaDeAutores() throws AcervoDaoException;
	public List<String> geraLista(Livro l) throws AcervoDaoException;

}
