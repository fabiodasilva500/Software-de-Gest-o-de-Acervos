package Dao;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


import Entidades.Emprestimo;
import Entidades.Livro;
import Entidades.Usuario;

public class EmprestimoDao implements IEmprestimoDao{
	JTDSUtil gDao=new JTDSUtil();
	Connection c = gDao.getConnection();



	public boolean InsereEmprestimo(Emprestimo emprestimo) throws EmprestimoDaoException{
		boolean inserido=false;

		LivroDao livro=new LivroDao();
		int l=emprestimo.getIDLivro();

		try {
			livro.buscaQuantidade(l);
		} catch (LivroDaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		UsuarioDao usuario=new UsuarioDao();
		String u=emprestimo.getIDUsuario();
		String reserva=emprestimo.getReserva();

		try {
			usuario.buscaQuantidade(u, reserva);
		} catch (UsuarioDaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		try {
			if ((livro.buscaQuantidade(l)==false)&&(usuario.buscaQuantidade(u,reserva)==false)){
				JOptionPane.showMessageDialog(null,"O livro desejado n�o est� dispon�vel e o usu�rio est� com a quantidade m�xima de livros permitida","Aten��o",JOptionPane.CANCEL_OPTION);
			}

			else

				if(livro.buscaQuantidade(l)==false){
					JOptionPane.showMessageDialog(null,"O livro desejado n�o encontra-se dispon�vel","Aten��o",JOptionPane.CANCEL_OPTION);
				}

				else


					if(usuario.buscaQuantidade(u,reserva)==false){
						JOptionPane.showMessageDialog(null,"O usu�rio cadastrado encontra-se com a quantidade m�xima\nde livros permitida","Aten��o",JOptionPane.CANCEL_OPTION);
					}




					else

						if ((livro.buscaQuantidade(l)==true)&&(usuario.buscaQuantidade(u,reserva)==true)){


							String sql="INSERT INTO Emprestimo VALUES (?,?,?,?,?)";   

							try{


								PreparedStatement ps=c.prepareStatement(sql);
								ps.setInt(1, emprestimo.getIDLivro());
								ps.setString(2, emprestimo.getIDUsuario());
								ps.setString(3, emprestimo.getData());
								ps.setString(4,emprestimo.getStatus());
								ps.setString(5, emprestimo.getReserva());


								int i=emprestimo.getIDLivro();


								ps.execute();
								ps.close();
								inserido=true;
							}

							catch(Exception e){
								throw new EmprestimoDaoException("Erro na inser��o do empr�stimo:"+emprestimo.getIDEmprestimo());

							}
						}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LivroDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inserido;
	}


	/*
	 * 
	 * 
	 * 
	 */

	public boolean AtualizarEmprestimo(Emprestimo emprestimo) throws EmprestimoDaoException{
		boolean atualizado=false;

		String sql = "UPDATE emprestimo SET  data = ?" +
				" Where IDEmprestimo = ?";

		try{

			//Realizando o pr� processamento
			//seta-se o sql e em seguida s�o procurados os atributos que s�o setados, ou seja, que ter�o valores
			//para serem inseridos


			//O Statement ja busca os valores diretamente no SQL Server

			PreparedStatement ps=c.prepareStatement(sql);

			ps.setString(1, emprestimo.getData());
			ps.setInt(2, emprestimo.getIDEmprestimo());

			//Executando a conex�o
			//Finalizando a conex�o
			//Atribuindo true a vari�vel inserido 
			ps.execute();
			ps.close();
			atualizado=true;
		}

		catch(Exception e){
			throw new EmprestimoDaoException("Erro na atualiza��o do empr�stimo:"+emprestimo.getIDEmprestimo());

		}

		return atualizado;
	}



	public boolean AtualizaStatusEmprestimo(Emprestimo emprestimo) throws EmprestimoDaoException{
		boolean atualizado=false;

		String sql = "UPDATE emprestimo SET  statusEmprestimo = ?" +
				" Where IDEmprestimo = ?";

		try{

			PreparedStatement ps=c.prepareStatement(sql);

			ps.setString(1, emprestimo.getStatus());
			ps.setInt(2, emprestimo.getIDEmprestimo());

			ps.execute();
			ps.close();
			atualizado=true;
		}

		catch(Exception e){
			throw new EmprestimoDaoException("Erro na atualiza��o do status do empr�stimo:"+emprestimo.getIDEmprestimo());

		}


		return atualizado;
	}


	/*
	 * 
	 * 
	 * 
	 */

	public boolean excluirEmprestimo(Emprestimo emprestimo) throws EmprestimoDaoException{


		boolean excluido=false;

		String sql="DELETE Emprestimo Where IDEmprestimo = ?";

		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, emprestimo.getIDEmprestimo());
			ps.execute();
			ps.close();
			excluido=true;
		}


		catch(Exception e){
			throw new EmprestimoDaoException("Erro na exclus�o do empr�stimo:"+emprestimo.getIDEmprestimo());

		}
		return excluido;
	}



	/*
	 * 
	 * 
	 * 
	 */

	public Emprestimo consultaEmprestimo(Emprestimo emprestimo) throws EmprestimoDaoException{
		Emprestimo consultaEmprestimo = new Emprestimo();

		String sql="Select IDEmprestimo, IDLivro, IDUsuario, data, Statusemprestimo, reserva from emprestimo where IDEmprestimo = ?";


		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, emprestimo.getIDEmprestimo());
			ResultSet rs=ps.executeQuery();     //retornando um result set

			if(rs.next()){

				consultaEmprestimo.setIDEmprestimo(emprestimo.getIDEmprestimo());
				consultaEmprestimo.setIDLivro(rs.getInt("IDLivro"));
				consultaEmprestimo.setIDUsuario(rs.getString("IDUsuario"));
				consultaEmprestimo.setData(rs.getString("data"));
				consultaEmprestimo.setStatus(rs.getString("statusemprestimo"));
				consultaEmprestimo.setReserva(rs.getString("Reserva"));

			}else{
				JOptionPane.showMessageDialog(null, "N�o Encontrado");
			}


			ps.close();
			rs.close();
		}

