import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.*;
import java.util.List;


public class ConfigurableDrawing extends JFrame{
    String sampleInfo = "<!--\n" +
            "【1】配置文件用xml格式，注释采用标准的xml注释格式。\n" +
            "【2】有8类设定：\n" +
            "（1）<bg>：用于设定画布的背景颜色及横向和纵向坐标范围；\n" +
            "（2）<points>：用于画一个或多个点，可以对点的颜色和填充等进行配置；\n" +
            "（3）<lines>：用于画折线，可以对折线的颜色和粗细进行配置：\n" +
            "（4）<line>：根据提供的单个点和斜率，画射线；\n" +
            "（5）<curve>：用于画曲线，画出采用公式描述的曲线；\n" +
            "（6）<shape>：用于画矩形和椭圆，能够对填充颜色进行配置；\n" +
            "（7）<xScale>：用于画x轴刻度；\n" +
            "（8）<yScale>：用于画y轴刻度。\n" +
            "【3】与颜色相关的标签中，均可以采用两种方式之一设定：\n" +
            "（1）用名称颜色，名称颜色共10种：\n" +
            "black, blue, cyan, gray, green, \n" +
            "orange, pink, red, white, yellow\n" +
            "（2）用RGB颜色，例如：\n" +
            "255.0.155\n" +
            "\n" +
            "【4】其它设定包括：\n" +
            "（1）所有与线的粗细有关的设定都默认为1；\n" +
            "（2）所有与颜色相关的设定的默认值都为黑色（0,0,0)；背景为白色；\n" +
            "（3）画曲线时，默认离散点的数量为500；\n" +
            "（4）画刻度时，默认间隔数量<amount>为10.\n" +
            "\n" +
            "【5】以下为各种设置示例：\n" +
            "-->\n" +
            "\n" +
            "<bg>\n" +
            "<col> 255,255,255 </col>\n" +
            "<xRange> -0.1, 1.1 </xRange>\n" +
            "<yRange> -0.1, 1.1 </yRange>\n" +
            "</bg>\n" +
            "\n" +
            "<points>\n" +
            "<pad> true </pad>\n" +
            "<col> red </col>\n" +
            "<wid> 2 </wid>\n" +
            "<radius> 6 </radius>\n" +
            "<list>\n" +
            "0.2, 0.2\n" +
            "0.2, 0.5\n" +
            "0.2, 0.8\n" +
            "0.5, 0.8\n" +
            "0.8, 0.8\n" +
            "0.8, 0.5\n" +
            "0.8, 0.2\n" +
            "0.5, 0.2\n" +
            "</list>\n" +
            "</points>\n" +
            "\n" +
            "<lines>\n" +
            "<col> orange </col>\n" +
            "<wid> 3 </wid>\n" +
            "<list>\n" +
            "0.2, 0.2\n" +
            "0.2, 0.5\n" +
            "0.2, 0.8\n" +
            "0.5, 0.8\n" +
            "0.8, 0.8\n" +
            "0.8, 0.5\n" +
            "0.8, 0.2\n" +
            "0.5, 0.2\n" +
            "0.5, 0.5\n" +
            "</list>\n" +
            "</lines>\n" +
            "\n" +
            "<line>\n" +
            "<col>  </col>\n" +
            "<wid> 1 </wid>\n" +
            "<point> 0.3, 0.3 </point>\n" +
            "<slope> inf </slope>\n" +
            "</line>\n" +
            "\n" +
            "<curve>\n" +
            "<col> 0,0,255 </col>\n" +
            "<wid> 3 </wid>\n" +
            "<range> 0, 1 </range>\n" +
            "<amount> 500 </amount>\n" +
            "<function> Math.pow(x,2)-0.05 </function>\n" +
            "</curve>\n" +
            "\n" +
            "<shape>\n" +
            "<col> 180,180,180 </col>\n" +
            "<wid> 1 </wid>\n" +
            "<type> oval </type>\n" +
            "<!-- type includes oval & rect -->\n" +
            "<pad> true </pad>\n" +
            "<center> 0.6, 0.8 </center>\n" +
            "<width> 0.3 </width>\n" +
            "<height> 0.2 </height>\n" +
            "</shape>\n" +
            "\n" +
            "<shape>\n" +
            "<col> 180,180,180 </col>\n" +
            "<wid> 5 </wid>\n" +
            "<type> rect </type>\n" +
            "<!-- type includes oval & rect -->\n" +
            "<pad> false </pad>\n" +
            "<center> 0.3, 0.4 </center>\n" +
            "<width> 0.3 </width>\n" +
            "<height> 0.2 </height>\n" +
            "</shape>\n" +
            "\n" +
            "<scale>\n" +
            "<col> black </col>\n" +
            "<wid> 1 </wid>\n" +
            "<direction> x </direction>\n" +
            "<pos> 0.0 </pos>\n" +
            "<from> 0 </from>\n" +
            "<step> 0.1 </step>\n" +
            "<amount> 10 </amount>\n" +
            "<precision> 1 </precision>\n" +
            "</scale>\n" +
            "\n" +
            "<scale>\n" +
            "<col> black </col>\n" +
            "<wid> 1 </wid>\n" +
            "<direction> y </direction>\n" +
            "<pos> 0 </pos>\n" +
            "<from> 0 </from>\n" +
            "<step> 0.1 </step>\n" +
            "<amount> 10 </amount>\n" +
            "<precision> 1 </precision>\n" +
            "</scale>";
    //画布
    Canvas canvas = new Canvas();
    //绘画按钮
    JButton drawButton = new JButton("Draw");
    //配置样例按钮
    JButton sampleButton = new JButton("Sample");
    //配置信息文本框
    TextArea configurationInfo = new TextArea();
    //画布面板
    JPanel canvasPanel = new JPanel();
    //配置面板
    JPanel configurationPanel = new JPanel();
    //分割线
    JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, canvasPanel, configurationPanel);
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
    public static void main(String[] args){
        EventQueue.invokeLater(()->{
            //默认初始大小为900*600
            ConfigurableDrawing configurableDrawing = new ConfigurableDrawing(900, 600);
        });
    }
}


