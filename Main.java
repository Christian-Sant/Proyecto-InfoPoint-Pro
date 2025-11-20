package ProyectoInfoPointPro;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
		final Splash splash = new Splash();
        splash.setVisible(true);
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
                        Login login = new Login();
                        login.setVisible(true);                    
                    }
                });
            }
        });
        hilo.start();
	}
}
