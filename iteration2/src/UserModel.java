import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Iterator;


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
    private int isManager; // 1 -> Manager, 0 -> Cashier
    //private BufferedImage profilePicture;
    private String profilePicture;
    private int isSignedIn;


    ////////////////////////////////////
    //////	 Methods
    ////////////////////////////////////

    public UserModel() {
        name = "";
    }

    public UserModel(String nameIn, String displayNameIn, int userIDIn, Date startDateIn, String passwordIn, int isManagerIn, int isSignedIn) {
        this.name = nameIn;
        this.displayName = displayNameIn;
        this.userID = userIDIn;
        this.startDate = startDateIn;
        this.password = passwordIn;
        this.isManager = isManagerIn;
        this.isSignedIn = isSignedIn;
    }
    ////////////////////////////////////
    ////// 	Getters
    ///////////////////////////////////
    public String 	getName()		 { return this.name;}
    public int 		getUserID()		 { return this.userID;}
    public Date 	getStartDate()	 { return this.startDate;}
    public int 	getIsManager()	 { return this.isManager;}
    public String 	getPassword() 	 { return this.password;}
    public String 	getDisplayName() { return this.displayName;}
    //public BufferedImage getProfilePicture() { return this.profilePicture;}
    public String getProfilePicture() { return this.profilePicture;}

    public int getIsSignedIn() {
        return isSignedIn;
    }

    ////////////////////////////////////
    ////// 	Setters
    ///////////////////////////////////
    public void setUserID(int uid) 			{ this.userID = uid;}
    public void setName(String uname) 		{ this.name = uname;}
    public void setIsManager(int x) 	{ this.isManager = x;}
    public void setStartDate(Date dateIn) 	{ this.startDate = dateIn;}
    public void setPassword(String pw)		{ this.password = pw;}
    public void setDisplayName(String nm) 	{ this.displayName = nm;}

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setIsSignedIn(int isSignedIn) {
        this.isSignedIn = isSignedIn;
    }

    public void setUserType(String userType) {
        if (userType.toLowerCase().trim().compareTo("manager") == 0) {
            this.isManager = 1; // 1 -> Manager (or isManager = true)
        } else { // otherwise, 0 -> Cashier
            this.isManager = 0;
        }
    }


    public String getJobTitle() {
        String jobTitle;
        if (this.isManager == 0) {
            jobTitle = "Cashier";
        } else {
            jobTitle = "Manager";
        }
        return jobTitle;
    }
    /**
     *
     * @param 	path to image - String
     * @throws 	FileNotFoundException if image not found
     *
    public void setProfilePicture(String imagePath) throws FileNotFoundException {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(imagePath));
            this.profilePicture = img;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
    */

}