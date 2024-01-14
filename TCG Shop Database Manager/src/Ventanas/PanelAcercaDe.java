package Ventanas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class PanelAcercaDe extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelAcercaDe() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("TCG Shop Database Manager");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(144, 140, 357, 20);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Desarrollado por Luis Vaquero Gil");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(144, 333, 357, 14);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("2023 © Todos los derechos reservados ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(144, 358, 357, 14);
		add(lblNewLabel_2);

		JTextArea txt_catch = new JTextArea();
		txt_catch.setBorder(null);
		txt_catch.setForeground(new Color(0, 0, 0));
		txt_catch.setBackground(Color.DARK_GRAY);
		txt_catch.setEditable(false);
		txt_catch.setFont(new Font("Tahoma", Font.ITALIC, 13));
		txt_catch.setText("Este software está diseñado específicamente para ayudar a\r\nlos dueños de tiendas de cartas coleccionables a mantener un seguimiento \r\neficiente de sus inventarios y ventas.\r\n\r\nCon esta herramienta, podrás gestionar de manera efectiva y \r\nfácil todo tu stock de cartas, conocer la \r\ndisponibilidad de tus productos en tiempo real y controlar \r\nlas ventas y transacciones de manera precisa. ");
		txt_catch.setBounds(144, 182, 357, 140);
		txt_catch.setOpaque(false);
		txt_catch.setBackground(new Color(0, 0, 0, 0));
		add(txt_catch);

		JLabel lbl_icono = new JLabel("");
		lbl_icono.setIcon(new ImageIcon(PanelAcercaDe.class.getResource("/Tienda/96.png")));
		lbl_icono.setBounds(277, 33, 96, 96);
		add(lbl_icono);
	}
}
