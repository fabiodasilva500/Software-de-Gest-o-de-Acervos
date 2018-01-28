package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


import java.util.ArrayList;

import javax.swing.JOptionPane;

import Entidades.Livro;


public class LivroDao implements ILivroDao{

	JTDSUtil gDao=new JTDSUtil();
	Connection c = gDao.getConnection();

	public boolean InsereLivro(Livro livro) throws LivroDaoException{
		boolean inserido=false;

		String sql="INSERT INTO Livro VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";   //Realizando uma pr� compila��o SQL dentro do java

		try{

			//Realizando o pr� processamento
			//seta-se o sql e em seguida s�o procurados os atributos que s�o setados, ou seja, que ter�o valores
			//para serem inseridos


			//O Statement ja busca os valores diretamente no SQL Server

			PreparedStatement ps=c.prepareStatement(sql);
			ps.setString(1, livro.getTitulo());
			ps.setString(2, livro.getEdicao());
			ps.setInt(3, livro.getPaginas());
			ps.setString(4, livro.getVolume());
			ps.setString(5,livro.getISBN());
			ps.setString(6, livro.getClassificacao());
			ps.setString(7, livro.getAssunto());
			ps.setFloat(8, livro.getComprimento());
			ps.setFloat(9, livro.getLargura());
			ps.setString(10, livro.getLocalizacao());
			ps.setInt(11, livro.getQtde());
			ps.setInt(12, livro.getQuantidade_Disponivel());


			//Executando a conex�o
			//Finalizando a conex�o
			//Atribuindo true a vari�vel inserido 
			ps.execute();
			ps.close();
			inserido=true;
		}

		catch(Exception e){
			throw new LivroDaoException("Erro na inser��o do livro:"+livro.getIDLivro());
		}

		return inserido;
	}


	public boolean AtualizarLivroID(Livro livro) throws LivroDaoException{
		boolean atualizado=false;

		String sql = "UPDATE Livro SET  titulo = ?, edicao = ?, paginas = ?, volume = ?,ISBN = ?, classificacao = ?, assunto = ?, comprimento = ?, largura = ?,localizacao = ?, quantidade_total = ?, quantidade_disponivel = ? Where IDLivro = ?";

		try{
			PreparedStatement ps=c.prepareStatement(sql);

			ps.setString(1, livro.getTitulo());
			ps.setString(2, livro.getEdicao());
			ps.setInt(3, livro.getPaginas());
			ps.setString(4, livro.getVolume());
			ps.setString(5,livro.getISBN());
			ps.setString(6, livro.getClassificacao());
			ps.setString(7, livro.getAssunto());
			ps.setFloat(8, livro.getComprimento());
			ps.setFloat(9, livro.getLargura());
			ps.setString(10, livro.getLocalizacao());
			ps.setInt(11, livro.getQtde());
			ps.setInt(12, livro.getQuantidade_Disponivel());

			ps.setInt(13,livro.getIDLivro());
			ps.execute();
			ps.close();
			atualizado=true;
		}

		catch(Exception e){
			throw new LivroDaoException("Erro na atualiza��o do livro:"+livro.getIDLivro());
		}

		return atualizado;
	}


	public boolean AtualizarLivroNome(Livro livro) throws LivroDaoException{
		boolean atualizado=false;

		String sql = "UPDATE Livro SET  edicao = ?, paginas = ?, volume = ?, ISBN = ?, classificacao = ?, assunto = ?, comprimento = ?, largura = ?,localizacao = ?, quantidade_total = ?, quantidade_disponivel = ? Where titulo = ?";

		try{
			PreparedStatement ps=c.prepareStatement(sql);


			ps.setString(1, livro.getEdicao());
			ps.setInt(2, livro.getPaginas());
			ps.setString(3, livro.getVolume());
			ps.setString(4,livro.getISBN());
			ps.setString(5, livro.getClassificacao());
			ps.setString(6, livro.getAssunto());
			ps.setFloat(7, livro.getComprimento());
			ps.setFloat(8, livro.getLargura());
			ps.setString(9, livro.getLocalizacao());
			ps.setInt(10, livro.getQtde());
			ps.setInt(11, livro.getQuantidade_Disponivel());

			ps.setString(12, livro.getTitulo());
			ps.execute();
			ps.close();
			atualizado=true;
		}

		catch(Exception e){
			throw new LivroDaoException("Erro na atualiza��o do livro"+livro.getTitulo());
		}

		return atualizado;
	}



