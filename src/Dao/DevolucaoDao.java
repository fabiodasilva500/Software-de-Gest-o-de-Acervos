package Dao;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Entidades.Devolucao;

public class DevolucaoDao  implements IDevolucaoDao
{

	JTDSUtil gDao=new JTDSUtil();
	Connection c = gDao.getConnection();

	public boolean insereDevolucao(Devolucao devolucao) throws DevolucaoDaoException
	{
		boolean inserido = false;

		int ep=devolucao.getIDEmprestimo();
		EmprestimoDao emp=new EmprestimoDao();
		try {
			emp.consultaStatus(ep);
		} catch (EmprestimoDaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if(!emp.consultaStatus(ep).getStatus().equals("Reservado")){



				String sql="INSERT INTO devolucao VALUES (?,?,?,?)";
				try
				{
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setInt(1, devolucao.getIDEmprestimo());
					ps.setString(2,devolucao.getAtrasado());
					ps.setInt(3, devolucao.getDiasAtraso());
					ps.setFloat(4, devolucao.getMulta());

					ps.execute();
					ps.close();
					inserido=true;
				}

				catch(Exception e)
				{
					throw new DevolucaoDaoException("Erro na inserção da devolução:"+devolucao.getIDEmprestimo());
				}
			}
			else{
				JOptionPane.showMessageDialog(null,"A devolução não pode ser cadastrada devido a este índice estar associado a uma reserva","Atenção",JOptionPane.CANCEL_OPTION);
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmprestimoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return inserido;
	}

	public boolean AtualizarDevolucao(Devolucao devolucao) throws DevolucaoDaoException
	{

		boolean atualizado = false;

		String sql = "UPDATE devolucao SET atrasado = ?,  dias_Atraso = ?, valor_multa = ? Where IDEmprestimo = ?";
		//		String sql = "UPDATE Editora SET nome = ?, descricao = ? Where IDEditora = ?";

		try
		{
			PreparedStatement ps=c.prepareStatement(sql);
			ps.setString(1,devolucao.getAtrasado());
			ps.setInt(2, devolucao.getDiasAtraso());
			ps.setFloat(3, devolucao.getMulta());
			ps.setInt(4, devolucao.getIDEmprestimo());

			ps.execute();
			ps.close();
			atualizado=true;

		}

		catch(Exception e)
		{
			throw new DevolucaoDaoException("Erro na atualização da devolução:"+devolucao.getIDEmprestimo());
		}

		return atualizado;
	}




	public boolean ExcluirDevolucao(Devolucao devolucao) throws DevolucaoDaoException
	{
		boolean excluido=false;

		String sql = "DELETE devolucao Where IDEmprestimo = ?";

		try
		{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, devolucao.getIDEmprestimo());
			ps.execute();
			ps.close();
			excluido=true;
		}

		catch(Exception e)
		{
			throw new DevolucaoDaoException("Erro exclusão da devolução:"+devolucao.getIDEmprestimo());
		}

		return excluido;
	}

	public Devolucao consultaDevolucao(Devolucao devolucao) throws DevolucaoDaoException{
		Devolucao devolucaoConsultada=new Devolucao();

		String sql="SELECT IDEmprestimo, atrasado, dias_atraso, valor_multa from Devolucao where IDEmprestimo = ?";


		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, devolucao.getIDEmprestimo());

			ResultSet rs=ps.executeQuery();    

			if(rs.next()){
				devolucaoConsultada.setIDEmprestimo(rs.getInt("IDEmprestimo"));
				devolucaoConsultada.setAtrasado(rs.getString("Atrasado"));	
				devolucaoConsultada.setDiasAtraso(rs.getInt("Dias_Atraso"));
				devolucaoConsultada.setMulta(rs.getFloat("Valor_Multa"));


			}else{
				JOptionPane.showMessageDialog(null, "Não Encontrado","Atenção", JOptionPane.CANCEL_OPTION);
			}

			ps.close();
			rs.close();
		}


		catch(Exception e)
		{
			throw new DevolucaoDaoException("Erro consulta da devolução:"+devolucao.getIDEmprestimo());
		}


		return devolucaoConsultada;
	}



}
