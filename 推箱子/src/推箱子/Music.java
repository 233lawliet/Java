package ������;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import java.io.*;
import javazoom.jl.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;
public class Music extends Thread{
 String  music_path="D:\\eclipse�����\\������\\src\\music\\";
 String  music_name="��ˮ����.mid";
 boolean music_state=false;
 Player player;
 InputStream input;
 Sequence seq;   //����
 Sequencer midi;    //������������
 
  public Music()  {
	 load_music();
 }
 
  void load_music() {
	  try{ 
		//  input = new FileInputStream(music_path+music_name);
	 	 // player=new Player(input);
	 	 if(this.music_state==true){
	    		seq = MidiSystem.getSequence(new File(music_path+music_name));   //musicλ��
		    	midi=MidiSystem.getSequencer();    //������������
		        midi.open();   //�򿪲�����
		        midi.setSequence(seq);    //����ָ��Ŀ¼�����
		        midi.start();   //��ʼ����
		        midi.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);  //ѭ������	 
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
