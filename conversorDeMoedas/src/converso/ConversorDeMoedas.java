package converso;

import calculadora.CalculadoraConversao;
import java.util.Scanner;

public class ConversorDeMoedas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nCONVERSOR DE MOEDAS:");
            System.out.println("1. Dólar - Real");
            System.out.println("2. Real - Dólar");
            System.out.println("3. Real - Peso Argentino");
            System.out.println("4. Peso Argentino - Real");
            System.out.println("5. Euro - Real");
            System.out.println("6. Real - Euro");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            String fromCurrency = "";
            String toCurrency = "";

            switch (opcao) {
                case 1: fromCurrency = "USD"; toCurrency = "BRL"; break;
                case 2: fromCurrency = "BRL"; toCurrency = "USD"; break;
                case 3: fromCurrency = "BRL"; toCurrency = "ARS"; break;
                case 4: fromCurrency = "ARS"; toCurrency = "BRL"; break;
                case 5: fromCurrency = "EUR"; toCurrency = "BRL"; break;
                case 6: fromCurrency = "BRL"; toCurrency = "EUR"; break;
                case 7:
                    System.out.println("Até a proxima.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            if (opcao >= 1 && opcao <= 6) {
                System.out.print("Digite o valor a ser convertido: ");
                double valor = scanner.nextDouble();
                CalculadoraConversao.converterMoeda(fromCurrency, toCurrency, valor);

            }

        } while (opcao != 7);

        scanner.close();
    }
}