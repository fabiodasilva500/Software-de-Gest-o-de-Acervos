package Controller;

import Entidades.Autor;

public interface ControllerAutor {
	public boolean inserirAutor (Autor autor);
	public boolean atualizarAutor(Autor autor);
	public Autor consultarAutorID (Autor autor);
	public Autor consultarAutorNome (Autor autor);
	public boolean excluirAutor (Autor autor);

}
