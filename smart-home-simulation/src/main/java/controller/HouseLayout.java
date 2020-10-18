package controller;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.ContextSimulation;

public class HouseLayout extends JPanel{  
	private BufferedImage closedWindow;
	private BufferedImage openedWindow;
	private BufferedImage closedDoor;
	private BufferedImage openedDoor;
	private BufferedImage lightOff;
	private BufferedImage lightOn;
	private BufferedImage userImage;
	private BufferedImage blocked;
	private ContextSimulation context;
	private int index=0;
	
	public void drawRooms(Graphics g) {
			try 
			{
				int offSet= 0;
				userImage = ImageIO.read(getClass().getResource("/resources/user.jpg"));
				Users users = new Users();
				ArrayList <Users> usersArray = new ArrayList(users.getUserList());
				ReadingJsonFile rjFile = new ReadingJsonFile("myJSON.json");
		    	for (int i=0; i<rjFile.getRoomArray().size(); i++) {
		    		if (i==0) {
		    			g.drawRect(0, 0, 400, 100);
		    			g.drawString("Outside", 25, 20);
		    			if (usersArray.get(index).getLocation().equals("Outside"))
		    		        g.drawImage(userImage,  200, 20, 25, 25, null);
		    			g.translate(0, 100);
		    				
		    		}
		    		if (i<4) {
		    			drawRoom(g, rjFile.getRoomArray().get(i).toString());
		    			if (rjFile.getRoomArray().get(i).toString().equals(usersArray.get(index).getLocation()))
		    		        g.drawImage(userImage,  40, 70, 25, 25, null);
		    			//draw rooms on the left
		    			g.translate(0, 100);
		    		}
		    		if (i==4)
		    		{
		    			g.translate(150, -400);
		    			g.drawRect(0, 0, 100, 400);
		    			g.drawString("Hallway", 40, 200);
		    			if (("Hallway").equals(usersArray.get(index).getLocation()))
		    		        g.drawImage(userImage,  40, 100, 25, 25, null);
		    			g.translate(100, 0);	
		    		}
		    		if (i>=4)
		    		{
		    			drawRoom(g, rjFile.getRoomArray().get(i).toString());
		    			if (rjFile.getRoomArray().get(i).toString().equals(usersArray.get(index).getLocation()))
		    		        g.drawImage(userImage,  40, 70, 25, 25, null);
		    			g.translate(0, 100);
		    			offSet = offSet - 100;
		    		}
		    	}
		    	g.translate(-250, offSet);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		public void drawWindows(Graphics g) {
			Windows windows = new Windows();
			try 
			{
				int offSet= 0;
				closedWindow = ImageIO.read(getClass().getResource("/resources/closedWindow.png"));
				openedWindow = ImageIO.read(getClass().getResourceAsStream("/resources/openedWindow.png"));
				blocked = ImageIO.read(getClass().getResourceAsStream("/resources/blocked.png"));
				ReadingJsonFile rjFile = new ReadingJsonFile("myJSON.json");
		    	for (int i=0; i<rjFile.getRoomArray().size(); i++) 
		    	{
		    		if(i<4) 
		    		{
		    			g.drawImage(closedWindow, 0, 0, 25, 25, null);
		    			if (windows.getWindowList().get(i).isBlocked())
		    				g.drawImage(blocked, 25, 0, 25, 25, null);
		    			g.translate(0, 100);
		    		}	
		    		if(i==4)
		    			g.translate(250, -400);
		    		if(i>=4)
		    		{
		    			g.drawImage(openedWindow, 125, 0, 25, 25, null);
		    			if (windows.getWindowList().get(i).isBlocked())
		    				g.drawImage(blocked, 25, 0, 25, 25, null);
		    			g.translate(0, 100);
		    			offSet= offSet - 100;
		    		}
		    	}
		    	g.translate(-250, offSet);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void drawDoors(Graphics g) {
			try 
			{
				int offSet= 0;
				openedDoor = ImageIO.read(getClass().getResourceAsStream("/resources/openedDoor.png"));
				closedDoor = ImageIO.read(getClass().getResourceAsStream("/resources/closedDoor.png"));
				ReadingJsonFile rjFile = new ReadingJsonFile("myJSON.json");
		    	for (int i=0; i<rjFile.getRoomArray().size(); i++) 
		    	{
		    		if(i<4) 
		    		{
		    			g.drawImage(openedDoor, 125, 0, 25, 25, null);
		    			g.translate(0, 100);
		    		}	
		    		if(i==4)
		    			g.translate(250, -400);
		    		if(i>=4)
		    		{
		    			g.drawImage(closedDoor, 0, 0, 25, 25, null);
		    			g.translate(0, 100);
		    			offSet = offSet - 100;
		    		}
		    	}
		    	g.translate(-250, offSet);
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		public void drawLights(Graphics g)
		{
			try 
			{
				int offSet= 0;
				lightOff = ImageIO.read(getClass().getResourceAsStream("/resources/lightOff.png"));
				lightOn = ImageIO.read(getClass().getResourceAsStream("/resources/lightOn.png"));
				ReadingJsonFile rjFile = new ReadingJsonFile("myJSON.json");
		    	for (int i=0; i<rjFile.getRoomArray().size(); i++) 
		    	{
		    		if(i<4) 
		    		{
		    			g.drawImage(lightOff, 0, 75, 25, 25, null);
		    			g.translate(0, 100);
		    		}	
		    		if(i==4)
		    			g.translate(250, -400);
		    		if(i>=4)
		    		{
		    			g.drawImage(lightOn, 0, 75, 25, 25, null);
		    			g.translate(0, 100);
		    			offSet = offSet - 100;
		    		}
		    	}
		    	g.translate(-250, offSet);
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		public void drawRoom(Graphics g, String s) {
			g.drawRect(0, 0, 150, 100);
	        g.drawString(s, 40, 50);
		}
		
	    public void paint(Graphics g)
	    {
			drawRooms(g);	
			drawWindows(g);
			drawDoors(g);
			drawLights(g);
	    }
	    
	    public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}