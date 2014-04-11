package finalyearproject;

import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AppletClass extends JApplet {

	private static final long serialVersionUID = 1L;
	
	public final JTextArea ta = new JTextArea();
	
	public static boolean finished = false;

	@Override
	public void init() {
		
		this.setLayout(new GridLayout(4, 1));
		
		this.setSize(600, 300);
		
		JPanel panel1,panel2,panel3,panel4;
		
		panel1 = new JPanel();	panel2 = new JPanel();	panel3 = new JPanel();	panel4 = new JPanel();
		
		JLabel l1,l2,l3;
		
		l1 = new JLabel("Method");
		
		l2 = new JLabel("Algorithm Type");
		
		l3 = new JLabel("Fault Index");
		
		JTextField fault = new JTextField(10);
		
		JRadioButton m1,m2,m3;
		
		m1 = new JRadioButton("Daily");
		
		m2 = new JRadioButton("Monthly");
		
		m3 = new JRadioButton("Yearly");
		
		JRadioButton a1,a2;
		
		a1 = new JRadioButton("Speculative");
		
		a2 = new JRadioButton("Non Speculative");
		
		ButtonGroup method , algorithm_g;
		
		method = new ButtonGroup();	
		
		method.add(m1); method.add(m2); method.add(m3);
		
		algorithm_g = new ButtonGroup();
		
		algorithm_g.add(a1); algorithm_g.add(a2);
		
		panel1.setSize(this.WIDTH, 100);	panel3.setSize(this.WIDTH, 100);	panel2.setSize(this.WIDTH, 100);
		
		panel2.add(l2); panel1.add(l1); panel3.add(l3);
		
		panel2.add(a1); panel2.add(a2);	panel1.add(m3); panel1.add(m2); panel1.add(m1);	panel3.add(fault);	panel4.add(new JButton("Start"));
		
		this.add(panel1);	this.add(panel2);	this.add(panel3);	this.add(panel4);
		
		int  type = Integer.parseInt(this.getParameter("type"));
		
		int algorithm = Integer.parseInt(this.getParameter("algorithm"));
		
		int fault_index = Integer.parseInt(this.getParameter("fault_index"));
		
		ByzantineFaultSystem ob = null;
		try 
		{
			ob = new ByzantineFaultSystem("mapper_class",
					"reducer_class", "IN", "OUT", false , 2);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		ob.run();
		
		finished = true;
		
		System.out.println("Finished!");
		
		super.init();
	}
	
	@Override
	public void start() {
		super.start();
	}
	
	@Override
	public void repaint() {
		super.repaint();
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}
	
	@Override
	public void stop() {
		super.stop();
	}
	
}
