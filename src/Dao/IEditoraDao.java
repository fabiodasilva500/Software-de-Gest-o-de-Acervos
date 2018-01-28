package Dao;

import java.util.List;

import Entidades.Editora;

public interface IEditoraDao {
	public boolean InsereEditora(Editora editora) throws EditoraDaoException;
	public boolean AtualizarEditoraID(Editora editora) throws EditoraDaoException;
	public boolean AtualizarEditoraNome(Editora editora) throws EditoraDaoException;
	public boolean ExcluirEditora(Editora editora) throws EditoraDaoException;
	public Editora consultaEditoraID(Editora editora) throws EditoraDaoException;
	public Editora consultaEditoraNome(Editora editora) throws EditoraDaoException;
	public List<Editora> ListaDeEditoras() throws EditoraDaoException;


}
