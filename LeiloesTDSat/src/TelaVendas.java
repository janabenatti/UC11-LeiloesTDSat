/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Benatti's
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class TelaVendas extends javax.swing.JFrame {

    public TelaVendas() {
        initComponents();
        carregarVendas(); // Chama o método para carregar os dados ao iniciar a tela
    }

    private void carregarVendas() {
        DefaultTableModel modelo = (DefaultTableModel) jTableVendas.getModel();
        modelo.setRowCount(0);  // Limpar a tabela antes de preencher

        try {
            conectaDAO conexao = new conectaDAO();
            Connection conn = conexao.connectDB();
            String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                // Adiciona as linhas na tabela com os dados
                modelo.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade"),
                    rs.getString("data_venda"),
                    rs.getString("status")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        // Inicialização da interface gráfica
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVendas = new javax.swing.JTable();
        jButtonVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID", "Nome", "Preço", "Quantidade", "Data de Venda", "Status"
            }
        ));
        jScrollPane1.setViewportView(jTableVendas);

        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        // Layout e ajuste da tela
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(jButtonVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonVoltar)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {
        // Código para voltar à tela anterior (pode fechar ou ir para outra tela)
        this.dispose();  // Exemplo: fecha a tela atual
    }

    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableVendas;

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaVendas().setVisible(true);
            }
        });
    }
}

