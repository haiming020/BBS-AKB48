// {$R InfoFrame.JFM}

/* infoFrame.java
 * Copyright (c) 1997 Bill Bereza. All Rights Reserved.
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
 */
 
import java.awt.*;
import java.applet.*;
import java.net.*;

// Class InfoFrame
public class InfoFrame extends Frame
{
    final int MenuBarHeight = 0;
    boolean fForm_Create;

    // Component Declaration
    public Image myImage;
    public Label titleLabel;
    public Label copyrightLabel;
    public TextArea gnuNotice;
    public Label rightsLabel;
    public Label checkLabel;
    public Button mailButton;
    public Button webButton;
    public Label addressLabel;
    public Label Label1;
    public Label Label2;
    // End of Component Declaration

    public Applet myAppl;

    // Constructor
    public InfoFrame(Applet appl, String Title1, String Title2)
    {
        myAppl=appl;
        // Frame Initialization
        setForeground(Color.black);
        setBackground(Color.orange);
        setFont(new Font("Helvetica",Font.BOLD,12));
        setTitle(Title1+" "+Title2);
        setLayout(null);
        setResizable(false);
        // End of Frame Initialization

        // Component Initialization
        titleLabel = new Label(Title1,Label.LEFT);
        titleLabel.setFont(new Font("Dialog",Font.BOLD + Font.ITALIC,24));
        copyrightLabel = new Label("Copyright (c) 1997 by Bill Bereza.",Label.LEFT);
        copyrightLabel.setFont(new Font("Dialog",Font.BOLD,12));
        gnuNotice = new TextArea("This software comes with ABSOLUTELY NO WARRANTY.\nThis is free software, and you are welcome to redistribute it\nunder certain conditions.\n");
        gnuNotice.setForeground(Color.black);
        gnuNotice.setBackground(Color.white);
        gnuNotice.setFont(new Font("Dialog",Font.PLAIN,9));
        rightsLabel = new Label("All Rights Reserved.",Label.LEFT);
        rightsLabel.setFont(new Font("Dialog",Font.BOLD,12));
        checkLabel = new Label(Title2,Label.LEFT);
        checkLabel.setFont(new Font("Dialog",Font.BOLD + Font.ITALIC,24));
        //myImage = Toolkit.getDefaultToolkit().getImage("images/bill.gif");
        mailButton = new Button("mailto:bereza@pobox.com");
        mailButton.setFont(new Font("Dialog",Font.PLAIN,10));
        webButton = new Button("http://www.pobox.com/~bereza");
        webButton.setFont(new Font("Dialog",Font.PLAIN,10));
        addressLabel = new Label("Address:",Label.LEFT);
        addressLabel.setFont(new Font("Dialog",Font.PLAIN,10));
        Label1 = new Label("9526 Judson Rd.",Label.LEFT);
        Label1.setFont(new Font("Dialog",Font.PLAIN,10));
        Label2 = new Label("Ravenna, MI 49451",Label.LEFT);
        Label2.setFont(new Font("Dialog",Font.PLAIN,10));
        // End of Component Initialization

        myImage = myAppl.getImage(myAppl.getCodeBase(), "images/bill.gif");
        gnuNotice.setEditable(false);

        // Add()s
        add(Label2);
        add(Label1);
        add(addressLabel);
        add(webButton);
        add(mailButton);
        add(checkLabel);
        add(rightsLabel);
        add(gnuNotice);
        add(copyrightLabel);
        add(titleLabel);
        // End of Add()s

        fForm_Create = true;
    }

    public void InitialPositionSet()
    {
        // InitialPositionSet()
        reshape(131,104,400,340);
        titleLabel.reshape(30,78+MenuBarHeight,100,36);
        copyrightLabel.reshape(30,158+MenuBarHeight,256,21);
        gnuNotice.reshape(23,209+MenuBarHeight,354,90);
        rightsLabel.reshape(30,179+MenuBarHeight,173,19);
        checkLabel.reshape(30,114+MenuBarHeight,168,35);
        mailButton.reshape(164,27+MenuBarHeight,186,19);
        webButton.reshape(164,51+MenuBarHeight,186,20);
        addressLabel.reshape(164,82+MenuBarHeight,51,16);
        Label1.reshape(229,81+MenuBarHeight,94,16);
        Label2.reshape(229,101+MenuBarHeight,120,19);
        // End of InitialPositionSet()

        fForm_Create = false;
    }

    public boolean handleEvent(Event evt)
    {
        // handleEvent()
        if (evt.id == Event.WINDOW_DESTROY && evt.target == this) InfoFrame_WindowDestroy(evt.target);
        else if (evt.id == Event.ACTION_EVENT && evt.target == mailButton) mailButton_Action(evt.target);
        else if (evt.id == Event.ACTION_EVENT && evt.target == webButton) webButton_Action(evt.target);
        // End of handleEvent()

        return super.handleEvent(evt);
    }

    public void paint(Graphics g)
    {
        // paint()
        g.drawImage(myImage, 356,7,35,46, this);
        // End of paint()
        if (fForm_Create) InitialPositionSet();
    }

    // Event Handling Routines
    public void InfoFrame_WindowDestroy(Object target)
    {
        hide();
    }

    public void mailButton_Action(Object target)
    {
        Button me=(Button)target;
        URL mailURL;
        AppletContext apc;

        try {
            mailURL=new URL(me.getLabel());
        } catch(MalformedURLException e) {
            System.out.println(e.getMessage());
            return;
        }
        
        apc=myAppl.getAppletContext();
        apc.showDocument(mailURL, me.getLabel());
    }

    public void webButton_Action(Object target)
    {
        Button me=(Button)target;
        URL webURL;
        AppletContext apc;
        
        try {
            webURL=new URL(me.getLabel());
        } catch(MalformedURLException e) {
            System.out.println(e.getMessage());
            return;
        }

        apc=myAppl.getAppletContext();
        apc.showDocument(webURL, me.getLabel());
    }

    // End of Event Handling Routines

} // End of Class InfoFrame