		catch(Exception e){
			throw new EmprestimoDaoException("Erro na consulta do empr�stimo:"+emprestimo.getIDEmprestimo());
		}

		return consultaEmprestimo;
	}


	public Emprestimo consultaIDQuantidadeSoma(int l) throws EmprestimoDaoException{
		Emprestimo emprestimoConsultado=new Emprestimo();

		String sql="Select IDLivro  from Emprestimo where IDEmprestimo = ?";


		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, l);
			ResultSet rs=ps.executeQuery();     //retornando um result set

			if(rs.next()){
				emprestimoConsultado.setIDLivro(rs.getInt("IDLivro"));

			}

			ps.close();
			rs.close();
			LivroDao livro=new LivroDao();

			int ID=emprestimoConsultado.getIDLivro();
			livro.consultaIDQuantidadeSoma(ID);
		}


		catch(Exception e){
			throw new EmprestimoDaoException("Erro na rotina que realizaria a tentativa de atualiza��o posterior da quantidade do livro dispon�vel ap�s o cadastro de uma devolu��o");
		}


		return emprestimoConsultado;
	}



	public Emprestimo consultaIDQuantidadeSubtrai(int l) throws EmprestimoDaoException{
		Emprestimo emprestimoConsultado=new Emprestimo();

		String sql="Select IDLivro  from Emprestimo where IDEmprestimo = ?";


		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, l);
			ResultSet rs=ps.executeQuery();     //retornando um result set

			if(rs.next()){
				emprestimoConsultado.setIDLivro(rs.getInt("IDLivro"));

			}

			ps.close();
			rs.close();
			LivroDao livro=new LivroDao();

			int ID=emprestimoConsultado.getIDLivro();
			livro.consultaIDQuantidadeSubtrai(ID);
		}


		catch(Exception e){
			throw new EmprestimoDaoException("Erro na rotina que realizaria a tentativa de atualiza��o posterior da quantidade do livro dispon�vel ap�s a exclus�o de uma devolu��o");
		}



		return emprestimoConsultado;
	}



	public Emprestimo consultaQuantidadeSoma(int u) throws EmprestimoDaoException{
		Emprestimo emprestimoConsultado=new Emprestimo();

		String sql="Select IDUsuario  from Emprestimo where IDEmprestimo = ?";


		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, u);
			ResultSet rs=ps.executeQuery();     //retornando um result set

			if(rs.next()){
				emprestimoConsultado.setIDUsuario(rs.getString("IDUsuario"));

			}

			ps.close();
			rs.close();
			UsuarioDao usuario=new UsuarioDao();

			String ID=emprestimoConsultado.getIDUsuario();
			usuario.consultaIDQuantidadeSoma(ID);
		}


		catch(Exception e){
			throw new EmprestimoDaoException("Erro na rotina que realizaria a tentativa de atualiza��o posterior da quantidade de livros em posse do usu�rio ap�s a exclus�o de uma devolu��o");
		}

		return emprestimoConsultado;
	}




	public Emprestimo consultaQuantidadeSubtrai(int u) throws EmprestimoDaoException{
		Emprestimo emprestimoConsultado=new Emprestimo();

		String sql="Select IDUsuario  from Emprestimo where IDEmprestimo = ?";


		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, u);
			ResultSet rs=ps.executeQuery();     //retornando um result set

			if(rs.next()){
				emprestimoConsultado.setIDUsuario(rs.getString("IDUsuario"));

			}

			ps.close();
			rs.close();
			UsuarioDao usuario=new UsuarioDao();

			String ID=emprestimoConsultado.getIDUsuario();
			usuario.consultaIDQuantidadeSubtrai(ID);
		}


		catch(Exception e){
			throw new EmprestimoDaoException("Erro na rotina que realizaria a tentativa de atualiza��o posterior da quantidade de livros em posse do usu�rio ap�s o cadastro de uma devolu��o");
		}


		return emprestimoConsultado;
	}



	public Emprestimo consultaStatus(int emp) throws EmprestimoDaoException{


		Emprestimo emprestimoConsultado=new Emprestimo();

		String sql="Select Statusemprestimo  from Emprestimo where IDEmprestimo = ?";

		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, emp);
			ResultSet rs=ps.executeQuery();     //retornando um result set

			if(rs.next()){
				emprestimoConsultado.setStatus(rs.getString("StatusEmprestimo"));
			}

			ps.close();
			rs.close();				
		}


		catch(Exception e){
			throw new EmprestimoDaoException("Erro na consulta do status do empr�stimo");
		}



		return emprestimoConsultado;

	}


	public List<Livro> ListaDeLivros() throws EmprestimoDaoException{

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
			throw new EmprestimoDaoException("Erro na gera��o da lista de livros");
		}


		return listaLivro;
	}



	public List<Usuario> ListaDeUsuarios() throws EmprestimoDaoException{

		List<Usuario> listaUsuario=new ArrayList<Usuario>();

		String sql="select IDUsuario, nome from Usuario";

		try{

			PreparedStatement ps= c.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();


			while(rs.next()){
				Usuario usuario=new Usuario();
				usuario.setIdentificacao(rs.getString("IDUsuario"));
				usuario.setNome(rs.getString("Nome"));
				listaUsuario.add(usuario);
			}
		}

		catch(Exception e){
			throw new EmprestimoDaoException("Erro na gera��o da lista de usu�rios");
		}

		return listaUsuario;
	}


	public List<Emprestimo> ListaDeEmprestimos() throws EmprestimoDaoException{

		List<Emprestimo> listaEmprestimo=new ArrayList<Emprestimo>();

		//String sql="select IDEmprestimo, IDUsuario, IDLivro from Emprestimo";
		String sql="Select IDEmprestimo, IDLivro, IDUsuario, Statusemprestimo from emprestimo where Statusemprestimo like ? order by IDEmprestimo";

		try{

			PreparedStatement ps= c.prepareStatement(sql);
			ps.setString(1, "Em andamento");
			ResultSet rs=ps.executeQuery();

			while(rs.next()){
				Emprestimo emprestimo=new Emprestimo();
				emprestimo.setIDEmprestimo(rs.getInt("IDEmprestimo"));
				emprestimo.setIDLivro(rs.getInt("IDLivro"));
				emprestimo.setIDUsuario(rs.getString("IDUsuario"));
				emprestimo.setStatus(rs.getString("StatusEmprestimo"));


				listaEmprestimo.add(emprestimo);
			}
		}

		catch(Exception e){
			throw new EmprestimoDaoException("Erro na gera��o da lista de empr�stimos");
		}

		return listaEmprestimo;
	}



	public List<String> geraLista() throws EmprestimoDaoException{

		List<String> listaEmprestimos= new ArrayList<String>();

		String sql = "select Emprestimo.IDEmprestimo, Usuario.nome, Livro.titulo, Livro.edicao " +
				"from Usuario " +
				"inner Join Emprestimo " +
				"on Emprestimo.IDUsuario=Usuario.IDUsuario " +
				"inner join Livro "+
				"on Livro.IDLivro=Emprestimo.IDLivro "+
				"where Emprestimo.Statusemprestimo like ? ";


		try{
			PreparedStatement ps= c.prepareStatement(sql);
			ps.setString(1, "Em andamento");
			ResultSet rs=ps.executeQuery();

			while(rs.next()){

				//Emprestimo emprestimo=new Emprestimo();  
				Emprestimo emp=new Emprestimo();
				emp.setIDEmprestimo(rs.getInt("IDEmprestimo"));
				Usuario usuario=new Usuario();
				usuario.setNome(rs.getString("Nome"));
				Livro livro=new Livro();
				livro.setTitulo(rs.getString("Titulo"));
				livro.setEdicao(rs.getString("Edicao"));

				//listaNome.add(String.valueOf(emprestimo.getIDEmprestimo()));
				listaEmprestimos.add("ID:"+String.valueOf(emp.getIDEmprestimo()+" Usu�rio:"+usuario.getNome()+" Livro:"+livro.getTitulo()+" Edi��o:"+livro.getEdicao()));

			}
		}


		catch(Exception e){
			throw new EmprestimoDaoException("Erro na gera��o da lista de empr�stimos em andamento");
		}

		return listaEmprestimos; 
	}


	public List<String> geraListaReservados() throws EmprestimoDaoException{

		List<String> listaReservas = new ArrayList<String>();

		String sql = "select Emprestimo.IDEmprestimo, Usuario.nome, Livro.titulo, Livro.edicao " +
				"from Usuario " +
				"inner Join Emprestimo " +
				"on Emprestimo.IDUsuario=Usuario.IDUsuario " +
				"inner join Livro "+
				"on Livro.IDLivro=Emprestimo.IDLivro "+
				"where Emprestimo.Statusemprestimo like ? ";


		try{
			PreparedStatement ps= c.prepareStatement(sql);
			ps.setString(1, "Reservado");
			ResultSet rs=ps.executeQuery();

			while(rs.next()){

				//Emprestimo emprestimo=new Emprestimo();  
				Emprestimo emp=new Emprestimo();
				emp.setIDEmprestimo(rs.getInt("IDEmprestimo"));
				Usuario usuario=new Usuario();
				usuario.setNome(rs.getString("Nome"));
				Livro livro=new Livro();
				livro.setTitulo(rs.getString("Titulo"));
				livro.setEdicao(rs.getString("Edicao"));


				//listaNome.add(String.valueOf(emprestimo.getIDEmprestimo()));
				listaReservas.add("ID:"+String.valueOf(emp.getIDEmprestimo()+" Usu�rio:"+usuario.getNome()+" Livro:"+livro.getTitulo()+" Edi��o:"+livro.getEdicao()));

			}
		}


		catch(Exception e){
			throw new EmprestimoDaoException("Erro na gera��o da lista de reservas cadastradas");
		}

		return listaReservas; 
	}


	public List<String> geraListaData(Emprestimo emprestimo) throws EmprestimoDaoException{

		List<String> listaEmprestimosData = new ArrayList<String>();

		String sql = "select Emprestimo.IDEmprestimo, Usuario.nome, Livro.titulo, Livro.edicao " +
				"from Usuario " +
				"inner Join Emprestimo " +
				"on Emprestimo.IDUsuario=Usuario.IDUsuario " +
				"inner join Livro "+
				"on Livro.IDLivro=Emprestimo.IDLivro "+
				"where Emprestimo.data = ? ";


		try{
			PreparedStatement ps= c.prepareStatement(sql);
			ps.setString(1, emprestimo.getData());
			ResultSet rs=ps.executeQuery();

			while(rs.next()){

				Emprestimo emp=new Emprestimo();  
				emp.setIDEmprestimo(rs.getInt("IDEmprestimo"));

				Usuario usuario=new Usuario();
				usuario.setNome(rs.getString("Nome"));

				Livro livro=new Livro();
				livro.setTitulo(rs.getString("Titulo"));
				livro.setEdicao(rs.getString("Edicao"));



				listaEmprestimosData.add("ID:"+emp.getIDEmprestimo()+" Usuario:"+usuario.getNome()+" T�tulo:"+livro.getTitulo()+" Edi��o:"+livro.getEdicao());
			}
		}


		catch(Exception e){
			throw new EmprestimoDaoException("Erro na gera��o da lista de empr�stimos por data");
		}

		return listaEmprestimosData; 
	}



	public List<String> livrosEmAtraso(Emprestimo emprestimo) throws EmprestimoDaoException{

		List<String> listaEmprestimosData = new ArrayList<String>();

		String sql = "select Usuario.nome, Usuario.email, Livro.titulo " +
				"from Usuario " +
				"inner Join Emprestimo " +
				"on Emprestimo.IDUsuario=Usuario.IDUsuario " +
				"inner join Livro "+
				"on Livro.IDLivro=Emprestimo.IDLivro "+
				"where Emprestimo.data = ? and statusEmprestimo = ? ";


		try{
			PreparedStatement ps= c.prepareStatement(sql);
			ps.setString(1, emprestimo.getData());
			ps.setString(2, "Em andamento");
			ResultSet rs=ps.executeQuery();

			while(rs.next()){

				Usuario usuario=new Usuario();
				usuario.setNome(rs.getString("Nome"));

				usuario.setEmail(rs.getString("Email"));

				Livro livro=new Livro();
				livro.setTitulo(rs.getString("Titulo"));

				listaEmprestimosData.add("Usuario: "+usuario.getNome()+" Email:"+usuario.getEmail() +" T�tulo: "+livro.getTitulo());
			}
		}


		catch(Exception e){
			throw new EmprestimoDaoException("Erro na gera��o da lista de empr�stimos em atraso");
		}

		return listaEmprestimosData; 
	}


	public List<String> listaDeEmailsEmAtraso(Emprestimo emprestimo) throws EmprestimoDaoException{

		List<String> listaEmprestimosData = new ArrayList<String>();

		String sql = "select Usuario.email " +
				"from Usuario " +
				"inner Join Emprestimo " +
				"on Emprestimo.IDUsuario=Usuario.IDUsuario " +
				"inner join Livro "+
				"on Livro.IDLivro=Emprestimo.IDLivro "+
				"where Emprestimo.data = ? and statusEmprestimo = ? ";


		try{
			PreparedStatement ps= c.prepareStatement(sql);
			ps.setString(1, emprestimo.getData());
			ps.setString(2, "Em andamento");
			ResultSet rs=ps.executeQuery();

			while(rs.next()){

				Usuario usuario=new Usuario();				
				usuario.setEmail(rs.getString("Email"));


				listaEmprestimosData.add(usuario.getEmail());
			}
		}


		catch(Exception e){
			throw new EmprestimoDaoException("Erro na gera��o da lista de emails de empr�stimos em atraso");
		}

		return listaEmprestimosData; 
	}


}
