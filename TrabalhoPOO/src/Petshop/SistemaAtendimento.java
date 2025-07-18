package Petshop;

import java.util.Scanner;
import java.util.ArrayList;

public class SistemaAtendimento implements Crud {

    private ArrayList<Atendimento> atendimentos;
    private Scanner input;
    private SistemaCliente sistemaCliente;
    private SistemaFuncionario sistemaFuncionario;
    private SistemaAnimal sistemaAnimal;

    /**
     * Construtor da classe SistemaAtendimento Inicializa a lista de
     * atendimentos, o input para leitura de dados, o sistemaCliente, o
     * sistemaFuncionario e sistemaAnimal
     *
     * @param input O objeto Scanner utilizado para ler a entrada do usuário.
     * @param sistemaCliente O objeto SistemaCliente utilizado para
     * gerenciamento dos clientes
     * @param sistemaFuncionario O objeto SistemaFuncionario utilizado para
     * gerenciamento dos funcionarios
     * @param sistemaAnimal O objeto SistemaAnimal utilizado para gerencimando
     * dos animais
     */
    public SistemaAtendimento(Scanner input, SistemaCliente sistemaCliente, SistemaFuncionario sistemaFuncionario, SistemaAnimal sistemaAnimal) {
        this.atendimentos = new ArrayList<>();
        this.sistemaCliente = sistemaCliente;
        this.sistemaFuncionario = sistemaFuncionario;
        this.sistemaAnimal = sistemaAnimal;
        this.input = input;
    }

    /**
     * Exibe o menu de opcoes de operacoes com atendimento.
     */
    public static void menuAtendimento() {
        System.out.println("\n+--------------------------+");
        System.out.println("|        Atendimento       |");
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
     * Gerencia a escolha de operacao de atendimento realizada pelo usuario.
     */
    public void operacoesAtendimento() {
        int opcao;
        try {
            do {
                menuAtendimento();
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
                        System.out.println("Opcao invalida. Tente novamente.");
                }
            } while (opcao != 0);

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite apenas números.");
            operacoesAtendimento();
        }
    }

    /**
     * Pede para o usuario informar um cpf e verifica se ja existe um cliente
     * cadastrado com esse cpf caso houver: retorna o cliente, caso contrário,
     * redireciona o usuário para o cadastro de cliente
     *
     * @return O Cliente com o cpf informado
     */
    public Cliente cpfCliente() {
        String opcao, cpf;
        System.out.print("Digite o CPF do cliente: ");
        cpf = input.nextLine();
        if (sistemaCliente.buscaPorCpf(cpf) != null) {
            return sistemaCliente.buscaPorCpf(cpf);
        } else {
            System.out.print("Nenhum cliente cadastrado com esse cpf. Quer cadastrar? [s/n]: ");
            opcao = input.nextLine();
            switch (opcao) {
                case "s" -> {
                    sistemaCliente.cadastrar();
                    return cpfCliente();
                }
                default -> {
                    System.out.println("Tente novamente!\n");
                    return cpfCliente();
                }
            }
        }
    }

