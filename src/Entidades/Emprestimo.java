package Entidades;




public class Emprestimo {
	private String IDUsuario;
	private int IDLivro;
	private int IDEmprestimo;
	private String Data; 
	private String status; 
	private String reserva;



	public Emprestimo(){

	}





	public String getIDUsuario() {
		return IDUsuario;
	}

	public void setIDUsuario(String iDUsuario) {
		IDUsuario = iDUsuario;
	}

	public int getIDLivro() {
		return IDLivro;
	}

	public void setIDLivro(int iDLivro) {
		IDLivro = iDLivro;
	}

	public int getIDEmprestimo() {
		return IDEmprestimo;
	}

	public void setIDEmprestimo(int iDEmprestimo) {
		IDEmprestimo = iDEmprestimo;
	}

	public String getData() {
		return Data;
	}

	public void setData(String data) {
		Data = data;
	}


	public String getStatus(){
		return status;
	}


	public void setStatus(String status){
		this.status=status;
	}


	public String getReserva() {
		return reserva;
	}




	public void setReserva(String reserva) {
		this.reserva = reserva;
	}

}
