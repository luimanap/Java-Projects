package Ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Tienda.Factura;
import Tienda.ProgramaTienda;
import Usuarios.Carta;
import Usuarios.Cliente;

public class PanelBuscar extends JPanel {
	private static DefaultTableModel tableModel;
	private JTextField txt_buscador;
	private JTable t_resultados;
	private static JPanel contentPane;
	private JRadioButton rdb_clientes;
	private JRadioButton rdb_facturas;
	private JRadioButton rdb_cartas;
	public static ArrayList<Carta> catalogo = ProgramaTienda.catalogo;
	private ArrayList<Factura> facturas = ProgramaTienda.facturas;
	private ArrayList<Cliente> clientes = ProgramaTienda.clientes;
	

	/**
	 * Create the panel.
	 */
	public PanelBuscar() {
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setLayout(null);

		tableModel = new DefaultTableModel();
		t_resultados = new JTable(tableModel);
		// t_resultados.setBounds(66, 486, 525, -222);
		// add(t_resultados);

		JScrollPane scroll = new JScrollPane(t_resultados);
		scroll.setBounds(25, 198, 609, 225);
		add(scroll);

		txt_buscador = new JTextField();
		txt_buscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ejecutarBusqueda();
				}
			}
		});
		txt_buscador.setBounds(136, 165, 329, 20);
		add(txt_buscador);
		txt_buscador.setColumns(10);

		rdb_clientes = new JRadioButton("Clientes");
		rdb_clientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] headers = { "DNI", "NOMBRE", "SALDO" };
				tableModel.setColumnIdentifiers(headers);
				t_resultados.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				cargarTablaClientes();
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		rdb_clientes.setBounds(70, 121, 95, 23);
		add(rdb_clientes);

		rdb_facturas = new JRadioButton("Facturas");
		rdb_facturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] headers = { "ID", "CONCEPTO", "IMPORTE", "TIPO" };
				tableModel.setColumnIdentifiers(headers);
				t_resultados.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				cargarTablaFacturas();
				contentPane.revalidate();
				contentPane.repaint();
			}
		});

		rdb_facturas.setBounds(164, 121, 95, 23);
		add(rdb_facturas);

		rdb_cartas = new JRadioButton("Cartas");
		rdb_cartas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] headers = { "NOMBRE", "STOCK", "PRECIO" };
				tableModel.setColumnIdentifiers(headers);
				t_resultados.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				cargarTablaCartas();
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		rdb_cartas.setBounds(261, 121, 109, 23);
		add(rdb_cartas);

		JButton btn_buscar = new JButton("Buscar");
		btn_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ejecutarBusqueda();
			}

		});
		btn_buscar.setBounds(487, 164, 77, 23);
		add(btn_buscar);

		JLabel lbl_titulo = new JLabel("Buscar");
		lbl_titulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lbl_titulo.setBounds(27, 14, 458, 35);
		add(lbl_titulo);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 73, 641, 14);
		add(separator);

		JLabel lbl_icono = new JLabel("");
		lbl_icono.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Tienda/70.png")));
		lbl_icono.setBounds(584, 0, 67, 68);
		add(lbl_icono);

		JLabel lblNewLabel_1 = new JLabel("Elija un tipo de datos y realice una búsqueda");
		lblNewLabel_1.setBounds(37, 43, 346, 14);
		add(lblNewLabel_1);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdb_cartas);
		bg.add(rdb_facturas);
		bg.add(rdb_clientes);

		JLabel lblNewLabel_2 = new JLabel("¿Que quieres buscar?");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNewLabel_2.setBounds(37, 100, 272, 14);
		add(lblNewLabel_2);

	}

	public static void cargarTablaCartas() {
		tableModel.getDataVector().removeAllElements();
		for (Carta carta : catalogo) {
			String nombre = carta.getNombre();
			int stock = carta.getStock();
			double precio = carta.getPrecio();
			Object[] data = { nombre, stock, precio };
			tableModel.addRow(data);
		}
		contentPane.revalidate();
		contentPane.repaint();
	}

	private void cargarTablaFacturas() {
		tableModel.getDataVector().removeAllElements();
		for (Factura f : facturas) {
			String id = f.getIdFactura();
			String concepto = f.getConcepto();
			double importe = f.getImporte();
			String tipo = f.getTipo();
			Object[] data = { id, concepto, importe, tipo };
			tableModel.addRow(data);
		}
		contentPane.revalidate();
		contentPane.repaint();
	}

	private void cargarTablaClientes() {
		tableModel.getDataVector().removeAllElements();
		for (Cliente c : clientes) {
			String dni = c.getId();
			String nombre = c.getNombre();
			double saldo = c.getSaldo();
			Object[] data = { dni, nombre, saldo };
			tableModel.addRow(data);
		}
		contentPane.revalidate();
		contentPane.repaint();
	}

	private void actualizarTablaClientes(ArrayList<Cliente> clientesEncontrados) {
		/*
		 * for(int i=0; i< tableModel.getRowCount(); i++) { tableModel.removeRow(i); }
		 */
		tableModel.getDataVector().removeAllElements();
		for (Cliente c : clientesEncontrados) {
			String dni = c.getId();
			String nombre = c.getNombre();
			double saldo = c.getSaldo();
			Object[] data = { dni, nombre, saldo };
			tableModel.addRow(data);
		}
		contentPane.revalidate();
		contentPane.repaint();
	}

	private void actualizarTablaFacturas(ArrayList<Factura> facturasEncontradas) {
		tableModel.getDataVector().removeAllElements();
		for (Factura f : facturasEncontradas) {
			String id = f.getIdFactura();
			String concepto = f.getConcepto();
			double importe = f.getImporte();
			String tipo = f.getTipo();
			Object[] data = { id, concepto, importe, tipo };
			tableModel.addRow(data);
		}
		contentPane.revalidate();
		contentPane.repaint();
	}

	private void actualizarTablaCartas(ArrayList<Carta> cartasEncontradas) {
		tableModel.getDataVector().removeAllElements();
		for (Carta ca : cartasEncontradas) {
			String nombre = ca.getNombre();
			int stock = ca.getStock();
			double precio = ca.getPrecio();
			Object[] data = { nombre, stock, precio };
			tableModel.addRow(data);
		}
		contentPane.revalidate();
		contentPane.repaint();
	}

	private void ejecutarBusqueda() {
		if (rdb_cartas.isSelected()) {
			ArrayList<Carta> cartasEncontradas = new ArrayList<Carta>();
			String nombre = txt_buscador.getText();
			nombre = nombre.trim();
			nombre = nombre.toLowerCase();
			for (Carta carta : catalogo) {
				if (carta.getNombre().contains(nombre)) {
					cartasEncontradas.add(carta);
				}
			}

			actualizarTablaCartas(cartasEncontradas);
		} else if (rdb_facturas.isSelected()) {
			ArrayList<Factura> facturasEncontradas = new ArrayList<Factura>();
			String concepto = txt_buscador.getText();
			concepto = concepto.trim();
			concepto = concepto.toLowerCase();
			for (Factura factura : facturas) {
				if (factura.getConcepto().contains(concepto)) {
					facturasEncontradas.add(factura);
				}
			}

			actualizarTablaFacturas(facturasEncontradas);
		} else if (rdb_clientes.isSelected()) {
			ArrayList<Cliente> clientesEncontrados = new ArrayList<Cliente>();
			String busqueda = txt_buscador.getText();
			busqueda = busqueda.trim();
			busqueda = busqueda.toLowerCase();
			for (Cliente cliente : clientes) {
				if (cliente.getNombre().contains(busqueda) || cliente.getId().contains(busqueda)) {
					clientesEncontrados.add(cliente);
				}
			}
			actualizarTablaClientes(clientesEncontrados);
		}
	}

	
	
}
