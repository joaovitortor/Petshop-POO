package Petshop;

public class Animal {
    private String nome, especie;
    private float peso, altura;
	private Cliente dono;
	private int id;
    
	public Animal(String nome, String especie, float peso, float altura, Cliente dono, int id) {
		super();
		this.nome = nome;
		this.especie = especie;
		this.peso = peso;
		this.altura = altura;
		this.dono = dono;
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public float getAltura() {
		return altura;
	}
	public void setAltura(float altura) {
		this.altura = altura;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cliente getDono(){
		return dono;
	}
	public void setDono(Cliente dono){
		this.dono = dono;
	}

	public void exibirInformacoes(){
		System.out.println("Nome: " + getNome());
		System.out.println("Especie: " + getEspecie());
		System.out.println("Peso: " + getPeso());
		System.out.println("Altura: " + getAltura());
		System.out.println("ID: " + getId());
		System.out.println("Dono: " + getDono().getNome());
	}	
}