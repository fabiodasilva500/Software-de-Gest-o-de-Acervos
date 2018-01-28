package Controller;

import Entidades.Acervo;
public interface ControllerAcervo {
	public boolean inserirAcervo(Acervo acervo);
	public boolean excluirLivro(Acervo acervo);
	public boolean excluirEditora(Acervo acervo);
	public boolean excluirAutor(Acervo acervo);
}
