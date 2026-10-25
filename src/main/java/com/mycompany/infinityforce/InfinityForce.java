
package com.mycompany.infinityforce;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InfinityForce extends JFrame {

    private JTextField txtNombre, txtCorreo;
    private DefaultTableModel modeloSocios;
    private JTable tablaSocios;

    public InfinityForce() {
        setTitle("Infinity Force - Gestión del Gimnasio");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.WHITE);
  
        JLabel titulo = new JLabel(
                "INFINITY FORCE - GESTIÓN DE SOCIOS",
                SwingConstants.CENTER
        );
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(20, 70, 130));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        panelPrincipal.add(titulo, BorderLayout.NORTH);

        // Panel de registro de socios
        JPanel panelRegistro = new JPanel(new GridLayout(3, 2, 10, 10));
        panelRegistro.setBorder(BorderFactory.createTitledBorder(
                "Registrar nuevo socio"
        ));

        txtNombre = new JTextField();
        txtCorreo = new JTextField();

        panelRegistro.add(new JLabel("Nombre del socio:"));
        panelRegistro.add(txtNombre);
        panelRegistro.add(new JLabel("Correo electrónico:"));
        panelRegistro.add(txtCorreo);

        JButton btnRegistrar = new JButton("Registrar socio");
        JButton btnLimpiar = new JButton("Limpiar campos");

        panelRegistro.add(btnRegistrar);
        panelRegistro.add(btnLimpiar);

        // Tabla de socios
        modeloSocios = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Correo electrónico"}, 0
        );

        tablaSocios = new JTable(modeloSocios);
        JScrollPane scrollTabla = new JScrollPane(tablaSocios);
        scrollTabla.setBorder(BorderFactory.createTitledBorder(
                "Lista de socios"
        ));

        // Menú de funcionalidades
        JPanel panelFunciones = new JPanel(
                new GridLayout(1, 3, 10, 10)
        );
        panelFunciones.setBorder(BorderFactory.createTitledBorder(
                "Servicios del gimnasio"
        ));

        JButton btnRutinas = new JButton("Rutinas");
        JButton btnNutricion = new JButton("Planes nutricionales");
        JButton btnCatalogo = new JButton("Productos y servicios");

        panelFunciones.add(btnRutinas);
        panelFunciones.add(btnNutricion);
        panelFunciones.add(btnCatalogo);

        JPanel panelCentro = new JPanel(new BorderLayout(10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(
                0, 15, 15, 15
        ));
        panelCentro.add(panelRegistro, BorderLayout.NORTH);
        panelCentro.add(scrollTabla, BorderLayout.CENTER);
        panelCentro.add(panelFunciones, BorderLayout.SOUTH);

        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        add(panelPrincipal);

        // Registrar socio
        btnRegistrar.addActionListener(e -> registrarSocio());

        // Limpiar campos
        btnLimpiar.addActionListener(e -> {
            txtNombre.setText("");
            txtCorreo.setText("");
        });

        // Mostrar rutinas
        btnRutinas.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "RUTINAS DE ENTRENAMIENTO\n\n"
                        + "1. Rutina de fuerza\n"
                        + "2. Rutina de resistencia\n"
                        + "3. Rutina de entrenamiento general",
                        "Rutinas", JOptionPane.INFORMATION_MESSAGE)
        );

        // Mostrar planes nutricionales
        btnNutricion.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "PLANES NUTRICIONALES\n\n"
                        + "1. Plan de alimentación equilibrada\n"
                        + "2. Plan orientado al aumento de masa muscular\n"
                        + "3. Plan orientado al control de peso\n\n"
                        + "Consultar a un profesional para "
                        + "recibir orientación personalizada.",
                        "Nutrición", JOptionPane.INFORMATION_MESSAGE)
        );

        // Mostrar catálogo
        btnCatalogo.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "CATÁLOGO INFINITY FORCE\n\n"
                        + "PRODUCTOS:\n"
                        + "- Proteínas\n"
                        + "- Accesorios deportivos\n\n"
                        + "SERVICIOS:\n"
                        + "- Membresías\n"
                        + "- Entrenamiento personalizado",
                        "Productos y servicios",
                        JOptionPane.INFORMATION_MESSAGE)
        );
    }

    private void registrarSocio() {
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();

        if (nombre.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Complete el nombre y el correo electrónico.",
                    "Campos obligatorios",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!correo.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            JOptionPane.showMessageDialog(this,
                    "Ingrese un correo electrónico válido.",
                    "Correo incorrecto",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = modeloSocios.getRowCount() + 1;

        modeloSocios.addRow(new Object[]{
            id, nombre, correo
        });

        JOptionPane.showMessageDialog(this,
                "Socio registrado correctamente.");

        txtNombre.setText("");
        txtCorreo.setText("");
        txtNombre.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InfinityForce().setVisible(true);
        });
    }
}




