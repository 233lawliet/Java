package 推箱子;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import java.io.*;
import javazoom.jl.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;
public class Music extends Thread{
 String  music_path="D:\\eclipse程序夹\\推箱子\\src\\music\\";
 String  music_name="似水流年.mid";
 boolean music_state=false;
 Player player;
 InputStream input;
 Sequence seq;   //序列
 Sequencer midi;    //声明流播放器
 
  public Music()  {
	 load_music();
 }
 
  void load_music() {
	  try{ 
		//  input = new FileInputStream(music_path+music_name);
	 	 // player=new Player(input);
	 	 if(this.music_state==true){
	    		seq = MidiSystem.getSequence(new File(music_path+music_name));   //music位置
		    	midi=MidiSystem.getSequencer();    //创建流播放器
		        midi.open();   //打开播放器
		        midi.setSequence(seq);    //播放指定目录额歌曲
		        midi.start();   //开始播放
		        midi.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);  //循环次数	 
	       }
	       else if(this.music_state==false){
	    		midi.stop();
	    	//	midi.close();
	       }
	     }catch(Exception ex){
	    	 ex.printStackTrace();
	     }
  }
 void stop_music(){
	 if(this.music_state==true){
		 midi.stop();
		 midi.close();
		 this.music_state=true;
	 }
 }
 void set_music(String name){
	 this.music_name=name;
 }
}
