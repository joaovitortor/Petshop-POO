package Petshop;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaCliente implements Crud{
    private ArrayList<Cliente> clientes;
    private SistemaAtendimento sistemaAtendimento;
    private SistemaAnimal sistemaAnimal;
    private Scanner input;

    public SistemaCliente(Scanner input){
        this.clientes = new ArrayList<>();
        this.input = input;
    }
    
    public void setSistemaAtendimento(SistemaAtendimento sistemaAtendimento){
        this.sistemaAtendimento = sistemaAtendimento;
    }

    public void setSistemaAnimal(SistemaAnimal sistemaAnimal){
        this.sistemaAnimal = sistemaAnimal;
    }

    public void menuCliente() {
    	System.out.println("\n+--------------------------+");
    	System.out.println("|           Cliente        |");
    	System.out.println("+--------------------------+");
    	System.out.println("| 1) Cadastrar             |");
    	System.out.println("| 2) Consultar             |");
    	System.out.println("| 3) Alterar               |");
    	System.out.println("| 4) Remover               |");
        System.out.println("| 5) Relatorio             |");
    	System.out.println("| 0) Voltar                |");
        System.out.println("+--------------------------+");
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
                case 5 -> relatorio();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opcao invalida. Tente novamente");
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
        System.out.println("\n---- Cadastro de cliente ----");
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
        System.out.println("\nCadastro realizado com sucesso!");
    }

    @Override
    public void consultar(){
        int i = 0;
        String cpf;
        System.out.println("\n---- Consulta de cliente ----");
        System.out.print("Digite o CPF do cliente: ");
        cpf = input.nextLine();

        while (i < clientes.size() && !clientes.get(i).getCpf().equals(cpf)){
            i++;
        }

        if (i < clientes.size()){
            System.out.println("\nCliente encontrado!");
            clientes.get(i).exibirInformacoes();
        } else {
            System.out.println("\nCliente não encontrado.");
        }
    }

    public void menuAlterar(int i){
        System.out.println("1) Nome: " + clientes.get(i).getNome());
        System.out.println("2) Telefone: " + clientes.get(i).getTelefone());
        System.out.println("3) CPF: " + clientes.get(i).getCpf());
        System.out.println("4) RG: " + clientes.get(i).getRg());
        System.out.println("5) Email: " + clientes.get(i).getEmail());
        System.out.println("0) Cancelar");
        System.out.print("Digite o numero correspondente a alteracao desejada: ");
    }

    @Override
    public void alterar(){
        int opcao;
        System.out.println("\n---- Alteracao de cliente ----");
        int i = verificaCliente();
        if (i > -1){            
            System.out.println("Cliente encontrado!\n");
            do { 
                menuAlterar(i);
                opcao = Integer.parseInt(input.nextLine());
                switch (opcao) {
                    case 1 -> {
                        System.out.print("\nDigite o novo nome: ");
                        String nome = input.nextLine();
                        clientes.get(i).setNome(nome);
                        System.out.println("Nome atualizado com sucesso!");
                    }
                    case 2 -> {
                        System.out.print("\nDigite o novo Telefone: ");
                        String telefone = input.nextLine();
                        clientes.get(i).setNome(telefone);
                        System.out.println("Telefone atualizado com sucesso!");
                    }
                    case 3 -> {
                        System.out.print("\nDigite o novo CPF: ");
                        String cpf = input.nextLine();
                        clientes.get(i).setCpf(cpf);
                        System.out.println("CPF atualizado com sucesso!");
                    }
                    case 4 -> {
                        System.out.print("\nDigite o novo RG: ");
                        String rg = input.nextLine();
                        clientes.get(i).setRg(rg);  
                        System.out.println("RG atualizado com sucesso!");
                    }
                    case 5 -> {
                        System.out.print("\nDigite o novo email: ");
                        String email = input.nextLine();
                        clientes.get(i).setEmail(email);
                        System.out.println("Email atualizado com sucesso!");
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
            System.out.println("Nenhum cliente com esse CPF");
        }
    }

    public int verificaCliente(){
        int i = 0;
        boolean achou = false;
        System.out.print("Digite o CPF do cliente: ");
        String cpf = input.nextLine();
        while(i < clientes.size() && !achou){
            if(clientes.get(i).getCpf().equals(cpf)){
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
        boolean inclusoEmAtendimento, temAnimal;
        System.out.println("\n---- Remocao de cliente ----");
        i = verificaCliente();
        if(i > -1){
            String cpf = clientes.get(i).getCpf();
            inclusoEmAtendimento = sistemaAtendimento.verificaCliente(cpf);
            temAnimal = sistemaAnimal.verificaDono(cpf);
            if(!temAnimal && !inclusoEmAtendimento){
                clientes.remove(i);
                System.out.println("\nO cliente foi removido!");
            } else {
                System.out.println("\nO cliente tem pelo menos um animal ou está em pelo menos um atendimento. Não é possível remover.");
            }
        }
    }

    @Override
    public void relatorio(){
        System.out.println("\n---- Relatorio de clientes ----\n");
        if(clientes.size() > 0){
            for(Cliente cliente: clientes){
            cliente.exibirInformacoes();
            System.out.println("");
            }
        } else {
            System.out.println("Nenhum cliente cadastrado.");
        }
    }

    public ArrayList<Cliente> getListaCliente(){
        return clientes;
    }   

    public Cliente buscaPorCpf(String cpf){
        int i = 0;
        boolean achou = false;

        while (i < clientes.size() && !achou){
            if(clientes.get(i).getCpf().equals(cpf)){
                achou = true;
                return clientes.get(i);
            }
            i++;
        }
        return null;
    }
}   