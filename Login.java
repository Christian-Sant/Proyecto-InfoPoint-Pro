package ProyectoInfoPointPro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.net.URL;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTextField textFieldUsuario;
    private JPasswordField passwordField;
    private boolean admin;
    
    private String admins[] = {"1","2","3"};
    private JTextField textFieldID;
    private JLabel lblId;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                	Login dialog = new Login();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
    	try {
            if (UIManager.getLookAndFeel().getName().equals("Metal")) {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } else {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        setTitle("Inicio de Sesión");
        getContentPane().setLayout(null);
        admin = false;
        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(0, 0, 434, 232);
        contentPanel.setLayout(null);
        getContentPane().add(contentPanel);

        JLabel lblUsuario = new JLabel("Usuario: ");
        lblUsuario.setForeground(Color.BLACK);
        lblUsuario.setBounds(85, 111, 89, 14);
        contentPanel.add(lblUsuario);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBounds(85, 154, 89, 14);
        contentPanel.add(lblPassword);

        textFieldUsuario = new JTextField();
        textFieldUsuario.setBounds(184, 103, 146, 30);
        contentPanel.add(textFieldUsuario);
        textFieldUsuario.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(184, 146, 146, 30);
        contentPanel.add(passwordField);

        // Imagen de fondo (opcional)
        JLabel lblImagen = new JLabel("");
        URL url = getClass().getResource("/ProyectoInfoPointPro/resources/inicio.png");
        if (url != null) {
            lblImagen.setIcon(new ImageIcon(url));
        }
        lblImagen.setBounds(0, 0, 434, 232);
        contentPanel.add(lblImagen);
        
        JButton btnAdmin = new JButton("Administrador");
        btnAdmin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		lblId.setVisible(true);
        		textFieldID.setVisible(true);
        	}
        });
        btnAdmin.setBounds(274, 0, 160, 23);
        contentPanel.add(btnAdmin);
        
        lblId = new JLabel("ID: ");
        lblId.setForeground(Color.BLACK);
        lblId.setBounds(85, 70, 89, 14);
        contentPanel.add(lblId);
        lblId.setVisible(false);
        
        textFieldID = new JTextField();
        textFieldID.setColumns(10);
        textFieldID.setBounds(184, 62, 146, 30);
        contentPanel.add(textFieldID);
        textFieldID.setVisible(false);

        // Panel de botones
        JPanel buttonPane = new JPanel();
        buttonPane.setBounds(0, 228, 434, 33);
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                loginUsuario();
            }
        });
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose(); // cerrar login
            }
        });
        buttonPane.add(cancelButton);
    }

    private void loginUsuario() {
        String usuario = textFieldUsuario.getText();
        String contrasena = new String(passwordField.getPassword());

        if (usuario.equals("usuario") && contrasena.equals("12345")) {
            setVisible(false); // cerrar login

            final Splash splash = new Splash();
            splash.setVisible(true);

            // Hilo para animar barra de progreso
            Thread hilo = new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i <= 100; i++) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }

                        final int porcentaje = i;
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                splash.iniciarCarga(porcentaje);
                            }
                        });
                    }

                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            splash.dispose(); 
                            // Abrir tu aplicación principal
                            if(admin) {
                            	BackOffice panelAdministrador = new BackOffice();
                            	panelAdministrador.setVisible(true);
                            	JOptionPane.showMessageDialog(null, "Carga completada. Aquí abriría el panel Admin.");
                            }
                            else {
                            	AplicacionBibliotecaMunicipal app = new AplicacionBibliotecaMunicipal();
                                app.setVisible(true);
                                JOptionPane.showMessageDialog(null, "Carga completada. Aquí abriría la App.");
                            }
                        }
                    });
                }
            });
            hilo.start();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
