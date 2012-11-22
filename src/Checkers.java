// {$R Checkers.JFM}

/*
 * Checkers.java version 1.0
 * Copyright (c) 1995-1997 Bill Bereza. All Rights Reserved.
 *
    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
    
    Contact Bill Bereza:
    email: bereza@pobox.com
    url:   www.pobox.com/~bereza
    
    address:
           9526 Judson Rd.
           Ravenna, MI 49451
           
 *
 * $Log: Checkers.java,v $
 * Revision 1.1  1997/12/06 21:59:05  bereza
 * Initial revision
 *
 *
 *      $Id: Checkers.java,v 1.1 1997/12/06 21:59:05 bereza Exp bereza $
 */

import java.awt.*;
import java.applet.Applet;
import java.lang.*;

class BoardGrid {
    public int xpos;
    public int ypos;
    public int xwidth;
    public int ywidth;
}

// Class Checkers
public class Checkers extends Applet
{
    final int MenuBarHeight = 0;
    final int maxToughnessForUndo = 2;

    // Component Declaration
    public CheckersPanel checkersPanel;
    public Choice toughChoice;
    public Label toughLabel;
    public CheckboxGroup CheckboxGrpInCheckers;
    public Checkbox redRadio;
    public Checkbox blackRadio;
    public Button newGameButton;
    public Label colorLabel;
    public Button undoButton;
    public Label Title;
    public Image checkersLogo;
    public Label nameLabel;
    public Button infoButton;
    // End of Component Declaration

    public InfoFrame checkersInfo;
    
    private boolean infoShown=false;

    // init()
    public void init()
    {
        // Frame Initialization
        setForeground(Color.black);
        setBackground(Color.lightGray);
        setFont(new Font("Dialog",Font.BOLD,12));
        setLayout(null);
        // End of Frame Initialization

        // Component Initialization
        checkersPanel = new CheckersPanel(this);
        //checkersPanel.setLayout(null);
        //checkersPanel.setForeground(Color.black);
        //checkersPanel.setBackground(Color.lightGray);
        //checkersPanel.setFont(new Font("Dialog",Font.BOLD,12));
        toughChoice = new Choice();
        toughChoice.addItem("Wimp");
        toughChoice.addItem("Easy");
        toughChoice.addItem("Normal");
        toughChoice.addItem("Hard");
        toughChoice.addItem("Genius");
        toughChoice.setFont(new Font("Helvetica",Font.BOLD,12));
        toughChoice.select(2);
        toughLabel = new Label("Toughness",Label.RIGHT);
        toughLabel.setFont(new Font("Helvetica",Font.BOLD,12));
        CheckboxGrpInCheckers = new CheckboxGroup(); // CheckboxGroup in Checkers
        redRadio = new Checkbox("Red",CheckboxGrpInCheckers,false);
        redRadio.setBackground(Color.lightGray);
        redRadio.setFont(new Font("Dialog",Font.BOLD,12));
        blackRadio = new Checkbox("Black",CheckboxGrpInCheckers,true);
        blackRadio.setBackground(Color.lightGray);
        blackRadio.setFont(new Font("Dialog",Font.BOLD,12));
        newGameButton = new Button("New Game");
        newGameButton.setFont(new Font("Dialog",Font.BOLD,14));
        colorLabel = new Label("Color",Label.RIGHT);
        colorLabel.setFont(new Font("Dialog",Font.BOLD,12));
        undoButton = new Button("Undo");
        undoButton.setFont(new Font("Dialog",Font.BOLD,12));
        Title = new Label("Java Checkers",Label.LEFT);
        Title.setFont(new Font("Dialog",Font.BOLD + Font.ITALIC,12));
        checkersLogo = getImage(getCodeBase(), "images/bill.gif");
        nameLabel = new Label("by Bill Bereza",Label.LEFT);
        nameLabel.setFont(new Font("Dialog",Font.PLAIN,12));
        infoButton = new Button("Info...");
        infoButton.setFont(new Font("Dialog",Font.BOLD,12));
        // End of Component Initialization

        checkersInfo=new InfoFrame(this, "Java", "Checkers");

        // Add()s
        add(infoButton);
        add(nameLabel);
        add(Title);
        add(undoButton);
        add(colorLabel);
        add(newGameButton);
        add(blackRadio);
        add(redRadio);
        add(toughLabel);
        add(toughChoice);
        add(checkersPanel);
        // End of Add()s

        InitialPositionSet();
    } // End of init()

