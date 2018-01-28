package Entidades;


public class Usuario {
	private String identificacao;
	private String nome;
	private String RG;
	private String Data_Nasc;
	private String endereco;
	private int numero;
	private String bairro;
	private String cep;
	private String cidade;
	private String UF;
	private String email;
	private String telefone;
	private int quantidade_livros_posse;
	private String categoria;



	public Usuario(){

	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public String getData_Nasc() {
		return Data_Nasc;
	}

	public void setData_Nasc(String data_Nasc) {
		Data_Nasc = data_Nasc;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getQuantidade() {
		return quantidade_livros_posse;
	}

	public void setQuantidade(int quantidade_livros_posse) {
		this.quantidade_livros_posse = quantidade_livros_posse;
	}


	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}










}
