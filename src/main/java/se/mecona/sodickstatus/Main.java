/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.mecona.sodickstatus;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Mcx8
 */
public class Main {
    
    private static final String SCREEN_SHOT_CMD = "\"C:\\Program Files\\Oracle\\VirtualBox\\VBoxManage.exe\" controlvm \"IE8 - Win7\" screenshotpng Test.png";
    private static final String PATH_TO_SCREEN_SHOT = "C:\\Users\\Mcx8\\Documents\\NetBeansProjects\\SodickStatus\\Test.png";
    private static final String PATH_TO_PR_IMAGE = "C:\\Users\\Mcx8\\Documents\\NetBeansProjects\\SodickStatus\\pr.png";
    private static final String PATH_TO_STATUS_IMAGE = "C:\\Users\\Mcx8\\Documents\\NetBeansProjects\\SodickStatus\\status.png";
    
    public static void main(String[] args) {
        System.out.println("Starting");
        System.out.println("Taking screenshot");
        try {
            Process process = Runtime.getRuntime().exec(SCREEN_SHOT_CMD);
            BufferedImage image = ImageIO.read(new File(PATH_TO_SCREEN_SHOT));

            BufferedImage prImage = image.getSubimage(420, 222, 40, 20);
            File prFile = new File(PATH_TO_PR_IMAGE);
            ImageIO.write(prImage, "png", prFile);

            image = ImageIO.read(new File(PATH_TO_SCREEN_SHOT));
            BufferedImage statusImage = image.getSubimage(187, 378, 295, 22);
            File statusFile = new File(PATH_TO_STATUS_IMAGE);
            ImageIO.write(statusImage, "png", statusFile);

        } catch (IOException ex) {
            System.out.println("Exception " + ex.getMessage());
        }
    }

}
