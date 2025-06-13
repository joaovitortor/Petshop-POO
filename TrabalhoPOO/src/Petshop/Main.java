package Petshop;

import java.util.Scanner;

public class Main {
	
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
    
    public static void menuCliente() {
    	System.out.println("\n--------------------------");
    	System.out.println("          Cliente         ");
    	System.out.println("--------------------------");
    	System.out.println("1)Cadastro");
    	System.out.println("2)Consulta");
    	System.out.println("3)Alteração");
    	System.out.println("4)Remoção");
    	System.out.println("0)Voltar");
    	System.out.print("Digite o comando desejado: ");
    }
    
    public static void menuFuncionario() {
    	System.out.println("\n--------------------------");
    	System.out.println("        Funcionário       ");
    	System.out.println("--------------------------");
    	System.out.println("1)Cadastro");
    	System.out.println("2)Consulta");
    	System.out.println("3)Alteração");
    	System.out.println("4)Remoção");
    	System.out.println("0)Voltar");
    	System.out.print("Digite o comando desejado: ");
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
    
    public static void menuAtendimento() {
    	System.out.println("\n--------------------------");
    	System.out.println("        Atendimento       ");
    	System.out.println("--------------------------");
    	System.out.println("1)Cadastro");
    	System.out.println("2)Consulta");
    	System.out.println("3)Alteração");
    	System.out.println("4)Remoção");
    	System.out.println("0)Voltar");
    	System.out.print("Digite o comando desejado: ");
    }
    
    public static void menuRelatorio() {
    	System.out.println("\n--------------------------");
    	System.out.println("        Relatórios        ");
    	System.out.println("--------------------------");
    	System.out.println("1)Clientes cadastrados");
    	System.out.println("2)Funcionários cadastrados");
    	System.out.println("3)Animais cadastrados");
    	System.out.println("4)Atendimentos realizados");
    	System.out.println("0)Voltar");
    	System.out.print("Digite o comando desejado: ");
    }
    
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        GerenciarSistema controlador = new GerenciarSistema(input);
        controlador.iniciar();
    }
}

