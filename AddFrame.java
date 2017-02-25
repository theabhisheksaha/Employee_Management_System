
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import mu.*;

public class AddFrame extends JFrame
{
	
	JPanel jp1,jp2;
	JButton add, back;
	JLabel l1,l2;
	JTextField t1,t2;
	public AddFrame()
	{
		super("Employee Management System");
		setSize(500,150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jp1=new JPanel();
		jp1.setLayout(new FlowLayout(FlowLayout.CENTER,10,25));
		add=new JButton("Add");
		back=new JButton("Back");
		l1=new JLabel("Id:");
		l2=new JLabel("Name:");
		t1=new JTextField(5);
		t2=new JTextField(10);
		jp1.add(l1);
		jp1.add(t1);
		jp1.add(l2);
		jp1.add(t2);
		add(jp1);
		jp2=new JPanel();
		jp2.setLayout(new FlowLayout(FlowLayout.CENTER,10,25));
		jp2.add(add);
		jp2.add(back);
		add(jp2,BorderLayout.SOUTH);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				HomeFrame h=new HomeFrame();
				dispose();
			}
		});
		
		
		//Event handling
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				HomeFrame a=new HomeFrame();
				dispose();
			}
		});
		
		
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String ID=t1.getText();
				String NAME=t2.getText();
				if(ID.length()==0|NAME.length()==0)
				{
					Sound.failure();
					JOptionPane.showMessageDialog(new JDialog(),"All Fields are mandatory");
					return;
				}
				try
				{
				DatabaseHandler q=new DatabaseHandler();
				q.insert(Integer.parseInt(ID),NAME);
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(new JDialog(),"Please Insert Valid Values");
				}
				t1.setText("");
				t2.setText("");
			}
		
			
		});
	
	
		
	}
}