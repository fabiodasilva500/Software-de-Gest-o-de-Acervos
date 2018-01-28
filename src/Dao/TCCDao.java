package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;


import Entidades.TCC;

public class TCCDao  implements ITCCDao{
	JTDSUtil gDao=new JTDSUtil();
	Connection c = gDao.getConnection();


	@Override
	public boolean InsereTCC(TCC tcc) throws TCCDaoException {
		boolean inserido=false;

		String sql="INSERT INTO TCC VALUES (?,?,?,?,?,?,?,?)";   //Realizando uma pré compilação SQL dentro do java

		try{

			PreparedStatement ps=c.prepareStatement(sql);
			ps.setString(1, tcc.getTitulo());
			ps.setString(2, tcc.getDesenvolvedor());
			ps.setString(3, tcc.getInstituicao());
			ps.setInt(4, tcc.getAno());
			ps.setString(5,tcc.getSemestre());
			ps.setString(6, tcc.getFormato());
			ps.setString(7, tcc.getCurso());
			ps.setString(8, tcc.getDescricao());


			ps.execute();
			ps.close();
			inserido=true;
		}

		catch(Exception e){
			throw new TCCDaoException("Erro na inserção do TCC:"+tcc.getID());
		}

		return inserido;
	}


	@Override
	public boolean AtualizarTCCNome(TCC tcc) throws TCCDaoException {
		boolean atualizado=false;

		String sql = "UPDATE TCC SET  desenvolvedor = ?, instituicao = ?, ano = ?, semestre = ?, formato = ?, curso = ?, descricao = ? Where titulo like ?";

		try{
			PreparedStatement ps=c.prepareStatement(sql);


			ps.setString(1, tcc.getDesenvolvedor());
			ps.setString(2, tcc.getInstituicao());
			ps.setInt(3, tcc.getAno());
			ps.setString(4, tcc.getSemestre());
			ps.setString(5, tcc.getFormato());
			ps.setString(6, tcc.getCurso());
			ps.setString(7, tcc.getDescricao());

			ps.setString(8, "%"+tcc.getTitulo()+"%");

			ps.execute();
			ps.close();
			atualizado=true;
		}

		catch(Exception e){
			throw new TCCDaoException("Erro na atualização do TCC"+tcc.getTitulo());
		}

		return atualizado;
	}



	@Override
	public boolean excluirTCC(TCC tcc) throws TCCDaoException {
		boolean excluido=false;

		String sql="DELETE TCC Where titulo = ?";

		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, tcc.getTitulo());
			ps.execute();
			ps.close();
			excluido=true;
		}

		catch(Exception e){
			throw new TCCDaoException("Erro na exclusão do tcc:"+tcc.getID());
		}

		return excluido;
	}


	@Override
	public List<TCC> consultarTCCNome(String titulo) throws TCCDaoException{

		List <TCC> geraLista=new ArrayList<TCC>();

		String sql="Select  id, titulo, desenvolvedor, instituicao, ano, semestre, formato, curso, descricao from TCC where titulo like ?";


		try{
			TCC tccConsultado=new TCC();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, "%"+titulo+"%");
			ResultSet rs=ps.executeQuery();     //retornando um result set

			while(rs.next()){


				tccConsultado.setID(rs.getInt("id"));
				tccConsultado.setTitulo(rs.getString("titulo"));
				tccConsultado.setDesenvolvedor(rs.getString("desenvolvedor"));
				tccConsultado.setInstituicao(rs.getString("instituicao"));
				tccConsultado.setAno(rs.getInt("ano"));
				tccConsultado.setSemestre(rs.getString("semestre"));
				tccConsultado.setFormato(rs.getString("formato"));
				tccConsultado.setCurso(rs.getString("curso"));
				tccConsultado.setDescricao(rs.getString("descricao"));
				geraLista.add(tccConsultado);
			}



			ps.close();
			rs.close();
		}


		catch(Exception e){
			throw new TCCDaoException("Erro na consulta do tcc:"+titulo);
		}

		return geraLista;
	}

}





