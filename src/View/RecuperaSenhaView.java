package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import Controller.ControllerAcesso;
import Dao.AcessoLoginDao;
import Dao.AcessoLoginDaoException;
import Entidades.Acesso;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;

public class RecuperaSenhaView extends JFrame implements ControllerAcesso {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfCPF;
	private JTextField tfLogin;
	private JPasswordField tfSenha;

	/**
	 * Launch the application.
	 */



	private void verificaInputCPF(KeyEvent key){
		char sub = key.getKeyChar();	
		if(Character.isDigit(sub) == false){
			key.consume();			
		}

		if(tfCPF.getText().length() >= 11){
			key.consume();			
		}
	}


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecuperaSenhaView frame = new RecuperaSenhaView();
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
	public RecuperaSenhaView() {
		super("Esqueci Minha Senha");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome completo");
		lblNome.setBounds(33, 146, 219, 14);
		contentPane.add(lblNome);

		JLabel lblCPF = new JLabel("Insira o seu CPF");
		lblCPF.setBounds(33, 35, 152, 14);
		contentPane.add(lblCPF);

		tfNome = new JTextField();
		tfNome.setColumns(10);
		tfNome.setBounds(266, 143, 408, 20);
		contentPane.add(tfNome);

		tfCPF = new JTextField();
		tfCPF.setColumns(10);
		tfCPF.setBounds(266, 32, 245, 20);
		contentPane.add(tfCPF);

		tfCPF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputCPF(tecla);
			}
		});


		JLabel lblCargo = new JLabel(" Cargo que voc\u00EA ocupa na biblioteca");
		lblCargo.setBounds(33, 201, 219, 14);
		contentPane.add(lblCargo);

		final JComboBox cbCargo = new JComboBox();
		cbCargo.setModel(new DefaultComboBoxModel(new String[] {"Administrativo", "Atendente", "Estagi\u00E1rio", ""}));
		cbCargo.setBounds(266, 198, 294, 20);
		contentPane.add(cbCargo);

		JLabel lblLogin = new JLabel("Login utilizado atualmente");
		lblLogin.setBounds(33, 261, 219, 14);
		contentPane.add(lblLogin);

		tfLogin = new JTextField();
		tfLogin.setColumns(10);
		tfLogin.setBounds(266, 258, 223, 20);
		contentPane.add(tfLogin);

		JLabel lblSenha = new JLabel("Senha utilizada atualmente");
		lblSenha.setBounds(33, 322, 219, 14);
		contentPane.add(lblSenha);

		tfSenha = new JPasswordField();
		tfSenha.setColumns(10);
		tfSenha.setBounds(266, 319, 163, 20);
		contentPane.add(tfSenha);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(163, 496, 89, 23);
		contentPane.add(btnConsultar);



		tfNome.setEnabled(false);
		cbCargo.setEnabled(false);
		tfLogin.setEnabled(false);
		tfSenha.setEnabled(false);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 462, 794, 5);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 84, 794, 5);
		contentPane.add(separator_1);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(471, 496, 89, 23);
		contentPane.add(btnVoltar);


		ActionListener buscaListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Acesso acesso=new Acesso();
				AcessoLoginDao dao=new AcessoLoginDao();

				Acesso acessoConsultado=new Acesso();
				acesso.setCpf(tfCPF.getText());
				acessoConsultado=consultarLogin(acesso);

				tfCPF.setText(acessoConsultado.getCpf());
				tfNome.setText(acessoConsultado.getNome());
				cbCargo.setSelectedItem(acessoConsultado.getCargo());
				tfLogin.setText(acessoConsultado.getLogin());
				tfSenha.setText(acessoConsultado.getSenha());



			}


		};
		btnConsultar.addActionListener(buscaListener);




		ActionListener voltar =new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AcessoLoginView().setVisible(true);
				RecuperaSenhaView.this.dispose();

			}

		};

		btnVoltar.addActionListener(voltar);


	}


	@Override
	public boolean inserirLogin(Acesso acesso) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean excluirLogin(Acesso acesso) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Acesso consultarLogin(Acesso acesso) {
		Acesso acessoConsultado=new Acesso();
		AcessoLoginDao dao=new AcessoLoginDao();
		try {
			acessoConsultado=dao.consultaDados(acesso);
		} catch (AcessoLoginDaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return acessoConsultado;

	}
}
