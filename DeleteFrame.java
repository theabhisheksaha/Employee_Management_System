import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import mu.*;

public class DeleteFrame extends JFrame
{
	
	JPanel jp1,jp2;
	JButton delete,back;
	JLabel l1;
	JTextField t1;
	public DeleteFrame()
	{
		super("Employe Management System");
		setSize(500,150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jp1=new JPanel();
		jp1.setLayout(new FlowLayout(FlowLayout.CENTER,10,25));
		delete=new JButton("Delete");
		back=new JButton("Back");
		l1=new JLabel("Id:");
		t1=new JTextField(5);
		jp1.add(l1);
		jp1.add(t1);
		add(jp1);
		jp2=new JPanel();
		jp2.setLayout(new FlowLayout(FlowLayout.CENTER,10,25));
		jp2.add(delete);
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
		
		
		delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String id=t1.getText();
				if(id.length()==0)
				{
					Sound.failure();
					JOptionPane.showMessageDialog(new JDialog(),"Please Enter A valid Id");
					return;
				}
				try
				{
				DatabaseHandler q=new DatabaseHandler();
				q.remove(Integer.parseInt(id));
				t1.setText("");
				}
				catch(Exception e)
				{
					Sound.failure();
					JOptionPane.showMessageDialog(new JDialog(),"Enter Valid ID");
				}
			}	
		});
		}
}
		