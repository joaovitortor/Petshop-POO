package Petshop;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        GerenciarSistema controlador = new GerenciarSistema(input);
        controlador.iniciar();
    }
}

