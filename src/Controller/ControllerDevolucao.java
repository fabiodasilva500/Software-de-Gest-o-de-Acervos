package Controller;

import Entidades.Devolucao;

public interface ControllerDevolucao {
	public boolean inserirDevolucao(Devolucao devolucao);
	public boolean atualizarDevolucao(Devolucao devolucao);
	public Devolucao consultarDevolucao (Devolucao devolucao);
	public boolean excluirDevolucao (Devolucao devolucao);
}
