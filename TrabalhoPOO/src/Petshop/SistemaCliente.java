package Petshop;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaCliente implements Crud{
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
    	System.out.println("\n+--------------------------+");
    	System.out.println("|          Cliente         |");
    	System.out.println("+--------------------------+");
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
    			case 1 -> cadastrar();
    			case 2 -> consultar();
    			case 3 -> alterar();
    			case 4 -> remover();
    		}
    	} while (opcao != 0);
    }
    
    /**
    * Cadastra um novo cliente no sistema.
    *
    * @param input Scanner usado para entrada de dados.
    * @param clientes Lista onde o novo cliente será adicionado.
    */
    @Override
    public void cadastrar(){
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

    @Override
    public void consultar(){
        int i = 0;
        String cpf;
        System.out.print("Digite o CPF do cliente: ");
        cpf = input.nextLine();

        while (i < clientes.size() && !clientes.get(i).getCpf().equals(cpf)){
            i++;
        }

        if (i < clientes.size()){
            System.out.println("\nCliente encontrado!");
            clientes.get(i).exibirInformacoes();
        } else {
            System.out.println("\nCliente não encontrado");
        }
    }

    public void menuAlterar(int i){
        System.out.println("----------------------");
        System.out.println("1) Nome: " + clientes.get(i).getNome());
        System.out.println("2) Telefone: " + clientes.get(i).getTelefone());
        System.out.println("3) CPF: " + clientes.get(i).getCpf());
        System.out.println("4) RG: " + clientes.get(i).getRg());
        System.out.println("5) Email: " + clientes.get(i).getEmail());
        System.out.println("0) Cancelar");
        System.out.println("O que deseja alterar?");
    }

    @Override
    public void alterar(){
        int i = 0, opcao;
        System.out.print("Digite o cpf do cliente para alteração: ");
        String cpf = input.nextLine();
        while (i < clientes.size() && !clientes.get(i).getCpf().equals(cpf)){
            i++;
        }

        if (i < clientes.size()){            
            System.out.println("Cliente encontrado.\n");
            do { 
                menuAlterar(i);
                opcao = Integer.parseInt(input.nextLine());
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Digite o novo nome: ");
                        String nome = input.nextLine();
                        clientes.get(i).setNome(nome);
                    }
                    case 2 -> {
                        System.out.print("Digite o novo Telefone: ");
                        String telefone = input.nextLine();
                        clientes.get(i).setNome(telefone);
                    }
                    case 3 -> {
                        System.out.print("Digite o novo CPF: ");
                        cpf = input.nextLine();
                        clientes.get(i).setCpf(cpf);
                    }
                    case 4 -> {
                        System.out.print("Digite o novo RG: ");
                        String rg = input.nextLine();
                        clientes.get(i).setRg(rg);  
                    }
                    case 5 -> {
                        System.out.print("Digite o novo email: ");
                        String email = input.nextLine();
                        clientes.get(i).setEmail(email);
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

    public ArrayList<Cliente> getListaCliente(){
        return clientes;
    }   

    public Cliente buscaPorCpf(String cpf){
        int i = 0;
        while (i < clientes.size() && !clientes.get(i).getCpf().equals(cpf)){
                i++;
        }
        if (i < clientes.size() && clientes.get(i).getCpf().equals(cpf)){
            return clientes.get(i);
        } else {
            return null;
        }
    }
}