    /**
     * Pede para o usuario informar um numero de matricula e verifica se ja
     * existe um funcionario cadastrado com esse numero de matricula. Caso
     * houver: retorna o funcionario, caso contrário, redireciona o usuário para
     * o cadastro de funcionario
     *
     * @return O Funcionario com o numero de matricula informado
     */
    public Funcionario numMatricula() {
        int numMatricula;
        String opcao;
        try {
            System.out.print("Digite o numero de matrícula do funcionário: ");
            numMatricula = Integer.parseInt(input.nextLine());
            if (sistemaFuncionario.buscaPorNumMatricula(numMatricula) != null) {
                return sistemaFuncionario.buscaPorNumMatricula(numMatricula);
            } else {
                System.out.print("Nenhum funcionario cadastrado com esse número. Quer cadastrar? [s/n]: ");
                opcao = input.nextLine();
                switch (opcao) {
                    case "s" -> {
                        sistemaFuncionario.cadastrar();
                        return numMatricula();
                    }
                    default -> {
                        System.out.println("Tente novamente!\n");
                        return numMatricula();
                    }
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite apenas números.");
            return numMatricula();
        }

    }

    /**
     * Pede para o usuario informar um id e verifica se ja existe um animal
     * cadastrado com esse id caso houver: retorna o animal, caso contrário,
     * redireciona o usuário para o cadastro de animal
     *
     * @return O Animal com o id informado
     */
    public Animal idAnimal() {
        int id;
        String opcao;
        try {
            System.out.print("Digite o Id do animal: ");
            id = Integer.parseInt(input.nextLine());
            if (sistemaAnimal.buscaPorIDAnimal(id) != null) {
                return sistemaAnimal.buscaPorIDAnimal(id);
            } else {
                System.out.print("Nenhum animal cadastrado com esse Id. Quer cadastrar? [s/n]: ");
                opcao = input.nextLine();
                switch (opcao) {
                    case "s" -> {
                        sistemaAnimal.cadastrar();
                        return idAnimal();
                    }
                    default -> {
                        System.out.println("Tente novamente!\n");
                        return idAnimal();
                    }
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite apenas números.");
            return idAnimal();
        }

    }

    /**
     * Cadastra um novo atendimento no sistema e insere na lista de
     * atendimentos.
     */
    @Override
    public void cadastrar() {
        int codigo;
        String data;
        Cliente cliente;
        Animal animal;
        Funcionario funcionario;
        System.out.println("\n---- Cadastro de atendimento ----");
        if (atendimentos.isEmpty()) {
            codigo = 1;
        } else {
            codigo = atendimentos.get(atendimentos.size() - 1).getCodigo() + 1;
        }
        System.out.println("Codigo: " + codigo);
        System.out.print("Digite a data: ");
        data = input.nextLine();
        cliente = cpfCliente();
        funcionario = numMatricula();
        animal = idAnimal();

        if (cliente != null && funcionario != null && animal != null) {
            atendimentos.add(new Atendimento(codigo, data, cliente, animal, funcionario));
            System.out.println("Atendimento cadastrado com sucesso!");
        } else {
            System.out.println("Erro no cadastro. Tente novamente.\n");
        }
    }

    /**
     * Verifica se um atendimento está cadastrado pelo codigo e, se estiver,
     * exibe suas informacoes.
     */
    @Override
    public void consultar() {
        int codigo;
        Atendimento atendimento;
        System.out.println("\n---- Consulta de atendimento ----");
        try {
            System.out.print("Digite o codigo do atendimento: ");
            codigo = Integer.parseInt(input.nextLine());
            atendimento = buscaPorCodigoAtendimento(codigo);
            if (atendimento == null) {
                System.out.println("\nAtendimento não encontrado.");
            } else {
                System.out.println("\nAtendimento encontrado!");
                atendimento.exibirInformacoes();
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite apenas números.");
            consultar();
        }
    }

    /**
     * Exibe um menu de opcoes com as informacoes do atendimento e pede para o
     * usuario digitar a opcao correspondente ao atributo ele quer alterar.
     *
     * @param atendimento o atendimento selecionado para alteracao.
     */
    public void menuAlterar(Atendimento atendimento) {
        System.out.println("----------------------");
        System.out.println("1) Data: " + atendimento.getData());
        System.out.println("2) Cliente: " + atendimento.getCliente().getNome());
        System.out.println("3) Animal: " + atendimento.getAnimal().getNome());
        System.out.println("4) Funcionario: " + atendimento.getFuncionario().getNome());
        System.out.println("0) Cancelar");
        System.out.print("Digite o numero correspondente a alteracao desejada: ");
    }

    /**
     * Realiza a alteracao do cliente do atendimento. Caso exista um cliente
     * cadastrado com o cpf informado, é realizada a alteracao. Caso contrario o
     * usuario pode ser redirecionado para o cadastro de cliente ou cancelar a
     * alteracao
     *
     * @param cpf cpf do cliente para ser alterado
     * @param atendimento atendimento no qual o cliente vai ser alterado
     */
    public void alterarClienteAtendimento(Atendimento atendimento, String cpf) {
        String opcao;
        if (sistemaCliente.buscaPorCpf(cpf) != null) {
            atendimento.setCliente(sistemaCliente.buscaPorCpf(cpf));
            System.out.println("Cliente alterado com sucesso!");
        } else {
            System.out.print("Nenhum cliente com esse cpf. Deseja cadastrar um cliente? [s/n]: ");
            opcao = input.nextLine();
            if (opcao.equals("s")) {
                sistemaCliente.cadastrar();
                alterar();
            }
        }
    }

    /**
     * Realiza a alteracao do funcionario do atendimento. Caso exista um
     * funcionario cadastrado com o numero de matricula informado, é realizada a
     * alteracao. Caso contrario o usuario pode ser redirecionado para o
     * cadastro de funcionario ou cancelar a alteracao
     *
     * @param numMat numero de matricula do funcionario para ser alterado
     * @param atendimento atendimento no qual o funcionario vai ser alterado
     */
    public void alterarFuncionarioAtendimento(Atendimento atendimento, int numMat) {
        String opcao;
        if (sistemaFuncionario.buscaPorNumMatricula(numMat) != null) {
            atendimento.setFuncionario(sistemaFuncionario.buscaPorNumMatricula(numMat));
            System.out.println("Funcionario alterado com sucesso!");
        } else {
            System.out.print("Nenhum funcionario com esse numero de matricula. Deseja cadastrar um funcionario? [s/n]: ");
            opcao = input.nextLine();
            if (opcao.equals("s")) {
                sistemaFuncionario.cadastrar();
                alterar();
            }
        }
    }

    /**
     * Realiza a alteracao do ani aldo atendimento. Caso exista um animal
     * cadastrado com o id informado, é realizada a alteracao. Caso contrario o
     * usuario pode ser redirecionado para o cadastro de animal ou cancelar a
     * alteracao
     *
     * @param id id do animal para ser alterado
     * @param atendimento atendimento no qual o animal vai ser alterado
     */
    public void alterarAnimalAtendimento(Atendimento atendimento, int id) {
        String opcao;
        if (sistemaAnimal.buscaPorIDAnimal(id) != null) {
            atendimento.setAnimal(sistemaAnimal.buscaPorIDAnimal(id));
            System.out.println("Animal alterado com sucesso!");
        } else {
            System.out.print("Nenhum animal com esse id. Deseja cadastrar um animal? [s/n]: ");
            opcao = input.nextLine();
            if (opcao.equals("s")) {
                sistemaAnimal.cadastrar();
                alterar();
            }
        }
    }

    /**
     * Realiza a alteracao de algum dado do atendimento, caso o atendimento
     * exista.
     */
    @Override
    public void alterar() {
        int opcao, codigo;
        Atendimento atendimento;
        try {
            System.out.println("--- Alteracao de atendimento ---");
            System.out.print("Digite o codigo do atendimento: ");
            codigo = Integer.parseInt(input.nextLine());
            atendimento = buscaPorCodigoAtendimento(codigo);
            if (atendimento != null) {
                System.out.println("Atendimento encontrado.\n");
                do {
                    menuAlterar(atendimento);
                    opcao = Integer.parseInt(input.nextLine());
                    switch (opcao) {
                        case 1 -> {
                            System.out.print("\nDigite a nova data: ");
                            String data = input.nextLine();
                            atendimento.setData(data);
                            System.out.println("Data alterada com sucesso!");
                        }
                        case 2 -> {
                            System.out.print("\nDigite o cpf do cliente novo: ");
                            String cpf = input.nextLine();
                            alterarClienteAtendimento(atendimento, cpf);
                        }
                        case 3 -> {
                            System.out.print("\nDigite o id do animal novo: ");
                            int id = Integer.parseInt(input.nextLine());
                            alterarAnimalAtendimento(atendimento, id);
                        }
                        case 4 -> {
                            System.out.print("\nDigite o número de matrícula do funcionário novo: ");
                            int numMat = Integer.parseInt(input.nextLine());
                            alterarFuncionarioAtendimento(atendimento, numMat);
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
                System.out.println("Nenhum atendimento com esse código.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite apenas números.");
            alterar();
        }

    }

    /**
     * Remove um atendimento do sistema.
     */
    @Override
    public void remover() {
        int codigo;
        Atendimento atendimento;
        try {
            System.out.println("\n---- Remocao de atedimento ----");
            System.out.print("Digite o codigo do atendimento: ");
            codigo = Integer.parseInt(input.nextLine());
            atendimento = buscaPorCodigoAtendimento(codigo);
            if (atendimento != null) {
                atendimentos.remove(atendimento);
                System.out.println("\nO atendimento foi removido!");
            } else {
                System.out.println("\nAtendimento nao encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite apenas números.");
            remover();
        }
    }

    /**
     * Exibe as informacoes de todos os atendimentos cadastrados.
     */
    @Override
    public void relatorio() {
        System.out.println("\n--- Relatorio de atendimentos ---\n");
        if (!atendimentos.isEmpty()) {
            for (Atendimento atendimento : atendimentos) {
                atendimento.exibirInformacoes();
            }
        } else {
            System.out.println("Nenhum antendimento cadastrado.");
        }
    }

    /**
     * Realiza uma busca sequencial pela lista de atendimentos para encontrar o
     * atendimento que possui o mesmo codigo que o codigo buscado.
     *
     * @param codigo codigo do atendimento a ser encontrado.
     *
     * @return Atendimento caso encontre o atendimento, caso contrario retorna
     * null.
     */
    public Atendimento buscaPorCodigoAtendimento(int codigo) {
        int i = 0;
        while (i < atendimentos.size()) {
            if (atendimentos.get(i).getCodigo() == codigo) {
                return atendimentos.get(i);
            }
            i++;
        }
        return null;
    }

    /**
     * Realiza uma busca sequencial pela lista de atendimentos para encontrar
     * pelo menos um atendimento que possua um funcionario com o numero de
     * matricula correspondente ao numero de matricula buscado.
     *
     * @param numMat numero de matricula do funcionario a ser buscado.
     *
     * @return true caso encontre o funcionario, caso contrario retorna false.
     */
    public boolean verificaFuncionario(int numMat) {
        int i = 0;
        while (i < atendimentos.size()) {
            if (atendimentos.get(i).getFuncionario().getNumMatricula() == numMat) {
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * Realiza uma busca sequencial pela lista de atendimentos para encontrar o
     * animal que possui o mesmo id que o id o buscado.
     *
     * @param id id do animal a ser encontrado.
     *
     * @return true caso encontre o animal, caso contrario retorna false.
     */
    public boolean verificaAnimal(int id) {
        int i = 0;
        while (i < atendimentos.size()) {
            if (atendimentos.get(i).getAnimal().getId() == id) {
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * Realiza uma busca sequencial pela lista de atendimentos para encontrar o
     * cliente que possui o mesmo cpf que o cpf o buscado.
     *
     * @param cpf cpf do cliente a ser encontrado.
     *
     * @return true caso encontre o cliente, caso contrario retorna false.
     */
    public boolean verificaCliente(String cpf) {
        int i = 0;
        while (i < atendimentos.size()) {
            if (atendimentos.get(i).getCliente().getCpf().equals(cpf)) {
                return true;
            }
            i++;
        }
        return false;
    }
}
