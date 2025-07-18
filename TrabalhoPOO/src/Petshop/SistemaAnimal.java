package Petshop;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaAnimal implements Crud {

    private ArrayList<Animal> animais;
    private SistemaCliente sistemaCliente;
    private SistemaAtendimento sistemaAtendimento;
    private Scanner input;

    /**
     * Construtor da classe SistemaAnimal. Inicializa a lista de animais e o
     * input para leitura de dados. O sistemaAtendimento e sistemaCliente sao
     * inicializados posteriormente para evitar problemas relacionados a
     * dependencia circular.
     *
     * @param input O objeto Scanner utilizado para ler a entrada do usuário.
     */
    public SistemaAnimal(Scanner input) {
        this.animais = new ArrayList<>();
        this.input = input;
    }

    /**
     * Inicializa o sistemaCliente.
     * 
     * @param sistemaCliente o sistemaCliente a ser inicializado
     */
    public void setSistemaCliente(SistemaCliente sistemaCliente) {
        this.sistemaCliente = sistemaCliente;
    }

    /**
     * Inicializa o sistemaAtendimento.
     * 
     * @param sistemaAtendimento o sistemaAtendimento a ser inicializado
     */
    public void setSistemaAtendimento(SistemaAtendimento sistemaAtendimento) {
        this.sistemaAtendimento = sistemaAtendimento;
    }

    /**
     * Exibe o menu de opcoes de operacoes com animal.
     */
    public static void menuAnimal() {
        System.out.println("\n+--------------------------+");
        System.out.println("|           Animal         |");
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
     * Gerencia a escolha de operacao de animal realizada pelo usuario.
     */
    public void operacoesAnimal() {
        int opcao;
        try {
            do {
                menuAnimal();
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
            operacoesAnimal();
        }
    }

    /**
     * Cadastra um novo animal no sistema e insere na lista de animais.
     */
    @Override
    public void cadastrar() {
        String nome, especie, cpf;
        float peso, altura;
        int id;
        Cliente dono;
        try {
            System.out.println("\n---- Cadastro de Animal ----");
            System.out.print("Digite o CPF do dono: ");
            cpf = input.nextLine();
            dono = sistemaCliente.buscaPorCpf(cpf);
            if (dono != null) {
                System.out.print("Digite o nome do animal: ");
                nome = input.nextLine();
                System.out.print("Digite a especie do animal: ");
                especie = input.nextLine();
                System.out.print("Digite o peso do animal: ");
                peso = Float.parseFloat(input.nextLine());
                System.out.print("Digite a altura do animal: ");
                altura = Float.parseFloat(input.nextLine());
                if (animais.isEmpty()) {
                    id = 1;
                } else {
                    id = animais.get(animais.size() - 1).getId() + 1;
                }
                System.out.println("\nId do animal: " + id);
                animais.add(new Animal(nome, especie, peso, altura, dono, id));
                System.out.println("\nCadastro realizado com sucesso!");
            } else {
                System.out.print("\nNenhum cliente cadastrado com esse cpf. Quer cadastrar? [s/n]: ");
                String opcao = input.nextLine();
                switch (opcao) {
                    case "s" -> {
                        sistemaCliente.cadastrar();
                        System.out.println("\nCliente cadastrado!");
                        cadastrar();
                    }
                    default -> {
                        System.out.println("Tente novamente!");
                        cadastrar();
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite apenas números.");
            cadastrar();
        }
    }

    /**
     * Verifica se um animal está cadastrado pelo id e, se estiver, exibe suas
     * informacoes.
     */
    @Override
    public void consultar() {
        int id;
        Animal animal;
        System.out.println("\n---- Consulta de animal ----");
        try {
            System.out.print("Digite o id do animal: ");
            id = Integer.parseInt(input.nextLine());
            animal = buscaPorIDAnimal(id);
            if (animal != null) {
                System.out.println("\nAnimal encontrado!\n");
                animal.exibirInformacoes();
            } else {
                System.out.println("\nAnimal não encontrado.\n");
            }

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite apenas números.");
            consultar();
        }
    }

    /**
     * Exibe um menu de opcoes com as informacoes do animal e pede para o
     * usuario digitar a opcao correspondente ao atributo ele quer alterar.
     *
     * @param animal o animal selecionado para alteracao.
     */
    public void menuAlterar(Animal animal) {
        System.out.println("1) Nome: " + animal.getNome());
        System.out.println("2) Especie: " + animal.getEspecie());
        System.out.println("3) Peso: " + animal.getPeso());
        System.out.println("4) Altura: " + animal.getAltura());
        System.out.println("5) Dono: " + animal.getDono().getNome());
        System.out.println("0) Cancelar");
        System.out.print("Digite o numero correspondente a alteracao desejada: ");
    }

    /**
     * Realiza a alteração do dono do animal. Caso exista um cliente(dono) com o
     * cpf informado, é realizada a alteração. Caso contrário, o usuario pode
     * ser redirecionado para o cadastro de cliente ou cancelar a alteracao.
     *
     * @param animal o animal selecionado para a alteracao do dono.
     * @param cpf do novo dono.
     */
    public void alterarDonoAnimal(Animal animal, String cpf) {
        if (sistemaCliente.buscaPorCpf(cpf) != null) {
            animal.setDono(sistemaCliente.buscaPorCpf(cpf));
            System.out.println("Dono alterado com sucesso!");
        } else {
            System.out.print("Nenhum cliente com esse cpf. Deseja adicionar cliente [s/n]: ");
            String opcao = input.nextLine();
            if (opcao.equals("s")) {
                sistemaCliente.cadastrar();
                alterar();
            }
        }
    }

    /**
     * Realiza a alteracao de algum dado do animal, caso o animal exista.
     */
    @Override
    public void alterar() {
        int opcao, id;
        Animal animal;
        try {
            System.out.print("\n--- Alteracao de animal ---");
            System.out.print("\nDigite o Id do animal: ");
            id = Integer.parseInt(input.nextLine());
            animal = buscaPorIDAnimal(id);
            if (animal != null) {
                System.out.println("Atendimento encontrado!\n");
                do {
                    menuAlterar(animal);
                    opcao = Integer.parseInt(input.nextLine());
                    switch (opcao) {
                        case 1 -> {
                            System.out.print("\nDigite o novo nome: ");
                            String nome = input.nextLine();
                            animal.setNome(nome);
                            System.out.println("Nome atualizado com sucesso!");
                        }
                        case 2 -> {
                            System.out.print("\nDigite a nova especie: ");
                            String especie = input.nextLine();
                            animal.setEspecie(especie);
                            System.out.println("Especie atualizada com sucesso!");
                        }
                        case 3 -> {
                            System.out.print("\nDigite o novo peso: ");
                            float peso = Float.parseFloat(input.nextLine());
                            animal.setPeso(peso);
                            System.out.println("Peso atualizado com sucesso!");
                        }
                        case 4 -> {
                            System.out.print("\nDigite a nova altura: ");
                            float altura = Float.parseFloat(input.nextLine());
                            animal.setAltura(altura);
                            System.out.println("Altura atualizada com sucesso!");
                        }
                        case 5 -> {
                            System.out.print("\nDigite o cpf do novo dono: ");
                            String cpf = input.nextLine();
                            alterarDonoAnimal(animal, cpf);
                        }
                        case 0 -> {
                            System.out.println("\nVoltando...");
                        }
                        default -> {
                            System.out.println("Comando invalido. Tente novamente");
                        }
                    }
                } while (opcao < 0 || opcao > 6);
            } else {
                System.out.println("Nenhum animal com esse id.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite apenas números.");
            alterar();
        }
    }

    /**
     * Remove um animal do sistema caso ele nao esteja vinculado a pelo menos um
     * atendimento.
     */
    @Override
    public void remover() {
        boolean inclusoEmAtendimento;
        int id;
        Animal animal;
        try {
            System.out.println("\n---- Remocao de animal ----");
            System.out.print("Digite o id do animal: ");
            id = Integer.parseInt(input.nextLine());
            animal = buscaPorIDAnimal(id);
            if (animal != null) {
                inclusoEmAtendimento = sistemaAtendimento.verificaAnimal(id);
                if (!inclusoEmAtendimento) {
                    animais.remove(animal);
                    System.out.println("\nO Animal foi removido!");
                } else {
                    System.out.println("\nAnimal está cadastrado em pelo menos um atendimento. Não é possível remover.");
                }
            } else {
                System.out.println("\nNenhum animal cadastrado com esse Id.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite apenas números.");
            remover();
        }
    }

    /**
     * Exibe as informacoes de todos os animais cadastrados.
     */
    @Override
    public void relatorio() {
        System.out.println("\n---- Relatorio de animais ----\n");
        if (!animais.isEmpty()) {
            for (Animal animal : animais) {
                animal.exibirInformacoes();
                System.out.println("");
            }
        } else {
            System.out.println("Nenhum animal cadastrado.");
        }
    }

    /**
     * Realiza uma busca sequencial pela lista de animais para encontrar o
     * animal que possui o mesmo id que o id buscado.
     *
     * @param id id do animal a ser encontrado.
     *
     * @return Animal caso encontre o animal, caso contrario retorna null.
     */
    public Animal buscaPorIDAnimal(int id) {
        int i = 0;
        while (i < animais.size()) {
            if (animais.get(i).getId() == id) {
                return animais.get(i);
            }
            i++;
        }
        return null;
    }

    /**
     * Realiza uma busca sequencia pela lista de animais para encontrar algum
     * animal que possua um dono com cpf correspondente ao cpf buscado.
     *
     * @param cpf cpf do cliente(dono) a ser encontrado.
     *
     * @return true caso encontre o dono, caso contrario retorna false.
     */
    public boolean verificaDono(String cpf) {
        int i = 0;
        while (i < animais.size()) {
            if (animais.get(i).getDono().getCpf().equals(cpf)) {
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * A função busca na lista de animais, os que possuem dono com o cpf
     * correspondente ao cpf buscado e insere-os na lista animaisDoCpf.
     *
     * @param cpf cpf do cliente(dono) para a busca dos animais.
     *
     * @return animaisDoCPF caso o cliente possua animais, caso contrario
     * retorna null.
     */
    public ArrayList<Animal> animaisPorDono(String cpf) {
        ArrayList<Animal> animaisDoCpf = new ArrayList<>();
        int i = 0;
        while (i < animais.size()) {
            if (animais.get(i).getDono().getCpf().equals(cpf)) {
                animaisDoCpf.add(animais.get(i));
            }
            i++;
        }
        if (!animaisDoCpf.isEmpty()) {
            return animaisDoCpf;
        } else {
            return null;
        }
    }
}
