package demo_wc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WC_command {
	private static BufferedReader br = null;
	private static String s = null;
	
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		
		System.out.println("------------------------------------------");
		System.out.println("---------欢迎来到黄飞越的WC系统-----------");
		System.out.println("------------------------------------------");
		System.out.println("------------------------------------------");
		System.out.println("-c : 返回文件的字符数");
		System.out.println("-w ：返回文件的词数");
		System.out.println("-l : 返回文件的行数");
		System.out.println("-h : 帮助");
		System.out.println("-e : 退出");
		System.out.println("-s : 递归处理目录下符合条件的文件");
		System.out.println("-a : 返回更复杂的数据（代码行 / 空行 / 注释行）");
		System.out.println("------------------------------------------");
		
		boolean exit = false;
		while(!exit){
		System.out.println("请输入命令+路径 :[-cwlhe] [-sa] 文件路径");
		byte[]input = new byte[100];
		try {
			int n = System.in.read(input);
			String inputs = new String(input,0,n);
			String command[] = inputs.split(" ");
			int commands = command.length;//取出指令的个数，如果等于2即代表有一个指令，后面一个为路径；
			File inFile = null;
			if(commands == 2){//若指令为一个，文件路径为第3个字节后的字符串
				inFile = new File(new String(input,3,n-5));}
			else if(commands == 3){//指令为两个，则为第6个后
				inFile = new File(new String(input,6,n-8));
			}

			if (commands == 1){//当指令为-h或-e时
				if(command[0].trim().equals("-h")){
					System.out.println("------------------------------------------");
					System.out.println("-c : 返回文件的字符数");
					System.out.println("-w ：返回文件的词数");
					System.out.println("-l :返回文件的行数");
					System.out.println("-h :帮助");
					System.out.println("-e : 退出");
					System.out.println("-s :递归处理目录下符合条件的文件");
					System.out.println("-a :返回更复杂的数据（代码行 / 空行 / 注释行）");
				}
				else if(command[0].trim().equals("-e")){
					exit = true ;
				}else {
					System.out.println("输入指令有误请重新输入！");
				}
			}
			else {//当指令为其他的时候
				switch(command[0]){//检测用户输入的第一个命令
			case "-c": 
				if(commands == 3 ){
					if(command[1].equals("-s")){
					lists(inFile, 'c');
					System.out.println();}
					else if(command[1].equals("-a")){				
						codeLine(inFile, 'c');
					}
					else {
						System.out.println("输入命令有误！请重新输入。");
					}
				}
				else {Acc_c(inFile);}break;
				
			case "-w":
				if(commands == 3 ){
					if(command[1].equals("-s")){
					lists(inFile, 'w');}
					else if(command[1].equals("-a")){
						codeLine(inFile, 'w');
					}
					else {
						System.out.println("输入命令有误！请重新输入。");
					}
				}
				else if (commands == 2){Acc_w(inFile);} else
					System.out.println("输入有误，请重新输入！");break;
				
			case "-l":
				if(commands == 3 ){
					if(command[1].equals("-s")){
					lists(inFile, 'l');}
					else if(command[1].equals("-a")){
						codeLine(inFile, 'l');
					}
					else {
						System.out.println("输入命令有误！请重新输入。");
					}
				}
				else if(commands == 2) {Acc_l(inFile);} 
				else 	System.out.println("输入有误，请重新输入！");break;
				
			
			default :System.out.println(command[0]); //用户第一个命令输入错误
				System.out.println("第一个命令就错了你还想怎样？重新输入！");break;
			}//end of switch
		}// end of else
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("输入有误，请重新输入！");return;
		}
		System.out.println("------------------------------------------");
		
		} //end of while
		System.out.println("如果觉得好用别忘了给小编点个赞噢！");
	}
	
	/*
	 计算文件的字符数
	 */
	public static void Acc_c(File infile){

		try {
			int amount = 0;
			br = new BufferedReader(new FileReader(infile));
			while((s=br.readLine())!= null){
				amount += s.length();
			}
			System.out.println("/"+infile.getName()+" 的字符数为 :"+amount);
			br.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
        	System.out.println("找不到相应文件，请重新输入！");return;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 计算文件的词数
	 */
	public static void Acc_w(File infile){

		try {
			int amount = 0;
			br = new BufferedReader(new FileReader(infile));
			while((s=br.readLine())!= null){
				amount += s.split(" ").length;
			}
			System.out.println("/"+infile.getName()+" 的词数为 :"+amount);
			br.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
        	System.out.println("找不到相应文件，请重新输入！");return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/*
	 计算文件的行数
	 */
	public static void Acc_l(File infile){
		try {
			int amount = 0;
			br = new BufferedReader(new FileReader(infile));
			while((s=br.readLine())!= null){
				amount ++;
			}
			System.out.println("/"+infile.getName()+"的总行数为 : "+amount);
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
        	System.out.println("找不到相应文件，请重新输入！");return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	递归目录的文件
	 */
	public static void lists(File dir,char method){
        if(dir.isFile()){
            System.out.println("不是目录，无效");
            return;
        }
        System.out.println();
        System.out.println("[dir]:"+dir);
        File[] files = dir.listFiles();
        for(File file:files){
            if(file.isDirectory()){
                lists(file,method);
            }else{//看要递归调用哪个查询方法
                if(method == 'c'){
                	Acc_c(file);
                }else if(method == 'w'){
                	Acc_w(file);
                }else {
                	Acc_l(file);
                }
            }
        }    
    }
	
	/*
	 计算代码行 / 空行 / 注释行数
	 */
	public static void codeLine(File infile,char method){
		try {
			int codeLine = 0, blankLine = 0,antLine = 0;
			String s = null;
            br = new BufferedReader(new FileReader(infile));
            boolean comm = false;
            while((s = br.readLine()) != null) {
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
            System.out.println("------------------------------------------");
            br.close();
            if(method == 'c'){
            	Acc_c(infile);
            }else if(method == 'w'){
            	Acc_w(infile);
            }else {
            	Acc_l(infile);
            }				
            
            System.out.println("/"+infile.getName()+" 的代码行数为 ："+codeLine);
            System.out.println("/"+infile.getName()+" 的注释行数为 ："+antLine);
            System.out.println("/"+infile.getName()+" 的空白行数为 ："+blankLine);

        } catch (FileNotFoundException e) {
        	System.out.println("找不到相应文件，请重新输入！");return;
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
