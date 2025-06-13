package Petshop;

import java.util.Scanner;
import java.util.ArrayList;

public class SistemaAtendimento {
    private ArrayList<Atendimento> atendimentos;
    private Scanner input;

    public SistemaAtendimento(Scanner input){
        this.atendimentos = new ArrayList<>();
        this.input = input;
    }

    public static void cadastrarAtendimento(Scanner input, ArrayList<Atendimento> atendimentos) {
    	int codigo;
    	String data, cliente, animal, funcionario;
    	
    	System.out.print("Digite o código do antendimento: ");
    	codigo = input.nextInt();
    	System.out.print("Digite a data: ");
    	data = input.nextLine();
    	System.out.print("Digite o nome do cliente: ");
    	cliente = input.nextLine();
    	System.out.print("Digite o nome do animal: ");
    	animal = input.nextLine();
    	System.out.print("Digite o nome do funcionario: ");
    	funcionario = input.nextLine();
    }

    public static void consultarAtendimento(){

    }

    public static void alterarAtendimento(){
        
    }
}
