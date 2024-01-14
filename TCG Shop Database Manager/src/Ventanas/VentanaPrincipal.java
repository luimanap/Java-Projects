package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/Tienda/32.png")));

		PanelBuscar pb = new PanelBuscar();
		PanelCatalogo pc = new PanelCatalogo();
		PanelAcercaDe pa = new PanelAcercaDe();
		PanelFacturas pf = new PanelFacturas();
		PanelClientes pcl = new PanelClientes();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 500);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mn_archivo = new JMenu("Archivo");
		mn_archivo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mn_archivo.setMnemonic('A');
		mn_archivo.setName("");
		menuBar.add(mn_archivo);

		JMenuItem mni_buscar = new JMenuItem("Buscar...");
		mni_buscar.setMnemonic('B');
		mni_buscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mni_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(pb);
				pb.revalidate();
				pb.repaint();
			}
		});

		JMenuItem mntmNewMenuItem = new JMenuItem("Inicio");
		mntmNewMenuItem.setMnemonic('I');
		mntmNewMenuItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(contentPane);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		mn_archivo.add(mntmNewMenuItem);
		mn_archivo.add(mni_buscar);

		JMenuItem mni_salir = new JMenuItem("Salir");
		mni_salir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mni_salir.setMnemonic('S');
		mni_salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenuItem mni_acercade = new JMenuItem("Acerca de");
		mni_acercade.setMnemonic('C');
		mni_acercade.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mni_acercade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(pa);
				pa.revalidate();
				pa.repaint();
			}
		});
		mn_archivo.add(mni_acercade);
		mn_archivo.add(mni_salir);
		
		JMenu mn_admin = new JMenu("Herramientas");
		mn_admin.setMnemonic('H');
		menuBar.add(mn_admin);
		
				JMenu mn_submenuGestionar = new JMenu("Gestión...");
				mn_submenuGestionar.setMnemonic('G');
				mn_admin.add(mn_submenuGestionar);
				mn_submenuGestionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
						JMenuItem mni_gestionarCatalogo = new JMenuItem("Catálogo");
						mni_gestionarCatalogo.setMnemonic('T');
						mni_gestionarCatalogo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						mn_submenuGestionar.add(mni_gestionarCatalogo);
						mni_gestionarCatalogo.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								setContentPane(pc);
								pc.revalidate();
								pc.repaint();
							}
						});
						
								JMenuItem mntmNewMenuItem_5 = new JMenuItem("Facturas");
								mntmNewMenuItem_5.setMnemonic('F');
								mntmNewMenuItem_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
								mntmNewMenuItem_5.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										setContentPane(pf);
										pf.revalidate();
										pc.repaint();
									}
								});
								mn_submenuGestionar.add(mntmNewMenuItem_5);
								
										JMenuItem mntmNewMenuItem_6 = new JMenuItem("Clientes");
										mntmNewMenuItem_6.setMnemonic('C');
										mntmNewMenuItem_6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
										mntmNewMenuItem_6.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												setContentPane(pcl);
												pcl.revalidate();
												pcl.repaint();
											}
										});
										mn_submenuGestionar.add(mntmNewMenuItem_6);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_titulo = new JLabel("TCG Shop Database Manager");
		lbl_titulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lbl_titulo.setBounds(25, 14, 458, 35);
		contentPane.add(lbl_titulo);

		JLabel lbl_bienvenida = new JLabel("Bienvenido, " + System.getProperty("user.name"));
		lbl_bienvenida.setForeground(new Color(0, 0, 0));
		lbl_bienvenida.setBounds(25, 118, 602, 14);
		contentPane.add(lbl_bienvenida);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 72, 641, 14);
		contentPane.add(separator);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Tienda/70.png")));
		lblNewLabel.setBounds(584, 0, 67, 68);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Gestione su base de datos con la ayuda de una interfaz intuitiva");
		lblNewLabel_1.setBounds(35, 40, 422, 21);
		contentPane.add(lblNewLabel_1);
	}
}