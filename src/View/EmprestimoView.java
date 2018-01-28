package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

import javax.swing.JSeparator;

import Controller.ControllerEmprestimo;
import Dao.EmprestimoDao;
import Dao.EmprestimoDaoException;
import Dao.LivroDao;
import Dao.LivroDaoException;
import Dao.UsuarioDao;
import Dao.UsuarioDaoException;
import Entidades.Emprestimo;
import Entidades.Livro;
import Entidades.Usuario;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;

public class EmprestimoView extends JFrame  implements ControllerEmprestimo{

	private JPanel contentPane;
	private JTextField tfIdUsuario;
	private JTextField tfIdLivro;
	private JTextField tfIdEmprestimo;
	private JTextField tfDataDevolucao;
	private final JComboBox cbLivro = new JComboBox();
	private final JComboBox cbUsuario = new JComboBox();
	private final JComboBox cbEmprestimo = new JComboBox();
	private final JComboBox cbStatus = new JComboBox();
	private  final JComboBox cbReserva = new JComboBox();


	private void verificaInputIDUsuario(KeyEvent key){
		if(tfIdUsuario.getText().length() >= 10){
			key.consume();			
		}
	}
	private void verificaInputIDLivro(KeyEvent key){
		char sub = key.getKeyChar();		
		if(Character.isDigit(sub) == false){
			key.consume();			
		}
	}
	private void verificaInputIDEmprestimo(KeyEvent key){
		char sub = key.getKeyChar();		
		if(Character.isDigit(sub) == false){
			key.consume();			
		}
	}
	private void verificaInputDevolucao(KeyEvent key){
		char sub = key.getKeyChar();

		if(tfDataDevolucao.getText().length() >= 10){
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
					EmprestimoView frame = new EmprestimoView();
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
	public EmprestimoView() {
		super("Manutenção do Cadastro de Empréstimos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfIdUsuario = new JTextField();
		tfIdUsuario.setBounds(147, 112, 170, 20);
		contentPane.add(tfIdUsuario);
		tfIdUsuario.setColumns(10);
		tfIdUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputIDUsuario(tecla);
			}
		});

		tfIdLivro = new JTextField();
		tfIdLivro.setBounds(147, 81, 170, 20);
		contentPane.add(tfIdLivro);
		tfIdLivro.setColumns(10);
		tfIdLivro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputIDLivro(tecla);
			}
		});

		JLabel lblIdUsuario = new JLabel("ID Usu\u00E1rio*");
		lblIdUsuario.setBounds(17, 115, 70, 14);
		contentPane.add(lblIdUsuario);

		JLabel lblIdLivro = new JLabel("ID Livro*");
		lblIdLivro.setBounds(17, 77, 70, 14);
		contentPane.add(lblIdLivro);

		final JButton btnEfetuar = new JButton("Efetuar");
		btnEfetuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEfetuar.setBounds(86, 415, 89, 23);
		contentPane.add(btnEfetuar);

		final JButton btnRenovar = new JButton("Renovar");
		btnRenovar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRenovar.setBounds(310, 415, 89, 23);
		contentPane.add(btnRenovar);

		final JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExcluir.setBounds(505, 415, 89, 23);
		contentPane.add(btnExcluir);

		tfIdEmprestimo = new JTextField();
		tfIdEmprestimo.setBounds(147, 38, 170, 20);
		contentPane.add(tfIdEmprestimo);
		tfIdEmprestimo.setColumns(10);
		tfIdEmprestimo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputIDEmprestimo(tecla);
			}
		});

		JLabel lblIdEmprstimo = new JLabel("ID Empr\u00E9stimo");
		lblIdEmprstimo.setBounds(17, 41, 89, 14);
		contentPane.add(lblIdEmprstimo);

		tfDataDevolucao = new JTextField();
		tfDataDevolucao.setBounds(520, 169, 109, 20);
		contentPane.add(tfDataDevolucao);
		tfDataDevolucao.setColumns(10);
		tfDataDevolucao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputDevolucao(tecla);
			}
		});

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(17, 207, 46, 14);
		contentPane.add(lblStatus);

		final JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(681, 415, 89, 23);
		contentPane.add(btnConsultar);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 274, 884, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 402, 884, 2);
		contentPane.add(separator_1);

		JCheckBox checkBox = new JCheckBox("New check box");
		checkBox.setBounds(248, 109, -412, 239);
		contentPane.add(checkBox);


		cbLivro.setBounds(382, 74, 471, 20);
		contentPane.add(cbLivro);


		cbUsuario.setBounds(382, 112, 471, 20);
		contentPane.add(cbUsuario);

		JLabel lblOpcoes = new JLabel("Menu de Sele\u00E7\u00E3o");
		lblOpcoes.setBounds(17, 274, 120, 14);
		contentPane.add(lblOpcoes);

		JRadioButton rdbtnCadastro = new JRadioButton("Cadastro");
		rdbtnCadastro.setBounds(120, 326, 109, 23);
		contentPane.add(rdbtnCadastro);

		final JRadioButton rdbtnEditar = new JRadioButton("Consulta/Renova\u00E7\u00E3o");
		rdbtnEditar.setBounds(334, 325, 161, 23);
		contentPane.add(rdbtnEditar);

		final JRadioButton rdbtnExcluir = new JRadioButton("Exclus\u00E3o");
		rdbtnExcluir.setBounds(592, 326, 109, 23);
		contentPane.add(rdbtnExcluir);


		ButtonGroup GrupoRB = new ButtonGroup(); //usado para agrupar os radios buttons, quando é clicado em um ele desmarca os outros

		GrupoRB.add(rdbtnCadastro);
		GrupoRB.add(rdbtnExcluir);
		GrupoRB.add(rdbtnEditar);


		cbEmprestimo.setBounds(382, 38, 471, 20);
		contentPane.add(cbEmprestimo);


		cbStatus.setModel(new DefaultComboBoxModel(new String[] {"Em andamento", "Devolvido ", "Reservado"}));
		cbStatus.setBounds(147, 204, 170, 20);
		contentPane.add(cbStatus);


		atualizaCBLivro();
		atualizaCBUsuario();
		atualizaCBEmprestimo();
		desabilitaCampos();


		btnEfetuar.setVisible(false);
		btnRenovar.setVisible(false);
		btnExcluir.setVisible(false);
		btnConsultar.setVisible(false);

		cbStatus.setEnabled(false);

		JLabel lblDataDevolucao = new JLabel("Data de Devolu\u00E7\u00E3o");
		lblDataDevolucao.setBounds(382, 172, 113, 14);
		contentPane.add(lblDataDevolucao);

		JLabel lblMensagem = new JLabel("Campos com * s\u00E3o obrigat\u00F3rios para inser\u00E7\u00E3o/altera\u00E7\u00E3o");
		lblMensagem.setBounds(198, 274, 382, 14);
		contentPane.add(lblMensagem);

		JLabel lblVocEstReservando = new JLabel("Reservando");
		lblVocEstReservando.setBounds(17, 160, 113, 14);
		contentPane.add(lblVocEstReservando);

		cbReserva.setModel(new DefaultComboBoxModel(new String[] {"N\u00E3o", "Sim"}));
		cbReserva.setBounds(147, 157, 70, 20);
		contentPane.add(cbReserva);


		ActionListener rbCadastroListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				atualizaCBLivro();
				atualizaCBUsuario();
				btnEfetuar.setVisible(true);
				btnRenovar.setVisible(false);
				btnConsultar.setVisible(false);
				btnExcluir.setVisible(false);
				cbStatus.setModel(new DefaultComboBoxModel(new String[] {"Em andamento", "Devolvido "}));
				cbEmprestimo.setVisible(true);
				habilitaCampos();
				tfIdEmprestimo.setEnabled(false);
				limpaCampo();			
				tfDataDevolucao.setEnabled(false);

			}


		};



		ActionListener rbEditaoListener =new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				atualizaCBLivro();
				atualizaCBUsuario();
				cbStatus.setModel(new DefaultComboBoxModel(new String[] {"Em andamento", "Devolvido "}));
				cbEmprestimo.setVisible(true);
				tfDataDevolucao.setEnabled(false);

				btnEfetuar.setVisible(false);
				btnConsultar.setVisible(true);
				btnRenovar.setVisible(true);
				btnExcluir.setVisible(false);


				desabilitaCampos();
				tfIdEmprestimo.setEnabled(true);
				limpaCampo();		

			}
		};


		ActionListener rbExlusaoListener =new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				atualizaCBLivro();
				atualizaCBUsuario();

				btnEfetuar.setVisible(false);
				btnConsultar.setVisible(true);
				btnRenovar.setVisible(false);
				btnExcluir.setVisible(true);
				cbStatus.setModel(new DefaultComboBoxModel(new String[] {"Em andamento", "Devolvido "}));
				cbEmprestimo.setVisible(true);

				limpaCampo();		

				desabilitaCampos();
				tfIdEmprestimo.setEnabled(true);

			}
		};


		rdbtnCadastro.addActionListener(rbCadastroListener);
		rdbtnEditar.addActionListener(rbEditaoListener);
		rdbtnExcluir.addActionListener(rbExlusaoListener);

		ActionListener cadastroListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Emprestimo emprestimo = new Emprestimo();
				cbStatus.setEnabled(false);
				cbReserva.setEnabled(true);
				tfDataDevolucao.setEnabled(false);

				if((!tfIdLivro.getText().isEmpty() && !tfIdUsuario.getText().isEmpty()&&((tfIdUsuario.getText().length()==7)||(tfIdUsuario.getText().length()==9)||(tfIdUsuario.getText().length()==10)))){


					emprestimo.setIDLivro(Integer.parseInt(tfIdLivro.getText()));
					emprestimo.setIDUsuario(tfIdUsuario.getText());


					SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");   

					if(tfIdUsuario.getText().length()!=10){
						Calendar c = Calendar.getInstance(); 
						c.add(Calendar.DAY_OF_MONTH, 7);  
						tfDataDevolucao.setText(sd.format(c.getTime()));
						emprestimo.setData(tfDataDevolucao.getText());
					}

					else
						if(tfIdUsuario.getText().length()==10){
							Calendar c = Calendar.getInstance(); 
							c.add(Calendar.DAY_OF_MONTH, 15);  
							tfDataDevolucao.setText(sd.format(c.getTime()));
							emprestimo.setData(tfDataDevolucao.getText());
						}


					emprestimo.setStatus(cbStatus.getSelectedItem().toString());
					emprestimo.setReserva(cbReserva.getSelectedItem().toString());

					if(emprestimo.getData().length()==10){

						if(cbReserva.getSelectedItem().equals("Sim")){
							cbStatus.setSelectedItem("Reservado");
							emprestimo.setStatus("Reservado");    
						}

						boolean inserido = false;
						inserido=inserir(emprestimo);

						if(inserido){

							if(emprestimo.getReserva().equals("Não")){	
								JOptionPane.showMessageDialog(null,"Empréstimo inserido com Êxito",
										"Êxito",JOptionPane.INFORMATION_MESSAGE);
							}
							if(emprestimo.getReserva().equals("Sim")){	
								JOptionPane.showMessageDialog(null,"Reserva realizada com Êxito",
										"Êxito",JOptionPane.INFORMATION_MESSAGE);

							}

							Livro livro=new Livro();
							livro.setIDLivro(Integer.parseInt(tfIdLivro.getText()));

							int l=livro.getIDLivro();
							AtualizaQuantidadeDisponivelSubtrai(l);



							if(cbReserva.getSelectedItem().equals("Não")){

								Usuario usuario=new Usuario();
								usuario.setIdentificacao(tfIdUsuario.getText());

								String u=usuario.getIdentificacao();
								AtualizaQuantidadeUsuarioSoma(u);
							}

							atualizaCBLivro();
							atualizaCBUsuario();
							atualizaCBEmprestimo();
							limpaCampo();
						}
					}
					else{
						JOptionPane.showMessageDialog(null,"Data inválida",
								"Atenção", JOptionPane.CANCEL_OPTION);

					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Dados não inseridos, verifique os campos informados.",
							"Atenção", JOptionPane.CANCEL_OPTION);

				} 
			}
		};

		ActionListener edicaoListener =new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Emprestimo emprestimo = new Emprestimo();

				if(!tfIdLivro.getText().isEmpty() && !tfIdUsuario.getText().isEmpty()){

					emprestimo.setIDEmprestimo(Integer.parseInt(tfIdEmprestimo.getText()));



					SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");   

					if(tfIdUsuario.getText().length()!=10){
						Calendar c = Calendar.getInstance(); 
						c.add(Calendar.DAY_OF_MONTH, 7);  
						tfDataDevolucao.setText(sd.format(c.getTime()));
						emprestimo.setData(tfDataDevolucao.getText());
					}

					else
						if(tfIdUsuario.getText().length()==10){
							Calendar c = Calendar.getInstance(); 
							c.add(Calendar.DAY_OF_MONTH, 15);  
							tfDataDevolucao.setText(sd.format(c.getTime()));
							emprestimo.setData(tfDataDevolucao.getText());
						}


					boolean editado = false ;
					editado=renovar(emprestimo);

					if(editado){
						JOptionPane.showMessageDialog(null,"Data do empréstimo/reserva renovada com sucesso", 
								"Êxito",JOptionPane.INFORMATION_MESSAGE);

						atualizaCBLivro();
						atualizaCBUsuario();
						atualizaCBEmprestimo();
						limpaCampo();
						desabilitaCampos();
						tfIdEmprestimo.setEnabled(true);
					}
				}

				else{
					JOptionPane.showMessageDialog(null,"Data do empréstimo/reserva não renovada, verifique","Erro",JOptionPane.CANCEL_OPTION);   
					desabilitaCampos();
					tfIdEmprestimo.setEnabled(true);
				}
			}
		};


		ActionListener buscaListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Emprestimo emprestimo = new Emprestimo();
				EmprestimoDao dao = new EmprestimoDao(); 
				Emprestimo consulta = new Emprestimo();
				desabilitaCampos();
				tfIdEmprestimo.setEnabled(true);


				if(!tfIdEmprestimo.getText().isEmpty()){

					emprestimo.setIDEmprestimo(Integer.parseInt(tfIdEmprestimo.getText()));
					try {
						consulta=dao.consultaEmprestimo(emprestimo);
					} catch (EmprestimoDaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


					tfIdEmprestimo.setText(String.valueOf(consulta.getIDEmprestimo()));
					tfIdUsuario.setText(consulta.getIDUsuario());
					tfIdLivro.setText(String.valueOf(consulta.getIDLivro()));
					tfDataDevolucao.setText(consulta.getData());
					cbStatus.setSelectedItem(consulta.getStatus());
					cbReserva.setSelectedItem(consulta.getReserva());

					if(rdbtnEditar.isSelected() &&(!tfIdLivro.getText().isEmpty()) &&(!tfIdUsuario.getText().isEmpty())){
						desabilitaCampos();
						tfDataDevolucao.setEnabled(false);
						cbReserva.setEnabled(true);
						cbReserva.setEnabled(false);
						cbStatus.setEnabled(true);
						cbStatus.setEnabled(false);

					}


					if(rdbtnExcluir.isSelected() &&(!tfIdLivro.getText().isEmpty()) &&(!tfIdUsuario.getText().isEmpty())){
						desabilitaCampos();
						cbStatus.setEnabled(false);

					}


				}
				else{
					JOptionPane.showMessageDialog(null, "A busca não foi realizada","Atenção",JOptionPane.CANCEL_OPTION);
				}
			}
		};


		ActionListener exclusaoListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Emprestimo emprestimo = new Emprestimo();


				if(!tfIdEmprestimo.getText().isEmpty()){
					emprestimo.setIDEmprestimo(Integer.parseInt(tfIdEmprestimo.getText()));

					if((!tfIdEmprestimo.getText().isEmpty())&&(!tfIdLivro.getText().isEmpty()&&(!tfIdUsuario.getText().isEmpty() &&(!tfIdLivro.getText().isEmpty())))){

						boolean excluido = false;
						excluido=excluir(emprestimo);

						if(excluido){

							if(cbReserva.getSelectedItem().equals("Não")){
								JOptionPane.showMessageDialog(null,"Empréstimo Excluído com Êxito",
										"Êxito",JOptionPane.INFORMATION_MESSAGE);  
								tfIdEmprestimo.setEnabled(true);
							}

							if(cbReserva.getSelectedItem().equals("Sim")){
								JOptionPane.showMessageDialog(null,"Reserva Excluída com Êxito",
										"Êxito",JOptionPane.INFORMATION_MESSAGE);       	
								tfIdEmprestimo.setEnabled(true);
							}



							Livro livro=new Livro();
							livro.setIDLivro(Integer.parseInt(tfIdLivro.getText()));

							int l=livro.getIDLivro();
							AtualizaQuantidadeDisponivelSoma(l);

							if(cbReserva.getSelectedItem().equals("Não")){
								Usuario usuario=new Usuario();
								usuario.setIdentificacao(tfIdUsuario.getText());

								String u=usuario.getIdentificacao();
								AtualizaQuantidadeUsuarioSubtrai(u);
							}


							atualizaCBLivro();
							atualizaCBUsuario();
							atualizaCBEmprestimo();
							limpaCampo();

						}
						else{
							JOptionPane.showMessageDialog(null,"Dados não excluídos","Erro",JOptionPane.CANCEL_OPTION);
							limpaCampo();
							tfIdEmprestimo.setEnabled(true);
						}
					}
					else{
						JOptionPane.showMessageDialog(null,"Faça a busca","Erro",JOptionPane.CANCEL_OPTION);

					}
				}

				else{
					JOptionPane.showMessageDialog(null,"A busca não foi realizada","Atenção",JOptionPane.CANCEL_OPTION);
				}
			}
		};


		btnEfetuar.addActionListener(cadastroListener);
		btnRenovar.addActionListener(edicaoListener);
		btnConsultar.addActionListener(buscaListener);
		btnExcluir.addActionListener(exclusaoListener);
	}



	public void atualizaCBLivro(){

		final EmprestimoDao dao =new EmprestimoDao();

		List<Livro> listaLivro = new ArrayList<Livro>();
		try {
			listaLivro=dao.ListaDeLivros();
		} catch (EmprestimoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(cbLivro!=null){
			cbLivro.removeAllItems();
		}

		for (Livro livro:listaLivro){
			cbLivro.addItem("ID:"+livro.getIDLivro()+" Título:"+livro.getTitulo()+" "+livro.getEdicao());
		}
	}




	public void atualizaCBUsuario(){

		final EmprestimoDao dao =new EmprestimoDao();

		List<Usuario> listaUsuario = new ArrayList<Usuario>();

		try {
			listaUsuario=dao.ListaDeUsuarios();
		} catch (EmprestimoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(cbUsuario!=null){
			cbUsuario.removeAllItems();
		}

		for (Usuario usuario:listaUsuario){
			cbUsuario.addItem("Identificação:"+usuario.getIdentificacao()+" Nome:"+usuario.getNome());
		}
	}



	public void atualizaCBEmprestimo(){

		final EmprestimoDao dao =new EmprestimoDao();

		List<Emprestimo> listaEmprestimo = new ArrayList<Emprestimo>();

		try {
			listaEmprestimo=dao.ListaDeEmprestimos();
		} catch (EmprestimoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(cbEmprestimo!=null){

			cbEmprestimo.removeAllItems();
		}


		for (Emprestimo emprestimo:listaEmprestimo){
			cbEmprestimo.addItem("ID do Empréstimo:"+emprestimo.getIDEmprestimo()+" ID do Livro:"+emprestimo.getIDLivro()+" ID do Usuário:"+emprestimo.getIDUsuario()+"Status:"+emprestimo.getStatus());
		}
	}




	public void desabilitaCampos(){
		tfIdEmprestimo.setEnabled(false);
		tfIdLivro.setEnabled(false);
		tfIdUsuario.setEnabled(false);
		tfDataDevolucao.setEnabled(false);
		cbStatus.setEnabled(false);
		cbReserva.setEnabled(false);
	}


	public void habilitaCampos(){
		tfIdEmprestimo.setEnabled(true);
		tfIdLivro.setEnabled(true);
		tfIdUsuario.setEnabled(true);
		tfDataDevolucao.setEnabled(true);
		cbStatus.setEnabled(false);
		cbReserva.setEnabled(true);

	}



	public void limpaCampo(){
		tfIdEmprestimo.setText("");
		tfIdUsuario.setText("");
		tfIdLivro.setText("");
		tfDataDevolucao.setText("");
	}



	public void AtualizaQuantidadeDisponivelSubtrai(int l){
		LivroDao quant=new LivroDao();
		try {
			quant.consultaIDQuantidadeSubtrai(l);
		} catch (LivroDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void AtualizaQuantidadeUsuarioSubtrai(String u){
		UsuarioDao quant=new UsuarioDao();
		try {
			quant.consultaIDQuantidadeSubtrai(u);
		} catch (UsuarioDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void AtualizaQuantidadeDisponivelSoma(int l){
		LivroDao quant=new LivroDao();

		try {
			quant.consultaIDQuantidadeSoma(l);
		} catch (LivroDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void AtualizaQuantidadeUsuarioSoma(String u){
		UsuarioDao quant=new UsuarioDao();
		try {
			quant.consultaIDQuantidadeSoma(u);
		} catch (UsuarioDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void RetornaQuantidadeDisponivelSoma(int l){
		EmprestimoDao dao=new EmprestimoDao();
		try {
			dao.consultaIDQuantidadeSoma(l);
		} catch (EmprestimoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void RetornaQuantidadeDisponivelSubtrai(int l){
		EmprestimoDao dao=new EmprestimoDao();
		try {
			dao.consultaIDQuantidadeSubtrai(l);
		} catch (EmprestimoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void RetornaQuantidadeSubtrai(int u){
		EmprestimoDao dao=new EmprestimoDao();
		try {
			dao.consultaQuantidadeSubtrai(u);
		} catch (EmprestimoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void RetornaQuantidadeSoma(int u){
		EmprestimoDao dao=new EmprestimoDao();
		try {
			dao.consultaQuantidadeSoma(u);
		} catch (EmprestimoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void atualizaCBStatus(Emprestimo emprestimo){
		cbStatus.setModel(new DefaultComboBoxModel(new String[] {"Devolvido "}));
		emprestimo.setStatus(cbStatus.getSelectedItem().toString());

		EmprestimoDao emp=new EmprestimoDao();

		try {
			emp.AtualizaStatusEmprestimo(emprestimo);
		} catch (EmprestimoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void atualizaCBStatusEmAndamento(Emprestimo emprestimo){
		cbStatus.setModel(new DefaultComboBoxModel(new String[] {"Em andamento"}));
		emprestimo.setStatus(cbStatus.getSelectedItem().toString());

		EmprestimoDao emp=new EmprestimoDao();

		try {
			emp.AtualizaStatusEmprestimo(emprestimo);
		} catch (EmprestimoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public boolean inserir(Emprestimo emprestimo) {
		boolean inserido=false;
		EmprestimoDao dao=new EmprestimoDao();

		try {
			inserido = dao.InsereEmprestimo(emprestimo);
		} catch (EmprestimoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inserido;
	}


	@Override
	public boolean renovar(Emprestimo emprestimo) {
		boolean editado=false;

		EmprestimoDao dao = new EmprestimoDao();
		try {
			editado=dao.AtualizarEmprestimo(emprestimo);
		} catch (EmprestimoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return editado;
	}


	@Override
	public Emprestimo consultar(Emprestimo emprestimo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean excluir(Emprestimo emprestimo) {
		boolean excluido=false;
		EmprestimoDao dao = new EmprestimoDao();						
		try {
			excluido = dao.excluirEmprestimo(emprestimo);
		} catch (EmprestimoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excluido;

	}
}
