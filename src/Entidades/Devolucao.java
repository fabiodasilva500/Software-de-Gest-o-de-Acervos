package Entidades;

public class Devolucao {
	private int IDEmprestimo;
	private String atrasado;
	private int diasAtraso;
	private float multa;

	public Devolucao(){


	}

	public int getIDEmprestimo() {
		return IDEmprestimo;
	}

	public void setIDEmprestimo(int iDEmprestimo) {
		IDEmprestimo = iDEmprestimo;
	}

	public String getAtrasado() {
		return atrasado;
	}

	public void setAtrasado(String atrasado) {
		this.atrasado = atrasado;
	}

	public int getDiasAtraso() {
		return diasAtraso;
	}

	public void setDiasAtraso(int diasAtraso) {
		this.diasAtraso = diasAtraso;
	}

	public float getMulta() {
		return multa;
	}

	public void setMulta(float multa) {
		this.multa = multa;
	}


}
