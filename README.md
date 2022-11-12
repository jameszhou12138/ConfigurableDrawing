# ConfigurableDrawing
## 实验题目

可配置的绘图工具软件       

## 实验环境

Intellij IDEA、记事本、命令提示符等   

## 实验要求

1. 掌握基本的布局类型，包括FlowLayout、BorderLayout、GridLayout。

2. 掌握在画布上绘制点、线、字的方式方法，掌握颜色、线粗细、填充等技巧。

3. 掌握用xml或类xml方式配置信息供程序消费的方式方法。

4. 具体地，制作一个可配置的绘图工具软件，软件可以根据用户的配置绘制相应的任意多个图形。

 

## 实验内容

### 1．实验背景介绍

绘图工具有很多种，Java在绘制图形方面的功能也不弱，但Java需要通过程序控制图形的生成，这给使用者带来了不小的困难。你的任务是开发一个绘图软件，能够帮助使用者方便地绘制图形。

 

###2．用记事本书写一个Java程序

（1）、建立个人子目录

步骤1：建立个人子目录

第一次上机时先在D盘上建立一个以自己学号+姓名为目录名的子目录，如学号为210824109的张三同学，就用“210824109张三”为子目录名。实验完成的源代码、Java字节码和实验报告三个文件都要放在这个文件夹下（称为上交文件夹）。

 

步骤2：建立Java源代码文件

在所建立的文件夹下建立一个记事本文件ConfigurableDrawing.txt，并把它重命名为ConfigurableDrawing.java

（2）、编写源代码

步骤1：创建一个公共类ConfigurableDrawing

要创建的公共类在默认包中，可引入其它的包。所创建的公共类在文件中的一行如下：

public class ConfigurableDrawing{… …}

步骤2：建立主方法main( )

在类ConfigurableDrawing的类体中编写主方法：

public static void main(String[] args){… …}

步骤3：编写方法和ConfigurableDrawing的主方法main( )

主方法用于测试。辅助方法的方法头请参见附件

 

3．调试和运行

（1）、调试

步骤1：使用命令行工具，先进入用所建的目录下。

步骤2：用javac ConfigurableDrawing.java编译并调试源代码文件。

（2）、运行

使用java ConfigurableDrawing运行程序。

 

## 实验方法

根据实验内容，将该实验大致分为三个步骤：

（1）解析读取xml文件；

（2）编写点、直线、折线、曲线、矩形和椭圆形类；

（3）编写用户图形界面，用于展示图形。

 

## 实验结果

（1）用命令提示符cmd编译运行程序。（如图1所示）

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml17064\wps1.jpg) 

图1 编译运行ConfigurableDrawing

（2）弹出图形用户界面，初始化界面。左侧画布用于展示用户所绘制的图形；右侧上方为2个按钮——“Draw”按钮用于绘图，Sample用于显示配置样例的信息；右下为配置样例信息的文本框，用户可通过修改该文本框来绘制自己所需要的图形。（如图2所示）

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml17064\wps2.jpg) 

图2 初始化界面

 

（3）点击“sample”按钮，配置样例信息的文本框种出现配置样例，再点击“draw”按钮，左侧画布显示配置样例所绘制的所有图形。（如图3所示）

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml17064\wps3.jpg) 

图3 配置样例绘图

（4）设置背景和坐标

设置背景<bg>：修改背景中<col>标签的RGB数值或使用名称颜色以改变背景颜色。

设置坐标<scale>：修改坐标中<col>标签的RGB数值或使用名称颜色以改变坐标轴颜色；修改坐标中<wid>标签的值以改变坐标粗细；修改坐标中<wid>标签的值以改变坐标粗细；修改坐标中<direction>标签的值为x或者y以确定所绘制的坐标为x轴或者y轴；修改坐标中<pos>标签的值以改变坐标在画布中的起始位置；修改坐标中<from>标签的值以改变坐标的起始坐标；修改坐标中<step>标签的值以改变坐标轴刻度的步长；修改坐标中<amount>标签的值以改变坐标轴上显示的数值个数；修改坐标中的<precision>标签的值以修改坐标轴上的精度。（如图4所示，图中配置如下）

