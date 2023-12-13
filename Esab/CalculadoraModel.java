import java.sql.Connection;
import java.sql.PreparedStatement;

public class CalculadoraModel {
    private double numero1;
    private double numero2;
    private double resultado;
    private String operacao;

    // Construtor, getters e setters...

    public void setNumerosOperacao(double numero1, double numero2, String operacao) {
        this.numero1 = numero1;
        this.numero2 = numero2;
        this.operacao = operacao;
    }

    public void calcular() {
        switch (operacao) {
            case "+":
                resultado = numero1 + numero2;
                break;
            case "-":
                resultado = numero1 - numero2;
                break;
            case "*":
                resultado = numero1 * numero2;
                break;
            case "/":
                resultado = numero1 / numero2;
                break;
            default:
                System.out.println("Operação inválida");
        }
    }
 
    public int executeInsert(double numero1, double numero2, String operador, double resultado) {
        Conexao conexao = new Conexao();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = conexao.openDataBase();
    String sql = "INSERT INTO calculadora (numero1, numero2, operacao, resultado) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, numero1);
            stmt.setDouble(2, numero2);
            stmt.setString(3, operador);
            stmt.setDouble(4, resultado);
            return stmt.executeUpdate();
        } catch (Exception error) {
            System.out.println("Erro ao executar insert: " + error.getMessage());
        }
        return -1;
    }
    
    public double getResultado() {
        return resultado;
    }
    public String toString() {
        return String.format("%.2f %s %.2f = %.2f", numero1, operacao, numero2, resultado);
    }
}
