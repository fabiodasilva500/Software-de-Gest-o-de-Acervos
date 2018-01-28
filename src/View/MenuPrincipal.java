package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
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
	public MenuPrincipal() {
		super("Menu Principal");
		new AcessoLoginView().setVisible(false);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setLocation(275, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1000, 21);
		contentPane.add(menuBar);

		JMenuItem menuLivro = new JMenuItem("Livro");
		menuBar.add(menuLivro);


		JMenuItem menuUsuario = new JMenuItem("Usu\u00E1rio");
		menuBar.add(menuUsuario);


		JMenuItem menuEmprestimo = new JMenuItem("Empr\u00E9stimo");
		menuBar.add(menuEmprestimo);

		JMenuItem menuDevolucao = new JMenuItem("Devolu\u00E7\u00E3o");
		menuBar.add(menuDevolucao);

		JMenuItem menuEditora = new JMenuItem("Editora");
		menuBar.add(menuEditora);

		JMenuItem menuAutor = new JMenuItem("Autor");
		menuBar.add(menuAutor);

		JMenuItem menuAcervo = new JMenuItem("Acervo");
		menuBar.add(menuAcervo);

		JMenuItem menuTCC = new JMenuItem("TCC");
		menuBar.add(menuTCC);

		JMenuItem menuConsultas = new JMenuItem("Consultas");
		menuBar.add(menuConsultas);

		JMenuItem menuServicoEmail = new JMenuItem("Notificação individual");
		menuBar.add(menuServicoEmail);

		JMenuItem notificacaoAutomatica = new JMenuItem("Notificação coletiva");
		menuBar.add(notificacaoAutomatica);

		ActionListener listenerMenuLivro = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new LivroView().setVisible(true);
			}
		};

		ActionListener listenerMenuUsuario = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new UsuarioView().setVisible(true);
			}

		};

		ActionListener listenerMenuEmprestimo = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new EmprestimoView().setVisible(true);
			}
		};

		ActionListener listenerMenuDevolucao = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new DevolucaoView().setVisible(true);
			}
		};

		ActionListener listenerEditora = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new EditoraView().setVisible(true);
			}
		};

		ActionListener listenerAutor = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new AutorView().setVisible(true);
			}
		};


		ActionListener listenerAcervo = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new AcervoView().setVisible(true);
			}
		};


		ActionListener listenerTCC = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new TCCView();
			}
		};
		ActionListener listenerConsultas = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new ConsultaGeral().setVisible(true);
			}
		};



		ActionListener listenerAtraso = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new ServicoDeEmailView().setVisible(true);
			}
		};


		ActionListener listenerNotificacao = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new ServicoDeEmailAutomaticoView().setVisible(true);
			}
		};



		menuLivro.addActionListener(listenerMenuLivro);
		menuUsuario.addActionListener(listenerMenuUsuario);
		menuEmprestimo.addActionListener(listenerMenuEmprestimo);
		menuDevolucao.addActionListener(listenerMenuDevolucao);
		menuEditora.addActionListener(listenerEditora);
		menuAutor.addActionListener(listenerAutor);
		menuAcervo.addActionListener(listenerAcervo);
		menuTCC.addActionListener(listenerTCC);
		menuConsultas.addActionListener(listenerConsultas);
		menuServicoEmail.addActionListener(listenerAtraso);
		notificacaoAutomatica.addActionListener(listenerNotificacao);


	}
}