    // start()
    public void start()
    {
    //checkersPanel.newGame();
    } // End of start()

    // stop()
    public void stop()
    {
    } // End of stop()

    // destroy()
    public void destroy()
    {
    } // End of destroy()

    public void paint(Graphics g)
    {
        // paint()
        g.drawImage(checkersLogo, 365,413,35,46, this);
        // End of paint()
    }

    public void InitialPositionSet()
    {
        // InitialPositionSet()
        resize(410,505);
        checkersPanel.reshape(5,5+MenuBarHeight,400,400);
        toughChoice.reshape(95,413+MenuBarHeight,106,27);
        toughLabel.reshape(2,417+MenuBarHeight,88,19);
        redRadio.reshape(95,448+MenuBarHeight,59,16);
        blackRadio.reshape(95,466+MenuBarHeight,67,16);
        newGameButton.reshape(284,465+MenuBarHeight,116,34);
        colorLabel.reshape(46,453+MenuBarHeight,43,19);
        undoButton.reshape(178,465+MenuBarHeight,99,34);
        
        Title.reshape(212,417+MenuBarHeight,119,19);
        nameLabel.reshape(213,436+MenuBarHeight,99,19);
        infoButton.reshape(6,477+MenuBarHeight,76,22);
        // End of InitialPositionSet()
        
        //checkersInfo.show();
        //checkersInfo.InitialPositionSet();
    }

    public boolean handleEvent(Event evt)
    {
        // handleEvent()
        if (evt.id == Event.WINDOW_DESTROY && evt.target == this) Checkers_WindowDestroy(evt.target);
        else if (evt.id == Event.ACTION_EVENT && evt.target == newGameButton) newGameButton_Action(evt.target);
        else if (evt.id == Event.ACTION_EVENT && evt.target == redRadio) redRadio_Action(evt.target);
        else if (evt.id == Event.ACTION_EVENT && evt.target == blackRadio) blackRadio_Action(evt.target);
        else if (evt.id == Event.ACTION_EVENT && evt.target == toughChoice) toughChoice_Action(evt.target);
        else if (evt.id == Event.ACTION_EVENT && evt.target == undoButton) undoButton_Action(evt.target);
        else if (evt.id == Event.ACTION_EVENT && evt.target == infoButton) infoButton_Action(evt.target);
        // End of handleEvent()

        return super.handleEvent(evt);
    }

    // Event Handling Routines
    public void Checkers_WindowDestroy(Object target)
    {
        System.exit(0);
    }

    public void newGameButton_Action(Object target)
    {
        checkersPanel.newGame();
    }

    public void redRadio_Action(Object target)
    {
        checkersPanel.setColor("red");
    }

    public void blackRadio_Action(Object target)
    {
        checkersPanel.setColor("black");
    }

    public void toughChoice_Action(Object target)
    {
        Choice myChoice=(Choice)target;
        int selected=myChoice.getSelectedIndex();
        
        if(selected > maxToughnessForUndo) {
            undoButton.hide(); undoButton.disable();
        }
        else {
            undoButton.enable(); undoButton.show();
        }
        //myErr(target);
        checkersPanel.setToughness(selected);
    }

    public void undoButton_Action(Object target)
    {
        checkersPanel.undo();
    }

    public void infoButton_Action(Object target)
    {
        checkersInfo.show();
        if(!infoShown) {
            checkersInfo.InitialPositionSet();
            infoShown=true;
        }
        //System.out.println("Button1_Action");
    }

    // End of Event Handling Routines

    public String getAppletInfo() {
        return "Java Checkers Copyright (c) 1997 by Bill Bereza\nAll Rights Reserved.\nJavaCheckers comes with ABSOLUTELY NO WARRANTY.\nThis is free software, and you are welcome to redistribute it\nunder certain conditions.";
    }
    
} // End of Class Checkers

