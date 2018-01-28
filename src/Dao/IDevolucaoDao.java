package Dao;

import Entidades.Devolucao;

public interface IDevolucaoDao {
	public boolean insereDevolucao(Devolucao devolucao) throws DevolucaoDaoException;
	public boolean AtualizarDevolucao(Devolucao devolucao) throws DevolucaoDaoException;
	public boolean ExcluirDevolucao(Devolucao devolucao) throws DevolucaoDaoException;
	public Devolucao consultaDevolucao(Devolucao devolucao) throws DevolucaoDaoException;
}
