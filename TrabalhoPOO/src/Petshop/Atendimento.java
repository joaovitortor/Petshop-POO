package Petshop;

public class Atendimento {
    private int codigo;
    private String data, animal;
	private Cliente cliente;
	private Funcionario funcionario;
    
	public Atendimento(int codigo, String data, Cliente cliente, String animal, Funcionario funcionario) {
		super();
		this.codigo = codigo;
		this.data = data;
		this.cliente = cliente;
		this.animal = animal;
		this.funcionario = funcionario;
	}
		
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getAnimal() {
		return animal;
	}
	public void setAnimal(String animal) {
		this.animal = animal;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
    
    
}
