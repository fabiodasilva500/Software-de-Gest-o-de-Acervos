package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.ControllerUsuario;
import Dao.UsuarioDao;
import Dao.UsuarioDaoException;
import Entidades.Usuario;

import javax.swing.JRadioButton;


public class UsuarioView extends JFrame implements ControllerUsuario {

	private JPanel contentPane;
	private JTextField tfIdentificacao;
	private JTextField tfNomeUsuario;
	private JTextField tfRg;
	private JTextField tfDataNasc;
	private JTextField tfEndereco;
	private JTextField tfNumeroResidencial;
	private JTextField tfBairro;
	private JTextField tfCep;
	private JTextField tfCidade;
	private JTextField tfEmail;
	private JTextField tfTelefone;
	private JTextField tfQuantidade;
	private final JRadioButton rbEtec = new JRadioButton("Aluno Etec");
	private final JRadioButton rbProfessor = new JRadioButton("Professor");
	private final JRadioButton rbFatec = new JRadioButton("Aluno Fatec");


	private void verificaInputNome(KeyEvent key){
		char sub = key.getKeyChar();		
		if(Character.isDigit(sub) == true){
			key.consume();			
		}
		if(tfNomeUsuario.getText().length() >= 70){
			key.consume();			
		}
	}

	private void verificaInputIdentificacaoProfessor(KeyEvent key){
		char sub = key.getKeyChar();		
		if(tfIdentificacao.getText().length() ==10){
			key.consume();			
		}
	}


	private void verificaInputIdentificacaoAlunoEtec(KeyEvent key){
		char sub = key.getKeyChar();		
		if(tfIdentificacao.getText().length() ==9){
			key.consume();			
		}
	}


	private void verificaInputIdentificacaoAlunoFatec(KeyEvent key){
		char sub = key.getKeyChar();	
		if(Character.isDigit(sub)==false){
			key.consume();
		}

		if(tfIdentificacao.getText().length() == 7){
			key.consume();			
		}
	}

	private void verificaInputRG(KeyEvent key){
		char sub = key.getKeyChar();
		if(Character.isDigit(sub)==false){
			key.consume();
		}
		if(tfRg.getText().length() >= 9){
			key.consume();			
		}
	}

	private void verificaInputNascimento(KeyEvent key){
		if(tfDataNasc.getText().length() >= 10){
			key.consume();			
		}
	}

	private void verificaInputEndereco(KeyEvent key){
		char sub = key.getKeyChar();
		if(Character.isDigit(sub)==true){
			key.consume();
		}
		if(tfEndereco.getText().length() >= 70){
			key.consume();			
		}
	}

	private void verificaInputNumero(KeyEvent key){
		char sub = key.getKeyChar();
		if(Character.isDigit(sub)==false){
			key.consume();
		}
	}

	private void verificaInputBairro(KeyEvent key){
		char sub = key.getKeyChar();
		if(Character.isDigit(sub)==true){
			key.consume();
		}
		if(tfBairro.getText().length() >= 50){
			key.consume();			
		}
	}

	private void verificaInputCep(KeyEvent key){
		char sub = key.getKeyChar();
		if(Character.isDigit(sub)==false){
			key.consume();
		}
		if(tfCep.getText().length() >= 8){
			key.consume();			
		}
	}

	private void verificaInputCidade(KeyEvent key){
		char sub = key.getKeyChar();
		if(Character.isDigit(sub)==true){
			key.consume();
		}
		if(tfCidade.getText().length() >= 40){
			key.consume();			
		}
	}

	private void verificaInputEmail(KeyEvent key){

		if(tfEmail.getText().length() >= 80){
			key.consume();			
		}
	}

