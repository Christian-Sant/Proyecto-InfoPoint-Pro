package ProyectoInfoPointPro;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Splash extends JDialog {

    private JProgressBar barra;
    private JLabel lblMensaje;

    public static void main(String[] args) {
        Splash s = new Splash();
        s.setVisible(true);
    }

    public Splash() {

        setTitle("Cargando...");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setModal(true);   // se comporta como una splash falsa
        setLayout(new BorderLayout());

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        // Título
        JLabel lblTitulo = new JLabel("Iniciando la aplicación...", JLabel.CENTER);
        panel.add(lblTitulo, BorderLayout.NORTH);

        // Mensaje dinámico
        lblMensaje = new JLabel("Cargando...", JLabel.CENTER);
        panel.add(lblMensaje, BorderLayout.CENTER);

        // Barra de progreso
        barra = new JProgressBar(0, 100);
        barra.setStringPainted(true);
        panel.add(barra, BorderLayout.SOUTH);

        // Iniciar el hilo
        iniciarCarga();
    }

    private void iniciarCarga() {

        Thread hilo = new Thread() {
            public void run() {

                String[] mensajes = {
                    "Conectando...",
                    "Cargando estilos...",
                    "Preparando interfaz...",
                    "Cargando datos...",
                    "Listo"
                };

                int progreso = 0;
            	int index = 0;
                for (int i = 0; i <= 100; i++) {
                	if(i % 25 == 0) {
                		System.out.println(index);
                        lblMensaje.setText(mensajes[index]);
                        System.out.println(lblMensaje.getText());
                        index++;
                	}
                    barra.setValue(progreso);
                    progreso += 1;

                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                dispose(); // Cerrar splash
                new VentanaPrincipal().setVisible(true);
            }
        };

        hilo.start();
    }

    // Ventana principal simple
    public static class VentanaPrincipal extends JDialog {

        public VentanaPrincipal() {
            setTitle("Ventana Principal");
            setSize(400, 300);
            setLocationRelativeTo(null);
            add(new JLabel("Bienvenido", JLabel.CENTER));
        }
    }
}
