package org.jbox2d.testbed.tests;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.framework.TestbedTest;
import org.jbox2d.testbed.json.Composition;
import org.jbox2d.testbed.json.jsonReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class mainTest extends TestbedTest {

    public static final int BOARD_HEIGHT = 900;
    public static final int BOARD_WIDTH = 900;

    @Override
    public void initTest(){

        String fileName = "Synthetic_95";
        World world = getWorld();
        Composition comp = jsonReader.fromJSON("Synthetic Images\\" + fileName + ".json");

        int height = 1;
        int width = 1;
        try {
            File f = new File("C:\\Users\\Danielle\\Downloads\\jbox2d\\Synthetic Images\\" + fileName + ".jpg");
            BufferedImage image = ImageIO.read(f);
            height = image.getHeight();
            width = image.getWidth();
        } catch (IOException ioe){ioe.printStackTrace();}

        double proportionX = (double)width/BOARD_WIDTH;
        double proportionY = (double)height/BOARD_HEIGHT;

        jsonReader.CompositionDecoder(comp, world, proportionX, proportionY);
        new Rect(world, new Vec2(BOARD_WIDTH/2,-BOARD_HEIGHT/2), new Vec2(BOARD_WIDTH/2,BOARD_HEIGHT/2), false);
        // TODO: normalise SCALE
        getCamera().setCamera(new Vec2(BOARD_WIDTH/2,-BOARD_HEIGHT/2), 1);

    }

    @Override
    public String getTestName() {
        return "MainTest";
    }
}

