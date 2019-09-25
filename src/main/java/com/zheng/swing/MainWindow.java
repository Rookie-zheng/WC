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
        f=new JFrame("���Դ���");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane=f.getContentPane();
        //        textArea=new JTextArea();
        JScrollPane scrollPane=new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 300));
        //JTextArea textArea = new JTextArea(10, 10);
        JPanel panel=new JPanel();
        JMenuBar menuBar1=new JMenuBar();  //��Ӳ˵������
        f.setJMenuBar(menuBar1);          //���˵�����ӵ�����������
        JMenu menu1=new JMenu("�ļ�");
        JMenu menu2=new JMenu("�ַ���");
        JMenu menu3=new JMenu("������");
        JMenu menu4=new JMenu("����");
        JMenu menu5=new JMenu("��չ");
        JMenu menu6=new JMenu("ȫ��");
        
        //���˵������ӵ��˵��������
        menuBar1.add(menu1);
        menuBar1.add(menu2);
        menuBar1.add(menu3);
        menuBar1.add(menu4);
        menuBar1.add(menu5);
        menuBar1.add(menu6);
        
        
        //�����˵������
        JMenuItem item1=new JMenuItem("��");
        JMenuItem item4=new JMenuItem("�˳�");
        menu1.add(item1);
        menu1.addSeparator();           //�˵���֮��ķָ������
        menu1.add(item4);
        //���ö���������Ŀɼ���
        f.setVisible(true);
        label=new JLabel("",JLabel.CENTER);
        contentPane.add(label, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(panel, BorderLayout.SOUTH);
        f.pack();
        //�����˳��˵���Ķ����¼�
        item4.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed( ActionEvent e) {
                // TODO Auto-generated method stub
                int i=JOptionPane.showConfirmDialog(null, "�Ƿ�����˳�ϵͳ",
                        "�˳�ȷ�϶Ի���",JOptionPane.YES_NO_CANCEL_OPTION);
                //ͨ���Ի����а�ť��ѡ�����������������yesʱ������ֱ����ʧ
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
					textArea.append("�ַ���" + cst.charNum(file)+"\r\n");
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
					textArea.append("������" + wst.wordsNum(file)+"\r\n");
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
					textArea.append("����" + lst.lineNum(file)+"\r\n");
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
					System.out.println("��������Ϊ��" + lineNotEmptyNum);
					System.out.println("�ո�����Ϊ��" + lineEmptyNum);
					System.out.println("ע������Ϊ��" + noteNum);
					textArea.append("��������Ϊ��" + lineNotEmptyNum + "\r\n"
							+ "�ո�����Ϊ��" + lineEmptyNum + "\r\n"
							+ "ע������Ϊ��" + noteNum +"\r\n");
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
					System.out.println("��������Ϊ��" + lineNotEmptyNum);
					System.out.println("�ո�����Ϊ��" + lineEmptyNum);
					System.out.println("ע������Ϊ��" + noteNum);
					textArea.append("�ַ���" + cst.charNum(file)+"\r\n");
					textArea.append("������" + wst.wordsNum(file)+"\r\n");
					textArea.append("����" + lst.lineNum(file)+"\r\n");
					textArea.append("��������Ϊ��" + lineNotEmptyNum + "\r\n"
							+ "�ո�����Ϊ��" + lineEmptyNum + "\r\n"
							+ "ע������Ϊ��" + noteNum +"\r\n");
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
        
    public void readFile(File file){//���ļ�
        BufferedReader bReader;
        try {
            bReader=new BufferedReader(new FileReader(file));
            StringBuffer sBuffer=new StringBuffer();
            String str;
            while((str=bReader.readLine())!=null){
                sBuffer.append(str+'\n');
                System.out.println(str);
            }
            textArea.setText("�ļ�����" + file.getPath() +"\r\n");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
