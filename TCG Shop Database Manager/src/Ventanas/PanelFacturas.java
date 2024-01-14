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
import javax.swing.table.DefaultTableModel;

import Models.Facturasdb;
import Tienda.Factura;
import Tienda.ProgramaTienda;

public class PanelFacturas extends JPanel {
	private JPanel contentPane;
	private JTextField txt_id;
	private JTextField txt_concepto;
	private JTextField txt_importe;
	private DefaultTableModel tableModel;
	private JTable t_listaFacturas;
	public static ArrayList<Factura> facturas = ProgramaTienda.facturas;
	private JTextField txt_tipo;

	/**
	 * Create the panel.
	 */
	public PanelFacturas() {
		contentPane = new JPanel();
		setLayout(null);
		
		JButton btn_borrar = new JButton("Borrar");
		btn_borrar.setBounds(485, 175, 89, 23);
		btn_borrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txt_id.getText();
				String concepto = txt_concepto.getText();
				String textoImporte = txt_importe.getText();
				textoImporte = textoImporte.substring(0, textoImporte.length() - 1);
				double precio = Double.valueOf(textoImporte);
				String tipo = txt_tipo.getText();
				Factura facturaBorrar = new Factura(id, concepto, precio, tipo);
				
				
				int dialogButton = JOptionPane.showConfirmDialog(null, "Continuar? Esta acción es irreversible", "Confirmar borrado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (dialogButton == JOptionPane.YES_OPTION) {
					Facturasdb.borrarFactura(facturaBorrar);
				}
				else if(dialogButton == JOptionPane.NO_OPTION){
					JOptionPane.showMessageDialog(null, "La acción no se pudo realizar", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				actualizarTablaFacturas(Facturasdb.obtenerFacturas());
			}
		});
		add(btn_borrar);
		
		txt_id = new JTextField();
		txt_id.setBounds(228, 110, 204, 20);
		add(txt_id);
		txt_id.setColumns(10);
		txt_id.setEditable(false);

		txt_concepto = new JTextField();
		txt_concepto.setColumns(10);
		txt_concepto.setBounds(228, 138, 204, 20);
		add(txt_concepto);

		txt_importe = new JTextField();
		txt_importe.setColumns(10);
		txt_importe.setBounds(228, 166, 204, 20);
		add(txt_importe);

