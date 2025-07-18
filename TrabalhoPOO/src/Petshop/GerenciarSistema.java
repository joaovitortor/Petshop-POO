package Petshop;

import java.util.Scanner;

public class GerenciarSistema {

    private Scanner input;
    protected SistemaCliente sistemaCliente;
    protected SistemaFuncionario sistemaFuncionario;
    protected SistemaAnimal sistemaAnimal;
    protected SistemaAtendimento sistemaAtendimento;

    /**
     * Construtor da classe GerenciarSistema. Inicializa a lista de clientes e o
     * input para leitura de dados. Inicializa o sistemaCliente com o input para
     * leitura de dados. Inicializa o sistemaFuncionario com o input para
     * leitura de dados. Inicialia o sistemaAnimal com o input para leitura de
     * dados. Inicializa o sistemaAtendimento com o input para leitura de dados,
     * com o sistemaCliente, com o sistemaFuncionario e com o sistemaAnimal.
     *
     * Apos todos os sistemas serem inicializados: Seta o sistemaAtendimento e o
     * sistemaAnimal no sistemaCliente para evitar problemas relacinados a
     * dependencia circular.
     *
     * Seta o sistemaAtendimento no sistemaFuncionario para evitar problemas
     * relacionados a dependencia circular.
     *
     * Seta o sistemaCliente e o sistemaAtendimento no sistemaAnimal para evitar
     * problemas relacionados a dependencia circular.
     *
     * @param input O objeto Scanner utilizado para ler a entrada do usuario.
     */
    public GerenciarSistema(Scanner input) {
        this.input = input;
        this.sistemaCliente = new SistemaCliente(input);
        this.sistemaFuncionario = new SistemaFuncionario(input);
        this.sistemaAnimal = new SistemaAnimal(input);
        this.sistemaAtendimento = new SistemaAtendimento(input, this.sistemaCliente, this.sistemaFuncionario, this.sistemaAnimal);

        this.sistemaCliente.setSistemaAtendimento(this.sistemaAtendimento);
        this.sistemaCliente.setSistemaAnimal(this.sistemaAnimal);

        this.sistemaFuncionario.setSistemaAtendimento(this.sistemaAtendimento);

        this.sistemaAnimal.setSistemaCliente(this.sistemaCliente);
        this.sistemaAnimal.setSistemaAtendimento(this.sistemaAtendimento);
    }

    /**
     * Exibe o menu de operacoes do Petshop.
     */
    public static void menuPrincipal() {
        System.out.println("\n+--------------------------+");
        System.out.println("|         Petshop          |");
        System.out.println("+--------------------------+");
        System.out.println("| 1) Cliente               |");
        System.out.println("| 2) Funcionario           |");
        System.out.println("| 3) Animal                |");
        System.out.println("| 4) Atendimento           |");
        System.out.println("| 0) Sair                  |");
        System.out.println("+--------------------------+");
        System.out.print("Digite o comando desejado: ");
    }

    /**
     * Inicializa o sistema de gerenciamento do petshop.
     */
    public void iniciar() {
        int opcao;
        try {
            do {
                menuPrincipal();
                opcao = Integer.parseInt(input.nextLine());
                switch (opcao) {
                    case 1 ->
                        sistemaCliente.operacoesCliente();
                    case 2 ->
                        sistemaFuncionario.operacoesFuncionario();
                    case 3 ->
                        sistemaAnimal.operacoesAnimal();
                    case 4 ->
                        sistemaAtendimento.operacoesAtendimento();
                    case 0 ->
                        System.out.println("Saindo...");
                    default ->
                        System.out.println("Comando errado. tente novamente!\n");
                }
            } while (opcao != 0);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite apenas números.");
            iniciar();
        }
    }
}
