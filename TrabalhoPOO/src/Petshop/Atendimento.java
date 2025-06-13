package Petshop;

public class Atendimento {
    private int codigo;
    private String data, cliente, animal, funcionario;
    
	public Atendimento(int codigo, String data, String cliente, String animal, String funcionario) {
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
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getAnimal() {
		return animal;
	}
	public void setAnimal(String animal) {
		this.animal = animal;
	}
	public String getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}
    
    
}
