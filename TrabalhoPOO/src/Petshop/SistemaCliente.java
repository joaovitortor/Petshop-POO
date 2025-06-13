package Petshop;

import java.util.Scanner;
import java.util.ArrayList;

public class SistemaCliente {
    protected ArrayList<Cliente> clientes;
    private Scanner input;

    public SistemaCliente(){
        super();
    }
    
    public SistemaCliente(Scanner input){
        this.clientes = new ArrayList<>();
        this.input = input;
    }

    public void menuCliente() {
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
    
    public void operacoesCliente(){
    	int opcao;
    	do {
            menuCliente();
            opcao = Integer.parseInt(input.nextLine());
    		switch (opcao){
    			case 1 -> cadastrarCliente();
    			case 2 -> consultarCliente();
    			case 3 -> alterarCliente();
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
    public void cadastrarCliente(){
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

    public void consultarCliente(){
        int i = 0;
        String cpf;
        System.out.print("Digite o CPF do cliente: ");
        cpf = input.nextLine();

        while (i < clientes.size() && !clientes.get(i).getCpf().equals(cpf)){
            i++;
        }

        if (i < clientes.size()){
            System.out.println("Cliente encontrado!");
        } else {
            System.out.println("Cliente não encontrado");
        }
    }

    public void alterarCliente(){
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
}