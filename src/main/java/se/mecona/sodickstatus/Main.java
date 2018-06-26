/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.mecona.sodickstatus;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

/**
 *
 * @author Mcx8
 */
public class Main {

    private static final String QUOTE_CHAR = "\"";
    private static final String BASE_PATH = "D:\\Mats Dokument\\Dropbox\\Screenshots\\";
    private static final String SCREEN_SHOT_CMD = "\"C:\\Program Files\\Oracle\\VirtualBox\\VBoxManage.exe\" controlvm \"IE8 - Win7\" screenshotpng "
            + QUOTE_CHAR + BASE_PATH + "Test.png" + QUOTE_CHAR;
    private static final String PATH_TO_SCREEN_SHOT = BASE_PATH + "Test.png";
    private static final String PATH_TO_PR_IMAGE = BASE_PATH + "pr.png";
    private static final String PATH_TO_STATUS_IMAGE = BASE_PATH + "status.png";

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = 
                Executors.newScheduledThreadPool(1);
        
        scheduler.scheduleAtFixedRate(() -> { doScreenshot(); },
                0,
                60,
                TimeUnit.SECONDS
        );
        
    }

    private static void doScreenshot() {
        System.out.println("Starting");
        System.out.println("Taking screenshot");
        try {
            Process process = Runtime.getRuntime().exec(SCREEN_SHOT_CMD);
            int processOutput = process.waitFor();
            System.out.println("Return code " + processOutput);
            System.out.println("Reading screenshot");
            BufferedImage image = ImageIO.read(new File(PATH_TO_SCREEN_SHOT));

            BufferedImage prImage = image.getSubimage(420, 222, 40, 20);
            File prFile = new File(PATH_TO_PR_IMAGE);
            System.out.println("Writing procent ready image");
            ImageIO.write(prImage, "png", prFile);

            image = ImageIO.read(new File(PATH_TO_SCREEN_SHOT));
            BufferedImage statusImage = image.getSubimage(187, 378, 295, 22);
            File statusFile = new File(PATH_TO_STATUS_IMAGE);
            System.out.println("Writing status image");
            ImageIO.write(statusImage, "png", statusFile);

        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            System.out.println("Exception type " + ex.toString());
        }
    }

}
