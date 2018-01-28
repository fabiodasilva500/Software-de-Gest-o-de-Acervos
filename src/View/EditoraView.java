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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;

import Controller.ControllerEditora;
import Dao.EditoraDao;
import Dao.EditoraDaoException;
import Entidades.Editora;

import javax.swing.JComboBox;


public class EditoraView extends JFrame implements ControllerEditora {

	private JPanel contentPane;
	private JTextField tfNomeEditora;
	private JLabel lblLabel;
	private JTextField tfIdEditora;
	private final JComboBox cbEditora = new JComboBox();
	private JTextField tfDescricaoEditora;

	private void verificaInputIDEditora(KeyEvent key){
		char sub = key.getKeyChar();		
		if(Character.isDigit(sub) == false){
			key.consume();			
		}
	}
	private void verificaInputNome(KeyEvent key){
		char sub = key.getKeyChar();		
		if(tfNomeEditora.getText().length() >= 70){
			key.consume();			
		}
	}


	private void verificaInputDescricao(KeyEvent key){
		char sub = key.getKeyChar();		
		if(tfNomeEditora.getText().length() >= 100){
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
					EditoraView frame = new EditoraView();
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
	public EditoraView() {
		super("Manutenção do Cadastro de Editoras");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		JLabel lblNome = new JLabel("Nome*");
		lblNome.setBounds(21, 64, 66, 14);
		contentPane.add(lblNome);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o*");
		lblDescricao.setBounds(21, 137, 66, 14);
		contentPane.add(lblDescricao);

		lblLabel = new JLabel("ID Editora");
		lblLabel.setBounds(21, 25, 66, 14);
		contentPane.add(lblLabel);

		final JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(34, 403, 89, 23);
		contentPane.add(btnAdicionar);

		final JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(145, 403, 79, 23);
		contentPane.add(btnAlterar);

		final JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(248, 403, 89, 23);
		contentPane.add(btnExcluir);

		final JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(360, 403, 89, 23);
		contentPane.add(btnConsultar);

		tfIdEditora = new JTextField();
		tfIdEditora.setBounds(97, 22, 46, 20);
		contentPane.add(tfIdEditora);
		tfIdEditora.setColumns(10);
		tfIdEditora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputIDEditora(tecla);
			}
		});