	private void verificaInputTelefone(KeyEvent key){
		char sub = key.getKeyChar();
		if(Character.isDigit(sub)==false){
			key.consume();
		}
		if(tfTelefone.getText().length() >= 10){
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
					UsuarioView frame = new UsuarioView();
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
	public UsuarioView() {
		super("Manutenção do Cadastro de Usuários");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIdentificao = new JLabel("Identifica\u00E7\u00E3o*");
		lblIdentificao.setBounds(377, 11, 102, 14);
		contentPane.add(lblIdentificao);

		JLabel lblNome = new JLabel("Nome*");
		lblNome.setBounds(10, 47, 46, 14);
		contentPane.add(lblNome);

		JLabel lblRg = new JLabel("RG*");
		lblRg.setBounds(10, 72, 46, 14);
		contentPane.add(lblRg);

		JLabel lblDataDeNascimento = new JLabel("Data Nascimento*");
		lblDataDeNascimento.setBounds(10, 97, 102, 14);
		contentPane.add(lblDataDeNascimento);

		JLabel lblEndereco = new JLabel("Endere\u00E7o*");
		lblEndereco.setBounds(10, 128, 62, 14);
		contentPane.add(lblEndereco);

		JLabel lblNmero = new JLabel("N\u00FAmero*");
		lblNmero.setBounds(604, 160, 66, 14);
		contentPane.add(lblNmero);

		JLabel lblBairro = new JLabel("Bairro*");
		lblBairro.setBounds(10, 160, 62, 14);
		contentPane.add(lblBairro);

		JLabel lblCep = new JLabel("CEP*");
		lblCep.setBounds(604, 191, 30, 14);
		contentPane.add(lblCep);

		JLabel lblCidade = new JLabel("Cidade*");
		lblCidade.setBounds(10, 191, 62, 14);
		contentPane.add(lblCidade);

		JLabel lblUf = new JLabel("UF");
		lblUf.setBounds(604, 225, 46, 14);
		contentPane.add(lblUf);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 225, 62, 14);
		contentPane.add(lblEmail);

		JLabel lblTelefone = new JLabel("Telefone*");
		lblTelefone.setBounds(10, 253, 62, 14);
		contentPane.add(lblTelefone);

		tfIdentificacao = new JTextField();
		tfIdentificacao.setBounds(507, 8, 152, 20);
		contentPane.add(tfIdentificacao);
		tfIdentificacao.setColumns(10);



		tfNomeUsuario = new JTextField();
		tfNomeUsuario.setBounds(142, 44, 449, 20);
		contentPane.add(tfNomeUsuario);
		tfNomeUsuario.setColumns(10);
		tfNomeUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputNome(tecla);
			}
		});

		tfRg = new JTextField();
		tfRg.setBounds(142, 69, 131, 20);
		contentPane.add(tfRg);
		tfRg.setColumns(10);
		tfRg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputRG(tecla);
			}
		});

		tfDataNasc = new JTextField();
		tfDataNasc.setBounds(142, 94, 102, 20);
		contentPane.add(tfDataNasc);
		tfDataNasc.setColumns(10);
		tfDataNasc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputNascimento(tecla);
			}
		});


		tfEndereco = new JTextField();
		tfEndereco.setBounds(142, 125, 420, 20);
		contentPane.add(tfEndereco);
		tfEndereco.setColumns(10);
		tfEndereco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputEndereco(tecla);
			}
		});

		tfNumeroResidencial = new JTextField();
		tfNumeroResidencial.setBounds(680, 157, 54, 20);
		contentPane.add(tfNumeroResidencial);
		tfNumeroResidencial.setColumns(10);
		tfNumeroResidencial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputNumero(tecla);
			}
		});

		tfBairro = new JTextField();
		tfBairro.setBounds(142, 157, 420, 20);
		contentPane.add(tfBairro);
		tfBairro.setColumns(10);
		tfBairro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputBairro(tecla);
			}
		});

		tfCep = new JTextField();
		tfCep.setBounds(680, 188, 92, 20);
		contentPane.add(tfCep);
		tfCep.setColumns(10);
		tfCep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputCep(tecla);
			}
		});

		tfCidade = new JTextField();
		tfCidade.setBounds(142, 188, 420, 20);
		contentPane.add(tfCidade);
		tfCidade.setColumns(10);
		tfCidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputCidade(tecla);
			}
		});

		final JComboBox comboBoxUf = new JComboBox();
		comboBoxUf.setModel(new DefaultComboBoxModel(new String[] {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS ", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		comboBoxUf.setToolTipText("");
		comboBoxUf.setBounds(681, 222, 53, 20);
		contentPane.add(comboBoxUf);

		tfEmail = new JTextField();
		tfEmail.setBounds(142, 219, 420, 20);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputEmail(tecla);
			}
		});

		tfTelefone = new JTextField();
		tfTelefone.setBounds(142, 250, 152, 20);
		contentPane.add(tfTelefone);
		tfTelefone.setColumns(10);
		tfTelefone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputTelefone(tecla);
			}
		});

		final JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(71, 423, 89, 23);
		contentPane.add(btnAdicionar);

		final JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(255, 423, 89, 23);
		contentPane.add(btnAlterar);

		final JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(455, 423, 89, 23);
		contentPane.add(btnExcluir);

		final JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(628, 423, 89, 23);
		contentPane.add(btnConsultar);

		JSeparator separator = new JSeparator();
		separator.setBounds(-14, 294, 798, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 384, 798, 2);
		contentPane.add(separator_1);

		JLabel lblOpcoes = new JLabel("Menu de Sele\u00E7\u00E3o ");
		lblOpcoes.setBounds(10, 304, 102, 14);
		contentPane.add(lblOpcoes);

		JRadioButton rdbtnCadastrar = new JRadioButton("Cadastrar");
		rdbtnCadastrar.setBounds(32, 342, 109, 23);
		contentPane.add(rdbtnCadastrar);

		final JRadioButton rdbtnAlterar = new JRadioButton("Alterar");
		rdbtnAlterar.setBounds(185, 342, 109, 23);
		contentPane.add(rdbtnAlterar);

		JRadioButton rdbtnExcluir = new JRadioButton("Excluir");
		rdbtnExcluir.setBounds(340, 342, 109, 23);
		contentPane.add(rdbtnExcluir);

		travarCampo();
		comboBoxUf.enable(false);
		btnAdicionar.setVisible(false);
		btnAlterar.setVisible(false);
		btnConsultar.setVisible(false);
		btnExcluir.setVisible(false);

		ButtonGroup GrupoRB = new ButtonGroup(); //usado para agrupar os radios buttons, quando é clicado em um ele desmarca os outros

		ButtonGroup GrupoRBCategoria = new ButtonGroup(); //usado para agrupar os radios buttons, quando é clicado em um ele desmarca os outros


		GrupoRB.add(rdbtnCadastrar);
		GrupoRB.add(rdbtnAlterar);
		GrupoRB.add(rdbtnExcluir);

		GrupoRBCategoria.add(rbEtec);
		GrupoRBCategoria.add(rbFatec);
		GrupoRBCategoria.add(rbProfessor);

		JLabel lblCamposCom = new JLabel("Campos com  * s\u00E3o obrigat\u00F3rios");
		lblCamposCom.setBounds(471, 294, 284, 14);
		contentPane.add(lblCamposCom);

		JLabel lblQuantidade = new JLabel("N\u00FAmero de Livros em posse");
		lblQuantidade.setBounds(329, 256, 180, 14);
		contentPane.add(lblQuantidade);

		tfQuantidade = new JTextField();
		tfQuantidade.setEnabled(false);
		tfQuantidade.setBounds(507, 250, 72, 20);
		contentPane.add(tfQuantidade);
		tfQuantidade.setColumns(10);

		rbEtec.setBounds(10, 7, 109, 23);
		contentPane.add(rbEtec);

		rbProfessor.setBounds(255, 7, 92, 23);
		contentPane.add(rbProfessor);

		rbFatec.setBounds(142, 7, 109, 23);
		contentPane.add(rbFatec);




		ActionListener rdbtnCadastroListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				destravarCampo();
				rbEtec.setEnabled(true);
				rbFatec.setEnabled(true);
				rbProfessor.setEnabled(true);

				comboBoxUf.setEnabled(true);
				btnAdicionar.setVisible(true);
				btnAlterar.setVisible(false);
				btnConsultar.setVisible(false);
				btnExcluir.setVisible(false);
				LimparCampo();
			}
		};

		ActionListener rdbtnAlterarListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				preparaNovaPesquisaID();
				comboBoxUf.setEnabled(false);
				rbEtec.setEnabled(true);

				rbFatec.setEnabled(true);
				rbProfessor.setEnabled(true);

				btnAdicionar.setVisible(false);
				btnAlterar.setVisible(true);
				btnConsultar.setVisible(true);
				btnExcluir.setVisible(false);
				LimparCampo();
			}
		};

		ActionListener rdbtnExcluirListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				travarCampo();
				LimparCampo();

				comboBoxUf.setEnabled(false);
				rbEtec.setEnabled(true);
				rbFatec.setEnabled(true);
				rbProfessor.setEnabled(true);
				tfIdentificacao.setEnabled(true);
				btnConsultar.setVisible(true);
				btnExcluir.setVisible(true);
				btnAdicionar.setVisible(false);
				btnAlterar.setVisible(false);
			}
		};


		ActionListener rbEtecListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tfIdentificacao.setText("0");
				tfIdentificacao.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent tecla) {

						if(rbEtec.isSelected()){
							verificaInputIdentificacaoAlunoEtec(tecla);
						}

					}
				}
						);

			}
		};


		ActionListener rbFatecListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tfIdentificacao.setText("0");
				tfIdentificacao.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent tecla) {

						if(rbFatec.isSelected()){
							verificaInputIdentificacaoAlunoFatec(tecla);
						}

					}
				}
						);

			}
		};



		ActionListener rbProfessorListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				tfIdentificacao.setText("0");
				tfIdentificacao.addKeyListener(new KeyAdapter(){
					@Override
					public void keyTyped(KeyEvent tecla) {
						verificaInputIdentificacaoProfessor(tecla);
					}
				}
						);
			}

		};

		rdbtnCadastrar.addActionListener(rdbtnCadastroListener);
		rdbtnAlterar.addActionListener(rdbtnAlterarListener);
		rdbtnExcluir.addActionListener(rdbtnExcluirListener);
		rbEtec.addActionListener(rbEtecListener);
		rbFatec.addActionListener(rbFatecListener);
		rbProfessor.addActionListener(rbProfessorListener);



		/**
		 * 
		 * 
		 * 
		 */

		ActionListener cadastroListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Usuario usuario = new Usuario();
				if(!tfIdentificacao.getText().isEmpty() && !tfNomeUsuario.getText().isEmpty() &&
						!tfBairro.getText().isEmpty() && !tfCep.getText().isEmpty()&&
						!tfCidade.getText().isEmpty() && !tfDataNasc.getText().isEmpty()&&
						!tfEndereco.getText().isEmpty() && !tfNumeroResidencial.getText().isEmpty()&&
						!tfRg.getText().isEmpty() && !tfTelefone.getText().isEmpty()){





					usuario.setIdentificacao(tfIdentificacao.getText());
					usuario.setNome(tfNomeUsuario.getText());
					usuario.setRG(tfRg.getText());
					usuario.setData_Nasc(tfDataNasc.getText());
					usuario.setEndereco(tfEndereco.getText());
					usuario.setNumero(Integer.parseInt(tfNumeroResidencial.getText()));
					usuario.setBairro(tfBairro.getText());
					usuario.setCep(tfCep.getText());
					usuario.setCidade(tfCidade.getText());
					usuario.setUF(comboBoxUf.getSelectedItem().toString());
					usuario.setEmail(tfEmail.getText());
					usuario.setTelefone(tfTelefone.getText());
					usuario.setQuantidade(Integer.parseInt(tfQuantidade.getText()));

					if((rbEtec.isSelected())&&(usuario.getIdentificacao().length()==9)){
						usuario.setCategoria("Aluno Etec");	
					}

					else
						if((rbFatec.isSelected()&&(usuario.getIdentificacao().length()==7))){
							usuario.setCategoria("Aluno Fatec");	
						}

						else
							if((rbProfessor.isSelected())&&(usuario.getIdentificacao().length()==10)){
								usuario.setCategoria("Professor");	
							}
							else
								if(usuario.getCategoria()==null){
									JOptionPane.showMessageDialog(null,"Selecione a categoria do usuário que você deseja inserí-lo\nou verifique se a quantidade informada de caracteres está correta","Atenção",JOptionPane.CANCEL_OPTION);
								}




					if((usuario.getCategoria()!=null) &&(usuario.getData_Nasc().length()==10)){
						boolean inserido = false;

						inserido=inserirUsuario(usuario);
						if(inserido){
							JOptionPane.showMessageDialog(null,"Usuário inserido com sucesso",
									"Êxito",JOptionPane.INFORMATION_MESSAGE);
						}
						btnAdicionar.setVisible(true);
						btnAlterar.setVisible(false);
						btnConsultar.setVisible(false);
						btnExcluir.setVisible(false);
						LimparCampo();
					}
					else{
						JOptionPane.showMessageDialog(null,"Data inválida","Atenção",JOptionPane.CANCEL_OPTION);
					}
				}

				else{
					JOptionPane.showMessageDialog(null,"Usuário não inserido campos vázios, ou informados de forma incorreta",
							"Atenção", JOptionPane.CANCEL_OPTION);
					btnAdicionar.setVisible(true);
					btnAlterar.setVisible(false);
					btnConsultar.setVisible(false);
					btnExcluir.setVisible(false);

				}
			} 

		};


		ActionListener edicaoListener =new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Usuario usuario = new Usuario();


				if(!tfIdentificacao.getText().isEmpty() && !tfNomeUsuario.getText().isEmpty() &&
						!tfBairro.getText().isEmpty() && !tfCep.getText().isEmpty()&&
						!tfCidade.getText().isEmpty() && !tfDataNasc.getText().isEmpty()&&
						!tfEndereco.getText().isEmpty() && !tfNumeroResidencial.getText().isEmpty()&&
						!tfRg.getText().isEmpty() && !tfTelefone.getText().isEmpty()){

					usuario.setIdentificacao(tfIdentificacao.getText());	
					usuario.setNome(tfNomeUsuario.getText());
					usuario.setRG(tfRg.getText());
					usuario.setData_Nasc(tfDataNasc.getText());
					usuario.setEndereco(tfEndereco.getText());
					usuario.setNumero(Integer.parseInt(tfNumeroResidencial.getText()));
					usuario.setBairro(tfBairro.getText());
					usuario.setCep(tfCep.getText());
					usuario.setCidade(tfCidade.getText());
					usuario.setUF(comboBoxUf.getSelectedItem().toString());
					usuario.setEmail(tfEmail.getText());
					usuario.setTelefone(tfTelefone.getText());
					usuario.setQuantidade(Integer.parseInt(tfQuantidade.getText()));



					if (usuario.getData_Nasc().length()==10){

						boolean editado = false;

						editado=atualizarUsuario(usuario);

						if(editado){
							JOptionPane.showMessageDialog(null,"Usuário editado com sucesso","Sucesso",JOptionPane.INFORMATION_MESSAGE);
							btnAdicionar.setVisible(false);
							btnAlterar.setVisible(true);
							btnConsultar.setVisible(true);
							btnExcluir.setVisible(false);
							LimparCampo();		 
							preparaNovaPesquisaID();
							tfIdentificacao.setEnabled(true);

						}

					}else{
						JOptionPane.showMessageDialog(null,"Usuário não editado há campos vázios ou a informada está data inválida","Atenção",JOptionPane.CANCEL_OPTION);
						LimparCampo();
						travarCampo();
						tfIdentificacao.setEnabled(true);

					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Usuário não editado","Atenção",JOptionPane.CANCEL_OPTION);
				}


			}
		};


		ActionListener buscaListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Usuario usuario = new Usuario();
				Usuario consulta = new Usuario();
				if(tfIdentificacao.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Digite o Numero de Indentificação para buscar", "Atenção",JOptionPane.CANCEL_OPTION);
				}else{

					usuario.setIdentificacao(tfIdentificacao.getText());

					consulta=consultarUsuario(usuario);

					if(tfNomeUsuario.getText().isEmpty()){
						tfIdentificacao.setEnabled(false);				         
						tfIdentificacao.setText(consulta.getIdentificacao());
						tfNomeUsuario.setText(consulta.getNome());
						tfRg.setText(consulta.getRG());
						tfDataNasc.setText(consulta.getData_Nasc());
						tfEndereco.setText(consulta.getEndereco());
						tfNumeroResidencial.setText(String.valueOf(consulta.getNumero()));
						tfBairro.setText(consulta.getBairro());
						tfCep.setText(consulta.getCep());
						tfCidade.setText(consulta.getCidade());
						comboBoxUf.setSelectedItem(consulta.getUF());
						tfEmail.setText(consulta.getEmail());
						tfTelefone.setText(consulta.getTelefone());
						tfQuantidade.setText(String.valueOf(consulta.getQuantidade()));

						if(rdbtnAlterar.isSelected() &&(!tfNomeUsuario.getText().isEmpty())){
							destravarCampo();
							comboBoxUf.setEnabled(true);
							tfIdentificacao.setEnabled(false);
							rbEtec.setEnabled(false);
							rbFatec.setEnabled(false);
							rbProfessor.setEnabled(false);

						}
					}

					if(tfNomeUsuario.getText().isEmpty()){
						tfIdentificacao.setEnabled(true);
						rbEtec.setEnabled(true);
						rbFatec.setEnabled(true);
						rbProfessor.setEnabled(true);


					}

				}
			}

		};

		ActionListener exclusaoListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Usuario usuario = new Usuario();

				if(tfIdentificacao.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Digite a Identificação","Atenção",JOptionPane.CANCEL_OPTION);
				}
				else

					if(!tfNomeUsuario.getText().isEmpty()){


						usuario.setIdentificacao(tfIdentificacao.getText());
						boolean excluido = false;

						excluido=excluirUsuario(usuario);

						if(excluido){
							JOptionPane.showMessageDialog(null,"Usuário Excluído com sucesso",
									"Sucesso",JOptionPane.INFORMATION_MESSAGE);
							btnAdicionar.setVisible(false);
							btnAlterar.setVisible(false);
							btnConsultar.setVisible(true);
							btnExcluir.setVisible(true);
							LimparCampo();
							travarCampo();
							tfIdentificacao.setEnabled(true);

						}
						else{
							travarCampo();
							tfIdentificacao.setEnabled(true);
							LimparCampo();
						}


					}
					else{
						JOptionPane.showMessageDialog(null,"Usuário inválido","Atenção",JOptionPane.CANCEL_OPTION);

					}


			}   
		};


		btnAdicionar.addActionListener(cadastroListener);
		btnAlterar.addActionListener(edicaoListener);
		btnExcluir.addActionListener(exclusaoListener);
		btnConsultar.addActionListener(buscaListener);
	}


	public void travarCampo(){
		tfBairro.setEnabled(false);
		tfCep.setEnabled(false);
		tfCidade.setEnabled(false);
		tfDataNasc.setEnabled(false);
		tfEmail.setEnabled(false);
		tfEndereco.setEnabled(false);
		tfIdentificacao.setEnabled(false);
		tfNomeUsuario.setEnabled(false);
		tfNumeroResidencial.setEnabled(false);
		tfRg.setEnabled(false);
		tfTelefone.setEnabled(false);
	}



	public void preparaNovaPesquisaID(){
		tfIdentificacao.setEnabled(true);
		tfNomeUsuario.setEnabled(false);
		tfBairro.setEnabled(false);
		tfCep.setEnabled(false);
		tfCidade.setEnabled(false);
		tfDataNasc.setEnabled(false);
		tfEmail.setEnabled(false);
		tfEndereco.setEnabled(false);
		tfNumeroResidencial.setEnabled(false);
		tfQuantidade.setEnabled(false);
		tfRg.setEnabled(false);
		tfTelefone.setEnabled(false);
		rbEtec.setEnabled(true);
		rbFatec.setEnabled(true);
		rbProfessor.setEnabled(true);



	}

	public void destravarCampo(){
		tfBairro.setEnabled(true);
		tfCep.setEnabled(true);
		tfCidade.setEnabled(true);
		tfDataNasc.setEnabled(true);
		tfEmail.setEnabled(true);
		tfEndereco.setEnabled(true);
		tfIdentificacao.setEnabled(true);
		tfNomeUsuario.setEnabled(true);
		tfNumeroResidencial.setEnabled(true);
		tfRg.setEnabled(true);
		tfTelefone.setEnabled(true);
	}

	public void LimparCampo(){
		tfBairro.setText("");
		tfCep.setText("");
		tfCidade.setText("");
		tfDataNasc.setText("");
		tfEmail.setText("");
		tfEndereco.setText("");
		tfIdentificacao.setText("");
		tfNomeUsuario.setText("");
		tfNumeroResidencial.setText("");
		tfRg.setText("");
		tfTelefone.setText("");
		tfQuantidade.setText("0");

	}

	@Override
	public boolean inserirUsuario(Usuario usuario) {
		boolean inserido = false;
		UsuarioDao dao = new UsuarioDao();

		try {
			inserido = dao.InsereUsuario(usuario);
		} catch (UsuarioDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inserido;		
	}



	@Override
	public boolean atualizarUsuario(Usuario usuario) {
		boolean editado = false;
		UsuarioDao dao = new UsuarioDao();
		try {
			editado = dao.AtualizarUsuario(usuario);
		} catch (UsuarioDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return editado;
	}


	@Override
	public Usuario consultarUsuario(Usuario usuario) {
		Usuario usuarioConsultado=new Usuario();
		UsuarioDao dao=new UsuarioDao();

		try {
			usuarioConsultado=dao.consultaUsuario(usuario);
		} catch (UsuarioDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return usuarioConsultado;
	}

	@Override
	public boolean excluirUsuario(Usuario usuario) {
		boolean excluido=false;
		UsuarioDao dao = new UsuarioDao();


		try {
			excluido = dao.excluirUsuario(usuario);
		} catch (UsuarioDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excluido;
	}	
}


