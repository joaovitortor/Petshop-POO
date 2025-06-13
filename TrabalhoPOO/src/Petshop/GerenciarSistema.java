package Petshop;


import java.util.Scanner;

public class GerenciarSistema {
    private Scanner input;
    private SistemaCliente sistemaCliente;
    private SistemaFuncionario sistemaFuncionario;
    private SistemaAnimal sistemaAnimal;
    private SistemaAtendimento sistemaAtendimento;

    public GerenciarSistema(Scanner input){
        this.input = input;
        this.sistemaCliente = new SistemaCliente(input);
        this.sistemaFuncionario = new SistemaFuncionario(input);
        //this.sistemaAnimal = new SistemaAnimal(input);
        //this.sistemaAtendimento = new SistemaAtendimento
    }

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

    public void iniciar() {
        int opcao;
        do {
            menuPrincipal();
            opcao = Integer.parseInt(input.nextLine());
            switch(opcao) {
                case 1 -> sistemaCliente.operacoesCliente();
                //case 2 -> sistemaFuncionario.menuFuncionario();
                //case 3 -> sistemaAnimal.menuAnimal();
                //case 4 -> sistemaAtendimento.menuAtendimento();
            }
        } while (opcao != 0);
    }
}
