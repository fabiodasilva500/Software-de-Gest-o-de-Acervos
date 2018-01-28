package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;


import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import Dao.EmprestimoDao;
import Dao.EmprestimoDaoException;
import Entidades.Emprestimo;


public class ServicoDeEmailView extends JFrame {

	private JPanel contentPane;
	private JTextField tfRemetente;
	private JPasswordField tfSenha;
	private JTextField tfDestinatario;
	private JTextField tfConteudo;
	private final JButton btnEnviar = new JButton("Enviar");
	private JTextField tfAssunto;
	private JLabel lblTitpo;
	private JComboBox cbEmail;
	private final JComboBox cbDados = new JComboBox();
	private JTextField tfData;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServicoDeEmailView frame = new ServicoDeEmailView();
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
	public ServicoDeEmailView() {
		super("Manutenção de Noficações de Usuários sobre Livros em Atraso");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEmailRemetente = new JLabel("Email Remetente");
		lblEmailRemetente.setBounds(10, 173, 101, 14);
		contentPane.add(lblEmailRemetente);

		JLabel lblSenha = new JLabel("Senha do Email Remetente");
		lblSenha.setBounds(10, 217, 193, 14);
		contentPane.add(lblSenha);

		JLabel lblEmailDeDestinatrio = new JLabel("Email de Destinat\u00E1rio");
		lblEmailDeDestinatrio.setBounds(10, 319, 137, 14);
		contentPane.add(lblEmailDeDestinatrio);

		tfRemetente = new JTextField();
		tfRemetente.setBounds(121, 170, 646, 20);
		contentPane.add(tfRemetente);
		tfRemetente.setColumns(10);

		tfSenha = new JPasswordField();
		tfSenha.setBounds(213, 214, 186, 20);
		contentPane.add(tfSenha);
		tfSenha.setColumns(10);

		tfDestinatario = new JTextField();
		tfDestinatario.setBounds(157, 316, 617, 20);
		contentPane.add(tfDestinatario);
		tfDestinatario.setColumns(10);

		tfConteudo = new JTextField();
		tfConteudo.setColumns(10);
		tfConteudo.setBounds(10, 448, 764, 69);
		contentPane.add(tfConteudo);

		JLabel lblConteudo = new JLabel("Conte\u00FAdo do Email");
		lblConteudo.setBounds(288, 423, 193, 14);
		contentPane.add(lblConteudo);

		btnEnviar.setBounds(146, 528, 89, 23);
		contentPane.add(btnEnviar);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 371, 808, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 273, 914, 2);
		contentPane.add(separator_1);

		tfAssunto = new JTextField();
		tfAssunto.setBounds(197, 384, 498, 20);
		contentPane.add(tfAssunto);
		tfAssunto.setColumns(10);

		lblTitpo = new JLabel("Provedor de email usado");
		lblTitpo.setBounds(10, 122, 167, 14);
		contentPane.add(lblTitpo);

		cbEmail = new JComboBox();
		cbEmail.setModel(new DefaultComboBoxModel(new String[] {"gmail", "hotmail"}));
		cbEmail.setBounds(213, 119, 143, 20);
		contentPane.add(cbEmail);

		JLabel lblAssunto = new JLabel("Assunto");
		lblAssunto.setBounds(104, 387, 78, 14);
		contentPane.add(lblAssunto);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(532, 528, 89, 23);
		contentPane.add(btnVoltar);

		JLabel lblData = new JLabel("Data desejada");
		lblData.setBounds(10, 22, 113, 14);
		contentPane.add(lblData);

		final JComboBox cbDados = new JComboBox();
		cbDados.setBounds(10, 58, 757, 20);
		contentPane.add(cbDados);

		tfData = new JTextField();
		tfData.setBounds(110, 19, 125, 20);
		contentPane.add(tfData);
		tfData.setColumns(10);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(268, 18, 89, 23);
		contentPane.add(btnConsultar);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(-257, 101, 1041, 2);
		contentPane.add(separator_2);


