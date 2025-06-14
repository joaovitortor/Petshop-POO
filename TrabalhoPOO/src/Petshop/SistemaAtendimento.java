package Petshop;

import java.util.Scanner;
import java.util.ArrayList;

public class SistemaAtendimento extends GerenciarSistema implements Crud{
    private ArrayList<Atendimento> atendimentos;
    private Scanner input;
	private SistemaCliente sistemaCliente;
	private SistemaFuncionario sistemaFuncionario;
	

    public SistemaAtendimento(Scanner input){
        //testar dar extens no gerenciadorSistema e testar usar as funçoes aq
        super(input);
        this.atendimentos = new ArrayList<>();
        this.sistemaCliente = sistemaCliente;
		this.sistemaFuncionario = sistemaFuncionario;
        this.input = input;
    }
    
    public static void menuAtendimento() {
    	System.out.println("\n--------------------------");
    	System.out.println("        Atendimento       ");
    	System.out.println("--------------------------");
    	System.out.println("1)Cadastro");
    	System.out.println("2)Consulta");
    	System.out.println("3)Alteração");
    	System.out.println("4)Remoção");
    	System.out.println("0)Voltar");
    	System.out.print("Digite o comando desejado: ");
    }

	public void operacoesAtendimento(){
    	int opcao;
    	do {
            menuAtendimento();
            opcao = Integer.parseInt(input.nextLine());
    		switch (opcao){
    			case 1 -> cadastrar();
    			case 2 -> consultar();
    			case 3 -> alterar();
    			case 4 -> remover();
    		}
    	} while (opcao != 0);
        
    }

	public boolean buscaPorCpf(String cpf){
        int i = 0;
		ArrayList<Cliente> listaCliente = sistemaCliente.getListaCliente();
        while (i < atendimentos.size() && !listaCliente.get(i).getCpf().equals(cpf)){
                i++;
        }
        if (i < atendimentos.size() && listaCliente.get(i).getCpf().equals(cpf)){
            return true;
        } else {
            return false;
        }
    }


    public Cliente cpfCliente(){
        //ArrayList<Cliente> clientes = sistemaCliente.getListaCliente();
        System.out.print("Digite o CPF do cliente: ");
        String cpf = input.nextLine();
        if(sistemaCliente.buscaPorCpf(cpf) != null){
            return sistemaCliente.buscaPorCpf(cpf);
        }
        else{
            System.out.print("Nenhum cliente cadastrado com esse cpf. Quer cadastrar? [s/n]");
            String opcao = input.nextLine();
            switch (opcao) {
                case "s":
                    sistemaCliente.cadastrar();
                    cpfCliente();
                    break;
                default:
                    System.out.println("Tente novamente!");
                    cpfCliente();
                    break;
            }
            return null;
        }
    }

    public Funcionario numMatricula(){
        System.out.print("Digite o numero de matrícula: ");
        int numMatricula = Integer.parseInt(input.nextLine());
        if(sistemaFuncionario.buscaPorNumMatricula(numMatricula) != null){
            return sistemaFuncionario.buscaPorNumMatricula(numMatricula);
        }
        else{
            System.out.print("Nenhum funcinario cadastrado com esse número. Quer cadastrar? [s/n]");
            String opcao = input.nextLine();
            switch (opcao) {
                case "s":
                    sistemaFuncionario.cadastrar();
                    numMatricula();
                    break;
                default:
                    System.out.println("Tente novamente!");
                    numMatricula();
                    break;
            }
            return null;
        }
    }

	@Override
    public void cadastrar() {
    	int codigo;
    	String data, cpf, opcao, nomeAnimal;
		Cliente cliente;
		//Animal animal;
		Funcionario funcionario;

    	
    	codigo = atendimentos.size() + 1;
		System.out.println("Codigo: " + codigo);
    	System.out.print("Digite a data: ");
    	data = input.nextLine();
        cliente = cpfCliente();
        funcionario = numMatricula();
        System.out.println("Nome do animal: ");
        nomeAnimal = input.nextLine();
        codigo = atendimentos.size() + 1;

        atendimentos.add(new Atendimento(codigo, data, cliente, nomeAnimal, funcionario));

        
    	//cliente = input.nextLine();
    	//System.out.print("Digite o nome do animal: ");
    	//animal = input.nextLine();
    	//System.out.print("Digite o nome do funcionario: ");
    	//funcionario = input.nextLine();
    }

    @Override
    public void consultar(){
		int codigo, i = 0;
        System.out.print("Digite o codigo do atendimento ");
        codigo = Integer.parseInt(input.nextLine());

        while (i < atendimentos.size() && atendimentos.get(i).getCodigo() != codigo){
            i++;
        }
        if (i < atendimentos.size()){
            System.out.println("\nAtendimento encontrado!");
            atendimentos.get(i).exibirInformacoes();
        } else {
            System.out.println("\nAtendimento não encontrado");
        }
    }

	public void menuAlterar(int i){
				System.out.println("----------------------");
				System.out.println("1) Data: " + atendimentos.get(i).getData());
				System.out.println("2) Cliente: " + atendimentos.get(i).getCliente().getNome());
				System.out.println("3) Animal: " + atendimentos.get(i).getAnimal());
				System.out.println("4) Funcionario: " + atendimentos.get(i).getFuncionario().getNome());
				System.out.println("0) Cancelar");
				System.out.println("O que deseja alterar?");
			}

    @Override
    public void alterar(){
        int codigo, i = 0, opcao;
        System.out.print("Digite o numero de matricula do funcionario para alteração: ");
        codigo = Integer.parseInt(input.nextLine());
        while (i < atendimentos.size() && atendimentos.get(i).getCodigo() != codigo){
            i++;
        }
        if (i < atendimentos.size()){            
            System.out.println("Atendimento encontrado.\n");
            do { 
                menuAlterar(i);
                opcao = Integer.parseInt(input.nextLine());
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Digite a nova data: ");
                        String data = input.nextLine();
                        atendimentos.get(i).setData(data);
                    }
                    case 2 -> {
                        System.out.print(" "); //Aqui alterar qual cliente, talvez perguntar se é um cliente existente
                        
                    }
                    case 3 -> {
                        System.out.print(" "); //A mesma coisa aqui, a gente pode implemetar a mesma coisa de cliente
                        
                    }
                    case 4 -> {
                        System.out.print(" "); //idem
                    }
                    case 0 -> {
                        System.out.println("Voltando...");
                    }
                    default -> {
                        System.out.println("Comando invalido. Tente novamente");
                    }       
                }      
            } while (opcao < 0 || opcao > 5);
        }
    }

    @Override
	public void remover(){

	}
}
