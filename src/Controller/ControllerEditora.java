package Controller;

import Entidades.Editora;

public interface ControllerEditora {
	public boolean inserirEditora (Editora editora);
	public boolean atualizarEditoraID(Editora editora);
	public boolean atualizarEditoraNome(Editora editora);
	public Editora consultarEditoraID (Editora editora);
	public Editora consultarEditoraNome (Editora editora);
	public boolean excluirEditora (Editora editora);

}
