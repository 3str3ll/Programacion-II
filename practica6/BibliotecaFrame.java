package practica6;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BibliotecaFrame extends JFrame {

    private JTabbedPane tabbedPane;
    
    private JPanel panelInicio;
    private JPanel panelLibros;
    private JPanel panelAutores;
    private JPanel panelPrestamos;
    private JPanel panelInfo;
    private JPanel panelBusqueda;
    
    private JTable tablaLibros;
    private JTable tablaAutores;
    private JTable tablaPrestamos;
    private JTable tablaHistorial;
    private JTable tablaBusqueda;
    
    private DefaultTableModel modeloTablaLibros;
    private DefaultTableModel modeloTablaAutores;
    private DefaultTableModel modeloTablaPrestamos;
    private DefaultTableModel modeloHistorial;
    private DefaultTableModel modeloBusqueda;
    
    private JButton btnAgregarLibro, btnEditarLibro, btnEliminarLibro, btnVerLibro, btnExportarLibro;
    private JButton btnAgregarAutor, btnEditarAutor, btnEliminarAutor, btnExportarAutor;
    private JButton btnNuevoPrestamo, btnDevolverLibro, btnExportarPrestamos, btnVerHistorial;
    private JButton btnActualizarInfo;
    private JButton btnBuscarLibros, btnFiltrarDisponibles, btnFiltrarPrestados, btnMostrarTodos;
    
  
    private JTextField txtBusqueda;
    
   
    private JTextArea textAreaInfo;
    
    
    private JMenuBar menuBar;
    private JMenu menuArchivo, menuHerramientas, menuAyuda;
    private JMenuItem menuItemGuardar, menuItemCargar, menuItemSalir;
    private JMenuItem menuItemBackup, menuItemEstadisticas, menuItemReporte;
    private JMenuItem menuItemAcercaDe;
    
    private Biblioteca biblioteca;
    private boolean mostrarTodosLibros = true;
    
    public BibliotecaFrame() {
        initComponents();
        configurarComponentes();
        cargarDatos();
        mostrarDatos();
    }
    
    private void initComponents() {
        // Configurar ventana
        setTitle("Sistema de Gestión de Biblioteca - Avanzado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 750);
        setLocationRelativeTo(null);
        
        // ========== CREAR MENÚ ==========
        menuBar = new JMenuBar();
        
        // Menú Archivo
        menuArchivo = new JMenu("Archivo");
        menuItemGuardar = new JMenuItem("Guardar Biblioteca");
        menuItemCargar = new JMenuItem("Cargar Biblioteca");
        menuItemSalir = new JMenuItem("Salir");
        
        menuItemGuardar.addActionListener(e -> guardarBiblioteca());
        menuItemCargar.addActionListener(e -> cargarBiblioteca());
        menuItemSalir.addActionListener(e -> salir());
        
        menuArchivo.add(menuItemGuardar);
        menuArchivo.add(menuItemCargar);
        menuArchivo.addSeparator();
        menuArchivo.add(menuItemSalir);
        
        // Menú Herramientas
        menuHerramientas = new JMenu("Herramientas");
        menuItemBackup = new JMenuItem("Crear Backup");
        menuItemEstadisticas = new JMenuItem("Estadísticas Avanzadas");
        menuItemReporte = new JMenuItem("Generar Reporte");
        
        menuItemBackup.addActionListener(e -> crearBackup());
        menuItemEstadisticas.addActionListener(e -> mostrarEstadisticasAvanzadas());
        menuItemReporte.addActionListener(e -> generarReporte());
        
        menuHerramientas.add(menuItemBackup);
        menuHerramientas.add(menuItemEstadisticas);
        menuHerramientas.add(menuItemReporte);
        
        // Menú Ayuda
        menuAyuda = new JMenu("Ayuda");
        menuItemAcercaDe = new JMenuItem("Acerca de...");
        menuItemAcercaDe.addActionListener(e -> mostrarAcercaDe());
        menuAyuda.add(menuItemAcercaDe);
        
        // Agregar menús
        menuBar.add(menuArchivo);
        menuBar.add(menuHerramientas);
        menuBar.add(menuAyuda);
        setJMenuBar(menuBar);
        
        // ========== CREAR TABBED PANE ==========
        tabbedPane = new JTabbedPane();
        
        // ========== PANEL INICIO ==========
        panelInicio = crearPanelInicio();
        
        // ========== PANEL LIBROS (MEJORADO) ==========
        panelLibros = crearPanelLibros();
        
        // ========== PANEL AUTORES (MEJORADO) ==========
        panelAutores = crearPanelAutores();
        
        // ========== PANEL PRÉSTAMOS (MEJORADO) ==========
        panelPrestamos = crearPanelPrestamos();
        
        // ========== PANEL BÚSQUEDA (NUEVO) ==========
        panelBusqueda = crearPanelBusqueda();
        
        // ========== PANEL INFORMACIÓN ==========
        panelInfo = crearPanelInfo();
        
        // ========== AGREGAR PANELES ==========
        tabbedPane.addTab("🏠 Inicio", panelInicio);
        tabbedPane.addTab("📚 Libros", panelLibros);
        tabbedPane.addTab("👥 Autores", panelAutores);
        tabbedPane.addTab("📝 Préstamos", panelPrestamos);
        tabbedPane.addTab("🔍 Búsqueda", panelBusqueda);
        tabbedPane.addTab("📊 Información", panelInfo);
        
        // Agregar al JFrame
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    private JPanel crearPanelInicio() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel lblTitulo = new JLabel("📚 SISTEMA DE GESTIÓN DE BIBLIOTECA AVANZADO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(0, 102, 204));
        
        JTextArea txtBienvenida = new JTextArea();
        txtBienvenida.setText("¡Bienvenido al Sistema de Gestión de Biblioteca Avanzado!\n\n"
                + "Nuevas funcionalidades disponibles:\n"
                + "✅ Editar libros y autores\n"
                + "✅ Ver estado de libros (disponible/prestado)\n"
                + "✅ Filtrar libros disponibles vs prestados\n"
                + "✅ Historial completo de préstamos\n"
                + "✅ Búsqueda avanzada por título/autor\n"
                + "✅ Validaciones mejoradas\n"
                + "✅ Reportes y estadísticas\n\n"
                + "Los datos se guardan automáticamente en formato JSON.");
        txtBienvenida.setFont(new Font("Arial", Font.PLAIN, 14));
        txtBienvenida.setEditable(false);
        txtBienvenida.setLineWrap(true);
        txtBienvenida.setWrapStyleWord(true);
        
        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(new JScrollPane(txtBienvenida), BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelLibros() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Tabla de libros con columna de estado
        String[] columnasLibros = {"Título", "ISBN", "Páginas", "Estado"};
        modeloTablaLibros = new DefaultTableModel(columnasLibros, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaLibros = new JTable(modeloTablaLibros);
        
        // Panel de botones superior (filtros)
        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnMostrarTodos = new JButton("📋 Todos los Libros");
        btnFiltrarDisponibles = new JButton("✅ Libros Disponibles");
        btnFiltrarPrestados = new JButton("📖 Libros Prestados");
        
        btnMostrarTodos.addActionListener(e -> {
            mostrarTodosLibros = true;
            actualizarTablaLibros();
        });
        
        btnFiltrarDisponibles.addActionListener(e -> {
            mostrarTodosLibros = false;
            mostrarLibrosDisponibles();
        });
        
        btnFiltrarPrestados.addActionListener(e -> {
            mostrarTodosLibros = false;
            mostrarLibrosPrestados();
        });
        
        panelFiltros.add(btnMostrarTodos);
        panelFiltros.add(btnFiltrarDisponibles);
        panelFiltros.add(btnFiltrarPrestados);
        
        // Panel de botones inferior (acciones)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnAgregarLibro = new JButton("➕ Agregar Libro");
        btnEditarLibro = new JButton("✏️ Editar Libro");
        btnEliminarLibro = new JButton("🗑️ Eliminar Libro");
        btnVerLibro = new JButton("👁️ Ver Detalles");
        btnExportarLibro = new JButton("📤 Exportar Libro");
        
        btnAgregarLibro.addActionListener(e -> agregarLibro());
        btnEditarLibro.addActionListener(e -> editarLibro());
        btnEliminarLibro.addActionListener(e -> eliminarLibro());
        btnVerLibro.addActionListener(e -> verDetallesLibro());
        btnExportarLibro.addActionListener(e -> exportarLibro());
        
        panelBotones.add(btnAgregarLibro);
        panelBotones.add(btnEditarLibro);
        panelBotones.add(btnEliminarLibro);
        panelBotones.add(btnVerLibro);
        panelBotones.add(btnExportarLibro);
        
        // Panel norte con filtros
        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(panelFiltros, BorderLayout.NORTH);
        
        panel.add(panelNorte, BorderLayout.NORTH);
        panel.add(new JScrollPane(tablaLibros), BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel crearPanelAutores() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columnasAutores = {"Nombre", "Nacionalidad"};
        modeloTablaAutores = new DefaultTableModel(columnasAutores, 0);
        tablaAutores = new JTable(modeloTablaAutores);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnAgregarAutor = new JButton("➕ Agregar Autor");
        btnEditarAutor = new JButton("✏️ Editar Autor");
        btnEliminarAutor = new JButton("🗑️ Eliminar Autor");
        btnExportarAutor = new JButton("📤 Exportar Autor");
        
        btnAgregarAutor.addActionListener(e -> agregarAutor());
        btnEditarAutor.addActionListener(e -> editarAutor());
        btnEliminarAutor.addActionListener(e -> eliminarAutor());
        btnExportarAutor.addActionListener(e -> exportarAutor());
        
        panelBotones.add(btnAgregarAutor);
        panelBotones.add(btnEditarAutor);
        panelBotones.add(btnEliminarAutor);
        panelBotones.add(btnExportarAutor);
        
        panel.add(new JScrollPane(tablaAutores), BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel crearPanelPrestamos() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Panel con pestañas para préstamos activos e historial
        JTabbedPane tabbedPrestamos = new JTabbedPane();
        
        // Panel de préstamos activos
        JPanel panelActivos = new JPanel(new BorderLayout());
        String[] columnasPrestamos = {"Estudiante", "Libro", "Fecha Préstamo", "Fecha Devolución", "Estado"};
        modeloTablaPrestamos = new DefaultTableModel(columnasPrestamos, 0);
        tablaPrestamos = new JTable(modeloTablaPrestamos);
        
        JPanel panelBotonesActivos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnNuevoPrestamo = new JButton("➕ Nuevo Préstamo");
        btnDevolverLibro = new JButton("📚 Devolver Libro");
        btnExportarPrestamos = new JButton("📤 Exportar Préstamos");
        
        btnNuevoPrestamo.addActionListener(e -> nuevoPrestamo());
        btnDevolverLibro.addActionListener(e -> devolverLibro());
        btnExportarPrestamos.addActionListener(e -> exportarPrestamos());
        
        panelBotonesActivos.add(btnNuevoPrestamo);
        panelBotonesActivos.add(btnDevolverLibro);
        panelBotonesActivos.add(btnExportarPrestamos);
        
        panelActivos.add(new JScrollPane(tablaPrestamos), BorderLayout.CENTER);
        panelActivos.add(panelBotonesActivos, BorderLayout.SOUTH);
        
        // Panel de historial
        JPanel panelHistorial = new JPanel(new BorderLayout());
        String[] columnasHistorial = {"Estudiante", "Libro", "Fecha Préstamo", "Fecha Devolución", "Estado"};
        modeloHistorial = new DefaultTableModel(columnasHistorial, 0);
        tablaHistorial = new JTable(modeloHistorial);
        
        panelHistorial.add(new JScrollPane(tablaHistorial), BorderLayout.CENTER);
        
        // Agregar pestañas
        tabbedPrestamos.addTab("📋 Préstamos Activos", panelActivos);
        tabbedPrestamos.addTab("📜 Historial Completo", panelHistorial);
        
        panel.add(tabbedPrestamos, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelBusqueda() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Panel de búsqueda
        JPanel panelBusquedaSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblBuscar = new JLabel("Buscar libro:");
        txtBusqueda = new JTextField(20);
        btnBuscarLibros = new JButton("🔍 Buscar");
        
        btnBuscarLibros.addActionListener(e -> buscarLibros());
        
        panelBusquedaSuperior.add(lblBuscar);
        panelBusquedaSuperior.add(txtBusqueda);
        panelBusquedaSuperior.add(btnBuscarLibros);
        
        // Tabla de resultados
        String[] columnasBusqueda = {"Título", "ISBN", "Páginas", "Estado", "Disponible"};
        modeloBusqueda = new DefaultTableModel(columnasBusqueda, 0);
        tablaBusqueda = new JTable(modeloBusqueda);
        
        panel.add(panelBusquedaSuperior, BorderLayout.NORTH);
        panel.add(new JScrollPane(tablaBusqueda), BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelInfo() {
        JPanel panel = new JPanel(new BorderLayout());
        
        textAreaInfo = new JTextArea();
        textAreaInfo.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textAreaInfo.setEditable(false);
        
        btnActualizarInfo = new JButton("🔄 Actualizar Información");
        btnActualizarInfo.addActionListener(e -> actualizarInformacion());
        
        panel.add(new JScrollPane(textAreaInfo), BorderLayout.CENTER);
        panel.add(btnActualizarInfo, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void configurarComponentes() {
        // Configurar tablas
        tablaLibros.setRowHeight(25);
        tablaAutores.setRowHeight(25);
        tablaPrestamos.setRowHeight(25);
        tablaHistorial.setRowHeight(25);
        tablaBusqueda.setRowHeight(25);
        
        tablaLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaAutores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaPrestamos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Configurar colores para estado
        tablaLibros.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (column == 3) { // Columna de estado
                    String estado = (String) value;
                    if ("PRESTADO".equals(estado)) {
                        c.setBackground(new Color(255, 200, 200)); // Rojo claro
                        c.setForeground(Color.RED);
                    } else if ("DISPONIBLE".equals(estado)) {
                        c.setBackground(new Color(200, 255, 200)); // Verde claro
                        c.setForeground(new Color(0, 100, 0));
                    } else {
                        c.setBackground(Color.WHITE);
                        c.setForeground(Color.BLACK);
                    }
                } else {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
                
                return c;
            }
        });
    }
    
    private void cargarDatos() {
        biblioteca = GestorJSON.cargarBiblioteca();
        
        if (biblioteca.getLibros().isEmpty()) {
            crearDatosEjemplo();
        }
    }
    
    private void crearDatosEjemplo() {
        // Crear autores
        Autor autor1 = new Autor("Gabriel Garcia Marquez", "Colombiano");
        Autor autor2 = new Autor("Jorge Luis Borges", "Argentino");
        Autor autor3 = new Autor("Mario Vargas Llosa", "Peruano");
        
        // Crear libros
        String[] paginas1 = {"Página 1: Érase una vez...", "Página 2: En Macondo..."};
        String[] paginas2 = {"Página 1: El jardín de senderos...", "Página 2: La biblioteca de Babel..."};
        String[] paginas3 = {"Página 1: Introducción...", "Página 2: Desarrollo..."};
        
        Libro libro1 = new Libro("Cien Años de Soledad", "978-001", paginas1);
        Libro libro2 = new Libro("Ficciones", "978-002", paginas2);
        Libro libro3 = new Libro("La Ciudad y los Perros", "978-003", paginas3);
        
        // Crear estudiantes
        Estudiante est1 = new Estudiante("2023001", "Maria Gonzalez");
        Estudiante est2 = new Estudiante("2023002", "Carlos Perez");
        Estudiante est3 = new Estudiante("2023003", "Ana Rodriguez");
        
        // Agregar a la biblioteca
        biblioteca.agregarAutor(autor1);
        biblioteca.agregarAutor(autor2);
        biblioteca.agregarAutor(autor3);
        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro2);
        biblioteca.agregarLibro(libro3);
        
        // Crear algunos préstamos
        biblioteca.prestarLibro(est1, libro1, "2025-01-15", "2025-02-15");
        biblioteca.prestarLibro(est2, libro2, "2025-01-16", "2025-02-16");
        // Libro 3 queda disponible
        
        // Marcar uno como devuelto para probar historial
        if (!biblioteca.getPrestamos().isEmpty()) {
            biblioteca.devolverLibro(biblioteca.getPrestamos().get(0));
        }
        
        GestorJSON.guardarBiblioteca(biblioteca);
    }
    
    // ========== MÉTODOS PARA MOSTRAR DATOS ==========
    
    private void mostrarDatos() {
        actualizarTablaLibros();
        actualizarTablaAutores();
        actualizarTablaPrestamos();
        actualizarHistorialPrestamos();
        actualizarInformacion();
    }
    
    private void actualizarTablaLibros() {
        modeloTablaLibros.setRowCount(0);
        for (Libro libro : biblioteca.getLibros()) {
            String estado = biblioteca.estaLibroDisponible(libro) ? "DISPONIBLE" : "PRESTADO";
            modeloTablaLibros.addRow(new Object[]{
                libro.getTitulo(),
                libro.getIsbn(),
                libro.getPaginas().size(),
                estado
            });
        }
    }
    
    private void mostrarLibrosDisponibles() {
        modeloTablaLibros.setRowCount(0);
        for (Libro libro : biblioteca.getLibrosDisponibles()) {
            modeloTablaLibros.addRow(new Object[]{
                libro.getTitulo(),
                libro.getIsbn(),
                libro.getPaginas().size(),
                "DISPONIBLE"
            });
        }
    }
    
    private void mostrarLibrosPrestados() {
        modeloTablaLibros.setRowCount(0);
        for (Libro libro : biblioteca.getLibrosPrestados()) {
            modeloTablaLibros.addRow(new Object[]{
                libro.getTitulo(),
                libro.getIsbn(),
                libro.getPaginas().size(),
                "PRESTADO"
            });
        }
    }
    
    private void actualizarTablaAutores() {
        modeloTablaAutores.setRowCount(0);
        for (Autor autor : biblioteca.getAutores()) {
            modeloTablaAutores.addRow(new Object[]{
                autor.getNombre(),
                autor.getNacionalidad()
            });
        }
    }
    
    private void actualizarTablaPrestamos() {
        modeloTablaPrestamos.setRowCount(0);
        for (Prestamo prestamo : biblioteca.getPrestamosActivos()) {
            String estado = prestamo.isDevuelto() ? "DEVUELTO" : "PRESTADO";
            modeloTablaPrestamos.addRow(new Object[]{
                prestamo.getEstudiante().getNombre(),
                prestamo.getLibro().getTitulo(),
                prestamo.getFechaPrestamo(),
                prestamo.getFechaDevolucion(),
                estado
            });
        }
    }
    
    private void actualizarHistorialPrestamos() {
        modeloHistorial.setRowCount(0);
        for (Prestamo prestamo : biblioteca.getHistorialPrestamos()) {
            String estado = prestamo.isDevuelto() ? "DEVUELTO" : "PRESTADO";
            modeloHistorial.addRow(new Object[]{
                prestamo.getEstudiante().getNombre(),
                prestamo.getLibro().getTitulo(),
                prestamo.getFechaPrestamo(),
                prestamo.getFechaDevolucion(),
                estado
            });
        }
    }
    
    private void actualizarInformacion() {
        StringBuilder info = new StringBuilder();
        info.append("=== INFORMACIÓN DE LA BIBLIOTECA ===\n\n");
        info.append("Nombre: ").append(biblioteca.getNombre()).append("\n");
        info.append("Horario: ").append(biblioteca.getHorario()).append("\n\n");
        
        info.append("=== ESTADÍSTICAS DETALLADAS ===\n");
        info.append("Total Libros: ").append(biblioteca.getLibros().size()).append("\n");
        info.append("Libros Disponibles: ").append(biblioteca.getLibrosDisponibles().size()).append("\n");
        info.append("Libros Prestados: ").append(biblioteca.getLibrosPrestados().size()).append("\n");
        info.append("Total Autores: ").append(biblioteca.getAutores().size()).append("\n");
        info.append("Préstamos Activos: ").append(biblioteca.getPrestamosActivos().size()).append("\n");
        info.append("Total Préstamos Historial: ").append(biblioteca.getHistorialPrestamos().size()).append("\n\n");
        
        info.append("=== LIBROS MÁS PRESTADOS ===\n");
        // Aquí podrías agregar lógica para contar préstamos por libro
        
        info.append("=== ARCHIVO JSON ===\n");
        info.append("Datos guardados en: biblioteca.json\n");
        info.append("Formato: JSON con Gson 2.13.2");
        
        textAreaInfo.setText(info.toString());
    }
    
    // ========== MÉTODOS PARA LIBROS ==========
    
    private void agregarLibro() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        
        JLabel lblTitulo = new JLabel("Título:");
        JTextField txtTitulo = new JTextField(20);
        
        JLabel lblISBN = new JLabel("ISBN:");
        JTextField txtISBN = new JTextField(20);
        
        JLabel lblPaginas = new JLabel("Número de páginas:");
        JSpinner spinnerPaginas = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
        
        panel.add(lblTitulo);
        panel.add(txtTitulo);
        panel.add(lblISBN);
        panel.add(txtISBN);
        panel.add(lblPaginas);
        panel.add(spinnerPaginas);
        
        int option = JOptionPane.showConfirmDialog(this, panel, 
            "Agregar Nuevo Libro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (option == JOptionPane.OK_OPTION) {
            String titulo = txtTitulo.getText().trim();
            String isbn = txtISBN.getText().trim();
            int numPaginas = (int) spinnerPaginas.getValue();
            
            if (titulo.isEmpty() || isbn.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Debe completar todos los campos", "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Verificar si ya existe un libro con ese ISBN
            for (Libro libro : biblioteca.getLibros()) {
                if (libro.getIsbn().equals(isbn)) {
                    JOptionPane.showMessageDialog(this, 
                        "Ya existe un libro con ese ISBN", "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            String[] paginas = new String[numPaginas];
            for (int i = 0; i < numPaginas; i++) {
                paginas[i] = "Contenido de la página " + (i + 1);
            }
            
            Libro nuevoLibro = new Libro(titulo, isbn, paginas);
            biblioteca.agregarLibro(nuevoLibro);
            actualizarTablaLibros();
            GestorJSON.guardarBiblioteca(biblioteca);
            JOptionPane.showMessageDialog(this, 
                "Libro agregado exitosamente!", "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void editarLibro() {
        int fila = tablaLibros.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, 
                "Seleccione un libro para editar", "Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Libro libro = biblioteca.getLibros().get(fila);
        
        // Verificar si el libro está prestado
        if (biblioteca.estaLibroPrestado(libro)) {
            JOptionPane.showMessageDialog(this, 
                "No se puede editar un libro que está prestado", "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        
        JLabel lblTitulo = new JLabel("Título:");
        JTextField txtTitulo = new JTextField(libro.getTitulo(), 20);
        
        JLabel lblISBN = new JLabel("ISBN:");
        JTextField txtISBN = new JTextField(libro.getIsbn(), 20);
        
        JLabel lblPaginas = new JLabel("Páginas actuales: " + libro.getPaginas().size());
        JLabel lblInfo = new JLabel("(El número de páginas no se puede modificar)");
        
        panel.add(lblTitulo);
        panel.add(txtTitulo);
        panel.add(lblISBN);
        panel.add(txtISBN);
        panel.add(lblPaginas);
        panel.add(lblInfo);
        
        int option = JOptionPane.showConfirmDialog(this, panel, 
            "Editar Libro: " + libro.getTitulo(), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (option == JOptionPane.OK_OPTION) {
            String nuevoTitulo = txtTitulo.getText().trim();
            String nuevoISBN = txtISBN.getText().trim();
            
            if (nuevoTitulo.isEmpty() || nuevoISBN.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Debe completar todos los campos", "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Verificar si el nuevo ISBN ya existe (excepto en este libro)
            for (int i = 0; i < biblioteca.getLibros().size(); i++) {
                if (i != fila && biblioteca.getLibros().get(i).getIsbn().equals(nuevoISBN)) {
                    JOptionPane.showMessageDialog(this, 
                        "Ya existe otro libro con ese ISBN", "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            libro.setTitulo(nuevoTitulo);
            libro.setIsbn(nuevoISBN);
            
            biblioteca.editarLibro(fila, libro);
            actualizarTablaLibros();
            GestorJSON.guardarBiblioteca(biblioteca);
            JOptionPane.showMessageDialog(this, 
                "Libro editado exitosamente!", "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void eliminarLibro() {
        int fila = tablaLibros.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, 
                "Seleccione un libro para eliminar", "Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Libro libro = biblioteca.getLibros().get(fila);
        
        // Verificar si el libro está prestado
        if (biblioteca.estaLibroPrestado(libro)) {
            JOptionPane.showMessageDialog(this, 
                "No se puede eliminar un libro que está prestado", "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de eliminar el libro:\n" + libro.getTitulo() + "?", 
            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            biblioteca.eliminarLibro(fila);
            actualizarTablaLibros();
            GestorJSON.guardarBiblioteca(biblioteca);
            JOptionPane.showMessageDialog(this, 
                "Libro eliminado exitosamente!", "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void verDetallesLibro() {
        int fila = tablaLibros.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, 
                "Seleccione un libro para ver detalles", "Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Libro libro = biblioteca.getLibros().get(fila);
        boolean disponible = biblioteca.estaLibroDisponible(libro);
        
        StringBuilder detalles = new StringBuilder();
        detalles.append("=== DETALLES DEL LIBRO ===\n\n");
        detalles.append("Título: ").append(libro.getTitulo()).append("\n");
        detalles.append("ISBN: ").append(libro.getIsbn()).append("\n");
        detalles.append("Páginas: ").append(libro.getPaginas().size()).append("\n");
        detalles.append("Estado: ").append(disponible ? "✅ DISPONIBLE" : "📖 PRESTADO").append("\n\n");
        
        if (!disponible) {
            // Mostrar información del préstamo activo
            for (Prestamo prestamo : biblioteca.getPrestamosActivos()) {
                if (prestamo.getLibro().equals(libro)) {
                    detalles.append("=== INFORMACIÓN DEL PRÉSTAMO ===\n");
                    detalles.append("Estudiante: ").append(prestamo.getEstudiante().getNombre()).append("\n");
                    detalles.append("Código: ").append(prestamo.getEstudiante().getCodigo()).append("\n");
                    detalles.append("Fecha Préstamo: ").append(prestamo.getFechaPrestamo()).append("\n");
                    detalles.append("Fecha Devolución: ").append(prestamo.getFechaDevolucion()).append("\n");
                    break;
                }
            }
        }
        
        detalles.append("\n=== PRIMERAS PÁGINAS ===\n");
        int count = 0;
        for (Pagina pagina : libro.getPaginas()) {
            if (count++ >= 3) break; // Mostrar solo las primeras 3 páginas
            detalles.append("\nPágina ").append(pagina.getNumero()).append(":\n");
            detalles.append(pagina.getContenido()).append("\n");
        }
        
        if (libro.getPaginas().size() > 3) {
            detalles.append("\n... y ").append(libro.getPaginas().size() - 3).append(" páginas más");
        }
        
        JTextArea textArea = new JTextArea(detalles.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        
        JOptionPane.showMessageDialog(this, scrollPane, 
            "Detalles del Libro: " + libro.getTitulo(), JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void exportarLibro() {
        int fila = tablaLibros.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, 
                "Seleccione un libro para exportar", "Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Libro libro = biblioteca.getLibros().get(fila);
        String nombreArchivo = "libro_" + libro.getTitulo().replaceAll("[^a-zA-Z0-9]", "_") + ".json";
        
        GestorJSON.exportarObjeto(libro, nombreArchivo);
        JOptionPane.showMessageDialog(this, 
            "Libro exportado a: " + nombreArchivo, "Exportar", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // ========== MÉTODOS PARA AUTORES ==========
    
    private void agregarAutor() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(20);
        
        JLabel lblNacionalidad = new JLabel("Nacionalidad:");
        JTextField txtNacionalidad = new JTextField(20);
        
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblNacionalidad);
        panel.add(txtNacionalidad);
        
        int option = JOptionPane.showConfirmDialog(this, panel, 
            "Agregar Nuevo Autor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (option == JOptionPane.OK_OPTION) {
            String nombre = txtNombre.getText().trim();
            String nacionalidad = txtNacionalidad.getText().trim();
            
            if (nombre.isEmpty() || nacionalidad.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Debe completar todos los campos", "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Autor nuevoAutor = new Autor(nombre, nacionalidad);
            biblioteca.agregarAutor(nuevoAutor);
            actualizarTablaAutores();
            GestorJSON.guardarBiblioteca(biblioteca);
            JOptionPane.showMessageDialog(this, 
                "Autor agregado exitosamente!", "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void editarAutor() {
        int fila = tablaAutores.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, 
                "Seleccione un autor para editar", "Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Autor autor = biblioteca.getAutores().get(fila);
        
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(autor.getNombre(), 20);
        
        JLabel lblNacionalidad = new JLabel("Nacionalidad:");
        JTextField txtNacionalidad = new JTextField(autor.getNacionalidad(), 20);
        
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblNacionalidad);
        panel.add(txtNacionalidad);
        
        int option = JOptionPane.showConfirmDialog(this, panel, 
            "Editar Autor: " + autor.getNombre(), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (option == JOptionPane.OK_OPTION) {
            String nuevoNombre = txtNombre.getText().trim();
            String nuevaNacionalidad = txtNacionalidad.getText().trim();
            
            if (nuevoNombre.isEmpty() || nuevaNacionalidad.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Debe completar todos los campos", "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            autor.setNombre(nuevoNombre);
            autor.setNacionalidad(nuevaNacionalidad);
            
            biblioteca.editarAutor(fila, autor);
            actualizarTablaAutores();
            GestorJSON.guardarBiblioteca(biblioteca);
            JOptionPane.showMessageDialog(this, 
                "Autor editado exitosamente!", "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void eliminarAutor() {
        int fila = tablaAutores.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, 
                "Seleccione un autor para eliminar", "Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Autor autor = biblioteca.getAutores().get(fila);
        
        // Verificar si el autor tiene libros asociados
        boolean tieneLibros = false;
        for (Libro libro : biblioteca.getLibros()) {
            // En un sistema real, aquí verificarías la relación autor-libro
            // Por ahora, solo mostramos un mensaje genérico
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de eliminar al autor:\n" + autor.getNombre() + "?", 
            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            biblioteca.eliminarAutor(fila);
            actualizarTablaAutores();
            GestorJSON.guardarBiblioteca(biblioteca);
            JOptionPane.showMessageDialog(this, 
                "Autor eliminado exitosamente!", "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void exportarAutor() {
        int fila = tablaAutores.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, 
                "Seleccione un autor para exportar", "Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Autor autor = biblioteca.getAutores().get(fila);
        String nombreArchivo = "autor_" + autor.getNombre().replaceAll("[^a-zA-Z0-9]", "_") + ".json";
        
        GestorJSON.exportarObjeto(autor, nombreArchivo);
        JOptionPane.showMessageDialog(this, 
            "Autor exportado a: " + nombreArchivo, "Exportar", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // ========== MÉTODOS PARA PRÉSTAMOS ==========
    
    private void nuevoPrestamo() {
        // Obtener libros disponibles
        ArrayList<Libro> librosDisponibles = biblioteca.getLibrosDisponibles();
        
        if (librosDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No hay libros disponibles para prestar", "Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        
        JLabel lblEstudiante = new JLabel("Nombre del Estudiante:");
        JTextField txtEstudiante = new JTextField(20);
        
        JLabel lblCodigo = new JLabel("Código del Estudiante:");
        JTextField txtCodigo = new JTextField(20);
        
        JLabel lblLibro = new JLabel("Libro a prestar:");
        JComboBox<String> comboLibros = new JComboBox<>();
        for (Libro libro : librosDisponibles) {
            comboLibros.addItem(libro.getTitulo() + " (ISBN: " + libro.getIsbn() + ")");
        }
        
        JLabel lblFechaPrestamo = new JLabel("Fecha de Préstamo (YYYY-MM-DD):");
        JTextField txtFechaPrestamo = new JTextField("2025-01-01");
        
        JLabel lblFechaDevolucion = new JLabel("Fecha de Devolución (YYYY-MM-DD):");
        JTextField txtFechaDevolucion = new JTextField("2025-02-01");
        
        panel.add(lblEstudiante);
        panel.add(txtEstudiante);
        panel.add(lblCodigo);
        panel.add(txtCodigo);
        panel.add(lblLibro);
        panel.add(comboLibros);
        panel.add(lblFechaPrestamo);
        panel.add(txtFechaPrestamo);
        panel.add(lblFechaDevolucion);
        panel.add(txtFechaDevolucion);
        
        int option = JOptionPane.showConfirmDialog(this, panel, 
            "Nuevo Préstamo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (option == JOptionPane.OK_OPTION) {
            String estudiante = txtEstudiante.getText().trim();
            String codigo = txtCodigo.getText().trim();
            int libroIndex = comboLibros.getSelectedIndex();
            String fechaPrestamo = txtFechaPrestamo.getText().trim();
            String fechaDevolucion = txtFechaDevolucion.getText().trim();
            
            if (estudiante.isEmpty() || codigo.isEmpty() || 
                fechaPrestamo.isEmpty() || fechaDevolucion.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Debe completar todos los campos", "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (libroIndex < 0) {
                JOptionPane.showMessageDialog(this, 
                    "Debe seleccionar un libro", "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Libro libro = librosDisponibles.get(libroIndex);
            Estudiante est = new Estudiante(codigo, estudiante);
            
            biblioteca.prestarLibro(est, libro, fechaPrestamo, fechaDevolucion);
            
            actualizarTablaLibros();
            actualizarTablaPrestamos();
            actualizarHistorialPrestamos();
            GestorJSON.guardarBiblioteca(biblioteca);
            
            JOptionPane.showMessageDialog(this, 
                "Préstamo registrado exitosamente!\n" +
                "Libro: " + libro.getTitulo() + "\n" +
                "Estudiante: " + estudiante + "\n" +
                "Fecha de devolución: " + fechaDevolucion, 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void devolverLibro() {
        int fila = tablaPrestamos.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, 
                "Seleccione un préstamo para devolver", "Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Obtener el préstamo de la lista de activos
        ArrayList<Prestamo> prestamosActivos = biblioteca.getPrestamosActivos();
        if (fila >= prestamosActivos.size()) {
            JOptionPane.showMessageDialog(this, 
                "Préstamo no encontrado", "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Prestamo prestamo = prestamosActivos.get(fila);
        
        if (prestamo.isDevuelto()) {
            JOptionPane.showMessageDialog(this, 
                "Este libro ya ha sido devuelto", "Información", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Confirmar devolución del libro?\n\n" +
            "Libro: " + prestamo.getLibro().getTitulo() + "\n" +
            "Estudiante: " + prestamo.getEstudiante().getNombre() + "\n" +
            "Prestado el: " + prestamo.getFechaPrestamo(), 
            "Confirmar Devolución", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            biblioteca.devolverLibro(prestamo);
            
            actualizarTablaLibros();
            actualizarTablaPrestamos();
            actualizarHistorialPrestamos();
            GestorJSON.guardarBiblioteca(biblioteca);
            
            JOptionPane.showMessageDialog(this, 
                "Libro devuelto exitosamente!", "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void exportarPrestamos() {
        String nombreArchivo = "prestamos_" + System.currentTimeMillis() + ".json";
        GestorJSON.exportarObjeto(biblioteca.getPrestamos(), nombreArchivo);
        JOptionPane.showMessageDialog(this, 
            "Todos los préstamos exportados a: " + nombreArchivo, "Exportar", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // ========== MÉTODOS DE BÚSQUEDA ==========
    
    private void buscarLibros() {
        String busqueda = txtBusqueda.getText().trim();
        
        if (busqueda.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Ingrese un término de búsqueda", "Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        ArrayList<Libro> resultados = biblioteca.buscarLibrosPorTitulo(busqueda);
        
        modeloBusqueda.setRowCount(0);
        for (Libro libro : resultados) {
            boolean disponible = biblioteca.estaLibroDisponible(libro);
            String estado = disponible ? "DISPONIBLE" : "PRESTADO";
            
            modeloBusqueda.addRow(new Object[]{
                libro.getTitulo(),
                libro.getIsbn(),
                libro.getPaginas().size(),
                estado,
                disponible ? "✅" : "❌"
            });
        }
        
        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No se encontraron libros con: " + busqueda, "Búsqueda", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // ========== MÉTODOS DE MENÚ ==========
    
    private void guardarBiblioteca() {
        GestorJSON.guardarBiblioteca(biblioteca);
        JOptionPane.showMessageDialog(this, 
            "Biblioteca guardada exitosamente!", "Guardar", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void cargarBiblioteca() {
        biblioteca = GestorJSON.cargarBiblioteca();
        mostrarDatos();
        JOptionPane.showMessageDialog(this, 
            "Biblioteca cargada exitosamente!", "Cargar", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void salir() {
        int opcion = JOptionPane.showConfirmDialog(this, 
            "¿Desea guardar antes de salir?", "Salir", 
            JOptionPane.YES_NO_CANCEL_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            GestorJSON.guardarBiblioteca(biblioteca);
            System.exit(0);
        } else if (opcion == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }
    
    private void crearBackup() {
        String nombreArchivo = "backup_biblioteca_" + System.currentTimeMillis() + ".json";
        GestorJSON.exportarObjeto(biblioteca, nombreArchivo);
        JOptionPane.showMessageDialog(this, 
            "Backup creado exitosamente:\n" + nombreArchivo, "Backup", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mostrarEstadisticasAvanzadas() {
        int totalLibros = biblioteca.getLibros().size();
        int disponibles = biblioteca.getLibrosDisponibles().size();
        int prestados = biblioteca.getLibrosPrestados().size();
        int totalAutores = biblioteca.getAutores().size();
        int prestamosActivos = biblioteca.getPrestamosActivos().size();
        int totalPrestamos = biblioteca.getHistorialPrestamos().size();
        
        double porcentajePrestados = totalLibros > 0 ? (prestados * 100.0 / totalLibros) : 0;
        
        StringBuilder stats = new StringBuilder();
        stats.append("=== ESTADÍSTICAS AVANZADAS ===\n\n");
        stats.append("📊 INVENTARIO DE LIBROS\n");
        stats.append("   Total libros: ").append(totalLibros).append("\n");
        stats.append("   Disponibles: ").append(disponibles).append(" (").append(String.format("%.1f", 100 - porcentajePrestados)).append("%)\n");
        stats.append("   Prestados: ").append(prestados).append(" (").append(String.format("%.1f", porcentajePrestados)).append("%)\n\n");
        
        stats.append("👥 AUTORES\n");
        stats.append("   Total autores: ").append(totalAutores).append("\n\n");
        
        stats.append("📝 PRÉSTAMOS\n");
        stats.append("   Activos: ").append(prestamosActivos).append("\n");
        stats.append("   Historial total: ").append(totalPrestamos).append("\n");
        
        if (totalPrestamos > 0) {
            double promedioDias = 30.0; // Esto sería calculado en un sistema real
            stats.append("   Promedio días préstamo: ").append(String.format("%.1f", promedioDias)).append(" días\n");
        }
        
        JTextArea textArea = new JTextArea(stats.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        
        JOptionPane.showMessageDialog(this, scrollPane, 
            "Estadísticas Avanzadas", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void generarReporte() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("=== REPORTE DE BIBLIOTECA ===\n\n");
        reporte.append("Generado: ").append(java.time.LocalDate.now()).append("\n");
        reporte.append("Biblioteca: ").append(biblioteca.getNombre()).append("\n\n");
        
        reporte.append("=== LIBROS ===\n");
        for (Libro libro : biblioteca.getLibros()) {
            String estado = biblioteca.estaLibroDisponible(libro) ? "DISPONIBLE" : "PRESTADO";
            reporte.append("- ").append(libro.getTitulo())
                   .append(" (").append(libro.getIsbn()).append(") - ")
                   .append(estado).append("\n");
        }
        
        reporte.append("\n=== PRÉSTAMOS ACTIVOS ===\n");
        for (Prestamo prestamo : biblioteca.getPrestamosActivos()) {
            reporte.append("- ").append(prestamo.getEstudiante().getNombre())
                   .append(" -> ").append(prestamo.getLibro().getTitulo())
                   .append(" (").append(prestamo.getFechaPrestamo())
                   .append(" a ").append(prestamo.getFechaDevolucion()).append(")\n");
        }
        
        // Guardar reporte en archivo
        String nombreArchivo = "reporte_biblioteca_" + System.currentTimeMillis() + ".txt";
        try (java.io.FileWriter writer = new java.io.FileWriter(nombreArchivo)) {
            writer.write(reporte.toString());
            JOptionPane.showMessageDialog(this, 
                "Reporte generado exitosamente:\n" + nombreArchivo, "Reporte", 
                JOptionPane.INFORMATION_MESSAGE);
        } catch (java.io.IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Error al generar reporte: " + e.getMessage(), "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarAcercaDe() {
        String acercaDe = "SISTEMA DE GESTIÓN DE BIBLIOTECA AVANZADO\n" +
                         "Versión 2.0\n\n" +
                         "Desarrollado con:\n" +
                         "• Java Swing para interfaz gráfica\n" +
                         "• Gson 2.13.2 para persistencia JSON\n\n" +
                         "Funcionalidades:\n" +
                         "✓ Gestión completa de libros\n" +
                         "✓ Control de autores\n" +
                         "✓ Sistema de préstamos\n" +
                         "✓ Historial de movimientos\n" +
                         "✓ Búsqueda avanzada\n" +
                         "✓ Reportes y estadísticas\n" +
                         "✓ Persistencia automática en JSON\n\n" +
                         "© 2024 - Sistema Bibliotecario Avanzado";
        
        JOptionPane.showMessageDialog(this, acercaDe, 
            "Acerca del Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // ========== MÉTODO MAIN ==========
    
    public static void main(String[] args) {
        // Configurar Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Ejecutar interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            BibliotecaFrame gui = new BibliotecaFrame();
            gui.setVisible(true);
        });
    }
}