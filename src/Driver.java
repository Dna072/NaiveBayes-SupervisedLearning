import javax.swing.UIManager;

@SuppressWarnings("unused")
public class Driver {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		Program program = new Program();
	}
}
