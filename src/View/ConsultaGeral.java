package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import Dao.AcervoDao;
import Dao.AcervoDaoException;
import Dao.EmprestimoDao;
import Dao.EmprestimoDaoException;
import Entidades.Emprestimo;
import Entidades.Livro;

import javax.swing.JSeparator;
import javax.swing.JTextField;

public class ConsultaGeral extends JFrame {
	private JTextField tfDados;
	private JTextField tfData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaGeral frame = new ConsultaGeral();
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
	public ConsultaGeral() {
		super("Elaboração de consultas gerais sobre os dados do sistema");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);

		JComboBox cbEmprestimo = new JComboBox();
		cbEmprestimo.setBounds(151, 80, 592, 20);
		getContentPane().add(cbEmprestimo);

		JLabel lblAndamento = new JLabel("Dados");
		lblAndamento.setBounds(63, 83, 46, 14);
		getContentPane().add(lblAndamento);

		JSeparator separator = new JSeparator();
		separator.setBounds(-10, 28, 808, 2);
		getContentPane().add(separator);

		JLabel lblEmprestimos = new JLabel("Empr\u00E9stimos em andamento ");
		lblEmprestimos.setBounds(25, 28, 176, 14);
		getContentPane().add(lblEmprestimos);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(-10, 151, 856, 2);
		getContentPane().add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(-10, 267, 808, 2);
		getContentPane().add(separator_2);

		JLabel lblLivros = new JLabel("Editora(s) e autor(es) do livro desejado");
		lblLivros.setBounds(26, 415, 244, 14);
		getContentPane().add(lblLivros);

		final JComboBox cbAcervo = new JComboBox();
		cbAcervo.setBounds(151, 503, 592, 20);
		getContentPane().add(cbAcervo);

		JLabel lblLivro = new JLabel("Insira o  ID do Livro");
		lblLivro.setBounds(87, 456, 145, 17);
		getContentPane().add(lblLivro);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(-10, 415, 856, 2);
		getContentPane().add(separator_3);

		tfDados = new JTextField();
		tfDados.setBounds(252, 454, 86, 20);
		getContentPane().add(tfDados);
		tfDados.setColumns(10);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(378, 450, 89, 23);
		getContentPane().add(btnConsultar);

		JLabel lblReservados = new JLabel("Livros Reservados");
		lblReservados.setBounds(25, 151, 176, 14);
		getContentPane().add(lblReservados);

		JComboBox cbReserva = new JComboBox();
		cbReserva.setBounds(151, 201, 592, 20);
		getContentPane().add(cbReserva);

		JLabel lblReserva = new JLabel("Dados");
		lblReserva.setBounds(63, 204, 46, 14);
		getContentPane().add(lblReserva);

		JLabel lblRealizados = new JLabel("Devolu\u00E7\u00F5es e expira\u00E7\u00F5es de reservas previstas por data");
		lblRealizados.setBounds(25, 268, 421, 14);
		getContentPane().add(lblRealizados);

		final JComboBox cbData = new JComboBox();
		cbData.setBounds(151, 354, 592, 20);
		getContentPane().add(cbData);

		JLabel lblData = new JLabel("Insira a Data desejada");
		lblData.setBounds(87, 307, 145, 14);
		getContentPane().add(lblData);

		tfData = new JTextField();
		tfData.setBounds(252, 301, 86, 20);
		getContentPane().add(tfData);
		tfData.setColumns(10);

		JButton btnConsultarData = new JButton("Consultar");
		btnConsultarData.setBounds(378, 303, 89, 23);
		getContentPane().add(btnConsultarData);

		final EmprestimoDao dao=new EmprestimoDao();

		List<String>listaEmprestimo=new ArrayList<String>();
		try {
			listaEmprestimo= dao.geraLista();
		} catch (EmprestimoDaoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		for (String usuario: listaEmprestimo){
			cbEmprestimo.addItem(usuario);
		}



		final EmprestimoDao daoR=new EmprestimoDao();

		List<String>listaEmprestimoReservados=new ArrayList<String>();
		try {
			listaEmprestimo= daoR.geraListaReservados();
		} catch (EmprestimoDaoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		for (String usuario: listaEmprestimo){
			cbReserva.addItem(usuario);
		}


		ActionListener consulta=new ActionListener(){

			public void actionPerformed(ActionEvent e){
				final AcervoDao acerDao=new AcervoDao();
				cbAcervo.removeAllItems();

				List<String>listaAcervo=new ArrayList<String>();
				Livro livro=new Livro();
				if(!tfDados.getText().isEmpty()){
					livro.setIDLivro(Integer.parseInt(tfDados.getText()));


					try {
						listaAcervo= acerDao.geraLista(livro);
					} catch (AcervoDaoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					for (String l: listaAcervo){
						cbAcervo.addItem(l);
					}
				}

				else{
					JOptionPane.showMessageDialog(null,"ID do livro não informado, por favor digite-o","Atenção",JOptionPane.CANCEL_OPTION);
				}
			}
		};


		btnConsultar.addActionListener(consulta);

		ActionListener consultaData=new ActionListener(){

			public void actionPerformed(ActionEvent e){
				final EmprestimoDao empDao=new EmprestimoDao();
				cbData.removeAllItems();

				List<String>listaEmprestimosData=new ArrayList<String>();
				Emprestimo emprestimo=new Emprestimo();
				if(!tfData.getText().isEmpty()){
					emprestimo.setData(tfData.getText());


					try {
						listaEmprestimosData= empDao.geraListaData(emprestimo);
					} catch (EmprestimoDaoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					for (String emp: listaEmprestimosData){
						cbData.addItem(emp);
					}
				}

				else{
					JOptionPane.showMessageDialog(null,"Data não informada, por favor digite-a","Atenção",JOptionPane.CANCEL_OPTION);
				}
			}
		};

		btnConsultarData.addActionListener(consultaData);

	}
}

