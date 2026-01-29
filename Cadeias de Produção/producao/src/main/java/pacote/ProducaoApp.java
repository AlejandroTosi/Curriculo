import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ProducaoApp {

    private static final String DB_URL = "jdbc:sqlite:producao.db";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Scanner sc = new Scanner(System.in)) {

            ProducaoService service = new ProducaoService(conn);

            while (true) {
                System.out.println("\n=== Menu Principal ===");
                System.out.println("1 - Listar receitas");
                System.out.println("2 - Listar receitas que usam insumo");
                System.out.println("3 - Listar produção atual");
                System.out.println("4 - Adicionar/Alterar produto");
                System.out.println("0 - Sair");

                int opcao = lerOpcao(sc);

                switch (opcao) {
                    case 1 -> service.listarReceitas();
                    case 2 -> {
                        System.out.print("Nome do insumo: ");
                        String insumo = sc.nextLine();
                        service.listarReceitasPorInsumo(insumo);
                    }
                    case 3 -> service.listarProducaoAtual();
                    case 4 -> {
                        System.out.print("Nome do produto: ");
                        String nome = sc.nextLine();
                        service.adicionarOuAlterarProduto(nome);
                    }
                    case 0 -> {
                        return;
                    }

                    default -> System.out.println("Opção inválida!");
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro de banco: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static int lerOpcao(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Digite um número válido: ");
            }
        }
    }
}
