package demo_wc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class WC_gui extends JFrame{
	 public WC_gui(String title)
     {
        JFrame jf = new JFrame(title); 
         Container conn = jf.getContentPane();    //�õ����ڵ�����
         
         Panel choose = new Panel();
         JButton chooseFile = new JButton("ѡ���ļ�ֱ�Ӳ�ѯ");
         choose.add(chooseFile);
         
         JTextArea show = new JTextArea();
         show.setEditable(false);
         show.setFont(new Font("����",Font.BOLD,24));
         show.setText("��ӭʹ��WC�ļ���ѯϵͳ��������ťѡ���ļ����в�ѯ��");
         jf.add(show);
         
         //���ڰ��˰�ťѡ����ļ�������Ӧ��ѯ�Լ���Ӧ����ʾ
         chooseFile.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				FileDialog fdl=new FileDialog(jf, "ѡ���ļ�");
				fdl.setVisible(true);
				File inFile = new File(fdl.getDirectory()+fdl.getFile());
				byte[]input = new byte[100];
				BufferedReader br = null;
				
				int cs=0,ws=0,ls=0;
				int codeLine = 0, blankLine = 0,antLine = 0;
				boolean comm = false;
				String s = null;
				try {
					br = new BufferedReader(new FileReader(inFile));
					while((s=br.readLine())!= null){
						cs += s.length();
						ws += s.split(" ").length;
						ls ++;
						s = s.trim();
	                	if(s.startsWith("/*") && s.endsWith("*/")) {
	                        antLine ++;
	                    } else if(s.startsWith("//")) {
	                    	antLine++;
	                    } else if(s.startsWith("/*")&& !s.endsWith("*/") ) {
	                    	antLine++;
	                        comm = true;
	                    } else if(!s.startsWith("/*") && s.endsWith("*/")) {
	                    	antLine++;
	                        comm = false;
	                    } else if(comm) {
	                    	antLine++;
	                    } else if(s.length() < 1) {
	                    	blankLine++;
	                    } else {
	                    	codeLine++;
	                    } 
					}
					show.setText("");
					show.append("/*"+inFile.getName()+" ��ͳ���������£�");
					show.append("\n�ļ����ַ��� :"+cs);
					show.append("\n�ļ��Ĵ��� :"+ws);
					show.append("\n�ļ������� :"+ls);
					show.append("\n�ļ���ע������ ��"+antLine);
					show.append("\n�ļ��Ŀհ����� ��"+blankLine);
					show.append("\n�ļ��Ĵ������� ��"+codeLine);

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					System.out.println("�Ҳ����ļ��������£�");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		});
 			
 			
         conn.add(choose,BorderLayout.NORTH);
        jf.setBounds(300,250,800,500); //���ô��ڵ����� ����λ���Լ����ڵĴ�С
        jf.setResizable(false);
        jf.setVisible(true);//���ô��ڿɼ�
        jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //���ùرշ�ʽ ��������õĻ� �ƺ��رմ���֮�󲻻��˳�����
     }
     

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new WC_gui("����");
	}






}