```xml
<bg>
<col> 200,200,200 </col>
<xRange> -0.1, 1.1 </xRange>
<yRange> -0.1, 1.1 </yRange>
</bg>

<scale>
<col> cyan </col>
<wid> 3 </wid>
<direction> x </direction>
<pos> 1.0 </pos>
<from> 0.1 </from>
<step> 0.1 </step>
<amount> 10 </amount>
<precision> 2 </precision>
</scale>

<scale>
<col> 255,0,255 </col>
<wid> 1 </wid>
<direction> y </direction>
<pos> 0 </pos>
<from> 0 </from>
<step> 0.2 </step>
<amount> 8 </amount>
<precision> 1 </precision>
</scale>
```

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml17064\wps4.jpg) 

图4 设置背景和坐标

（5）设置点的集合

设置点的集合<points>：修改点集中<pad>标签的值为true时显示为填充效果，false时显示为描边效果；修改点集中<col>标签的RGB数值或使用名称颜色以改变该点集里所有点的颜色；修改点集中<wid>标签的值以改变该点集里所有点的粗细；修改点集中<radius>标签的值以改变该点集里所有点的半径；修改点集中<list>标签的值以选择点的坐标。此外，用户可增加多个点集用于绘制不同样式的点。（如图5所示，图中配置如下）

```xml
<bg>
<col> 255,255,255 </col>
<xRange> -0.1, 1.1 </xRange>
<yRange> -0.1, 1.1 </yRange>
</bg>

<points>
<pad> true </pad>
<col> red </col>
<wid> 2 </wid>
<radius> 6 </radius>
<list>
0.2, 0.5
0.5, 0.8
0.8, 0.5
0.5, 0.2
</list>
</points>

<points>
<pad> false </pad>
<col> 0,255,0 </col>
<wid> 2 </wid>
<radius> 8 </radius>
<list>
0.2, 0.2
0.2, 0.8
0.8, 0.8
0.8, 0.2
0.5, 0.5
</list>
</points>

<scale>
<col> black </col>
<wid> 1 </wid>
<direction> x </direction>
<pos> 0.0 </pos>
<from> 0 </from>
<step> 0.1 </step>
<amount> 10 </amount>
<precision> 1 </precision>
</scale>

<scale>
<col> black </col>
<wid> 1 </wid>
<direction> y </direction>
<pos> 0 </pos>
<from> 0 </from>
<step> 0.1 </step>
<amount> 10 </amount>
<precision> 1 </precision>
</scale>
```

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml17064\wps5.jpg) 

图5 设置点集

（6）设置折线段

设置折线段<lines>：修改折线段中<col>标签的RGB数值或使用名称颜色以改变该折线段里所有线段的颜色；修改折线段中<wid>标签的值以改变折线段里所有线段的粗细；修改折线段中<list>标签的值以选择点的坐标。此外，用户可增加多个折线段用于绘制不同样式的折线段。（如图6所示，图中配置如下）

```xml
<bg>
<col> 255,255,255 </col>
<xRange> -0.1, 1.1 </xRange>
<yRange> -0.1, 1.1 </yRange>
</bg>

<lines>
<col> pink </col>
<wid> 5 </wid>
<list>
0.8, 0.8
0.8, 0.5
0.8, 0.2
0.5, 0.2
0.2, 0.2
0.2, 0.5
0.2, 0.8
0.5, 0.8
0.5, 0.5
</list>
</lines>

<lines>
<col> 0,255,255 </col>
<wid> 2 </wid>
<list>
0.8, 0.8
0.5, 0.5
0.2, 0.8
</list>
</lines>

<lines>
<col> 0,0,255 </col>
<wid> 10 </wid>
<list>
0.2, 0.2
0.5, 0.5
0.8, 0.2
</list>
</lines>

<scale>
<col> black </col>
<wid> 1 </wid>
<direction> x </direction>
<pos> 0.0 </pos>
<from> 0 </from>
<step> 0.1 </step>
<amount> 10 </amount>
<precision> 1 </precision>
</scale>

<scale>
<col> black </col>
<wid> 1 </wid>
<direction> y </direction>
<pos> 0 </pos>
<from> 0 </from>
<step> 0.1 </step>
<amount> 10 </amount>
<precision> 1 </precision>
</scale>
```

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml17064\wps6.jpg) 

图6 设置折线

（7）设置直线

设置直线<line>：修改直线中<col>标签的RGB数值或使用名称颜色以改变该直线的颜色；修改直线中<wid>标签的值以改变直线的粗细；修改直线中<point>标签的值以选择直线的点的坐标；修改直线中<slope>标签的值以选择直线的斜率。此外，用户可增加多条直线用于绘制不同样式的直线。（如图7所示，图中配置如下）

