/*

 Author: Azret Missirov 
 Program Name: ColorFactory.java
 Objective: 	This program draws oval which gets filled with mixture 
            	of three colors. Scroll bars allow user to choose colors.
				When user moves scroll bar, three rectangles of three 
				different colors dinamicly change their sizes.User can 
				choose numeral system: decimal, octal, binary, hex, 
				which displayed under rectangles and change dinamicly 
				as user moves scroll bar.				         
*/
 
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.ItemEvent; 
import java.awt.event.ItemListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

//********************************ColorFactory()********************************* 

public class ColorFactory 
{
 static JScrollBar redbar,greenbar,bluebar;
 static JRadioButton decimal,octal,binary,hex;

 static PaintPanel paintPanel;
 static ToolsPanel toolsPanel;
  
 static int rc, gc, bc, base;
 
//********************************ToolsPanel()********************************* 
//           This class creates right side panel where scroll bars,
//           and radio buttons located.


 public static class ToolsPanel extends JPanel 
 {
  public ToolsPanel () 
  {
     
   setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
   setPreferredSize (new Dimension(170, 500));
	
	//labels on top of scroll bars					
   JLabel red=new JLabel("Red");
   JLabel blue=new JLabel("Blue");
   JLabel green=new JLabel("Green");
	
	//title of radio buttons cells
   JLabel numsys=new JLabel("Numeral Systems");
     
   numsys.setForeground(Color.gray);
   
	//title of scroll bars cells
   JLabel colormix=new JLabel("Color Mixture");
   colormix.setForeground(Color.gray);
	
	//radio buttons 		
   decimal=new JRadioButton("Decimal",true);
   octal=new JRadioButton("Octal");
   binary=new JRadioButton("Binary");
   hex=new JRadioButton("Hex");
   
	//uniting radio buttons in one group      
   ButtonGroup group = new ButtonGroup();
   group.add(decimal);
   group.add(octal);
   group.add(binary);
   group.add(hex);
	
	//radio buttons listen to events		
   decimal.addItemListener(itemListener);
   octal.addItemListener(itemListener);
   binary.addItemListener(itemListener);
   hex.addItemListener(itemListener);
   
	//satting up scroll bars and listening to events
   redbar= new JScrollBar(Adjustable.HORIZONTAL, 0, 0, 0, 255);
   redbar.setBlockIncrement(1);
   redbar.addAdjustmentListener(adjustmentListener); 

   greenbar= new JScrollBar(Adjustable.HORIZONTAL, 0, 0, 0, 255);
   greenbar.setBlockIncrement(1);
   greenbar.addAdjustmentListener(adjustmentListener);

   bluebar= new JScrollBar(Adjustable.HORIZONTAL, 0, 0, 0, 255);
   bluebar.setBlockIncrement(1);
   bluebar.addAdjustmentListener(adjustmentListener);

   // adding to panel
   add(numsys);
   add (Box.createRigidArea (new Dimension (0, 10)));
   add(decimal);
   add(octal);
   add(binary);
   add(hex);
   add (Box.createRigidArea (new Dimension (0, 30)));
   add(colormix);
   add (Box.createRigidArea (new Dimension (0, 10)));
   add(red);
   add(redbar);
   add(green);
   add(greenbar);
   add(blue);
   add(bluebar);
  } 	
 };
 
//********************************adjustmentListener()********************************* 
//                           Listens to scroll bar events.
              
  public static AdjustmentListener adjustmentListener = new AdjustmentListener() 
  {
   public void adjustmentValueChanged(AdjustmentEvent adjustmentEvent) 
   {
    rc=redbar.getValue();
    gc=greenbar.getValue();
    bc=bluebar.getValue();
    paintPanel.repaint();
   }
  };
 
//********************************itemListener()********************************* 
//                           Listens to radio buttons events.

  public static ItemListener itemListener = new ItemListener()
  {
   public void itemStateChanged(ItemEvent itemEvent)
   {
    paintPanel.repaint();
    if (octal.isSelected()) base=8; // octal
    else if (decimal.isSelected()) base=0; // decimal
    else if (binary.isSelected()) base=2; // binary
    else if (hex.isSelected()) base=16;  // hex
   }
  };

//********************************PaintPanel()********************************* 
//           This class creates left side panel where we draw oval,rectangles,
//           and strings.  
  public static class PaintPanel extends JPanel 
  {
   public PaintPanel ()
   {
    setPreferredSize (new Dimension(450,600));
    setBorder (BorderFactory.createEtchedBorder ()); 
   }
 
   public void paintComponent (Graphics page)
   {
    int barInitialPosition=1;
   
	 super.paintComponent (page);
    
	 //using following varibles to convert numerical systems
	 String str="",str1="", str2="";
    String redstr="",greenstr="",bluestr="";
  
    if (base==2)
    {
	  //converting to binary
     redstr = Integer.toBinaryString(rc);
     greenstr= Integer.toBinaryString(gc);
     bluestr = Integer.toBinaryString(bc);
	  str=redstr; 
     str1=greenstr;
     str2=bluestr; 
    }
    else if (base==16)
    {
	  //converting to hex
	  redstr = Integer.toHexString(rc);
	  greenstr = Integer.toHexString(gc);
	  bluestr = Integer.toHexString(bc);
	  str=redstr; 
	  str1=greenstr;
     str2=bluestr; 
    } 
    else
    {
	  //converting to octal
	  str=Integer.toString(rc,base);
	  str1=Integer.toString(gc,base);
     str2=Integer.toString(bc,base);
    }    
	  //title
	  page.setColor(Color.gray);
	  page.setFont(new Font("times", Font.BOLD, 30));
     page.drawString("COLOR FACTORY",70,60);
	  
	  //rectangles' titles
	  page.setFont(new Font("times", Font.BOLD, 15));
	  page.setColor (new Color(rc,gc,bc));
     
	  //draw oval
	  page.fillOval(100, 440, 250, 150);
	 
	  //red rectangle
	  page.setColor(Color.red);
	  page.fillRect(85,360-rc,60,barInitialPosition+rc);
	  page.drawString("RED",100,380);
     page.drawString(str,105,395);	 
	  
	  //green rectangle 
     page.setColor(Color.green);
	  page.fillRect(200,360-gc,60,barInitialPosition+gc);
	  page.drawString("GREEN",210,380);
	  page.drawString(str1,215,395);	 
	  
	  //blue rectangle	
  	  page.setColor (Color.blue);
	  page.fillRect(305,360-bc,60,barInitialPosition+bc);
	  page.drawString("BLUE",320,380);
	  page.drawString(str2,325,395);	 
   }
  };
  
  //********************************main()********************************* 

 public static void main (String[] args)
 {
  JFrame frame = new JFrame ("Color Factory");
  frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
  
  toolsPanel=new ToolsPanel();
  paintPanel=new PaintPanel();

  JPanel primary = new JPanel();
  primary.add (paintPanel);
  primary.add (toolsPanel);
  
  frame.getContentPane().add(primary);
  frame.setResizable(false);
  frame.pack();
  frame.setVisible(true);
 } 
}