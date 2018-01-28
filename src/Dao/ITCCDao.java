package Dao;

import java.util.List;

import Entidades.TCC;

public interface ITCCDao {
	public boolean InsereTCC(TCC tcc) throws TCCDaoException;
	public boolean AtualizarTCCNome(TCC tcc) throws TCCDaoException;
	public boolean excluirTCC(TCC tcc) throws TCCDaoException;
	public List<TCC> consultarTCCNome(String titulo) throws TCCDaoException;
}

