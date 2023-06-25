import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
// import java.awt.graphics.*;
public class QuizApp implements ActionListener,WindowListener{
	JFrame f,l,q,qu;
	JButton log,quiz,subuser,adduser,addques,nex,pre;
	JLabel user,pass,access,noofques,ques,op1,op2,op3,op4,ans,question,title;
	JTextField tu,no,anstext;
	JTextArea questextfield,op1text,op2text,op3text,op4text;
	JPasswordField tp;
	JRadioButton option1,option2,option3,option4;
	ButtonGroup bg;
	ArrayList<String[]> users,questions;
	File userfile,quesfile;
	FileWriter fw;
	String full = "",quesfull="";
	int count =0,limit=-1,i=0,score=0;
	boolean quizflag = false;
	QuizApp(){
		f = new JFrame("Quiz App");
		log = new JButton("Login");
		quiz = new JButton("Start Quiz");
		userfile = new File("users.txt");
		quesfile = new File("ques.txt");
		f.setSize(300,300);f.setLayout(null);
		f.add(log);f.add(quiz);
		log.setBounds(100,100,100,25);quiz.setBounds(100,140,100,25);
		f.setVisible(true);f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		log.addActionListener(this);
		quiz.addActionListener(this);
		users = new ArrayList<String[]>();
	}
	public void intializeUserFile(){
		try{
			FileReader fr = new FileReader(userfile);
			int s = 0;
			while((s=fr.read())!=-1){
				full+=(char)s;
			}
			String[] splittedstring = full.split("\r\n");
			for (String i:splittedstring){
				users.add(i.split(" "));
			}
			fr.close();
		}catch(IOException e){System.out.println(e);}
	}
	public void openCreateQuestions(){
		l.dispose();
		q = new JFrame("Questions Writer");
		limit = Integer.parseInt(JOptionPane.showInputDialog(q,"Enter the Limit : "));
		System.out.println("Limit "+limit);
		title = new JLabel("Question Writer");
		q.getContentPane().setBackground(new Color(100,190,50));
		ques = new JLabel("Question : ");
		questextfield = new JTextArea(3,20);

		op1 = new JLabel("Option 1 : ");
		op1text = new JTextArea(2,20);
		op2 = new JLabel("Option 2 : ");
		op2text = new JTextArea(2,20);

		op3 = new JLabel("Option 3 : ");
		op3text = new JTextArea(2,20);

		op4 = new JLabel("Option 4 : ");
		op4text = new JTextArea(2,20);
		ans = new JLabel("Answer : ");
		anstext = new JTextField(2);

		addques = new JButton("Add Question");
		q.setSize(600,600);q.setLayout(null);q.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		q.add(title);q.add(ques);q.add(questextfield);q.add(op1);q.add(op1text);q.add(op2);q.add(op2text);q.add(op3);q.add(op3text);q.add(op4);q.add(op4text);q.add(ans);q.add(anstext);q.add(addques);
		questextfield.setLineWrap(true);
		op1text.setLineWrap(true);
		op2text.setLineWrap(true);
		op3text.setLineWrap(true);
		op4text.setLineWrap(true);
		title.setBounds(240,40,300,20);
		ques.setBounds(100,80,120,20);
		questextfield.setBounds(250,80,240,50);
		op1.setBounds(100,140,120,20);
		op1text.setBounds(250,140,240,50);
		op2.setBounds(100,200,120,20);
		op2text.setBounds(250,200,240,50);
		op3.setBounds(100,260,120,20);
		op3text.setBounds(250,260,240,50);
		op4.setBounds(100,320,120,20);
		op4text.setBounds(250,320,240,50);
		ans.setBounds(100,380,120,20);
		anstext.setBounds(250,380,25,25);
		addques.setBounds(225,440,150,25);
		q.setVisible(true);
		try{
		fw = new FileWriter(quesfile);
		addques.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
				if(e.getSource()==addques){
					String question = questextfield.getText();
					String option1 = op1text.getText();
					String option2 = op2text.getText();
					String option3 = op3text.getText();
					String option4 = op4text.getText();
					String answer = "";
					if(Integer.parseInt(anstext.getText())==1){
						answer = op1text.getText();
						fw.write(question+"  "+option1+"  "+option2+"  "+option3+"  "+option4+"  "+option1+"\r\n");
						
					}
					else if(Integer.parseInt(anstext.getText())==2){
						answer = op2text.getText();
						fw.write(question+"  "+option1+"  "+option2+"  "+option3+"  "+option4+"  "+option2+"\r\n");

					}
					else if(Integer.parseInt(anstext.getText())==3){
						answer = op3text.getText();
						fw.write(question+"  "+option1+"  "+option2+"  "+option3+"  "+option4+"  "+option3+"\r\n");

					}
					else if(Integer.parseInt(anstext.getText())==4){
						answer = op4text.getText();
						fw.write(question+"  "+option1+"  "+option2+"  "+option3+"  "+option4+"  "+option4+"\r\n");
					}
					questextfield.setText("");
					op1text.setText("");
					op2text.setText("");
					op3text.setText("");
					op4text.setText("");
					anstext.setText("");
					count+=1;

					if(count == limit){
						q.dispose();
						l.dispose();
						fw.close();
					}
				}
				}catch(IOException f){System.out.println(f);}
			}
		});
		}catch(IOException f){System.out.println(f);}
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==log){
			l = new JFrame("Staff Login");
			l.setSize(300,300);l.setLayout(null);
			l.addWindowListener(this);
			user = new JLabel("Username : ");
			pass = new JLabel("Password : ");
			tu = new JTextField(20);
			tp = new JPasswordField(20);
			subuser = new JButton("Submit");
			adduser = new JButton("Add user");
			access=new JLabel("");
			l.add(user);l.add(tu);l.add(pass);l.add(tp);l.add(subuser);l.add(adduser);l.add(access);
			user.setBounds(30,50,70,20);tu.setBounds(125,50,120,25);pass.setBounds(30,80,70,20);tp.setBounds(125,80,120,25);subuser.setBounds(30,165,100,25);adduser.setBounds(145,165,100,25);access.setBounds(40,190,200,25);
			l.setVisible(true);
			subuser.addActionListener(this);
			adduser.addActionListener(this);
			intializeUserFile();
		}
		if(e.getSource()==subuser){
			String username = tu.getText();
			String password = new String(tp.getPassword());
			boolean flag = false;
			for (String[] i:users ) {
				if(username.equals(i[0]) && password.equals(i[1])){
					openCreateQuestions();
					flag = true;
					break;
				}
			}
			if(!flag){
				access.setText("Invalid Username or Password");
			}
		}
		if(e.getSource()==adduser){
			String username = tu.getText();
			String password = new String(tp.getPassword());
			boolean flag = true;
			for (String[] i:users) {
				if(username.equals(i[0]) && password.equals(i[1])){
					access.setText("User Already Available");
					flag = false;
					break;
				}
			}
			if(flag){
				try{
					FileWriter rewrite = new FileWriter(userfile);
					String[] s = {username,password};
					users.add(s);
					full+="\r\n"+username+" "+password;
					rewrite.write(full);
					rewrite.close();
				}catch(IOException f){System.out.println(e);}
			}
		}
		if(e.getSource()==quiz){
			try{
				f.dispose();
				FileReader fr = new FileReader(quesfile);
				questions = new ArrayList<String[]>();
				int s = 0;
				while((s=fr.read())!=-1){
					quesfull+=(char)s;
				}
				String[] splittedstring = quesfull.split("\r\n");				
				for (String i:splittedstring){
					questions.add(i.split("  "));
				}
				fr.close();
				quizflag = true;
			}catch(IOException f){System.out.println(f);}
			qu = new JFrame("Quiz");
			question = new JLabel("Question No "+(i+1)+" : "+questions.get(i)[0]);
			// question.setWordWrap(true);
			option1 = new JRadioButton(questions.get(i)[1]);
			option2 = new JRadioButton(questions.get(i)[2]);
			option3 = new JRadioButton(questions.get(i)[3]);
			option4 = new JRadioButton(questions.get(i)[4]);
			bg = new ButtonGroup();
			nex = new JButton("Next ->");
			pre = new JButton("<- Prev");
			qu.setSize(550,500);
			bg.add(option1);bg.add(option2);bg.add(option3);bg.add(option4);
			qu.setLayout(null);
			qu.add(question);qu.add(option1);qu.add(option2);qu.add(option3);qu.add(option4);qu.add(pre);qu.add(nex);
			question.setBounds(30,30,500,70);
			option1.setBounds(100,100,200,30);
			option2.setBounds(100,150,200,30);
			option3.setBounds(100,200,200,30);
			option4.setBounds(100,250,200,30);
			pre.setBounds(30,350,100,30);
			nex.setBounds(400,350,100,30);

			qu.setVisible(true);
			qu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			limit=questions.size();
			nex.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent a){
					try{
					if(option1.isSelected() && option1.getText().equals(questions.get(i)[5])){
						score+=1;
						System.out.println("Score "+score);
					}else if(option2.isSelected() && option2.getText().equals(questions.get(i)[5])){
						score+=1;
						System.out.println("Score "+score);
					}else if(option3.isSelected() && option3.getText().equals(questions.get(i)[5])){
						score+=1;
						System.out.println("Score "+score);
					}else if(option4.isSelected() && option4.getText().equals(questions.get(i)[5])){
						score+=1;
						System.out.println("Score "+score);
					}
					if(i<limit){
						i+=1;
					}
					if(i==limit){
						JOptionPane.showMessageDialog(qu,"Your Score in the Quiz is : "+score);
						qu.dispose();
						f.dispose();
					}
					question.setText("Question No "+(i+1)+" : "+questions.get(i)[0]);
					option1.setText(questions.get(i)[1]);
					option2.setText(questions.get(i)[2]);
					option3.setText(questions.get(i)[3]);
					option4.setText(questions.get(i)[4]);
					
					}catch(IndexOutOfBoundsException ee){}
				}
			});
			pre.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent a){
					score-=1;
					if(i>0){
						i-=1;
					}
					if(i==0){
						score=0;
					}
					question.setText("Question No "+(i+1)+" : "+questions.get(i)[0]);
					option1.setText(questions.get(i)[1]);
					option2.setText(questions.get(i)[2]);
					option3.setText(questions.get(i)[3]);
					option4.setText(questions.get(i)[4]);
					// option1.setSelected(false);
					// option2.setSelected(false);
					// option3.setSelected(false);
					// option4.setSelected(false);
					System.out.println("Score "+score);
				}
			});
		}
	}
	public void windowClosed(WindowEvent e){
		if(e.getSource()==l){l.dispose();}
	}
	public void windowDeactivated(WindowEvent e){}public void windowActivated(WindowEvent e){}public void windowDeiconified(WindowEvent e){}public void windowIconified(WindowEvent e){}public void windowClosing(WindowEvent e){}public void windowOpened(WindowEvent e){}
	public static void main(String[] args) {new QuizApp();}
}