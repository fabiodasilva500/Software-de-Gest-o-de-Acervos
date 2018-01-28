package Dao;

import java.util.List;

import Entidades.Emprestimo;
import Entidades.Livro;
import Entidades.Usuario;

public interface IEmprestimoDao {
	public boolean InsereEmprestimo(Emprestimo emprestimo) throws EmprestimoDaoException;
	public boolean AtualizarEmprestimo(Emprestimo emprestimo) throws EmprestimoDaoException;
	public boolean AtualizaStatusEmprestimo(Emprestimo emprestimo) throws EmprestimoDaoException;
	public boolean excluirEmprestimo(Emprestimo emprestimo) throws EmprestimoDaoException;
	public Emprestimo consultaEmprestimo(Emprestimo emprestimo) throws EmprestimoDaoException;
	public Emprestimo consultaIDQuantidadeSoma(int l) throws EmprestimoDaoException;
	public Emprestimo consultaIDQuantidadeSubtrai(int l) throws EmprestimoDaoException;
	public Emprestimo consultaQuantidadeSoma(int u) throws EmprestimoDaoException;
	public Emprestimo consultaQuantidadeSubtrai(int u) throws EmprestimoDaoException;
	public Emprestimo consultaStatus(int emp) throws EmprestimoDaoException;
	public List<Livro> ListaDeLivros() throws EmprestimoDaoException;
	public List<Usuario> ListaDeUsuarios() throws EmprestimoDaoException;
	public List<Emprestimo> ListaDeEmprestimos() throws EmprestimoDaoException;
	public List<String> geraLista() throws EmprestimoDaoException;
	public List<String> geraListaReservados() throws EmprestimoDaoException;
	public List<String> geraListaData(Emprestimo emprestimo) throws EmprestimoDaoException;
	public List<String> livrosEmAtraso(Emprestimo emprestimo) throws EmprestimoDaoException;


}
