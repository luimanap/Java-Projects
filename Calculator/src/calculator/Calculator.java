package calculator;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.*;

public class Calculator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbldown = new JLabel("0");
	private JLabel lblup = new JLabel("");
	private JButton btnonoff, btnBack, btnc, btnperc, btnslash, btnx, btnsum, btnminus, btnequal, btndot, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
	
	private boolean equalpressed = false;
	private boolean enabled = false;
	private boolean dotpressed = false;

	/**
	 * Launch the application.
	 */
	/**
	 * @author Luis Vaquero
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator frame = new Calculator();
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
	public Calculator() {
		
		installFont();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 334, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{262, 0};
		gbl_contentPane.rowHeights = new int[]{37, 52, 210, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		
		lblup.setForeground(new Color(0, 204, 0));
		lblup.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblup.setFont(new Font("Monocraft", Font.BOLD, 12));
		lblup.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblup, gbc_lblNewLabel);
		lbldown.setForeground(new Color(0, 204, 0));
		lbldown.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbldown.setHorizontalAlignment(SwingConstants.RIGHT);
		lbldown.setFont(new Font("Monocraft", Font.BOLD, 30));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lbldown, gbc_lblNewLabel_1);
		
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new GridLayout(0, 4, 0, 0));
		
		//TODO Boton on/off
		btnonoff = new JButton("ON/OFF");
		btnonoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onoff();
				
			}	
		});
		btnonoff.setForeground(new Color(0, 204, 0));
		btnonoff.setFont(new Font("Monocraft", Font.BOLD, 20));
		panel.add(btnonoff);
		
		//¡TODO Boton C
		btnc = new JButton("C");
		btnc.setEnabled(false);
		btnc.setForeground(new Color(0, 204, 0));
		btnc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbldown.setText("0");				
				lblup.setText("");
			}
		});
		//TODO Boton porcentaje
		btnperc = new JButton("%");
		btnperc.setEnabled(false);
		btnperc.setForeground(new Color(0, 204, 0));
		btnperc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!lbldown.getText().isEmpty()) {
					double value = Double.valueOf(lbldown.getText());
					double newvalue = value/100;
					lbldown.setText(String.valueOf(newvalue));
				}
			}
		});
		btnperc.setFont(new Font("Monocraft", Font.BOLD, 20));
		panel.add(btnperc);
		panel.add(btnc);
		btnc.setFont(new Font("Monocraft", Font.BOLD, 20));
		
		
		//TODO Boton borrar
		btnBack = new JButton("<=");
		btnBack.setEnabled(false);
		btnBack.setForeground(new Color(0, 204, 0));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletelastchar();
			}
		});
		btnBack.setFont(new Font("Monocraft", Font.BOLD, 20));
		panel.add(btnBack);
		
		//TODO Boton 7
		btn7 = new JButton("7");
		btn7.setEnabled(false);
		btn7.setForeground(new Color(0, 204, 0));
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeNumber("7");
			}
		});
		btn7.setFont(new Font("Monocraft", Font.BOLD, 20));
		panel.add(btn7);
		
		//TODO Boton 8
		btn8 = new JButton("8");
		btn8.setEnabled(false);
		btn8.setForeground(new Color(0, 204, 0));
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeNumber("8");
			}
		});
		btn8.setFont(new Font("Monocraft", Font.BOLD, 20));
		panel.add(btn8);
		
		//TODO Boton 9
		btn9 = new JButton("9");
		btn9.setEnabled(false);
		btn9.setForeground(new Color(0, 204, 0));
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeNumber("9");
			}
		});
		btn9.setFont(new Font("Monocraft", Font.BOLD, 20));
		panel.add(btn9);
		
		//TODO Boton division
		btnslash = new JButton("/");
		btnslash.setEnabled(false);
		btnslash.setForeground(new Color(0, 204, 0));
		btnslash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeSign("/");
			}
		});
		btnslash.setFont(new Font("Monocraft", Font.BOLD, 20));
		panel.add(btnslash);
		
		//TODO Boton 4
		btn4 = new JButton("4");
		btn4.setEnabled(false);
		btn4.setForeground(new Color(0, 204, 0));
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeNumber("4");
			}
		});
		btn4.setFont(new Font("Monocraft", Font.BOLD, 20));
		panel.add(btn4);
		
		//TODO Boton 5
		btn5 = new JButton("5");
		btn5.setEnabled(false);
		btn5.setForeground(new Color(0, 204, 0));
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeNumber("5");
			}
		});
		btn5.setFont(new Font("Monocraft", Font.BOLD, 20));
		panel.add(btn5);
		
		//TODO Boton 6
		btn6 = new JButton("6");
		btn6.setEnabled(false);
		btn6.setForeground(new Color(0, 204, 0));
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeNumber("6");
			}
		});
		btn6.setFont(new Font("Monocraft", Font.BOLD, 20));
		panel.add(btn6);
		
		//TODO Boton multiplicacion
		btnx = new JButton("X");
		btnx.setEnabled(false);
		btnx.setForeground(new Color(0, 204, 0));
		btnx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeSign("*");
			}
		});
		btnx.setFont(new Font("Monocraft", Font.BOLD, 20));
		panel.add(btnx);
		
		//TODO Boton 1
		btn1 = new JButton("1");
		btn1.setEnabled(false);
		btn1.setForeground(new Color(0, 204, 0));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeNumber("1");
			}
		});
		btn1.setFont(new Font("Monocraft", Font.BOLD, 20));
		panel.add(btn1);
		
		//TODO Boton 2
		btn2 = new JButton("2");
		btn2.setEnabled(false);
		btn2.setForeground(new Color(0, 204, 0));
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeNumber("2");
			}
		});
		btn2.setFont(new Font("Monocraft", Font.BOLD, 20));
		panel.add(btn2);
		
		//TODO Boton 3
		btn3 = new JButton("3");
		btn3.setEnabled(false);
		btn3.setForeground(new Color(0, 204, 0));
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeNumber("3");
			}
		});
		btn3.setFont(new Font("Monocraft", Font.BOLD, 20));
		panel.add(btn3);
		
		//TODO Boton resta
		btnminus = new JButton("-");
		btnminus.setEnabled(false);
		btnminus.setForeground(new Color(0, 204, 0));
		btnminus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeSign("-");
			}
		});
		btnminus.setFont(new Font("Monocraft", Font.BOLD, 20));
		panel.add(btnminus);
		
		//TODO Boton decimal
		btndot = new JButton(".");
		btndot.setEnabled(false);
		btndot.setForeground(new Color(0, 204, 0));
		btndot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!lbldown.getText().contains(".")) {
					dotpressed = true;
				}
			}
		});
		btndot.setFont(new Font("Monocraft", Font.BOLD, 20));
		panel.add(btndot);
		
		//TODO Boton 0
		btn0 = new JButton("0");
		btn0.setEnabled(false);
		btn0.setForeground(new Color(0, 204, 0));
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeNumber("0");
			}
		});
		btn0.setFont(new Font("Monocraft", Font.BOLD, 20));
		panel.add(btn0);
		
		//TODO Boton igual
		btnequal = new JButton("=");
		btnequal.setEnabled(false);
		btnequal.setForeground(new Color(0, 204, 0));
		btnequal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeCalc();
			}
		});
		btnequal.setFont(new Font("Monocraft", Font.BOLD, 20));
		panel.add(btnequal);
		
		//TODO Boton suma
		btnsum = new JButton("+");
		btnsum.setEnabled(false);
		btnsum.setForeground(new Color(0, 204, 0));
		btnsum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeSign("+");
			}
		});
		btnsum.setFont(new Font("Monocraft", Font.BOLD, 20));
		panel.add(btnsum);
		
	}
	private void installFont() {
		Font monocraft;
		boolean installed = false;
		try {
			File fontFile = new File("font/Monocraft.ttf");
			monocraft = Font.createFont(Font.TRUETYPE_FONT, fontFile);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			String[] systemfonts = ge.getAvailableFontFamilyNames();
			for (String font : systemfonts) {
				if(font.equals("Monocraft")) {
					installed = true;
					break;
				}
			}
			if(!installed) {
				ge.registerFont(monocraft);
				System.out.println("Fuente instalada ;)");
			}else {
				System.out.println("Fuente ya instalada en el sistema ;)");
			}
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void makeCalc() {
	    String expression = lblup.getText();
	    String operand = lbldown.getText();
	    String operator = "";

	    if (expression.contains("+") && !operand.isEmpty()) {
	        operator = "+";
	    } else if (expression.contains("-") && !operand.isEmpty()) {
	        operator = "-";
	    } else if (expression.contains("*") && !operand.isEmpty()) {
	        operator = "*";
	    } else if (expression.contains("/") && !operand.isEmpty()) {
	        operator = "/";
	    } else {
	    	JOptionPane.showMessageDialog(null, "Operator is not valid","Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (expression.replace(operator, "").isEmpty()) {
	        expression = "0" + operator;
	    }
	    double result;
	    switch (operator) {
	        case "+":
	            result = sum(expression.replace(operator, ""), operand);
	            break;
	        case "-":
	            result = sub(expression.replace(operator, ""), operand);
	            break;
	        case "*":
	            result = mult(expression.replace(operator, ""), operand);
	            break;
	        case "/":
	            result = div(expression.replace(operator, ""), operand);
	            break;
	        default:
	        	JOptionPane.showMessageDialog(null, "Unspected Error","Error", JOptionPane.ERROR_MESSAGE);
	            return;
	    }

	    String resultStr = String.valueOf(result);

	    if (resultStr.endsWith(".0")) {
	        resultStr = resultStr.substring(0, resultStr.length() - 2);
	    }

	    lblup.setText(resultStr + operator);
	    /*lbldown.setText(resultStr);
	    
	    long tiempoInicial = System.currentTimeMillis();
        long tiempoPausa = 1000; // 2 segundos en milisegundos
        
        while (System.currentTimeMillis() - tiempoInicial < tiempoPausa) {
            // No hagas nada, solo espera
        }*/
	    
	    lbldown.setText("0");
	    equalpressed = true;
	}

	private void deletelastchar() {
		if(!lbldown.getText().isEmpty()) {
			String text = lbldown.getText();
			int length = text.length();
			String newText = text.substring(0,length-1);
			lbldown.setText(newText);
			if(lbldown.getText().isEmpty()) {
				lbldown.setText("0");
			}
			/*String removechar = lbldown.getText();
			removechar = removechar.replace(removechar.substring(removechar.length()-1),"");
			lbldown.setText(removechar);*/
		}
	}
	private void writeSign(String sign) {
		
		if(sign.equals("+")||sign.equals("-")||sign.equals("*")||sign.equals("/")) {
			//añadimos el signo si el label de arriba esta vacio
			if(lblup.getText().isEmpty()|!lblup.getText().contains(sign)) {
				lblup.setText(lbldown.getText()+sign);
				lbldown.setText("");
			}//sustituimos otros signos si los hubiese
			String signonstring = lblup.getText().substring(lblup.getText().length()-1,lblup.getText().length());
			if(!signonstring.equals(sign)&&!lblup.getText().isEmpty()) {
				lblup.setText(lblup.getText().replace(signonstring,sign));
			}
			
		}
		/*if(lblup.getText().contains("-")) {
			lblup.setText(lblup.getText().replace("-",sign));
		}
		else if(lblup.getText().contains("*")) {
			lblup.setText(lblup.getText().replace("*",sign));
		}
		else if(lblup.getText().contains("/")) {
			lblup.setText(lblup.getText().replace("/",sign));
		}
		else if(lblup.getText().contains("+")) {
			lblup.setText(lblup.getText().replace("+",sign));
		}*/
		
	}
	
	private Double sum(String num1, String num2) {
		double num1_d = Double.valueOf(num1);
		double num2_d = Double.valueOf(num2);
		return num1_d+num2_d;
	}
	private Double sub(String num1, String num2) {
		double num1_d = Double.valueOf(num1);
		double num2_d = Double.valueOf(num2);
		return num1_d-num2_d;
	}
	private Double mult(String num1, String num2) {
		double num1_d = Double.valueOf(num1);
		double num2_d = Double.valueOf(num2);
		return num1_d*num2_d;
	}
	private Double div(String num1, String num2) {
		double num1_d = Double.valueOf(num1);
		double num2_d = Double.valueOf(num2);
		return num1_d/num2_d;
	}
	
	private void writeNumber(String number) {
		if(lbldown.getText().equals("0")) {
			lbldown.setText("");	
		}
		else if(equalpressed) {
			String result = lbldown.getText();
			String operator = lblup.getText().substring(lblup.getText().length()-1,lblup.getText().length());
			lblup.setText(result+operator);
			lbldown.setText("");
			equalpressed = false;
		}
		
		if(!lbldown.getText().equals("")) {
			if(dotpressed) {
				lbldown.setText(lbldown.getText()+"."+number);
				dotpressed = false;
			}
			else {
				lbldown.setText(lbldown.getText()+number);
			}	
		}
		else {
			if(dotpressed) {
				lbldown.setText("0."+number);
				dotpressed = false;
			}
			else {
				lbldown.setText(number);
			}
		}
		
	}
	private void onoff() {
		boolean isEnabled = !enabled;
        Component[] buttons = {btnBack,btnc,btnperc,btnslash,btnx,btnsum,btnminus,btnequal,btndot,btn0, btn1, btn2, btn3, btn4, btn5, btn6,btn7, btn8, btn9};
        for (Component button : buttons) {
            button.setEnabled(isEnabled);
        }
        enabled = isEnabled;
	}
}