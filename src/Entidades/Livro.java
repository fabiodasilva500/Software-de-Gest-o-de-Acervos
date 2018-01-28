package Entidades;

public class Livro {
	private int IDLivro;

	private String titulo;
	private String edicao;
	private int paginas;
	private String volume;
	private String ISBN;
	private String classificacao;
	private String assunto;
	private float comprimento;
	private float largura;
	private String localizacao;
	private int qtde;
	private String status;
	private int quantidade_disponivel;

	public Livro(){

	}


	public int getIDLivro() {
		return IDLivro;
	}


	public void setIDLivro(int iDLivro) {
		IDLivro = iDLivro;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getEdicao() {
		return edicao;
	}


	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}




	public int getPaginas() {
		return paginas;
	}


	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}


	public String getVolume() {
		return volume;
	}


	public void setVolume(String volume) {
		this.volume = volume;
	}


	public String getISBN() {
		return ISBN;
	}


	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}


	public String getClassificacao() {
		return classificacao;
	}


	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}


	public String getAssunto() {
		return assunto;
	}


	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}


	public float getComprimento() {
		return comprimento;
	}


	public void setComprimento(float comprimento) {
		this.comprimento = comprimento;
	}


	public float getLargura() {
		return largura;
	}


	public void setLargura(float largura) {
		this.largura = largura;
	}

	public String getLocalizacao() {
		return localizacao;
	}


	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}


	public int getQtde() {
		return qtde;
	}


	public void setQtde(int qtde) {
		this.qtde = qtde;
	}


	public String getStatus (){
		return status;
	}


	public void setStatus(String status){
		this.status=status;
	}

	public int getQuantidade_Disponivel() {
		return quantidade_disponivel;
	}


	public void setQuantidade_Disponivel(int quantidade_disponivel) {
		this.quantidade_disponivel = quantidade_disponivel;
	}




}