```xml
<bg>
<col> 255,255,255 </col>
<xRange> -0.1, 1.1 </xRange>
<yRange> -0.1, 1.1 </yRange>
</bg>

<line>
<col>  </col>
<wid> 2 </wid>
<point> 0.3, 0.3 </point>
<slope> inf </slope>
</line>

<line>
<col> orange </col>
<wid> 1 </wid>
<point> 0.3, 0.3 </point>
<slope>0.7  </slope>
</line>

<line>
<col> 120,120,120 </col>
<wid> 5 </wid>
<point> 0.3, 0.3 </point>
<slope> 0 </slope>
</line>

<line>
<col> red </col>
<wid> 1 </wid>
<point> 0.3, 0.3 </point>
<slope> -1 </slope>
</line>

<scale>
<col> black </col>
<wid> 1 </wid>
<direction> x </direction>
<pos> 0.0 </pos>
<from> 0 </from>
<step> 0.1 </step>
<amount> 10 </amount>
<precision> 1 </precision>
</scale>

<scale>
<col> black </col>
<wid> 1 </wid>
<direction> y </direction>
<pos> 0 </pos>
<from> 0 </from>
<step> 0.1 </step>
<amount> 10 </amount>
<precision> 1 </precision>
</scale>
```

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml17064\wps7.jpg) 

图7 设置直线

（8）设置曲线

设置曲线<curve>：修改曲线中<col>标签的RGB数值或使用名称颜色以改变该曲线的颜色；修改曲线中<wid>标签的值以改变曲线的粗细；修改曲线中<range>标签的值以选择曲线定义域的范围；修改曲线中<amount>标签的值以改变曲线中离散点的数量；修改曲线中<function>标签的值以改变曲线的函数表达式。此外，用户可增加多条曲线用于绘制不同样式的曲线。（如图7所示，图中配置如下）

```xml
<bg>
<col> 255,255,255 </col>
<xRange> -0.1, 1.1 </xRange>
<yRange> -0.1, 1.1 </yRange>
</bg>

<curve>
<col> 0,0,255 </col>
<wid> 3 </wid>
<range> 0, 1 </range>
<amount> 500 </amount>
<function> Math.pow(x,2)-0.05 </function>
</curve>

<curve>
<col> gray </col>
<wid> 5 </wid>
<range> 0, 1 </range>
<amount> 300 </amount>
<function> -0.5*x+0.9 </function>
</curve>

<scale>
<col> black </col>
<wid> 1 </wid>
<direction> x </direction>
<pos> 0.0 </pos>
<from> 0 </from>
<step> 0.1 </step>
<amount> 10 </amount>
<precision> 1 </precision>
</scale>

<scale>
<col> black </col>
<wid> 1 </wid>
<direction> y </direction>
<pos> 0 </pos>
<from> 0 </from>
<step> 0.1 </step>
<amount> 10 </amount>
<precision> 1 </precision>
</scale>
```

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml17064\wps8.jpg) 

图8 设置曲线

（9）设置图形

设置图形<shape>：修改图形中<col>标签的RGB数值或使用名称颜色以改变该图形的颜色；修改图形中<wid>标签的值以改变图形边框的粗细；修改图形中<type>标签的值为oval时图形为圆或椭圆，rect时图形为矩形；修改图形中<pad>标签的值为true时显示为填充效果，false时显示为描边效果；修改图形中<center>标签的值以改变图形的中心坐标；修改图形中<width>标签的值以改变图形的宽度；修改图形中<height>标签的值以改变图形的高度。此外，用户可增加多条曲线用于绘制不同样式的曲线。（如图7所示，图中配置如下）

