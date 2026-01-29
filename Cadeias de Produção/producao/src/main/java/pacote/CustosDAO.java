import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustosDAO {

    public void inserirCusto(Connection conn, int idReceita, int idRecurso, double quantidade) throws SQLException {
        String sql = """
            INSERT INTO custos (idreceita, idrecurso, quantidade)
            VALUES (?, ?, ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idReceita);
            ps.setInt(2, idRecurso);
            ps.setDouble(3, quantidade);
            ps.executeUpdate();
        }
    }
}
