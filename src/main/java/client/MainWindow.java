package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

import communication.GroceryItemBuffer;
import factory.AbstractFactory;
import factory.FactoryProvider;
import reports.ReportType;

public class MainWindow implements Observer{
	
	private JFrame frame;
	JPanel tablePanel = new JPanel();
	
	JTextField goalField = new JTextField(5);
	JTextField nameField = new JTextField(10);
	JTextField caloriesField = new JTextField(5);
	JTextField quantityField = new JTextField(5);
	JTextField pDateYField = new JTextField(10);
	JTextField eDateYField = new JTextField(10);
	JTextField pDateMField = new JTextField(10);
	JTextField eDateMField = new JTextField(10);
	JTextField pDateDField = new JTextField(10);
	JTextField eDateDField = new JTextField(10);
	JTextField numberField = new JTextField(5);
	
	private int userId;
	private ClientConnection cn;
	private ArrayList<GroceryItemBuffer> itemList;
	
	private Boolean firstAction;
	
	public MainWindow(int userId, ClientConnection cn) {
		this.userId = userId;
		this.cn = cn;
		itemList = new ArrayList<GroceryItemBuffer>();
		cn.addMainWindow(this);
		updateItemListRequest();
		initUI();
		updateItemListRequest();
		firstAction = true;
	}
	
	private void initUI() {
		frame = new JFrame("Wasteless");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 400);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		JPanel showPanel = new JPanel();
		JPanel goalPanel = new JPanel();
		goalPanel.setLayout(new BoxLayout(goalPanel, BoxLayout.X_AXIS));
		JPanel consumePanel = new JPanel();
		consumePanel.setLayout(new BoxLayout(consumePanel, BoxLayout.X_AXIS));
		JPanel addPanel = new JPanel();
		addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
		JPanel addPanel1 = new JPanel();
		addPanel1.setLayout(new BoxLayout(addPanel1, BoxLayout.X_AXIS));
		JPanel addPanel2 = new JPanel();
		addPanel2.setLayout(new BoxLayout(addPanel2, BoxLayout.Y_AXIS));
		JPanel addPanel21 = new JPanel();
		addPanel21.setLayout(new BoxLayout(addPanel21, BoxLayout.X_AXIS));
		JPanel addPanel22 = new JPanel();
		addPanel22.setLayout(new BoxLayout(addPanel22, BoxLayout.X_AXIS));
		
		goalField.setMaximumSize(new Dimension(50,30));
		nameField.setMaximumSize(new Dimension(200,30));
		caloriesField.setMaximumSize(new Dimension(50,30));
		quantityField.setMaximumSize(new Dimension(50,30));
		pDateYField.setMaximumSize(new Dimension(50,30));
		pDateMField.setMaximumSize(new Dimension(50,30));
		pDateDField.setMaximumSize(new Dimension(50,30));
		eDateYField.setMaximumSize(new Dimension(50,30));
		eDateMField.setMaximumSize(new Dimension(50,30));
		eDateDField.setMaximumSize(new Dimension(50,30));
		numberField.setMaximumSize(new Dimension(50,30));
		
		JTable table = createTable();
		tablePanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		tablePanel.add(table, BorderLayout.CENTER);
		
		JButton refreshBtn = new JButton("Refresh");
		refreshBtn.addActionListener(new Clicker("refresh"));
		
