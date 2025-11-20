package ProyectoInfoPointPro;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JLabel lblNewLabel_1;

    private DefaultListModel<Book> model;
    private JList<Book> list;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Biblioteca frame = new Biblioteca();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Biblioteca() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 790, 512);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("BIBLIOTECA");
        lblNewLabel.setForeground(new Color(253, 187, 66));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("HP Simplified Hans", Font.BOLD, 25));
        lblNewLabel.setBounds(0, 0, 774, 50);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setToolTipText("");
        textField.setBounds(192, 61, 400, 27);
        contentPane.add(textField);
        textField.setColumns(10);

        lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Tarde\\Downloads\\lupa-de-busqueda (2).png"));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
        // Cambia la ruta del icono o quítalo si no aplica
        lblNewLabel_1.setBounds(156, 54, 32, 42);
        contentPane.add(lblNewLabel_1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 99, 774, 344);
        contentPane.add(scrollPane);

        // Modelo y lista
        model = new DefaultListModel<>();
        list = new JList<>(model);
        list.setBackground(new Color(192, 192, 192));
        list.setCellRenderer(new BookCellRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPane.setViewportView(list);

        // Cargar datos de ejemplo (la base de datos aún no está lista)
        loadSampleBooks();

        // Filtrado simple al escribir en el textField
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = textField.getText().trim().toLowerCase();
                filterList(text);
            }
        });
    }

    // Carga de datos de ejemplo en memoria (sustituirá a la lectura desde la BD cuando la tengas lista)
    private void loadSampleBooks() {
        List<Book> samples = new ArrayList<>();
        samples.add(new Book(1, "El Quijote", "Una novela clásica de Miguel de Cervantes sobre las aventuras de Don Quijote y Sancho Panza. Una obra extensa y repleta de episodios humorísticos y reflexivos."));
        samples.add(new Book(2, "Cien años de soledad", "La saga de la familia Buendía en Macondo, por Gabriel García Márquez. Realismo mágico y memoria histórica."));
        samples.add(new Book(3, "Introducción a Java", "Manual práctico para aprender programación en Java, cubriendo desde los fundamentos hasta conceptos intermedios y ejemplos prácticos."));
        samples.add(new Book(4, "Patrones de diseño", "Resumen y ejemplos de los patrones de diseño más usados en ingeniería de software: Singleton, Factory, Observer, Decorator, etc."));
        samples.add(new Book(5, "Historia de España", "Recorrido por los hechos más relevantes de la Historia de España desde la Edad Media hasta la contemporaneidad."));

        model.clear();
        for (Book b : samples) model.addElement(b);
    }

    // Filtra el modelo mostrando solo los libros cuyo título o descripción contenga el texto
    private void filterList(String text) {
        // Como los datos están en memoria, iteramos sobre el modelo original
        DefaultListModel<Book> backup = new DefaultListModel<>();
        for (int i = 0; i < model.getSize(); i++) backup.addElement(model.get(i));

        if (text.isEmpty()) {
            // recargar ejemplos (fácil y seguro aquí)
            loadSampleBooks();
            return;
        }

        DefaultListModel<Book> filtered = new DefaultListModel<>();
        for (int i = 0; i < backup.getSize(); i++) {
            Book b = backup.get(i);
            if (b.getTitle().toLowerCase().contains(text) || b.getDescription().toLowerCase().contains(text)) {
                filtered.addElement(b);
            }
        }

        model.clear();
        for (int i = 0; i < filtered.getSize(); i++) model.addElement(filtered.get(i));
    }

    // Modelo simple de libro
    static class Book {
        private final int id;
        private final String title;
        private final String description;

        public Book(int id, String title, String description) {
            this.id = id;
            this.title = title;
            this.description = description;
        }

        public int getId() { return id; }
        public String getTitle() { return title; }
        public String getDescription() { return description; }

        @Override
        public String toString() {
            return title; // útil para debugging
        }
    }

    // Renderer personalizado: título a la izquierda, descripción a la derecha (la descripción se abrevia si es muy larga)
    static class BookCellRenderer extends JPanel implements ListCellRenderer<Book> {

        private final JLabel lblTitle = new JLabel();
        private final JLabel lblDescription = new JLabel();

        public BookCellRenderer() {
            setLayout(new BorderLayout(10, 0));
            lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 14f));

            // Para que la descripción pueda ocupar varias líneas se usa HTML en el JLabel
            lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
            lblDescription.setFont(lblDescription.getFont().deriveFont(Font.PLAIN, 12f));

            add(lblTitle, BorderLayout.WEST);
            add(lblDescription, BorderLayout.CENTER);
            setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Book> list, Book value, int index, boolean isSelected, boolean cellHasFocus) {
            lblTitle.setText(value.getTitle());

            String desc = value.getDescription() == null ? "" : value.getDescription();
            // Usamos HTML para permitir saltos de línea y limitar la anchura visual
            if (desc.length() > 150) desc = desc.substring(0, 147) + "...";
            String html = String.format("<html><div style='width:400px;'>%s</div></html>", escapeHtml(desc));
            lblDescription.setText(html);

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            lblTitle.setForeground(getForeground());
            lblDescription.setForeground(getForeground());

            setOpaque(true);
            return this;
        }

        // Método simple para escapar caracteres HTML básicos
        private String escapeHtml(String s) {
            return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("\'", "&#39;");
        }
    }
}
