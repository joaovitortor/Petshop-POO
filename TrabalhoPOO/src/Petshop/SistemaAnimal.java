package Petshop;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaAnimal implements Crud{
    private ArrayList<Animal> animais;
    private SistemaCliente sistemaCliente;
    private SistemaAtendimento sistemaAtendimento;
    private Scanner input;

    public SistemaAnimal(Scanner input){
        this.animais = new ArrayList<>();
        this.input = input;
    }

    public void setSistemaCliente(SistemaCliente sistemaCliente){
        this.sistemaCliente = sistemaCliente;
    }

    public void setSistemaAtendimento(SistemaAtendimento sistemaAtendimento){
        this.sistemaAtendimento = sistemaAtendimento;
    }

    public static void menuAnimal() {
    	System.out.println("\n+--------------------------+");
    	System.out.println("|           Animal         |");
    	System.out.println("+--------------------------+");
    	System.out.println("| 1) Cadastro              |");
    	System.out.println("| 2) Consulta              |");
    	System.out.println("| 3) Alteração             |");
    	System.out.println("| 4) Remoção               |");
        System.out.println("| 5) Relatorio             |");
    	System.out.println("| 0) Voltar                |");
        System.out.println("+--------------------------+");
    	System.out.print("Digite o comando desejado: ");
    }

    public void operacoesAnimal(){
    	int opcao;
    	do {
            menuAnimal();
            opcao = Integer.parseInt(input.nextLine());
    		switch (opcao){
    			case 1 -> cadastrar();
    			case 2 -> consultar();
    			case 3 -> alterar();
    			case 4 -> remover();
                case 5 -> relatorio();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opcao invalida. Tente novamente.");
    		}
    	} while (opcao != 0);
    }

    @Override
    public void cadastrar(){
        String nome, especie, cpf;
        float peso, altura;
        int id;
        Cliente dono;
        int i = 0;

        System.out.println("\n---- Cadastro de Animal ----");
        System.out.print("Digite o CPF do dono: ");
        cpf = input.nextLine();

        if (sistemaCliente.buscaPorCpf(cpf) != null) {
            dono = sistemaCliente.getListaCliente().get(i);  
            System.out.print("Digite o nome do animal: ");
            nome = input.nextLine();
            System.out.print("Digite a especie do animal: ");
            especie = input.nextLine();
            System.out.print("Digite o peso do animal: ");
            peso = Float.parseFloat(input.nextLine());
            System.out.print("Digite a altura do animal: ");
            altura = Float.parseFloat(input.nextLine());
            if (animais.size() > 0){
                id = animais.get(animais.size() -1).getId() + 1;
            } else {
                id = 1;
            }
            System.out.println("\nId do animal: " + id);
            animais.add(new Animal(nome, especie, peso, altura, dono, id));
            System.out.println("\nCadastro realizado com sucesso!");
        } else{
            System.out.print("\nNenhum cliente cadastrado com esse cpf. Quer cadastrar? [s/n]: ");
            String opcao = input.nextLine();
            switch (opcao) {
                case "s":
                    sistemaCliente.cadastrar();
                    System.out.println("\nCliente cadastrado!");
                    cadastrar();
                    break;
                default:
                    System.out.println("Tente novamente!");
                    cadastrar();
                    break;
            }
        }
    }
    
    @Override
    public void consultar(){
        int id, i = 0;
        String cpf;
        boolean achou = false;
        System.out.println("\n---- Consulta de animal ----");
        System.out.print("Digite o id do animal: ");
        id = Integer.parseInt(input.nextLine());

        while (i < animais.size() && !achou){
            if (animais.get(i).getId() == id){
                achou = true;
            }else{
                i++;
            }
        }

        if (achou){
            System.out.println("\nAnimal encontrado!\n");
            animais.get(i).exibirInformacoes();
        } else {
            System.out.println("\nAnimal não encontrado.\n");
        }
    }

    public void menuAlterar(int i){
				System.out.println("1) Nome: " + animais.get(i).getNome());
				System.out.println("2) Especie: " + animais.get(i).getEspecie());
				System.out.println("3) Peso: " + animais.get(i).getPeso());
				System.out.println("4) Altura: " + animais.get(i).getAltura());
                System.out.println("5) Dono: " + animais.get(i).getDono().getNome());
				System.out.println("0) Cancelar");
                System.out.print("Digite o numero correspondente a alteracao desejada: ");

	}

    public void alterarDonoAnimal(int i, String cpf){
        if (sistemaCliente.buscaPorCpf(cpf) != null){
            animais.get(i).setDono(sistemaCliente.buscaPorCpf(cpf));
            System.out.println("Dono alterado com sucesso!");
        } else {
            System.out.print("Nenhum cliente com esse cpf. Deseja adicionar cliente [s/n]: ");
            String opcao = input.nextLine();
            if (opcao.equals("s")){
                sistemaCliente.cadastrar();
                alterar();
            }
        }
    }
    
    @Override
    public void alterar(){
        System.out.print("\n--- Alteracao de animal ---");
        int opcao, i = verificaAnimal();
        
        if (i > -1){            
            System.out.println("Atendimento encontrado!\n");
            do { 
                menuAlterar(i);
                opcao = Integer.parseInt(input.nextLine());
                switch (opcao) {
                    case 1 -> {
                        System.out.print("\nDigite o novo nome: ");
                        String nome = input.nextLine();
                        animais.get(i).setNome(nome);
                        System.out.println("Nome atualizado com sucesso!");
                    }
                    case 2 -> {
                        System.out.print("\nDigite a nova especie: "); 
                        String especie = input.nextLine();
                        animais.get(i).setEspecie(especie);
                        System.out.println("Especie atualizada com sucesso!");
                    }
                    case 3 -> {
                        System.out.print("\nDigite o novo peso: ");   
                        float peso = Float.parseFloat(input.nextLine());
                        animais.get(i).setPeso(peso);
                        System.out.println("Peso atualizado com sucesso!");
                    }
                    case 4 -> {
                        System.out.print("\nDigite a nova altura: "); 
                        float altura = Float.parseFloat(input.nextLine());
                        animais.get(i).setAltura(altura);
                        System.out.println("Altura atualizada com sucesso!");
                    }
                    case 5 -> {
                        System.out.print("\nDigite o cpf do novo dono: "); 
                        String cpf = input.nextLine();
                        alterarDonoAnimal(i, cpf);
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
    }

    public int verificaAnimal(){
        int i = 0;
        boolean achou = false;
        System.out.print("\nDigite o Id do animal: ");
        int id = Integer.parseInt(input.nextLine());
        while(i < animais.size() && !achou){
            if(animais.get(i).getId() == id){
                achou = true;
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public void remover(){
        boolean inclusoEmAtendimento;
        System.out.println("\n---- Remocao de animal ----");
        int i = verificaAnimal();
        if(i > -1){
            int id = animais.get(i).getId();
            inclusoEmAtendimento = sistemaAtendimento.verificaAnimal(id);
            if(!inclusoEmAtendimento){
                animais.remove(i);
                System.out.println("\nO Animal foi removido!");
            } else {
                System.out.println("\nAnimal está cadastrado em pelo menos um atendimento. Não é possível remover.");
            }
        } else {
            System.out.println("\nNenhum animal cadastrado com esse Id.");
        }
    }

    @Override
    public void relatorio(){
        System.out.println("\n---- Relatorio de animais ----\n");
        if (animais.size() > 0){
            for(Animal animal: animais){
            animal.exibirInformacoes();
            System.out.println("");
            }
        } else {
            System.out.println("Nenhum animal cadastrado.");
        }
    }

    public ArrayList<Animal> getListaAnimal(){
        return animais; 
    }  

    public Animal buscaPorIDAnimal(int id){
        int i = 0;
        boolean achou = false;

        while (i < animais.size() && !achou){
            if(animais.get(i).getId() == id){
                achou = true;
                return animais.get(i);
            }
            i++;
        }
        return null;
    }

    public boolean verificaDono(String cpf){
        int i = 0;
        while (i < animais.size()) {
            if (animais.get(i).getDono().getCpf().equals(cpf)){
                return true;
            }
            i++;
        }
        return false;
    }
}

//colocar mensagem de atendimento cadastrado com sucesso