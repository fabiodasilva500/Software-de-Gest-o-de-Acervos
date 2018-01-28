package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Entidades.Usuario;

public class UsuarioDao implements IUsuarioDao {
	JTDSUtil gDao=new JTDSUtil();
	Connection c = gDao.getConnection();

	public boolean InsereUsuario(Usuario usuario) throws UsuarioDaoException{
		boolean inserido=false;

		String sql="INSERT INTO Usuario VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";   //Realizando uma pré compilação SQL dentro do java

		try{

			//Realizando o pré processamento
			//seta-se o sql e em seguida são procurados os atributos que são setados, ou seja, que terão valores
			//para serem inseridos


			//O Statement ja busca os valores diretamente no SQL Server

			PreparedStatement ps=c.prepareStatement(sql);
			ps.setString(1, usuario.getIdentificacao());
			ps.setString(2, usuario.getNome());
			ps.setString(3, usuario.getRG());
			ps.setString(4, usuario.getData_Nasc());
			ps.setString(5,usuario.getEndereco());
			ps.setInt(6, usuario.getNumero());
			ps.setString(7, usuario.getBairro());
			ps.setString(8, usuario.getCep());
			ps.setString(9, usuario.getCidade());
			ps.setString(10, usuario.getUF());
			ps.setString(11, usuario.getEmail());
			ps.setString(12, usuario.getTelefone());
			ps.setInt(13, usuario.getQuantidade());
			ps.setString(14, usuario.getCategoria());

			//Executando a conexão
			//Finalizando a conexão
			//Atribuindo true a variável inserido 
			ps.execute();
			ps.close();
			inserido=true;
		}

		catch(Exception e){
			throw new UsuarioDaoException("Erro na inserção do usuário:"+usuario.getIdentificacao());
		}

		return inserido;
	}

	/*
	 * 
	 * 
	 * 
	 */

	public boolean AtualizarUsuario(Usuario usuario) throws UsuarioDaoException{
		boolean atualizado=false;

		String sql = "UPDATE Usuario SET   nome = ?, rg = ?, data_nasc = ?,endereco = ?, numero = ?, bairro = ?, cep = ?, cidade = ?,uf = ?, email = ? , telefone = ? Where IDUsuario = ?";

		try{

			//Realizando o pré processamento
			//seta-se o sql e em seguida são procurados os atributos que são setados, ou seja, que terão valores
			//para serem inseridos


			//O Statement ja busca os valores diretamente no SQL Server

			PreparedStatement ps=c.prepareStatement(sql);
			//ps.setString(13, usuario.getIdentificacao());
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getRG());
			ps.setString(3, usuario.getData_Nasc());
			ps.setString(4,usuario.getEndereco());
			ps.setInt(5, usuario.getNumero());
			ps.setString(6, usuario.getBairro());
			ps.setString(7, usuario.getCep());
			ps.setString(8, usuario.getCidade());
			ps.setString(9, usuario.getUF());
			ps.setString(10, usuario.getEmail());
			ps.setString(11, usuario.getTelefone());
			ps.setString(12, usuario.getIdentificacao());

			//Executando a conexão
			//Finalizando a conexão
			//Atribuindo true a variável inserido 
			ps.execute();
			ps.close();
			atualizado=true;
		}

		catch(Exception e){
			throw new UsuarioDaoException("Erro na atualização do usuário:"+usuario.getIdentificacao());
		}

