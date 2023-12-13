public class TesteConexao {

    public static void main(String[] args) {
        Conexao conexao = new Conexao();

        // Abre a conexão
        conexao.openDataBase();

        // Verifica se a conexão está aberta
        if (conexao.isConnected()) {
            System.out.println("Conexão bem-sucedida!");
        } else {
            System.out.println("Falha na conexão.");
        }

        // Fecha a conexão
        conexao.closeDataBase();
    }
}
