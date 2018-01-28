package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


import java.util.ArrayList;

import javax.swing.JOptionPane;

import Entidades.Autor;

public class AutorDao implements IAutorDao{

	JTDSUtil gDao=new JTDSUtil();
	Connection c = gDao.getConnection();

	public boolean InsereAutor(Autor autor)  throws AutorDaoException{
		boolean inserido=false;

		String sql="INSERT INTO Autor VALUES (?)";  

		try{



			PreparedStatement ps=c.prepareStatement(sql);
			ps.setString(1, autor.getNome());

			ps.execute();
			ps.close();
			inserido=true;
		}

		catch(Exception e){
			throw new AutorDaoException("Erro na inserção do autor:"+autor.getIDAutor());
		}

		return inserido;
	}


	public boolean AtualizarAutor(Autor autor)  throws AutorDaoException{
		boolean atualizado=false;

		String sql = "UPDATE Autor SET nome_autor = ? Where IDAutor = ?";



		try{
			PreparedStatement ps=c.prepareStatement(sql);
			ps.setString(1,autor.getNome());
			ps.setInt(2,autor.getIDAutor());
			ps.execute();
			ps.close();
			atualizado=true;
		}

		catch(Exception e){
			throw new AutorDaoException("Erro na atualização do autor:"+autor.getIDAutor());
		}
		return atualizado;
	}



	public boolean ExcluirAutor(Autor autor)  throws AutorDaoException{
		boolean excluido=false;

		String sql="DELETE Autor Where IDAutor = ?";

		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, autor.getIDAutor());
			ps.execute();
			ps.close();
			excluido=true;
		}

		catch(Exception e){
			throw new AutorDaoException("Erro na exclusão do autor:"+autor.getIDAutor());
		}

		return excluido;
	}

	public Autor consultaAutorID(Autor autor)  throws AutorDaoException{
		Autor autorConsultado=new Autor();

		String sql="SELECT IDAutor, nome_autor from Autor where IDAutor = ?";


		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, autor.getIDAutor());
			ResultSet rs=ps.executeQuery();    

			if(rs.next()){
				autorConsultado.setIDAutor(autor.getIDAutor());
				autorConsultado.setNome(rs.getString("Nome_Autor"));
			}else{
				JOptionPane.showMessageDialog(null, "Não Encontrado","Atenção", JOptionPane.CANCEL_OPTION);
			}

			ps.close();
			rs.close();
		}



		catch(Exception e){
			throw new AutorDaoException("Erro na consulta do autor:"+autor.getIDAutor());
		}

		return autorConsultado;
	}


	public Autor consultaAutorNome(Autor autor)  throws AutorDaoException{
		Autor autorConsultado=new Autor();

		String sql="SELECT IDAutor, nome_autor from Autor where nome_autor = ?";


		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, autor.getNome());
			ResultSet rs=ps.executeQuery();    

			if(rs.next()){
				autorConsultado.setIDAutor(rs.getInt("IDAutor"));
				autorConsultado.setNome(autor.getNome());
			}else{
				JOptionPane.showMessageDialog(null, "Não Encontrado", "Atenção", JOptionPane.CANCEL_OPTION);
			}

			ps.close();
			rs.close();
		}



		catch(Exception e){
			throw new AutorDaoException("Erro na consulta do autor:"+autor.getIDAutor());
		}

		return autorConsultado;
	}


	public List<Autor> ListaDeAutores()  throws AutorDaoException{
		List<Autor> listaAutor=new ArrayList<Autor>();

		String sql="Select IDAutor, nome_autor from Autor";

		try{
			PreparedStatement ps=c.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();

			while(rs.next()){
				Autor autor=new Autor();
				autor.setIDAutor(rs.getInt("IDAutor"));
				autor.setNome(rs.getString("Nome_autor"));
				listaAutor.add(autor);
			}
		}

		catch(Exception e){
			throw new AutorDaoException("Erro na geração da lista de autores");
		}
		return listaAutor;
	}


}

