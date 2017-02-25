import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import mu.*;

public class ModifyFrame extends JFrame
{
	
	JPanel jp1,jp2;
	JButton modify,back;
	JLabel l1,l2;
	JTextField t1,t2;
	//Sound so=new Sound();
	public ModifyFrame()
	{
		super("Employe Management System");
		setSize(500,150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jp1=new JPanel();
		jp1.setLayout(new FlowLayout(FlowLayout.CENTER,10,25));
		modify=new JButton("Update");
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
		jp2.add(modify);
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
		
		
		modify.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String id=t1.getText();
				String name=t2.getText();
				if(id.length()==0|name.length()==0)
				{
					Sound.failure();
					JOptionPane.showMessageDialog(new JDialog(),"All Fields are mandatory");
					return;
				}
				try
				{
				DatabaseHandler q=new DatabaseHandler();
				q.update(Integer.parseInt(id),name);
				t1.setText("");
				t2.setText("");
				}
				catch(Exception e)
				{
					Sound.failure();
					JOptionPane.showMessageDialog(new JDialog(),"Enter Valid Values");
				}
			}
		});
		
	}
}