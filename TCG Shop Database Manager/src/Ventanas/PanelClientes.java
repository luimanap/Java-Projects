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
import Models.Clientesdb;
import Tienda.ProgramaTienda;
import Usuarios.Carta;
import Usuarios.Cliente;

public class PanelClientes extends JPanel {
	private JPanel contentPane;
	private JTextField txt_dni;
	private JTextField txt_nombre;
	private JTextField txt_saldo;
	private DefaultTableModel tableModel;
	private JTable t_listaClientes;
	public static ArrayList<Cliente> clientes = ProgramaTienda.clientes;

	/**
	 * Create the panel.
	 */
	public PanelClientes() {
		contentPane = new JPanel();
		setLayout(null);

		JButton btn_registrar = new JButton("Registar");
		btn_registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = txt_dni.getText();
				String nombre = txt_nombre.getText();
				double saldo = Double.valueOf(txt_saldo.getText());
				Cliente c = new Cliente(dni,nombre,saldo);
				Clientesdb.insertarCliente(c);
				actualizarTablaClientes(Clientesdb.obtenerClientes());
			}
		});
		btn_registrar.setEnabled(false);
		btn_registrar.setBounds(485, 109, 89, 23);
		add(btn_registrar);

		txt_dni = new JTextField();
		txt_dni.setBounds(213, 110, 221, 20);
		add(txt_dni);
		txt_dni.setColumns(10);

		txt_nombre = new JTextField();
		txt_nombre.setColumns(10);
		txt_nombre.setBounds(213, 138, 221, 20);
		add(txt_nombre);

		txt_saldo = new JTextField();
		txt_saldo.setColumns(10);
		txt_saldo.setBounds(213, 166, 221, 20);
		add(txt_saldo);

		tableModel = new DefaultTableModel();
		t_listaClientes = new JTable(tableModel);
		t_listaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		t_listaClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btn_registrar.setEnabled(false);
				DefaultTableModel model = (DefaultTableModel) t_listaClientes.getModel();
				int selectedRowIndex = t_listaClientes.getSelectedRow();
				txt_dni.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txt_nombre.setText(model.getValueAt(selectedRowIndex, 1).toString());
				txt_saldo.setText(model.getValueAt(selectedRowIndex, 2).toString() + "€");
				int rowIndex = selectedRowIndex;
				int columnIndex = 0;
				boolean includeSpacing = true;
				Rectangle cellRect = t_listaClientes.getCellRect(rowIndex, columnIndex, includeSpacing);
				t_listaClientes.scrollRectToVisible(cellRect);
			}
		});
		// t_resultados.setBounds(66, 486, 525, -222);
		// add(t_resultados);

		JScrollPane scroll = new JScrollPane(t_listaClientes);
		scroll.setBounds(25, 265, 609, 158);
		add(scroll);
		String[] headers = { "DNI", "NOMBRE", "SALDO" };
		tableModel.setColumnIdentifiers(headers);
		t_listaClientes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		cargarTablaClientes();

		JLabel lbl_titulo = new JLabel("Gestionar Clientes");
		lbl_titulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lbl_titulo.setBounds(27, 14, 458, 35);
		add(lbl_titulo);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 71, 641, 14);
		add(separator);

		JLabel lbl_icono = new JLabel("");
		lbl_icono.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Tienda/70.png")));
		lbl_icono.setBounds(584, 0, 67, 68);
		add(lbl_icono);

		JLabel lblNewLabel_1 = new JLabel("Registrar, modificar o eliminar clientes");
		lblNewLabel_1.setBounds(37, 40, 547, 14);
		add(lblNewLabel_1);

		JLabel lbl_dni = new JLabel("Dni: ");
		lbl_dni.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_dni.setBounds(137, 113, 66, 14);
		add(lbl_dni);

		JLabel lbl_nombre = new JLabel("Nombre: ");
		lbl_nombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_nombre.setBounds(137, 141, 66, 14);
		add(lbl_nombre);

		JLabel lbl_saldo = new JLabel("Saldo:");
		lbl_saldo.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_saldo.setBounds(137, 169, 66, 14);
		add(lbl_saldo);

		JButton btn_primero = new JButton("<<");
		btn_primero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_registrar.setEnabled(false);
				DefaultTableModel model = (DefaultTableModel) t_listaClientes.getModel();
				t_listaClientes.setRowSelectionInterval(0, 0);
				int selectedRowIndex = t_listaClientes.getSelectedRow();
				txt_dni.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txt_nombre.setText(model.getValueAt(selectedRowIndex, 1).toString());
				txt_saldo.setText(model.getValueAt(selectedRowIndex, 2).toString() + "€");
				int rowIndex = selectedRowIndex;
				int columnIndex = 0;
				boolean includeSpacing = true;
				Rectangle cellRect = t_listaClientes.getCellRect(rowIndex, columnIndex, includeSpacing);
				t_listaClientes.scrollRectToVisible(cellRect);
			}
		});
		btn_primero.setBounds(156, 214, 54, 23);
		add(btn_primero);

		JButton btn_anterior = new JButton("<");
		btn_anterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_registrar.setEnabled(false);
				DefaultTableModel model = (DefaultTableModel) t_listaClientes.getModel();
				int selectedRowIndex = t_listaClientes.getSelectedRow();
				if (selectedRowIndex <= 0) {
					t_listaClientes.setRowSelectionInterval(0, 0);
				} else {
					int filaanterior = selectedRowIndex - 1;
					t_listaClientes.setRowSelectionInterval(filaanterior, filaanterior);

				}
				selectedRowIndex = t_listaClientes.getSelectedRow();
				txt_dni.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txt_nombre.setText(model.getValueAt(selectedRowIndex, 1).toString());
				txt_saldo.setText(model.getValueAt(selectedRowIndex, 2).toString() + "€");
				int rowIndex = selectedRowIndex;
				int columnIndex = 0;
				boolean includeSpacing = true;
				Rectangle cellRect = t_listaClientes.getCellRect(rowIndex, columnIndex, includeSpacing);
				t_listaClientes.scrollRectToVisible(cellRect);
			}
		});
		btn_anterior.setBounds(220, 214, 41, 23);
		add(btn_anterior);

		JButton btn_siguiente = new JButton(">");
		btn_siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_registrar.setEnabled(false);
				DefaultTableModel model = (DefaultTableModel) t_listaClientes.getModel();
				int selectedRowIndex = t_listaClientes.getSelectedRow();
				try {
					if (selectedRowIndex < 0) {
						t_listaClientes.setRowSelectionInterval(0, 0);
					} else if (selectedRowIndex > t_listaClientes.getRowCount()) {
						int ultimafila = t_listaClientes.getRowCount() - 1;
						t_listaClientes.setRowSelectionInterval(ultimafila, ultimafila);
					} else {
						int siguientefila = selectedRowIndex + 1;
						t_listaClientes.setRowSelectionInterval(siguientefila, siguientefila);
					}
				} catch (Exception e1) {
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f, "No existen más filas.", "Error", JOptionPane.WARNING_MESSAGE);
					System.out.println("No existen mas filas");
				}
				selectedRowIndex = t_listaClientes.getSelectedRow();
				txt_dni.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txt_nombre.setText(model.getValueAt(selectedRowIndex, 1).toString());
				txt_saldo.setText(model.getValueAt(selectedRowIndex, 2).toString() + "€");
				int rowIndex = selectedRowIndex;
				int columnIndex = 0;
				boolean includeSpacing = true;
				Rectangle cellRect = t_listaClientes.getCellRect(rowIndex, columnIndex, includeSpacing);
				t_listaClientes.scrollRectToVisible(cellRect);
			}
		});
		btn_siguiente.setBounds(271, 214, 41, 23);
		add(btn_siguiente);

		JButton btn_ultimo = new JButton(">>");
		btn_ultimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_registrar.setEnabled(false);
				DefaultTableModel model = (DefaultTableModel) t_listaClientes.getModel();
				int ultimaFila = t_listaClientes.getRowCount() - 1;
				t_listaClientes.setRowSelectionInterval(ultimaFila, ultimaFila);
				int selectedRowIndex = t_listaClientes.getSelectedRow();
				txt_dni.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txt_nombre.setText(model.getValueAt(selectedRowIndex, 1).toString());
				txt_saldo.setText(model.getValueAt(selectedRowIndex, 2).toString() + "€");
				int rowIndex = selectedRowIndex;
				int columnIndex = 0;
				boolean includeSpacing = true;
				Rectangle cellRect = t_listaClientes.getCellRect(rowIndex, columnIndex, includeSpacing);
				t_listaClientes.scrollRectToVisible(cellRect);
			}
		});
		btn_ultimo.setBounds(322, 214, 54, 23);
		add(btn_ultimo);

		JButton btn_modificar = new JButton("Modificar");
		btn_modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = txt_dni.getText();
				String nombre = txt_nombre.getText();
				String textoSaldo = txt_saldo.getText();
				textoSaldo = textoSaldo.substring(0, textoSaldo.length() - 1);
				double saldo = Double.valueOf(textoSaldo);
				Cliente c = new Cliente(dni, nombre, saldo);
				Clientesdb.editarCliente(c);
				actualizarTablaClientes(Clientesdb.obtenerClientes());
			}
		});
		btn_modificar.setBounds(485, 141, 89, 23);
		add(btn_modificar);

		JButton btn_borrar = new JButton("Borrar");
		btn_borrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = txt_dni.getText();
				String nombre = txt_nombre.getText();
				String textoSaldo = txt_saldo.getText();
				textoSaldo = textoSaldo.substring(0, textoSaldo.length() - 1);
				double saldo = Double.valueOf(textoSaldo);
				Cliente clienteBorrar = new Cliente(dni,nombre,saldo);
				int dialogButton = JOptionPane.showConfirmDialog(contentPane, "Continuar? Esta acción es irreversible.", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (dialogButton == JOptionPane.YES_OPTION) {
					Clientesdb.borrarCliente(clienteBorrar);
				}
				else if(dialogButton == JOptionPane.NO_OPTION){
					JOptionPane.showMessageDialog(null, "La acción no se pudo realizar", "Error", JOptionPane.ERROR_MESSAGE);
				}
				actualizarTablaClientes(Clientesdb.obtenerClientes());
			}
		});
		btn_borrar.setBounds(485, 175, 89, 23);
		add(btn_borrar);

		JButton btn_nuevo = new JButton("Nuevo");
		btn_nuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_dni.setText("");
				txt_nombre.setText("");
				txt_saldo.setText("");
				btn_registrar.setEnabled(true);

			}
		});
		btn_nuevo.setBounds(485, 209, 89, 23);
		add(btn_nuevo);
		JLabel lbl_mensaje = new JLabel("");
		lbl_mensaje.setBounds(441, 218, 193, 14);
		add(lbl_mensaje);
	}

	public void cargarTablaClientes() {
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
}