		tableModel = new DefaultTableModel();
		t_listaFacturas = new JTable(tableModel);
		t_listaFacturas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		t_listaFacturas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txt_id.setEditable(false);
				DefaultTableModel model = (DefaultTableModel) t_listaFacturas.getModel();
				int selectedRowIndex = t_listaFacturas.getSelectedRow();
				txt_id.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txt_concepto.setText(model.getValueAt(selectedRowIndex, 1).toString());
				txt_importe.setText(model.getValueAt(selectedRowIndex, 2).toString() + "€");
				txt_tipo.setText(model.getValueAt(selectedRowIndex, 3).toString());
				if (txt_tipo.getText().equals("Luz")) {
					btn_borrar.setEnabled(false);
				}
				else {
					btn_borrar.setEnabled(true);
				}
			}
		});
		// t_resultados.setBounds(66, 486, 525, -222);
		// add(t_resultados);

		JScrollPane scroll = new JScrollPane(t_listaFacturas);
		scroll.setBounds(25, 291, 609, 132);
		add(scroll);
		String[] headers = { "ID", "CONCEPTO", "IMPORTE", "TIPO" };
		tableModel.setColumnIdentifiers(headers);
		t_listaFacturas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		cargarTablaFacturas();

		JLabel lbl_titulo = new JLabel("Gestionar Facturas");
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

		JLabel lbl_subtitulo = new JLabel("Ver, modificar o eliminar facturas");
		lbl_subtitulo.setBounds(37, 40, 547, 14);
		add(lbl_subtitulo);

		JLabel lbl_id = new JLabel("Id:");
		lbl_id.setBounds(137, 113, 66, 14);
		add(lbl_id);

		JLabel lbl_concepto = new JLabel("Concepto:");
		lbl_concepto.setBounds(137, 141, 66, 14);
		add(lbl_concepto);

		JLabel lbl_importe = new JLabel("Importe:");
		lbl_importe.setBounds(137, 169, 66, 14);
		add(lbl_importe);

		JButton btn_primero = new JButton("<<");
		btn_primero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_id.setEditable(false);
				DefaultTableModel model = (DefaultTableModel) t_listaFacturas.getModel();
				t_listaFacturas.setRowSelectionInterval(0, 0);
				int selectedRowIndex = t_listaFacturas.getSelectedRow();
				txt_id.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txt_concepto.setText(model.getValueAt(selectedRowIndex, 1).toString());
				txt_importe.setText(model.getValueAt(selectedRowIndex, 2).toString() + "€");
				txt_tipo.setText(model.getValueAt(selectedRowIndex, 3).toString());
				int rowIndex = selectedRowIndex;
				int columnIndex = 0;
				boolean includeSpacing = true;
				Rectangle cellRect = t_listaFacturas.getCellRect(rowIndex, columnIndex, includeSpacing);
				t_listaFacturas.scrollRectToVisible(cellRect);
				if (txt_tipo.getText().equals("Luz")) {
					btn_borrar.setEnabled(false);
				}
				else {
					btn_borrar.setEnabled(true);
				}
			}
		});
		btn_primero.setBounds(157, 241, 54, 23);
		add(btn_primero);

		JButton btn_anterior = new JButton("<");
		btn_anterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_id.setEditable(false);
				DefaultTableModel model = (DefaultTableModel) t_listaFacturas.getModel();
				int selectedRowIndex = t_listaFacturas.getSelectedRow();
				if (selectedRowIndex <= 0) {
					t_listaFacturas.setRowSelectionInterval(0, 0);
					selectedRowIndex = t_listaFacturas.getSelectedRow();
					txt_id.setText(model.getValueAt(selectedRowIndex, 0).toString());
					txt_concepto.setText(model.getValueAt(selectedRowIndex, 1).toString());
					txt_importe.setText(model.getValueAt(selectedRowIndex, 2).toString() + "€");
					txt_tipo.setText(model.getValueAt(selectedRowIndex, 3).toString());
				} else {
					int filaanterior = selectedRowIndex - 1;
					t_listaFacturas.setRowSelectionInterval(filaanterior, filaanterior);

				}
				selectedRowIndex = t_listaFacturas.getSelectedRow();
				txt_id.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txt_concepto.setText(model.getValueAt(selectedRowIndex, 1).toString());
				txt_importe.setText(model.getValueAt(selectedRowIndex, 2).toString() + "€");
				txt_tipo.setText(model.getValueAt(selectedRowIndex, 3).toString());
				int rowIndex = selectedRowIndex;
				int columnIndex = 0;
				boolean includeSpacing = true;
				Rectangle cellRect = t_listaFacturas.getCellRect(rowIndex, columnIndex, includeSpacing);
				t_listaFacturas.scrollRectToVisible(cellRect);
				if (txt_tipo.getText().equals("Luz")) {
					btn_borrar.setEnabled(false);
				}
				else {
					btn_borrar.setEnabled(true);
				}
			}
		});
		btn_anterior.setBounds(221, 241, 41, 23);
		add(btn_anterior);

		JButton btn_siguiente = new JButton(">");
		btn_siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_id.setEditable(false);
				DefaultTableModel model = (DefaultTableModel) t_listaFacturas.getModel();
				int selectedRowIndex = t_listaFacturas.getSelectedRow();
				try {
					if (selectedRowIndex < 0) {
						t_listaFacturas.setRowSelectionInterval(0, 0);
					} else if (selectedRowIndex > t_listaFacturas.getRowCount() - 1) {
						int ultimafila = t_listaFacturas.getRowCount() - 1;
						t_listaFacturas.setRowSelectionInterval(ultimafila, ultimafila);
					} else {
						int siguientefila = selectedRowIndex + 1;
						t_listaFacturas.setRowSelectionInterval(siguientefila, siguientefila);
					}
				} catch (Exception e1) {
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f, "No existen más filas.", "Error", JOptionPane.WARNING_MESSAGE);
					System.out.println("No existen mas filas");
				}
				selectedRowIndex = t_listaFacturas.getSelectedRow();
				txt_id.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txt_concepto.setText(model.getValueAt(selectedRowIndex, 1).toString());
				txt_importe.setText(model.getValueAt(selectedRowIndex, 2).toString() + "€");
				txt_tipo.setText(model.getValueAt(selectedRowIndex, 3).toString());

				int rowIndex = selectedRowIndex;
				int columnIndex = 0;
				boolean includeSpacing = true;
				Rectangle cellRect = t_listaFacturas.getCellRect(rowIndex, columnIndex, includeSpacing);
				t_listaFacturas.scrollRectToVisible(cellRect);
				if (txt_tipo.getText().equals("Luz")) {
					btn_borrar.setEnabled(false);
				}
				else {
					btn_borrar.setEnabled(true);
				}
			}
		});
		btn_siguiente.setBounds(272, 241, 41, 23);
		add(btn_siguiente);

		JButton btn_ultimo = new JButton(">>");
		btn_ultimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_id.setEditable(false);
				DefaultTableModel model = (DefaultTableModel) t_listaFacturas.getModel();
				int ultimaFila = t_listaFacturas.getRowCount() - 1;
				t_listaFacturas.setRowSelectionInterval(ultimaFila, ultimaFila);
				int selectedRowIndex = t_listaFacturas.getSelectedRow();
				txt_id.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txt_concepto.setText(model.getValueAt(selectedRowIndex, 1).toString());
				txt_importe.setText(model.getValueAt(selectedRowIndex, 2).toString() + "€");
				txt_tipo.setText(model.getValueAt(selectedRowIndex, 3).toString());
				int rowIndex = selectedRowIndex;
				int columnIndex = 0;
				boolean includeSpacing = true;
				Rectangle cellRect = t_listaFacturas.getCellRect(rowIndex, columnIndex, includeSpacing);
				t_listaFacturas.scrollRectToVisible(cellRect);
				if (txt_tipo.getText().equals("Luz")) {
					btn_borrar.setEnabled(false);
				}
				else {
					btn_borrar.setEnabled(true);
				}
			}
		});
		btn_ultimo.setBounds(323, 241, 54, 23);
		add(btn_ultimo);

		JButton btn_modificar = new JButton("Modificar");
		btn_modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txt_id.getText();
				String concepto = txt_concepto.getText();
				String textoImporte = txt_importe.getText();
				textoImporte = textoImporte.substring(0, textoImporte.length() - 1);
				double precio = Double.valueOf(textoImporte);
				String tipo = txt_tipo.getText();
				Factura f = new Factura(id, concepto, precio, tipo);
				Facturasdb.editarFactura(f);
				actualizarTablaFacturas(Facturasdb.obtenerFacturas());
			}
		});
		btn_modificar.setBounds(485, 141, 89, 23);
		add(btn_modificar);

		

		txt_tipo = new JTextField();
		txt_tipo.setColumns(10);
		txt_tipo.setBounds(228, 197, 204, 20);
		add(txt_tipo);

		JLabel lbl_tipo = new JLabel("Tipo:");
		lbl_tipo.setBounds(137, 200, 66, 14);
		add(lbl_tipo);

		JButton btn_añadir = new JButton("Añadir");
		btn_añadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txt_id.getText();
				String concepto = txt_concepto.getText();
				double importe = Double.valueOf(txt_importe.getText());
				String tipo = txt_tipo.getText();
				Factura f = new Factura(id, concepto, importe, tipo);
				Facturasdb.insertarFactura(f);
				actualizarTablaFacturas(Facturasdb.obtenerFacturas());
			}
		});
		btn_añadir.setEnabled(false);
		btn_añadir.setBounds(485, 110, 89, 23);
			
		add(btn_añadir);

		JButton btn_nuevo = new JButton("Nuevo");
		btn_nuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_id.setText("");
				txt_concepto.setText("");
				txt_importe.setText("");
				txt_tipo.setText("Compra");
				txt_tipo.setEditable(false);
				txt_id.setEditable(true);
				btn_añadir.setEnabled(true);
			}
		});
		btn_nuevo.setBounds(485, 210, 89, 23);
		add(btn_nuevo);
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
}
