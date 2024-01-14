package Ventanas;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Models.Cartasdb;
import Tienda.ProgramaTienda;
import Usuarios.Carta;

public class PanelCatalogo extends JPanel {
	private JPanel contentPane;
	private JTextField txt_nombre;
	private JTextField txt_stock;
	private JTextField txt_precio;
	private DefaultTableModel tableModel;
	private JTable t_listaCatalogo;
	public static ArrayList<Carta> catalogo = ProgramaTienda.catalogo;

	/**
	 * Create the panel.
	 */
	public PanelCatalogo() {
		contentPane = new JPanel();
		setLayout(null);

		JButton btn_añadir = new JButton("Añadir");
		btn_añadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txt_nombre.getText();
				int stock = Integer.valueOf(txt_stock.getText());
				String textoPrecio = txt_precio.getText();
				textoPrecio = textoPrecio.substring(0, textoPrecio.length() - 1);
				double precio = Double.valueOf(textoPrecio);
				Carta cartaAñadir = new Carta(nombre, stock, precio);
				Cartasdb.insertarCarta(cartaAñadir);
				actualizarTablaCartas(Cartasdb.obtenerCartas());
			}
		});
		btn_añadir.setEnabled(false);
		btn_añadir.setBounds(485, 109, 89, 23);
		add(btn_añadir);

		txt_nombre = new JTextField();
		txt_nombre.setBounds(213, 110, 221, 20);
		add(txt_nombre);
		txt_nombre.setColumns(10);

		txt_stock = new JTextField();
		txt_stock.setColumns(10);
		txt_stock.setBounds(213, 138, 117, 20);
		add(txt_stock);

		txt_precio = new JTextField();
		txt_precio.setHorizontalAlignment(SwingConstants.LEFT);
		txt_precio.setColumns(10);
		txt_precio.setBounds(213, 166, 221, 20);
		add(txt_precio);

		tableModel = new DefaultTableModel();
		t_listaCatalogo = new JTable(tableModel);
		t_listaCatalogo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		t_listaCatalogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btn_añadir.setEnabled(false);
				DefaultTableModel model = (DefaultTableModel) t_listaCatalogo.getModel();
				int selectedRowIndex = t_listaCatalogo.getSelectedRow();
				txt_nombre.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txt_stock.setText(model.getValueAt(selectedRowIndex, 1).toString());
				txt_precio.setText(model.getValueAt(selectedRowIndex, 2).toString() + " €");
				
			}
		});
		// t_resultados.setBounds(66, 486, 525, -222);
		// add(t_resultados);

		JScrollPane scroll = new JScrollPane(t_listaCatalogo);
		scroll.setBounds(25, 265, 609, 158);
		add(scroll);
		String[] headers = { "NOMBRE", "STOCK", "PRECIO" };
		tableModel.setColumnIdentifiers(headers);
		t_listaCatalogo.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		cargarTablaCartas();

		JLabel lbl_titulo = new JLabel("Gestionar Catálogo");
		lbl_titulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lbl_titulo.setBounds(27, 14, 458, 35);
		add(lbl_titulo);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 71, 641, 14);
		add(separator);

		JLabel lblNewLabel_1 = new JLabel("Añadir, modificar o quitar cartas del catálogo");
		lblNewLabel_1.setBounds(37, 40, 547, 20);
		add(lblNewLabel_1);

		JLabel lbl_nombre = new JLabel("Nombre: ");
		lbl_nombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_nombre.setBounds(137, 113, 66, 14);
		add(lbl_nombre);

		JLabel lbl_stock = new JLabel("Stock: ");
		lbl_stock.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_stock.setBounds(137, 141, 66, 14);
		add(lbl_stock);

		JLabel lbl_precio = new JLabel("Precio: ");
		lbl_precio.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_precio.setBounds(137, 169, 66, 14);
		add(lbl_precio);

		JButton btn_primero = new JButton("<<");
		btn_primero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_añadir.setEnabled(false);
				DefaultTableModel model = (DefaultTableModel) t_listaCatalogo.getModel();
				t_listaCatalogo.setRowSelectionInterval(0, 0);
				int selectedRowIndex = t_listaCatalogo.getSelectedRow();
				int rowIndex = selectedRowIndex;
				int columnIndex = 0;
				boolean includeSpacing = true;
				Rectangle cellRect = t_listaCatalogo.getCellRect(rowIndex, columnIndex, includeSpacing);
				t_listaCatalogo.scrollRectToVisible(cellRect);
				txt_nombre.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txt_stock.setText(model.getValueAt(selectedRowIndex, 1).toString());
				txt_precio.setText(model.getValueAt(selectedRowIndex, 2).toString() + " €");
			}
		});
		btn_primero.setBounds(162, 209, 54, 23);
		add(btn_primero);

		JButton btnNewButton_1 = new JButton("<");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_añadir.setEnabled(false);
				DefaultTableModel model = (DefaultTableModel) t_listaCatalogo.getModel();
				int selectedRowIndex = t_listaCatalogo.getSelectedRow();
				if (selectedRowIndex <= 0) {
					t_listaCatalogo.setRowSelectionInterval(0, 0);
					selectedRowIndex = t_listaCatalogo.getSelectedRow();
					txt_nombre.setText(model.getValueAt(selectedRowIndex, 0).toString());
					txt_stock.setText(model.getValueAt(selectedRowIndex, 1).toString());
					txt_precio.setText(model.getValueAt(selectedRowIndex, 2).toString() + " €");
				} else {
					int filaanterior = selectedRowIndex - 1;
					t_listaCatalogo.setRowSelectionInterval(filaanterior, filaanterior);

				}
				selectedRowIndex = t_listaCatalogo.getSelectedRow();
				txt_nombre.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txt_stock.setText(model.getValueAt(selectedRowIndex, 1).toString());
				txt_precio.setText(model.getValueAt(selectedRowIndex, 2).toString() + " €");
				int rowIndex = selectedRowIndex;
				int columnIndex = 0;
				boolean includeSpacing = true;
				Rectangle cellRect = t_listaCatalogo.getCellRect(rowIndex, columnIndex, includeSpacing);
				t_listaCatalogo.scrollRectToVisible(cellRect);
			}
		});
		btnNewButton_1.setBounds(226, 209, 41, 23);
		add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton(">");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_añadir.setEnabled(false);
				DefaultTableModel model = (DefaultTableModel) t_listaCatalogo.getModel();
				int selectedRowIndex = t_listaCatalogo.getSelectedRow();
				try {
					if (selectedRowIndex < 0) {
						t_listaCatalogo.setRowSelectionInterval(0, 0);
					} else if (selectedRowIndex >= t_listaCatalogo.getRowCount()) {
						int ultimafila = t_listaCatalogo.getRowCount() - 1;
						t_listaCatalogo.setRowSelectionInterval(ultimafila, ultimafila);
					} else {
						int siguientefila = selectedRowIndex + 1;
						t_listaCatalogo.setRowSelectionInterval(siguientefila, siguientefila);
					}
				} catch (Exception e1) {
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f, "No existen más filas.", "Error", JOptionPane.WARNING_MESSAGE);
					System.out.println("No existen mas filas");
				}
				selectedRowIndex = t_listaCatalogo.getSelectedRow();
				txt_nombre.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txt_stock.setText(model.getValueAt(selectedRowIndex, 1).toString());
				txt_precio.setText(model.getValueAt(selectedRowIndex, 2).toString() + " €");
				int rowIndex = selectedRowIndex;
				int columnIndex = 0;
				boolean includeSpacing = true;
				Rectangle cellRect = t_listaCatalogo.getCellRect(rowIndex, columnIndex, includeSpacing);
				t_listaCatalogo.scrollRectToVisible(cellRect);
			}
		});
		btnNewButton_2.setBounds(277, 209, 41, 23);
		add(btnNewButton_2);

		JButton btn_ultimo = new JButton(">>");
		btn_ultimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_añadir.setEnabled(false);
				DefaultTableModel model = (DefaultTableModel) t_listaCatalogo.getModel();
				int ultimaFila = t_listaCatalogo.getRowCount() - 1;
				t_listaCatalogo.setRowSelectionInterval(ultimaFila, ultimaFila);
				int selectedRowIndex = t_listaCatalogo.getSelectedRow();
				int rowIndex = selectedRowIndex;
				int columnIndex = 0;
				boolean includeSpacing = true;
				Rectangle cellRect = t_listaCatalogo.getCellRect(rowIndex, columnIndex, includeSpacing);
				t_listaCatalogo.scrollRectToVisible(cellRect);
				txt_nombre.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txt_stock.setText(model.getValueAt(selectedRowIndex, 1).toString());
				txt_precio.setText(model.getValueAt(selectedRowIndex, 2).toString() + " €");
			}
		});
		btn_ultimo.setBounds(328, 209, 54, 23);
		add(btn_ultimo);

		JButton btn_modificar = new JButton("Modificar");
		btn_modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txt_nombre.getText();
				String textoPrecio = txt_precio.getText();
				textoPrecio = textoPrecio.substring(0, textoPrecio.length() - 1);
				double precio = Double.valueOf(textoPrecio);
				int stock = Integer.valueOf(txt_stock.getText());
				Carta c = new Carta(nombre, stock, precio);
				Cartasdb.editarCarta(c);
				actualizarTablaCartas(Cartasdb.obtenerCartas());
			}
		});
		btn_modificar.setBounds(485, 141, 89, 23);
		add(btn_modificar);

		JButton btn_borrar = new JButton("Borrar");
		btn_borrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txt_nombre.getText();
				int stock = Integer.valueOf(txt_stock.getText());
				String textoPrecio = txt_precio.getText();
				textoPrecio = textoPrecio.substring(0, textoPrecio.length() - 1);
				double precio = Double.valueOf(textoPrecio);
				Carta cartaBorrar = new Carta(nombre, stock, precio);
				
				int dialogButton = JOptionPane.showConfirmDialog(null, "Continuar? Esta acción es irreversible.", "Confirmar", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
				if (dialogButton == JOptionPane.YES_OPTION) {
					Cartasdb.borrarCarta(cartaBorrar);
				}
				
				else if(dialogButton == JOptionPane.NO_OPTION){
					JOptionPane.showMessageDialog(null, "La acción no se pudo realizar", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				actualizarTablaCartas(Cartasdb.obtenerCartas());
			}
		});
		btn_borrar.setBounds(485, 175, 89, 23);
		add(btn_borrar);

		JButton btn_nuevo = new JButton("Nuevo");
		btn_nuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_nombre.setText("");
				txt_stock.setText("");
				txt_precio.setText("");
				btn_añadir.setEnabled(true);
			}
		});
		btn_nuevo.setBounds(485, 209, 89, 23);
		add(btn_nuevo);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Tienda/70.png")));
		lblNewLabel.setBounds(584, 0, 67, 68);
		add(lblNewLabel);

		JButton btnSumar = new JButton("+");
		btnSumar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Aumentar el stock de la carta en 1
				int stock = Integer.valueOf(txt_stock.getText());
				stock++;
				txt_stock.setText(String.valueOf(stock));
			}
		});
		btnSumar.setBounds(340, 137, 42, 23);
		add(btnSumar);

		JButton btnRestar = new JButton("-");
		btnRestar.setBounds(392, 137, 42, 23);
		btnRestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Reducir el stock de la carta en 1
				int stock = Integer.valueOf(txt_stock.getText());
				stock--;
				txt_stock.setText(String.valueOf(stock));
			}
		});
		add(btnRestar);
	}

	public void cargarTablaCartas() {
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
}