class CheckersPanel extends Panel {
/* layout of checker board in a 36 piece array
0       1       2       3
4       5       6       7       8X
9       10      11      12
13      14      15      16      17X
18      19      20      21
22      23      24      25      26X
27      28      29      30
31      32      33      34      35X

places labeled X are null places

all single moves are done in increments of +/-4 and +/-5
jumps are done in increments of +/-8 +/-10

if any move would land in a null place, then it can't be done
*/

Checkers myAppl;
  
protected BoardGrid mainGrid[] = new BoardGrid[36];
protected static int redType = 0;
protected static int blackType=1;
protected static int redKingType = 2;
protected static int blackKingType=3;
protected static int emptyType=-1;
protected static int nullType=-2;
  
protected int oppositeType(int type) {
  return (int)((type>=0) ? ((type+1) % 2) : type);
}
  
protected int kingType(int type) {
  return (int)((type==0 || type==1) ? (type+2) : type);
}

protected int plainType(int type) {
  return (int)((type>=0) ? (type % 2) : type);
}

protected int mainBoard[] = new int[36];
protected int preBoard[]  = new int[36];

protected void undo() {
    System.arraycopy(preBoard,0,mainBoard,0,36);
    repaint();
    myAppl.play(myAppl.getCodeBase(), "audio/ding.au");
}

protected int carryPiece;
protected int userColor;

protected boolean movingPiece=false;
protected boolean didjump=false;
protected int carryPlace;
protected int carryXpos, carryYpos;
protected int tough=0;
protected int defTough=2;
protected boolean waiting=false;

protected void setToughness(int toTough)
{
    tough=toTough;
}

String color;
String defColor="black";

protected void setColor(String toColor)
{
    color=toColor;
}

//protected Panel controlPanel;

public double score() {
  return score(userColor);
}

public double score(int type) { 
  //myErr("score: "+type);
  double t=score(type, mainBoard);
  //myErr("score: return "+t);
  if(t<0 || t>24) myErr("score(i)");
  return t;
}

public double score(int type, int aBoard[]) {
  double reds, blacks, ret=0.0;
  int loop;     

  for(reds=blacks=loop=0;loop<36;loop++) {
    switch(aBoard[loop]) {
    case 2: //redKingType:
      reds+=1;
    case 0://redType:
      reds+=1;
      break;
    case 3://blackKingType:
      blacks+=1;
    case 1://blackType:
      blacks+=1;
      break;
    default:
      break;
    }
  }
  switch(type) {
  case 0://redType:
    if(reds==0) {
      return 0.0;
    }
    if(blacks==0) {
      return 24.0;
    }
    ret=reds/blacks;
    if(ret>24 || ret<0) myErr("score(i,i)");
    return ret;
  case 1://blackType:
    if(reds==0) {
      return 24.0;
    }
    if(blacks==0) {
      return 0.0;
    }
    ret=blacks/reds;
    if(ret>24 || ret<0) myErr("score(i,i)");
    return ret;
  default:
    break;
  }
  return ret;
}               

  //#returns < zero if the piece could not jump
  //#or returns the place to jump to
  //#
  //#int piece_could_jump(int piece, color,  board)
protected int piece_could_jump(int piececount, int piececolor, int tBoard[]) {
  int j[] = new int[4];
  int ret=-1;
  int loop;

  if(plainType(piececolor)!=redType && plainType(piececolor)!=blackType)
    return -1;
  //myErr("piece_could_jump: "+piececount+", "+piececolor+", "+tBoard+", "+fromPiece);

  j[0]=piececount-8;
  j[1]=piececount-10;
  j[2]=piececount+8;
  j[3]=piececount+10;

  for(loop=0;loop<4;loop++) {
    if(j[loop] >=0 && j[loop] <= 31) {
      if(valid_move(piececount, j[loop], tBoard, piececolor)) {
        //myErr( "JA!");
            ret=j[loop];
      }
    }
  }

  //myErr("piece_could_jump: "+ret);
  //if(ret>0) {
  //  while((ret=j[(int)(Math.random()*4.0) - 1])<0);
  //}
  return ret;
}

