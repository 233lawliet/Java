package ������;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.nio.file.*;

public class Fox extends   JFrame implements ActionListener,ItemListener{
    public JLabel title;
    public JButton back,next_lever,last_lever,first_lever,end_lever,chooce_lever,again,music_switch,help; 
	public JComboBox  music;
	public Music sound;
    public JPanel  right_panel;
    public FoxPanel  foxPanel=new FoxPanel();
    
    String  music_name[]={"��ˮ����","��������","�������"};
    public Fox(){
      Container c = getContentPane();
      c.setLayout(new BorderLayout());
        
	  Image  ball=new ImageIcon("C:/Users/17854/Desktop/java/box(html)/images/ball.png").getImage();
	  this.setIconImage(ball);
	  this.setTitle("��������Ϸ");
	  this.setSize(700,680);
	  this.setLocationRelativeTo(null);
	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  
	  //��������
	  title=new JLabel("2017��������",JLabel.CENTER);
	  title.setFont(new Font("����",Font.BOLD,30));
	  title.setForeground(Color.BLUE);
	  this.add(title,BorderLayout.NORTH);
	 
	  //��ť����
	  back=new JButton("��һ��");
	  next_lever=new JButton("��һ��");
	  last_lever=new JButton("��һ��");
	  first_lever=new JButton("��һ��");
	  end_lever=new JButton("���һ��");
	  chooce_lever=new JButton("ѡ��ؿ�");
	  again=new JButton("�����˹�");
	  music_switch=new JButton("���ֹ�");
	  music=new JComboBox(music_name);
	  help=new JButton("�����ĵ�");
	  //��ť�¼�ע��
	  back.addActionListener(this);
	  next_lever.addActionListener(this);
	  last_lever.addActionListener(this);
	  first_lever.addActionListener(this);
	  end_lever.addActionListener(this);
	  chooce_lever.addActionListener(this);
	  again.addActionListener(this);
	  music_switch.addActionListener(this);
	  music.addItemListener(this);
	  help.addActionListener(this);
	 //����������Ȧ
	  back.setFocusable(false);
	  next_lever.setFocusable(false);
	  last_lever.setFocusable(false);
	  first_lever.setFocusable(false);
	  end_lever.setFocusable(false);
	  chooce_lever.setFocusable(false);
	  again.setFocusable(false);
	  music.setFocusable(false);
	  music_switch.setFocusable(false);
	  help.setFocusable(false);
	  
	//��ť���
	  right_panel=new JPanel(new GridLayout(10,1,8,8));
	  right_panel.add(back);
	  right_panel.add(again);
	  right_panel.add(next_lever);
	  right_panel.add(last_lever);
	  right_panel.add(first_lever);
	  right_panel.add(end_lever);
	  right_panel.add(chooce_lever);
	  right_panel.add(music_switch);
	  right_panel.add(music);
	  this.add(right_panel,BorderLayout.EAST);
	  
	  //�������
	  this.add(foxPanel,BorderLayout.CENTER);
	  //foxPanel.addKeyListener(foxPanel);
	  this.addKeyListener(foxPanel);
	  foxPanel.setFocusable(true);
	  //�������
	  
	  sound=new Music();
	  //���ӻ�
	  this.setVisible(true);
    }
	public  static void main(String args[]){
		Fox fox=new Fox();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//����
		  if(e.getSource()==again){
			foxPanel.init(foxPanel.level);
		}
		//��һ��
		else if(e.getSource()==back){
			foxPanel.back();
		}
		//��һ��
		else if(e.getSource()==next_lever){
			if(foxPanel.level==foxPanel.Max_level) ;
			else foxPanel.level++;
			foxPanel.init(foxPanel.level);
		}
		//��һ��
		else if(e.getSource()==last_lever){
			if(foxPanel.level==1) ;
			else foxPanel.level--;
			foxPanel.init(foxPanel.level);
		}
		//��һ��
		else if(e.getSource()==first_lever){
			foxPanel.level=1;
			foxPanel.init(foxPanel.level);
		}
		//���һ��
		else if(e.getSource()==end_lever){
			foxPanel.level=foxPanel.Max_level;
			foxPanel.init(foxPanel.level);
		}
		//ѡ��
		else if(e.getSource()==chooce_lever){
			String str="��ѡ��ؿ�:1~"+foxPanel.Max_level+"  ";
			String chooce=JOptionPane.showInputDialog(null,str,"�ؿ�ѡ��",JOptionPane.YES_NO_OPTION);
			//1 ������  2 ��������  3 ����title  4 ���ڵ���ʾ��ť
		    try{
		    	foxPanel.level=Integer.parseInt(chooce);
		    }catch(Exception ex){
		    	JOptionPane.showMessageDialog(null,"�ؿ�Ӧ��Ϊ������ʾ");
		    }finally{
		    	
		    	foxPanel.init(foxPanel.level);
		    }    
	    }
		else if(e.getSource()==music_switch){
			String music_state=music_switch.getText();
			if(music_state.equals("���ֿ�")){
				music_switch.setText("���ֹ�");
			    sound.music_state=false;
			}else if(music_state.equals("���ֹ�")){
				music_switch.setText("���ֿ�");
				sound.music_state=true;
			}
			sound.load_music();
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		System.out.println("\n"+music.getSelectedIndex());
		int  index=music.getSelectedIndex();
		sound.stop_music();
		sound.set_music(music_name[index]+".mid");
		sound.load_music();
	}
	
	
}
