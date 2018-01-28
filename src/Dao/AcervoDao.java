package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Entidades.Acervo;
import Entidades.Autor;
import Entidades.Editora;
import Entidades.Livro;

public class AcervoDao implements IAcervoDao {

	JTDSUtil gDao=new JTDSUtil();
	Connection c = gDao.getConnection();


	public boolean InsereLivroEditoraAutor(Acervo acervo) throws AcervoDaoException{
		boolean inserido=false;

		String sql="INSERT INTO Acervo values (?,?,?)";

		try{
			PreparedStatement ps=c.prepareStatement(sql);
			ps.setInt(1, acervo.getIDLivro());
			ps.setInt(2, acervo.getIDAutor());
			ps.setInt(3, acervo.getIDEditora());
			ps.execute();
			ps.close();
			inserido=true;
		}
		catch (Exception e){
			throw new AcervoDaoException("Erro na inserção do acervo:"+acervo.getIDAcervo());
		}
		return inserido;
	}


	public boolean ExcluirLivro(Acervo acervo) throws AcervoDaoException{
		boolean excluido=false;

		String sql="DELETE Acervo Where IDLivro = ?";

		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, acervo.getIDLivro());
			ps.execute();
			ps.close();
			excluido=true;
		}

		catch(Exception e){
			throw new AcervoDaoException("Erro na exclusão do livro:"+acervo.getIDLivro());
		}

		return excluido;
	}



	public boolean ExcluirEditora(Acervo acervo) throws AcervoDaoException{
		boolean excluido=false;

		String sql="DELETE Acervo Where IDEditora= ?";

		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, acervo.getIDEditora());
			ps.execute();
			ps.close();
			excluido=true;
		}

		catch(Exception e){
			throw new AcervoDaoException("Erro na exclusão da editora:"+acervo.getIDEditora());
		}


		return excluido;
	}


	public boolean ExcluirAutor(Acervo acervo) throws AcervoDaoException{
		boolean excluido=false;

		String sql="DELETE Acervo Where IDAutor= ?";

		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, acervo.getIDAutor());
			ps.execute();
			ps.close();
			excluido=true;
		}

		catch(Exception e){
			throw new AcervoDaoException("Erro na exclusão do autor:"+acervo.getIDAutor());
		}


		return excluido;
	}


	public List<Livro> ListaDeLivros() throws AcervoDaoException{

		List<Livro> listaLivro=new ArrayList<Livro>();

		String sql="select IDLivro, titulo, edicao from Livro";

		try{

			PreparedStatement ps= c.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();


			while(rs.next()){
				Livro livro=new Livro();
				livro.setIDLivro(rs.getInt("IDLivro"));
				livro.setTitulo(rs.getString("Titulo"));
				livro.setEdicao(rs.getString("Edicao"));
				listaLivro.add(livro);
			}
		}

		catch(Exception e){
			throw new AcervoDaoException("Erro na geração da lista de livros");
		}


		return listaLivro;
	}


	public List<Editora> ListaDeEditoras() throws AcervoDaoException{
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
			throw new AcervoDaoException("Erro na geração da lista de editoras");
		}

		return listaEditora;
	}


	public List<Autor> ListaDeAutores() throws AcervoDaoException{
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
			throw new AcervoDaoException("Erro na geração da lista de acervos");
		}

		return listaAutor;
	}



	public List<String> geraLista(Livro l) throws AcervoDaoException{

		List<String> listaNome = new ArrayList<String>();

		String sql= "select Livro.titulo, Editora.Nome, Autor.nome_autor "+
				"from Acervo "+
				"Inner join Livro "+
				"ON Acervo.IDLivro=Livro.IDLivro "+
				"inner join Editora "+
				"On Acervo.IDEditora=Editora.IDEditora "+
				"INNER JOIN Autor "+
				"ON Acervo.IDAutor=Autor.IDAutor "+
				"where Livro.IDLivro = ? "; 

		try{
			PreparedStatement ps= c.prepareStatement(sql);
			ps.setInt(1, l.getIDLivro());
			ResultSet rs=ps.executeQuery();

			while(rs.next()){

				//Emprestimo emprestimo=new Emprestimo();  
				Livro livro=new Livro();
				livro.setTitulo(rs.getString("Titulo"));

				Editora editora=new Editora();
				editora.setNome(rs.getString("Nome"));

				Autor autor=new Autor();
				autor.setNome(rs.getString("Nome_Autor"));

				//listaNome.add(String.valueOf(emprestimo.getIDEmprestimo()));


				listaNome.add("Livro:"+livro.getTitulo()+" Editora:"+editora.getNome()+" Autor:"+autor.getNome());
			}
		}

		catch(Exception e){
			throw new AcervoDaoException("Erro na geração da lista geral");
		}


		return listaNome; 
	}



}







