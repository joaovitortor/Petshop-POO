package Petshop;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaAnimal extends SistemaCliente{
    private ArrayList<Animal> animais;
    private Scanner input;

    public SistemaAnimal(Scanner input){
        super();
        this.animais = new ArrayList<>();
        this.input = input; 
    }

    public void cadastrarAnimal(){
        String nome, especie, cpf;
        float peso, altura;
        Cliente dono;
        int i = 0;


        System.out.print("Digite o nome do animal: ");
        nome = input.nextLine();
        System.out.print("Digite o peso do animal: ");
        especie = input.nextLine();
        System.out.print("Digite a espécie do animal: ");
        peso = input.nextFloat();
        System.out.print("Digite a altura do animal do funcionario: ");
        altura = input.nextFloat();
        System.out.print("Digite o CPF do dono: ");
        do{
            cpf = input.nextLine();
            i++;
        }while(i < clientes.size() && !clientes.get(i).getCpf().equals(cpf));
        if (i < clientes.size() && clientes.get(i).getCpf().equals(cpf)) {
            dono = clientes.get(i);
            animais.add(new Animal(nome, especie, peso, altura, dono));  
        } else{
            System.out.println("Nenhum cliente cadastrado com esse cpf. Quer cadastrar?");
        }

    }

    public static void consultarAnimal(){

    }
    
    public static void alterarAnimal(){

    }
}
