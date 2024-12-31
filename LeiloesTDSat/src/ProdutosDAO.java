/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    public void cadastrarProduto(ProdutosDTO produto) throws SQLException {

        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conectaDAO conexao = new conectaDAO();
            conn = conexao.connectDB();

            String query = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setInt(2, produto.getValor());
            preparedStatement.setString(3, produto.getStatus());

            // Executar a query
            preparedStatement.executeUpdate();
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        //conn = new conectaDAO().connectDB();
    }

    public ArrayList<ProdutosDTO> listarProdutos() throws SQLException {
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    Connection conn = null;
    PreparedStatement preparedStatement = null;

    try {
        conectaDAO conexao = new conectaDAO();
        conn = conexao.connectDB();

        String sql = "SELECT * FROM produtos";
        preparedStatement = conn.prepareStatement(sql);

        ResultSet resposta = preparedStatement.executeQuery();
        while (resposta.next()) {
            ProdutosDTO p = new ProdutosDTO();
            p.setId(resposta.getInt("id"));
            p.setNome(resposta.getString("nome"));
            p.setValor(resposta.getInt("valor")); 
            p.setStatus(resposta.getString("status"));
            listagem.add(p);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    } finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    return listagem; 
}
}
