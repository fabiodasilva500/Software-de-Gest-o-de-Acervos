package View;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.ControllerTCC;
import Dao.TCCDao;
import Dao.TCCDaoException;
import Entidades.TCC;



public class TCCView implements ActionListener, ListSelectionListener, ControllerTCC{
	private JFrame janela=new JFrame();
	private JPanel panPrincipal, panTextos, panBotoes, panTabela;
	private JTextField tfID, tfTitulo, tfDesenvolvedor, tfInstituicao, tfAno, tfSemestre, tfFormato, tfCurso, tfDescricao;
	private JButton btnInserir, btnPesquisar, btnEditar, btnExcluir;

	private TCCDao fDAO;
	private JTable tabela;

	private List<TCC> TCCs;


	public TCCView(){
		janela=new JFrame("Janela");
		fDAO=new TCCDao();
		tabela=new JTable();
		panPrincipal=new JPanel();
		panTextos=new JPanel();
		panBotoes=new JPanel();
		panTabela=new JPanel();



		tabela=new JTable();
		tabela.getSelectionModel().addListSelectionListener(this);



		panPrincipal.setLayout(new BorderLayout());
		panTextos.setLayout(new GridLayout(9,2));
		panBotoes.setLayout(new GridLayout(1,4));


		panPrincipal.add(panTextos,BorderLayout.NORTH);
		panPrincipal.add(panTabela,BorderLayout.CENTER);
		panPrincipal.add(panBotoes,BorderLayout.SOUTH);

		panTextos.add(new JLabel("ID"));
		panTextos.add(tfID=new JTextField());
		tfID.setEnabled(false);

		panTextos.add(new JLabel("Título"));
		panTextos.add(tfTitulo=new JTextField());


		panTextos.add(new JLabel("Desenvolvedor"));
		panTextos.add(tfDesenvolvedor=new JTextField());

		panTextos.add(new JLabel("Instituição"));
		panTextos.add(tfInstituicao=new JTextField());

		panTextos.add(new JLabel("Ano"));
		panTextos.add(tfAno=new JTextField());

		panTextos.add(new JLabel("Semestre"));
		panTextos.add(tfSemestre=new JTextField());

		panTextos.add(new JLabel("Formato"));
		panTextos.add(tfFormato=new JTextField());

		panTextos.add(new JLabel("Curso"));
		panTextos.add(tfCurso=new JTextField());

		panTextos.add(new JLabel("Descrição"));
		panTextos.add(tfDescricao=new JTextField());


		panTabela.add(new JScrollPane(tabela));


		panBotoes.add(btnInserir=new JButton("Inserir"));
		panBotoes.add(btnPesquisar=new JButton("Pesquisar"));
		panBotoes.add(btnEditar=new JButton("Editar"));
		panBotoes.add(btnExcluir=new JButton("Excluir"));



		janela.setContentPane(panPrincipal);
		janela.setSize(800,700);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		btnInserir.addActionListener(this);
		btnEditar.addActionListener(this);
		btnPesquisar.addActionListener(this);
		btnExcluir.addActionListener(this);

		limpaCampo();


	}



	public void updateTabelaTCCs(List<TCC> f){
		TabelaTCCModel TCCTabela=new TabelaTCCModel(f);
		tabela.setModel(TCCTabela);
		tabela.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tabela.repaint();
	}



	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();

