package Petshop;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
    public static void menuPrincipal(){
        System.out.println("\n--------------------------");
        System.out.println("          menu            ");
        System.out.println("--------------------------");
        System.out.println("1) Cliente");
        System.out.println("2) Funcionário");
        System.out.println("3) Animal");
        System.out.println("4) Atendimento");
        System.out.println("5) Relatórios");
        System.out.println("0) Sair");
        System.out.print("Digite o comando desejado: ");
    }   
    
    public static void menuCliente() {
    	System.out.println("\n--------------------------");
    	System.out.println("          Cliente         ");
    	System.out.println("--------------------------");
    	System.out.println("1)Cadastro");
    	System.out.println("2)Consulta");
    	System.out.println("3)Alteração");
    	System.out.println("4)Remoção");
    	System.out.println("0)Voltar");
    	System.out.print("Digite o comando desejado: ");
    }
    
    public static void menuFuncionario() {
    	System.out.println("\n--------------------------");
    	System.out.println("        Funcionário       ");
    	System.out.println("--------------------------");
    	System.out.println("1)Cadastro");
    	System.out.println("2)Consulta");
    	System.out.println("3)Alteração");
    	System.out.println("4)Remoção");
    	System.out.println("0)Voltar");
    	System.out.print("Digite o comando desejado: ");
    }
    
    public static void menuAnimal() {
    	System.out.println("\n--------------------------");
    	System.out.println("          Animal          ");
    	System.out.println("--------------------------");
    	System.out.println("1)Cadastro");
    	System.out.println("2)Consulta");
    	System.out.println("3)Alteração");
    	System.out.println("4)Remoção");
    	System.out.println("0)Voltar");
    	System.out.print("Digite o comando desejado: ");
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
    
    public static void menuRelatorio() {
    	System.out.println("\n--------------------------");
    	System.out.println("        Relatórios        ");
    	System.out.println("--------------------------");
    	System.out.println("1)Clientes cadastrados");
    	System.out.println("2)Funcionários cadastrados");
    	System.out.println("3)Animais cadastrados");
    	System.out.println("4)Atendimentos realizados");
    	System.out.println("0)Voltar");
    	System.out.print("Digite o comando desejado: ");
    }

    public static void operacoesCliente(Scanner input, ArrayList<Cliente> clientes){
    	int opcao;
    	do {
            menuCliente();
            opcao = Integer.parseInt(input.nextLine());
    		switch (opcao){
    			case 1 -> cadastrarCliente(input, clientes);
    			case 2 -> consultarCliente(input, clientes);
    			case 3 -> alterarCliente(input, clientes);
    			//case 4 -> removerCliente();
    		}
    	} while (opcao != 0);
    }
    
    /**
    * Cadastra um novo cliente no sistema.
    *
    * @param input Scanner usado para entrada de dados.
    * @param clientes Lista onde o novo cliente será adicionado.
    */
    public static void cadastrarCliente(Scanner input, ArrayList<Cliente> clientes){
        String nome, telefone, rg, cpf, email;
        System.out.print("Digite o nome do cliente: ");
        nome = input.nextLine();
        System.out.print("Digite o telefone do cliente: ");
        telefone = input.nextLine();
        System.out.print("Digite o email do cliente: ");
        email = input.nextLine();
        System.out.print("Digite o RG do cliente: ");
        rg = input.nextLine();
        System.out.print("Digite o CPF do cliente: ");
        cpf = input.nextLine();
        clientes.add(new Cliente(nome, telefone, email, rg, cpf));
    }

    public static void consultarCliente(Scanner input, ArrayList<Cliente> clientes){
        int i = 0;
        String nome;
        System.out.print("Digite o nome do cliente: ");
        nome = input.nextLine();

        while (i < clientes.size() && !clientes.get(i).getNome().equals(nome)){
            i++;
        }

        if (i < clientes.size()){
            System.out.println("Cliente encontrado!");
            System.out.println(clientes.get(i).getTelefone());
        } else {
            System.out.println("Cliente nao encontrado");
        }
    }

    public static void alterarCliente(Scanner input, ArrayList<Cliente> clientes){
        int i = 0;
        System.out.print("Digite o cpf do cliente para alteração: ");
        String cpf = input.nextLine();
        while (i < clientes.size() && !clientes.get(i).getCpf().equals(cpf)){
            i++;
        }

        if (i < clientes.size()){
            System.out.println("Cliente encontrado. O que voce deseja alterar?");
            clientes.get(i).exibirInformacoes();
        }
    }

    public static void cadastrarFuncionario(Scanner input, ArrayList<Funcionario> funcionarios){
        String nome, qualificacao, descricaoFuncao;
        int cargaHorariaSemanal, numMatricula;
        System.out.print("Digite o nome do funcionario: ");
        nome = input.nextLine();
        System.out.print("Digite a qualificacao do funcionario: ");
        qualificacao = input.nextLine();
        System.out.print("Digite a descricao da função do funcionario: ");
        descricaoFuncao = input.nextLine();
        System.out.print("Digite a carga horaria semanal do funcionario: ");
        cargaHorariaSemanal = input.nextInt();
        System.out.print("Digite o numero da matricula do funcionario: ");
        numMatricula = input.nextInt();
        funcionarios.add(new Funcionario(nome, qualificacao, descricaoFuncao, cargaHorariaSemanal, numMatricula));
    }
    
    public static void cadastrarAnimal(Scanner input, ArrayList<Animal> animais){
        String nome;
        float peso, altura;
        System.out.print("Digite o nome do animal: ");
        nome = input.nextLine();
        System.out.print("Digite o peso do animal: ");
        peso = input.nextFloat();
        System.out.print("Digite a altura do animal do funcionario: ");
        altura = input.nextFloat();
        animais.add(new Animal(nome, peso, altura));  
    }
    
    public static void cadastrarAtendimento(Scanner input, ArrayList<Atendimento> atendimentos) {
    	int codigo;
    	String data, cliente, animal, funcionario;
    	
    	System.out.print("Digite o código do antendimento: ");
    	codigo = input.nextInt();
    	System.out.print("Digite a data: ");
    	data = input.nextLine();
    	System.out.print("Digite o nome do cliente: ");
    	cliente = input.nextLine();
    	System.out.print("Digite o nome do animal: ");
    	animal = input.nextLine();
    	System.out.print("Digite o nome do funcionario: ");
    	funcionario = input.nextLine();
    }
    
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        ArrayList<Animal> animais = new ArrayList<Animal>();
        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
        ArrayList<Atendimento> atendimentos = new ArrayList<Atendimento>();
        int opcaoPrincipal, opcaoEspecifica;

        do {
            menuPrincipal();
            opcaoPrincipal = Integer.parseInt(input.nextLine());
            switch (opcaoPrincipal){
                case 1 -> operacoesCliente(input, clientes);
            //    case 2 -> operacoesFuncionario(input, funcionarios);
            //    case 3 -> operacoesAnimal(input, animais);
            //    case 4 -> operacoesAtendimento(input, );
            //    case 0 -> System.out.println("Saindo...");
            //    default -> System.out.println("Comando errado, tente novamente!");
            } 
        } while (opcaoPrincipal != 0);
    }
}

