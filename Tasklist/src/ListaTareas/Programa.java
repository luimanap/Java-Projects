package ListaTareas;

import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;

public class Programa {

	public static void main(String[] args) {
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			Properties props = new Properties();
			props.put("logoString", "Tasklist v2");
			props.put("textAntiAliasingMode", "grey");
			props.put("dynamicLayout", "on");
			GraphiteLookAndFeel.setCurrentTheme(props);
			UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Ventana frame = new Ventana();
		frame.setTitle("TaskList v.2");
		frame.setVisible(true);
		frame.setResizable(false);
	}
}