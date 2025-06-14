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
        //testar dar extens no gerenciadorSistema e testar usar as funçoes aq
        this.atendimentos = new ArrayList<>();
        this.sistemaCliente = sistemaCliente;
		this.sistemaFuncionario = sistemaFuncionario;
        this.sistemaAnimal = sistemaAnimal;
        this.input = input;
    }
    
    public static void menuAtendimento() {
    	System.out.println("\n--------------------------");
    	System.out.println("        Atendimento       ");
    	System.out.println("--------------------------");
    	System.out.println("1) Cadastro");
    	System.out.println("2) Consulta");
    	System.out.println("3) Alteração");
    	System.out.println("4) Remoção");
        System.out.println("5) Relatório");
    	System.out.println("0) Voltar");
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
    		}
    	} while (opcao != 0);
        
    }

    public Cliente cpfCliente(){
        System.out.print("Digite o CPF do cliente: ");
        String cpf = input.nextLine();
        Cliente cliente;
        if(sistemaCliente.buscaPorCpf(cpf) != null){
            return sistemaCliente.buscaPorCpf(cpf);
        } else{
            System.out.print("Nenhum cliente cadastrado com esse cpf. Quer cadastrar? [s/n]");
            String opcao = input.nextLine();
            switch (opcao) {
                case "s":
                    System.out.println("\nCadastro Cliente:");
                    sistemaCliente.cadastrar();
                    System.out.println("\nCliente cadastrado! Insira o CPF novamente.\n");
                    cpfCliente();
                    break;
                default:
                    System.out.println("Tente novamente!\n");
                    cpfCliente();
                    break;
            }
            return sistemaCliente.buscaPorCpf(cpf);
        }
    }

    public Funcionario numMatricula(){
        System.out.print("Digite o numero de matrícula do funcionário: ");
        int numMatricula = Integer.parseInt(input.nextLine());
        if(sistemaFuncionario.buscaPorNumMatricula(numMatricula) != null){
            return sistemaFuncionario.buscaPorNumMatricula(numMatricula);
        }
        else{
            System.out.print("Nenhum funcinario cadastrado com esse número. Quer cadastrar? [s/n]");
            String opcao = input.nextLine();
            switch (opcao) {
                case "s":
                    System.out.println("\nCadastro Funcionario:");
                    sistemaFuncionario.cadastrar();
                    System.out.println("\nFuncionário cadastrado. Insira o número de matrícula novamente.\n");
                    numMatricula();
                    break;
                default:
                    System.out.println("Tente novamente!\n");
                    numMatricula();
                    break;
            }
            return sistemaFuncionario.buscaPorNumMatricula(numMatricula);
        }
    }

    public Animal idAnimal(){
        System.out.print("Digite o Id do animal: ");
        int id = Integer.parseInt(input.nextLine());
        if(sistemaAnimal.buscaPorIDAnimal(id) != null){
            return sistemaAnimal.buscaPorIDAnimal(id);
        }
        else{
            System.out.println("Nenhum animal cadastrado com esse Id. Quer cadastrar? [s/n]");
            String opcao = input.nextLine();
            switch (opcao){
                case "s":
                    System.out.println("\nCadastro Animal: ");
                    sistemaAnimal.cadastrar();
                    System.out.println("\nAnimal cadastrado. Insira o Id novamente.\n");
                    idAnimal();
                    break;
                default:
                    System.out.println("Tente novamente!\n");
                    idAnimal();
                    break;
            }
            return sistemaAnimal.buscaPorIDAnimal(id);
        }
    }

	@Override
    public void cadastrar() {
    	int codigo;
    	String data, cpf, opcao, nomeAnimal;
		Cliente cliente;
		Animal animal;
		Funcionario funcionario;
    	
    	codigo = atendimentos.size() + 1;
		System.out.println("Codigo: " + codigo);
    	System.out.print("Digite a data: ");
    	data = input.nextLine();
        cliente = cpfCliente();
        funcionario = numMatricula();
        animal = idAnimal();

        atendimentos.add(new Atendimento(codigo, data, cliente, animal, funcionario));
    }

    @Override
    public void consultar(){
		int codigo, i = 0;
        boolean achou = false;

        System.out.print("Digite o codigo do atendimento ");
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
            System.out.println("\nAtendimento não encontrado");
        } 
    }

	public void menuAlterar(int i){
				System.out.println("----------------------");
				System.out.println("1) Data: " + atendimentos.get(i).getData());
				System.out.println("2) Cliente: " + atendimentos.get(i).getCliente().getNome());
				System.out.println("3) Animal: " + atendimentos.get(i).getAnimal().getNome());
				System.out.println("4) Funcionario: " + atendimentos.get(i).getFuncionario().getNome());
				System.out.println("0) Cancelar");
				System.out.println("O que deseja alterar?");
	}

    public void alterarClienteAtendimento(int i, String cpf){
        if (sistemaCliente.buscaPorCpf(cpf) != null){
            atendimentos.get(i).setCliente(sistemaCliente.buscaPorCpf(cpf));
            System.out.println("Cliente alterado com sucesso!");
        } else {
            System.out.println("Nenhum cliente com esse cpf. Deseja tentar de novo? [s/n]");
            String opcao = input.nextLine();
            if (opcao.equals("s")){
                System.out.println("Digite o cpf novamente: ");
                cpf = input.nextLine();
                alterarClienteAtendimento(i, cpf);
            }
        }
    }

    public void alterarFuncionarioAtendimento(int i, int numMat){
        if (sistemaFuncionario.buscaPorNumMatricula(numMat) != null){
            atendimentos.get(i).setFuncionario(sistemaFuncionario.buscaPorNumMatricula(numMat));
            System.out.println("Cliente alterado com sucesso!");
        } else {
            System.out.println("Nenhum funcionario com esse numero de matricula. Deseja tentar de novo? [s/n]");
            String opcao = input.nextLine();
            if (opcao.equals("s")){
                System.out.println("Digite o numero de matricula novamente: ");
                numMat = Integer.parseInt(input.nextLine());
                alterarFuncionarioAtendimento(i, numMat);
            }
        }
    }

    public void alterarAnimalAtendimento(int i, int id){
        if (sistemaAnimal.buscaPorIDAnimal(id) != null){
            atendimentos.get(i).setAnimal(sistemaAnimal.buscaPorIDAnimal(id));
            System.out.println("Cliente alterado com sucesso!");
        } else {
            System.out.println("Nenhum animal com esse id. Deseja tentar de novo? [s/n]");
            String opcao = input.nextLine();
            if (opcao.equals("s")){
                System.out.println("Digite o id do animal novamente: ");
                id = Integer.parseInt(input.nextLine());
                alterarFuncionarioAtendimento(i, id);
            }
        }
    }

    @Override
    public void alterar(){
        int codigo, i = 0, opcao;
        boolean achou = false;
        System.out.print("Digite o código do atenimento para a alteração: ");
        codigo = Integer.parseInt(input.nextLine());
        while (i < atendimentos.size() && !achou){
            if(atendimentos.get(i).getCodigo() == codigo){
                achou = true;
            } else{
                i++;
            }
        }
        if (achou){            
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
                        System.out.print("Digite o cpf do cliente novo: "); 
                        String cpf = input.nextLine();
                        alterarClienteAtendimento(i, cpf);
                    }
                    case 3 -> {
                        System.out.print("Digite o id do animal novo: ");   
                        int id = Integer.parseInt(input.nextLine());
                        alterarAnimalAtendimento(i, id);
                    }
                    case 4 -> {
                        System.out.print("Digite o número de matrícula do funcionário novo: "); 
                        int numMat = Integer.parseInt(input.nextLine());
                        alterarFuncionarioAtendimento(i, numMat);
                    }
                    case 0 -> {
                        System.out.println("Voltando...");
                    }
                    default -> {
                        System.out.println("Comando invalido. Tente novamente");
                    }       
                }      
            } while (opcao < 0 || opcao > 5);
        } else {
            System.out.println("Nenhum atendimento com esse código");
        } 
    }

    @Override
	public void remover(){

	}

    @Override
    public void relatorio(){
        for(Atendimento atendimento: atendimentos){
            atendimento.exibirInformacoes();
        }
    }
}
