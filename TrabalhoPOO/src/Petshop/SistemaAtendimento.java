package Petshop;

import java.util.Scanner;
import java.util.ArrayList;

public class SistemaAtendimento implements Crud{
    private ArrayList<Atendimento> atendimentos;
    private Scanner input;
	private SistemaCliente sistemaCliente;
	private SistemaFuncionario sistemaFuncionario;
    private SistemaAnimal sistemaAnimal;

    public SistemaAtendimento(Scanner input, SistemaCliente sistemaCliente, SistemaFuncionario sistemaFuncionario, SistemaAnimal sistemaAnimal){
        this.atendimentos = new ArrayList<>();
        this.sistemaCliente = sistemaCliente;
		this.sistemaFuncionario = sistemaFuncionario;
        this.sistemaAnimal = sistemaAnimal;
        this.input = input;
    }
    
    public static void menuAtendimento() {
    	System.out.println("\n+--------------------------+");
    	System.out.println("|        Atendimento       |");
    	System.out.println("+--------------------------+");
    	System.out.println("| 1) Cadastro              |");
    	System.out.println("| 2) Consulta              |");
    	System.out.println("| 3) Alteração             |");
    	System.out.println("| 4) Remoção               |");
        System.out.println("| 5) Relatorio             |");
    	System.out.println("| 0) Voltar                |");
        System.out.println("+--------------------------+");
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
                case 5 -> relatorio();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opcao invalida. Tente novamente.");
    		}
    	} while(opcao != 0);
        
    }

    public Cliente cpfCliente(){
        System.out.print("Digite o CPF do cliente: ");
        String cpf = input.nextLine();
        Cliente cliente;
        if(sistemaCliente.buscaPorCpf(cpf) != null){
            return sistemaCliente.buscaPorCpf(cpf);
        } else{
            System.out.print("Nenhum cliente cadastrado com esse cpf. Quer cadastrar? [s/n]: ");
            String opcao = input.nextLine();
            switch (opcao) {
                case "s":
                    sistemaCliente.cadastrar();
                    System.out.println("\nCliente cadastrado! Insira o CPF novamente.\n");
                    return cpfCliente();
                default:
                    System.out.println("Tente novamente!\n");
                    return cpfCliente();
            }
            
        }
    }

    public Funcionario numMatricula(){
        System.out.print("Digite o numero de matrícula do funcionário: ");
        int numMatricula = Integer.parseInt(input.nextLine());
        if(sistemaFuncionario.buscaPorNumMatricula(numMatricula) != null){
            return sistemaFuncionario.buscaPorNumMatricula(numMatricula);
        }
        else{
            System.out.print("Nenhum funcinario cadastrado com esse número. Quer cadastrar? [s/n]: ");
            String opcao = input.nextLine();
            switch (opcao) {
                case "s":
                    sistemaFuncionario.cadastrar();
                    System.out.println("\nFuncionário cadastrado. Insira o número de matrícula novamente.\n");
                    return numMatricula();
                default:
                    System.out.println("Tente novamente!\n");
                    return numMatricula();
            }
        }
    }

    public Animal idAnimal(){
        System.out.print("Digite o Id do animal: ");
        int id = Integer.parseInt(input.nextLine());
        if(sistemaAnimal.buscaPorIDAnimal(id) != null){
            return sistemaAnimal.buscaPorIDAnimal(id);
        }
        else{
            System.out.print("Nenhum animal cadastrado com esse Id. Quer cadastrar? [s/n]: ");
            String opcao = input.nextLine();
            switch (opcao){
                case "s":
                    sistemaAnimal.cadastrar();
                    System.out.println("\nAnimal cadastrado. Insira o Id novamente.\n");
                    return idAnimal();
                default:
                    System.out.println("Tente novamente!\n");
                    return idAnimal();
            }
        }
    }

	@Override
    public void cadastrar() {
    	int codigo;
    	String data, cpf, opcao;
		Cliente cliente;
		Animal animal;
		Funcionario funcionario;
    	System.out.println("\n---- Cadastro de atendimento ----");
        if(atendimentos.size() > 0){
            codigo = atendimentos.get(atendimentos.size()-1).getCodigo() + 1;
        }else{
            codigo = 1;
        }
		System.out.println("Codigo: " + codigo);
    	System.out.print("Digite a data: ");
    	data = input.nextLine();
        cliente = cpfCliente();
        funcionario = numMatricula();
        animal = idAnimal();

        if (cliente != null && funcionario != null && animal != null){
            atendimentos.add(new Atendimento(codigo, data, cliente, animal, funcionario));
            System.out.println("\nCadastrado realizado com sucesso!");
        } else {
            System.out.println("Erro no cadastro. Tente novamente.\n");
        }
    }

    @Override
    public void consultar(){
		int codigo, i = 0;
        boolean achou = false;
        System.out.println("\n---- Consulta de atendimento ----");
        System.out.print("Digite o codigo do atendimento: ");
        codigo = Integer.parseInt(input.nextLine());

        while (i < atendimentos.size() && !achou){
            if(atendimentos.get(i).getCodigo() == codigo){
                achou = true;
                System.out.println("\nAtendimento encontrado!");
                atendimentos.get(i).exibirInformacoes();
            } 
            i++; 
        }
        if (!achou){
            System.out.println("\nAtendimento não encontrado.");
        } 
    }

	public void menuAlterar(int i){
				System.out.println("----------------------");
				System.out.println("1) Data: " + atendimentos.get(i).getData());
				System.out.println("2) Cliente: " + atendimentos.get(i).getCliente().getNome());
				System.out.println("3) Animal: " + atendimentos.get(i).getAnimal().getNome());
				System.out.println("4) Funcionario: " + atendimentos.get(i).getFuncionario().getNome());
				System.out.println("0) Cancelar");
				System.out.print("Digite o numero correspondente a alteracao desejada: ");
	}

    public void alterarClienteAtendimento(int i, String cpf){
        if (sistemaCliente.buscaPorCpf(cpf) != null){
            atendimentos.get(i).setCliente(sistemaCliente.buscaPorCpf(cpf));
            System.out.println("Cliente alterado com sucesso!");
        } else {
            System.out.print("Nenhum cliente com esse cpf. Deseja cadastrar um cliente? [s/n]: ");
            String opcao = input.nextLine();
            if (opcao.equals("s")){
                sistemaCliente.cadastrar();
                alterar();
            }
        }
    }

    public void alterarFuncionarioAtendimento(int i, int numMat){
        if (sistemaFuncionario.buscaPorNumMatricula(numMat) != null){
            atendimentos.get(i).setFuncionario(sistemaFuncionario.buscaPorNumMatricula(numMat));
            System.out.println("Funcionario alterado com sucesso!");
        } else {
            System.out.print("Nenhum funcionario com esse numero de matricula. Deseja cadastrar um funcionario? [s/n]: ");
            String opcao = input.nextLine();
            if (opcao.equals("s")){
                sistemaFuncionario.cadastrar();
                alterar();
            }
        }
    }

    public void alterarAnimalAtendimento(int i, int id){
        if (sistemaAnimal.buscaPorIDAnimal(id) != null){
            atendimentos.get(i).setAnimal(sistemaAnimal.buscaPorIDAnimal(id));
            System.out.println("Animal alterado com sucesso!");
        } else {
            System.out.print("Nenhum animal com esse id. Deseja cadastrar um animal? [s/n]: ");
            String opcao = input.nextLine();
            if (opcao.equals("s")){
                sistemaAnimal.cadastrar();
                alterar();
            }
        }
    }

    @Override
    public void alterar(){
        int i;
        int opcao;
        System.out.println("--- Alteracao de atendimento ---");
        i = verificaAtendimento();
        if (i > -1){            
            System.out.println("Atendimento encontrado.\n");
            do { 
                menuAlterar(i);
                opcao = Integer.parseInt(input.nextLine());
                switch (opcao) {
                    case 1 -> {
                        System.out.print("\nDigite a nova data: ");
                        String data = input.nextLine();
                        atendimentos.get(i).setData(data);
                        System.out.println("Data alterada com sucesso!");
                    }
                    case 2 -> {
                        System.out.print("\nDigite o cpf do cliente novo: "); 
                        String cpf = input.nextLine();
                        alterarClienteAtendimento(i, cpf);
                    }
                    case 3 -> {
                        System.out.print("\nDigite o id do animal novo: ");   
                        int id = Integer.parseInt(input.nextLine());
                        alterarAnimalAtendimento(i, id);
                    }
                    case 4 -> {
                        System.out.print("\nDigite o número de matrícula do funcionário novo: "); 
                        int numMat = Integer.parseInt(input.nextLine());
                        alterarFuncionarioAtendimento(i, numMat);
                    }
                    case 0 -> {
                        System.out.println("\nVoltando...");
                    }
                    default -> {
                        System.out.println("Comando invalido. Tente novamente.");
                    }       
                }      
            } while (opcao < 0 || opcao > 5);
        } else {
            System.out.println("Nenhum atendimento com esse código.");
        } 
    }

	public int verificaAtendimento(){
        int codigo, i = 0;
        boolean achou = false;
        System.out.print("Digite o codigo do atendimento: ");
        codigo = Integer.parseInt(input.nextLine());
        while(i < atendimentos.size() && !achou){
            if(atendimentos.get(i).getCodigo() == codigo){
                achou = true;
                return i;
            }
            i++;
        }
        return -1;
	}

    @Override
	public void remover(){
        int i;
        System.out.println("\n---- Remocao de atedimento ----");
        i = verificaAtendimento();
        if(i != -1){
            atendimentos.remove(i);
            System.out.println("\nO atendimento foi removido!");
        }else{
            System.out.println("\nAtendimento nao encontrado.");
        }

	}

    @Override
    public void relatorio(){
        System.out.println("\n--- Relatorio de atendimentos ---\n");
        
        if (atendimentos.size() > 0) {
            for(Atendimento atendimento: atendimentos){
            atendimento.exibirInformacoes();
        }
        } else {
            System.out.println("Nenhum antendimento cadastrado.");
        }
       
    }

    public boolean verificaFuncionario(int numMat){
        int i = 0;
        while (i < atendimentos.size()){
            if (atendimentos.get(i).getFuncionario().getNumMatricula() == numMat){
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean verificaAnimal(int id){
        int i = 0;
        while (i < atendimentos.size()){
            if (atendimentos.get(i).getAnimal().getId() == id){
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean verificaCliente(String cpf){
        int i = 0;
        while (i < atendimentos.size()) {
            if (atendimentos.get(i).getCliente().getCpf().equals(cpf)){
                return true;
            }
            i++;
        }
        return false;
    }
}