class Canvas extends JPanel{
    public int base = 50;
    public int dotHeight = 5;
    //背景
    public Color backgroundColor = Color.white;
    //x坐标
    public int xAmount = 10;
    public Color xColor = Color.black;
    public float xWidth = 1;
    public double xPosition = 0;
    public double xFrom = 0;
    public double xStep = 0.1;
    public int xPrecision = 1;
    //y坐标
    public int yAmount = 10;
    public Color yColor = Color.black;
    public float yWidth = 1;
    public double yPosition = 0;
    public double yFrom = 0;
    public double yStep = 0.1;
    public int yPrecision = 1;
    //点
    List<Points> points = new ArrayList<>();
    //折线
    List<Lines> lines = new ArrayList<>();
    //直线
    List<MyLine> line = new ArrayList<>();
    //曲线
    List<Curve> curve = new ArrayList<>();
    //形状
    List<Shape> shape = new ArrayList<>();
    @Override public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        int width = this.getWidth(), height = this.getHeight();
        //背景颜色
        g2.setBackground(backgroundColor);
        //清空画布
        g2.clearRect(0, 0, (int)getBounds().getWidth(), (int)getBounds().getHeight());
        //绘制x轴和y轴以及x轴y轴上的点
        int x0 = (int) (base * (1 + xPosition));
        int y0 = height - (int) (base * (1 + yPosition));
        g2.setStroke(new BasicStroke(xWidth));
        g2.setColor(xColor);
        //绘制x轴
        g2.drawLine(x0, y0, x0 + xAmount * base, y0);
        //绘制x轴上的点
        for (int i = 0; i <= xAmount; i++){
            g2.drawLine(x0 + i * base, y0, x0 + i * base, y0 - dotHeight);
            g2.drawString(String.format("%."+ xPrecision + "f", xFrom + i * xStep), x0 + i * base - xPrecision * dotHeight, y0 + 3 * dotHeight);
        }
        g2.setStroke(new BasicStroke(yWidth));
        g2.setColor(yColor);
        //绘制y轴
        g2.drawLine(x0, y0, x0, y0 - yAmount * base);
        //绘制y轴上的点
        for (int i = 0; i <= yAmount; i++){
            g2.drawLine(x0, y0 - i * base, x0 + dotHeight, y0 - i * base);
            g2.drawString(String.format("%."+ yPrecision + "f", yFrom + i * yStep), x0 - (yPrecision + 4) * dotHeight, y0 - i * base + dotHeight);
        }
        //绘制点
        for (Points p : points){
            g2.setStroke(new BasicStroke(p.getPointWidth()));
            g2.setPaint(p.getPointColor());
            int x = x0 + (int) (base * (p.getPointX() - xFrom) / xStep) - p.getPointRadius();
            int y = y0 - (int) (base * (p.getPointY() - yFrom) / yStep) - p.getPointRadius();
            if (p.isPointPad()){
                g2.fillOval(x, y, p.getPointRadius() * 2, p.getPointRadius() * 2);
            }else{
                g2.drawOval(x, y, p.getPointRadius() * 2, p.getPointRadius() * 2);
            }
        }
        //绘制折线
        for (Lines ls : lines){
            g2.setStroke(new BasicStroke(ls.getLinesWidth()));
            g2.setPaint(ls.getLinesColor());
            List<Double> ps = ls.getPoints();
            for (int i = 2; i < ps.size(); i += 2){
                int x1 = x0 + (int) (base * (ps.get(i - 2) - xFrom) / xStep);
                int y1 = y0 - (int) (base * (ps.get(i - 1) - yFrom) / yStep);
                int x2 = x0 + (int) (base * (ps.get(i) - xFrom) / xStep);
                int y2 = y0 - (int) (base * (ps.get(i + 1) - yFrom) / yStep);
                g2.drawLine(x1, y1, x2, y2);
            }
        }
        //绘制直线
        for (MyLine l : line){
            g2.setStroke(new BasicStroke(l.getLineWidth()));
            g2.setPaint(l.getLineColor());
            double k = l.getLineSlope();
            double px1 = l.getLineX(), py1 = l.getLineY();
            int x1 = x0 + (int) (base * (px1 - xFrom) / xStep);
            int y1 = y0 - (int) (base * (py1 - yFrom) / yStep);
            if (k == Double.MAX_VALUE){
                g2.drawLine(x1, y1, x1, 1000);
                g2.drawLine(x1, y1, x1, -1000);
            }else{
                double px2 = -100.0, py2 = py1 + k * (px2 - px1);
                double px3 = 100.0, py3 = py1 + k * (px3 - px1);
                int x2 = x0 + (int) (base * (px2 - xFrom) / xStep);
                int y2 = y0 - (int) (base * (py2 - yFrom) / yStep);
                int x3 = x0 + (int) (base * (px3 - xFrom) / xStep);
                int y3 = y0 - (int) (base * (py3 - yFrom) / yStep);
                g2.drawLine(x1, y1, x2, y2);
                g2.drawLine(x1, y1, x3, y3);
            }
        }
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
        //绘制图形
        for (Shape s : shape){
            g2.setStroke(new BasicStroke(s.getShapeWid()));
            g2.setPaint(s.getShapeColor());
            double px = s.getShapeCenterX(), py = s.getShapeCenterY();
            double pw = s.getShapeWidth(), ph = s.getShapeHeight();
            int x = x0 + (int) (base * ((px - pw / 2) - xFrom) / xStep);
            int y = y0 - (int) (base * ((py + ph / 2) - yFrom) / yStep);
            int w = (int) (base * pw / xStep);
            int h = (int) (base * ph / yStep);
            if (Objects.equals(s.getShapeType(), "rect")){
                if (s.isShapePad()){
                    g2.fillRect(x, y, w, h);
                }else{
                    g2.drawRect(x, y, w, h);
                }
            }else{
                if (s.isShapePad()){
                    g2.fillOval(x, y, w, h);
                }else{
                    g2.drawOval(x, y, w, h);
                }
            }
        }
		//清空所有List
        points.clear();
        lines.clear();
        line.clear();
        curve.clear();
        shape.clear();
    }
    public List<Double> listExchange(String stringList){
        List<Double> ans = new ArrayList<>();
        stringList = stringList.trim();
        stringList = stringList.replaceAll(",", "");
        stringList = stringList.replaceAll("\n", " ");
        String[] xy = stringList.split(" ");
        for (String value : xy) {
            double tmp = Double.parseDouble(value);
            ans.add(tmp);
        }
        return ans;
    }
    public Color colorExchange(String color){
        color = color.trim();
        if (color.equals("")){
            return Color.black;
        }
        if (color.equals("black")){
            return Color.black;
        }else if (color.equals("blue")){
            return Color.blue;
        }else if (color.equals("cyan")){
            return Color.cyan;
        }else if (color.equals("gray")){
            return Color.gray;
        }else if (color.equals("green")){
            return Color.green;
        }else if (color.equals("orange")){
            return Color.orange;
        }else if (color.equals("pink")){
            return Color.pink;
        }else if (color.equals("red")){
            return Color.red;
        }else if (color.equals("white")){
            return Color.white;
        }else if (color.equals("yellow")){
            return Color.yellow;
        }else{
            String[] rgb = color.split(",");
            int r = Integer.parseInt(rgb[0]);
            int g = Integer.parseInt(rgb[1]);
            int b = Integer.parseInt(rgb[2]);
            Color col = new Color(r, g, b);
            return col;
        }
    }
    public void parseXML(String xml){
        //更改xml文件
        xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<Component>\n" + xml + "\n</Component>";
        //保存xml文件
        try{
            FileOutputStream fos = new FileOutputStream("ConfigurableDrawing.xml");
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            osw.write(xml);
            osw.flush();
            osw.close();
        }catch (Exception e){
            System.out.println("xml文件保存失败！！！");
        }
        //读取xml文件
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            Document doc = db.parse("ConfigurableDrawing.xml");
            //背景
            NodeList backgroundList = doc.getElementsByTagName("bg");
            for (int i = 0; i < backgroundList.getLength(); i++){
                Node node = backgroundList.item(i);
                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++){
                    if (childNodes.item(j).getNodeName().compareTo("col") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            this.backgroundColor = colorExchange(childNodes.item(j).getTextContent());
                        }
                    }
                }
            }
            //点
            NodeList pointList = doc.getElementsByTagName("points");
            for (int i = 0; i < pointList.getLength(); i++){
                boolean pad = true;
                Color color = Color.black;
                float wid = 1;
                int rad = 1;
                List<Double> list = new ArrayList<>();
                org.w3c.dom.Node node = pointList.item(i);
                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++){
                    if (childNodes.item(j).getNodeName().compareTo("pad") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            pad = childNodes.item(j).getTextContent().trim().equals("true");
                        }
                    }
                    if (childNodes.item(j).getNodeName().compareTo("col") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            color = colorExchange(childNodes.item(j).getTextContent());
                        }
                    }
                    if (childNodes.item(j).getNodeName().compareTo("wid") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            wid = Float.parseFloat(childNodes.item(j).getTextContent().trim());
                        }
                    }
                    if (childNodes.item(j).getNodeName().compareTo("radius") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            rad = Integer.parseInt(childNodes.item(j).getTextContent().trim());
                        }
                    }
                    if (childNodes.item(j).getNodeName().compareTo("list") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            list = listExchange(childNodes.item(j).getTextContent());
                        }
                    }
                }
                for (int j = 0; j < list.size(); j += 2){
                    Points p = new Points(pad, color, wid, rad, list.get(j), list.get(j + 1));
                    points.add(p);
                }
            }
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
            //直线
            NodeList lineList = doc.getElementsByTagName("line");
            for (int i = 0; i < lineList.getLength(); i++){
                Color col = Color.black;
                float wid = 1;
                List<Double> list = new ArrayList<>();
                double slope = 0;
                Node node = lineList.item(i);
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
                    if (childNodes.item(j).getNodeName().compareTo("point") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            list = listExchange(childNodes.item(j).getTextContent());
                        }
                    }
                    if (childNodes.item(j).getNodeName().compareTo("slope") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            String sl = childNodes.item(j).getTextContent().trim();
                            if (sl.equals("inf")){
                                slope = Double.MAX_VALUE;
                            }else{
                                slope = Double.parseDouble(sl);
                            }
                        }
                    }
                }
                line.add(new MyLine(col, wid, list.get(0), list.get(1), slope));
            }
            //曲线
            NodeList curveList = doc.getElementsByTagName("curve");
            for (int i = 0; i < curveList.getLength(); i++){
                Color col = Color.black;
                float width = 1;
                List<Double> range = new ArrayList<>();
                int amount = 500;
                String func = "";
                Node node = curveList.item(i);
                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++){
                    if (childNodes.item(j).getNodeName().compareTo("col") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            col = colorExchange(childNodes.item(j).getTextContent());
                        }
                    }
                    if (childNodes.item(j).getNodeName().compareTo("wid") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            width = Float.parseFloat(childNodes.item(j).getTextContent().trim());
                        }
                    }
                    if (childNodes.item(j).getNodeName().compareTo("range") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            range = listExchange(childNodes.item(j).getTextContent().trim());
                        }
                    }
                    if (childNodes.item(j).getNodeName().compareTo("amount") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            amount = Integer.parseInt(childNodes.item(j).getTextContent().trim());
                        }
                    }
                    if (childNodes.item(j).getNodeName().compareTo("function") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            func = childNodes.item(j).getTextContent().trim();
                        }
                    }
                }
                curve.add(new Curve(col, width, range.get(0), range.get(1), amount, func));
            }
            //图形
            NodeList shapeList = doc.getElementsByTagName("shape");
            for (int i = 0; i < shapeList.getLength(); i++){
                Color col = Color.black;
                float wid = 1;
                String type = "rect";
                boolean pad = true;
                List<Double> center = new ArrayList<>();
                double width = 0, height = 0;
                Node node = shapeList.item(i);
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
                    if (childNodes.item(j).getNodeName().compareTo("type") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            type = childNodes.item(j).getTextContent().trim();
                        }
                    }
                    if (childNodes.item(j).getNodeName().compareTo("pad") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            pad = childNodes.item(j).getTextContent().trim().equals("true");
                        }
                    }
                    if (childNodes.item(j).getNodeName().compareTo("center") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            center = listExchange(childNodes.item(j).getTextContent().trim());
                        }
                    }
                    if (childNodes.item(j).getNodeName().compareTo("width") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            width = Double.parseDouble(childNodes.item(j).getTextContent().trim());
                        }
                    }
                    if (childNodes.item(j).getNodeName().compareTo("height") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            height = Double.parseDouble(childNodes.item(j).getTextContent().trim());
                        }
                    }
                }
                shape.add(new Shape(col, wid, type, pad, center.get(0), center.get(1), width, height));
            }
            //坐标
            NodeList scaleList = doc.getElementsByTagName("scale");
            for (int i = 0; i < scaleList.getLength(); i++){
                Color col = Color.black;
                float wid = 1;
                String dir = "x";
                double pos = 0;
                double from = 0, step = 0;
                int amount = 0;
                int precision = 1;
                Node node = scaleList.item(i);
                NodeList childNodes=node.getChildNodes();
                for (int j=0;j<childNodes.getLength();j++){
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
                    if (childNodes.item(j).getNodeName().compareTo("direction") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            dir = childNodes.item(j).getTextContent().trim();
                        }
                    }
                    if (childNodes.item(j).getNodeName().compareTo("pos") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            pos = Double.parseDouble(childNodes.item(j).getTextContent().trim());
                        }
                    }
                    if (childNodes.item(j).getNodeName().compareTo("from") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            from = Double.parseDouble(childNodes.item(j).getTextContent().trim());
                        }
                    }
                    if (childNodes.item(j).getNodeName().compareTo("step") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            step = Double.parseDouble(childNodes.item(j).getTextContent().trim());
                        }
                    }
                    if (childNodes.item(j).getNodeName().compareTo("amount") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            amount = Integer.parseInt(childNodes.item(j).getTextContent().trim());
                        }
                    }
                    if (childNodes.item(j).getNodeName().compareTo("precision") == 0){
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                            precision = Integer.parseInt(childNodes.item(j).getTextContent().trim());
                        }
                    }
                }
                if (dir.equals("x") || dir.equals("X")){
                    xColor = col;
                    xWidth = wid;
                    xPosition = pos;
                    xFrom = from;
                    xStep = step;
                    xAmount = amount;
                    xPrecision = precision;
                }else{
                    yColor = col;
                    yWidth = wid;
                    yPosition = pos;
                    yFrom = from;
                    yStep = step;
                    yAmount = amount;
                    yPrecision = precision;
                }
            }
        }catch (Exception e){
            System.out.println("文件打开失败！");
        }
    }
}