  //#returns a -1 if the color could not jump otherwise return the index of 
  //#the jumpable piece
  //#int could_jump(char *color, board)
protected int could_jump(int jumpcol, int tBoard[]) {
  int ret=-1;
  int loop;

  //myErr("could_jump: "+jumpcol+", "+tBoard);

  for(loop=0;ret<0 && loop<36;loop++) {
    if(plainType(tBoard[loop]) == jumpcol) {
      if(piece_could_jump(loop, jumpcol, tBoard)>=0) {
          ret=loop;
      }
    }
  }
  //myErr("could_jump: "+ret);
  return ret;
}

protected boolean valid_move(int from, int to, int aBoard[], int colorfor) {
  if(plainType(colorfor) == userColor) {
    return (to>=0 && to<=35 && from >=0 && from<=35 && plainType(aBoard[from])==colorfor && aBoard[to]==emptyType 
            && ((from-to == 4 || from-to==5) 
                || ((from-to == 10 && plainType(aBoard[from-5])==oppositeType(colorfor)) 
                    || (from-to == 8 && plainType(aBoard[from-4])==oppositeType(colorfor))) 
                || (aBoard[from]==kingType(colorfor) 
                    && ((to-from == 4 || to-from==5) 
                        || ((to-from == 10 && plainType(aBoard[from+5])==oppositeType(colorfor)) 
                            || (to-from == 8 && plainType(aBoard[from+4])==oppositeType(colorfor)))))));
  }
  else {
    return (to>=0 && to<=35 && from >=0 && from<=35 && plainType(aBoard[from])==colorfor && aBoard[to]==emptyType 
            && ((to-from == 4 || to-from==5) 
                || ((to-from == 10 && plainType(aBoard[from+5])==oppositeType(colorfor)) 
                    || (to-from == 8 && plainType(aBoard[from+4])==oppositeType(colorfor))) 
                || (aBoard[from]==kingType(colorfor) 
                    && ((from-to == 4 || from-to==5) 
                        || ((from-to == 10 && plainType(aBoard[from-5])==oppositeType(colorfor)) 
                            || (from-to == 8 && plainType(aBoard[from-4])==oppositeType(colorfor)))))));
  }
}

  //int retperf[] = new int[36];
protected int[] perform_move(int from, int to, int zBoard[]) {
  //System.arraycopy(zBoard,0,retperf,0,36);
  zBoard[to]=zBoard[from];
  zBoard[from]=emptyType;
  didjump=false;
  if((to>=0 && to<=3) || (to>=31 && to<=35)) zBoard[to]=kingType(zBoard[to]);
  if(to>from) {
    //if(to-from<=5) return zBoard;
    if(to-from == 8) { 
      didjump=true;
      zBoard[from+4]=emptyType;
    }
    else if(to-from == 10) {
      didjump=true;
      zBoard[from+5]=emptyType;
    }
  }
  else if(to<from) {
    //if(from-to<=5) return zBoard;
    if(from-to == 8) { 
      didjump=true;
      zBoard[from-4]=emptyType;
    }
    else if(from-to == 10) {
      didjump=true;
      zBoard[from-5]=emptyType;
    }
  }     
  return zBoard;
}       

protected void myErr(String message)
{
    System.out.println(message);
}

