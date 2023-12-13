import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalculadoraApp {
    public static void main(String[] args) throws SQLException {
        List<String> historico = new ArrayList<>();
        CalculadoraModel model = new CalculadoraModel();
        CalculadoraView view = new CalculadoraView();
        CalculadoraController controller = new CalculadoraController(model, view);
    
        boolean sair = false;
        Scanner scanner = new Scanner(System.in);
        while (!sair) {
            System.out.print("Deseja fazer outra operação? (S para sim, N para não, H para histórico): ");
            String escolha = scanner.nextLine().toUpperCase();
    
            switch (escolha) {
                case "N":
                    sair = true;
                    break;
                case "H":
                    view.exibirHistorico();
                    break;
                default:
                    controller.calcular();
                    historico.add(model.toString());
                    view.mostrarResultado(model.getResultado());
                    break;
            }
        }
 
        scanner.close();
    }  
}
