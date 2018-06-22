import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;




public class Program extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5983872089426406196L;
	private JSpinner percentSplit;
	private JButton train;
	private JButton test;
	private JComboBox<String> occupation;
	private JComboBox<String> gender;
	private JComboBox<String> income;
	static JTextArea results;
	private DefaultTableModel model;
	private JProgressBar pBar;
	private String[] colNames = { "Occupation", "Gender", "Income", "Political Inclination" };
	private ArrayList<Person> data = new ArrayList<Person>();
	private List<Person> trainSet = new ArrayList<Person>();
	private List<Person> testSet = new ArrayList<Person>();
	private int percentSplitVal;

	private File n;

	public Program() {
		buildUI();
		setFrameProps();
	}

	private void setFrameProps() {
		this.getContentPane().setLayout(new FlowLayout());
		this.setSize(700, 600);
		this.setTitle("Predictor");
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(getOwner());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void buildUI() {
		buildTrainToolbar();
	}

	private void buildTrainToolbar() {
		JButton selectData = new JButton("Load Political dataset");
		selectData.addActionListener(e -> selectDataActionPerformed(e));
		JLabel percentSplitLabel = new JLabel("Percentage split");
		buildSpinner();
		percentSplitVal = (int) percentSplit.getValue();
		ChangeListener listener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				percentSplitVal = (int) percentSplit.getValue();
			}
		};
		percentSplit.addChangeListener(listener);
		train = new JButton("Train");
		train.addActionListener(e -> trainButtonActionPerformed(e));
		train.setEnabled(false);
		this.add(selectData);
		this.add(percentSplitLabel);
		this.add(percentSplit);
		this.add(train);
	}

	private void trainButtonActionPerformed(ActionEvent e) {
		splitData(percentSplitVal);
		Trainer train = new Trainer(trainSet);
		enableAll();
	}

	private void splitData(int value) {
		int total = data.size();
		int splitIdx = (int) (total * value / 100.0);
		trainSet = data.subList(0, splitIdx);
		testSet = data.subList(splitIdx, total);

	}

	private void enableAll() {
		occupation.setEnabled(true);
		gender.setEnabled(true);
		income.setEnabled(true);
		test.setEnabled(true);
		results.setEnabled(true);

	}

	private void selectDataActionPerformed(ActionEvent e) {
		try {
			n = new File("political data.csv");
			readFile(n);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);
	}

	private void readFile(File fileName) throws Exception {
		model = new DefaultTableModel(colNames, 0) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		if (fileName != null) {
			BufferedReader stream = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			String line;
			stream.readLine();
			while ((line = stream.readLine()) != null) {
				String[] in = line.replaceAll("\\s+","").split(",");
				Person person = new Person();
				Object[] modelData = new Object[in.length];

				for (int i = 0; i < in.length; i++) {
					modelData[i] = in[i];
					person.setOccupation(in[0]);
					person.setGender(in[1]);
					person.setIncome(in[2]);
					person.setPoliticalAff(in[3]);
				}
				model.addRow(modelData);
				data.add(person);
			}
			train.setEnabled(true);
			buildDataPanel();
			buildTestToolbar();
			disableAll();
			stream.close();
		}
	}

	private void buildDataPanel() {
		JTable dataset = new JTable();
		dataset.setRowHeight(30);
		dataset.setModel(model);
		JScrollPane datapane = new JScrollPane(dataset);
		datapane.setPreferredSize(new Dimension(650, 300));
		this.add(datapane);

	}

	private void buildTestToolbar() {
		this.add(new JLabel("Occupation"));
		occupation = new JComboBox<String>();
		occupation.addItem("Analyst");
		occupation.addItem("Barister");
		occupation.addItem("Cook");
		occupation.addItem("Doctor");
		this.add(occupation);
		this.add(new JLabel("Gender"));
		gender = new JComboBox<String>();
		gender.addItem("Male");
		gender.addItem("Female");
		this.add(gender);

		this.add(new JLabel("Income"));
		income = new JComboBox<String>();
		income.addItem("High");
		income.addItem("Medium");
		income.addItem("Low");
		this.add(income);

		test = new JButton("Test");
		test.addActionListener(e -> testButtonActionPerformed(e));
		this.add(test);

		results = new JTextArea();
		results.setEditable(false);
		results.setFont(new Font("Arial", 3, 18));
		//results.setBackground(Color.blue);
		//results.setForeground(Color.white);
		results.setPreferredSize(new Dimension(650, 150));
		this.add(results);
		disableAll();
	}

	private void testButtonActionPerformed(ActionEvent e) {
		splitData(percentSplitVal);
		Tester t = new Tester(testSet);
		Person p=new Person(occupation.getSelectedItem().toString(),gender.getSelectedItem().toString(),income.getSelectedItem().toString(),null);
		NBClassfier i=new NBClassfier(p);
		results.append("\n\n"+p.toString()+": Likely to be a "+i.classify(p));

	}

	private void disableAll() {
		occupation.setEnabled(false);
		gender.setEnabled(false);
		income.setEnabled(false);
		test.setEnabled(false);
		results.setEnabled(false);

	}

	public void buildSpinner() {
		SpinnerNumberModel m_numberSpinnerModel;
		Integer current = new Integer(10);
		Integer min = new Integer(10);
		Integer max = new Integer(100);
		Integer step = new Integer(10);
		m_numberSpinnerModel = new SpinnerNumberModel(current, min, max, step);
		percentSplit = new JSpinner(m_numberSpinnerModel);
	}

}
