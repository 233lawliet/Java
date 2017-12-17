
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 233lawliet on 2017/12/15.
 */
public class Main {
  List<Element> elements=new ArrayList<>();
  List<Class> allClass=new ArrayList<>();
  List<List<Double>> wT=new ArrayList<>();
  Double ρ=0.0;

  /****************************************获取数据******************************************/
  public void getElements(){
      List<Double> list=new ArrayList<>();
      list.add(1.0);list.add(1.0);
      this.elements.add(new Element(list,1));
      list=new ArrayList<>();
      list.add(1.0);list.add(2.0);
      this.elements.add(new Element(list,1));
      list=new ArrayList<>();
      list.add(2.0);list.add(1.0);
      this.elements.add(new Element(list,1));
      list=new ArrayList<>();
      list.add(2.0);list.add(2.0);
      this.elements.add(new Element(list,2));
      list=new ArrayList<>();
      list.add(3.0);list.add(1.0);
      this.elements.add(new Element(list,2));
      list=new ArrayList<>();
      list.add(3.0);list.add(2.0);
      this.elements.add(new Element(list,2));
      list=new ArrayList<>();
      list.add(4.0);list.add(2.0);
      this.elements.add(new Element(list,3));

  }
  public void getInfors(String fileName) throws IOException {
      //建立输入流--文件输入流
      File file=new File(fileName);
      InputStreamReader inputSteam=new InputStreamReader(new FileInputStream(file));
      BufferedReader reader=new BufferedReader(inputSteam);

      //读取数据并且转换
      String nextLine=reader.readLine();
      while(nextLine!=null){
          String[] lineData=nextLine.split("\\s+");
          Element element=new Element();
          List<Double> linePoint=new ArrayList<>();
          for(int i=0;i<lineData.length;i++){
              if((int)Double.parseDouble(lineData[0])!=4){    //去除第四类
                  if(i==0&&lineData[i].length()>0){
                      element.idOfClass=((int)Double.parseDouble(lineData[i]));
                  }else if(lineData[i].length()>0){
                      linePoint.add(Double.parseDouble(lineData[i]));
                  }
                  if(i==lineData.length-1){
                      element.point=linePoint;
                      this.elements.add(element);
                  }
              }
          }

          nextLine=reader.readLine();
      }

  }


  /********************************************类的******************************************/
   //把获取到的元素进行归类
  public void classify(){
      for(int i=0;i<elements.size();i++){
          int test=0;  //查询当前类是否存在
          for(int j=0;j<allClass.size();j++){
              if(elements.get(i).idOfClass==allClass.get(j).classId){
                  test++;
              }
          }
          if(test==0){   //test=0添加新类
              Class oneClass=new Class();
              oneClass.classId=elements.get(i).idOfClass;
              oneClass.elements.add(elements.get(i));
              allClass.add(oneClass);
          }else{        //test!=0原有的类添加元素
              for(int j=0;j<allClass.size();j++){
                    if(allClass.get(j).classId==elements.get(i).idOfClass){
                        allClass.get(j).elements.add(elements.get(i));
                    }
              }
          }
      }
  }
  //类的信息加工  包括所有类+1维，其他类*-1
  public void initClass(int i){
      allClass.get(i).step=0;
      for(int j=0;j<allClass.size();j++) {
          //对处理的标记1
          if(allClass.get(j).classId==allClass.get(i).classId){
              allClass.get(i).tempClassId=1;
          }
          for(int k=0;k<allClass.get(j).elements.size();k++){

              allClass.get(j).elements.get(k).point.add(1.0);
              //其他的类的数据点*（-1.0），可以计算wT时候都用+
              if(allClass.get(i).classId==allClass.get(j).classId){
              }else{
                  for(int l=0;l<allClass.get(j).elements.get(k).point.size();l++){
                      allClass.get(j).elements.get(k).point.set(l,allClass.get(j).elements.get(k).point.get(l)*(-1));
                  }
              }

          }
      }
  }
  //类的信息还原 包括所有类-1维 其他类还原*（-1）
  public void returnClass(int i){
      //分为两类结束，数据还原
      for(int j=0;j<allClass.size();j++) {
          //清除临时的类标记
          if(allClass.get(j).classId==allClass.get(i).classId){
              allClass.get(i).tempClassId=0;
          }
          for(int k=0;k<allClass.get(j).elements.size();k++){
              //当前处理的类不*（-1.0），其他的类*（-1.0）还原
              if(allClass.get(i).classId==allClass.get(j).classId){

              }else{
                  for(int l=0;l<allClass.get(j).elements.get(k).point.size();l++){
                      allClass.get(j).elements.get(k).point.set(l,allClass.get(j).elements.get(k).point.get(l)*(-1.0));
                  }
              }
              //消除最后一位
              allClass.get(j).elements.get(k).point.remove(allClass.get(j).elements.get(k).point.size()-1);
          }
      }
  }


