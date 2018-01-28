package Controller;

import java.util.List;

import Entidades.TCC;

public interface ControllerTCC {
	public boolean inserir(TCC tcc);
	public boolean atualizar(TCC tcc);
	public boolean remover(TCC tcc);
	public List<TCC> pesquisar(String titulo);


}