		if("INSERIR".equalsIgnoreCase(cmd)){
			TCC t=generica();
			boolean inserido=inserir (t);
			if(!t.getTitulo().isEmpty() && !t.getDescricao().isEmpty() && !t.getInstituicao().isEmpty()  && !t.getSemestre().isEmpty() && !t.getFormato().isEmpty() && !t.getCurso().isEmpty() && !t.getDescricao().isEmpty()){

				if(inserido){
					JOptionPane.showMessageDialog(null,"Trabalho de graduação cadatrado com sucesso","Sucesso",JOptionPane.INFORMATION_MESSAGE);
					limpaCampo();
				}
			}

			else{
				JOptionPane.showMessageDialog(null,"Trabalho de graduação não inserido, verifique os campos informados","Atenção",JOptionPane.CANCEL_OPTION);
			}
		}
		else
			if("EDITAR".equalsIgnoreCase(cmd)){
				TCC t=generica();
				boolean editado=atualizar(t);
				if(!t.getTitulo().isEmpty() && !t.getDescricao().isEmpty() && !t.getInstituicao().isEmpty()  && !t.getSemestre().isEmpty() && !t.getFormato().isEmpty() && !t.getCurso().isEmpty() && !t.getDescricao().isEmpty()){
					if(editado){
						JOptionPane.showMessageDialog(null,"Trabalho de graduação editado com sucesso","Sucesso",JOptionPane.INFORMATION_MESSAGE);
						limpaCampo();
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Trabalho de graduação não editado, verifique os campos informados","Atenção",JOptionPane.CANCEL_OPTION);
				}
			}
			else
				if("EXCLUIR".equalsIgnoreCase(cmd)){
					TCC t=generica();
					boolean excluido=remover(t);
					if(!t.getTitulo().isEmpty() && !t.getDescricao().isEmpty() && !t.getInstituicao().isEmpty()  && !t.getSemestre().isEmpty() && !t.getFormato().isEmpty() && !t.getCurso().isEmpty() && !t.getDescricao().isEmpty()){
						if(excluido){
							JOptionPane.showMessageDialog(null,"Trabalho de graduação excluído com sucesso","Sucesso",JOptionPane.INFORMATION_MESSAGE);
							limpaCampo();
						}
					}
					else{
						JOptionPane.showMessageDialog(null,"Trabalho de graduação não excluído, verifique os campos informados","Atenção",JOptionPane.CANCEL_OPTION);
					}
				}
				else
					if("PESQUISAR".equalsIgnoreCase(cmd)){
						List <TCC> func=pesquisar(tfTitulo.getText());
						updateTabelaTCCs(func);
					}
	}


	public TCC generica(){
		TCC t=new TCC();
		t.setTitulo(tfTitulo.getText());

		t.setDesenvolvedor(tfDesenvolvedor.getText());
		t.setInstituicao(tfInstituicao.getText());

		t.setAno(Integer.parseInt(tfAno.getText()));
		t.setSemestre(tfSemestre.getText());

		t.setFormato(tfFormato.getText());
		t.setCurso(tfCurso.getText());
		t.setDescricao(tfDescricao.getText());


		return t;
	}


	public void genericaPesquisa(TCC t){
		tfID.setText(String.valueOf(t.getID()));
		tfTitulo.setText(t.getTitulo());
		tfDesenvolvedor.setText(t.getDesenvolvedor());
		tfInstituicao.setText(t.getInstituicao());
		tfAno.setText(String.valueOf(t.getAno()));
		tfSemestre.setText(t.getSemestre());
		tfFormato.setText(t.getFormato());
		tfCurso.setText(t.getCurso());
		tfDescricao.setText(t.getDescricao());
		tfID.setEnabled(false);
		tfTitulo.setEnabled(false);
	}


	public boolean inserir(TCC tcc) {
		boolean inserido=false;
		try {
			fDAO.InsereTCC(tcc);
			inserido=true;
		} catch (TCCDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inserido;

	}


	public boolean atualizar(TCC tcc) {
		boolean editado=false;
		try {
			fDAO.AtualizarTCCNome(tcc);
			editado=true;
		} catch (TCCDaoException e) {
			e.printStackTrace();
		}
		return editado;
	}


	public boolean remover(TCC tcc) {
		boolean removido=false;
		try {
			fDAO.excluirTCC(tcc);
			removido=true;
		} catch (TCCDaoException e) {
			e.printStackTrace();
		}
		return removido;
	}


	public List<TCC> pesquisar(String titulo) {
		List <TCC> tccConsultado = new ArrayList<TCC>();
		try {
			tccConsultado = fDAO.consultarTCCNome(titulo);	
		} catch (TCCDaoException e) {
			e.printStackTrace();
		}
		return tccConsultado;
	}



	public void valueChanged(ListSelectionEvent e){
		ListSelectionModel lsm=(ListSelectionModel)e.getSource();
		TabelaTCCModel tabModel=(TabelaTCCModel)tabela.getModel();
		TCC l=tabModel.get(lsm.getAnchorSelectionIndex());
		genericaPesquisa(l);
	}


	public void limpaCampo(){
		tfID.setText("");
		tfTitulo.setText("");
		tfTitulo.setEnabled(true);
		tfDesenvolvedor.setText("");
		tfInstituicao.setText("");
		tfAno.setText("0");
		tfSemestre.setText("");
		tfFormato.setText("");
		tfCurso.setText("");
		tfDescricao.setText("");
	}


	public static void main(String[]args){
		new TCCView();
	}
}