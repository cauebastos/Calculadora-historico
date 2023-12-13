import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CalculadoraController {
    private CalculadoraModel model;
    private CalculadoraView view;
    private Conexao conexao;

        public CalculadoraController(Conexao conexao) {
            this.conexao = conexao;
        }
    
        public void salvarNoBanco(double numero1, double numero2, String operacao, double resultado) {
            conexao.executeInsert(numero1, numero2, operacao, resultado);
        }
    
    
    public CalculadoraController(CalculadoraModel model, CalculadoraView view) {
        this.model = model;
        this.view = view;
    }

    public void calcular() {
        double numero1 = view.obterNumero("primeiro");
        double numero2 = view.obterNumero("segundo");
        String operacao = view.obterOperacao();
        
        model.setNumerosOperacao(numero1, numero2, operacao);
        model.calcular();
        model.executeInsert(numero1, numero2, operacao, numero2);
    }
       public void exibirHistoricoDoBanco() {
        Conexao conexao = new Conexao();
        conexao.openDataBase();

        String sql = "SELECT * FROM calculadora";
        try (ResultSet resultSet = conexao.executeQuery(sql)) {
            List<String> historico = new ArrayList<>();

            while (resultSet.next()) {
                double numero1 = resultSet.getDouble("numero1");
                double numero2 = resultSet.getDouble("numero2");
                String operacao = resultSet.getString("operacao");
                double resultado = resultSet.getDouble("resultado");

                String operacaoFormatada = String.format("%.2f %s %.2f = %.2f", numero1, operacao, numero2, resultado);
                historico.add(operacaoFormatada);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeDataBase();
        }
    }
}
