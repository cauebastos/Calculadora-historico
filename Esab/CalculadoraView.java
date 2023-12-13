import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CalculadoraView {
    public void mostrarResultado(double resultado) {
        System.out.println("Resultado: " + resultado);
    }

    public String obterOperacao() {
        System.out.print("Digite a operação (+, -, *, /): ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public double obterNumero(String numero) {
        System.out.print("Digite o " + numero + " número: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    public void exibirHistorico() throws SQLException {
        Conexao conexao = new Conexao();
        conexao.openDataBase();
   
        String sql = "SELECT * FROM calculadora";
        try (ResultSet resultSet = conexao.executeQuery(sql)) {
            System.out.println("\nHistórico de Operações:");
            while (resultSet.next()) {
                double numero1 = resultSet.getDouble("numero1");
                double numero2 = resultSet.getDouble("numero2");
                String operacao = resultSet.getString("operacao");
                double resultado = resultSet.getDouble("resultado");
                String operacaoFormatada = String.format("%.2f %s %.2f = %.2f", numero1, operacao, numero2, resultado);
                System.out.println(operacaoFormatada);
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}