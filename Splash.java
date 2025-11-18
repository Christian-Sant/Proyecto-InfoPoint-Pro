package ProyectoInfoPointPro;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Splash extends JDialog {

    private static final long serialVersionUID = 1L;
    private JProgressBar barra;
    private JLabel lblMensaje;
    private int index = 0; // índice para los mensajes dinámicos

    private final String[] mensajes = {
        "Conectando...",
        "Cargando estilos...",
        "Preparando interfaz...",
        "Cargando datos...",
        "Listo"
    };

    public Splash() {
        setTitle("Cargando...");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setModal(false);  // No bloquea la ejecución
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        JLabel lblTitulo = new JLabel("Iniciando la aplicación...", JLabel.CENTER);
        panel.add(lblTitulo, BorderLayout.NORTH);

        lblMensaje = new JLabel("Cargando...", JLabel.CENTER);
        panel.add(lblMensaje, BorderLayout.CENTER);

        barra = new JProgressBar(0, 100);
        barra.setStringPainted(true);
        panel.add(barra, BorderLayout.SOUTH);
    }

    public void iniciarCarga(int porcentaje) {
        barra.setValue(porcentaje);

        // Cambiar mensaje cada 25%
        if (porcentaje % 25 == 0 && index < mensajes.length) {
            lblMensaje.setText(mensajes[index]);
            index++;
        }
    }
}