//点
class Points{
    private boolean pointPad = true;
    private Color pointColor = Color.black;
    private float pointWidth = 1;
    private int pointRadius = 1;
    private double pointX = 0, pointY = 0;
    Points(boolean pointPad, Color pointColor, float pointWidth, int pointRadius, double pointX, double pointY){
        this.pointPad = pointPad;
        this.pointColor = pointColor;
        this.pointWidth = pointWidth;
        this.pointRadius = pointRadius;
        this.pointX = pointX;
        this.pointY = pointY;
    }
    @Override
    public String toString(){
        return "Points{" +
                "pointPad=" + pointPad +
                ", pointColor=" + pointColor +
                ", pointWidth=" + pointWidth +
                ", pointRadius=" + pointRadius +
                ", pointX=" + pointX +
                ", pointY=" + pointY +
                '}' + '\n';
    }
    public void setPointPad(boolean pointPad){
        this.pointPad = pointPad;
    }
    public void setPointColor(Color pointColor){
        this.pointColor = pointColor;
    }
    public void setPointWidth(int pointWidth){
        this.pointWidth = pointWidth;
    }
    public void setPointRadius(int pointRadius){
        this.pointRadius = pointRadius;
    }
    public void setPoint(int pointX, int pointY){
        this.pointX = pointX;
        this.pointY = pointY;
    }
    public boolean isPointPad(){
        return pointPad;
    }
    public Color getPointColor(){
        return pointColor;
    }
    public float getPointWidth(){
        return pointWidth;
    }
    public int getPointRadius(){
        return pointRadius;
    }
    public double getPointX(){
        return pointX;
    }
    public double getPointY(){
        return pointY;
    }
}

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

