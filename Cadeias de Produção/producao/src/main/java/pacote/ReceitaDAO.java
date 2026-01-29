import java.sql.*;
import java.util.Optional;

public class ReceitaDAO {

    public Optional<Integer> buscarIdReceitaPorProduto(Connection conn, String nomeProduto) throws SQLException {
        String sql = """
            SELECT r.idreceita
            FROM receita r
            JOIN recurso rec ON rec.idrecurso = r.saida
            WHERE rec.nome = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nomeProduto);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(rs.getInt("idreceita"));
                }
                return Optional.empty();
            }
        }
    }
}
