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
    ArrayList<ProdutosDTO> listagemVendidos = new ArrayList<>();
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
            listagemVendidos.add(p); 
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

    return listagemVendidos; 
    
}
    
   public void venderProduto(ProdutosDTO produto) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            // Estabelecendo a conexão com o banco de dados
            conectaDAO conexao = new conectaDAO();
            conn = conexao.connectDB();

            // Comando SQL para atualizar o status do produto
            String sql = "UPDATE produtos SET status = ? WHERE id = ?";
            preparedStatement = conn.prepareStatement(sql);

            // Substituindo os placeholders no SQL pelos valores do produto
            preparedStatement.setString(1, "Vendido");
            preparedStatement.setInt(2, produto.getId());

            // Executando a atualização
            int linhasAfetadas = preparedStatement.executeUpdate();

            // Verificando se o produto foi atualizado com sucesso
            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            // Fechando o PreparedStatement e a conexão
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }       
        }

   }

    ArrayList<ProdutosDTO> listarProdutosVendidos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void venderProduto(int idProduto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean produtoExiste(int idProduto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

