import java.sql.*;
import java.util.Scanner;

public class producao {

    private static final String DB_URL = "jdbc:sqlite:producao.db";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Scanner sc = new Scanner(System.in)) {

            if (conn == null) {
                System.out.println("Arquivo .db não encontrado");
                return;
            }

            while (true) {
                System.out.println("\n=== Menu Principal ===");
                System.out.println("1 - Listar receitas");
                System.out.println("2 - Listar produtos por recurso necessário");
                System.out.println("3 - Listar produções em andamento");
                System.out.println("4 - Listar pesquisas");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1 -> listarReceitas(conn);
                    case 2 -> pesquisarReceita(conn, sc);
                    case 3 -> listarConstrutoras(conn);
                    case 4 -> listarPesquisas(conn);
                    case 0 -> {
                        System.out.println("Saindo...");
                        return;
                    }
                    default -> System.out.println("Opção inválida!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void listarReceitas(Connection conn) throws SQLException {
        String sql = "SELECT * FROM view_receitas";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- Receitas ---");
            while (rs.next()) {
                System.out.printf("ID: %d | Receita: %s | %s x%.2f | %s x%.2f | %s x%.2f | %s x%.2f | Tempo: %.2f\n",
                        rs.getInt("id"),
                        rs.getString("receita"),
                        rs.getString("ingrediente1"), rs.getDouble("quantidade1"),
                        rs.getString("ingrediente2"), rs.getDouble("quantidade2"),
                        rs.getString("ingrediente3"), rs.getDouble("quantidade3"),
                        rs.getString("ingrediente4"), rs.getDouble("quantidade4"),
                        rs.getDouble("tempo"));
            }
        }
    }

    private static void pesquisarReceita(Connection conn, Scanner sc) throws SQLException {
    System.out.print("Digite o nome do recurso (ingrediente) para pesquisar: ");
    String pesquisaNome = sc.nextLine();

    String sql = "SELECT * FROM view_receitas WHERE " +
            "ingrediente1 = ? OR ingrediente2 = ? OR ingrediente3 = ? OR ingrediente4 = ?";

    try (PreparedStatement pst = conn.prepareStatement(sql)) {
        for (int i = 1; i <= 4; i++) {
            pst.setString(i, pesquisaNome);
        }

        try (ResultSet rs = pst.executeQuery()) {
            System.out.println("\n--- Receitas que usam esse recurso ---");
            boolean encontrou = false;
            while (rs.next()) {
                encontrou = true;
                System.out.printf("ID: %d | Receita: %s | %s x%.2f | %s x%.2f | %s x%.2f | %s x%.2f | Tempo: %.2f\n",
                        rs.getInt("id"),
                        rs.getString("receita"),
                        rs.getString("ingrediente1"), rs.getDouble("quantidade1"),
                        rs.getString("ingrediente2"), rs.getDouble("quantidade2"),
                        rs.getString("ingrediente3"), rs.getDouble("quantidade3"),
                        rs.getString("ingrediente4"), rs.getDouble("quantidade4"),
                        rs.getDouble("tempo"));
            }

            if (!encontrou) {
                System.out.println("Nenhuma receita encontrada com esse recurso.");
                }
            }
        }
    }

    private static void listarConstrutoras(Connection conn) throws SQLException {
        String sql = "SELECT * FROM view_construtoras";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- Construtoras ---");
            while (rs.next()) {
                System.out.printf("ID: %d | Quantidade: %d | Produz: %s\n",
                        rs.getInt("id"),
                        rs.getInt("quantidade"),
                        rs.getString("produzindo"));
            }
        }
    }

    private static void listarPesquisas(Connection conn) throws SQLException {
        String sql = "SELECT * FROM view_pesquisas";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- Pesquisas ---");
            while (rs.next()) {
                System.out.printf("ID: %d | Nome: %s | Desbloqueia: %s | Vermelha: %d | Verde: %d | Azul: %d | Amarela: %d | Roxa: %d | Cinza: %d\n",
                        rs.getInt("id"),
                        rs.getString("pesquisa"),
                        rs.getString("desbloqueia"),
                        rs.getInt("pesquisa_vermelha"),
                        rs.getInt("pesquisa_verde"),
                        rs.getInt("pesquisa_azul"),
                        rs.getInt("pesquisa_amarela"),
                        rs.getInt("pesquisa_roxa"),
                        rs.getInt("pesquisa_cinza"));
            }
        }
    }
}
