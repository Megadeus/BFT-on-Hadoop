package finalyearproject;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InputApplet extends JApplet{

	private static final long serialVersionUID = -5635183545131065745L;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		
		this.setLayout(new GridLayout(4, 1));
		
		this.setSize(600, 300);
		
		JPanel panel1,panel2,panel3,panel4;
		
		panel1 = new JPanel();	panel2 = new JPanel();	panel3 = new JPanel();	panel4 = new JPanel();
		
		final JLabel l1,l2,l3;
		
		l1 = new JLabel("Method");
		
		l2 = new JLabel("Algorithm Type");
		
		l3 = new JLabel("Fault Index");
		
		final JTextField fault = new JTextField(10);
		
		final JRadioButton m1,m2,m3;
		
		m1 = new JRadioButton("Daily");
		
		m2 = new JRadioButton("Monthly");
		
		m3 = new JRadioButton("Yearly");
		
		final JRadioButton a1,a2;
		
		a1 = new JRadioButton("Speculative");
		
		a2 = new JRadioButton("Non Speculative");
		
		ButtonGroup method , algorithm;
		
		method = new ButtonGroup();	
		
		method.add(m1); method.add(m2); method.add(m3);
		
		algorithm = new ButtonGroup();
		
		algorithm.add(a1); algorithm.add(a2);
		
		panel1.setSize(this.WIDTH, 100);	panel3.setSize(this.WIDTH, 100);	panel2.setSize(this.WIDTH, 100);
		
		panel2.add(l2); panel1.add(l1); panel3.add(l3);	final JTextArea tp = new JTextArea();
		
		JButton start_button = new JButton("Start");  panel4.add(start_button); 
		
		panel4.add(tp);
		
		final JApplet o = this;
		
		start_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

//				tp.insert("",  0);
//				
//				if(m1.isSelected())
//					tp.append("Daily");
//				if(m2.isSelected())
//					tp.append("Monthly");
//				if(m3.isSelected())
//					tp.append("Yearly");
				
				final int type = m1.isSelected() ? 10 : ( m2.isSelected() ? 7 : 4);
				
				final boolean algorithm = a1.isSelected() ? true : false ;
				
				final int fault_index = Integer.parseInt(fault.getText());
				
				ForecastMapper.End_year = type;	JobTrackerNonSpecMapper.End_year = type;
				
//				int algorithm = 
						
//				int  type =  
//				
//				int algorithm =  
//				
//				int fault_index =  
//				
				ByzantineFaultSystem ob = null;
				try 
				{
					ob = new ByzantineFaultSystem("mapper_class",
							"reducer_class", "IN", "OUT", algorithm , fault_index);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				ob.run();
				
				System.out.println("Finished!");
				
				o.destroy();
			}
		});
		
		panel2.add(a1); panel2.add(a2);	panel1.add(m3); panel1.add(m2); panel1.add(m1);	panel3.add(fault);	panel4.add(start_button);
		
		this.add(panel1);	this.add(panel2);	this.add(panel3);	this.add(panel4);
		
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		super.start();
	}
	
	
}
