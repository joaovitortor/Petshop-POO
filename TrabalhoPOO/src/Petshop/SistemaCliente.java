package Petshop;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaCliente implements Crud{
    private ArrayList<Cliente> clientes;
    private SistemaAtendimento sistemaAtendimento;
    private SistemaAnimal sistemaAnimal;
    private Scanner input;

    /**
     * Construtor da classe SistemaCliente
     * Inicializa a lista de clientes e o input para leitura de dados
     * O sistemaAtendimento e sistemaAnimal sao inicializados posteriormente para evitar
     * problemas relacionados a dependencia circular
     * @param input O objeto Scanner utilizado para ler a entrada do usuário.
     */
    public SistemaCliente(Scanner input){
        this.clientes = new ArrayList<>();
        this.input = input;
    }
    
     /**
     * Inicializa o sistemaAtendimento.
     */   
    public void setSistemaAtendimento(SistemaAtendimento sistemaAtendimento){
        this.sistemaAtendimento = sistemaAtendimento;
    }

    /**
     * Inicializa o sistemaAnimal.
     */
    public void setSistemaAnimal(SistemaAnimal sistemaAnimal){
        this.sistemaAnimal = sistemaAnimal;
    }

    /**
     * Exibe o menu de opcoes de operacoes com cliente.
     */
    public void menuCliente() {
    	System.out.println("\n+--------------------------+");
    	System.out.println("|         Cliente          |");
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
    
    /**
     * Gerencia a escolha de operacao de cliente realizada pelo usuario.
     */
    public void operacoesCliente(){
    	int opcao;
        try{
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
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite apenas números.");
            operacoesCliente();
        }
    	
    }
    
    /**
    * Cadastra um novo cliente no sistema e insere na lista de clientes.
    */
    @Override
    public void cadastrar(){
        System.out.println("\n---- Cadastro de cliente ----");
        String nome, telefone, rg, cpf, email;
        System.out.print("Digite o CPF do cliente: ");
        cpf = input.nextLine();
        if(buscaPorCpf(cpf) == null){
            System.out.print("Digite o nome do cliente: ");
            nome = input.nextLine();
            System.out.print("Digite o telefone do cliente: ");
            telefone = input.nextLine();
            System.out.print("Digite o email do cliente: ");
            email = input.nextLine();
            System.out.print("Digite o RG do cliente: ");
            rg = input.nextLine();
            clientes.add(new Cliente(nome, telefone, email, rg, cpf));
            System.out.println("\nCadastro realizado com sucesso!");
        } else {
            System.out.println("Já possui um cliente cadastrado com esse cpf!\n");
        }
    }

    /**
     * Verifica se um cliente está cadastrado pelo cpf e, se estiver, exibe
     * suas informacoes
     */
    @Override
    public void consultar(){
        Cliente cliente;
        String cpf;
        System.out.println("\n---- Consulta de cliente ----");
        System.out.print("Digite o CPF do cliente: ");
        cpf = input.nextLine();
        cliente = buscaPorCpf(cpf);
        if(cliente != null) {
            System.out.println("\nCliente encontrado!");
            cliente.exibirInformacoes();
            if (sistemaAnimal.animaisPorDono(cpf) != null) {
                System.out.println("Animais:");
                for(Animal animal: sistemaAnimal.animaisPorDono(cpf)){
                    System.out.println(animal.getNome());
                }
            } else {
                System.out.println("Animais: Nenhum");
            }  
        } else {
            System.out.println("\nCliente não encontrado.");
        }
    }

    /**
     * Exibe um menu de opcoes com as informacoes do cliente e pede para o usuario digitar
     * a opcao correspondente ao atributo ele quer alterar.
     * 
     * @param cliente o cliente selecionado para alteracao.
     */
    public void menuAlterar(Cliente cliente){
        System.out.println("1) Nome: " + cliente.getNome());
        System.out.println("2) Telefone: " + cliente.getTelefone());
        System.out.println("3) CPF: " + cliente.getCpf());
        System.out.println("4) RG: " + cliente.getRg());
        System.out.println("5) Email: " + cliente.getEmail());
        System.out.println("0) Cancelar");
        System.out.print("Digite o numero correspondente a alteracao desejada: ");
    }

    /**
     * Realiza a alteracao de algum dado do cliente, caso o cliente exista
     */
    @Override
    public void alterar(){
        int opcao;
        String cpf;
        Cliente cliente;
        try{
            System.out.println("\n---- Alteracao de cliente ----");
            System.out.print("Digite o CPF do cliente: ");
            cpf = input.nextLine();
            cliente = buscaPorCpf(cpf);
            if (cliente != null){            
                System.out.println("Cliente encontrado!\n");
                do { 
                    menuAlterar(cliente);
                    opcao = Integer.parseInt(input.nextLine());
                    switch (opcao) {
                        case 1 -> {
                            System.out.print("\nDigite o novo nome: ");
                            String nome = input.nextLine();
                            cliente.setNome(nome);
                            System.out.println("Nome atualizado com sucesso!");
                        }
                        case 2 -> {
                            System.out.print("\nDigite o novo Telefone: ");
                            String telefone = input.nextLine();
                            cliente.setTelefone(telefone);
                            System.out.println("Telefone atualizado com sucesso!");
                        }
                        case 3 -> {
                            System.out.print("\nDigite o novo CPF: ");
                            cpf = input.nextLine();
                            cliente.setCpf(cpf);
                            System.out.println("CPF atualizado com sucesso!");
                        }
                        case 4 -> {
                            System.out.print("\nDigite o novo RG: ");
                            String rg = input.nextLine();
                            cliente.setRg(rg);  
                            System.out.println("RG atualizado com sucesso!");
                        }
                        case 5 -> {
                            System.out.print("\nDigite o novo email: ");
                            String email = input.nextLine();
                            cliente.setEmail(email);
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

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite apenas números.");
            alterar();
        }
    }
    /**
     * Remove um cliente do sistema caso ele nao esteja vinculado a pelo menos um atendimento ou animal.
     */
    @Override
    public void remover(){
        Cliente cliente;
        String cpf;
        boolean inclusoEmAtendimento, temAnimal;
        System.out.println("\n---- Remocao de cliente ----");
        System.out.print("Digite o CPF do cliente: ");
        cpf = input.nextLine();
        cliente = buscaPorCpf(cpf);
        if(cliente != null){            
            inclusoEmAtendimento = sistemaAtendimento.verificaCliente(cpf);
            temAnimal = sistemaAnimal.verificaDono(cpf);
            if(!temAnimal && !inclusoEmAtendimento){
                clientes.remove(cliente);
                System.out.println("\nO cliente foi removido!");
            } else {
                System.out.println("\nO cliente tem pelo menos um animal ou está em pelo menos um atendimento. Não é possível remover.");
            }
        } else {
            System.out.println("Nenhum cliente cadastrado com esse cpf");
        }
    }

    /**
     * Exibe as informacoes de todos os clientes cadastrados.
     */
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

    /**
     * Realiza uma busca sequencial pela lista de clientes para encontrar o cliente
     * que possui o mesmo cpf que o cpf buscado.
     * 
     * @param cpf cpf do cliente a ser encontrado.
     * 
     * @return Cliente caso encontre o cliente, caso contrario retorna null.
     */
    public Cliente buscaPorCpf(String cpf){
        int i = 0;
        while (i < clientes.size()){
            if(clientes.get(i).getCpf().equals(cpf)){
                return clientes.get(i);
            }
            i++;
        }
        return null;
    }

}   