		return atualizado;
	}

	/*
	 * 
	 * 
	 * 
	 */

	public boolean excluirUsuario(Usuario usuario) throws UsuarioDaoException{


		boolean excluido=false;

		String sql="DELETE Usuario Where IDUsuario = ?";

		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, usuario.getIdentificacao());
			ps.execute();
			ps.close();
			excluido=true;
		}

		catch(Exception e){
			throw new UsuarioDaoException("Erro na exclusão do usuário:"+usuario.getIdentificacao());
		}

		return excluido;
	}



	/*
	 * 
	 * 
	 * 
	 */

	public Usuario consultaUsuario(Usuario usuario) throws UsuarioDaoException{
		Usuario usuarioConsultado = new Usuario();

		String sql="Select IDUsuario, nome, rg, data_nasc, endereco, numero, bairro, cep, cidade, uf, email, telefone, quantidade_livros_posse from Usuario where IDUsuario = ?";


		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, usuario.getIdentificacao());
			ResultSet rs=ps.executeQuery();     //retornando um result set

			if(rs.next()){

				usuarioConsultado.setIdentificacao(usuario.getIdentificacao());
				usuarioConsultado.setNome(rs.getString("nome"));
				usuarioConsultado.setRG(rs.getString("RG"));
				usuarioConsultado.setData_Nasc(rs.getString("data_nasc"));
				usuarioConsultado.setEndereco(rs.getString("endereco"));
				usuarioConsultado.setNumero(rs.getInt("numero"));
				usuarioConsultado.setBairro(rs.getString("bairro"));
				usuarioConsultado.setCep(rs.getString("cep"));
				usuarioConsultado.setCidade(rs.getString("cidade"));
				usuarioConsultado.setUF(rs.getString("uf"));
				usuarioConsultado.setEmail(rs.getString("email"));
				usuarioConsultado.setTelefone(rs.getString("telefone"));
				usuarioConsultado.setQuantidade(rs.getInt("Quantidade_Livros_Posse"));
				usuarioConsultado.setIdentificacao(rs.getString("IDUsuario"));

			}else{
				JOptionPane.showMessageDialog(null, "Não Encontrado", "Atenção",JOptionPane.CANCEL_OPTION);
			}

			ps.close();
			rs.close();
		}

		catch(Exception e){
			throw new UsuarioDaoException("Erro na consulta do usuário:"+usuario.getIdentificacao());
		}
		return usuarioConsultado;
	}



	public Usuario consultaIDQuantidadeSubtrai(String u) throws UsuarioDaoException{
		Usuario usuarioConsultado=new Usuario();

		String sql="Select IDUsuario, quantidade_livros_posse from Usuario where IDUsuario = ?";


		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, u);
			ResultSet rs=ps.executeQuery();     //retornando um result set

			if(rs.next()){

				usuarioConsultado.setIdentificacao(rs.getString("IDUsuario"));
				usuarioConsultado.setQuantidade(rs.getInt("Quantidade_Livros_Posse"));
			}else{
				JOptionPane.showMessageDialog(null, "Não Encontrado");
			}



			ps.close();
			rs.close();
			AtualizaQuantidadeDisponivelSubtrai(usuarioConsultado);
		}


		catch(Exception e){
			throw new UsuarioDaoException("Erro no método que buscará dados para a subtração da quantidade de livros que o usuário encontra-se em posse");
		}

		return usuarioConsultado;
	}




	public Usuario consultaIDQuantidadeSoma(String u) throws UsuarioDaoException{
		Usuario usuarioConsultado=new Usuario();

		String sql="Select IDUsuario, quantidade_livros_posse from Usuario where IDUsuario = ?";


		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, u);
			ResultSet rs=ps.executeQuery();     //retornando um result set

			if(rs.next()){

				usuarioConsultado.setIdentificacao(rs.getString("IDUsuario"));
				usuarioConsultado.setQuantidade(rs.getInt("Quantidade_Livros_Posse"));
			}else{
				JOptionPane.showMessageDialog(null, "Não Encontrado");
			}



			ps.close();
			rs.close();
			AtualizaQuantidadeDisponivelSoma(usuarioConsultado);
		}



		catch(Exception e){
			throw new UsuarioDaoException("Erro no método que buscará dados para a soma da quantidade de livros que o usuário encontra-se em posse");
		}

		return usuarioConsultado;
	}



	public void AtualizaQuantidadeDisponivelSubtrai(Usuario usuario) throws UsuarioDaoException{


		String sql = "UPDATE Usuario SET  quantidade_livros_posse = ? Where IDUsuario = ?";
		try{
			PreparedStatement ps=c.prepareStatement(sql);

			ps.setInt(1, usuario.getQuantidade()-1);
			ps.setString(2,usuario.getIdentificacao());
			ps.execute();
			ps.close();


		}


		catch(Exception e){
			throw new UsuarioDaoException("Erro no método que a subtraíria a quantidade de livros que o usuário encontra-se em posse");
		}

	}




	public void AtualizaQuantidadeDisponivelSoma(Usuario usuario) throws UsuarioDaoException{


		String sql = "UPDATE Usuario SET  quantidade_livros_posse = ? Where IDUsuario = ?";
		try{
			PreparedStatement ps=c.prepareStatement(sql);

			ps.setInt(1, usuario.getQuantidade()+1);
			ps.setString(2,usuario.getIdentificacao());
			ps.execute();
			ps.close();


		}

		catch(Exception e){
			throw new UsuarioDaoException("Erro no método que a somaria a quantidade de livros que o usuário encontra-se em posse");
		}


	}




	public boolean buscaQuantidade(String u, String reserva) throws UsuarioDaoException{

		boolean q_livros_posse=true;
		Usuario consultaQuantidade = new Usuario();

		String sql="Select quantidade_livros_posse,  IDUsuario from Usuario  where IDUsuario = ?";

		try{

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, u);
			ResultSet rs=ps.executeQuery();     //retornando um result set

			if(rs.next()){
				consultaQuantidade.setQuantidade(rs.getInt("Quantidade_Livros_Posse"));
				consultaQuantidade.setIdentificacao(rs.getString("IDUsuario"));
			}

			if(reserva=="Sim"){
				q_livros_posse=true;
			}
			else
				if((consultaQuantidade.getIdentificacao().length()==7) && (consultaQuantidade.getQuantidade()==2)){
					q_livros_posse=false;	
				}

				else
					if((consultaQuantidade.getIdentificacao().length()==9) && (consultaQuantidade.getQuantidade()==2)){
						q_livros_posse=false;	
					}
					else
						if((consultaQuantidade.getIdentificacao().length()==10) && (consultaQuantidade.getQuantidade()==10)){
							q_livros_posse=false;	
						}


			ps.close();
			rs.close();
		}

		catch(Exception e){
			throw new UsuarioDaoException("Erro no método que impede o empréstimo que professores tenham  10 livros em posse e para alunos tenham 2");
		}


		return q_livros_posse; 
	}

}
