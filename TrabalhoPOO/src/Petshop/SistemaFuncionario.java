package Petshop;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaFuncionario implements Crud {

    private ArrayList<Funcionario> funcionarios;
    private SistemaAtendimento sistemaAtendimento;
    private Scanner input;

    /**
     * Construtor da classe SistemaFuncionario Inicializa a lista de
     * funcionarios e o input para leitura de dados. O sistemaAtendimento é
     * inicializado posteriormente para evitar problemas relacionados a
     * dependencia circular.
     *
     * @param input O objeto Scanner utilizado para ler a entrada do usuário.
     */
    public SistemaFuncionario(Scanner input) {
        this.funcionarios = new ArrayList<>();
        this.input = input;
    }

    /**
     * Inicializa o sistemaAtendimento.
     * 
     * @param sistemaAtendimento sistemaAtendimento a ser inicializado
     */
    public void setSistemaAtendimento(SistemaAtendimento sistemaAtendimento) {
        this.sistemaAtendimento = sistemaAtendimento;
    }

    /**
     * Exibe o menu de opcoes de operacoes com funcionario.
     */
    public static void menuFuncionario() {
        System.out.println("\n+--------------------------+");
        System.out.println("|        Funcionário       |");
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
     * Gerencia a escolha de operacao de funcionario realizada pelo usuario.
     */
    public void operacoesFuncionario() {
        int opcao;
        try {
            do {
                menuFuncionario();
                opcao = Integer.parseInt(input.nextLine());
                switch (opcao) {
                    case 1 ->
                        cadastrar();
                    case 2 ->
                        consultar();
                    case 3 ->
                        alterar();
                    case 4 ->
                        remover();
                    case 5 ->
                        relatorio();
                    case 0 ->
                        System.out.println("Voltando...");
                    default ->
                        System.out.println("Opcao invalida. Tente novamente!");
                }
            } while (opcao != 0);

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite apenas números.");
            operacoesFuncionario();
        }
    }

    /**
     * Cadastra um novo funcionario no sistema e insere na lista de
     * funcionarios.
     */
    @Override
    public void cadastrar() {
        String nome, qualificacao, descricaoFuncao;
        int cargaHorariaSemanal, numMatricula;
        try {
            System.out.println("\n---- Cadastro de funcionario ----");
            System.out.print("Digite o numero da matricula do funcionario: ");
            numMatricula = Integer.parseInt(input.nextLine());

            if (buscaPorNumMatricula(numMatricula) == null) {
                System.out.print("Digite o nome do funcionario: ");
                nome = input.nextLine();
                System.out.print("Digite a qualificacao do funcionario: ");
                qualificacao = input.nextLine();
                System.out.print("Digite a descricao da função do funcionario: ");
                descricaoFuncao = input.nextLine();
                System.out.print("Digite a carga horaria semanal do funcionario: ");
                cargaHorariaSemanal = Integer.parseInt(input.nextLine());
                funcionarios.add(new Funcionario(nome, qualificacao, descricaoFuncao, cargaHorariaSemanal, numMatricula));
                System.out.println("\nCadastro realizado com sucesso!");
            } else {
                System.out.println("Já possui um funcionario cadastrado com esse numero de matricula!\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite apenas números.");
            cadastrar();
        }
    }

    /**
     * Verifica se um funcionario está cadastrado pelo numero de matricula e, se
     * estiver, exibe suas informacoes.
     */
    @Override
    public void consultar() {
        int numMatricula;
        Funcionario funcionario;
        System.out.println("\n---- Consulta de funcionarios ----");
        try {
            System.out.print("Digite o numero de matricula do funcionario: ");
            numMatricula = Integer.parseInt(input.nextLine());
            funcionario = buscaPorNumMatricula(numMatricula);
            if (funcionario != null) {
                System.out.println("\nFuncionario encontrado!\n");
                funcionario.exibirInformacoes();
            } else {
                System.out.println("\nFuncionario não encontrado.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite apenas números.");
            consultar();
        }
    }

    /**
     * Exibe um menu de opcoes com as informacoes do funcionario e pede para o
     * usuario digitar a opcao correspondente ao atributo ele quer alterar.
     *
     * @param funcionario o funcionario selecionado para alteracao.
     */
    public void menuAlterar(Funcionario funcionario) {
        System.out.println("1) Nome: " + funcionario.getNome());
        System.out.println("2) Qualificacao: " + funcionario.getQualificacao());
        System.out.println("3) Descricao da funcao: " + funcionario.getDescricaoFuncao());
        System.out.println("4) Carga horaria semanal: " + funcionario.getCargaHorariaSemanal());
        System.out.println("5) Numero de matricula: " + funcionario.getNumMatricula());
        System.out.println("0) Cancelar");
        System.out.print("Digite o numero correspondente a alteracao desejada: ");
    }

    /**
     * Realiza a alteracao de algum dado do funcionario, caso o funcionario
     * exista.
     */
    @Override
    public void alterar() {
        int opcao, numMatricula;
        Funcionario funcionario;
        try {
            System.out.println("\n---- Alteracao de funcionario -----");
            System.out.print("Digite o numero de matricula: ");
            numMatricula = Integer.parseInt(input.nextLine());
            funcionario = buscaPorNumMatricula(numMatricula);
            if (funcionario != null) {
                System.out.println("Funcionario encontrado.\n");
                do {
                    menuAlterar(funcionario);
                    opcao = Integer.parseInt(input.nextLine());
                    switch (opcao) {
                        case 1 -> {
                            System.out.print("\nDigite o novo nome: ");
                            String nome = input.nextLine();
                            funcionario.setNome(nome);
                            System.out.println("Nome atualizado com sucesso!");
                        }
                        case 2 -> {
                            System.out.print("\nDigite o nova qualificacao: ");
                            String qualificacao = input.nextLine();
                            funcionario.setQualificacao(qualificacao);
                            System.out.println("Qualificacao alterada com sucesso!");
                        }
                        case 3 -> {
                            System.out.print("\nDigite a nova descricao da funcao: ");
                            String descricaoFuncao = input.nextLine();
                            funcionario.setDescricaoFuncao(descricaoFuncao);
                            System.out.println("Descricao alterada com sucesso!");
                        }
                        case 4 -> {
                            System.out.print("\nDigite a nova carga horaria semanal: ");
                            int cargaHorariaSemanal = Integer.parseInt(input.nextLine());
                            funcionario.setCargaHorariaSemanal(cargaHorariaSemanal);
                            System.out.println("Carga horaria alterada com sucesso!");
                        }
                        case 5 -> {
                            System.out.print("\nDigite o novo numero de matricula ");
                            numMatricula = Integer.parseInt(input.nextLine());
                            funcionario.setNumMatricula(numMatricula);
                            System.out.println("Numero da matricula alterado com sucesso!");
                        }
                        case 0 -> {
                            System.out.println("\nVoltando...");
                        }
                        default -> {
                            System.out.println("Comando invalido. Tente novamente");
                        }
                    }
                } while (opcao < 0 || opcao > 5);
            } else {
                System.out.println("Funcionario não encontrado.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite apenas números.");
            alterar();
        }
    }

    /**
     * Remove um funcionario do sistema caso ele nao esteja vinculado a pelo
     * menos um atendimento.
     */
    @Override
    public void remover() {
        int numMatricula;
        boolean inclusoEmAtendimento;
        Funcionario funcionario;
        try {
            System.out.println("\n---- Remocao de funcionario ----");
            System.out.print("Digite o numero de matricula do funcionario: ");
            numMatricula = Integer.parseInt(input.nextLine());
            funcionario = buscaPorNumMatricula(numMatricula);
            if (funcionario != null) {
                inclusoEmAtendimento = sistemaAtendimento.verificaFuncionario(numMatricula);
                if (!inclusoEmAtendimento) {
                    funcionarios.remove(funcionario);
                    System.out.println("\nO funcionário foi removido!");
                } else {
                    System.out.println("\nEsse funcionário está cadastrado em pelo menos um atendimento. Não é possível remover.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite apenas números.");
            remover();
        }
    }

    /**
     * Exibe as informacoes de todos os funcionarios cadastrados.
     */
    @Override
    public void relatorio() {
        System.out.println("\n---- Relatorio de funcionarios ----\n");
        if (!funcionarios.isEmpty()) {
            for (Funcionario funcionario : funcionarios) {
                funcionario.exibirInformacoes();
                System.out.println("");
            }
        } else {
            System.out.println("Nenhum funcionario cadastrado.\n");
        }
    }

    /**
     * Realiza uma busca sequencial pela lista de funcionarios para encontrar o
     * funcionario que possui o mesmo numero de matricula que o numero de
     * matricula buscado.
     *
     * @param numMatricula numero de matricula do funcionario a ser encontrado.
     *
     * @return Funcionario caso encontre o funcionario, caso contrario retorna
     * null.
     */
    public Funcionario buscaPorNumMatricula(int numMatricula) {
        int i = 0;
        while (i < funcionarios.size()) {
            if (funcionarios.get(i).getNumMatricula() == numMatricula) {
                return funcionarios.get(i);
            }
            i++;
        }
        return null;
    }
}
