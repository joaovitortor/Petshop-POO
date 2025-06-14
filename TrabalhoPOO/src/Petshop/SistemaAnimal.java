package Petshop;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaAnimal implements Crud{
    private ArrayList<Animal> animais;
    private SistemaCliente sistemaCliente;
    private Scanner input;

    public SistemaAnimal(Scanner input, SistemaCliente sistemaCliente){
        this.animais = new ArrayList<>();
        this.sistemaCliente = sistemaCliente;
        this.input = input; 
    }

    public static void menuAnimal() {
    	System.out.println("\n--------------------------");
    	System.out.println("          Animal          ");
    	System.out.println("--------------------------");
    	System.out.println("1)Cadastro");
    	System.out.println("2)Consulta");
    	System.out.println("3)Alteração");
    	System.out.println("4)Remoção");
    	System.out.println("0)Voltar");
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
            System.out.print("Digite a altura do animal do funcionario: ");
            altura = Float.parseFloat(input.nextLine());
            id = animais.size() + 1;
            System.out.println("Id do animal: " + id);
            animais.add(new Animal(nome, especie, peso, altura, dono, id));
        } else{
            System.out.print("Nenhum cliente cadastrado com esse cpf. Quer cadastrar? [s/n]");
            String opcao = input.nextLine();
            switch (opcao) {
                case "s":
                    System.out.println("\nCadastro Cliente:");
                    sistemaCliente.cadastrar();
                    System.out.println("\nCliente cadastrado. Insira o CPF novamente.\n");
                    break;
                default:
                    System.out.println("Tente novamente!\n");
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
            System.out.println("\nAnimal encontrado!");
            animais.get(i).exibirInformacoes();
        } else {
            System.out.println("\nAnimal não encontrado");
        }
    }

    @Override
    public void alterar(){

    }

    @Override
    public void remover(){

    }

    @Override
    public void relatorio(){
        
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
}

