import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import mu.*;


public class HomeFrame extends JFrame
{
	
	JPanel jp;
	JButton add, modify, delete, view;
	public HomeFrame()
	{
		super("Employe Management System");
		setSize(500,150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jp=new JPanel();
		jp.setLayout(new FlowLayout(FlowLayout.CENTER,10,25));
		add=new JButton("Add");
		modify=new JButton("Modify");
		delete=new JButton("Delete");
		view=new JButton("View");
		
		jp.add(add);
		jp.add(modify);
		jp.add(delete);
		jp.add(view);
		add(jp);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				int output=JOptionPane.showConfirmDialog(new JDialog(),"Do you want to exit");
				if (output==JOptionPane.YES_OPTION){
					System.exit(1);
				}
			}
		});
		
	add.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae){
				AddFrame a=new AddFrame();
				dispose();
			}
		});
		modify.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae){
				ModifyFrame a=new ModifyFrame();
				dispose();
		}
		});
		delete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae){
				DeleteFrame a=new DeleteFrame();
				dispose();
		}
		});
		view.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae){
				ViewFrame a=new ViewFrame();
				dispose();
		}
		});
		
	}
public static void main(String args[])
		{
			HomeFrame h=new HomeFrame();
		}
	}
	
	
	
	
	
	class DatabaseHandler
{
	static Connection con;
	public static void getConnection()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			/* DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver()); */
	 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","cooldudes");
	 	}
		catch(Exception se)
		{
			JOptionPane.showMessageDialog(new JDialog(),""+se);
		}
	}	
	
	/*
	public void insert(int ID, String NAME)
	{
		try{
			getConnection();
			String q="INSERT INTO EMPLOYEE(ID,NAME) VALUES(?,?)";
			PreparedStatement pst=con.prepareStatement(q);
			pst.setInt(1, ID);
			pst.setString(2, NAME);
			int i=pst.executeUpdate();
			 Sound.success();
			
			JOptionPane.showMessageDialog(new JDialog(),"1 Record Added");
		}
		
	//	catch(SQLException sqe)
	//	{
	//		Sound.failure();
	//		JOptionPane.showMessageDialog(new JDialog(),"Error While Adding");
	///	}
	
		catch(Exception e)
		{
			Sound.failure();
			
			JOptionPane.showMessageDialog(new JDialog(),"Record Already Exists");
		}
	}
	*/
	
	
	public void insert(int id,String name)
{
try
{
	getConnection();
	Statement stmt=con.createStatement();
	int k=0;
			String sql="SELECT id FROM EMPLOYEE";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				if(id==rs.getInt(1))
				{
				k=1;
				break;
				
				}
			}
	if(k==0)
	{
	String q="insert into employee(id,first) values(?, ?)";
	PreparedStatement pst=con.prepareStatement(q);
	pst.setInt(1, id);
	pst.setString(2, name);
 	
	int i=pst.executeUpdate();
      

	Sound.success();
	JOptionPane.showMessageDialog(new JDialog(),"1 record added");
	}
	else
	{
		Sound.failure();
		JOptionPane.showMessageDialog(new JDialog(),"Record alredy exist");
	}
}//end of try
catch(Exception e)
{        
	Sound.failure();
	JOptionPane.showMessageDialog(new JDialog(),e);
}//end of catch
}//end of insert

	
	
	
	public String query()
	{
		StringBuffer sb=new StringBuffer();
		try{
			
			getConnection();
			String q="SELECT * FROM EMPLOYEE ORDER BY ID";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(q);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			sb.append("ID\t"+"NAME\n");
			while(rs.next())
			{
				sb.append(rs.getString(1)+"\t"+rs.getString(2)+"\n");
			}
			rs.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(new JDialog(),""+e);
		}
		return sb.toString();
	}
	
	
	
	public void remove(int id)
	{
		try{
			getConnection();
			Statement stmt=con.createStatement();
			
			int k=0;
			String sql="SELECT id FROM EMPLOYEE";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				if(id==rs.getInt(1))
				{
				k=1;
				break;
				
				}
			}
			
			
			
			if(k==1)
			{
		String q="delete from employee where id="+id;
			stmt.executeUpdate(q);
			 
			 
			 
			 Sound.success();
			
			JOptionPane.showMessageDialog(new JDialog(),"Employee with "+id+" Deleted");
			}
			else
			{
				throw new Exception();
			}
		}
		catch(Exception e)
		{
			Sound.failure();
			
			JOptionPane.showMessageDialog(new JDialog(),e);
		}
		
	}
	
	
	public void update(int id, String name)
	{
		try{
			getConnection();
			int k=0;
			Statement stmt=con.createStatement();
			String sql="SELECT id FROM EMPLOYEE";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				if(id==rs.getInt(1))
				{
				k=1;
				break;
				
				}
			}
			if(k==1)
			{
			String q="update employee set first=? where id=?";
			PreparedStatement pst=con.prepareStatement(q);
			pst.setString(1,name);
			pst.setInt(2,id);
			int i=pst.executeUpdate();
			Sound.success();
			
			JOptionPane.showMessageDialog(new JDialog(),"Employee with "+id+"Modified");
			}
			else
			{
				throw new Exception();
			}
		}
		catch(Exception e)
		{
			 Sound.failure();
			
			JOptionPane.showMessageDialog(new JDialog(),e);
		}
	}
	
	
}