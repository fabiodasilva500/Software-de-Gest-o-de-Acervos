package Controller;

import Entidades.Emprestimo;

public interface ControllerEmprestimo {
	public boolean inserir(Emprestimo emprestimo);
	public boolean renovar(Emprestimo emprestimo);
	public Emprestimo consultar(Emprestimo emprestimo);
	public boolean excluir(Emprestimo emprestimo);

}
