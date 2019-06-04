package org.jbox2d.testbed.tests;

import com.mathworks.engine.MatlabEngine;
import com.mathworks.matlab.types.Struct;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.framework.TestbedTest;
import org.jbox2d.testbed.framework.j2d.TestbedMain;
import org.jbox2d.testbed.json.Composition;
import org.jbox2d.testbed.json.jsonReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.jbox2d.testbed.framework.j2d.TestbedMain.getJsonPath;

// TODO: C# sends -y because its positive y axis is pointed downwards, and here its upwards.
// Maybe should send +y and center around different point.

public class mainTest extends TestbedTest {

    private static float BOARD_WIDTH = 50;
    private static float BOARD_HEIGHT;


    void RunJBox(World world, String imagePath, String jsonPath)
    {
        Composition comp = jsonReader.fromJSON(jsonPath);

        int height = 300;
        int width = 300;
        try {
            File f = new File(imagePath);
            BufferedImage image = ImageIO.read(f);
//            height = image.getHeight();
//            width = image.getWidth();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        // Normalise scale - to order of BOARD
        BOARD_HEIGHT = BOARD_WIDTH * height / width;
        float scaleFactor = BOARD_WIDTH / width;

        jsonReader.CompositionDecoder(comp, world, scaleFactor);
        getCamera().setCamera(new Vec2(BOARD_WIDTH/2, -BOARD_HEIGHT/2), 10);
    }

    @Override
    public void initTest() {

        World world = getWorld();

        String imagePath = TestbedMain.getImagePath();

        String jsonPath = getJsonPath();
//        String jasonPath = "C:\\Users\\Danielle\\Downloads\\jbox2d\\Images\\outputExp.json";

        RunJBox(world, imagePath, jsonPath);

//        DrawFrame(world);

    }

    @Override
    public String getTestName() {
        return "MainTest";
    }

    public void DrawFrame(World world)
    {
        // Draw Image Frame

        Vec2[] rectVec = {new Vec2(BOARD_WIDTH/2,-BOARD_HEIGHT/2),
                          new Vec2(BOARD_WIDTH/2,BOARD_HEIGHT/2),
                          new Vec2(-BOARD_WIDTH/2,BOARD_HEIGHT/2),
                          new Vec2(-BOARD_WIDTH/2,-BOARD_HEIGHT/2)};
        new Rect(world, rectVec,false);
    }
}

