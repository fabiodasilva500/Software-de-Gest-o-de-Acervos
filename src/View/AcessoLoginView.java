package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;

import Controller.ControllerAcesso;
import Dao.AcessoLoginDao;
import Dao.AcessoLoginDaoException;
import Entidades.Acesso;

import javax.swing.JSeparator;

public class AcessoLoginView extends JFrame  implements ControllerAcesso{

	private JPanel contentPane;
	private JTextField tfLogin;
	private JPasswordField tfSenha;
	private JButton btnNovoLogin = new JButton("N\u00E3o \u00E9 cadastrado? Clique aqui");
	private JSeparator separator;
	private JSeparator separator_1;
	/**
	 * Launch the application.
	 */


	private void verificaInputLogin(KeyEvent key){
		char sub = key.getKeyChar();		
		if(tfLogin.getText().length() >= 30){
			key.consume();			
		}
	}

	private void verificaInputSenha(KeyEvent key){
		char sub = key.getKeyChar();		
		if(tfSenha.getText().length() >= 20){
			key.consume();			
		}
	}



	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AcessoLoginView frame = new AcessoLoginView();
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
	public AcessoLoginView() {
		super("Bem vindo ao Sistema de Controle de Empréstimos da Biblioteca Etec e Fatec Zona Leste");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JButton btnAcessar = new JButton("Acessar");
		btnAcessar.setBounds(21, 437, 94, 23);
		contentPane.add(btnAcessar);

		tfLogin = new JTextField();
		tfLogin.setBounds(297, 119, 309, 20);
		contentPane.add(tfLogin);
		tfLogin.setColumns(10);

		tfLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputLogin(tecla);
			}
		});

		tfSenha = new JPasswordField();
		tfSenha.setBounds(297, 179, 224, 20);
		contentPane.add(tfSenha);
		tfSenha.setColumns(10);

		tfSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputSenha(tecla);
			}
		});


		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(177, 122, 46, 14);
		contentPane.add(lblLogin);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(177, 182, 46, 14);
		contentPane.add(lblSenha);


		btnNovoLogin.setBounds(354, 437, 211, 23);
		contentPane.add(btnNovoLogin);

		separator = new JSeparator();
		separator.setBounds(0, 69, 794, 5);
		contentPane.add(separator);

		separator_1 = new JSeparator();
		separator_1.setBounds(0, 299, 794, 5);
		contentPane.add(separator_1);

		JButton btnEsqueciMinhaSenha = new JButton("Esqueci Minha Senha");
		btnEsqueciMinhaSenha.setBounds(147, 437, 171, 23);
		contentPane.add(btnEsqueciMinhaSenha);

		JButton btnExcluirCadastro = new JButton("Exclus\u00E3o de Cadastro");
		btnExcluirCadastro.setBounds(599, 437, 159, 23);
		contentPane.add(btnExcluirCadastro);




		ActionListener login =new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new LoginCadastroView().setVisible(true);
				AcessoLoginView.this.dispose();
			}

		};

		btnNovoLogin.addActionListener(login);

		ActionListener buscaListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Acesso acesso=new Acesso();
				AcessoLoginDao dao=new AcessoLoginDao();

				Acesso acessoConsultado=new Acesso();
				acesso.setLogin(tfLogin.getText());
				acesso.setSenha(tfSenha.getText());
				acessoConsultado=consultarLogin(acesso);


				tfLogin.setText(acessoConsultado.getLogin());
				tfSenha.setText(acessoConsultado.getSenha());


				if(tfSenha.getText().equals(acessoConsultado.getSenha())){

					new MenuPrincipal().setVisible(true);
					AcessoLoginView.this.dispose();
				}
				else{
					JOptionPane.showMessageDialog(null,"Caro usuário seu login e senha estão incorretos, por favor verifique-os","Atenção",JOptionPane.CANCEL_OPTION);
				}
			}

		};

		btnAcessar.addActionListener(buscaListener);


		ActionListener RecuperaSenha =new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new RecuperaSenhaView().setVisible(true);
				AcessoLoginView.this.dispose();

			}

		};

		btnEsqueciMinhaSenha.addActionListener(RecuperaSenha);





		ActionListener ExcluirCadastro =new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new LoginExclusaoView().setVisible(true);
				AcessoLoginView.this.dispose();

			}

		};

		btnExcluirCadastro.addActionListener(ExcluirCadastro);

	}

	@Override
	public boolean inserirLogin(Acesso acesso) {
		return false;
	}

	@Override
	public boolean excluirLogin(Acesso acesso) {
		return false;
	}

	@Override
	public Acesso consultarLogin(Acesso acesso) {
		Acesso acessoConsultado=new Acesso();
		AcessoLoginDao dao=new AcessoLoginDao();
		try {
			acessoConsultado=dao.consultaLogin(acesso);
		} catch (AcessoLoginDaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return acessoConsultado;
	}

}


