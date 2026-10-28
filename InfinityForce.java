
package com.mycompany.infinityforce;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class InfinityForce extends JFrame {

    private final Color fondo = new Color(13, 18, 24);
    private final Color lateral = new Color(9, 14, 19);
    private final Color tarjeta = new Color(24, 33, 43);
    private final Color tarjeta2 = new Color(32, 44, 56);
    private final Color verde = new Color(181, 255, 48);
    private final Color blanco = new Color(242, 246, 250);
    private final Color gris = new Color(160, 174, 189);
    private final Color borde = new Color(48, 64, 79);

    private final JPanel contenido = new JPanel(new BorderLayout());
    private final Map<String, Modulo> modulos = new LinkedHashMap<>();

    private final String[] nombres = {
        "Socios", "Membresías", "Asistencia", "Rutinas",
        "Nutrición", "Inventario", "Pagos", "Progreso físico"
    };

    public InfinityForce() {
        setTitle("INFINITY FORCE | Fitness Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1350, 820);
        setMinimumSize(new Dimension(1050, 680));
        setLocationRelativeTo(null);

        // Crear los ocho módulos antes de mostrar el inicio.
        for (String nombre : nombres) {
            modulos.put(nombre, crearModulo(nombre));
        }

        getContentPane().setBackground(fondo);
        setLayout(new BorderLayout());
        add(crearMenu(), BorderLayout.WEST);
        add(crearAreaPrincipal(), BorderLayout.CENTER);

        mostrarInicio();
    }

    // ======================== MENU LATERAL ========================

    private JPanel crearMenu() {
        JPanel menu = new JPanel(new BorderLayout());
        menu.setPreferredSize(new Dimension(225, 0));
        menu.setBackground(lateral);
        menu.setBorder(BorderFactory.createMatteBorder(
                0, 0, 0, 1, borde));

        JPanel marca = new JPanel(new GridLayout(2, 1, 0, 6));
        marca.setBackground(lateral);
        marca.setBorder(new EmptyBorder(25, 18, 25, 10));

        JLabel logo = new JLabel("✚ INFINITY FORCE");
        logo.setFont(new Font("Arial", Font.BOLD, 21));
        logo.setForeground(verde);

        JLabel subtitulo = new JLabel("FITNESS MANAGEMENT");
        subtitulo.setFont(new Font("Arial", Font.BOLD, 10));
        subtitulo.setForeground(gris);

        marca.add(logo);
        marca.add(subtitulo);
        menu.add(marca, BorderLayout.NORTH);

        JPanel botones = new JPanel();
        botones.setLayout(new BoxLayout(botones, BoxLayout.Y_AXIS));
        botones.setBackground(lateral);
        botones.setBorder(new EmptyBorder(5, 10, 10, 10));

        agregarMenu(botones, "⌂", "Inicio", this::mostrarInicio);

        String[] iconos = {
            "♟", "▣", "▦", "✚", "●", "▤", "$", "▥"
        };

        for (int i = 0; i < nombres.length; i++) {
            final String nombre = nombres[i];
            agregarMenu(botones, iconos[i], nombre,
                    () -> mostrarModulo(nombre));
        }

        menu.add(botones, BorderLayout.CENTER);

        JPanel pie = new JPanel(new GridLayout(2, 1, 0, 6));
        pie.setBackground(lateral);
        pie.setBorder(new EmptyBorder(20, 16, 24, 10));

        JLabel frase = new JLabel("ENTRENA. SUPERA. REPITE.");
        frase.setForeground(verde);
        frase.setFont(new Font("Arial", Font.BOLD, 11));

        JLabel version = new JLabel("Versión académica 1.0");
        version.setForeground(gris);
        version.setFont(new Font("Arial", Font.PLAIN, 11));

        pie.add(frase);
        pie.add(version);
        menu.add(pie, BorderLayout.SOUTH);

        return menu;
    }

    private void agregarMenu(JPanel panel, String icono,
                             String texto, Runnable accion) {
        JButton boton = new JButton(icono + "    " + texto);
        boton.setAlignmentX(Component.LEFT_ALIGNMENT);
        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        boton.setPreferredSize(new Dimension(200, 44));
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        boton.setBackground(lateral);
        boton.setForeground(blanco);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        boton.setBorder(new EmptyBorder(0, 12, 0, 5));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        boton.addActionListener(e -> accion.run());

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                boton.setBackground(tarjeta2);
                boton.setForeground(verde);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                boton.setBackground(lateral);
                boton.setForeground(blanco);
            }
        });

        panel.add(boton);
        panel.add(Box.createVerticalStrut(5));
    }

    // ======================== AREA PRINCIPAL ========================

    private JPanel crearAreaPrincipal() {
        JPanel principal = new JPanel(new BorderLayout());
        principal.setBackground(fondo);

        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(fondo);
        encabezado.setBorder(new EmptyBorder(20, 25, 18, 25));

        JLabel titulo = new JLabel("TU GIMNASIO, TU PROGRESO");
        titulo.setForeground(blanco);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel estado = new JLabel("● SISTEMA ACTIVO");
        estado.setForeground(verde);
        estado.setFont(new Font("Arial", Font.BOLD, 12));

        encabezado.add(titulo, BorderLayout.WEST);
        encabezado.add(estado, BorderLayout.EAST);

        principal.add(encabezado, BorderLayout.NORTH);

        contenido.setBackground(fondo);
        contenido.setBorder(new EmptyBorder(0, 22, 20, 22));
        principal.add(contenido, BorderLayout.CENTER);

        return principal;
    }

    // ======================== PANEL DE INICIO ========================

    private void mostrarInicio() {
        contenido.removeAll();

        JPanel inicio = new JPanel(new BorderLayout(0, 15));
        inicio.setBackground(fondo);

        JPanel bienvenida = new JPanel(new BorderLayout(10, 5));
        bienvenida.setBackground(tarjeta);
        bienvenida.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borde),
                new EmptyBorder(20, 22, 20, 22)));

        JPanel textos = new JPanel(new GridLayout(2, 1, 0, 8));
        textos.setBackground(tarjeta);

        JLabel titulo = new JLabel("¡BIENVENIDO A INFINITY FORCE!");
        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        titulo.setForeground(verde);

        JLabel descripcion = new JLabel(
                "Gestiona socios, entrenamiento y bienestar desde un solo lugar.");
        descripcion.setFont(new Font("Arial", Font.PLAIN, 13));
        descripcion.setForeground(gris);

        textos.add(titulo);
        textos.add(descripcion);

        JLabel emoji = new JLabel("🏋️");
        emoji.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 45));

        bienvenida.add(textos, BorderLayout.CENTER);
        bienvenida.add(emoji, BorderLayout.EAST);
        inicio.add(bienvenida, BorderLayout.NORTH);

        JPanel estadisticas = new JPanel(new GridLayout(1, 4, 12, 12));
        estadisticas.setBackground(fondo);

        estadisticas.add(crearTarjeta("♟", "SOCIOS", "Socios"));
        estadisticas.add(crearTarjeta("▣", "MEMBRESÍAS", "Membresías"));
        estadisticas.add(crearTarjeta("▦", "ASISTENCIAS", "Asistencia"));
        estadisticas.add(crearTarjeta("▤", "PRODUCTOS", "Inventario"));

        GraficoRegistros grafico = new GraficoRegistros();

        JPanel zonaGrafico = new JPanel(new BorderLayout());
        zonaGrafico.setBackground(tarjeta);
        zonaGrafico.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borde),
                new EmptyBorder(14, 16, 12, 16)));

        JLabel tituloGrafico = new JLabel("RESUMEN DE REGISTROS POR MÓDULO");
        tituloGrafico.setFont(new Font("Arial", Font.BOLD, 14));
        tituloGrafico.setForeground(verde);
        zonaGrafico.add(tituloGrafico, BorderLayout.NORTH);
        zonaGrafico.add(grafico, BorderLayout.CENTER);

        JPanel centro = new JPanel(new BorderLayout(0, 14));
        centro.setBackground(fondo);
        centro.add(estadisticas, BorderLayout.NORTH);
        centro.add(zonaGrafico, BorderLayout.CENTER);

        inicio.add(centro, BorderLayout.CENTER);

        JLabel pie = new JLabel(
                "⚡ DISCIPLINA  •  ESFUERZO  •  PROGRESO");
        pie.setForeground(verde);
        pie.setFont(new Font("Arial", Font.BOLD, 13));
        pie.setBorder(new EmptyBorder(8, 2, 0, 0));
        inicio.add(pie, BorderLayout.SOUTH);

        contenido.add(inicio, BorderLayout.CENTER);
        contenido.revalidate();
        contenido.repaint();
    }

    private JPanel crearTarjeta(String icono, String titulo,
                                String nombreModulo) {
        JPanel tarjetaPanel = new JPanel(new BorderLayout(8, 10));
        tarjetaPanel.setBackground(tarjeta);
        tarjetaPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borde),
                new EmptyBorder(14, 14, 14, 14)));

        JLabel icon = new JLabel(icono);
        icon.setFont(new Font("Arial", Font.BOLD, 26));
        icon.setForeground(verde);

        JLabel label = new JLabel(titulo);
        label.setFont(new Font("Arial", Font.BOLD, 11));
        label.setForeground(gris);

        JLabel numero = new JLabel(String.valueOf(
                modulos.get(nombreModulo).modelo.getRowCount()));
        numero.setFont(new Font("Arial", Font.BOLD, 29));
        numero.setForeground(verde);

        JPanel texto = new JPanel(new GridLayout(2, 1, 0, 6));
        texto.setBackground(tarjeta);
        texto.add(label);
        texto.add(numero);

        tarjetaPanel.add(icon, BorderLayout.WEST);
        tarjetaPanel.add(texto, BorderLayout.CENTER);

        return tarjetaPanel;
    }

    // ======================== CREAR MODULOS ========================

    private Modulo crearModulo(String nombre) {
        String[] columnas;
        String[] etiquetas;
        String[] claves;

        switch (nombre) {
            case "Socios":
                columnas = new String[]{"ID", "Nombre", "Correo",
                    "Teléfono", "Fecha inscripción"};
                etiquetas = new String[]{"Nombre", "Correo", "Teléfono",
                    "Fecha (AAAA-MM-DD)"};
                claves = new String[]{"nombre", "correo", "telefono", "fecha"};
                break;

            case "Membresías":
                columnas = new String[]{"ID", "ID socio", "Tipo", "Precio",
                    "Inicio", "Vencimiento", "Estado"};
                etiquetas = new String[]{"ID socio", "Tipo", "Precio",
                    "Inicio (AAAA-MM-DD)", "Vencimiento (AAAA-MM-DD)"};
                claves = new String[]{"socio", "tipo", "precio",
                    "inicio", "vencimiento"};
                break;

            case "Asistencia":
                columnas = new String[]{"ID", "ID socio", "Fecha y hora"};
                etiquetas = new String[]{"ID socio",
                    "Fecha/hora (AAAA-MM-DDTHH:MM)"};
                claves = new String[]{"socio", "fecha"};
                break;

            case "Rutinas":
                columnas = new String[]{"ID", "ID socio", "Rutina",
                    "Objetivo", "Ejercicios"};
                etiquetas = new String[]{"ID socio", "Nombre rutina",
                    "Objetivo", "Ejercicios separados por coma"};
                claves = new String[]{"socio", "nombre",
                    "objetivo", "ejercicios"};
                break;

            case "Nutrición":
                columnas = new String[]{"ID", "ID socio", "Plan",
                    "Objetivo", "Observaciones"};
                etiquetas = new String[]{"ID socio", "Nombre del plan",
                    "Objetivo", "Observaciones"};
                claves = new String[]{"socio", "nombre",
                    "objetivo", "observaciones"};
                break;

            case "Inventario":
                columnas = new String[]{"ID", "Producto", "Categoría",
                    "Precio", "Stock"};
                etiquetas = new String[]{"Producto", "Categoría",
                    "Precio", "Stock"};
                claves = new String[]{"nombre", "categoria", "precio", "stock"};
                break;

            case "Pagos":
                columnas = new String[]{"ID", "ID socio", "Monto",
                    "Concepto", "Fecha", "Método"};
                etiquetas = new String[]{"ID socio", "Monto", "Concepto",
                    "Fecha (AAAA-MM-DD)", "Método de pago"};
                claves = new String[]{"socio", "monto",
                    "concepto", "fecha", "metodo"};
                break;

            default:
                columnas = new String[]{"ID", "ID socio", "Fecha",
                    "Peso kg", "Cintura cm", "Observaciones"};
                etiquetas = new String[]{"ID socio", "Fecha (AAAA-MM-DD)",
                    "Peso kg", "Cintura cm", "Observaciones"};
                claves = new String[]{"socio", "fecha", "peso",
                    "cintura", "observaciones"};
                break;
        }

        JPanel panel = new JPanel(new BorderLayout(10, 12));
        panel.setBackground(fondo);
        panel.setBorder(new EmptyBorder(5, 2, 2, 2));

        JLabel titulo = new JLabel(iconoModulo(nombre) + "  "
                + nombre.toUpperCase());
        titulo.setForeground(blanco);
        titulo.setFont(new Font("Arial", Font.BOLD, 23));

        JLabel descripcion = new JLabel(
                "Administra, busca y consulta los registros de "
                + nombre.toLowerCase() + ".");
        descripcion.setForeground(gris);
        descripcion.setFont(new Font("Arial", Font.PLAIN, 12));

        JPanel cabecera = new JPanel(new GridLayout(2, 1, 0, 5));
        cabecera.setBackground(fondo);
        cabecera.add(titulo);
        cabecera.add(descripcion);
        panel.add(cabecera, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(tarjeta);
        formulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borde),
                new EmptyBorder(10, 12, 10, 12)));

        Map<String, JTextField> campos = new LinkedHashMap<>();
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 6, 5, 6);
        g.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < etiquetas.length; i++) {
            int fila = i / 2;
            int columna = (i % 2) * 2;

            JLabel etiqueta = new JLabel(etiquetas[i]);
            etiqueta.setForeground(gris);
            etiqueta.setFont(new Font("Arial", Font.BOLD, 11));

            g.gridx = columna;
            g.gridy = fila;
            g.weightx = 0;
            formulario.add(etiqueta, g);

            JTextField campo = new JTextField(13);
            campo.setBackground(tarjeta2);
            campo.setForeground(blanco);
            campo.setCaretColor(verde);
            campo.setFont(new Font("Arial", Font.PLAIN, 13));
            campo.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(borde),
                    new EmptyBorder(7, 8, 7, 8)));

            campos.put(claves[i], campo);
            g.gridx = columna + 1;
            g.weightx = 1;
            formulario.add(campo, g);
        }

        JButton registrar = new JButton("＋ Registrar");
        JButton eliminar = new JButton("Eliminar seleccionado");
        JButton limpiar = new JButton("Limpiar");
        JButton detalle = new JButton("Ver detalle");

        estiloBoton(registrar, true);
        estiloBoton(eliminar, false);
        estiloBoton(limpiar, false);
        estiloBoton(detalle, false);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT, 7, 4));
        botones.setBackground(tarjeta);
        botones.add(registrar);
        botones.add(eliminar);
        botones.add(limpiar);
        botones.add(detalle);

        g.gridx = 0;
        g.gridy = (etiquetas.length + 1) / 2;
        g.gridwidth = 4;
        g.weightx = 1;
        formulario.add(botones, g);

        // Buscador y filtro por columna.
        JTextField buscar = new JTextField(17);
        buscar.setBackground(tarjeta2);
        buscar.setForeground(blanco);
        buscar.setCaretColor(verde);
        buscar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borde),
                new EmptyBorder(7, 8, 7, 8)));

        JComboBox<String> filtroColumna = new JComboBox<>();
        filtroColumna.addItem("Todas las columnas");
        for (String columna : columnas) {
            filtroColumna.addItem(columna);
        }
        filtroColumna.setBackground(tarjeta2);
        filtroColumna.setForeground(blanco);

        JLabel buscarLabel = new JLabel("⌕ Buscar:");
        buscarLabel.setForeground(verde);
        buscarLabel.setFont(new Font("Arial", Font.BOLD, 12));

        JPanel busqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 7));
        busqueda.setBackground(tarjeta);
        busqueda.add(buscarLabel);
        busqueda.add(buscar);
        busqueda.add(new JLabel("Filtrar por:") {{
            setForeground(gris);
        }});
        busqueda.add(filtroColumna);

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(30);
        tabla.setFont(new Font("Arial", Font.PLAIN, 12));
        tabla.setBackground(tarjeta);
        tabla.setForeground(blanco);
        tabla.setGridColor(borde);
        tabla.setSelectionBackground(new Color(62, 87, 40));
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setShowVerticalLines(false);
        tabla.setFillsViewportHeight(true);
        tabla.setAutoCreateRowSorter(true);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tabla.getTableHeader().setBackground(tarjeta2);
        tabla.getTableHeader().setForeground(verde);
        tabla.getTableHeader().setPreferredSize(new Dimension(0, 35));
        tabla.getTableHeader().setReorderingAllowed(false);

        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable t, Object valor, boolean seleccionado,
                    boolean foco, int fila, int columna) {

                Component c = super.getTableCellRendererComponent(
                        t, valor, seleccionado, foco, fila, columna);

                setBorder(new EmptyBorder(0, 8, 0, 8));

                if (seleccionado) {
                    c.setBackground(new Color(62, 87, 40));
                    c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(fila % 2 == 0
                            ? tarjeta : new Color(27, 37, 48));
                    c.setForeground(blanco);
                }
                return c;
            }
        });

        TableRowSorter<DefaultTableModel> sorter =
                new TableRowSorter<>(modelo);
        tabla.setRowSorter(sorter);

        Runnable aplicarFiltro = () -> {
            String texto = buscar.getText().trim();
            int indice = filtroColumna.getSelectedIndex() - 1;

            if (texto.isEmpty()) {
                sorter.setRowFilter(null);
            } else {
                try {
                    if (indice < 0) {
                        sorter.setRowFilter(RowFilter.regexFilter(
                                "(?i)" + Pattern.quote(texto)));
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter(
                                "(?i)" + Pattern.quote(texto), indice));
                    }
                } catch (java.util.regex.PatternSyntaxException ex) {
                    sorter.setRowFilter(null);
                }
            }
        };

        buscar.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                aplicarFiltro.run();
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                aplicarFiltro.run();
            }
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                aplicarFiltro.run();
            }
        });

        filtroColumna.addActionListener(e -> aplicarFiltro.run());

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(borde));
        scroll.getViewport().setBackground(tarjeta);

        JPanel centro = new JPanel(new BorderLayout(0, 8));
        centro.setBackground(fondo);
        centro.add(formulario, BorderLayout.NORTH);

        JPanel zonaTabla = new JPanel(new BorderLayout(0, 6));
        zonaTabla.setBackground(fondo);
        zonaTabla.add(busqueda, BorderLayout.NORTH);

        if (nombre.equals("Progreso físico")) {
            GraficoPeso graficoPeso = new GraficoPeso(modelo);
            JSplitPane division = new JSplitPane(
                    JSplitPane.VERTICAL_SPLIT, scroll, graficoPeso);
            division.setResizeWeight(0.65);
            division.setDividerSize(7);
            division.setBorder(null);
            division.setBackground(fondo);
            zonaTabla.add(division, BorderLayout.CENTER);
        } else {
            zonaTabla.add(scroll, BorderLayout.CENTER);
        }

        centro.add(zonaTabla, BorderLayout.CENTER);
        panel.add(centro, BorderLayout.CENTER);

        Modulo modulo = new Modulo(nombre, panel, modelo, tabla, campos);

        registrar.addActionListener(e -> registrar(modulo));
        eliminar.addActionListener(e -> eliminar(modulo));
        limpiar.addActionListener(e -> limpiar(modulo));
        detalle.addActionListener(e -> detalle(modulo));

        return modulo;
    }

    private String iconoModulo(String nombre) {
        switch (nombre) {
            case "Socios": return "♟";
            case "Membresías": return "▣";
            case "Asistencia": return "▦";
            case "Rutinas": return "✚";
            case "Nutrición": return "●";
            case "Inventario": return "▤";
            case "Pagos": return "$";
            default: return "▥";
        }
    }

    private void estiloBoton(JButton boton, boolean principal) {
        boton.setFont(new Font("Arial", Font.BOLD, 11));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setBorder(new EmptyBorder(9, 12, 9, 12));

        if (principal) {
            boton.setBackground(verde);
            boton.setForeground(new Color(15, 22, 13));
        } else {
            boton.setBackground(tarjeta2);
            boton.setForeground(blanco);
        }
    }

    // ======================== REGISTRAR DATOS ========================

    private void registrar(Modulo m) {
        Map<String, String> v = new LinkedHashMap<>();

        for (Map.Entry<String, JTextField> e : m.campos.entrySet()) {
            String valor = e.getValue().getText().trim();

            if (valor.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Completa todos los campos.");
                e.getValue().requestFocus();
                return;
            }
            v.put(e.getKey(), valor);
        }

        try {
            int id = m.modelo.getRowCount() + 1;
            Object[] fila;

            switch (m.nombre) {
                case "Socios":
                    if (!v.get("correo").matches(
                            "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
                        throw new IllegalArgumentException(
                                "Ingresa un correo electrónico válido.");
                    }

                    fila = new Object[]{id, v.get("nombre"),
                        v.get("correo"), v.get("telefono"),
                        LocalDate.parse(v.get("fecha"))};
                    break;

                case "Membresías":
                    double precio = positivo(v.get("precio"));
                    LocalDate inicio = LocalDate.parse(v.get("inicio"));
                    LocalDate fin = LocalDate.parse(v.get("vencimiento"));

                    if (fin.isBefore(inicio)) {
                        throw new IllegalArgumentException(
                                "El vencimiento no puede ser anterior al inicio.");
                    }

                    String estado = LocalDate.now().isBefore(inicio)
                            ? "Programada"
                            : LocalDate.now().isAfter(fin)
                            ? "Vencida" : "Vigente";

                    fila = new Object[]{id, enteroPositivo(v.get("socio")),
                        v.get("tipo"), precio, inicio, fin, estado};
                    break;

                case "Asistencia":
                    fila = new Object[]{id, enteroPositivo(v.get("socio")),
                        LocalDateTime.parse(v.get("fecha"))};
                    break;

                case "Rutinas":
                    fila = new Object[]{id, enteroPositivo(v.get("socio")),
                        v.get("nombre"), v.get("objetivo"), v.get("ejercicios")};
                    break;

                case "Nutrición":
                    fila = new Object[]{id, enteroPositivo(v.get("socio")),
                        v.get("nombre"), v.get("objetivo"),
                        v.get("observaciones")};
                    break;

                case "Inventario":
                    fila = new Object[]{id, v.get("nombre"),
                        v.get("categoria"), positivo(v.get("precio")),
                        enteroNoNegativo(v.get("stock"))};
                    break;

                case "Pagos":
                    fila = new Object[]{id, enteroPositivo(v.get("socio")),
                        positivo(v.get("monto")), v.get("concepto"),
                        LocalDate.parse(v.get("fecha")), v.get("metodo")};
                    break;

                default:
                    fila = new Object[]{id, enteroPositivo(v.get("socio")),
                        LocalDate.parse(v.get("fecha")), positivo(v.get("peso")),
                        positivo(v.get("cintura")), v.get("observaciones")};
                    break;
            }

            m.modelo.addRow(fila);
            limpiar(m);
            actualizarPantalla();

            JOptionPane.showMessageDialog(this,
                    "¡Registro agregado correctamente!",
                    "Infinity Force", JOptionPane.INFORMATION_MESSAGE);

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this,
                    "Fecha incorrecta.\nUsa AAAA-MM-DD.\n"
                    + "En asistencia usa AAAA-MM-DDTHH:MM.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Revisa los valores numéricos.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private int enteroPositivo(String texto) {
        int n = Integer.parseInt(texto);
        if (n <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser mayor que cero.");
        }
        return n;
    }

    private int enteroNoNegativo(String texto) {
        int n = Integer.parseInt(texto);
        if (n < 0) {
            throw new IllegalArgumentException(
                    "El stock no puede ser negativo.");
        }
        return n;
    }

    private double positivo(String texto) {
        double n = Double.parseDouble(texto.replace(',', '.'));
        if (!Double.isFinite(n) || n <= 0) {
            throw new IllegalArgumentException(
                    "El valor debe ser mayor que cero.");
        }
        return n;
    }

    // ======================== ELIMINAR, LIMPIAR Y DETALLE ========================

    private void eliminar(Modulo m) {
        int seleccionado = m.tabla.getSelectedRow();

        if (seleccionado < 0) {
            JOptionPane.showMessageDialog(this,
                    "Selecciona una fila de la tabla.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Deseas eliminar el registro seleccionado?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            int filaModelo = m.tabla.convertRowIndexToModel(seleccionado);
            m.modelo.removeRow(filaModelo);
            actualizarPantalla();
        }
    }

    private void limpiar(Modulo m) {
        for (JTextField campo : m.campos.values()) {
            campo.setText("");
        }
        if (!m.campos.isEmpty()) {
            m.campos.values().iterator().next().requestFocus();
        }
    }

    private void detalle(Modulo m) {
        int seleccionado = m.tabla.getSelectedRow();

        if (seleccionado < 0) {
            JOptionPane.showMessageDialog(this,
                    "Selecciona un registro para ver su detalle.");
            return;
        }

        int fila = m.tabla.convertRowIndexToModel(seleccionado);
        StringBuilder texto = new StringBuilder(
                m.nombre.toUpperCase()).append("\n\n");

        for (int c = 0; c < m.modelo.getColumnCount(); c++) {
            texto.append(m.modelo.getColumnName(c))
                    .append(": ")
                    .append(m.modelo.getValueAt(fila, c))
                    .append("\n");
        }

        JTextArea area = new JTextArea(texto.toString(), 12, 35);
        area.setEditable(false);
        area.setBackground(tarjeta);
        area.setForeground(blanco);
        area.setFont(new Font("Arial", Font.PLAIN, 14));
        area.setBorder(new EmptyBorder(10, 10, 10, 10));

        JOptionPane.showMessageDialog(this, new JScrollPane(area),
                "Detalle del registro", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarModulo(String nombre) {
        Modulo m = modulos.get(nombre);
        contenido.removeAll();
        contenido.add(m.panel, BorderLayout.CENTER);
        contenido.revalidate();
        contenido.repaint();
    }

    private void actualizarPantalla() {
        contenido.revalidate();
        contenido.repaint();
    }

    // ======================== GRAFICO DE REGISTROS ========================

    private class GraficoRegistros extends JPanel {
        GraficoRegistros() {
            setBackground(tarjeta);
            setPreferredSize(new Dimension(500, 280));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int ancho = getWidth();
            int alto = getHeight();
            int izquierda = 48;
            int derecha = 18;
            int arriba = 22;
            int abajo = 48;

            int zonaAncho = ancho - izquierda - derecha;
            int zonaAlto = alto - arriba - abajo;

            int max = 1;
            for (String nombre : nombres) {
                max = Math.max(max,
                        modulos.get(nombre).modelo.getRowCount());
            }

            g2.setFont(new Font("Arial", Font.PLAIN, 10));

            for (int i = 0; i <= 4; i++) {
                int y = arriba + zonaAlto - (zonaAlto * i / 4);
                g2.setColor(borde);
                g2.drawLine(izquierda, y, ancho - derecha, y);

                g2.setColor(gris);
                g2.drawString(String.valueOf(max * i / 4), 8, y + 4);
            }

            int espacio = zonaAncho / nombres.length;
            int bw = Math.max(12, Math.min(35, espacio - 12));

            for (int i = 0; i < nombres.length; i++) {
                String nombre = nombres[i];
                int valor = modulos.get(nombre).modelo.getRowCount();

                int altura = (int) ((double) valor / max * zonaAlto);
                int x = izquierda + i * espacio + (espacio - bw) / 2;
                int y = arriba + zonaAlto - altura;

                g2.setColor(verde);
                g2.fillRoundRect(x, y, bw, Math.max(3, altura), 8, 8);

                g2.setColor(blanco);
                g2.setFont(new Font("Arial", Font.BOLD, 10));
                g2.drawString(String.valueOf(valor),
                        x + bw / 2 - 3, y - 5);

                g2.setColor(gris);
                g2.setFont(new Font("Arial", Font.PLAIN, 9));

                String etiqueta = nombre.equals("Progreso físico")
                        ? "Progreso" : nombre;

                int tx = x + bw / 2
                        - g2.getFontMetrics().stringWidth(etiqueta) / 2;
                g2.drawString(etiqueta, tx, alto - 23);
            }

            g2.dispose();
        }
    }

    // ======================== GRAFICO DE PESO ========================

    private class GraficoPeso extends JPanel {
        private final DefaultTableModel modelo;

        GraficoPeso(DefaultTableModel modelo) {
            this.modelo = modelo;
            setBackground(tarjeta);
            setPreferredSize(new Dimension(500, 175));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int ancho = getWidth();
            int alto = getHeight();

            g2.setColor(verde);
            g2.setFont(new Font("Arial", Font.BOLD, 13));
            g2.drawString("EVOLUCIÓN DEL PESO REGISTRADO", 15, 20);

            java.util.List<Double> pesos = new java.util.ArrayList<>();

            for (int i = 0; i < modelo.getRowCount(); i++) {
                try {
                    pesos.add(Double.parseDouble(
                            String.valueOf(modelo.getValueAt(i, 3))));
                } catch (NumberFormatException ex) {
                    // Ignorar valores que no se puedan interpretar.
                }
            }

            if (pesos.isEmpty()) {
                g2.setColor(gris);
                g2.setFont(new Font("Arial", Font.PLAIN, 12));
                g2.drawString(
                        "Registra datos de progreso físico para visualizar el gráfico.",
                        15, 48);
                g2.dispose();
                return;
            }

            double min = java.util.Collections.min(pesos);
            double max = java.util.Collections.max(pesos);

            if (min == max) {
                min -= 1;
                max += 1;
            }

            int izq = 45;
            int der = 20;
            int arriba = 38;
            int abajo = 30;

            int zonaAncho = ancho - izq - der;
            int zonaAlto = alto - arriba - abajo;

            g2.setColor(borde);
            g2.drawLine(izq, arriba + zonaAlto,
                    ancho - der, arriba + zonaAlto);

            g2.drawLine(izq, arriba, izq, arriba + zonaAlto);

            g2.setColor(gris);
            g2.setFont(new Font("Arial", Font.PLAIN, 10));
            g2.drawString(String.format("%.1f kg", max), 3, arriba + 5);
            g2.drawString(String.format("%.1f kg", min),
                    3, arriba + zonaAlto);

            int anteriorX = -1;
            int anteriorY = -1;

            for (int i = 0; i < pesos.size(); i++) {
                int x = pesos.size() == 1
                        ? izq + zonaAncho / 2
                        : izq + i * zonaAncho / (pesos.size() - 1);

                int y = arriba + zonaAlto - (int) (
                        (pesos.get(i) - min) / (max - min) * zonaAlto);

                if (anteriorX >= 0) {
                    g2.setColor(verde);
                    g2.setStroke(new BasicStroke(2.5f));
                    g2.drawLine(anteriorX, anteriorY, x, y);
                }

                g2.setColor(verde);
                g2.fillOval(x - 4, y - 4, 8, 8);

                g2.setColor(blanco);
                g2.setFont(new Font("Arial", Font.BOLD, 10));
                g2.drawString(String.format("%.1f", pesos.get(i)),
                        x - 10, y - 8);

                anteriorX = x;
                anteriorY = y;
            }

            g2.dispose();
        }
    }

    // ======================== CLASE DEL MODULO ========================

    private static class Modulo {
        String nombre;
        JPanel panel;
        DefaultTableModel modelo;
        JTable tabla;
        Map<String, JTextField> campos;

        Modulo(String nombre, JPanel panel, DefaultTableModel modelo,
               JTable tabla, Map<String, JTextField> campos) {
            this.nombre = nombre;
            this.panel = panel;
            this.modelo = modelo;
            this.tabla = tabla;
            this.campos = campos;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                // Se conserva el estilo predeterminado si no está disponible.
            }

            new InfinityForce().setVisible(true);
        });
    }
}
 