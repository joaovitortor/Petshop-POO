package Petshop;

public class Funcionario {
	private String nome, qualificacao, descricaoFuncao;
	private int numMatricula, cargaHorariaSemanal;
	
	public Funcionario(String nome, String qualificacao, String descricaoFuncao, int cargaHorariaSemanal,
			int numMatricula) {
		super();
		this.nome = nome;
		this.qualificacao = qualificacao;
		this.descricaoFuncao = descricaoFuncao;
		this.cargaHorariaSemanal = cargaHorariaSemanal;
		this.numMatricula = numMatricula;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getQualificacao() {
		return qualificacao;
	}
	public void setQualificacao(String qualificacao) {
		this.qualificacao = qualificacao;
	}
	public String getDescricaoFuncao() {
		return descricaoFuncao;
	}
	public void setDescricaoFuncao(String descricaoFuncao) {
		this.descricaoFuncao = descricaoFuncao;
	}
	public int getCargaHorariaSemanal() {
		return cargaHorariaSemanal;
	}
	public void setCargaHorariaSemanal(int cargaHorariaSemanal) {
		this.cargaHorariaSemanal = cargaHorariaSemanal;
	}
	public int getNumMatricula() {
		return numMatricula;
	}
	public void setNumMatricula(int numMatricula) {
		this.numMatricula = numMatricula;
	}
	public void exibirInformacoes(){
		System.out.println("Nome: " + getNome());
		System.out.println("Número de matrícula: " + getNumMatricula());
		System.out.println("Qualificação " + getQualificacao());
		System.out.println("Descrição da função: " + getDescricaoFuncao());
		System.out.println("Carga Horária Semanal: " + getCargaHorariaSemanal());
	}

	
}
