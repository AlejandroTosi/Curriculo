import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class RecursoDAO {

    private final Connection conn;

    public RecursoDAO(Connection conn) {
        this.conn = conn;
    }

    // =======================================
    // BUSCAR RECURSO POR ID
    // =======================================
    public Optional<Integer> buscarIdPorNome(String nome) throws SQaLException {
        String sql = "SELECT idrecurso FROM recurso WHERE nome = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(rs.getInt(1));
                }
                return Optional.empty();
            }
        }
    }

    public int criar(String nome) throws SQLException {
        String sql = "INSERT INTO recurso (nome) VALUES (?)";

        try (PreparedStatement ps =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, nome);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    public int obterOuCriarRecurso(String nome) throws SQLException {
        Optional<Integer> existente = buscarIdPorNome(nome);
        if (existente.isPresent()) {
            return existente.get();
        }
        return criar(nome);
    }
}
