package Petshop;

import java.util.Scanner;

public class GerenciarSistema {
    private Scanner input;
    protected SistemaCliente sistemaCliente;
    protected SistemaFuncionario sistemaFuncionario;
    protected SistemaAnimal sistemaAnimal;
    protected SistemaAtendimento sistemaAtendimento;

    public GerenciarSistema(Scanner input){
        this.input = input;
        this.sistemaCliente = new SistemaCliente(input);
        this.sistemaFuncionario = new SistemaFuncionario(input);
        this.sistemaAnimal = new SistemaAnimal(input);
        this.sistemaAtendimento = new SistemaAtendimento(input);
    }

    public static void menuPrincipal(){
        System.out.println("\n+--------------------------+");
        System.out.println("|          menu            |");
        System.out.println("+--------------------------+");
        System.out.println("1) Cliente");
        System.out.println("2) Funcionário");
        System.out.println("3) Animal");
        System.out.println("4) Atendimento");
        System.out.println("5) Relatórios");
        System.out.println("0) Sair");
        System.out.print("Digite o comando desejado: ");
    }

    public static void menuRelatorio() {
    	System.out.println("\n+--------------------------+");
    	System.out.println("|        Relatórios        |");
    	System.out.println("+--------------------------+");
    	System.out.println("1)Clientes cadastrados");
    	System.out.println("2)Funcionários cadastrados");
    	System.out.println("3)Animais cadastrados");
    	System.out.println("4)Atendimentos realizados");
    	System.out.println("0)Voltar");
    	System.out.print("Digite o comando desejado: ");
    }

    public void iniciar() {
        int opcao;
        do {
            menuPrincipal();
            opcao = Integer.parseInt(input.nextLine());
            switch(opcao) {
                case 1 -> sistemaCliente.operacoesCliente();
                case 2 -> sistemaFuncionario.operacoesFuncionario();
                case 3 -> sistemaAnimal.operacoesAnimal();
                case 4 -> sistemaAtendimento.operacoesAtendimento();
                case 5 -> menuRelatorio();
                default -> System.out.println("Comando errado, tente novamente!\n");
            }
        } while (opcao != 0);
    }
}
