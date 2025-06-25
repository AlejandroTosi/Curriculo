import java.util.Scanner;
import java.text.DecimalFormat;

public class calculadoradejuros {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.00");

        System.out.println("Qual a operação?");
        System.out.println("1: Cálculo com parâmetros específicos");
        System.out.println("2: Calculadora 1-5%");
        System.out.println("3: Cálculo de tempo necessário");
        String modo = scanner.nextLine();

        // Opção 1
        if (modo.equalsIgnoreCase("1")) {
            String resposta1;
            do {
                System.out.println("Qual o valor inicial?");
                double inicial = scanner.nextDouble();

                System.out.println("Taxa de juros?");
                double t = scanner.nextDouble();

                System.out.println("Período?");
                int p = scanner.nextInt();

                int i = 1; // Período inicial
                System.out.println("    ");
                System.out.println("    ");

                double taxa = 1 + (t / 100);

                while (i <= p) {
                    double taxac = Math.pow(taxa, i);
                    double montante = inicial * taxac;
                    System.out.println("------  " + i + "  -------");
                    System.out.println("|Total: " + df.format(montante));
                    System.out.println("|Lucro: " + df.format((montante - inicial)));
                    System.out.println("Valor estimado seguinte: " + df.format((montante / 100) * t));
                    i++;
                }
                System.out.println("----------------");

                System.out.print("Deseja realizar outra operação? (S/N): ");
                scanner.nextLine(); 
                resposta1 = scanner.nextLine();
            } while (resposta1.equalsIgnoreCase("s"));
        }

        // Opção 2
        if (modo.equalsIgnoreCase("2")) {
            String resposta2;
            do {
                System.out.println("Qual o valor inicial?");
                double inicial = scanner.nextDouble();

                System.out.println("Por quantos períodos?");
                int p = scanner.nextInt();

                int i = 1; // Período inicial
                while (i <= p) {
                    double tx1 = Math.pow(1.01, i);
                    double m1 = inicial * tx1;
                    double tx2 = Math.pow(1.02, i);
                    double m2 = inicial * tx2;
                    double tx3 = Math.pow(1.03, i);
                    double m3 = inicial * tx3;
                    double tx4 = Math.pow(1.04, i);
                    double m4 = inicial * tx4;
                    double tx5 = Math.pow(1.05, i);
                    double m5 = inicial * tx5;

                    System.out.println("------ Mês: " + i + "  ----------------");
                    System.out.println("Total:	1%: " + df.format(m1) + "	2%: " + df.format(m2) + "	3%: " + df.format(m3) + "	4%: " + df.format(m4) + "	5%: " + df.format(m5));
                    i++;
                    System.out.println("----------------");
                }

                System.out.print("Deseja realizar outra operação? (S/N): ");
                scanner.nextLine(); 
                resposta2 = scanner.nextLine();
            } while (resposta2.equalsIgnoreCase("s"));
        }

        // Opção 3 - Cálculo de tempo necessário
        if (modo.equalsIgnoreCase("3")) {
            String resposta3;
            do {
                System.out.println("Qual o valor inicial?");
                double inicial = scanner.nextDouble();

                System.out.println("Qual o valor final desejado?");
                double finalDesejado = scanner.nextDouble();

                System.out.println("Taxa de juros (%) por período?");
                double t = scanner.nextDouble();

                if (inicial <= 0 || finalDesejado <= inicial || t <= 0) {
                    System.out.println("Entrada inválida. O valor final deve ser maior que o inicial e a taxa deve ser positiva.");
                } else {
                    double taxa = 1 + (t / 100);
                    double n = Math.log(finalDesejado / inicial) / Math.log(taxa);

                    System.out.println("Serão necessários aproximadamente " + df.format(n) + " períodos para atingir o valor desejado.");
                    System.out.println("Arredondando para cima: " + Math.ceil(n) + " períodos.");
                }

                System.out.print("Deseja realizar outra operação? (S/N): ");
                scanner.nextLine(); 
                resposta3 = scanner.nextLine();
            } while (resposta3.equalsIgnoreCase("s"));
        }

        scanner.close();
    }
}

