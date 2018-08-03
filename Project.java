import java.io.*;
import java.util.*;
import java.sql.*;

class Project
{
	String NAME,USN,COURSE,EMAIL,SYMBOL,PASS,VOTE;
	String CNAME,CUSN,CCOURSE,CEMAIL,ACHIEVEMENTS,CSYMBOL;
	int CNOV,CAGE,CSEM,SEM,AGE;
	Scanner cs=new Scanner(System.in);
	String user="root";
	String pass="password";
	Connection con;
	String vname;

	//initialization of sql
	void start()
	{
		try
		{
			//Load driver
			Class.forName("com.mysql.jdbc.Driver");
		
			//Create Connection
			String url="jdbc:mysql://localhost:3306/project";
			con=DriverManager.getConnection(url,user,pass);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//insert candidate details
	void insert() throws Exception
	{								
		System.out.println("Enter Candidate's details");
		System.out.print("name:         ");
		CNAME=cs.nextLine();
		System.out.print("usn:          ");
		CUSN=cs.nextLine();
		System.out.print("course:       ");
		CCOURSE=cs.nextLine();
		System.out.print("semester:     ");
		CSEM=cs.nextInt();
		System.out.print("age:          ");
		CAGE=cs.nextInt();
		System.out.print("email:        ");
		CEMAIL=cs.nextLine();
		System.out.print("achievements: ");
		ACHIEVEMENTS=cs.nextLine();
		System.out.print("Party Symbol: ");
		CSYMBOL=cs.nextLine();
		System.out.print("No. of Votes: "+"0");
		CNOV=0;
		PreparedStatement pst=con.prepareStatement("insert into candidate values('"+CNAME+"','"+CUSN+"','"+CCOURSE+"','"+CSEM+"','"+CAGE+"','"+CEMAIL+"','"+ACHIEVEMENTS+"','"+CSYMBOL+"','"+CNOV+"')");
		int i=pst.executeUpdate();
		if(i>0)
		{
			System.out.println("\n"+"Entry inserted Successfully!!!");
		}
		else
		{
			System.out.println("Some error occured while Inserting!!!"+"\n"+"Please try once more...");
		}
	}

	//delete candidate's record
	void delete() throws Exception
	{
		System.out.println("Enter the Candidate's USN whose record you want to delete...");
		Scanner input=new Scanner(System.in);
		CUSN=input.nextLine();
		PreparedStatement ps=con.prepareStatement("delete from candidate where CUSN='"+CUSN+"'");
		int i=ps.executeUpdate();
		if(i>0)
		{
			System.out.println("\n"+"Entry deleted Successfully!!!");
		}
		else
		{
			System.out.println("Some error occured while Deleting!!!"+"\n"+"Please try once more...");
		}	
	}

	//print candidate's records
	void print() throws Exception
	{
		System.out.println("what you want to print?");
		System.out.println("Enter your choice:"+"\n"+"1.Single Record"+"\n"+"2.All Records");
		Scanner sc=new Scanner(System.in);
		int ch=sc.nextInt();
		switch(ch)
		{
			case 1: System.out.println("Enter the Candidate's USN whose record you want to print...");
				Scanner inp=new Scanner(System.in);
				CUSN=inp.nextLine();
				PreparedStatement psd=con.prepareStatement("select * from candidate where CUSN='"+CUSN+"'");
				ResultSet rs=psd.executeQuery();
				while(rs.next())
				{
					System.out.printf("%-20s%-15s%-10s%-5s%-5s%-20s%-20s%-10s%-10s\n",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
				}
				break;
			case 2: PreparedStatement psm=con.prepareStatement("select * from candidate");
				ResultSet rst=psm.executeQuery();
				while(rst.next())
				{
					System.out.printf("%-20s%-15s%-10s%-5s%-5s%-20s%-20s%-10s%-10s\n",rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7),rst.getString(8),rst.getString(9));
				}	
				break;
			default: System.out.println("Invalid input");
		}
		
	}
	
	//view candidate details by voter
	void view() throws Exception
	{
		PreparedStatement sm=con.prepareStatement("select CNAME,CUSN,CCOURSE,CSEM,CAGE,CEMAIL,ACHIEVEMENTS,CSYMBOL from candidate");
		ResultSet st=sm.executeQuery();
		while(st.next())
		{
			System.out.printf("%-20s%-15s%-10s%-5s%-5s%-20s%-20s%-10s\n",st.getString(1),st.getString(2),st.getString(3),st.getString(4),st.getString(5),st.getString(6),st.getString(7),st.getString(8));
		}
	}
	
	//user or admin login
	void login() throws Exception
	{
		Project obj=new Project();
		obj.start();
		System.out.println("									  ||Please Login to Proceed||									");
		Scanner ct=new Scanner(System.in);
		System.out.print("Username(usn):       ");
		String name=ct.nextLine();
		vname=name;		//used in vote()
		System.out.print("Password:            ");
		String passwd=ct.nextLine();
		if(name.equals(obj.user) && passwd.equals(obj.pass))
		{
			int choe;
			System.out.println("								    	   ***** WELCOME ADMIN *****									");
			do{
				System.out.println("What operation you want to do?"+"\n");
				System.out.println("Enter your choice:"+"\n"+"1.Insertion"+"\n"+"2.Deletion"+"\n"+"3.Print table Contents"+"\n"+"4.View Winner"+"\n"+"Exit");
				choe=ct.nextInt();
				switch(choe)
				{
					case 1: obj.insert();	break;
					case 2: obj.delete();	break;
					case 3: obj.print();	break;
					case 4: obj.winner();	break;
					case 5: return;		
					default: System.out.println("Invalid input");
				}
			}while(choe!=5);	
		}
		else
		{
			PreparedStatement som=con.prepareStatement("select NAME from register where USN='"+name+"' and PASS='"+passwd+"'");
			ResultSet s=som.executeQuery();
			if(s.next())
			{
				int che;
				System.out.println("								    	   ***** WELCOME "+s.getString(1)+" *****									");
				do{
					System.out.println("What operation you want to do?");
					System.out.println("Enter your choice:"+"\n"+"1.View Candidate Details"+"\n"+"2.Vote"+"\n"+"3.Exit");
					che=ct.nextInt();
					switch(che)
					{
						case 1: obj.view();	break;
						case 2: obj.vote(vname);	break;
						case 3: return;
						default: System.out.println("Invalid input"+"\n");
					}
				}while(che!=3);
			}
			else
			{
				System.out.println("Forgot Password!!!"+"\n"+"Enter yes/no:");
				Scanner sd=new Scanner(System.in);
				String psd=sd.nextLine();
				if(psd.equals("yes"))
				{
					System.out.print("Enter new Password:    ");
					String npass=sd.nextLine();
					PreparedStatement mts=con.prepareStatement("update register set PASS='"+npass+"' where USN='"+name+"'");
					int ps=mts.executeUpdate();
					if(ps>0)
					{
						System.out.println("Password changed successfully");
					}
					else
					{
						System.out.println("Invalid Username...");
					}	
				}
				else
				{
					System.out.println("Invalid user!!"+"\n"+"Please Register..."+"\n");
				}
			}	
		}		
	}

	//used to find winner
	void winner() throws Exception
	{
		PreparedStatement mt=con.prepareStatement("select CNAME,CSYMBOL from candidate having max(CNOV)");
		ResultSet rps=mt.executeQuery();
		while(rps.next())
		{
			System.out.println("Winner name: "+rps.getString(1)+"\n"+"Winner Symbol: "+rps.getString(2));
		}
	}

	//voting process by voter
	void vote(String name) throws Exception
	{
					
		int p=0,i=0;
		String nvoted="false";
		PreparedStatement om=con.prepareStatement("select VOTE from register where USN='"+name+"'");
		ResultSet rp=om.executeQuery();
		while(rp.next())
		{
			if(rp.getString(1).equals(nvoted))
			{
				Scanner oss=new Scanner(System.in);
				System.out.println("Please choose the Symbol of Party you want to Vote");
				PreparedStatement sms=con.prepareStatement("select CSYMBOL from candidate");
				ResultSet sts=sms.executeQuery();
				while(sts.next())
				{
					p++;
					System.out.println(p+". "+sts.getString(1));
				}
				p=0;
				System.out.println("Choice is :");
				int chh=oss.nextInt();
				ResultSet sys=sms.executeQuery();
				while(sys.next())
				{
					p++;
					if(p==chh)
					{
						System.out.println("Your Vote is for: "+sys.getString(p));
						PreparedStatement som=con.prepareStatement("update register set VSYMBOL='"+sys.getString(p)+"' where USN='"+name+"'");
						int m=som.executeUpdate();
						PreparedStatement so=con.prepareStatement("update register set VOTE='true' where USN='"+name+"'");
						int w=so.executeUpdate();
						PreparedStatement sm=con.prepareStatement("select CNOV from candidate where CSYMBOL='"+sys.getString(p)+"'");
						ResultSet rss=sm.executeQuery();
						while(rss.next())
						{
							int j=rss.getInt(1)+1;
							PreparedStatement sim=con.prepareStatement("update candidate set CNOV='"+j+"' where CSYMBOL='"+sys.getString(p)+"'");
							int ss=sim.executeUpdate();	
						}	
						break;	
					}
				}
			}
			else
			{
				System.out.println("You have already given your vote");		
				break;	
			}
		}
	}

	//new voter's registration
	void register() throws Exception
	{
		int d=0,flag;
		System.out.println("Enter Voter's details");
		System.out.print("name:         ");
		NAME=cs.nextLine();
		flag=NAME.length();
		while(flag==NAME.length())
		{ d=0;
		do{
			flag=1;
			char ch=NAME.charAt(d);
			if (ch=='0'||ch=='1'||ch=='2'||ch=='3'||ch=='4'||ch=='5'||ch=='6'||ch=='7'||ch=='8'||ch=='9')
			{
				System.out.println("Invalid name...Please enter correct name");
				System.out.print("name:         ");
				NAME=cs.nextLine();
				flag=NAME.length();
				break;
			}
			else
			{
				flag++;
			}
			d++;
		}while(d<NAME.length());
		} 
		
		System.out.print("usn:          ");
		USN=cs.nextLine();
		System.out.println("course(BE/Btech/MCA/BCA/BSc/MSc/BBA/MBA/PhD):        ");
		COURSE=cs.nextLine();
		System.out.print("email(***@xxxx.com):        ");
		EMAIL=cs.nextLine();
		System.out.print("password:     ");
		PASS=cs.nextLine();
		/*if(COURSE!=("BE")||COURSE!="Btech"||COURSE!="MCA"||COURSE!="BCA"||COURSE!="BSc"||COURSE!="MSc"||COURSE!="BBA"||COURSE!="MBA"||COURSE!="PhD")
		{
			System.out.println("course(BE/Btech/MCA/BCA/BSc/MSc/BBA/MBA/PhD): **case sensitive**       ");
			COURSE=cs.nextLine();
			//if(COURSE.equals("BE")||COURSE.equals("Btech")||COURSE.equals("MCA")||COURSE.equals("BCA")||COURSE.equals("BSc")||COURSE.equals("MSc")||COURSE.equals("BBA")||COURSE.equals("MBA")||COURSE.equals("PhD"))
			{
				//break;
			}
		}*/
		System.out.println("semester:(1/2/3/4/5/6/7/8)     ");
		SEM=cs.nextInt();
		while(SEM<1 || SEM>8)
		{
			System.out.println("\n"+"Invalid Semester"+"\n"+"semester:(1/2/3/4/5/6/7/8)     ");
			SEM=cs.nextInt();
			if(SEM>=1 && SEM<=8)
			{
				break;
			}
		}
		System.out.println("age:(18-99)          ");
		AGE=cs.nextInt();
		while(AGE>=100 || AGE<=18)
		{
			System.out.println("\n"+"Invalid Age"+"\n"+"age(18-99):          ");
			AGE=cs.nextInt();
			if(AGE<=100 && AGE>=18)
			{
				break;
			}
		}

		SYMBOL="not voted";
		VOTE="false";
		PreparedStatement psi=con.prepareStatement("insert into register values('"+NAME+"','"+USN+"','"+COURSE+"','"+SEM+"','"+AGE+"','"+EMAIL+"','"+SYMBOL+"','"+PASS+"','"+VOTE+"')");
		int i=psi.executeUpdate();
		if(i>0)
		{
			System.out.println("\n"+" Registered Successfully!!!");
		}
		else
		{
			System.out.println("Some error occured while Registration!!!"+"\n"+"Please try again...");
		}	
	}

	public static void main(String[] args) throws Exception
	{
		Project ob=new Project();
		ob.start();
		try
		{
			System.out.println("************************************************************************************************************************************************************************************************************************************************************************************************************************************************");
			System.out.println("								        WELCOME TO COLLEGE VOTING SYSTEM								    ");
			System.out.println("************************************************************************************************************************************************************************************************************************************************************************************************************************************************");
			int chce;
			do{
				System.out.println("Enter your choice:"+"\n"+"1.Register"+"\n"+"2.Login"+"\n"+"3.Exit");
				Scanner sa=new Scanner(System.in);
				chce=sa.nextInt();
				switch(chce)
				{
					case 1: ob.register();	break;
					case 2: ob.login();	break;
					case 3: return;
					default: System.out.println("Invalid input");
				}
			}while(chce!=3);	
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}	
}