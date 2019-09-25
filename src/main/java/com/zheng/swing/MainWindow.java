package com.zheng.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.zheng.base.CharStatistics;
import com.zheng.base.LineStatistics;
import com.zheng.base.WordsStatistics;
import com.zheng.expand.ExpandStatistics;

public class MainWindow extends JFrame{
	JFrame f;
    JLabel label;
    JTextArea textArea= new JTextArea(10, 10);;
    JFileChooser fileChooser;
    FileInputStream fileInStream;
    static File file;
    CharStatistics cst = new CharStatistics();
    LineStatistics lst = new LineStatistics();
    WordsStatistics wst = new WordsStatistics();
    ExpandStatistics est = new ExpandStatistics();

    public MainWindow() {
        // TODO Auto-generated constructor stub
        f=new JFrame("测试窗口");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane=f.getContentPane();
        //        textArea=new JTextArea();
        JScrollPane scrollPane=new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 300));
        //JTextArea textArea = new JTextArea(10, 10);
        JPanel panel=new JPanel();
        JMenuBar menuBar1=new JMenuBar();  //添加菜单条组件
        f.setJMenuBar(menuBar1);          //将菜单栏添加到顶层容器中
        JMenu menu1=new JMenu("文件");
        JMenu menu2=new JMenu("字符数");
        JMenu menu3=new JMenu("单词数");
        JMenu menu4=new JMenu("行数");
        JMenu menu5=new JMenu("扩展");
        JMenu menu6=new JMenu("全部");
        
        //将菜单组件添加到菜单条组件中
        menuBar1.add(menu1);
        menuBar1.add(menu2);
        menuBar1.add(menu3);
        menuBar1.add(menu4);
        menuBar1.add(menu5);
        menuBar1.add(menu6);
        
        
        //创建菜单项组件
        JMenuItem item1=new JMenuItem("打开");
        JMenuItem item4=new JMenuItem("退出");
        menu1.add(item1);
        menu1.addSeparator();           //菜单项之间的分隔线组件
        menu1.add(item4);
        //设置顶层容器类的可见性
        f.setVisible(true);
        label=new JLabel("",JLabel.CENTER);
        contentPane.add(label, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(panel, BorderLayout.SOUTH);
        f.pack();
        //处理退出菜单项的动作事件
        item4.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed( ActionEvent e) {
                // TODO Auto-generated method stub
                int i=JOptionPane.showConfirmDialog(null, "是否真的退出系统",
                        "退出确认对话框",JOptionPane.YES_NO_CANCEL_OPTION);
                //通过对话框中按钮的选择来决定结果，单机yes时，窗口直接消失
                if(i==0)
                    f.dispose();
                
            }
        });
        item1.addActionListener(new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JFileChooser chooser = new JFileChooser();
                if (chooser.showOpenDialog(item1)==JFileChooser.APPROVE_OPTION) {//
                    file = chooser.getSelectedFile();
                    textArea.setText(file.getName()+":"+file.getPath()+"\n"+file.length());
                    readFile(file);
                };
                  
            }       
        });

        menu2.addMenuListener(new MenuListener() {
			
			@Override
			public void menuSelected(MenuEvent e) {
				try {
					System.out.println(cst.charNum(file));
					textArea.append("字符数" + cst.charNum(file)+"\r\n");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			@Override
			public void menuDeselected(MenuEvent e) {
				
			}
			@Override
			public void menuCanceled(MenuEvent e) {
				
			}
		});
        menu3.addMenuListener(new MenuListener() {
			
			@Override
			public void menuSelected(MenuEvent e) {
				try {
					System.out.println(wst.wordsNum(file));
					textArea.append("单词数" + wst.wordsNum(file)+"\r\n");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			@Override
			public void menuDeselected(MenuEvent e) {
			}
			
			@Override
			public void menuCanceled(MenuEvent e) {
			}
		});
        menu4.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
				try {
					System.out.println(lst.lineNum(file));
					textArea.append("行数" + lst.lineNum(file)+"\r\n");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void menuDeselected(MenuEvent e) {
			}
			@Override
			public void menuCanceled(MenuEvent e) {
			}
		});
        menu5.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				int lineNotEmptyNum = 0;
				int lineEmptyNum = 0;
				int noteNum = 0;
				try {
					est.demoFileStatistics(file.toString());
					String[] sSplit = est.demoFileStatistics1(file.toString()).split(" ");
					lineNotEmptyNum = Integer.parseInt(sSplit[0]);
					lineEmptyNum = Integer.parseInt(sSplit[1]);
					noteNum = Integer.parseInt(sSplit[2]);
					System.out.println("代码行数为：" + lineNotEmptyNum);
					System.out.println("空格行数为：" + lineEmptyNum);
					System.out.println("注释行数为：" + noteNum);
					textArea.append("代码行数为：" + lineNotEmptyNum + "\r\n"
							+ "空格行数为：" + lineEmptyNum + "\r\n"
							+ "注释行数为：" + noteNum +"\r\n");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void menuDeselected(MenuEvent e) {
			}
			
			@Override
			public void menuCanceled(MenuEvent e) {
			}
		});
        menu6.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				int lineNotEmptyNum = 0;
				int lineEmptyNum = 0;
				int noteNum = 0;
				try {
					est.demoFileStatistics(file.toString());
					String[] sSplit = est.demoFileStatistics1(file.toString()).split(" ");
					lineNotEmptyNum = Integer.parseInt(sSplit[0]);
					lineEmptyNum = Integer.parseInt(sSplit[1]);
					noteNum = Integer.parseInt(sSplit[2]);
					System.out.println("代码行数为：" + lineNotEmptyNum);
					System.out.println("空格行数为：" + lineEmptyNum);
					System.out.println("注释行数为：" + noteNum);
					textArea.append("字符数" + cst.charNum(file)+"\r\n");
					textArea.append("单词数" + wst.wordsNum(file)+"\r\n");
					textArea.append("行数" + lst.lineNum(file)+"\r\n");
					textArea.append("代码行数为：" + lineNotEmptyNum + "\r\n"
							+ "空格行数为：" + lineEmptyNum + "\r\n"
							+ "注释行数为：" + noteNum +"\r\n");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void menuDeselected(MenuEvent e) {
			}
			
			@Override
			public void menuCanceled(MenuEvent e) {
			}
		});
    }
        
    public void readFile(File file){//读文件
        BufferedReader bReader;
        try {
            bReader=new BufferedReader(new FileReader(file));
            StringBuffer sBuffer=new StringBuffer();
            String str;
            while((str=bReader.readLine())!=null){
                sBuffer.append(str+'\n');
                System.out.println(str);
            }
            textArea.setText("文件名：" + file.getPath() +"\r\n");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
