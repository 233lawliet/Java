package 推箱子;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FoxPanel extends JPanel implements KeyListener{
	public int  level=1;
    Stack<Integer>  step;
    public int last_data[][]=new int[16][16];
    public int person_row=0,person_column=0;
    final static  int Max_level=10;
	static  Image  image[]={
			new ImageIcon("src/images/block.gif").getImage(),
			new ImageIcon("src/images/wall.png").getImage(),
			new ImageIcon("src/images/ball.png").getImage(),
			new ImageIcon("src/images/box.png").getImage(),
			new ImageIcon("src/images/left.png").getImage(),
			new ImageIcon("src/images/up.png").getImage(),
			new ImageIcon("src/images/right.png").getImage(),
			new ImageIcon("src/images/down.png").getImage()
	};
	public int  temp_data[][]=new int[16][16];
	public int  init_data[][]=new int[16][16];
	public FoxPanel(){
		this.level=1;
	    this.step=new Stack<Integer>();
		this.copyData(level1);
		this.addKeyListener(this);
		//this.setFocusable(true);
		
	}
	final int level1[][]={
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,1,2,1,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,1,0,1,1,1,1,0,0,0,0},
	{0,0,0,0,1,1,1,3,0,3,2,1,0,0,0,0},
	{0,0,0,0,1,2,0,3,7,1,1,1,0,0,0,0},
	{0,0,0,0,1,1,1,1,3,1,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,1,2,1,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	
	final int level2[][]={
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0},
	{0,0,0,0,1,4,0,0,1,0,0,0,0,0,0,0},
	{0,0,0,0,1,0,3,3,1,0,1,1,1,0,0,0},
	{0,0,0,0,1,0,3,0,1,0,1,2,1,0,0,0},
	{0,0,0,0,1,1,1,0,1,1,1,2,1,0,0,0},
	{0,0,0,0,0,1,1,0,0,0,0,2,1,0,0,0},
	{0,0,0,0,0,1,0,0,0,1,0,0,1,0,0,0},
	{0,0,0,0,0,1,0,0,0,1,1,1,1,0,0,0},
	{0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	

	final int level3[][]={
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0},
			{0,0,0,0,1,1,0,0,1,0,0,0,0,0,0,0},
			{0,0,0,0,1,4,3,0,1,0,0,0,0,0,0,0},
			{0,0,0,0,1,1,3,0,1,1,0,0,0,0,0,0},
			{0,0,0,0,1,1,0,3,0,1,0,0,0,0,0,0},
			{0,0,0,0,1,2,3,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,1,2,2,0,2,1,0,0,0,0,0,0},
			{0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};


	final int level4[][]={
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0},
			{0,0,0,0,0,1,7,0,1,1,1,0,0,0,0,0},
			{0,0,0,0,0,1,0,3,0,0,1,0,0,0,0,0},
			{0,0,0,0,1,1,1,0,1,0,1,1,0,0,0,0},
			{0,0,0,0,1,2,1,0,1,0,0,1,0,0,0,0},
			{0,0,0,0,1,2,3,0,0,1,0,1,0,0,0,0},
			{0,0,0,0,1,2,0,0,0,3,0,1,0,0,0,0},
			{0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};


	final int level5[][]={
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0},
			{0,1,1,1,1,0,0,0,0,0,1,0,0,0,0,0},
			{0,1,0,0,0,2,1,1,1,0,1,0,0,0,0,0},
			{0,1,0,1,0,1,0,0,0,0,1,1,0,0,0,0},
			{0,1,0,1,0,3,0,3,1,2,0,1,0,0,0,0},
			{0,1,0,1,0,0,7,0,0,1,0,1,0,0,0,0},
			{0,1,0,2,1,3,0,3,0,1,0,1,0,0,0,0},
			{0,1,1,0,0,0,0,1,0,1,0,1,1,1,0,0},
			{0,0,1,0,1,1,1,2,0,0,0,0,0,1,0,0},
			{0,0,1,0,0,0,0,0,1,1,0,0,0,1,0,0},
			{0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};


	final int level6[][]={
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0},
			{0,0,0,0,0,1,1,0,0,1,0,7,1,0,0,0},
			{0,0,0,0,0,1,0,0,0,1,0,0,1,0,0,0},
			{0,0,0,0,0,1,3,0,3,0,3,0,1,0,0,0},
			{0,0,0,0,0,1,0,3,1,1,0,0,1,0,0,0},
			{0,0,0,1,1,1,0,3,0,1,0,1,1,0,0,0},
			{0,0,0,1,2,2,2,2,2,0,0,1,0,0,0,0},
			{0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};


	final int level7[][]={
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0},
			{0,0,0,0,1,1,1,0,0,0,0,1,0,0,0,0},
			{0,0,0,1,1,2,0,3,1,1,0,1,1,0,0,0},
			{0,0,0,1,2,2,3,0,3,0,0,7,1,0,0,0},
			{0,0,0,1,2,2,0,3,0,3,0,1,1,0,0,0},
			{0,0,0,1,1,1,1,1,1,0,0,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};


	final int level8[][]={
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0},
			{0,0,0,1,0,0,1,1,0,0,0,1,0,0,0,0},
			{0,0,0,1,0,0,0,3,0,0,0,1,0,0,0,0},
			{0,0,0,1,3,0,1,1,1,0,3,1,0,0,0,0},
			{0,0,0,1,0,1,2,2,2,1,0,1,0,0,0,0},
			{0,0,1,1,0,1,2,2,2,1,0,1,1,0,0,0},
			{0,0,1,0,3,0,0,3,0,0,3,0,1,0,0,0},
			{0,0,1,0,0,0,0,0,1,0,7,0,1,0,0,0},
			{0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};


	final int level9[][]={
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0},
			{0,0,0,0,1,1,1,3,3,3,0,1,0,0,0,0},
			{0,0,0,0,1,7,0,3,2,2,0,1,0,0,0,0},
			{0,0,0,0,1,0,3,2,2,2,1,1,0,0,0,0},
			{0,0,0,0,1,1,1,1,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};


	final int level10[][]={
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,1,1,1,0,0,1,1,1,1,1,0,0},
			{0,0,1,1,0,0,1,0,0,1,0,0,0,1,0,0},
			{0,0,1,0,3,0,1,1,1,1,3,0,0,1,0,0},
			{0,0,1,0,0,3,2,2,2,2,0,3,0,1,0,0},
			{0,0,1,1,0,0,0,0,1,0,7,0,1,1,0,0},
			{0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};

	public void  init(int level){
		this.level=level;
		switch(level){
		case 1: copyData(level1); break;
		case 2: copyData(level2); break;
		case 3: copyData(level3); break;
		case 4: copyData(level4); break;
		case 5: copyData(level5); break;
		case 6: copyData(level6); break;
		case 7: copyData(level7); break;
		case 8: copyData(level8); break;
		case 9: copyData(level9); break;
		case 10: copyData(level10); break;
		}
		repaint();//重新画图
		step.clear();
	}
	void copyData(int data[][]){
	   for(int i=0;i<16;i++){
		  for(int j=0;j<16;j++){
		     this.temp_data[i][j]=data[i][j];
		     this.init_data[i][j]=data[i][j];
		     if(temp_data[i][j]>=4){
					person_row=i;
					person_column=j;
		     }
		 }
	  }		
	}	
	//画图函数
	public  void  paint(Graphics g){
		int left,top;
		for(int i=0;i<16;i++){
			for(int j=0;j<16;j++){
				left=34*i;
				top=34*j;
				if(temp_data[i][j]!=0)	g.drawImage(image[0], left, top,34,34,this);  //去除背景空白
       			g.drawImage(image[temp_data[i][j]], left, top,34,34,this);
			}
		}		
	     
	}
	
	public void go_left(){
		//左边为墙
		if(temp_data[person_row-1][person_column]==1);
		//左边为地板或者球
		else if(temp_data[person_row-1][person_column]==0||temp_data[person_row-1][person_column]==2){
			temp_data[person_row-1][person_column]=4;
			step.push(1);
			if(init_data[person_row][person_column]==2){
			  temp_data[person_row][person_column]=2;
			}
			else if(init_data[person_row][person_column]==0||init_data[person_row][person_column]==3||init_data[person_row][person_column]>=4){
				temp_data[person_row][person_column]=0;
			}
				person_row--;
		}
		//左边为箱子
		else if(temp_data[person_row-1][person_column]==3){
		   //左左为箱子或者墙
		   if(temp_data[person_row-2][person_column]==1||temp_data[person_row-2][person_column]==3);
		   //左左为球或者地板
		   else if(temp_data[person_row-2][person_column]==0||temp_data[person_row-2][person_column]==2){
			   temp_data[person_row-2][person_column]=3;
			   temp_data[person_row-1][person_column]=4;
			   step.push(5);
			   if(init_data[person_row][person_column]==2){
				 temp_data[person_row][person_column]=2;
			   }
			   else if(init_data[person_row][person_column]==0||init_data[person_row][person_column]==3||init_data[person_row][person_column]>=4){
			  	 temp_data[person_row][person_column]=0;
			   }
			   person_row--;
		   }
	   }
		repaint();
		judge_end();
		System.out.println(person_row+"+"+person_column);
	}
	public void go_up(){
	    //上边为墙
		if(temp_data[person_row][person_column-1]==1);
		//上边为球或者地板
		else if(temp_data[person_row][person_column-1]==0||temp_data[person_row][person_column-1]==2){
			temp_data[person_row][person_column-1]=5;
			 if(init_data[person_row][person_column]==2){
				  temp_data[person_row][person_column]=2;
			  }
			  else if(init_data[person_row][person_column]==0||init_data[person_row][person_column]==3||init_data[person_row][person_column]>=4){
				  temp_data[person_row][person_column]=0;
			  }
			 person_column--;
			 step.push(2);
		}
		//上边为箱子
		else if(temp_data[person_row][person_column-1]==3){
		  if(temp_data[person_row][person_column-2]==1||temp_data[person_row][person_column-2]==3);
		  else  if(temp_data[person_row][person_column-2]==0||temp_data[person_row][person_column-2]==2){
			  temp_data[person_row][person_column-2]=3;
			  temp_data[person_row][person_column-1]=5;
			  step.push(6);
			  if(init_data[person_row][person_column]==2){
				  temp_data[person_row][person_column]=2;
			  }
			  else if(init_data[person_row][person_column]==0||init_data[person_row][person_column]==3||init_data[person_row][person_column]>=4){
				  temp_data[person_row][person_column]=0;
			  }
			  person_column--;
		  }
		}
		repaint();
		judge_end();
		System.out.println(person_row+"+"+person_column);
	}
	public void go_right(){
		//右边为墙
		if(temp_data[person_row+1][person_column]==1);
		//右边为地板或者球
		else if(temp_data[person_row+1][person_column]==0||temp_data[person_row+1][person_column]==2){
			temp_data[person_row+1][person_column]=6;
			step.push(3);
			if(init_data[person_row][person_column]==2){
			  temp_data[person_row][person_column]=2;
			}
			else if(init_data[person_row][person_column]==0||init_data[person_row][person_column]==3||init_data[person_row][person_column]>=4){
				temp_data[person_row][person_column]=0;
			}
	    	person_row++;
		}
		//右边为箱子
		else if(temp_data[person_row+1][person_column]==3){
		   //右右为箱子或者墙
		   if(temp_data[person_row+2][person_column]==1||temp_data[person_row+2][person_column]==3);
		   //右右为球或者地板
		   else if(temp_data[person_row+2][person_column]==0||temp_data[person_row+2][person_column]==2){
			   temp_data[person_row+2][person_column]=3;
			   temp_data[person_row+1][person_column]=6;
			   step.push(7);
			   if(init_data[person_row][person_column]==2){
				 temp_data[person_row][person_column]=2;
			   }
			   else if(init_data[person_row][person_column]==0||init_data[person_row][person_column]==3||init_data[person_row][person_column]>=4){
			  	 temp_data[person_row][person_column]=0;
			   }
			   person_row++;
		   }
	   }
		repaint();
		judge_end();
		System.out.println(person_row+"+"+person_column);
	}
	public void go_down(){
	   //下边为墙
		if(temp_data[person_row][person_column+1]==1);
		//下边为球或者地板
		else if(temp_data[person_row][person_column+1]==0||temp_data[person_row][person_column+1]==2){
			temp_data[person_row][person_column+1]=7;
			step.push(4);
			 if(init_data[person_row][person_column]==2){
				  temp_data[person_row][person_column]=2;
			  }
			  else if(init_data[person_row][person_column]==0||init_data[person_row][person_column]==3||init_data[person_row][person_column]>=4){
				  temp_data[person_row][person_column]=0;
			  }
			 person_column++;
		}
		//下边为箱子
		else if(temp_data[person_row][person_column+1]==3){
		  if(temp_data[person_row][person_column+2]==1||temp_data[person_row][person_column+2]==3);
		  else  if(temp_data[person_row][person_column+2]==0||temp_data[person_row][person_column+2]==2){
			  temp_data[person_row][person_column+2]=3;
			  temp_data[person_row][person_column+1]=7;
			  step.push(8);
			  if(init_data[person_row][person_column]==2){
				  temp_data[person_row][person_column]=2;
			  }
			  else if(init_data[person_row][person_column]==0||init_data[person_row][person_column]==3||init_data[person_row][person_column]>=4){
				  temp_data[person_row][person_column]=0;
			  }
			  person_column++;
		  }
		}
		repaint();
		judge_end();
		System.out.println(person_row+"+"+person_column);
	}
	public void  judge_end(){
	  int num=0;
	  int current=0;
	  for(int i=0;i<16;i++){
	    for(int j=0;j<16;j++){
	     if(init_data[i][j]==3) num++;
	     if(temp_data[i][j]==3&&init_data[i][j]==2)  current++;
	    }
	  }
	  if(num==current){ 
		 JOptionPane.showMessageDialog(null,"恭喜你，通关了!");
	     if(this.level==this.Max_level) ;
		 else this.level++;
		 this.init(this.level);
	  }
	}
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<16;i++){
			for(int j=0;j<16;j++){
				last_data[i][j]=temp_data[i][j];
			}
		}
		switch(e.getKeyCode()){
		 case KeyEvent.VK_LEFT:go_left();break;
		 case KeyEvent.VK_UP:go_up();break;
		 case KeyEvent.VK_RIGHT:go_right();break;
		 case KeyEvent.VK_DOWN:go_down();break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
		
	}

	public void box_move(int  step){
		//右移动后
		if(step==5){
			temp_data[person_row-1][person_column]=3;
			if(init_data[person_row-2][person_column]==2){
				temp_data[person_row-2][person_column]=2;
			}
			else if(init_data[person_row-2][person_column]>=4||init_data[person_row-2][person_column]==3||init_data[person_row-2][person_column]==0){
				temp_data[person_row-2][person_column]=0;	
			}
		}
		//下移动后
		else if(step==6){
			temp_data[person_row][person_column-1]=3;
			if(init_data[person_row][person_column-2]==2){
				temp_data[person_row][person_column-2]=2;
			}
			else if(init_data[person_row][person_column-2]>=4||init_data[person_row][person_column-2]==3||init_data[person_row][person_column-2]==0){
				temp_data[person_row][person_column-2]=0;	
			}
		}
		//左移动后
		else if(step==7){
			temp_data[person_row+1][person_column]=3;
			if(init_data[person_row+2][person_column]==2){
				temp_data[person_row+2][person_column]=2;
			}
			else if(init_data[person_row+2][person_column]>=4||init_data[person_row+2][person_column]==3||init_data[person_row+2][person_column]==0){
				temp_data[person_row+2][person_column]=0;	
			}
		}
		//上移动后
		else if(step==8){
			temp_data[person_row][person_column+1]=3;
			if(init_data[person_row][person_column+2]==2){
				temp_data[person_row][person_column+2]=2;
			}
			else if(init_data[person_row][person_column+2]>=4||init_data[person_row][person_column+2]==3||init_data[person_row][person_column+2]==0){
				temp_data[person_row][person_column+2]=0;	
			}
		}
	}
	public void  back(){
		if(step.isEmpty()){
			System.out.println("你还未走，无法悔步~");
			return;
		}
		int cur_step=step.pop();
		System.out.println(cur_step);
		switch(cur_step){
		
			case 1:go_right();break;
			case 2:go_down();break;
			case 3:go_left();break;
			case 4:go_up();break;
			case 5:go_right();box_move(5);break;
			case 6:go_down();box_move(6);break;
			case 7:go_left();box_move(7);break;
			case 8:go_up();box_move(8);break;
		}
		step.pop();
		this.repaint();
	}
	
	

}