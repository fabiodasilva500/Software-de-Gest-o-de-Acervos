package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


import java.util.ArrayList;

import javax.swing.JOptionPane;

import Entidades.Editora;

public class EditoraDao implements IEditoraDao{

	JTDSUtil gDao=new JTDSUtil();
	Connection c = gDao.getConnection();

	public boolean InsereEditora(Editora editora) throws EditoraDaoException{
		boolean inserido=false;

		String sql="INSERT INTO Editora VALUES (?,?)";  

		try{

			PreparedStatement ps=c.prepareStatement(sql);
			ps.setString(1, editora.getNome());
			ps.setString(2,editora.getDescricao());

			ps.execute();
			ps.close();
			inserido=true;
		}

		catch(Exception e){
			new EditoraDaoException("Erro na inserção da editora:"+editora.getIDEditora());
		}

		return inserido;
	}


	public boolean AtualizarEditoraID(Editora editora) throws EditoraDaoException{
		boolean atualizado=false;

		String sql = "UPDATE Editora SET nome = ?, descricao = ? Where IDEditora = ?";



		try{
			PreparedStatement ps=c.prepareStatement(sql);
			ps.setString(1, editora.getNome());
			ps.setString(2, editora.getDescricao());

			ps.setInt(3, editora.getIDEditora());

			ps.execute();
			ps.close();
			atualizado=true;
		}

		catch(Exception e){
			new EditoraDaoException("Erro na atualização da editora:"+editora.getIDEditora());
		}

		return atualizado;
	}



	public boolean AtualizarEditoraNome(Editora editora) throws EditoraDaoException{
		boolean atualizado=false;

		String sql = "UPDATE Editora SET descricao = ? Where nome = ?";



		try{
			PreparedStatement ps=c.prepareStatement(sql);
			ps.setString(1, editora.getDescricao());
			ps.setString(2, editora.getNome());

			ps.execute();
			ps.close();
			atualizado=true;
		}

		catch(Exception e){
			new EditoraDaoException("Erro na atualização da editora:"+editora.getNome());
		}


		return atualizado;
	}



	public boolean ExcluirEditora(Editora editora) throws EditoraDaoException{
		boolean excluido=false;

		String sql="DELETE Editora Where IDEditora = ?";

		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, editora.getIDEditora());
			ps.execute();
			ps.close();
			excluido=true;
		}

		catch(Exception e){
			new EditoraDaoException("Erro na exclusão da editora:"+editora.getIDEditora());
		}


		return excluido;
	}

	public Editora consultaEditoraID(Editora editora) throws EditoraDaoException{
		Editora editoraConsultada=new Editora();

		String sql="SELECT nome, descricao from Editora where IDEditora = ?";


		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, editora.getIDEditora());

			ResultSet rs=ps.executeQuery();    

			if(rs.next()){
				editoraConsultada.setIDEditora(editora.getIDEditora());

				editoraConsultada.setNome(rs.getString("Nome"));
				editoraConsultada.setDescricao(rs.getString("descricao"));
			}else{
				JOptionPane.showMessageDialog(null, "Não Encontrado", "Atenção",JOptionPane.CANCEL_OPTION);
			}

			ps.close();
			rs.close();
		}

		catch(Exception e){
			new EditoraDaoException("Erro na consulta da editora:"+editora.getIDEditora());
		}


		return editoraConsultada;
	}

	public Editora consultaEditoraNome(Editora editora) throws EditoraDaoException{
		Editora editoraConsultada=new Editora();

		String sql="SELECT IDEditora, nome, descricao from Editora where nome = ?";


		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, editora.getNome());

			ResultSet rs=ps.executeQuery();    

			if(rs.next()){
				editoraConsultada.setIDEditora(rs.getInt("IDEditora"));

				editoraConsultada.setNome(editora.getNome());
				editoraConsultada.setDescricao(rs.getString("descricao"));
			}else{
				JOptionPane.showMessageDialog(null, "Não Encontrado", "Atenção", JOptionPane.CANCEL_OPTION);
			}

			ps.close();
			rs.close();
		}


		catch(Exception e){
			new EditoraDaoException("Erro na atualização da editora:"+editora.getNome());
		}


		return editoraConsultada;
	}


	public List<Editora> ListaDeEditoras() throws EditoraDaoException{ 
		List<Editora> listaEditora=new ArrayList<Editora>();

		String sql="select IDEditora, nome from Editora";

		try{
			PreparedStatement ps= c.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();

			while(rs.next()){
				Editora editora=new Editora();
				editora.setIDEditora(rs.getInt("IDEditora"));
				editora.setNome(rs.getString("Nome"));
				listaEditora.add(editora);
			}
		}
		catch(Exception e){
			new EditoraDaoException("Erro na geração da lista de editoras:");
		}

		return listaEditora;
	}


}