```xml
<bg>
<col> 255,255,255 </col>
<xRange> -0.1, 1.1 </xRange>
<yRange> -0.1, 1.1 </yRange>
</bg>

<shape>
<col> 180,180,180 </col>
<wid> 1 </wid>
<type> oval </type>
<!-- type includes oval & rect -->
<pad> true </pad>
<center> 0.6, 0.8 </center>
<width> 0.3 </width>
<height> 0.2 </height>
</shape>

<shape>
<col> black </col>
<wid> 5 </wid>
<type> oval </type>
<!-- type includes oval & rect -->
<pad> true </pad>
<center> 0.6, 0.8 </center>
<width> 0.1 </width>
<height> 0.1 </height>
</shape>

<shape>
<col> yellow </col>
<wid> 2 </wid>
<type> oval </type>
<!-- type includes oval & rect -->
<pad> false </pad>
<center> 0.3, 0.4 </center>
<width> 0.2 </width>
<height> 0.2 </height>
</shape>

<shape>
<col> green </col>
<wid> 5 </wid>
<type> rect </type>
<!-- type includes oval & rect -->
<pad> false </pad>
<center> 0.3, 0.4 </center>
<width> 0.3 </width>
<height> 0.2 </height>
</shape>

<scale>
<col> black </col>
<wid> 1 </wid>
<direction> x </direction>
<pos> 0.0 </pos>
<from> 0 </from>
<step> 0.1 </step>
<amount> 10 </amount>
<precision> 1 </precision>
</scale>

<scale>
<col> black </col>
<wid> 1 </wid>
<direction> y </direction>
<pos> 0 </pos>
<from> 0 </from>
<step> 0.1 </step>
<amount> 10 </amount>
<precision> 1 </precision>
</scale>
```

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml17064\wps9.jpg) 

图9 设置图形

 

## 结论分析

（1）解析xml文件

① 修改xml文件：

修改配置信息中的非标准xml文件，加上版本号和编码方式，再将原有的xnl文件中的标签放入一个大标签中，使之能被识别。

```java
//更改xml文件
xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<Component>\n" + xml + "\n</Component>";
```

 

② 保存xml文件：

使用文件流的方式得到xml文件——将配置信息以xml文件的方式保存在与程序同目录下的文件夹中，命名为ConfigurableDrawing.xml，便于之后操作。

```java
try{
  FileOutputStream fos = new FileOutputStream("ConfigurableDrawing.xml");
  OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
  osw.write(xml);
  osw.flush();
  osw.close();
}catch (Exception e){
  System.out.println("xml文件保存失败！！！");
}
```

 

③读取xml文件：

创建DOM模式的解析器对象读取xml文件。以背景为例，先找到标签为lines的所有折线段放入列表中，遍历列表。对于每一个折线段，记录当前折线段的col、wid和list，生成一个新的折线段，放入折线段列表中。

```java
try{
  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
  DocumentBuilder db = factory.newDocumentBuilder();
  Document doc = db.parse("ConfigurableDrawing.xml");
  //折线段
  NodeList linesList = doc.getElementsByTagName("lines");
  for (int i = 0; i < linesList.getLength(); i++){
    Color col = Color.black;
    float wid = 1;
    List<Double> list = new ArrayList<>();
    Node node = linesList.item(i);
    NodeList childNodes = node.getChildNodes();
    for (int j = 0; j < childNodes.getLength(); j++){
      if (childNodes.item(j).getNodeName().compareTo("col") == 0){
        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
          col = colorExchange(childNodes.item(j).getTextContent());
        }
      }
      if (childNodes.item(j).getNodeName().compareTo("wid") == 0){
        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
          wid = Float.parseFloat(childNodes.item(j).getTextContent().trim());
        }
      }
      if (childNodes.item(j).getNodeName().compareTo("list") == 0){
        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
          list = listExchange(childNodes.item(j).getTextContent());
        }
      }
    }
    lines.add(new Lines(col, wid, list));
  }
}catch (Exception e){
  System.out.println("文件打开失败！");
}
```

 

（2）类：

以折线段为例，折线段类中有私有成员变量linesColor来表示该折线段的颜色、linesWidth表示该折线段的宽度、points列表来表示该折线段包含的所有点，构造方法Lines用于生成一个新的折线段，成员方法toString便于调试输出该折线段的信息以及一些方法用于修改或获取某一成员变量。

```java
//折线
class Lines{
  private Color linesColor = Color.black;
  private float linesWidth = 1;
  private List<Double> points = new ArrayList<>();
  Lines(Color linesColor, float linesWidth, List<Double> points){
    this.linesColor = linesColor;
    this.linesWidth = linesWidth;
    this.points = points;
  }
  @Override
  public String toString(){
    return "Lines{" +
        "linesColor=" + linesColor +
        ", linesWidth=" + linesWidth +
        ", points=" + points +
        '}' + '\n';
  }
  public void setLinesColor(Color linesColor){
    this.linesColor = linesColor;
  }
  public void setLinesWidth(int linesWidth){
    this.linesWidth = linesWidth;
  }
  public void setPoints(List<Double> points){
    this.points = points;
  }
  public Color getLinesColor(){
    return linesColor;
  }
  public float getLinesWidth(){
    return linesWidth;
  }
  public List<Double> getPoints(){
    return points;
  }
}
```

 

