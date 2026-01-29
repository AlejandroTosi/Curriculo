package pacote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProducaoDAO {

    private final Connection conn;

    public ProducaoDAO(Connection conn) {
        this.conn = conn;
    }

    // =============================
    // LISTAR PRODUÇÃO ATUAL (VIEW)
    // =============================
    public void listarSaidaAtual() throws SQLException {
        String sql = """
            SELECT produto, producao_por_tempo
            FROM saida_atual
            ORDER BY produto
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n=== Produção Atual ===");

            while (rs.next()) {
                String produto = rs.getString("produto");
                double producao = rs.getDouble("producao_por_tempo");

                System.out.printf("Produto: %-30s Produção/t: %.3f\n",
                        produto, producao);
            }
        }
    }
}
