package org.jbox2d.testbed.tests;

import com.mathworks.engine.MatlabEngine;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.framework.TestbedTest;
import org.jbox2d.testbed.json.Composition;
import org.jbox2d.testbed.json.jsonReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

// TODO: C# sends -y because its positive y axis is pointed downwards, and here its upwards.
// Maybe should send +y and center around different point.

public class mainTest extends TestbedTest {

    public static int BOARD_WIDTH = 50;
    public static int BOARD_HEIGHT;

    private static void RunMatlab() {
        try {
            MatlabEngine eng = MatlabEngine.startMatlab();

            // Change directory and evaluate your function
            eng.eval("cd 'C:\\Users\\Danielle\\Desktop'");
            String imagePath = "C:\\Users\\Danielle\\Technion\\Yair Moshe - project - Deep Learning for Classroom Augmented Reality Android App\\For_SIPL_Backup\\data\\Complex Images\\Real Images\\Real_301.jpg";
            Object[] results = eng.feval(3, "detectImage", imagePath, 0.95);

            double[][][] picture = (double[][][]) results[0];
            double[][] bboxes = (double[][]) results[1];
            String[] labels = (String[]) results[2];

            eng.eval("cd 'C:\\Users\\Danielle\\Technion\\Yair Moshe - project - Classroom Augmented Reality with Your Smartphone\\Object Mapper'");
            Object mappingResult = eng.feval(1, "mapObjects", picture, bboxes, labels);


            eng.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    void RunJBox()
    {
        String fileName = "Real_301";
        World world = getWorld();
        //Composition comp = jsonReader.fromJSON("Synthetic Images\\" + fileName + ".json");
        Composition comp = jsonReader.fromJSON("Synthetic Images\\" + "output_example.json");

        int height = 1;
        int width = 1;
        try {
            String path = "C:\\Users\\Danielle\\Downloads\\jbox2d\\Synthetic Images\\" + fileName + ".jpg";
            File f = new File(path);
            BufferedImage image = ImageIO.read(f);
            height = image.getHeight();
            width = image.getWidth();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        // Normalise scale - to order of BOARD
        BOARD_HEIGHT = BOARD_WIDTH * height / width;
        double proportionX = (double) width / BOARD_WIDTH;
        double proportionY = (double) height / BOARD_HEIGHT;

        jsonReader.CompositionDecoder(comp, world, proportionX, proportionY);
    }

    @Override
    public void initTest() {

//        RunMatlab();

        RunJBox();

        // Draw Image Frame
//        new Rect(world,
//                new Vec2(BOARD_WIDTH/2,-BOARD_HEIGHT/2),
//                new Vec2(BOARD_WIDTH/2,BOARD_HEIGHT/2), false);

        getCamera().setCamera(new Vec2(BOARD_WIDTH / 2, -BOARD_HEIGHT / 2), 10);

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

