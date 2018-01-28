package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import Controller.ControllerLivro;
import Dao.LivroDao;
import Dao.LivroDaoException;
import Entidades.Livro;

import javax.swing.JSeparator;
import javax.swing.JComboBox;

public class LivroView extends JFrame implements ControllerLivro {

	private JPanel contentPane;
	private JTextField tfIdLivro;
	private JTextField tfTitulo;
	private JTextField tfEdicao;
	private JTextField tfPaginas;
	private JTextField tfVolume;
	private JTextField tfIsbn;
	private JTextField tfClassificacao;
	private JTextField tfAssunto;
	private JTextField tfComprimento;
	private JTextField tfLargura;
	private JTextField tfLocalizacao;
	private JTextField tfQuantidade;
	private  final JComboBox cbLivro = new JComboBox();
	private JTextField tfDisponiveis;

	private void verificaInputID(KeyEvent key){
		char sub = key.getKeyChar();		
		if(Character.isDigit(sub) == false){
			key.consume();			
		}

	}

	private void verificaInputTitulo(KeyEvent key){
		char sub = key.getKeyChar();		
		if(tfTitulo.getText().length() >= 100){
			key.consume();			
		}
	}

	private void verificaInputEdicao(KeyEvent key){
		char sub = key.getKeyChar();		
		if(tfEdicao.getText().length() >= 12){
			key.consume();			
		}
	}
	private void verificaInputPaginas(KeyEvent key){
		char sub = key.getKeyChar();		
		if(Character.isDigit(sub) == false){
			key.consume();			
		}
	}
	private void verificaInputVolume(KeyEvent key){
		char sub = key.getKeyChar();		
		if(tfVolume.getText().length() >= 10){
			key.consume();			
		}
	}
	private void verificaInputISBN(KeyEvent key){
		char sub = key.getKeyChar();		
		if(tfIsbn.getText().length() >= 30){
			key.consume();			
		}
	}
	private void verificaInputClassificacao(KeyEvent key){
		char sub = key.getKeyChar();		
		if(tfClassificacao.getText().length() >= 50){
			key.consume();			
		}
	}
	private void verificaInputAssunto(KeyEvent key){
		char sub = key.getKeyChar();		
		if(tfAssunto.getText().length() >= 100){
			key.consume();			
		}
	}
	private void verificaInputComprimento(KeyEvent key){
		char sub = key.getKeyChar();		
		if(Character.isDigit(sub) == false){
			key.consume();			
		}
	}
	private void verificaInputLargura(KeyEvent key){
		char sub = key.getKeyChar();		
		if(Character.isDigit(sub) == false){
			key.consume();			
		}

	}
	private void verificaInputLocalizacao(KeyEvent key){
		char sub = key.getKeyChar();		
		if(tfLocalizacao.getText().length() >= 50){
			key.consume();			
		}
	}
	private void verificaInputQtde(KeyEvent key){
		char sub = key.getKeyChar();		
		if(Character.isDigit(sub) == false){
			key.consume();			
		}
	}

	private void verificaInputDisponivel(KeyEvent key){
		char sub = key.getKeyChar();		
		if(Character.isDigit(sub) == false){
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
					LivroView frame = new LivroView();
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
	public LivroView() {
		super("Manutenção do Cadastro de Livros");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIdentificao = new JLabel("ID Livro");
		lblIdentificao.setBounds(25, 11, 77, 14);
		contentPane.add(lblIdentificao);

		JLabel lblTtulo = new JLabel("T\u00EDtulo *");
		lblTtulo.setBounds(25, 36, 46, 14);
		contentPane.add(lblTtulo);

		JLabel lblEdicao = new JLabel("Edi\u00E7\u00E3o *");
		lblEdicao.setBounds(25, 61, 46, 14);
		contentPane.add(lblEdicao);

		JLabel lblPginas = new JLabel("P\u00E1ginas *");
		lblPginas.setBounds(25, 86, 58, 14);
		contentPane.add(lblPginas);

		JLabel lblVolume = new JLabel("Volume *");
		lblVolume.setBounds(25, 114, 58, 14);
		contentPane.add(lblVolume);

		JLabel lblIsbn = new JLabel("ISBN *");
		lblIsbn.setBounds(25, 137, 46, 14);
		contentPane.add(lblIsbn);

		JLabel lblClassificao = new JLabel("Classifica\u00E7\u00E3o*");
		lblClassificao.setBounds(25, 162, 86, 14);
		contentPane.add(lblClassificao);

		JLabel lblAssunto = new JLabel("Assunto *");
		lblAssunto.setBounds(25, 187, 58, 14);
		contentPane.add(lblAssunto);

		JLabel lblDimenso = new JLabel("Dimens\u00E3o");
		lblDimenso.setBounds(25, 209, 66, 14);
		contentPane.add(lblDimenso);

		JLabel lblLocalizao = new JLabel("Localiza\u00E7\u00E3o");
		lblLocalizao.setBounds(25, 234, 77, 14);
		contentPane.add(lblLocalizao);

		JLabel lblQuantidade = new JLabel("Quantidade *");
		lblQuantidade.setBounds(25, 262, 77, 14);
		contentPane.add(lblQuantidade);

		tfIdLivro = new JTextField();
		tfIdLivro.setBounds(128, 8, 66, 20);
		contentPane.add(tfIdLivro);
		tfIdLivro.setColumns(10);
		tfIdLivro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputID(tecla);
			}
		});

