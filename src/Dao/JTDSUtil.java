package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JTDSUtil {
	private static Connection con;


	public static Connection getConnection(){

		try{
			String driver = "net.sourceforge.jtds.jdbc.Driver";   //especificando o driver que ser� usado

			String endereco = "jdbc:jtds:sqlserver://127.0.0.1:1433;";     //especificando o IP e a porta do pc em que ocorrer� a conex�o
			String database = "DatabaseName=Projeto;";

			String pipeNomeado = "named=true";     //especificando a conex�o direta com o banco, somente v�lida para conex�es locais e n�o usada em servidores

			String user="sa";

			String senha="1234";

			StringBuffer sb=new StringBuffer();

			sb.append(endereco);                       //adicionando a StringBuffer o endere�o em que ocorrer� a conex�o com o banco
			sb.append(database);
			sb.append(pipeNomeado);                    //adicionando a StringBuffer a String que determina que a conex�o com o banco ocorrer� de forma direta
			sb.append(database);  
			Class.forName(driver);                     //setando o driver, caso ele seja encontrado ser�o passados os par�metros para  a realiza��o da conex�o 

			con=DriverManager.getConnection(sb.toString(),user,senha);  

			//System.out.println("Conex�o Ok");

		}catch(ClassNotFoundException e){         //verificando se h� erros com a classe para a realiza��o da conex�o com o banco
			e.printStackTrace();

		}


		catch(Exception e){                     //Verificando se h� algum outro erro qualquer
			e.printStackTrace();
		}

		return con;
	}

}