  //#recursively finds the score for the tree rooted at this
  //#board if the next move is made by the color
  //#
  //#float scores_for_moves(colorfor, colormove, board, depth)
protected double scores_for_moves(int colorfor, int colormove, int aBoard[], int depth) {
  //Board aBoard=tBoard;
  int piececount=0;
  int piece=0;
  int oddrow=0;
  int moves[] = new int[8];
  int loop=0;
  int newboard[]=new int[36];
  int t=0; double temp=0.0;
  double ret=0.0, lowest=25.0, highest=-1.0;
  int a=0;
  boolean valid=false, wasValid=false, anyValid=false;

  ret=score(colormove, aBoard);
  if(ret<0 || ret>24) myErr("ret out of bounds: "+ret+" :scores_for_moves:[no depth]");
  if(depth==0 || ret==0 || ret==24) { return ret; }

  if((could_jump(colormove, aBoard))>=0) {
    for(piececount=0;piececount<36;piececount++) {
                                
      piece=aBoard[piececount];

      t=piece_could_jump(piececount, colormove, aBoard);
      if(t>=0) {
        valid=valid_move(piececount, t, aBoard, colormove);
        System.arraycopy(aBoard, 0, newboard, 0, 36);
        if(valid) perform_move(piececount, t, newboard);
        wasValid=valid;
        while(wasValid && didjump
              && (a=piece_could_jump(t, colormove, newboard))>=0){
          wasValid=valid_move(t, a, newboard, colormove);
          if(wasValid) perform_move(t,a,newboard);
          t=a;
        }

        if(valid) {
         anyValid=true;
          temp=scores_for_moves(colorfor, oppositeType(colormove), newboard, depth-1);
      if(temp<0 || temp>24) 
        myErr("temp out of bounds: "+temp+" :scores_for_moves[must jump]");
          // myErr("temp: "+temp+" highest: "+highest+" lowest: "+lowest);
          if(temp>highest) { highest=temp; }
          if(temp<lowest)  { lowest =temp; }
        }
      } 
    }
  }
  else {
    for(piececount=0;piececount<36;piececount++) {
      piece=aBoard[piececount];                                              
      if(plainType(piece) == colormove) {

        moves[0]=piececount+5;
        moves[1]=piececount-5;
        moves[2]=piececount+4;
        moves[3]=piececount-4;
        moves[4]=piececount+8;
        moves[5]=piececount-8;
        moves[6]=piececount+10;
        moves[7]=piececount-10;

        for(loop=0;loop<8;loop++) {
          a = moves[loop];
          if((a>=0) && (a<=35)) {


            valid=valid_move(piececount, a, aBoard, colormove);
            System.arraycopy(aBoard,0,newboard,0,36);
            if(valid) perform_move(piececount, a, newboard);
            
            wasValid=valid;
            while(wasValid && didjump
                  && (t=piece_could_jump(a, colormove, newboard))>=0){
              wasValid=valid_move(a, t, newboard, colormove);
              if(wasValid) perform_move(a,t,newboard);
              a=t;
            }
            if(valid) {
                anyValid=true;
              temp=scores_for_moves(colorfor, oppositeType(colormove), newboard, depth-1);
              if(temp<0 || temp>24) 
                myErr("temp out of bounds: "+temp+" :scores_for_moves");
              // myErr("temp: "+temp+" highest: "+highest+" lowest: "+lowest);
              if(temp>highest) highest=temp;
              if(temp<lowest) lowest=temp;
            }
          }
        }
                                        
      }
      
    }
  }
  
  if(lowest<0 || lowest>24) {
        //myErr("lowest out of bounds: "+lowest+"scores_for_moves");
        lowest=score(colormove, aBoard);
  }
  
  ret=24.0-lowest;
  
  return ret;
}