（3）面板类的构造函数。

```java
public ConfigurableDrawing(int width, int height){
  //设置可视
  this.setVisible(true);
  //JFrame关闭后退出
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //设置标题
  this.setTitle("Configurable Drawing -- Developer: James Zhou");
  //设置窗口大小
  this.setSize(width, height);
  //打开后屏幕居中
  this.setLocationRelativeTo(getOwner());
  //分割线
  jSplitPane.setDividerLocation(670);
  jSplitPane.setDividerSize(10);
  this.add(jSplitPane);
  //清空配置面板布局管理器
  configurationPanel.setLayout(null);
  //绘图按钮
  drawButton.setBounds(0, 0, 100, 20);
  configurationPanel.add(drawButton);
  //配置样例按钮
  sampleButton.setBounds(105, 0, 100, 20);
  configurationPanel.add(sampleButton);
  //设置配置信息文本框
  configurationInfo.setBounds(0, 20, 205, 540);
  configurationPanel.add(configurationInfo);
  //配置面板改变大小监听事件
  configurationPanel.addComponentListener(new ComponentAdapter(){
    @Override
    public void componentResized(ComponentEvent e){
      int width = configurationPanel.getWidth(), height = configurationPanel.getHeight();
      int buttonWidth = (width - 5) / 2;
      //改变绘图按钮大小
      drawButton.setBounds(0, 0, buttonWidth, 20);
      //改变配置样例按钮大小
      sampleButton.setBounds(buttonWidth + 5, 0, buttonWidth, 20);
      //改变配置信息文本框大小
      configurationInfo.setBounds(0, 20, width, height - 20);
    }
  });
  //画布面板改变大小监听事件
  canvasPanel.addComponentListener(new ComponentAdapter(){
    @Override
    public void componentResized(ComponentEvent e){
      //改变画布大小
      canvas.setBounds(0, 0, canvasPanel.getWidth(), canvasPanel.getHeight());
    }
  });
  //绘图按钮监听事件
  drawButton.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent ae){
      //调整画布
      canvasPanel.add(canvas);
      canvas.setBounds(0, 0, canvasPanel.getWidth(), canvasPanel.getHeight());
      //图形信息
      String info = sampleInfo;
      if (! Objects.equals(configurationInfo.getText(), "")){
        info = configurationInfo.getText();
      }
      canvas.parseXML(info);
      canvas.repaint();
    }
  });
  //配置样例按钮监听事件
  sampleButton.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent ae){
      configurationInfo.setText(sampleInfo);
    }
  });
}
```

 

（4）绘制图形：

以绘制曲线为例，设置画笔的宽度和颜色。根据曲线的定义域和散点数，确定每两个散点之间的距离，遍历每个散点，通过动态编写自定义脚本实现扩展功能根据当前的x求出对应的函数值，便可求出该散点的坐标，根据计算得到该点在画布上所对应的坐标，将相邻散点之间绘制一条直线，便可出现所需曲线。

```java
//绘制曲线
for (Curve c : curve){
  g2.setStroke(new BasicStroke(c.getCurveWidth()));
  g2.setPaint(c.getCurveColor());
  double pl = c.getCurveLeft(), pr = c.getCurveRight();
  int amount = c.getCurveAmount();
  double dx = (pr - pl) / amount;
  for (int i = 1; i <= amount; i++){
    double px1 = pl + dx * (i - 1), px2 = pl + dx * i;
    double py1 = 0, py2 = 0;
    try{
      ScriptEngineManager manager = new ScriptEngineManager();
      ScriptEngine engineByName = manager.getEngineByName("JavaScript");
      String func1 = c.getCurveFunc().replaceAll("x","" + px1);
      py1 = (Double) engineByName.eval(func1);
      String func2 = c.getCurveFunc().replaceAll("x","" + px2);
      py2 = (Double) engineByName.eval(func2);
      int x1 = x0 + (int) (base * (px1 - xFrom) / xStep);
      int y1 = y0 - (int) (base * (py1 - yFrom) / yStep);
      int x2 = x0 + (int) (base * (px2 - xFrom) / xStep);
      int y2 = y0 - (int) (base * (py2 - yFrom) / yStep);
      g2.drawLine(x1, y1, x2, y2);
    }catch (ScriptException e){
      System.out.println("曲线表达式有误！！！");
    }
  }
}
```

 