		tfNomeEditora = new JTextField();
		tfNomeEditora.setBounds(97, 61, 352, 20);
		contentPane.add(tfNomeEditora);
		tfNomeEditora.setColumns(10);
		tfNomeEditora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputNome(tecla);
			}
		});

		tfDescricaoEditora = new JTextField();
		tfDescricaoEditora.setEnabled(false);
		tfDescricaoEditora.setBounds(97, 106, 352, 95);
		contentPane.add(tfDescricaoEditora);
		tfDescricaoEditora.setColumns(10);
		tfDescricaoEditora.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputDescricao(tecla);
			}
		});


		JSeparator separator = new JSeparator();
		separator.setBounds(0, 248, 500, 2);
		contentPane.add(separator);

		final JRadioButton rdbtnCadastro = new JRadioButton("Cadastro");
		rdbtnCadastro.setBounds(8, 327, 79, 23);
		contentPane.add(rdbtnCadastro);

		final JRadioButton rdbtnEditaID = new JRadioButton("Edi\u00E7\u00E3o por ID");
		rdbtnEditaID.setBounds(114, 327, 102, 23);
		contentPane.add(rdbtnEditaID);

		final JRadioButton rdbtnExclusao = new JRadioButton("Exclus\u00E3o");
		rdbtnExclusao.setBounds(398, 327, 80, 23);
		contentPane.add(rdbtnExclusao);

		final JRadioButton rdbtnEditaNome = new JRadioButton("Edi\u00E7\u00E3o por Nome");
		rdbtnEditaNome.setBounds(251, 327, 133, 23);
		contentPane.add(rdbtnEditaNome);


		cbEditora.setBounds(153, 22, 325, 20);
		contentPane.add(cbEditora);


		ButtonGroup GrupoRB=new ButtonGroup();

		GrupoRB.add(rdbtnCadastro);
		GrupoRB.add(rdbtnEditaID);
		GrupoRB.add(rdbtnEditaNome);
		GrupoRB.add(rdbtnExclusao);


		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 376, 500, 2);
		contentPane.add(separator_1);

		JLabel lblOpcoes = new JLabel("Menu de Sele\u00E7\u00E3o");
		lblOpcoes.setBounds(10, 247, 102, 14);
		contentPane.add(lblOpcoes);

		JLabel lblCamposCom = new JLabel("Campos com * s\u00E3o obrigat\u00F3rios para inser\u00E7\u00E3o/altera\u00E7\u00E3o");
		lblCamposCom.setBounds(145, 248, 339, 14);
		contentPane.add(lblCamposCom);




		cbEditora.setVisible(false);
		btnConsultar.setVisible(false);
		btnAdicionar.setVisible(false);
		btnAlterar.setVisible(false);
		btnExcluir.setVisible(false);

		desabilitaCampos();

		ActionListener rbCadastroListener=new ActionListener(){


			public void actionPerformed(ActionEvent arg0) {
				cbEditora.setVisible(false);
				atualizaCBEditora();
				btnConsultar.setVisible(false);
				btnAlterar.setVisible(false);
				btnExcluir.setVisible(false);
				btnAdicionar.setVisible(true);

				limpaCampo();
				habilitaCampos();
				tfIdEditora.setEnabled(false);

			}
		};

		ActionListener rbEdicaoListenerID=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				cbEditora.setVisible(true);
				atualizaCBEditora();

				btnConsultar.setVisible(true);
				btnAlterar.setVisible(true);
				btnExcluir.setVisible(false);
				btnAdicionar.setVisible(false);

				limpaCampo();
				tfDescricaoEditora.setEnabled(false);

				desabilitaCampos();
				tfIdEditora.setEnabled(true);
				inicializaCampo();

			}

		};


		ActionListener rbEdicaoListenerNome=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				cbEditora.setVisible(true);
				atualizaCBEditora();

				btnConsultar.setVisible(true);
				btnAlterar.setVisible(true);
				btnExcluir.setVisible(false);
				btnAdicionar.setVisible(false);


				limpaCampo();

				limpaCampo();
				desabilitaCampos();
				tfNomeEditora.setEnabled(true);
			}

		};


		ActionListener rbExclusaoListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0){
				final EditoraDao dao =new EditoraDao();


				cbEditora.setVisible(true);
				btnConsultar.setVisible(true);
				btnAlterar.setVisible(false);
				btnExcluir.setVisible(true);
				btnAdicionar.setVisible(false);

				limpaCampo();
				atualizaCBEditora();
				inicializaCampo();

				desabilitaCampos();
				tfIdEditora.setEnabled(true);

			}

		};


		rdbtnCadastro.addActionListener(rbCadastroListener);
		rdbtnEditaID.addActionListener(rbEdicaoListenerID);
		rdbtnEditaNome.addActionListener(rbEdicaoListenerNome);
		rdbtnExclusao.addActionListener(rbExclusaoListener);


		ActionListener cadastroListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Editora editora=new Editora();
				if(!tfNomeEditora.getText().isEmpty() && !tfDescricaoEditora.getText().isEmpty()){
					editora.setNome(tfNomeEditora.getText());
					editora.setDescricao(tfDescricaoEditora.getText());

					boolean inserido = inserirEditora(editora);

					if(inserido){
						JOptionPane.showMessageDialog(null,"Editora inserida com sucesso",
								"Êxito",JOptionPane.INFORMATION_MESSAGE);
						limpaCampo();
						atualizaCBEditora();

					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Editora não inserida",
							"Atenção",JOptionPane.CANCEL_OPTION);
				}

			}



		};


		ActionListener buscaListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Editora editora=new Editora();

				Editora editoraConsultada=new Editora();
				if(!rdbtnEditaNome.isSelected()){
					editora.setIDEditora(Integer.parseInt(tfIdEditora.getText()));
					editoraConsultada=consultarEditoraID(editora);
				}

				if(rdbtnEditaNome.isSelected()){
					editora.setNome(tfNomeEditora.getText());
					editoraConsultada=consultarEditoraNome(editora);
				}

				tfIdEditora.setText(String.valueOf(editoraConsultada.getIDEditora()));
				tfNomeEditora.setText(editoraConsultada.getNome());
				tfDescricaoEditora.setText(editoraConsultada.getDescricao());

				if(rdbtnEditaID.isSelected() && (!tfNomeEditora.getText().isEmpty())){
					habilitaCampos();
					tfIdEditora.setEnabled(false);
				}

				if((rdbtnEditaNome.isSelected()&&(!tfNomeEditora.getText().isEmpty()))){
					habilitaCampos();
					tfIdEditora.setEnabled(false);
					tfNomeEditora.setEnabled(false);
				}
			}

		};

		ActionListener exclusaoListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Editora editora=new Editora();

				editora.setIDEditora(Integer.parseInt(tfIdEditora.getText()));

				if((!tfNomeEditora.getText().isEmpty()&&(!tfIdEditora.getText().isEmpty()))){

					boolean excluido = excluirEditora(editora);

					if(excluido){
						JOptionPane.showMessageDialog(null,"Editora excluída com sucesso",
								"Êxito",JOptionPane.INFORMATION_MESSAGE);
						limpaCampo();
						inicializaCampo();
						atualizaCBEditora();
					}
					else{
						JOptionPane.showMessageDialog(null,"Editora não excluída",
								"Atenção",JOptionPane.CANCEL_OPTION);
						inicializaCampo();
						limpaCampo();
					}
				}

				else{
					JOptionPane.showMessageDialog(null,"Faça a busca",
							"Atenção",JOptionPane.CANCEL_OPTION);
					inicializaCampo();
				}
			}
		};


		ActionListener edicaoListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Editora editora=new Editora();

				if((!tfNomeEditora.getText().isEmpty()&&(!tfDescricaoEditora.getText().isEmpty()))){
					habilitaCampos();
					tfIdEditora.setEnabled(false);

					if(rdbtnEditaID.isSelected()){
						editora.setIDEditora(Integer.parseInt(tfIdEditora.getText()));
					}


					editora.setNome(tfNomeEditora.getText());
					editora.setDescricao(tfDescricaoEditora.getText());


					boolean editado = false;
					if(rdbtnEditaID.isSelected()){
						editado=atualizarEditoraID(editora);
					}

					else{
						editado=atualizarEditoraNome(editora);
					}


					if(editado){
						JOptionPane.showMessageDialog(null,"Editora atualizada com sucesso","Êxito",JOptionPane.INFORMATION_MESSAGE);
						limpaCampo();
						atualizaCBEditora();
						inicializaCampo();

					}
					else{
						JOptionPane.showMessageDialog(null,"Editora não atualizada","Atenção",JOptionPane.CANCEL_OPTION);
						inicializaCampo();
					}
				}  
				else{
					JOptionPane.showMessageDialog(null,"Editora não atualizada, verifique os campos informados\nou se a busca foi realizada.","Atenção",JOptionPane.CANCEL_OPTION);
					inicializaCampo();
					limpaCampo();
				}


				if(rdbtnEditaID.isSelected()){ 
					preparaNovaPesquisaID();

				}
				else{
					preparaNovaPesquisaNome();
				}
			}

		};

		btnAdicionar.addActionListener(cadastroListener);
		btnConsultar.addActionListener(buscaListener);
		btnAlterar.addActionListener(edicaoListener);
		btnExcluir.addActionListener(exclusaoListener);
	}


	public void limpaCampo(){
		tfIdEditora.setText("");
		tfNomeEditora.setText("");
		tfDescricaoEditora.setText("");

	}



	public void atualizaCBEditora(){
		List<Editora> listaEditora = new ArrayList<Editora>();
		final EditoraDao dao =new EditoraDao();
		try {
			listaEditora=dao.ListaDeEditoras();
		} catch (EditoraDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(cbEditora!=null){
			cbEditora.removeAllItems();
		}

		for (Editora editora:listaEditora){
			cbEditora.addItem("ID:"+editora.getIDEditora()+" Nome:"+editora.getNome());
		}
	}


	public void preparaNovaPesquisaID(){
		tfIdEditora.setEnabled(true);
		tfNomeEditora.setEnabled(false);
		tfDescricaoEditora.setEnabled(false);
	}


	public void preparaNovaPesquisaNome(){
		tfIdEditora.setText("");
		tfIdEditora.setEnabled(false);
		tfNomeEditora.setEnabled(true);
		tfDescricaoEditora.setEnabled(false);
	}


	public void habilitaCampos(){
		tfIdEditora.setEnabled(true);
		tfNomeEditora.setEnabled(true);
		tfDescricaoEditora.setEnabled(true);
	}


	public void desabilitaCampos(){
		tfIdEditora.setEnabled(false);
		tfNomeEditora.setEnabled(false);
		tfDescricaoEditora.setEnabled(false);
	}



	public void inicializaCampo(){
		tfIdEditora.setText("0");
	}

	@Override
	public boolean inserirEditora(Editora editora) {
		boolean inserido=false;
		EditoraDao dao=new EditoraDao();
		try {
			inserido = dao.InsereEditora(editora);
		} catch (EditoraDaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		return inserido;
	}



	@Override
	public boolean atualizarEditoraID(Editora editora) {
		boolean editado=false;
		EditoraDao dao=new EditoraDao();
		try {
			editado=dao.AtualizarEditoraID(editora);
		} catch (EditoraDaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return editado;
	}


	@Override
	public boolean atualizarEditoraNome(Editora editora) {
		boolean editado=false;
		EditoraDao dao=new EditoraDao();
		try {
			editado=dao.AtualizarEditoraNome(editora);
		} catch (EditoraDaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return editado;
	}



	@Override
	public Editora consultarEditoraID(Editora editora) {
		Editora editoraConsultada=new Editora();
		EditoraDao dao=new EditoraDao();
		try {
			editoraConsultada=dao.consultaEditoraID(editora);
		} catch (EditoraDaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		return editoraConsultada;
	}


	@Override
	public Editora consultarEditoraNome(Editora editora) {
		Editora editoraConsultada=new Editora();
		EditoraDao dao=new EditoraDao();
		try {
			editoraConsultada=dao.consultaEditoraNome(editora);
		} catch (EditoraDaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		return editoraConsultada;
	}

	@Override
	public boolean excluirEditora(Editora editora) {
		boolean excluido=false;
		EditoraDao dao=new EditoraDao();
		try {
			excluido = dao.ExcluirEditora(editora);
		} catch (EditoraDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excluido;
	}
}







