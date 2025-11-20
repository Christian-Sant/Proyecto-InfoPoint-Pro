package ProyectoInfoPointPro;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class BackOffice extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BackOffice frame = new BackOffice();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BackOffice() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 790, 800);
        setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel infoEmpleado = new JPanel();
		infoEmpleado.setBounds(0, 0, 774, 120);
		contentPane.add(infoEmpleado);
		infoEmpleado.setLayout(null);
		
		JLabel fotoEmpleado = new JLabel("");
		String imagePath = "C:\\Users\\Tarde\\Downloads\\premium_photo-1678197937465-bdbc4ed95815.png";
		 BufferedImage image;
		try {
			image = ImageIO.read(new File(imagePath));
            Image resizedImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    		fotoEmpleado.setIcon(new ImageIcon(resizedImage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fotoEmpleado.setBounds(0, 0, 120, 120);
		infoEmpleado.add(fotoEmpleado);
		
		JLabel infoID = new JLabel("");
		infoID.setBounds(130, 0, 644, 40);
		infoEmpleado.add(infoID);
		
		JLabel infoCargo = new JLabel("");
		infoCargo.setBounds(130, 40, 644, 40);
		infoEmpleado.add(infoCargo);
		
		JLabel infoPersonal = new JLabel("");
		infoPersonal.setBounds(130, 80, 644, 40);
		infoEmpleado.add(infoPersonal);
	}
}
