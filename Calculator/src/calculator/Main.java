package calculator;

import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;

public class Main {
	public static void main(String[] args) {
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			Properties props = new Properties();
			props.put("logoString","");
			props.put("textAntiAliasingMode", "grey");
			props.put("dynamicLayout", "on");
			HiFiLookAndFeel.setCurrentTheme(props);
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		Calculator frame = new Calculator();
		frame.setTitle("Calculator Simulator 2023");
		frame.setVisible(true);
		frame.pack();
	}
}