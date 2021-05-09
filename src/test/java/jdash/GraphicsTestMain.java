package jdash;

import jdash.exception.SpriteLoadException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicsTestMain {

    public static void main(String[] args) throws SpriteLoadException {
        /*// Build the client
        AnonymousGDClient client = GDClientBuilder.create().buildAnonymous();
        // Fetch RobTop's profile
        GDUser user = client.getUserByAccountId(71).block();
        // Instanciate the SpriteFactory
        SpriteFactory sf = SpriteFactory.create();
        // Read the user's icon set
        GDUserIconSet iconSet = new GDUserIconSet(user, sf);
        // For each existing icon type (cube, ship, UFO, etc), generate the image
        BufferedImage mainIcon = iconSet.generateIcon(user.getMainIconType());
        BufferedImage cube = iconSet.generateIcon(IconType.CUBE);
        BufferedImage ship = iconSet.generateIcon(IconType.SHIP);
        BufferedImage ball = iconSet.generateIcon(IconType.BALL);
        BufferedImage ufo = iconSet.generateIcon(IconType.UFO);
        BufferedImage wave = iconSet.generateIcon(IconType.WAVE);
        BufferedImage robot = iconSet.generateIcon(IconType.ROBOT);
        BufferedImage spider = iconSet.generateIcon(IconType.SPIDER);
        // Save the images
        savePNG(mainIcon, "RobTop-Main");
        savePNG(cube, "RobTop-Cube");
        savePNG(ship, "RobTop-Ship");
        savePNG(ball, "RobTop-Ball");
        savePNG(ufo, "RobTop-Ufo");
        savePNG(wave, "RobTop-Wave");
        savePNG(robot, "RobTop-Robot");
        savePNG(spider, "RobTop-Spider");*/
    }

    /**
     * Utility method to save an image into a PNG format in the system's temporary directory.
     */
    private static void savePNG(BufferedImage img, String name) {
        try {
            String path = System.getProperty("java.io.tmpdir") + File.separator + name + ".png";
            ImageIO.write(img, "png", new File(path));
            System.out.println("Saved " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
