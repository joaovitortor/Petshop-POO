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
        this.sistemaAnimal = new SistemaAnimal(input, sistemaCliente);
        this.sistemaAtendimento = new SistemaAtendimento(input, sistemaCliente, sistemaFuncionario, sistemaAnimal);
    }

    public static void menuPrincipal(){
        System.out.println("\n+--------------------------+");
        System.out.println("|          menu            |");
        System.out.println("+--------------------------+");
        System.out.println("1) Cliente");
        System.out.println("2) Funcionário");
        System.out.println("3) Animal");
        System.out.println("4) Atendimento");
        System.out.println("0) Sair");
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
                default -> System.out.println("Comando errado, tente novamente!\n");
            }
        } while (opcao != 0);
    }
}
