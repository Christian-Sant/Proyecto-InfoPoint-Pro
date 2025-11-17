package ProyectoInfoPointPro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JDialog {
	//CHRISTIAN JAY LAGO
		private static final long serialVersionUID = 1L;
		private final JPanel contentPanel = new JPanel();
		private JTextField textFieldUsuario;
		private JPasswordField passwordField;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			try {
				Login dialog = new Login();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * Create the dialog.
		 */
		public Login() {
			setBounds(100, 100, 450, 300);
			setLocationRelativeTo(null);     
			getContentPane().setLayout(null);
			setTitle("Inicio de Sesión");
			contentPanel.setBounds(0, 0, 434, 232);
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel);
			contentPanel.setLayout(null);
			JLabel lblUsuario = new JLabel("Usuario: ");
			lblUsuario.setForeground(new Color(255, 255, 255));
			lblUsuario.setBounds(84, 79, 89, 14);
			contentPanel.add(lblUsuario);
			
			JLabel lblPassword = new JLabel("Contraseña:");
			lblPassword.setForeground(new Color(255, 255, 255));
			lblPassword.setBounds(84, 131, 89, 14);
			contentPanel.add(lblPassword);
			
			textFieldUsuario = new JTextField();
			textFieldUsuario.setBounds(185, 76, 146, 20);
			contentPanel.add(textFieldUsuario);
			textFieldUsuario.setColumns(10);
			
			passwordField = new JPasswordField();
			passwordField.setBounds(185, 128, 146, 20);
			contentPanel.add(passwordField);
			
			JLabel lblImagen = new JLabel("");
			URL url = getClass().getResource("/ProyectoInfoPointPro/resources/inicio.png");
			System.out.println(url);


			
			lblImagen.setBounds(0, 0, 434, 232);
			contentPanel.add(lblImagen);
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setBounds(0, 228, 434, 33);
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane);
				{
					JButton okButton = new JButton("OK");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(textFieldUsuario.getText().toString().equals("admin") && passwordField.getText().toString().equals("12345")) {
								setVisible(false);
								Login splash = new Login();
								splash.setVisible(true);
								Thread hiloSplash = new Thread(new Runnable() {
								    @Override
								    public void run() {
								        for (int i = 0; i <= 100; i++) {
								            try {
								                Thread.sleep(50); 
								            } catch (InterruptedException ex) {
								                ex.printStackTrace();
								            }
								            /*splash.actualizarProgreso(i);*/
								        }
								        splash.dispose();
								        /*App app = new App();
								        app.setVisible(true);*/
								    }
								});
								hiloSplash.start();
							}
							else {
								JOptionPane.showMessageDialog(null, "RELLENE LOS CAMPOS BIEN", "WARNING", JOptionPane.WARNING_MESSAGE);
							}
						}
					});
					okButton.setActionCommand("OK");
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
				{
					JButton cancelButton = new JButton("Cancel");
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}
			}
		}
	}
