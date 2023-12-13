import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/calculadora";
    private static final String user = "root";
    private static final String senha = "admin";
    private Connection conn;
    private PreparedStatement stmt;

    public Connection openDataBase() {
        try {
            // Se a conexão já estiver aberta, retorna a mesma conexão
            if (this.conn != null && !this.conn.isClosed()) {
                return this.conn;
            }
    
            // Configuração da conexão com o banco de dados
            this.conn = DriverManager.getConnection(url, user, senha);
            return this.conn;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao abrir conexão com o banco de dados", e);
        }
    }
    

    public void closeDataBase() {
        try {
            if (this.stmt != null) {
                this.stmt.close();
            }
            if (this.conn != null) {
                this.conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int executeInsert(double numero1, double numero2, String operador, double resultado) {
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO calculadora (numero1, numero2, operacao, resultado) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, numero1);
            stmt.setDouble(2, numero2);
            stmt.setString(3, operador);
            stmt.setDouble(4, resultado);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // ou outra forma de indicar erro, se necessário
        } finally {
            closeStatement(stmt);
            closeDataBase();
        }
    }

    private void closeStatement(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet executeQuery(String sql) {
    ResultSet resultSet = null;
    try {
        // Criar a instrução SQL
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        // Executar a consulta e obter o conjunto de resultados
        resultSet = preparedStatement.executeQuery();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return resultSet;
}
    

    public boolean isConnected() {
        return false;
    }
}
