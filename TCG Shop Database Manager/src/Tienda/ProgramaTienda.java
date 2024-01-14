package Tienda;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;

import Models.Cartasdb;
import Models.Clientesdb;
import Models.Facturasdb;
import Usuarios.Carta;
import Usuarios.Cliente;
import Ventanas.Login;

public class ProgramaTienda {

	public static Scanner sc = new Scanner(System.in);
	public static ArrayList<Carta> catalogo = Cartasdb.obtenerCartas();
	public static ArrayList<Factura> facturas = Facturasdb.obtenerFacturas();
	public static ArrayList<Cliente> clientes = Clientesdb.obtenerClientes();

	public static void main(String[] args) {

		

		try {

			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			Properties props = new Properties();
			props.put("logoString", "TCGSDM");
			props.put("menuSelectionBackgroundColorDark", "229 110 255");
			props.put("menuSelectionBackgroundColorLight", "229 110 255");
			props.put("toolbarDecorated", "on");
			props.put("textAntiAliasingMode", "grey");
			props.put("dynamicLayout", "on");
			GraphiteLookAndFeel.setCurrentTheme(props);
			// LunaLookAndFeel.setCurrentTheme(props);
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			// UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
			// UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
			// UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
			UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Login frame = new Login();
		frame.setTitle("TCG Shop Database Manager");
		frame.setVisible(true);
		
	}
}