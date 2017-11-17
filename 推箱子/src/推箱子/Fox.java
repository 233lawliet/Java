package 推箱子;

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
    
    String  music_name[]={"似水流年","与龙共舞","往事随风"};
    public Fox(){
      Container c = getContentPane();
      c.setLayout(new BorderLayout());
        
	  Image  ball=new ImageIcon("C:/Users/17854/Desktop/java/box(html)/images/ball.png").getImage();
	  this.setIconImage(ball);
	  this.setTitle("推箱子游戏");
	  this.setSize(700,680);
	  this.setLocationRelativeTo(null);
	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  
	  //标题设置
	  title=new JLabel("2017版推箱子",JLabel.CENTER);
	  title.setFont(new Font("隶书",Font.BOLD,30));
	  title.setForeground(Color.BLUE);
	  this.add(title,BorderLayout.NORTH);
	 
	  //按钮定义
	  back=new JButton("悔一步");
	  next_lever=new JButton("下一关");
	  last_lever=new JButton("上一关");
	  first_lever=new JButton("第一关");
	  end_lever=new JButton("最后一关");
	  chooce_lever=new JButton("选择关卡");
	  again=new JButton("重来此关");
	  music_switch=new JButton("音乐关");
	  music=new JComboBox(music_name);
	  help=new JButton("帮助文档");
	  //按钮事件注册
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
	 //不设置鼠标光圈
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
	  
	//按钮添加
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
	  
	  //添加主板
	  this.add(foxPanel,BorderLayout.CENTER);
	  //foxPanel.addKeyListener(foxPanel);
	  this.addKeyListener(foxPanel);
	  foxPanel.setFocusable(true);
	  //添加音乐
	  
	  sound=new Music();
	  //可视化
	  this.setVisible(true);
    }
	public  static void main(String args[]){
		Fox fox=new Fox();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//重来
		  if(e.getSource()==again){
			foxPanel.init(foxPanel.level);
		}
		//悔一步
		else if(e.getSource()==back){
			foxPanel.back();
		}
		//下一关
		else if(e.getSource()==next_lever){
			if(foxPanel.level==foxPanel.Max_level) ;
			else foxPanel.level++;
			foxPanel.init(foxPanel.level);
		}
		//上一关
		else if(e.getSource()==last_lever){
			if(foxPanel.level==1) ;
			else foxPanel.level--;
			foxPanel.init(foxPanel.level);
		}
		//第一关
		else if(e.getSource()==first_lever){
			foxPanel.level=1;
			foxPanel.init(foxPanel.level);
		}
		//最后一关
		else if(e.getSource()==end_lever){
			foxPanel.level=foxPanel.Max_level;
			foxPanel.init(foxPanel.level);
		}
		//选关
		else if(e.getSource()==chooce_lever){
			String str="请选择关卡:1~"+foxPanel.Max_level+"  ";
			String chooce=JOptionPane.showInputDialog(null,str,"关卡选择",JOptionPane.YES_NO_OPTION);
			//1 父窗口  2 窗口内容  3 窗口title  4 窗口的显示按钮
		    try{
		    	foxPanel.level=Integer.parseInt(chooce);
		    }catch(Exception ex){
		    	JOptionPane.showMessageDialog(null,"关卡应该为数字显示");
		    }finally{
		    	
		    	foxPanel.init(foxPanel.level);
		    }    
	    }
		else if(e.getSource()==music_switch){
			String music_state=music_switch.getText();
			if(music_state.equals("音乐开")){
				music_switch.setText("音乐关");
			    sound.music_state=false;
			}else if(music_state.equals("音乐关")){
				music_switch.setText("音乐开");
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