		JButton goalBtn = new JButton("Update");
		goalBtn.addActionListener(new Clicker("goal"));
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new Clicker("delete"));
		
		JButton consumeBtn = new JButton("Mark as consumed");
		consumeBtn.addActionListener(new Clicker("consume"));
		
		JButton addBtn = new JButton("Add item");
		addBtn.addActionListener(new Clicker("add"));
		showPanel.add(refreshBtn);
		
		JButton burnBtn = new JButton("Calculate burndown rate");
		burnBtn.addActionListener(new Clicker("burn"));
		
		JLabel filler = new JLabel();
		filler.setPreferredSize(new Dimension(50, 50));
		filler.setMaximumSize(new Dimension(50, 50));
		
		goalPanel.add(new JLabel("New goal: "));
		goalPanel.add(goalField);
		goalPanel.add(goalBtn);
		
		consumePanel.add(new JLabel("Item #: "));
		consumePanel.add(numberField);
		consumePanel.add(consumeBtn);
		consumePanel.add(deleteBtn);
		
		addPanel1.add(new JLabel("Name: "));
		addPanel1.add(nameField);
		addPanel1.add(new JLabel("Calories"));
		addPanel1.add(caloriesField);
		addPanel1.add(new JLabel("Quantity"));
		addPanel1.add(quantityField);
		
		addPanel21.add(new JLabel("Purchase date year: "));
		addPanel21.add(pDateYField);
		addPanel21.add(new JLabel("Purchase date month: "));
		addPanel21.add(pDateMField);
		addPanel21.add(new JLabel("Purchase date day: "));
		addPanel21.add(pDateDField);
		addPanel22.add(new JLabel("Expiration date year: "));
		addPanel22.add(eDateYField);
		addPanel22.add(new JLabel("Expiration date month: "));
		addPanel22.add(eDateMField);
		addPanel22.add(new JLabel("Expiration date day: "));
		addPanel22.add(eDateDField);
		addPanel2.add(addPanel21);
		addPanel2.add(addPanel22);
		
		addPanel.add(addPanel1);
		addPanel.add(addPanel2);
		addPanel.add(addBtn);
		
		leftPanel.add(filler);
		leftPanel.add(goalPanel);
		leftPanel.add(new JSeparator());
		leftPanel.add(consumePanel);
		leftPanel.add(new JSeparator());
		leftPanel.add(addPanel);
		leftPanel.add(new JSeparator());
		leftPanel.add(burnBtn);
		rightPanel.add(showPanel);
		rightPanel.add(new JSeparator());
		rightPanel.add(tablePanel);
		mainPanel.add(leftPanel);
		mainPanel.add(new JSeparator());
		mainPanel.add(rightPanel);
		
		frame.setContentPane(mainPanel);
		frame.setVisible(true);
	}
	
	private class Clicker implements ActionListener {
		private String action;
		
		public Clicker(String action) {
			this.action = action;
		}
		public void actionPerformed(ActionEvent arg0) {
			if(firstAction) {
				createDonationNotify();
				firstAction = false;
			}
			if(action.equals("refresh")) {
				try {
					updateItemListRequest();
					tablePanel.removeAll();
					JTable table = createTable();
					tablePanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
					tablePanel.add(table, BorderLayout.CENTER);
					frame.revalidate();
				} catch(Exception e) {
					JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
			if(action.equals("goal")) {
				int goal = Integer.parseInt(goalField.getText());
				if(Validator.validateGoalInfo(goal)) {
					cn.sendGoalRequest(goal, userId);
				} else {
					JOptionPane.showMessageDialog(frame, "Goal should be a positive number!",
							"Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
			if(action.equals("delete")) {
				int number = Integer.parseInt(numberField.getText());
				if(number < itemList.size()) {
					cn.sendDeleteRequest(itemList.get(number).getItemid());
				} else {
					JOptionPane.showMessageDialog(frame, "Please input a number available in the list!",
							"Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
			if(action.equals("consume")) {
				int number = Integer.parseInt(numberField.getText());
				if(number < itemList.size()) {
					cn.sendConsumeRequest(itemList.get(number).getItemid());
				} else {
					JOptionPane.showMessageDialog(frame, "Please input a number available in the list!",
							"Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
			if(action.equals("add")) {
				String name = nameField.getText();
				int calories = Integer.parseInt(caloriesField.getText());
				int quantity = Integer.parseInt(quantityField.getText());
				int pYear = Integer.parseInt(pDateYField.getText());
				int pMonth = Integer.parseInt(pDateMField.getText());
				int pDay = Integer.parseInt(pDateDField.getText());
				int eYear = Integer.parseInt(eDateYField.getText());
				int eMonth = Integer.parseInt(eDateMField.getText());
				int eDay = Integer.parseInt(eDateDField.getText());
				if(Validator.validateItemInfo(name, calories, quantity) && 
						Validator.dateValidator(pYear, pMonth, pDay, eYear, eMonth, eDay)) {
					String pDate = LocalDate.of(pYear, pMonth, pDay).toString();
					String eDate = LocalDate.of(eYear, eMonth, eDay).toString();
					cn.sendAddRequest(userId, name, calories, quantity, pDate, eDate);
				} else {
					JOptionPane.showMessageDialog(frame, "Name must start with a letter and have at least 2 characters. "
							+ "Calories and quantity should be a positive number. Dates should be valid. "
							+ "Purchase date must be before today.",
							"Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
			if(action.equals("burn")) {
				updateItemListRequest();
				String msg = getReportMsg();
				JOptionPane.showMessageDialog(frame, msg, "Burndown", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private String getReportMsg() {
		AbstractFactory f = FactoryProvider.getFactory(ReportType.WEEKLY);
		String msg = f.createReport(userId).printReport();
		return msg;
	}
	
	private JTable createTable() {
		JTable ret;
		Object[][] entries = new Object[itemList.size()][7];
		String[] collumnNames = {"#", "Name", "Calories", "Quantity", "Purchase Date", "Expiration Date", "Consumption Date"};
		
		for(int i = 0; i < itemList.size(); i++) {
			GroceryItemBuffer g = itemList.get(i);
			entries[i][0] = i;
			entries[i][1] = g.getName();
			entries[i][2] = g.getCalories();
			entries[i][3] = g.getQuantity();
			entries[i][4] = g.getPurchaseDate();
			entries[i][5] = g.getExpirationDate();
			if(g.getConsumptionDate() == null)
				entries[i][6] = "Not consumed";
			else
				entries[i][6] = g.getConsumptionDate();
		}
		ret = new JTable(entries, collumnNames);
		return ret;
	}
	
	private void updateItemListRequest() {
		cn.sendRetrieveItemList(userId);
	}
	
	public void updateItemList(ArrayList<GroceryItemBuffer> list) {
		itemList = list;
	}
	
	private void createDonationNotify() {
		DonationNotify dn = new DonationNotify(this, itemList);
	}

	public void update(Observable o, Object arg) {
		String msg = (String) arg;
		JOptionPane.showMessageDialog(frame, msg,
				"Warning", JOptionPane.INFORMATION_MESSAGE);
		System.out.println("Received notification from observable.");
		
	}
}