	public boolean excluirLivro(Livro livro) throws LivroDaoException{
		boolean excluido=false;

		String sql="DELETE Livro Where IDLivro = ?";

		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, livro.getIDLivro());
			ps.execute();
			ps.close();
			excluido=true;
		}

		catch(Exception e){
			throw new LivroDaoException("Erro na exclus�o do livro:"+livro.getIDLivro());
		}

		return excluido;
	}

	public Livro consultaLivroID(Livro livro) throws LivroDaoException{
		Livro livroConsultado=new Livro();

		String sql="Select IDLivro, titulo, edicao, paginas, volume, ISBN, classificacao, assunto, comprimento, largura, localizacao, quantidade_total, quantidade_disponivel from Livro where IDLivro = ?";


		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, livro.getIDLivro());
			ResultSet rs=ps.executeQuery();     //retornando um result set

			if(rs.next()){

				livroConsultado.setIDLivro(livro.getIDLivro());
				livroConsultado.setTitulo(rs.getString("Titulo"));
				livroConsultado.setEdicao(rs.getString("Edicao"));
				livroConsultado.setPaginas(rs.getInt("Paginas"));
				livroConsultado.setVolume(rs.getString("Volume"));
				livroConsultado.setISBN(rs.getString("ISBN"));
				livroConsultado.setClassificacao(rs.getString("Classificacao"));
				livroConsultado.setAssunto(rs.getString("Assunto"));
				livroConsultado.setComprimento(rs.getFloat("Comprimento"));
				livroConsultado.setLargura(rs.getFloat("Largura"));
				livroConsultado.setLocalizacao(rs.getString("Localizacao"));
				livroConsultado.setQtde(rs.getInt("Quantidade_Total"));
				livroConsultado.setQuantidade_Disponivel(rs.getInt("Quantidade_Disponivel"));
			}else{
				JOptionPane.showMessageDialog(null, "N�o Encontrado","Aten��o",JOptionPane.CANCEL_OPTION);
			}



			ps.close();
			rs.close();
		}


		catch(Exception e){
			throw new LivroDaoException("Erro na consulta do livro:"+livro.getIDLivro());
		}

		return livroConsultado;
	}



	public Livro consultaLivroNome(Livro livro) throws LivroDaoException{
		Livro livroConsultado=new Livro();

		String sql="Select  IDLivro, titulo, edicao, paginas, volume, ISBN, classificacao, assunto, comprimento, largura, localizacao, quantidade_total, quantidade_disponivel from Livro where titulo = ?";


		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, livro.getTitulo());
			ResultSet rs=ps.executeQuery();     //retornando um result set

			if(rs.next()){


				livroConsultado.setIDLivro(rs.getInt("IDLivro"));
				livroConsultado.setTitulo(livro.getTitulo());
				livroConsultado.setEdicao(rs.getString("Edicao"));
				livroConsultado.setPaginas(rs.getInt("Paginas"));
				livroConsultado.setVolume(rs.getString("Volume"));
				livroConsultado.setISBN(rs.getString("ISBN"));
				livroConsultado.setClassificacao(rs.getString("Classificacao"));
				livroConsultado.setAssunto(rs.getString("Assunto"));
				livroConsultado.setComprimento(rs.getFloat("Comprimento"));
				livroConsultado.setLargura(rs.getFloat("Largura"));
				livroConsultado.setLocalizacao(rs.getString("Localizacao"));
				livroConsultado.setQtde(rs.getInt("Quantidade_Total"));
				livroConsultado.setQuantidade_Disponivel(rs.getInt("Quantidade_Disponivel"));

			}

			else{
				JOptionPane.showMessageDialog(null, "N�o Encontrado","Aten��o",JOptionPane.CANCEL_OPTION);
			}

			ps.close();
			rs.close();
		}


		catch(Exception e){
			throw new LivroDaoException("Erro na consulta do livro:"+livro.getTitulo());
		}

		return livroConsultado;
	}



	public Livro consultaIDQuantidadeSubtrai(int l) throws LivroDaoException{
		Livro livroConsultado=new Livro();

		String sql="Select IDLivro, quantidade_disponivel from Livro where IDLivro = ?";


		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, l);
			ResultSet rs=ps.executeQuery();     //retornando um result set

			if(rs.next()){

				livroConsultado.setIDLivro(rs.getInt("IDLivro"));
				livroConsultado.setQuantidade_Disponivel(rs.getInt("Quantidade_Disponivel"));
			}else{
				JOptionPane.showMessageDialog(null, "N�o Encontrado");
			}



			ps.close();
			rs.close();
			AtualizaQuantidadeDisponivelSubtrai(livroConsultado);
		}


		catch(Exception e){
			throw new LivroDaoException("Erro no m�todo que buscar� informa��es para a subtra��o da quantidade de determinado livro dispon�vel");
		}


		return livroConsultado;
	}




	public Livro consultaIDQuantidadeSoma(int l) throws LivroDaoException{
		Livro livroConsultado=new Livro();

		String sql="Select IDLivro, quantidade_disponivel from Livro where IDLivro = ?";


		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, l);
			ResultSet rs=ps.executeQuery();     //retornando um result set

			if(rs.next()){

				livroConsultado.setIDLivro(rs.getInt("IDLivro"));
				livroConsultado.setQuantidade_Disponivel(rs.getInt("Quantidade_Disponivel"));
			}else{
				JOptionPane.showMessageDialog(null, "N�o Encontrado");
			}



			ps.close();
			rs.close();
			AtualizaQuantidadeDisponivelSoma(livroConsultado);
		}


		catch(Exception e){
			throw new LivroDaoException("Erro no m�todo que buscar� informa��es para a soma da quantidade de determinado livro dispon�vel");
		}

		return livroConsultado;
	}




	public void AtualizaQuantidadeDisponivelSubtrai(Livro livro) throws LivroDaoException{


		String sql = "UPDATE Livro SET  quantidade_disponivel = ? Where IDLivro = ?";
		try{
			PreparedStatement ps=c.prepareStatement(sql);


			ps.setInt(1, livro.getQuantidade_Disponivel()-1);
			ps.setInt(2,livro.getIDLivro());
			ps.execute();
			ps.close();


		}

		catch(Exception e){
			throw new LivroDaoException("Erro no m�todo que subtrairia a quantidade de determinado livro dispon�vel");
		}

	}



	public void AtualizaQuantidadeDisponivelSoma(Livro livro) throws LivroDaoException{


		String sql = "UPDATE Livro SET  quantidade_disponivel = ? Where IDLivro = ?";
		try{
			PreparedStatement ps=c.prepareStatement(sql);


			ps.setInt(1, livro.getQuantidade_Disponivel()+1);
			ps.setInt(2,livro.getIDLivro());
			ps.execute();
			ps.close();


		}

		catch(Exception e){
			throw new LivroDaoException("Erro somaria a quantidade de determinado livro dispon�vel");
		}

	}


	public boolean buscaQuantidade(int l) throws LivroDaoException{

		boolean q_disponivel=true;
		Livro consultaQuantidade = new Livro();

		String sql="Select quantidade_disponivel from Livro where IDLivro = ?";

		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, l);
			ResultSet rs=ps.executeQuery();     //retornando um result set

			if(rs.next()){
				consultaQuantidade.setQuantidade_Disponivel(rs.getInt("Quantidade_disponivel"));
			}

			if(consultaQuantidade.getQuantidade_Disponivel()==0){
				q_disponivel=false;	
			}


			ps.close();
			rs.close();
		}

		catch(Exception e){
			throw new LivroDaoException("Erro no m�todo que impede que determinado livro passe a ter o valor de sua quantidade dispon�vel negativo");
		}


		return q_disponivel; 
	}

	public List<Livro> ListaDeLivros() throws LivroDaoException{

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
			throw new LivroDaoException("Erro na gera��o da lista de livros");
		}


		return listaLivro;
	}

}