//直线
class MyLine{
    private Color lineColor = Color.black;
    private float lineWidth = 1;
    private double lineX = 0, lineY = 0;
    private double lineSlope = Double.MAX_VALUE;
    MyLine(Color lineColor, float lineWidth, double lineX, double lineY, double lineSlope){
        this.lineColor = lineColor;
        this.lineWidth = lineWidth;
        this.lineX = lineX;
        this.lineY = lineY;
        this.lineSlope = lineSlope;
    }
    @Override
    public String toString(){
        return "MyLine{" +
                "lineColor=" + lineColor +
                ", lineWidth=" + lineWidth +
                ", lineX=" + lineX +
                ", lineY=" + lineY +
                ", lineSlope=" + lineSlope +
                '}' + '\n';
    }
    public void setLineColor(Color lineColor){
        this.lineColor = lineColor;
    }
    public void setLineWidth(int lineWidth){
        this.lineWidth = lineWidth;
    }
    public void setPoint(double lineX, double lineY){
        this.lineX = lineX;
        this.lineY = lineY;
    }
    public Color getLineColor(){
        return lineColor;
    }
    public float getLineWidth(){
        return lineWidth;
    }
    public double getLineX(){
        return lineX;
    }
    public double getLineY(){
        return lineY;
    }
    public double getLineSlope(){
        return lineSlope;
    }
}

