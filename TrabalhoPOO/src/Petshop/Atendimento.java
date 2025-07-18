package Petshop;

public class Atendimento {

    private int codigo;
    private String data;
    private Cliente cliente;
    private Animal animal;
    private Funcionario funcionario;

    public Atendimento(int codigo, String data, Cliente cliente, Animal animal, Funcionario funcionario) {
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

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void exibirInformacoes() {
        System.out.println("Codigo: " + getCodigo());
        System.out.println("Data: " + getData());
        System.out.println("Cliente: " + getCliente().getNome());
        System.out.println("Animal: " + getAnimal().getNome());
        System.out.println("Funcionario: " + getFuncionario().getNome());
    }
}