  //总的计算主体
  public void calculate(){
      //当前正在处理第i类
      for(int i=0;i<allClass.size();i++){
          //执行分为两类
          this.initClass(i);

          this.getwT(i);   //进行计算获取wt

          this.returnClass(i);


      }
  }

  //显示所有类的结构内容
  public void outClass(){
      for(int i=0;i<allClass.size();i++) {
          System.out.println("类的ID:"+allClass.get(i).classId+" "+"类的tempID:"+allClass.get(i).tempClassId);
          for (int j = 0; j < allClass.get(i).elements.size(); j++) {
              System.out.println(allClass.get(i).elements.get(j).idOfClass+" "+allClass.get(i).elements.get(j).point);
          }
      }
      System.out.println();

  }

  /*************************************关于wT************************************************/
  //初始化wT
    public void initwT(Double initwT,Double ρ){
        for(int i=0;i<allClass.size();i++){
            List<Double> lists=new ArrayList<>();
            for(int j=0;j<=allClass.get(i).elements.get(0).point.size();j++){
                lists.add(1.0);
            }
            this.ρ=ρ;
            this.wT.add(lists);
        }
    }
  //显示结果wT
    public void outwT(){
        for(int i=0;i<allClass.size();i++){
            System.out.print("类"+allClass.get(i).classId+"的wT:  ");
            for(int j=0;j<=allClass.get(i).elements.get(0).point.size();j++){
                System.out.print("w"+(j+1)+":"+wT.get(i).get(j)+" ");
            }
            System.out.println();
        }
    }
    //显示处理wT过程
    public void outwTProcess(double number,int i,int j,int k){
        allClass.get(i).step++;
        if(number>0){
            //System.out.print("第" + allClass.get(i).step + "步,");
            //System.out.print("point:"+allClass.get(j).elements.get(k).point);
            //System.out.println("   wT:"+wT.get(i)+"--->不变");
        }else {
            System.out.print("第" + allClass.get(i).step + "步,");
            System.out.print("point:"+allClass.get(j).elements.get(k).point);
            System.out.print("   wT:"+wT.get(i)+"--->[");
            for (int l = 0; l < allClass.get(j).elements.get(k).point.size(); l++) {
                System.out.print(" "+((double)((int)((wT.get(i).get(l)+allClass.get(j).elements.get(k).point.get(l)*this.ρ)*1000.0))/1000));
                if(l!=allClass.get(j).elements.get(k).point.size()-1)System.out.print(",");
            }
            System.out.println("]");
        }
    }

  //求解wT
  public void getwT(int i){
    while(true)  {
        int numOfElements = 0;
        for (int j = 0; j < allClass.size(); j++) {
            for (int k = 0; k < allClass.get(j).elements.size(); k++) {
                Double number=0.0;
                for(int l=0;l<allClass.get(j).elements.get(k).point.size();l++){  //每个点的元素计算
                    number+=wT.get(i).get(l)*allClass.get(j).elements.get(k).point.get(l);
                }
                //如果当前的计算结果>0 +1
                if(number>0){
                    numOfElements++;
                    outwTProcess(number,i,j,k);
                }
                //否则修改wT
                else{
                    outwTProcess(number,i,j,k);
                    //修改wT的信息
                    for(int l=0;l<allClass.get(j).elements.get(k).point.size();l++){  //每个点的元素计算
                        double num=(double)((int)((wT.get(i).get(l)+allClass.get(j).elements.get(k).point.get(l)*this.ρ)*1000.0))/1000;
                        wT.get(i).set(l,num);

                    }
                }
            }
        }
        //所有的点都符合 结束死循环
        if(numOfElements==elements.size()){
            System.out.println("当前类--------end");
            break;
        }
    }
  }


/*****************************主方法*********************************************/
  public static  void  main(String args[]) throws IOException {
     Main main=new Main();
     main.getInfors(main.getClass().getResource(".").getPath()+"infors.txt");//获取本地文件的信息
     //main.getElements(); //获取自动的信息、
     main.classify();   //element信息归类
     main.initwT(1.0,0.1);     //初始化wT为1.0所有的1.0


     main.calculate();   //类加工，wT获取，wT过程显示，wT的加工还原


     main.outwT();

  }
}
