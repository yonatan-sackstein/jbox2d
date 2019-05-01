package org.jbox2d.testbed.tests;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.framework.TestbedTest;
import org.jbox2d.testbed.json.CartDefinition;
import org.jbox2d.testbed.json.Composition;
import org.jbox2d.testbed.json.jsonReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// TODO: C# sends -y because its positive y axis is pointed downwards, and here its upwards.
// Maybe should send +y and center around different point.

public class mainTest extends TestbedTest {

    public static int BOARD_WIDTH = 50;
    public static int BOARD_HEIGHT;

    @Override
    public void initTest(){

        String fileName = "Synthetic_109";
        World world = getWorld();
        Composition comp = jsonReader.fromJSON("Synthetic Images\\" + fileName + ".json");

        int height = 1;
        int width = 1;
        try {
            String path = "C:\\Users\\Danielle\\Downloads\\jbox2d\\Synthetic Images\\" + fileName + ".jpg";
            File f = new File(path);
            BufferedImage image = ImageIO.read(f);
            height = image.getHeight();
            width = image.getWidth();
        } catch (IOException ioe){ioe.printStackTrace();}

        // Normalise scale - to order of BOARD
        BOARD_HEIGHT = BOARD_WIDTH * height / width;
        double proportionX = (double)width/BOARD_WIDTH;
        double proportionY = (double)height/BOARD_HEIGHT;

        jsonReader.CompositionDecoder(comp, world, proportionX, proportionY);

        // Draw Image Frame
//        new Rect(world,
//                new Vec2(BOARD_WIDTH/2,-BOARD_HEIGHT/2),
//                new Vec2(BOARD_WIDTH/2,BOARD_HEIGHT/2), false);

        getCamera().setCamera(new Vec2(BOARD_WIDTH/2,-BOARD_HEIGHT/2), 10);

        // for debug
//        CartDefinition cd = new CartDefinition();
//        cd.BodyCenter = "50, -50";
//        cd.BodyWidth = 15;
//        cd.BodyHeight = 10;
//        cd.Angle = (float)0.81;
//
//        cd.Draw(world, 1 ,1);


    }

    @Override
    public String getTestName() {
        return "MainTest";
    }
}