  //#returns a new board for a color's move
  //#
  //#board make_move(color, board)
protected void make_move(int colorfor, int aBoard[]) {
  int colormove=0;
  int piececount=0,a=0;
  int piecet=0, t=0;
  int newboard[]=new int[36];
  int piece=0;
  int oddrow=0, loop=0;
  int moves[] = new int[8];
  double ret = 0.0;
  double temp=0.0;
  double highest=-1.0, lowest=25.0;
  int highBoard[]=new int[36], lowBoard[]=new int[36];
  boolean valid=false, wasValid=false;
  int from=0, to=0;

  //myErr("make_move: "+colorfor+", "+aBoard);

  colormove=colorfor;

  piececount=0;

  System.arraycopy(aBoard,0,lowBoard,0,36);
  System.arraycopy(aBoard,0,highBoard,0,36);

  if((could_jump(colorfor, aBoard))>=0) {
    for(piececount=0;piececount<36;piececount++) {
                                
      piece=aBoard[piececount];

      t=piece_could_jump(piececount, colorfor, aBoard);
      if(t>=0) {
             valid=valid_move(piececount, t, aBoard, colorfor);
             System.arraycopy(aBoard,0,newboard,0,36);
             if(valid) perform_move(piececount, t, newboard);
             from=piececount; to=t;
             wasValid=valid;
             while(wasValid && didjump
               && (a=piece_could_jump(t, colorfor, newboard))>=0){
                 wasValid=valid_move(t, a, newboard, colorfor);
                 if(wasValid) perform_move(t,a,newboard);
                 from=t; to=a;
                 t=a;
             }

            if(valid) {
                temp= scores_for_moves(colorfor, oppositeType(colorfor), newboard, tough);
                if(temp<0 || temp>24) 
                        myErr("temp out of bounds: "+temp+" :make_move[must jump]");
                //myErr("temp: "+temp+" highest: "+highest+" lowest: "+lowest);
                if(temp>highest) { highest=temp; System.arraycopy(newboard,0,highBoard,0,36); }
                if(temp<lowest)  { lowest =temp; System.arraycopy(newboard,0,lowBoard,0,36); }
            }
      } 
    }
  }
  else {
    for(piececount=0;piececount<36;piececount++) {
      piece=aBoard[piececount];
                
      if(plainType(piece) == colorfor) {
        moves[0]=piececount+5;
        moves[1]=piececount-5;
        moves[2]=piececount+4;
        moves[3]=piececount-4;
        moves[4]=piececount+8;
        moves[5]=piececount-8;
        moves[6]=piececount+10;
        moves[7]=piececount-10;

        for(loop=0;loop<8;loop++) {
          a=moves[loop];
          if(a>=0 && a<=35) {
        
              valid=valid_move(piececount, a, aBoard, colormove);
              System.arraycopy(aBoard,0,newboard,0,36);
              if(valid) perform_move(piececount, a, newboard);
              from=piececount; to=a;
            
            wasValid=valid;
            while(wasValid && didjump
                  && (t=piece_could_jump(a, colormove, newboard))>=0){
              wasValid=valid_move(a, t, newboard, colormove);
              perform_move(a,t,newboard);
              from=a; to=t;
              a=t;
            }
            if(valid) {
              temp= scores_for_moves(colorfor, oppositeType(colorfor), newboard, tough);
              if(temp<0 || temp>24) 
                myErr("temp out of bounds: "+temp+" :make_move[must jump]");
              //myErr("temp: "+temp+" highest: "+highest+" lowest: "+lowest);
              if(temp>highest) { highest=temp; System.arraycopy(newboard,0,highBoard,0,36); }
              if(temp<lowest)  { lowest =temp; System.arraycopy(newboard,0,lowBoard,0,36); }
            }
          }
        }
                                        
      }
    }
  }

  if(lowest<0 || lowest>24) {
      lowest=score(colormove, aBoard);
        //myErr("lowest out of bounds: "+lowest+"make_move");
  }
  
  ret=24.0-lowest;

  if(ret == 24.0) {
    System.out.println("I will win within "+tough+" moves.");
  }
  else if(ret == 0.0) {
    System.out.println("I will lose within "+tough+" moves.");
  }
  System.arraycopy(lowBoard,0,aBoard,0,36);
}       

protected boolean pieceIsInside(int piece, int x, int y) {
  return (x>=mainGrid[piece].xpos && y>=mainGrid[piece].ypos 
          && x<=(mainGrid[piece].xpos+mainGrid[piece].xwidth)
          && y<=(mainGrid[piece].ypos+mainGrid[piece].ywidth));
}