		tfTitulo = new JTextField();
		tfTitulo.setBounds(128, 33, 601, 20);
		contentPane.add(tfTitulo);
		tfTitulo.setColumns(10);
		tfTitulo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputTitulo(tecla);
			}
		});

		tfEdicao = new JTextField();
		tfEdicao.setBounds(128, 58, 178, 20);
		contentPane.add(tfEdicao);
		tfEdicao.setColumns(10);
		tfEdicao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputEdicao(tecla);
			}
		});

		tfPaginas = new JTextField();
		tfPaginas.setBounds(128, 83, 66, 20);
		contentPane.add(tfPaginas);
		tfPaginas.setColumns(10);
		tfPaginas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputPaginas(tecla);
			}
		});

		tfVolume = new JTextField();
		tfVolume.setBounds(128, 111, 127, 20);
		contentPane.add(tfVolume);
		tfVolume.setColumns(10);
		tfVolume.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputVolume(tecla);
			}
		});

		tfIsbn = new JTextField();
		tfIsbn.setBounds(128, 134, 329, 20);
		contentPane.add(tfIsbn);
		tfIsbn.setColumns(10);
		tfIsbn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputISBN(tecla);
			}
		});

		tfClassificacao = new JTextField();
		tfClassificacao.setBounds(128, 159, 290, 20);
		contentPane.add(tfClassificacao);
		tfClassificacao.setColumns(10);
		tfClassificacao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputClassificacao(tecla);
			}
		});

		tfAssunto = new JTextField();
		tfAssunto.setBounds(128, 184, 591, 20);
		contentPane.add(tfAssunto);
		tfAssunto.setColumns(10);
		tfAssunto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputAssunto(tecla);
			}
		});

		tfComprimento = new JTextField();
		tfComprimento.setBounds(128, 206, 71, 20);
		contentPane.add(tfComprimento);
		tfComprimento.setColumns(10);
		tfComprimento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputComprimento(tecla);
			}
		});

		JLabel lblPor = new JLabel("por");
		lblPor.setBounds(223, 209, 46, 14);
		contentPane.add(lblPor);

		tfLargura = new JTextField();
		tfLargura.setBounds(279, 206, 71, 20);
		contentPane.add(tfLargura);
		tfLargura.setColumns(10);
		tfLargura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputLargura(tecla);
			}
		});

		JLabel lblCm = new JLabel("cm");
		lblCm.setBounds(372, 209, 46, 14);
		contentPane.add(lblCm);

		tfLocalizacao = new JTextField();
		tfLocalizacao.setBounds(128, 231, 329, 20);
		contentPane.add(tfLocalizacao);
		tfLocalizacao.setColumns(10);
		tfLocalizacao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputLocalizacao(tecla);
			}
		});

		tfQuantidade = new JTextField();
		tfQuantidade.setBounds(128, 256, 86, 20);
		contentPane.add(tfQuantidade);
		tfQuantidade.setColumns(10);
		tfQuantidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputQtde(tecla);
			}
		});


		tfDisponiveis = new JTextField();
		tfDisponiveis.setBounds(447, 259, 86, 20);
		contentPane.add(tfDisponiveis);	
		tfDisponiveis.setEnabled(false);
		tfDisponiveis.setColumns(10);
		tfDisponiveis.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputDisponivel(tecla);
			}
		});




		final JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(131, 428, 89, 23);
		contentPane.add(btnAdicionar);

		final JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(277, 428, 89, 23);
		contentPane.add(btnAlterar);

		final JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(427, 428, 89, 23);
		contentPane.add(btnExcluir);

		final JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(570, 428, 89, 23);
		contentPane.add(btnConsultar);

		final JRadioButton rdbtnCadastro = new JRadioButton("Cadastro");
		rdbtnCadastro.setBounds(75, 354, 109, 23);
		contentPane.add(rdbtnCadastro);

		final JRadioButton rdbtnEditarID = new JRadioButton("Edi\u00E7\u00E3o por ID");
		rdbtnEditarID.setBounds(241, 354, 109, 23);
		contentPane.add(rdbtnEditarID);

		final JRadioButton rdbtnEditarNome = new JRadioButton("Edi\u00E7\u00E3o por Nome");
		rdbtnEditarNome.setBounds(411, 354, 134, 23);
		contentPane.add(rdbtnEditarNome);

		final JRadioButton rdbtnExclusao = new JRadioButton("Exclus\u00E3o");
		rdbtnExclusao.setBounds(600, 354, 109, 23);
		contentPane.add(rdbtnExclusao);


		cbLivro.setBounds(211, 8, 563, 20);
		contentPane.add(cbLivro);



		ButtonGroup GrupoRB = new ButtonGroup(); //usado para agrupar os radios buttons, quando é clicado em um ele desmarca os outros

		GrupoRB.add(rdbtnCadastro);
		GrupoRB.add(rdbtnEditarID);
		GrupoRB.add(rdbtnEditarNome);
		GrupoRB.add(rdbtnExclusao);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 400, 815, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 323, 784, 2);
		contentPane.add(separator_1);

		JLabel lblOpcoes = new JLabel("Menu de sele\u00E7\u00E3o");
		lblOpcoes.setBounds(25, 323, 109, 14);
		contentPane.add(lblOpcoes);

		JLabel lblNewLabel = new JLabel("Campos com * s\u00E3o obrigat\u00F3rios para inser\u00E7\u00E3o/altera\u00E7\u00E3o");
		lblNewLabel.setBounds(187, 323, 329, 14);
		contentPane.add(lblNewLabel);

		JLabel lblQuantidadeDisponivel = new JLabel("Quantidade dispon\u00EDvel *");
		lblQuantidadeDisponivel.setBounds(298, 262, 139, 14);
		contentPane.add(lblQuantidadeDisponivel);


		btnAdicionar.setVisible(false);
		btnAlterar.setVisible(false);
		btnConsultar.setVisible(false);
		btnExcluir.setVisible(false);
		cbLivro.setVisible(false);

		desabilitaCampos();

		ActionListener rbCadastroListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cbLivro.setVisible(false);
				btnConsultar.setVisible(false);
				btnAdicionar.setVisible(true);
				btnAlterar.setVisible(false);
				btnExcluir.setVisible(false);

				limpaCampo();
				habilitaCampos();
				tfIdLivro.setEnabled(false);

			}
		};




		ActionListener rbEditaListenerID =new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cbLivro.setVisible(true);
				atualizaCBLivro();
				btnConsultar.setVisible(true);
				btnAdicionar.setVisible(false);
				btnAlterar.setVisible(true);
				btnExcluir.setVisible(false);

				limpaCampo();
				inicializaCampo();
				desabilitaCampos();
				tfIdLivro.setEnabled(true);

			}
		};

		ActionListener rbEditaListenerNome =new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cbLivro.setVisible(true);	
				atualizaCBLivro();
				btnConsultar.setVisible(true);
				btnAdicionar.setVisible(false);
				btnAlterar.setVisible(true);
				btnExcluir.setVisible(false);

				limpaCampo();
				desabilitaCampos();
				tfTitulo.setEnabled(true);


			}
		};


		ActionListener rbExclusaoListener =new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {


				cbLivro.setVisible(true);
				atualizaCBLivro();
				btnConsultar.setVisible(true);
				btnAdicionar.setVisible(false);
				btnAlterar.setVisible(false);
				btnExcluir.setVisible(true);

				limpaCampo();
				inicializaCampo();
				desabilitaCampos();
				tfIdLivro.setEnabled(true);

			}
		};


		rdbtnCadastro.addActionListener(rbCadastroListener);
		rdbtnEditarID.addActionListener(rbEditaListenerID);
		rdbtnEditarNome.addActionListener(rbEditaListenerNome);
		rdbtnExclusao.addActionListener(rbExclusaoListener);


		ActionListener cadastroListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Livro livro=new Livro();

				if(!tfTitulo.getText().isEmpty() && !tfEdicao.getText().isEmpty() &&
						!tfPaginas.getText().isEmpty() && !tfVolume.getText().isEmpty()&&
						!tfIsbn.getText().isEmpty() && !tfClassificacao.getText().isEmpty()&&
						!tfAssunto.getText().isEmpty() && !tfQuantidade.getText().isEmpty() && !tfDisponiveis.getText().isEmpty()){

					livro.setTitulo(tfTitulo.getText());
					livro.setEdicao(tfEdicao.getText());
					livro.setPaginas(Integer.parseInt(tfPaginas.getText()) );
					livro.setVolume(tfVolume.getText());
					livro.setISBN(tfIsbn.getText());
					livro.setClassificacao(tfClassificacao.getText());
					livro.setAssunto(tfAssunto.getText());
					livro.setComprimento(Float.parseFloat(tfComprimento.getText()));
					livro.setLargura(Float.parseFloat(tfLargura.getText()));
					livro.setLocalizacao(tfLocalizacao.getText());
					livro.setQtde(Integer.parseInt(tfQuantidade.getText())); 
					livro.setQuantidade_Disponivel(Integer.parseInt(tfDisponiveis.getText()));

					int q_total=Integer.parseInt(tfQuantidade.getText());
					int q_disponivel=Integer.parseInt(tfDisponiveis.getText());

					if(q_total<q_disponivel){
						JOptionPane.showMessageDialog(null,"A quantidade disponível não pode ser superior a quantidade de livros que será cadastrada","Atenção",JOptionPane.CANCEL_OPTION);
					}

					else{
						boolean inserido = inserirLivro(livro);

						if(inserido){
							JOptionPane.showMessageDialog(null,"Livro inserido com sucesso",
									"Êxito",JOptionPane.INFORMATION_MESSAGE);
							limpaCampo();
							atualizaCBLivro();
						}
					}
				}

				else{
					JOptionPane.showMessageDialog(null,"Livro não inserido, verifique os campos informados",
							"Atenção", JOptionPane.CANCEL_OPTION);


				}

			}

		};


		ActionListener buscaListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Livro livro =new Livro();
				Livro livroConsultado=new Livro();
				if(!rdbtnEditarNome.isSelected()) {
					livro.setIDLivro(Integer.parseInt(tfIdLivro.getText()));
					livroConsultado=consultarLivroID(livro);
				}

				if(rdbtnEditarNome.isSelected()){
					livro.setTitulo(tfTitulo.getText()); 
					livroConsultado=consultarLivroNome(livro);
				}

				tfIdLivro.setText(String.valueOf(livroConsultado.getIDLivro()));
				tfTitulo.setText(livroConsultado.getTitulo());
				tfEdicao.setText(livroConsultado.getEdicao());
				tfPaginas.setText(String.valueOf(livroConsultado.getPaginas()));
				tfVolume.setText(livroConsultado.getVolume());
				tfIsbn.setText(String.valueOf(livroConsultado.getISBN()));
				tfClassificacao.setText(livroConsultado.getClassificacao());
				tfAssunto.setText(livroConsultado.getAssunto());
				tfComprimento.setText(String.valueOf(livroConsultado.getComprimento()));
				tfLargura.setText(String.valueOf(livroConsultado.getLargura()));
				tfLocalizacao.setText(livroConsultado.getLocalizacao());
				tfQuantidade.setText(String.valueOf(livroConsultado.getQtde()));
				tfDisponiveis.setText(String.valueOf(livroConsultado.getQuantidade_Disponivel()));


				if(rdbtnEditarID.isSelected() &&(!tfTitulo.getText().isEmpty())){
					habilitaCampos();
					tfIdLivro.setEnabled(false);

				}

				if(rdbtnEditarNome.isSelected() &&(!tfTitulo.getText().isEmpty())){
					habilitaCampos();
					tfIdLivro.setEnabled(false);
					tfTitulo.setEnabled(false);

				}
			}

		};


		ActionListener exclusaoListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Livro livro=new Livro();
				livro.setIDLivro( Integer.parseInt(tfIdLivro.getText()) );

				if(!tfTitulo.getText().isEmpty()){
					LivroDao dao=new LivroDao();

					boolean excluido = excluirLivro(livro);

					if(excluido){
						JOptionPane.showMessageDialog(null,"Livro Excluído com sucesso",
								"Êxito",JOptionPane.INFORMATION_MESSAGE);

						limpaCampo();
						atualizaCBLivro();
						inicializaCampo();



					}
					else{
						JOptionPane.showMessageDialog(null,"Livro não excluído","Atenção",JOptionPane.CANCEL_OPTION);

						inicializaCampo();
						limpaCampo();
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Faça a busca!!!","Atenção",JOptionPane.CANCEL_OPTION);

					inicializaCampo();
				}
			}

		};

		ActionListener edicaoListener =new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cbLivro.setVisible(true);
				Livro livro=new Livro();

				habilitaCampos();
				tfIdLivro.setEnabled(false);



				if(!tfIdLivro.getText().isEmpty() && !tfTitulo.getText().isEmpty() && !tfEdicao.getText().isEmpty() &&
						!tfPaginas.getText().isEmpty() && !tfVolume.getText().isEmpty()&&
						!tfIsbn.getText().isEmpty() && !tfClassificacao.getText().isEmpty()&&
						!tfAssunto.getText().isEmpty() && !tfQuantidade.getText().isEmpty() && !tfDisponiveis.getText().isEmpty()){

					livro.setIDLivro(Integer.parseInt(tfIdLivro.getText()));	
					livro.setTitulo(tfTitulo.getText());
					livro.setEdicao(tfEdicao.getText());		
					livro.setPaginas(Integer.parseInt(tfPaginas.getText()));
					livro.setVolume(tfVolume.getText());
					livro.setISBN(tfIsbn.getText());
					livro.setClassificacao(tfClassificacao.getText());
					livro.setAssunto(tfAssunto.getText());
					livro.setComprimento(Float.parseFloat(tfComprimento.getText()));
					livro.setLargura(Float.parseFloat(tfLargura.getText()));
					livro.setLocalizacao(tfLocalizacao.getText());
					livro.setQtde(Integer.parseInt(tfQuantidade.getText()));
					livro.setQuantidade_Disponivel(Integer.parseInt(tfDisponiveis.getText()));




					int q_total=Integer.parseInt(tfQuantidade.getText());
					int q_disponivel=Integer.parseInt(tfDisponiveis.getText());

					if(q_total<q_disponivel){
						JOptionPane.showMessageDialog(null,"A quantidade disponível não pode ser superior a quantidade de livros que será cadastrada","Atenção",JOptionPane.CANCEL_OPTION);
						limpaCampo();
					}


					else{
						boolean editado=false;
						if(rdbtnEditarID.isSelected()){ 
							editado=atualizarLivroID(livro);
						}

						else{
							editado=
									atualizarLivroNome(livro);
						}

						if(editado){
							JOptionPane.showMessageDialog(null,"Livro editado com sucesso","Êxito",JOptionPane.INFORMATION_MESSAGE);
							limpaCampo();
							atualizaCBLivro();
							preparaNovaPesquisaID();
							inicializaCampo();
						}	
					}	
				}



				else{
					JOptionPane.showMessageDialog(null,"Livro não editado, verifique os campos informados\nou se a busca foi realizada","Atenção",JOptionPane.CANCEL_OPTION);
					limpaCampo();
					inicializaCampo();
				}



				if(rdbtnEditarID.isSelected()){ 
					preparaNovaPesquisaID();
				}
				else{
					preparaNovaPesquisaNome();
				}

			}
		};


		btnAdicionar.addActionListener(cadastroListener);
		btnConsultar.addActionListener(buscaListener);
		btnExcluir.addActionListener(exclusaoListener);
		btnAlterar.addActionListener(edicaoListener);

	}


	public void limpaCampo(){
		tfIdLivro.setText("");
		tfTitulo.setText("");
		tfEdicao.setText("");
		tfPaginas.setText("");
		tfVolume.setText("");
		tfIsbn.setText("");
		tfClassificacao.setText("");
		tfAssunto.setText("");
		tfComprimento.setText("0.0");
		tfLargura.setText("0.0");
		tfLocalizacao.setText("");
		tfQuantidade.setText("");
		tfDisponiveis.setText("");
	}


	public void atualizaCBLivro(){

		final LivroDao dao =new LivroDao();

		List<Livro> listaLivro = new ArrayList<Livro>();
		try {
			listaLivro=dao.ListaDeLivros();
		} catch (LivroDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(cbLivro!=null){
			cbLivro.removeAllItems();
		}

		for (Livro livro:listaLivro){
			cbLivro.addItem("ID:"+livro.getIDLivro()+" Título:"+livro.getTitulo()+"  Edição:"+livro.getEdicao());
		}
	}




	public void preparaNovaPesquisaID(){
		tfIdLivro.setEnabled(true);
		tfTitulo.setEnabled(false);
		tfEdicao.setEnabled(false);
		tfPaginas.setEnabled(false);
		tfVolume.setEnabled(false);
		tfIsbn.setEnabled(false);
		tfClassificacao.setEnabled(false);
		tfAssunto.setEnabled(false);
		tfComprimento.setEnabled(false);
		tfLargura.setEnabled(false);
		tfLocalizacao.setEnabled(false);
		tfQuantidade.setEnabled(false);
		tfDisponiveis.setEnabled(false);
	}



	public void preparaNovaPesquisaNome(){
		tfIdLivro.setText("");
		tfIdLivro.setEnabled(false);
		tfTitulo.setEnabled(true);
		tfEdicao.setEnabled(false);
		tfPaginas.setEnabled(false);
		tfVolume.setEnabled(false);
		tfIsbn.setEnabled(false);
		tfClassificacao.setEnabled(false);
		tfAssunto.setEnabled(false);
		tfComprimento.setEnabled(false);
		tfLargura.setEnabled(false);
		tfLocalizacao.setEnabled(false);
		tfQuantidade.setEnabled(false);
		tfDisponiveis.setEnabled(false);
	}


	public void inicializaCampo(){
		tfIdLivro.setText("0");
	}


	public void habilitaCampos(){
		tfIdLivro.setEnabled(true);
		tfTitulo.setEnabled(true);
		tfEdicao.setEnabled(true);
		tfPaginas.setEnabled(true);
		tfVolume.setEnabled(true);
		tfIsbn.setEnabled(true);
		tfClassificacao.setEnabled(true);
		tfAssunto.setEnabled(true);
		tfComprimento.setEnabled(true);
		tfLargura.setEnabled(true);
		tfLocalizacao.setEnabled(true);
		tfQuantidade.setEnabled(true);
		tfDisponiveis.setEnabled(true);
	}



	public void desabilitaCampos(){
		tfIdLivro.setEnabled(false);
		tfTitulo.setEnabled(false);
		tfEdicao.setEnabled(false);
		tfPaginas.setEnabled(false);
		tfVolume.setEnabled(false);
		tfIsbn.setEnabled(false);
		tfClassificacao.setEnabled(false);
		tfAssunto.setEnabled(false);
		tfComprimento.setEnabled(false);
		tfLargura.setEnabled(false);
		tfLocalizacao.setEnabled(false);
		tfQuantidade.setEnabled(false);
		tfDisponiveis.setEnabled(false);
	}

	@Override
	public boolean inserirLivro(Livro livro) {
		boolean inserido=false;
		LivroDao dao=new LivroDao();
		try {
			inserido = dao.InsereLivro(livro);
		} catch (LivroDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		return inserido;
	}

	@Override
	public boolean atualizarLivroID(Livro livro) {
		boolean editado=false;
		LivroDao dao=new LivroDao();
		try {
			editado=dao.AtualizarLivroID(livro);
			editado=true;
		} catch (LivroDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		return editado;
	}

	@Override
	public boolean atualizarLivroNome(Livro livro) {
		boolean editado=false;
		LivroDao dao=new LivroDao();
		try {
			editado=dao.AtualizarLivroID(livro);
		} catch (LivroDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		return editado;
	}

	@Override
	public Livro consultarLivroID(Livro livro) {
		Livro livroConsultado=new Livro();
		LivroDao dao=new LivroDao();

		try {
			livroConsultado=dao.consultaLivroID(livro);
		} catch (LivroDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		return livroConsultado;
	}


	@Override
	public Livro consultarLivroNome(Livro livro) {
		Livro livroConsultado=new Livro();
		LivroDao dao=new LivroDao();
		try {
			livroConsultado=dao.consultaLivroNome(livro);
		} catch (LivroDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		return livroConsultado;
	}

	@Override
	public boolean excluirLivro(Livro livro) {
		boolean excluido=false;
		LivroDao dao=new LivroDao();

		try {
			excluido = dao.excluirLivro(livro);
		} catch (LivroDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excluido;
	}
}