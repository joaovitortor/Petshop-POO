package Petshop;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaFuncionario implements Crud{
    private ArrayList<Funcionario> funcionarios;
    private SistemaAtendimento sistemaAtendimento;
    private Scanner input;

    public SistemaFuncionario(Scanner input){
        this.funcionarios = new ArrayList<>();
        this.input = input;
    }


    public void setSistemaAtendimento(SistemaAtendimento sistemaAtendimento){
        this.sistemaAtendimento = sistemaAtendimento;
    }

    public static void menuFuncionario() {
    	System.out.println("\n+--------------------------+");
    	System.out.println("|        Funcionário       |");
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
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opcao invalida. Tente novamente!");
    		}
    	} while (opcao != 0);
    }

    @Override
    public void cadastrar(){
        String nome, qualificacao, descricaoFuncao;
        int cargaHorariaSemanal, numMatricula;
        System.out.println("\n---- Cadastro de funcionario ----");
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
        System.out.println("\nCadastro realizado com sucesso!");
    }

    @Override
    public void consultar(){
        int numMatricula, i = 0;
        System.out.println("\n---- Consulta de funcionario ----");
        System.out.print("Digite o numero de matricula do funcionario: ");
        numMatricula = Integer.parseInt(input.nextLine());

        while (i < funcionarios.size() && funcionarios.get(i).getNumMatricula() != numMatricula){
            i++;
        }

        if (i < funcionarios.size()){
            System.out.println("\nFuncionario encontrado!");
            funcionarios.get(i).exibirInformacoes();
        } else {
            System.out.println("\nFuncionario nao encontrado.");
        }
    }

    public void menuAlterar(int i){
            System.out.println("1) Nome: " + funcionarios.get(i).getNome());
            System.out.println("2) Qualificacao: " + funcionarios.get(i).getQualificacao());
            System.out.println("3) Descricao da funcao: " + funcionarios.get(i).getDescricaoFuncao());
            System.out.println("4) Carga horaria semanal: " + funcionarios.get(i).getCargaHorariaSemanal());
            System.out.println("5) Numero de matricula: " + funcionarios.get(i).getNumMatricula());
            System.out.println("0) Cancelar");
            System.out.print("Digite o numero correspondente a alteracao desejada: ");
        }

    @Override
    public void alterar(){
        int opcao;
        int i;
        System.out.println("\n---- Alteracao de funcionario -----");
        i = verificaFuncionario();
        if (i > -1){            
            System.out.println("Funcionario encontrado.\n");
            do { 
                menuAlterar(i);
                opcao = Integer.parseInt(input.nextLine());
                switch (opcao) {
                    case 1 -> {
                        System.out.print("\nDigite o novo nome: ");
                        String nome = input.nextLine();
                        funcionarios.get(i).setNome(nome);
                        System.out.println("Nome atualizado com sucesso!");
                    }
                    case 2 -> {
                        System.out.print("\nDigite o nova qualificacao: ");
                        String qualificacao = input.nextLine();
                        funcionarios.get(i).setQualificacao(qualificacao);
                        System.out.println("Qualificacao alterada com sucesso!");
                    }
                    case 3 -> {
                        System.out.print("\nDigite a nova descricao da funcao: ");
                        String descricaoFuncao = input.nextLine();
                        funcionarios.get(i).setDescricaoFuncao(descricaoFuncao);
                        System.out.println("Descricao alterada com sucesso!");
                    }
                    case 4 -> {
                        System.out.print("\nDigite a nova carga horaria semanal: ");
                        int cargaHorariaSemanal = Integer.parseInt(input.nextLine());
                        funcionarios.get(i).setCargaHorariaSemanal(cargaHorariaSemanal);
                        System.out.println("Carga horaria alterada com sucesso!");
                    }
                    case 5 -> {
                        System.out.print("\nDigite o novo numero de matricula ");
                        int numMatricula = Integer.parseInt(input.nextLine());
                        funcionarios.get(i).setNumMatricula(numMatricula);
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
        }else{
            System.out.println("Funcionario não encontrado.\n");
        }
    }

    public int verificaFuncionario(){
        int i = 0;
        boolean achou = false;
        System.out.print("Digite o numero de matricula do funcionario: ");
        int numMatricula = Integer.parseInt(input.nextLine());
        while(i < funcionarios.size() && !achou){
            if(funcionarios.get(i).getNumMatricula() == numMatricula){
                achou = true;
                return i;
            }
            i++;
        }
        return -1;
    }
    
    @Override
    public void remover(){
        int i, id;
        boolean inclusoEmAtendimento;
        System.out.println("\n---- Remocao de funcionario ----");
        i = verificaFuncionario();
        if(i > -1){
            id = funcionarios.get(i).getNumMatricula();
            inclusoEmAtendimento = sistemaAtendimento.verificaFuncionario(id);
            if(!inclusoEmAtendimento){
                funcionarios.remove(i);
                System.out.println("\nO funcionário foi removido!");
            } else {
                System.out.println("\nEsse funcionário está cadastrado em pelo menos um atendimento. Não é possível remover.");
            }
        }
    }

    @Override
    public void relatorio(){
        System.out.println("\n---- Relatorio de funcionarios ----\n");
        if(funcionarios.size() > 0){
            for(Funcionario funcionario: funcionarios){
            funcionario.exibirInformacoes();
            System.out.println("");
            }
        } else {
            System.out.println("Nenhum funcionario cadastrado.\n");
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