  int couldjump=-1;
public boolean mouseDown(Event evt, int x, int y) {
  int loop;
  Graphics g = getGraphics();

  //myErr("\nmouseDown");

  for(loop=0;loop<36;loop++) {
    if(pieceIsInside(loop, x,y) 
       //&& (mainBoard[loop] != emptyType) ) {
       && (plainType(mainBoard[loop]) == userColor) ) {
      //myErr("mouse is inside piece " + loop);
      couldjump=could_jump(plainType(mainBoard[loop]), mainBoard);
      carryPiece=mainBoard[loop];
      carryPlace=loop;
      carryXpos=mainGrid[loop].xpos;
      carryYpos=mainGrid[loop].ypos;
      mainBoard[loop]=emptyType;
      g.drawImage(pieceImage(mainBoard[loop]), 
                  mainGrid[loop].xpos, mainGrid[loop].ypos,
                  mainGrid[loop].xwidth, mainGrid[loop].ywidth,
                  this);
      movingPiece=true;
      g.drawImage(pieceImage(carryPiece),
                  carryXpos, carryYpos,
                  mainGrid[carryPlace].xwidth, mainGrid[carryPlace].ywidth,
                  this);
                                
      return true;
    }
  }
                
  return true;
}

public boolean mouseUp(Event evt, int x, int y) {
  int loop;
  Graphics g = getGraphics();
  boolean valid;
                
  //myErr("\nmouseUp");

  if(!movingPiece) {
    return true;
  }

  for(loop=0;loop<36;loop++) {
    if(pieceIsInside(loop, x,y)
       && (mainBoard[loop] == emptyType)) {
      //myErr("mouse is inside piece " + loop);
      mainBoard[carryPlace]=carryPiece;
      System.arraycopy(mainBoard,0,preBoard,0,36);
      //couldjump=could_jump(carryPiece, mainBoard);
      valid=valid_move(carryPlace, loop, mainBoard, plainType(carryPiece));
      if(valid) {
        perform_move(carryPlace, loop, mainBoard);
              //System.arraycopy(retperf,0,mainBoard,0,36);
        //mainBoard[carryPlace]=emptyType;
                //mainBoard[loop]=carryPiece;
                //myErr("Score: " + score());
                movingPiece=false;      
                repaint();
                if(score()==0) {
                myAppl.play(myAppl.getCodeBase(), "audio/joy.au");
                newGame();
                // computer won
                }
                else if(score() == 24) {
                  myAppl.play(myAppl.getCodeBase(), "audio/joy.au");
                  newGame();
                  // user won
                }
                else { // keep going
                    //play(getCodeBase(), "audio/beep.au");
                    //myErr("couldjump: " + couldjump + " didjump: " + didjump);
                    // could've jumped but didn't
                    if(couldjump>=0 && !didjump) {
                        mainBoard[(couldjump == carryPlace) ? loop : couldjump]=emptyType;
                    }
                    if(didjump && piece_could_jump(loop,plainType(mainBoard[loop]), mainBoard)>=0) {
                        // user can jump again
                    }
                    else { // my move
                    waiting=true;
                    myAppl.showStatus("Checkers: making move...");
                    repaint();
                    make_move(oppositeType(userColor), mainBoard);
                    myAppl.showStatus("");
                    waiting=false;
                    myAppl.play(myAppl.getCodeBase(), "audio/ding.au");
                    //myErr("score after computer : " + score());
                    if(score()==0) {
                        myAppl.play(myAppl.getCodeBase(), "audio/joy.au");
                        newGame();
                        // computer won
                    }
                    else if(score() == 24) {
                        myAppl.play(myAppl.getCodeBase(), "audio/joy.au");
                        newGame();
                        // user won
                    }
                }
      }                                 
        System.gc();

        repaint();
        return true;
      }
      else {
        //myErr("Invalid move");
        break;
      }
    }
  }
  mainBoard[carryPlace]=carryPiece;             
  movingPiece=false;
  System.gc();

  repaint();
  return true;
}

public boolean mouseDrag(Event evt, int x, int y) {
                
  if(movingPiece) {
    carryXpos=(x-(mainGrid[carryPiece].xwidth/2));
    carryYpos=(y-(mainGrid[carryPiece].ywidth/2));
    repaint();
  }

  return true;
}

protected void newGame() {
  int loop;
  Dimension d=size();
  int xoff=d.width/8;
  int yoff=d.height/8;

  myAppl.play(myAppl.getCodeBase(), "audio/return.au");
  if(color.equals("red")) {
    userColor = redType;
    for(loop=0;loop<13;loop++) {
      mainBoard[loop] = blackType;
    }
    for(loop=13;loop<22;loop++) {
      mainBoard[loop] = emptyType;
    }
    for(loop=22;loop<35;loop++) {
      mainBoard[loop] = redType;
    }
  }
  else {
    userColor = blackType;
    for(loop=0;loop<13;loop++) {
      mainBoard[loop] = redType;
    }
    for(loop=13;loop<22;loop++) {
      mainBoard[loop] = emptyType;
    }
    for(loop=22;loop<35;loop++) {
      mainBoard[loop] = blackType;
    }
  }

  mainBoard[8]=nullType;
  mainBoard[17]=nullType;
  mainBoard[26]=nullType;
  mainBoard[35]=nullType;
  System.arraycopy(mainBoard,0,preBoard,0,36);

  repaint();
  if(userColor == redType) {
    waiting=true;
    myAppl.showStatus("Checkers: making move...");
    repaint();
    make_move(oppositeType(userColor), mainBoard);
    myAppl.showStatus("");
    waiting=false;
    myAppl.play(myAppl.getCodeBase(), "audio/ding.au");
    if(score()==0) {
      myAppl.play(myAppl.getCodeBase(), "audio/joy.au");
      newGame();
      // computer won
    }
    else if(score() == 24) {
      myAppl.play(myAppl.getCodeBase(), "audio/joy.au");
      newGame();
      // user won
    }
  }
}


public CheckersPanel(Checkers appl) {
  int loop;
  String param;
  
  myAppl=appl;
  //myErr("init begin");

  for(loop=0;loop<36;loop++) {
    mainGrid[loop]=new BoardGrid();
  }

  myAppl.showStatus("Checkers: getting images/black_black.gif");
  black_black=myAppl.getImage(myAppl.getCodeBase(), "images/black_black.gif");
  myAppl.showStatus("Checkers: getting images/black_black_king.gif");
  black_black_king=myAppl.getImage(myAppl.getCodeBase(), "images/black_black_king.gif");
  myAppl.showStatus("Checkers: getting images/black_empty.gif");
  black_empty=myAppl.getImage(myAppl.getCodeBase(), "images/black_empty.gif");
  myAppl.showStatus("Checkers: getting images/black_red.gif");
  black_red=myAppl.getImage(myAppl.getCodeBase(), "images/black_red.gif");
  myAppl.showStatus("Checkers: getting images/black_red_king.gif");
  black_red_king=myAppl.getImage(myAppl.getCodeBase(), "images/black_red_king.gif");
  myAppl.showStatus("Checkers: getting images/red_empty.gif");
  red_empty=myAppl.getImage(myAppl.getCodeBase(), "images/red_empty.gif");

  param = null;
  //param = myAppl.getParameter("COLOR");
  color = (param !=null) ? param : defColor;
  //param = myAppl.getParameter("TOUGH");

  tough = (param !=null) ? Integer.parseInt(param) : defTough;
        
  newGame();
  //myErr("toughness: "+tough+"\ninit end");
}


public void update(Graphics g) {
  paint(g);
}


//public String getAppletInfo() {
//  return "Java Checkers by Bill Bereza";
//}

//class CheckersCanvas extends Canvas {

protected Image black_black, black_black_king, black_empty, black_red, black_red_king, red_empty;

Image offscreen = null;
Dimension offscreensize = null;
Graphics offgraphics = null;

Color red = new Color((float)1.0, (float)0.0, (float)0.0);
Color black = Color.black;
Color darkgray = Color.darkGray;
Color darkred = new Color((float)0.7, (float)0.0, (float)0.0);
Color white = Color.white;
Color gray = Color.gray;
Color yellow = Color.yellow;

//public CheckersCanvas(

public Image pieceImage(int colorfor) {
  switch(colorfor) {
  case 0: /*redType:*/  return black_red;
  case 1: /*blackType:*/        return black_black;
  case 3:/*blackKingType:*/     return black_black_king;
  case 2:/*redKingType:*/       return black_red_king;
  default:      return black_empty;
  }
  //return null;
}

public void paint(Graphics g) {
  Dimension d = size();
  int x, y, loop, lx, ly;
  int xoff=d.width/8;
  int yoff=d.height/8;

  //myErr("update begin");

  if ((offscreen == null) || (d.width != offscreensize.width) || (d.height != offscreensize.height)) {
    offscreen = createImage(d.width, d.height);
    offscreensize = d;
    offgraphics = offscreen.getGraphics();
    offgraphics.setFont(getFont());
  }

  offgraphics.setColor(getBackground());
  offgraphics.fillRect(0, 0, d.width, d.height);

  //myErr("offgraphics done");
                
  //if(mainGrid==null) myErr("Insanity!");

  loop=0;
  for(ly=0, y=1;ly<8;ly++,y+=yoff) {
    for(lx=0,x=1;lx<8;lx++,x+=xoff) {
      if((lx%2)!=(ly%2) && loop<35) {
        if(loop==8 || loop==17 || loop==26) { loop++; }
        mainGrid[loop].xpos=x;
        mainGrid[loop].ypos=y;
        mainGrid[loop].xwidth=xoff-2;
        mainGrid[loop].ywidth=yoff-2;
        offgraphics.drawImage(pieceImage(mainBoard[loop++]), x, y, xoff-2, yoff-2, this);
      }
      else {
        //myErr("blank image");
        offgraphics.drawImage(red_empty, x, y, xoff-2, yoff-2, this);
      }
    }
  }

  if(movingPiece) {
    offgraphics.drawImage(pieceImage(carryPiece), 
                          carryXpos, carryYpos, 
                          mainGrid[carryPlace].xwidth, mainGrid[carryPlace].ywidth, this);
  }

  //if(waiting) {
  //    offgraphics.setColor(Color.white);
  //    offgraphics.fillRect(d.width/3, d.height/3, 2*d.width/3, 2*d.height/3);
  //}

  g.drawImage(offscreen,0,0,null);
  //myErr("g.drawImage done");
  //myErr("update end");
}

}
