package Petshop;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaFuncionario {
    private ArrayList<Funcionario> funcionarios;
    private Scanner input;

    public SistemaFuncionario(Scanner input){
        this.funcionarios = new ArrayList<>();
        this.input = input;
    }

    public void cadastrarFuncionario(){
        String nome, qualificacao, descricaoFuncao;
        int cargaHorariaSemanal, numMatricula;
        System.out.print("Digite o nome do funcionario: ");
        nome = input.nextLine();
        System.out.print("Digite a qualificacao do funcionario: ");
        qualificacao = input.nextLine();
        System.out.print("Digite a descricao da função do funcionario: ");
        descricaoFuncao = input.nextLine();
        System.out.print("Digite a carga horaria semanal do funcionario: ");
        cargaHorariaSemanal = input.nextInt();
        System.out.print("Digite o numero da matricula do funcionario: ");
        numMatricula = input.nextInt();
        funcionarios.add(new Funcionario(nome, qualificacao, descricaoFuncao, cargaHorariaSemanal, numMatricula));
    }

    public void consultarFuncionario(){
        int numMatricula, i = 0;
        String nome;
        System.out.print("Digite o número de matrícula do funcionario: ");
        numMatricula = Integer.parseInt(input.nextLine());

        while (i < funcionarios.size() && funcionarios.get(i).getNumMatricula() != numMatricula){
            i++;
        }

        if (i < funcionarios.size()){
            System.out.println("Cliente encontrado!");
            System.out.println(funcionarios.get(i).exibirInformacoes());
        } else {
            System.out.println("Cliente nao encontrado");
        }
    }

    public void alterarFuncionario(){

    }
}
