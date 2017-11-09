import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Iterator;

package grocerystore;

/**
 * Data for user 
 * 
 * @author William McCarty
 * @version 1.0
 */
public class UserModel {
	////////////////////////////////////
	//////// 	Fields
	////////////////////////////////////
	private String name;
	private String displayName;
	private int userID;
	private Date startDate; //java.util.*
	private String password;
	private boolean isManager;
	private BufferedImage profilePicture;


	////////////////////////////////////
	//////	 Methods
	////////////////////////////////////

	public UserModel() {}
	
	public UserModel(String nameIn, String displayNameIn, int userIDIn, Date startDateIn, String passwordIn, boolean isManagerIn) {
		this.name = nameIn;
		this.displayName = displayNameIn;
		this.userID = userIDIn;
		this.startDate = startDateIn;
		this.password = passwordIn;
		this.isManager = isManagerIn;
	}
	////////////////////////////////////
	////// 	Getters 
	///////////////////////////////////
	public String 	getName()		 { return this.name;}
	public int 		getUserID()		 { return this.userID;}
	public Date 	getStartDate()	 { return this.startdate;}
	public boolean 	getIsManager()	 { return this.isManager;}
	public String 	getPassword() 	 { return this.password;}
	public String 	getDisplayName() { return this.displayName;}
	public BufferedImage getProfilePicture() { return this.profilePic;}

	////////////////////////////////////
	////// 	Setters
	///////////////////////////////////
	public void setUserID(int uid) 			{ this.userID = uid;}
	public void setName(String uname) 		{ this.name = uname;}
	public void setIsManager(boolean x) 	{ this.isManager = x;}
	public void setStartDate(Date dateIn) 	{ this.startDate = dateIn;}
	public void setPassword(String pw)		{ this.password = pw;}
	public void setDisplayName(String nm) 	{ this.displayName = nm;}

	/**
	 * @param 	path to image - String
	 * @throws 	FileNotFoundException if image not found
	 */
	public void setProfilePicture(String imagePath) throws FileNotFoundException {
		BufferedImage img = null;
		try {
    		img = ImageIO.read(new File(imagePath));
    		this.profilePicture = img;
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		}
	}


}