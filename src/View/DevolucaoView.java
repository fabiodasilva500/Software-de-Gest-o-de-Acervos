package View;

import Controller.ControllerDevolucao;
import Dao.DevolucaoDao;
import Dao.DevolucaoDaoException;
import Entidades.Devolucao;
import Entidades.Emprestimo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JSeparator;


import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class DevolucaoView extends JFrame implements ControllerDevolucao{

	private JPanel contentPane;
	private JTextField tfIdEmprestimo;
	private JTextField tfDiasAtraso;
	private JTextField tfMulta;
	private final JComboBox cbAtraso = new JComboBox();

	private void verificaInputIdEmprestimo(KeyEvent key){
		char sub = key.getKeyChar();		
		if(Character.isDigit(sub) == false){
			key.consume();			
		}
	}
	private void verificaInputDias(KeyEvent key){
		char sub = key.getKeyChar();		
		if(Character.isDigit(sub) == false){
			key.consume();			
		}
	}
	private void verificaInputMulta(KeyEvent key){
		char sub = key.getKeyChar();		
		if(Character.isDigit(sub) == false	){
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
					DevolucaoView frame = new DevolucaoView();
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
	public DevolucaoView() {
		super("Manutenção do Cadastro de Devoluções");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setLocation(275, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIdEmprestimo = new JLabel("ID Empr\u00E9stimo*");
		lblIdEmprestimo.setBounds(10, 38, 89, 14);
		contentPane.add(lblIdEmprestimo);

		JLabel lblDiasAtraso = new JLabel("Dias \u00DAteis de Atraso");
		lblDiasAtraso.setBounds(10, 126, 122, 14);
		contentPane.add(lblDiasAtraso);

		JLabel lblAtraso = new JLabel("Atraso*");
		lblAtraso.setBounds(10, 81, 46, 14);
		contentPane.add(lblAtraso);

		JLabel lblIdMulta = new JLabel("Multa");
		lblIdMulta.setBounds(496, 81, 46, 14);
		contentPane.add(lblIdMulta);

		tfIdEmprestimo = new JTextField();
		tfIdEmprestimo.setBounds(163, 35, 229, 20);
		contentPane.add(tfIdEmprestimo);
		tfIdEmprestimo.setColumns(10);
		tfIdEmprestimo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputIdEmprestimo(tecla);
			}
		});

		final JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(585, 397, 89, 23);
		contentPane.add(btnConsultar);

		tfDiasAtraso = new JTextField();
		tfDiasAtraso.setBounds(163, 123, 89, 20);
		contentPane.add(tfDiasAtraso);
		tfDiasAtraso.setColumns(10);
		tfDiasAtraso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputDias(tecla);
			}
		});

		tfMulta = new JTextField();
		tfMulta.setBounds(595, 78, 80, 20);
		contentPane.add(tfMulta);
		tfMulta.setColumns(10);
		tfMulta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputMulta(tecla);
			}
		});

		final JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAlterar.setBounds(401, 397, 89, 23);
		contentPane.add(btnAlterar);

		final JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(249, 397, 89, 23);
		contentPane.add(btnExcluir);

		final JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(82, 397, 89, 23);
		contentPane.add(btnAdicionar);

		JSeparator separator = new JSeparator();
		separator.setBounds(-11, 207, 807, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(-11, 342, 818, 2);
		contentPane.add(separator_1);

		JLabel lblOpcoes = new JLabel("Menu de sele\u00E7\u00E3o");
		lblOpcoes.setBounds(10, 207, 124, 14);
		contentPane.add(lblOpcoes);

		final JRadioButton rdbtnCadastro = new JRadioButton("Cadastro");
		rdbtnCadastro.setBounds(106, 273, 109, 23);
		contentPane.add(rdbtnCadastro);

		final JRadioButton rdbtnEditar = new JRadioButton("Alterar");
		rdbtnEditar.setBounds(314, 273, 109, 23);
		contentPane.add(rdbtnEditar);

		final JRadioButton rdbtnExcluir = new JRadioButton("Excluir");
		rdbtnExcluir.setBounds(540, 273, 109, 23);
		contentPane.add(rdbtnExcluir);



		ButtonGroup GrupoRB=new ButtonGroup();

		GrupoRB.add(rdbtnCadastro);
		GrupoRB.add(rdbtnEditar);
		GrupoRB.add(rdbtnExcluir);


		cbAtraso.setModel(new DefaultComboBoxModel(new String[] {"N\u00E3o", "Sim"}));
		cbAtraso.setToolTipText("");
		cbAtraso.setBounds(163, 78, 89, 20);
		contentPane.add(cbAtraso);

		JLabel lblMensagem = new JLabel("Campos com * s\u00E3o obrigat\u00F3rios para inser\u00E7\u00E3o/altera\u00E7\u00E3o");
		lblMensagem.setBounds(292, 207, 382, 14);
		contentPane.add(lblMensagem);


		btnConsultar.setVisible(false);
		btnAdicionar.setVisible(false);
		btnAlterar.setVisible(false);
		btnExcluir.setVisible(false);

		desabilitaCampos();

		ActionListener rbCadastroListener=new ActionListener(){


			public void actionPerformed(ActionEvent arg0) {
				btnConsultar.setVisible(false);
				btnAlterar.setVisible(false);
				btnExcluir.setVisible(false);
				btnAdicionar.setVisible(true);

				limpaCampo();
				habilitaCampos();
				tfMulta.setEnabled(false);

			}
		};

		ActionListener rbEdicaoListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				btnConsultar.setVisible(true);
				btnAlterar.setVisible(true);
				btnExcluir.setVisible(false);
				btnAdicionar.setVisible(false);

				limpaCampo();

				limpaCampo();
				desabilitaCampos();


				limpaCampo();
				desabilitaCampos();
				tfIdEmprestimo.setEnabled(true);
				tfMulta.setEnabled(false);


			}

		};


		ActionListener rbExclusaoListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0){


				btnConsultar.setVisible(true);
				btnAlterar.setVisible(false);
				btnExcluir.setVisible(true);
				btnAdicionar.setVisible(false);

				limpaCampo();

				desabilitaCampos();
				tfIdEmprestimo.setEnabled(true);
			}

		};


		rdbtnCadastro.addActionListener(rbCadastroListener);
		rdbtnEditar.addActionListener(rbEdicaoListener);
		rdbtnExcluir.addActionListener(rbExclusaoListener);


		ActionListener cadastroListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Devolucao devolucao=new Devolucao();


				if((!tfIdEmprestimo.getText().isEmpty()&&(!tfDiasAtraso.getText().isEmpty()))){

					devolucao.setIDEmprestimo(Integer.parseInt(tfIdEmprestimo.getText()));
					devolucao.setAtrasado(cbAtraso.getSelectedItem().toString());
					devolucao.setDiasAtraso(Integer.parseInt(tfDiasAtraso.getText()));


					int valor=Integer.parseInt(tfDiasAtraso.getText());
					tfMulta.setText(String.valueOf(valor));
					devolucao.setMulta(Float.parseFloat(tfMulta.getText()));

					boolean inserido = false;
					inserido=inserirDevolucao(devolucao);

					if(inserido){
						Emprestimo emprestimo=new Emprestimo();
						emprestimo.setIDEmprestimo(Integer.parseInt(tfIdEmprestimo.getText()));

						EmprestimoView emp=new EmprestimoView();
						emp.atualizaCBStatus(emprestimo);



						JOptionPane.showMessageDialog(null,"Devolução realizada com sucesso",
								"Êxito",JOptionPane.INFORMATION_MESSAGE);
						limpaCampo();

						emp.RetornaQuantidadeDisponivelSoma(emprestimo.getIDEmprestimo());		
						emp.RetornaQuantidadeSubtrai(emprestimo.getIDEmprestimo());
					}

				}
				else{
					JOptionPane.showMessageDialog(null,"Devolução não realizada",
							"Atenção",JOptionPane.CANCEL_OPTION);
				}


			}

		};


		ActionListener buscaListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Devolucao devolucao=new Devolucao();
				Devolucao devolucaoConsultada=new Devolucao();

				if(!tfIdEmprestimo.getText().isEmpty()){

					if((rdbtnEditar.isSelected()||(rdbtnExcluir.isSelected()))){


						devolucao.setIDEmprestimo(Integer.parseInt(tfIdEmprestimo.getText()));
						devolucaoConsultada=consultarDevolucao(devolucao);

						tfIdEmprestimo.setText(String.valueOf(devolucaoConsultada.getIDEmprestimo()));

						cbAtraso.setSelectedItem(devolucaoConsultada.getAtrasado());
						tfDiasAtraso.setText(String.valueOf(devolucaoConsultada.getDiasAtraso()));
						tfMulta.setText(String.valueOf(devolucaoConsultada.getMulta()));

						if(rdbtnEditar.isSelected() && tfIdEmprestimo.getText()!="0"){


							habilitaCampos();
							tfMulta.setEnabled(false);
						}

						if(rdbtnExcluir.isSelected()){
							desabilitaCampos();
							tfIdEmprestimo.setEnabled(true);
						}
					}
				}
				else
				{	
					JOptionPane.showMessageDialog(null,"A busca não foi realizada", "Atenção", JOptionPane.CANCEL_OPTION);		
				}

			}


		};


		ActionListener exclusaoListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Devolucao devolucao=new Devolucao();


				if(!tfIdEmprestimo.getText().isEmpty()){

					devolucao.setIDEmprestimo(Integer.parseInt(tfIdEmprestimo.getText()));

					boolean excluido = false;
					excluido=excluirDevolucao(devolucao);

					if(excluido){
						Emprestimo emprestimo=new Emprestimo();
						emprestimo.setIDEmprestimo(Integer.parseInt(tfIdEmprestimo.getText()));

						EmprestimoView emp=new EmprestimoView();
						emp.atualizaCBStatusEmAndamento(emprestimo);

						if(!tfIdEmprestimo.getText().isEmpty() && (!tfDiasAtraso.getText().isEmpty()) &&(!tfMulta.getText().isEmpty()))
							JOptionPane.showMessageDialog(null,"Devolução excluída com sucesso",
									"Êxito",JOptionPane.INFORMATION_MESSAGE);
						limpaCampo();
						emp.RetornaQuantidadeDisponivelSubtrai(emprestimo.getIDEmprestimo());		
						emp.RetornaQuantidadeSoma(emprestimo.getIDEmprestimo());

					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Devolução não excluída",
							"Atenção",JOptionPane.CANCEL_OPTION);
					limpaCampo();
				}
			}


		};


		ActionListener edicaoListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Devolucao devolucao=new Devolucao();

				if((!tfIdEmprestimo.getText().isEmpty()&&(!tfDiasAtraso.getText().isEmpty())&&(!tfMulta.getText().isEmpty()))){
					habilitaCampos();
					tfMulta.setEnabled(false);


					devolucao.setIDEmprestimo(Integer.parseInt(tfIdEmprestimo.getText()));
					devolucao.setAtrasado(cbAtraso.getSelectedItem().toString());
					devolucao.setDiasAtraso(Integer.parseInt(tfDiasAtraso.getText()));
					devolucao.setMulta(Float.parseFloat(tfMulta.getText()));

					int valor=Integer.parseInt(tfDiasAtraso.getText());
					tfMulta.setText(String.valueOf(valor));
					devolucao.setMulta(Float.parseFloat(tfMulta.getText()));



					boolean editado = false;
					editado=atualizarDevolucao(devolucao);

					if(editado){
						JOptionPane.showMessageDialog(null,"Devolução atualizada com sucesso","Êxito",JOptionPane.INFORMATION_MESSAGE);
						limpaCampo();

					}
					else{
						JOptionPane.showMessageDialog(null,"Devolução não atualizada","Atenção",JOptionPane.CANCEL_OPTION);
					}
				}  
				else{
					JOptionPane.showMessageDialog(null,"Devolução não atualizada, verifique os campos informados\nou se a busca foi realizada.","Atenção",JOptionPane.CANCEL_OPTION);
					limpaCampo();

				}


				if(rdbtnEditar.isSelected()){ 
					preparaNovaPesquisaID();

				}

			}

		};

		btnAdicionar.addActionListener(cadastroListener);
		btnConsultar.addActionListener(buscaListener);
		btnAlterar.addActionListener(edicaoListener);
		btnExcluir.addActionListener(exclusaoListener);
	}



	public void limpaCampo(){
		tfIdEmprestimo.setText("");
		tfDiasAtraso.setText("");
		tfMulta.setText("");
	}



	public void preparaNovaPesquisaID(){
		tfIdEmprestimo.setEnabled(true);
		tfDiasAtraso.setEnabled(false);
		tfMulta.setEnabled(false);
	}


	public void habilitaCampos(){
		tfIdEmprestimo.setEnabled(true);
		cbAtraso.setEnabled(true);
		tfDiasAtraso.setEnabled(true);
		tfMulta.setEnabled(true);


	}


	public void desabilitaCampos(){
		tfIdEmprestimo.setEnabled(false);
		cbAtraso.setEnabled(false);
		tfDiasAtraso.setEnabled(false);
		tfMulta.setEnabled(false);
	}




	@Override
	public boolean inserirDevolucao(Devolucao devolucao) {
		boolean inserido=false;
		DevolucaoDao dao=new DevolucaoDao();
		try {
			inserido = dao.insereDevolucao(devolucao);
		} catch (DevolucaoDaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return inserido;
	}


	@Override
	public boolean atualizarDevolucao(Devolucao devolucao) {
		boolean editado=false;
		DevolucaoDao dao=new DevolucaoDao();
		try {
			editado=dao.AtualizarDevolucao(devolucao);
		} catch (DevolucaoDaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return editado;
	}


	@Override
	public Devolucao consultarDevolucao(Devolucao devolucao) {
		DevolucaoDao dao=new DevolucaoDao();
		Devolucao devolucaoConsultada=new Devolucao();
		try {
			devolucaoConsultada=dao.consultaDevolucao(devolucao);
		} catch (DevolucaoDaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return devolucaoConsultada;
	}


	@Override
	public boolean excluirDevolucao(Devolucao devolucao) {
		boolean excluido=false;
		DevolucaoDao dao=new DevolucaoDao();
		try {
			excluido = dao.ExcluirDevolucao(devolucao);
		} catch (DevolucaoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excluido;
	}
}