		ActionListener envio=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)  {
				cbEmail.setEnabled(false);
				Properties dados = new Properties();

				if(!tfRemetente.getText().isEmpty() && !tfSenha.getText().isEmpty() && !tfDestinatario.getText().isEmpty() && !tfAssunto.getText().isEmpty() && !tfConteudo.getText().isEmpty()){
					if(cbEmail.getSelectedItem()=="gmail"){
						dados.put("mail.smtp.host", "smtp."+cbEmail.getSelectedItem().toString()+".com");
						dados.put("mail.smtp.socketFactory.port", "465");
						dados.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
						dados.put("mail.smtp.auth", "true");
						dados.put("mail.smtp.port", "465");
						Session conteudo = Session.getDefaultInstance(dados,
								new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() 
							{

								return new PasswordAuthentication(tfRemetente.getText(), tfSenha.getText());
							}
						});

						conteudo.setDebug(true);

						try {

							Message message = new MimeMessage(conteudo);

							Address[] toUser = InternetAddress //Destinatário(s)
									.parse(tfDestinatario.getText());  

							message.setRecipients(Message.RecipientType.TO, toUser);
							message.setSubject(tfAssunto.getText());//Assunto
							message.setText(tfConteudo.getText());
							Transport.send(message);
							JOptionPane.showMessageDialog(null,"Email enviado com sucesso.","Êxito",JOptionPane.INFORMATION_MESSAGE);


						} catch (MessagingException enviado) {
							JOptionPane.showMessageDialog(null,"Email não enviado, verifique os campos informados.","Atenção",JOptionPane.CANCEL_OPTION);
							throw new RuntimeException(enviado);

						}

						limpaCampo();
					}

					else{
						dados.put("mail.transport.protocol", "smtp");
						dados.put("mail.smtp.host", "smtp.live.com");
						dados.put("mail.smtp.socketFactory.port", "587");
						dados.put("mail.smtp.socketFactory.fallback", "false");
						dados.put("mail.smtp.starttls.enable", "true");
						dados.put("mail.smtp.auth", "true");
						dados.put("mail.smtp.port", "587");

						Session conteudo = Session.getDefaultInstance(dados,
								new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() 
							{
								return new PasswordAuthentication(tfRemetente.getText(), tfSenha.getText());
							}
						});

						conteudo.setDebug(true);

						try {

							Message message = new MimeMessage(conteudo);
							message.setFrom(new InternetAddress(tfRemetente.getText())); 
							message.setRecipients(Message.RecipientType.TO, 
									InternetAddress.parse(tfDestinatario.getText())); 
							message.setSubject(tfAssunto.getText());
							message.setText(tfConteudo.getText());
							Transport.send(message);
							JOptionPane.showMessageDialog(null,"Email enviado com sucesso.","Êxito",JOptionPane.INFORMATION_MESSAGE);
							limpaCampo();

						} catch (MessagingException envio) {
							JOptionPane.showMessageDialog(null,"Email não enviado, por favor verifique os campos informados.","Atenção",JOptionPane.CANCEL_OPTION);
							throw new RuntimeException(envio);
						}
					}

				}
				else{
					JOptionPane.showMessageDialog(null,"Para que o email seja enviado todos os campos devem ser preenchidos.","Atenção",JOptionPane.CANCEL_OPTION);

				}
			}

		};


		ActionListener voltar=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ServicoDeEmailView.this.dispose();
			}
		};


		ActionListener consultarDados=new ActionListener(){

			public void actionPerformed(ActionEvent e){
				final EmprestimoDao empDao=new EmprestimoDao();

				limpaCampo();
				cbDados.removeAllItems();


				List<String>listaEmprestimosData=new ArrayList<String>();
				Emprestimo emprestimo=new Emprestimo();

				SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");   
				Calendar c = Calendar.getInstance(); 
				c.add(Calendar.DAY_OF_MONTH, 0);  
				Date data_atual = null;
				Date data_requerida = null;

				try {
					data_atual=sd.parse(sd.format(c.getTime()));
					data_requerida=sd.parse(tfData.getText());

				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}



				if(!tfData.getText().isEmpty() && (data_requerida.before(data_atual))){
					emprestimo.setData(tfData.getText());

					try {
						listaEmprestimosData= empDao.livrosEmAtraso(emprestimo);
					} catch (EmprestimoDaoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					for (String emp: listaEmprestimosData){
						cbDados.addItem(emp);
					}
				}

				else{
					JOptionPane.showMessageDialog(null,"A data não foi informada ou não é inferior a data atual, por favor verifique o campo inserido.","Atenção",JOptionPane.CANCEL_OPTION);
				}
			}

		};

		btnConsultar.addActionListener(consultarDados);

		btnEnviar.addActionListener(envio);
		btnVoltar.addActionListener(voltar);
	}


	public void limpaCampo(){
		tfDestinatario.setText("");
		tfAssunto.setText("");
		tfConteudo.setText("");
	}
}














