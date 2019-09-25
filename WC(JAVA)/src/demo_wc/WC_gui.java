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
         Container conn = jf.getContentPane();    //得到窗口的容器
         
         Panel choose = new Panel();
         JButton chooseFile = new JButton("选择文件直接查询");
         choose.add(chooseFile);
         
         JTextArea show = new JTextArea();
         show.setEditable(false);
         show.setFont(new Font("宋体",Font.BOLD,24));
         show.setText("欢迎使用WC文件查询系统，请点击按钮选择文件进行查询！");
         jf.add(show);
         
         //对于按了按钮选择的文件进行相应查询以及相应的显示
         chooseFile.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				FileDialog fdl=new FileDialog(jf, "选择文件");
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
					show.append("/*"+inFile.getName()+" 的统计数据如下：");
					show.append("\n文件的字符数 :"+cs);
					show.append("\n文件的词数 :"+ws);
					show.append("\n文件的行数 :"+ls);
					show.append("\n文件的注释行数 ："+antLine);
					show.append("\n文件的空白行数 ："+blankLine);
					show.append("\n文件的代码行数 ："+codeLine);

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					System.out.println("找不到文件，请重新！");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		});
 			
 			
         conn.add(choose,BorderLayout.NORTH);
        jf.setBounds(300,250,800,500); //设置窗口的属性 窗口位置以及窗口的大小
        jf.setResizable(false);
        jf.setVisible(true);//设置窗口可见
        jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //设置关闭方式 如果不设置的话 似乎关闭窗口之后不会退出程序
     }
     

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new WC_gui("窗口");
	}






}
