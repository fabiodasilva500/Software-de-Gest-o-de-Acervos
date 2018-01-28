package Dao;

import java.util.List;

import Entidades.Autor;

public interface IAutorDao {
	public boolean InsereAutor(Autor autor) throws AutorDaoException;
	public boolean AtualizarAutor(Autor autor)  throws AutorDaoException;
	public boolean ExcluirAutor(Autor autor)  throws AutorDaoException;
	public Autor consultaAutorID(Autor autor)  throws AutorDaoException;
	public Autor consultaAutorNome(Autor autor)  throws AutorDaoException;
	public List<Autor> ListaDeAutores() throws AutorDaoException;


}