//曲线
class Curve{
    private Color curveColor = Color.black;
    private float curveWidth = 1;
    private double curveLeft = 0, curveRight = 0;
    private int curveAmount = 500;
    private String curveFunc = "";
    Curve(Color curveColor, float curveWidth, double curveLeft, double curveRight, int curveAmount, String curveFunc){
        this.curveColor = curveColor;
        this.curveWidth = curveWidth;
        this.curveLeft = curveLeft;
        this.curveRight = curveRight;
        this.curveAmount = curveAmount;
        this.curveFunc = curveFunc;
    }
    @Override
    public String toString(){
        return "Curve{" +
                "curveColor=" + curveColor +
                ", curveWidth=" + curveWidth +
                ", curveLeft=" + curveLeft +
                ", curveRight=" + curveRight +
                ", curveAmount=" + curveAmount +
                ", curveFunc='" + curveFunc + '\'' +
                '}' + '\n';
    }
    public void setCurveColor(Color curveColor){
        this.curveColor = curveColor;
    }
    public void setCurveWidth(float curveWidth){
        this.curveWidth = curveWidth;
    }
    public void setCurveLeft(double curveLeft){
        this.curveLeft = curveLeft;
    }
    public void setCurveRight(double curveRight){
        this.curveRight = curveRight;
    }
    public void setCurveAmount(int curveAmount){
        this.curveAmount = curveAmount;
    }
    public void setCurveFunc(String curveFunc){
        this.curveFunc = curveFunc;
    }
    public Color getCurveColor(){
        return curveColor;
    }
    public float getCurveWidth(){
        return curveWidth;
    }
    public double getCurveLeft(){
        return curveLeft;
    }
    public double getCurveRight(){
        return curveRight;
    }
    public int getCurveAmount(){
        return curveAmount;
    }
    public String getCurveFunc(){
        return curveFunc;
    }
}

