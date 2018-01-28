package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Controller.ControllerAcervo;
import Dao.AcervoDao;
import Dao.AcervoDaoException;
import Entidades.Acervo;
import Entidades.Autor;
import Entidades.Editora;
import Entidades.Livro;

import javax.swing.JRadioButton;
import javax.swing.JSeparator;

public class AcervoView extends JFrame implements ControllerAcervo {

	private JPanel contentPane;
	private final JComboBox cbLivro = new JComboBox();
	private final JComboBox cbEditora = new JComboBox();
	private final JComboBox cbAutor = new JComboBox();
	private Acervo ad=new Acervo();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AcervoView frame = new AcervoView();
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
	public AcervoView() {
		super("Manutenção do Cadastro de Acervos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLivro = new JLabel("Livro*");
		lblLivro.setBounds(10, 42, 46, 14);
		contentPane.add(lblLivro);


		cbLivro.setBounds(69, 39, 642, 20);
		contentPane.add(cbLivro);

		JLabel lblEditora = new JLabel("Editora*");
		lblEditora.setBounds(10, 143, 46, 14);
		contentPane.add(lblEditora);

		cbEditora.setBounds(69, 140, 642, 20);
		contentPane.add(cbEditora);

		final JLabel lblAutor = new JLabel("Autor*");
		lblAutor.setBounds(10, 90, 46, 14);
		contentPane.add(lblAutor);

		cbAutor.setBounds(69, 87, 642, 20);
		contentPane.add(cbAutor);

		final JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(55, 415, 120, 23);
		contentPane.add(btnAdicionar);

		final JButton btnExcluirLivro = new JButton("Excluir Livro");
		btnExcluirLivro.setBounds(236, 415, 120, 23);
		contentPane.add(btnExcluirLivro);

		final JButton btnExcluirEditora = new JButton("Excluir Editora");
		btnExcluirEditora.setBounds(418, 415, 120, 23);
		contentPane.add(btnExcluirEditora);

		final JButton btnExcluirAutor = new JButton("Excluir Autor");
		btnExcluirAutor.setBounds(592, 415, 119, 23);
		contentPane.add(btnExcluirAutor);

		JRadioButton rdbtnCadastro = new JRadioButton("Cadastrar");
		rdbtnCadastro.setBounds(66, 330, 138, 23);
		contentPane.add(rdbtnCadastro);

		JRadioButton rdbtnExcluirLivro = new JRadioButton("Excluir Livro");
		rdbtnExcluirLivro.setBounds(605, 330, 132, 23);
		contentPane.add(rdbtnExcluirLivro);

		JRadioButton rdbtnExcluirEditora = new JRadioButton("Excluir Editora");
		rdbtnExcluirEditora.setBounds(253, 330, 136, 23);
		contentPane.add(rdbtnExcluirEditora);

		JRadioButton rdbtnExcluirAutor = new JRadioButton("Excluir Autor");
		rdbtnExcluirAutor.setBounds(430, 330, 132, 23);
		contentPane.add(rdbtnExcluirAutor);


		ButtonGroup GrupoRB = new ButtonGroup(); //usado para agrupar os radios buttons, quando é clicado em um ele desmarca os outros

		GrupoRB.add(rdbtnCadastro);
		GrupoRB.add(rdbtnExcluirLivro);
		GrupoRB.add(rdbtnExcluirEditora);
		GrupoRB.add(rdbtnExcluirAutor);

		JSeparator separator = new JSeparator();
		separator.setBounds(-4, 392, 788, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(-4, 273, 788, 2);
		contentPane.add(separator_1);

		JLabel lblOpcoes = new JLabel("Menu de Sele\u00E7\u00E3o");
		lblOpcoes.setBounds(10, 273, 120, 14);
		contentPane.add(lblOpcoes);

		JLabel lblMensagem = new JLabel("Campos com * s\u00E3o obrigat\u00F3rios para inser\u00E7\u00E3o");
		lblMensagem.setBounds(264, 273, 298, 14);
		contentPane.add(lblMensagem);

		btnAdicionar.setVisible(false);
		btnExcluirLivro.setVisible(false);
		btnExcluirEditora.setVisible(false);
		btnExcluirAutor.setVisible(false);

		atualizaCBLivro();
		atualizaCBEditora();
		atualizaCBAutor();


		ActionListener rbCadastroListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cbLivro.setVisible(true);
				cbEditora.setVisible(true);
				cbAutor.setVisible(true);


				btnAdicionar.setVisible(true);
				btnExcluirLivro.setVisible(false);
				btnExcluirEditora.setVisible(false);
				btnExcluirAutor.setVisible(false);
			}
		};


		ActionListener rbExclusaoLivro=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				cbLivro.setVisible(true);
				cbEditora.setVisible(false);
				cbAutor.setVisible(false);
				atualizaCBLivro();
				atualizaCBEditora();
				atualizaCBAutor();


				btnAdicionar.setVisible(false);
				btnExcluirLivro.setVisible(true);
				btnExcluirEditora.setVisible(false);
				btnExcluirAutor.setVisible(false);
			}
		};



		ActionListener rbExclusaoEditora=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cbLivro.setVisible(false);
				cbEditora.setVisible(true);
				cbAutor.setVisible(false);
				atualizaCBLivro();
				atualizaCBEditora();
				atualizaCBAutor();




				btnAdicionar.setVisible(false);
				btnExcluirLivro.setVisible(false);
				btnExcluirEditora.setVisible(true);
				btnExcluirAutor.setVisible(false);
			}
		};



		ActionListener rbExclusaoAutor=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cbLivro.setVisible(false);
				cbEditora.setVisible(false);
				cbAutor.setVisible(true);
				atualizaCBLivro();
				atualizaCBEditora();
				atualizaCBAutor();


				btnAdicionar.setVisible(false);
				btnExcluirLivro.setVisible(false);
				btnExcluirEditora.setVisible(false);
				btnExcluirAutor.setVisible(true);
			}
		};


		final AcervoDao dao =new AcervoDao();



		rdbtnCadastro.addActionListener(rbCadastroListener);
		rdbtnExcluirLivro.addActionListener(rbExclusaoLivro);
		rdbtnExcluirEditora.addActionListener(rbExclusaoEditora);
		rdbtnExcluirAutor.addActionListener(rbExclusaoAutor);


		final ActionListener listenerCadastro = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Acervo ad=new Acervo();



				String livro=cbLivro.getSelectedItem().toString();
				String[]vetL=livro.split("-");
				int codLivro=Integer.parseInt(vetL[0]);

				String editora=cbEditora.getSelectedItem().toString();
				String[]vetE=editora.split("-");
				int codEditora=Integer.parseInt(vetE[0]);

				String autor=cbAutor.getSelectedItem().toString();
				String[]vetA=autor.split("-");
				int codAutor=Integer.parseInt(vetA[0]);

				ad.setIDLivro(codLivro);
				ad.setIDEditora(codEditora);
				ad.setIDAutor(codAutor);

				boolean inserir = false;


				if((ad.getIDAutor()!=0)&&(ad.getIDEditora()!=0)&&(ad.getIDLivro()!=0)){
					inserir=inserirAcervo(ad);

					if(inserir){
						JOptionPane.showMessageDialog(null,"Dados do Acervo inseridos com sucesso","" +
								"Êxito",JOptionPane.INFORMATION_MESSAGE);
					}

					else{
						JOptionPane.showMessageDialog(null,"Dados do Acervo não inseridos","Atenção",JOptionPane.CANCEL_OPTION);
					}
				}

				else{
					JOptionPane.showMessageDialog(null,"Não há dados suficientes para que ocorra a inserção do acervo","Atenção",JOptionPane.CANCEL_OPTION);
				}
			}
		};

		final ActionListener listenerExclusaoLivro = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {


				String livro=cbLivro.getSelectedItem().toString();
				String[]vetL=livro.split("-");
				int codLivro=Integer.parseInt(vetL[0]);

				ad.setIDLivro(codLivro);

				boolean excluido = false;

				if(ad.getIDLivro()!=0){
					excluido=excluirLivro(ad);

					if(excluido){
						JOptionPane.showMessageDialog(null,"Dados do Livro excluídos com sucesso","" +
								"Êxito",JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null,"Dados do Livro não excluídos","Atenção",JOptionPane.CANCEL_OPTION);
					}
				}

				else{
					JOptionPane.showMessageDialog(null,"Não há dados de livros cadastrados para a realização de sua exclusão","Atenção",JOptionPane.CANCEL_OPTION);
				}

			}

		};

		final ActionListener listenerExclusaoEditora = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Acervo ad=new Acervo();



				String editora=cbEditora.getSelectedItem().toString();
				String[]vetL=editora.split("-");
				int codEditora=Integer.parseInt(vetL[0]);

				ad.setIDEditora(codEditora);

				boolean excluido = false;

				if(ad.getIDEditora()!=0){
					excluido=excluirEditora(ad);
					if(excluido){
						JOptionPane.showMessageDialog(null,"Dados da Editora excluídos com sucesso","" +
								"Êxito",JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null,"Dados da Editora não excluídos","Atenção",JOptionPane.CANCEL_OPTION);
					}
				}

				else{
					JOptionPane.showMessageDialog(null,"Não há dados de editoras cadastradas para a realização de sua exclusão","Atenção",JOptionPane.CANCEL_OPTION);
				}

			}

		};

		final ActionListener listenerExclusaoAutor = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Acervo ad=new Acervo();


				String autor=cbAutor.getSelectedItem().toString();
				String[]vetL=autor.split("-");
				int codAutor=Integer.parseInt(vetL[0]);

				ad.setIDAutor(codAutor);


				boolean excluido = false;

				if(ad.getIDAutor()!=0){
					excluido=excluirAutor(ad);

					if(excluido){
						JOptionPane.showMessageDialog(null,"Dados do Autor excluídos com sucesso","" +
								"Êxito",JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null,"Dados do Autor não excluídos","Atenção",JOptionPane.CANCEL_OPTION);
					}
				}

				else{
					JOptionPane.showMessageDialog(null,"Não há dados de autores cadastrados para a realização de sua exclusão","Atenção",JOptionPane.CANCEL_OPTION);
				}

			}

		};



		btnAdicionar.addActionListener(listenerCadastro);
		btnExcluirLivro.addActionListener(listenerExclusaoLivro);
		btnExcluirEditora.addActionListener(listenerExclusaoEditora);
		btnExcluirAutor.addActionListener(listenerExclusaoAutor);
	}



	public void atualizaCBLivro(){

		final AcervoDao dao =new AcervoDao();

		List<Livro> listaLivro = new ArrayList<Livro>();
		try {
			listaLivro=dao.ListaDeLivros();
		} catch (AcervoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(cbLivro!=null){
			cbLivro.removeAllItems();
		}

		for (Livro livro:listaLivro){
			cbLivro.addItem(livro.getIDLivro()+"-" +" Título:"+livro.getTitulo()+" Edição:"+livro.getEdicao());
		}

	}



	public void atualizaCBEditora(){

		final AcervoDao dao =new AcervoDao();

		List<Editora> listaEditora=new ArrayList<Editora>();
		try {
			listaEditora=dao.ListaDeEditoras();
		} catch (AcervoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(cbEditora!=null){
			cbEditora.removeAllItems();
		}

		for (Editora editora:listaEditora){
			cbEditora.addItem(editora.getIDEditora()+"-"+" Nome:"+editora.getNome());
		}


	}


	public void atualizaCBAutor(){

		final AcervoDao dao =new AcervoDao();


		List<Autor> listaAutor=new ArrayList<Autor>();
		try {
			listaAutor=dao.ListaDeAutores();
		} catch (AcervoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(cbAutor!=null){
			cbAutor.removeAllItems();
		}

		for (Autor autor:listaAutor){
			cbAutor.addItem(autor.getIDAutor()+"-"+" Nome:"+autor.getNome());
		}

	}

	@Override
	public boolean inserirAcervo(Acervo acervo) {
		boolean inserir=false;
		AcervoDao dao=new AcervoDao();
		try {
			inserir=dao.InsereLivroEditoraAutor(acervo);
		} catch (AcervoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		return inserir;
	}


	@Override
	public boolean excluirLivro(Acervo acervo) {
		boolean excluido=false;
		AcervoDao dao=new AcervoDao();
		try {
			excluido=dao.ExcluirLivro(ad);
		} catch (AcervoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		return excluido;
	}

	@Override
	public boolean excluirEditora(Acervo acervo) {
		boolean excluido=false;
		AcervoDao dao=new AcervoDao();
		try {
			excluido=dao.ExcluirEditora(acervo);
		} catch (AcervoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		return excluido;
	}

	@Override
	public boolean excluirAutor(Acervo acervo) {
		boolean excluido=false;
		AcervoDao dao=new AcervoDao();
		try {
			excluido=dao.ExcluirAutor(acervo);
		} catch (AcervoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		return excluido;
	}
}





