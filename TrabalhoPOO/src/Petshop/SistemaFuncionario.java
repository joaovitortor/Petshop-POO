package Petshop;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaFuncionario implements Crud{
    private ArrayList<Funcionario> funcionarios;
    private Scanner input;

    public SistemaFuncionario(Scanner input){
        this.funcionarios = new ArrayList<>();
        this.input = input;
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
    
    public void operacoesFuncionario(){
    	int opcao;
    	do {
            menuFuncionario();
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
        String nome, qualificacao, descricaoFuncao;
        int cargaHorariaSemanal, numMatricula;
        System.out.print("Digite o nome do funcionario: ");
        nome = input.nextLine();
        System.out.print("Digite a qualificacao do funcionario: ");
        qualificacao = input.nextLine();
        System.out.print("Digite a descricao da função do funcionario: ");
        descricaoFuncao = input.nextLine();
        System.out.print("Digite a carga horaria semanal do funcionario: ");
        cargaHorariaSemanal = Integer.parseInt(input.nextLine());
        System.out.print("Digite o numero da matricula do funcionario: ");
        numMatricula = Integer.parseInt(input.nextLine());
        funcionarios.add(new Funcionario(nome, qualificacao, descricaoFuncao, cargaHorariaSemanal, numMatricula));
    }

    @Override
    public void consultar(){
        int numMatricula, i = 0;
        System.out.print("Digite o numero de matricula do funcionario: ");
        numMatricula = Integer.parseInt(input.nextLine());

        while (i < funcionarios.size() && funcionarios.get(i).getNumMatricula() != numMatricula){
            i++;
        }

        if (i < funcionarios.size()){
            System.out.println("Funcionario encontrado!");
            funcionarios.get(i).exibirInformacoes();
        } else {
            System.out.println("Funcionario nao encontrado");
        }
    }

    public void menuAlterar(int i){
            System.out.println("----------------------");
            System.out.println("1) Nome: " + funcionarios.get(i).getNome());
            System.out.println("2) Qualificacao: " + funcionarios.get(i).getQualificacao());
            System.out.println("3) Descricao da funcao: " + funcionarios.get(i).getDescricaoFuncao());
            System.out.println("4) Carga horaria semanal: " + funcionarios.get(i).getCargaHorariaSemanal());
            System.out.println("5) Numero de matricula: " + funcionarios.get(i).getNumMatricula());
            System.out.println("0) Cancelar");
            System.out.println("O que deseja alterar?");
        }

    @Override
    public void alterar(){
        int numMatricula, i = 0, opcao;
        System.out.print("Digite o numero de matricula do funcionario para alteração: ");
        numMatricula = Integer.parseInt(input.nextLine());
        while (i < funcionarios.size() && funcionarios.get(i).getNumMatricula() != numMatricula){
            i++;
        }

        if (i < funcionarios.size()){            
            System.out.println("Cliente encontrado.\n");
            do { 
                menuAlterar(i);
                opcao = Integer.parseInt(input.nextLine());
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Digite o novo nome: ");
                        String nome = input.nextLine();
                        funcionarios.get(i).setNome(nome);
                    }
                    case 2 -> {
                        System.out.print("Digite o nova qualificacao: ");
                        String qualificacao = input.nextLine();
                        funcionarios.get(i).setQualificacao(qualificacao);
                    }
                    case 3 -> {
                        System.out.print("Digite a nova descricao da funcao: ");
                        String descricaoFuncao = input.nextLine();
                        funcionarios.get(i).setDescricaoFuncao(descricaoFuncao);
                    }
                    case 4 -> {
                        System.out.print("Digite a nova carga horaria semanal: ");
                        int cargaHorariaSemanal = Integer.parseInt(input.nextLine());
                        funcionarios.get(i).setCargaHorariaSemanal(cargaHorariaSemanal);
                    }
                    case 5 -> {
                        System.out.print("Digite o novo numero de matricula ");
                        numMatricula = Integer.parseInt(input.nextLine());
                        funcionarios.get(i).setNumMatricula(numMatricula);
                    }
                    case 0 -> {
                        System.out.println("Voltando...");
                    }
                    default -> {
                        System.out.println("Comando invalido. Tente novamente");
                    }       
                }      
            } while (opcao < 0 || opcao > 5);
        }
    }
    @Override
    public void remover(){
        
    }

    @Override
    public void relatorio(){
        int i = 1;
        if(funcionarios.size() > 0){
            for(Funcionario funcionario: funcionarios){
            System.out.println("---------------------------");
            System.out.println("\nCliente " + i);
            funcionario.exibirInformacoes();
            i++;
            }
        } else {
            System.out.println("Nenhum cliente cadastrado.");
        }
    }

    public ArrayList<Funcionario> getListaFuncionario(){
        return funcionarios;
    }

    public Funcionario buscaPorNumMatricula(int numMatricula){
        int i = 0;
        boolean achou = false;
        while (i < funcionarios.size() && !achou){
            if(funcionarios.get(i).getNumMatricula() == numMatricula){
                achou = true;
                return funcionarios.get(i);
            }
            i++;
        }
        return null;
    }
}