//形状
class Shape{
    private Color shapeColor = Color.black;
    private float shapeWid = 1;
    private String shapeType = "rect";
    private boolean shapePad = true;
    private double shapeCenterX = 0, shapeCenterY = 0;
    private double shapeWidth = 0, shapeHeight = 0;
    Shape(Color shapeColor, float shapeWid, String shapeType, boolean shapePad, double shapeCenterX, double shapeCenterY, double shapeWidth, double shapeHeight){
        this.shapeColor = shapeColor;
        this.shapeWid = shapeWid;
        this.shapeType = shapeType;
        this.shapePad = shapePad;
        this.shapeCenterX = shapeCenterX;
        this.shapeCenterY = shapeCenterY;
        this.shapeWidth = shapeWidth;
        this.shapeHeight =shapeHeight;
    }
    @Override
    public String toString(){
        return "Shape{" +
                "shapeColor=" + shapeColor +
                ", shapeWid=" + shapeWid +
                ", shapeType='" + shapeType + '\'' +
                ", shapePad=" + shapePad +
                ", shapeCenterX=" + shapeCenterX +
                ", shapeCenterY=" + shapeCenterY +
                ", shapeWidth=" + shapeWidth +
                ", shapeHeight=" + shapeHeight +
                '}' + '\n';
    }
    public void setShapeColor(Color shapeColor){
        this.shapeColor = shapeColor;
    }
    public void setShapeWid(float shapeWid){
        this.shapeWid = shapeWid;
    }
    public void setShapeType(String shapeType){
        this.shapeType = shapeType;
    }
    public void setShapePad(boolean shapePad){
        this.shapePad = shapePad;
    }
    public void setShapeCenterX(double shapeCenterX){
        this.shapeCenterX = shapeCenterX;
    }
    public void setShapeCenterY(double shapeCenterY){
        this.shapeCenterY = shapeCenterY;
    }
    public void setShapeWidth(double shapeWidth){
        this.shapeWidth = shapeWidth;
    }
    public void setShapeHeight(double shapeHeight){
        this.shapeHeight = shapeHeight;
    }
    public Color getShapeColor(){
        return shapeColor;
    }
    public float getShapeWid(){
        return shapeWid;
    }
    public String getShapeType(){
        return shapeType;
    }
    public boolean isShapePad(){
        return shapePad;
    }
    public double getShapeCenterX(){
        return shapeCenterX;
    }
    public double getShapeCenterY(){
        return shapeCenterY;
    }
    public double getShapeWidth(){
        return shapeWidth;
    }
    public double getShapeHeight(){
        return shapeHeight;
    }
}