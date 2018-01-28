package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButton;

import Controller.ControllerAutor;
import Dao.AutorDao;
import Dao.AutorDaoException;
import Entidades.Autor;

import javax.swing.JSeparator;
import javax.swing.JComboBox;

public class AutorView extends JFrame implements ControllerAutor{

	private JPanel contentPane;
	private JTextField tfIdAutor;
	private JTextField tfNome;

	private  final  JComboBox cbAutor = new JComboBox();
	private final JRadioButton rdbtnConsultarNome = new JRadioButton("Consultar por Nome");


	private void verificaInputID(KeyEvent key){
		char sub = key.getKeyChar();		
		if(Character.isDigit(sub) == false){
			key.consume();			
		}
	}
	private void verificaInputNome(KeyEvent key){
		char sub = key.getKeyChar();		
		if(Character.isDigit(sub) == true){
			key.consume();			
		}
		if(tfNome.getText().length() >= 70){
			key.consume();			
		}
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutorView frame = new AutorView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AutorView() {
		super("Manutenção do Cadastro de Autores");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(138, 320, 89, 23);
		contentPane.add(btnAlterar);

		final JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(21, 320, 89, 23);
		contentPane.add(btnAdicionar);

		final JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent arg0) {
			}
		});

		btnRemover.setBounds(250, 320, 89, 23);
		contentPane.add(btnRemover);

		final JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(371, 320, 89, 23);
		contentPane.add(btnConsultar);

		tfIdAutor = new JTextField();
		tfIdAutor.setBounds(78, 32, 86, 20);
		contentPane.add(tfIdAutor);
		tfIdAutor.setColumns(10);
		tfIdAutor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputID(tecla);
			}
		});

		tfNome = new JTextField();
		tfNome.setBounds(78, 86, 382, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputNome(tecla);
			}
		});

		JLabel lblIdAutor = new JLabel("ID Autor");
		lblIdAutor.setBounds(10, 35, 46, 14);
		contentPane.add(lblIdAutor);

		JLabel lblNome = new JLabel("Nome*");
		lblNome.setBounds(10, 89, 46, 14);
		contentPane.add(lblNome);

		JRadioButton rdbtnCadastro = new JRadioButton("Cadastro");
		rdbtnCadastro.setBounds(21, 235, 89, 23);
		contentPane.add(rdbtnCadastro);

		final JRadioButton rdbtnEditar = new JRadioButton("Edi\u00E7\u00E3o");
		rdbtnEditar.setBounds(130, 235, 80, 23);
		contentPane.add(rdbtnEditar);

		final JRadioButton rdbtnExclusao = new JRadioButton("Exclus\u00E3o");
		rdbtnExclusao.setBounds(375, 235, 89, 23);
		contentPane.add(rdbtnExclusao);

		final JRadioButton rdbtnConsultarNome = new JRadioButton("Consultar por Nome");
		rdbtnConsultarNome.setBounds(211, 235, 142, 23);
		contentPane.add(rdbtnConsultarNome);


		cbAutor.setBounds(177, 32, 297, 20);
		contentPane.add(cbAutor);



		ButtonGroup GrupoRB = new ButtonGroup(); //usado para agrupar os radios buttons, quando é clicado em um ele desmarca os outros

		GrupoRB.add(rdbtnCadastro);
		GrupoRB.add(rdbtnEditar);
		GrupoRB.add(rdbtnConsultarNome);
		GrupoRB.add(rdbtnExclusao);


		JSeparator separator = new JSeparator();
		separator.setBounds(0, 176, 509, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 289, 519, 2);
		contentPane.add(separator_1);

		JLabel lblOpcoes = new JLabel("Menu de sele\u00E7\u00E3o");
		lblOpcoes.setBounds(10, 180, 121, 14);
		contentPane.add(lblOpcoes);

		JLabel lblCamposCom = new JLabel("Campos com * s\u00E3o obrigat\u00F3rios para inser\u00E7\u00E3o/altera\u00E7\u00E3o");
		lblCamposCom.setBounds(153, 176, 321, 14);
		contentPane.add(lblCamposCom);


		cbAutor.setVisible(false);
		btnConsultar.setVisible(false);
		btnAdicionar.setVisible(false);
		btnAlterar.setVisible(false);
		btnRemover.setVisible(false);

		desabilitaCampos();


		ActionListener rbCadastroListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cbAutor.setVisible(false);
				atualizaCBAutor();
				btnConsultar.setVisible(false);
				btnAdicionar.setVisible(true);
				btnAlterar.setVisible(false);
				btnRemover.setVisible(false);

				limpaCampo();
				habilitaCampos();
				tfIdAutor.setEnabled(false);
			}

		};


		ActionListener rbEditaoListener =new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				cbAutor.setVisible(true);
				atualizaCBAutor();
				btnConsultar.setVisible(true);
				btnAdicionar.setVisible(false);
				btnAlterar.setVisible(true);
				btnRemover.setVisible(false);

				limpaCampo();
				inicializaCampo();
				desabilitaCampos();
				tfIdAutor.setEnabled(true);

			}
		};


		ActionListener rbConsultaListenerNome=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				cbAutor.setVisible(true);
				atualizaCBAutor();
				btnConsultar.setVisible(true);
				btnAlterar.setVisible(false);
				btnRemover.setVisible(false);
				btnAdicionar.setVisible(false);

				limpaCampo();

				desabilitaCampos();
				tfNome.setEnabled(true);
			}

		};


		ActionListener rbExclusaoListener =new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				cbAutor.setVisible(true);

				btnConsultar.setVisible(true);
				btnAdicionar.setVisible(false);
				btnAlterar.setVisible(false);
				btnRemover.setVisible(true);

				limpaCampo();
				atualizaCBAutor();
				inicializaCampo();

				desabilitaCampos();
				tfIdAutor.setEnabled(true);
			}
		};

		rdbtnCadastro.addActionListener(rbCadastroListener);
		rdbtnEditar.addActionListener(rbEditaoListener);
		rdbtnConsultarNome.addActionListener(rbConsultaListenerNome);
		rdbtnExclusao.addActionListener(rbExclusaoListener);

		ActionListener cadastroListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Autor autor=new Autor();

				if(!tfNome.getText().isEmpty()){
					autor.setNome(tfNome.getText());

					if(inserirAutor(autor)){
						JOptionPane.showMessageDialog(null,"Autor inserido com sucesso",
								"Êxito",JOptionPane.INFORMATION_MESSAGE);
						limpaCampo();
						atualizaCBAutor();
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Autor não inserido, verifique os campos informados",
							"Atenção", JOptionPane.CANCEL_OPTION);
					limpaCampo();
				}

			}
		};


		ActionListener buscaListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Autor autorConsultado=new Autor();

				Autor autor =new Autor();
				if(!rdbtnConsultarNome.isSelected()){
					autor.setIDAutor(Integer.parseInt(tfIdAutor.getText()));
					autorConsultado=consultarAutorID(autor);
				}

				if(rdbtnConsultarNome.isSelected()){
					autor.setNome(tfNome.getText());
					autorConsultado=consultarAutorNome(autor);
				}


				tfIdAutor.setText(String.valueOf(autorConsultado.getIDAutor()));
				tfNome.setText(autorConsultado.getNome());

				if(rdbtnEditar.isSelected() &&(!tfNome.getText().isEmpty())){
					habilitaCampos();
					tfIdAutor.setEnabled(false);

				}


				if(rdbtnConsultarNome.isSelected() &&(!tfNome.getText().isEmpty())){
					desabilitaCampos();
				}
			}


		};


		ActionListener exclusaoListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Autor autor=new Autor();
				autor.setIDAutor(Integer.parseInt(tfIdAutor.getText()));

				if((!tfNome.getText().isEmpty())){

					boolean excluido = excluirAutor(autor);

					if(excluido){
						JOptionPane.showMessageDialog(null,"Autor Excluído com sucesso",
								"Êxito",JOptionPane.INFORMATION_MESSAGE);
						limpaCampo();
						inicializaCampo();
						atualizaCBAutor();
					}
					else{
						JOptionPane.showMessageDialog(null,"Autor não excluído","Atenção",JOptionPane.CANCEL_OPTION);
						limpaCampo();
						inicializaCampo();
					}

				}
				else{
					JOptionPane.showMessageDialog(null,"Autor não excluído, verifique os campos informados\nou se a busca foi realizada","Atenção",JOptionPane.CANCEL_OPTION);
					limpaCampo();
					inicializaCampo();
				}


			}

		};

		ActionListener edicaoListener =new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Autor autor=new Autor();

				if (!tfNome.getText().isEmpty()){
					tfNome.setEnabled(true);



					autor.setIDAutor(Integer.parseInt(tfIdAutor.getText()));

					autor.setNome(tfNome.getText());

					AutorDao dao=new AutorDao();

					boolean editado = atualizarAutor(autor);


					if(editado){
						JOptionPane.showMessageDialog(null,"Autor editado com sucesso","Êxito",JOptionPane.INFORMATION_MESSAGE);
						limpaCampo();
						atualizaCBAutor();
						preparaNovaPesquisaID();
						inicializaCampo();

					}

				}

				else{
					JOptionPane.showMessageDialog(null,"Autor não editado, verifique os campos informados\nou se a busca foi realizada.","Atenção",JOptionPane.CANCEL_OPTION);
					limpaCampo();
					inicializaCampo();
					preparaNovaPesquisaID();
					limpaCampo();
				}
			}
		};

		btnAdicionar.addActionListener(cadastroListener);
		btnConsultar.addActionListener(buscaListener);
		btnRemover.addActionListener(exclusaoListener);
		btnAlterar.addActionListener(edicaoListener);

	}


	public void limpaCampo(){
		tfIdAutor.setText("");
		tfNome.setText("");
	}




	public void atualizaCBAutor(){
		final AutorDao dao =new AutorDao();

		List<Autor> listaAutor = new ArrayList<Autor>();
		try {
			listaAutor=dao.ListaDeAutores();
		} catch (AutorDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(cbAutor!=null){
			cbAutor.removeAllItems();
		}

		for (Autor autor:listaAutor){
			cbAutor.addItem("ID:"+autor.getIDAutor()+" Nome:"+autor.getNome());
		}
	}



	public void preparaNovaPesquisaID(){
		tfIdAutor.setEnabled(true);
		tfNome.setEnabled(false);
	}


	public void habilitaCampos(){
		tfIdAutor.setEnabled(true);
		tfNome.setEnabled(true);

	}


	public void desabilitaCampos(){
		tfIdAutor.setEnabled(false);
		tfNome.setEnabled(false);

	}




	public void inicializaCampo(){
		tfIdAutor.setText("0");
	}
	@Override
	public boolean inserirAutor(Autor autor) {
		AutorDao dao=new AutorDao();
		boolean inserido = false;
		try {
			inserido = dao.InsereAutor(autor);
		} catch (AutorDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inserido;
	}


	@Override
	public boolean atualizarAutor(Autor autor) {
		boolean editado=false;
		AutorDao dao=new AutorDao();
		try {
			editado = dao.AtualizarAutor(autor);
		} catch (AutorDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return editado;
	}


	@Override
	public Autor consultarAutorID(Autor autor) {
		Autor autorConsultado=new Autor();
		AutorDao dao=new AutorDao();
		try {
			autorConsultado=dao.consultaAutorID(autor);
		} catch (AutorDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autorConsultado;
	}


	@Override
	public Autor consultarAutorNome(Autor autor) {
		Autor autorConsultado=new Autor();
		AutorDao dao=new AutorDao();
		try {
			autorConsultado=dao.consultaAutorNome(autor);
		} catch (AutorDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autorConsultado;
	}


	@Override
	public boolean excluirAutor(Autor autor) {
		boolean excluido=false;
		AutorDao dao=new AutorDao();
		try {
			excluido = dao.ExcluirAutor(autor);
		} catch (AutorDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excluido;

	}

}





