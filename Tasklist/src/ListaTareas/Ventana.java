package ListaTareas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField textField;
	private static JList<String> list;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private static DefaultListModel<String> model = new DefaultListModel<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventana() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 334, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					if (!textField.getText().equalsIgnoreCase("")) {
						añadirTarea();
					}
					break;
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
					break;
				}
			}
		});
		textField.setBounds(10, 11, 182, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		list = new JList<String>();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(10, 42, 300, 174);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(model);
		contentPane.add(list);

		btnNewButton_1 = new JButton("Complete Task");
		btnNewButton_1.setMnemonic('C');
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				completeTask();

			}
		});
		btnNewButton_1.setBounds(10, 227, 145, 23);
		contentPane.add(btnNewButton_1);

		btnNewButton_2 = new JButton("Delete Task");
		btnNewButton_2.setMnemonic('D');
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int item = list.getSelectedIndex();
				if (item >= 0) {
					model.remove(item);
				} else {
					JOptionPane.showMessageDialog(null, "Selected item not found. Add a task or select one", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_2.setBounds(165, 227, 145, 23);
		contentPane.add(btnNewButton_2);

		JButton btn_addtask = new JButton("Add Task");
		btn_addtask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				añadirTarea();
			}
		});
		btn_addtask.setBounds(202, 10, 108, 23);
		contentPane.add(btn_addtask);

		JButton btnNewButton_1_1 = new JButton("Delete All Tasks");
		btnNewButton_1_1.setMnemonic('X');
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int item = list.getSelectedIndex();
				if (item > 0) {
					model.clear();
				} else {
					JOptionPane.showMessageDialog(null, "Tasklist is already emptyed", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_1_1.setBounds(10, 255, 300, 23);
		contentPane.add(btnNewButton_1_1);
	}

	private void completeTask() {
		int item = list.getSelectedIndex();
		if (item >= 0) {
			String task = (String) model.getElementAt(item);
			String tickchar = "\u2713";
			if (!task.contains(tickchar)) {
				task = "<html>" + tickchar + "  " + "<strike style=\"background-color:green\">" + task
						+ "</strike></html>";
				model.setElementAt(task, item);
			} else {
				model.setElementAt(task.replace(tickchar, "")
						.replace("<html>", "")
						.replace("</html>", "")
						.replace("<i>", "")
						.replace("<i>", "")
						.replace("</i>", "")
						.replace("<strike style=\"background-color:green\">", "")
						.replace("</strike>", "")
						.trim(), item);
			}

		} else {
			JOptionPane.showMessageDialog(null, "Selected item not found. Add a task or select one", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void añadirTarea() {
		String texto = textField.getText();
		if (!texto.equalsIgnoreCase("")) {
			model.addElement(texto);
			textField.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "Task is empty. Please write a task", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}