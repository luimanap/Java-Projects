package Ventanas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.sql.Statement;

import Models.ConfiguracionDB;

import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txt_user;
	private JPasswordField txt_pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/Tienda/32.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 277, 199);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_titulo = new JLabel("Iniciar Sesión");
		lbl_titulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lbl_titulo.setBounds(20, 11, 111, 25);
		contentPane.add(lbl_titulo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 47, 241, 2);
		contentPane.add(separator);
		
		txt_user = new JTextField();
		txt_user.setBounds(94, 60, 157, 20);
		contentPane.add(txt_user);
		txt_user.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setBounds(20, 63, 64, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setBounds(20, 94, 64, 14);
		contentPane.add(lblNewLabel_1_1);
		
		
		txt_pass = new JPasswordField();
		txt_pass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ejecutarLogin();
				}
			}

			
		});
		txt_pass.setColumns(10);
		txt_pass.setBounds(94, 91, 157, 20);
		contentPane.add(txt_pass);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ejecutarLogin();
			}
		});
		btnNewButton.setBounds(162, 122, 89, 23);
		contentPane.add(btnNewButton);
		
	
	}
	public void ejecutarLogin() {
		ResultSet resultado;
		PreparedStatement sentencia = null;
		String u = txt_user.getText();
		String p = txt_pass.getText();
		Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
		try {
			String ordensql = "SELECT * FROM usuarios WHERE user like ? and pass like ?";
			sentencia = conexion.prepareStatement(ordensql);
			String user = u;
			String pass = p;
			sentencia.setString(1, user);
			sentencia.setString(2,pass);
			resultado = sentencia.executeQuery();
			
			try {
				
				if(resultado.next()) {//si devuelve 1 fila lanza el programa
					VentanaPrincipal vp = new VentanaPrincipal();
					generarBD();
					dispose();
					vp.setTitle("TCG Shop Database Manager");
					vp.setVisible(true);
						
				}
				else{ //si no devuelve 1 fila canta el error
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos. Inténtelo de nuevo", "Error", JOptionPane.WARNING_MESSAGE);
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			resultado.close();
			sentencia.close();
			conexion.close();
		} catch (SQLException e1) {
			e1.printStackTrace();	
		}
		
	}
	public static boolean generarBD() {
		Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
		if (conexion == null) {
			return false;
		}
		try {
				CallableStatement sentencia = conexion.prepareCall("{call generar_base_de_datos()}");
	            sentencia.executeQuery();
				sentencia.close();
				conexion.close();
				
	            System.out.println("Base de datos generada correctamente.");

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
		return false;
	}
}
