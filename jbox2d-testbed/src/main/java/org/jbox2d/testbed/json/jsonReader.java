package org.jbox2d.testbed.json;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.cartTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class jsonReader {

    public static Composition fromJSON(String fileName) {
        Gson gson = new Gson();

        FileReader fr = null;
        try {
            fr = new FileReader(fileName);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(cartTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        JsonReader reader = new JsonReader(fr);

        Composition comp = gson.fromJson(reader, Composition.class);

        return comp;
    }

    public static void CompositionDecoder(Composition comp, World world, float scale)
    {
        Body[] CartsBodyArray = new Body[comp.Carts.size()];
        for (int ii=0; ii<comp.Carts.size(); ii++) {
            CartsBodyArray[ii] = comp.Carts.get(ii).Draw(world, scale);
        }

        Body[] BallsBodyArray = new Body[comp.Balls.size()];
        for (int ii=0; ii<comp.Balls.size(); ii++) {
            BallsBodyArray[ii] = comp.Balls.get(ii).Draw(world, scale);
        }

        Body[] WallsBodyArray = new Body[comp.Walls.size()];
        for (int ii=0; ii<comp.Walls.size(); ii++) {
            WallsBodyArray[ii] = comp.Walls.get(ii).Draw(world, scale);
        }

        Body[] BlocksBodyArray = new Body[comp.Blocks.size()];
        for (int ii=0; ii<comp.Blocks.size(); ii++) {
            BlocksBodyArray[ii] = comp.Blocks.get(ii).Draw(world, scale);
        }

        Body[] TrianglesBodyArray = new Body[comp.Triangles.size()];
        for (int ii=0; ii<comp.Triangles.size(); ii++) {
            TrianglesBodyArray[ii] = comp.Triangles.get(ii).Draw(world, scale);
        }

        for (LineSpringDefinition def : comp.Lines){
            def.Draw(world, scale, 0, 0, CartsBodyArray, BallsBodyArray,
                    WallsBodyArray, BlocksBodyArray, TrianglesBodyArray);
        }

        for (LineSpringDefinition def : comp.Springs){
            def.Draw(world, scale, 0.5f, 0, CartsBodyArray, BallsBodyArray,
                    WallsBodyArray, BlocksBodyArray, TrianglesBodyArray);
        }
    }

    public static Vec2 toVec2(String point)
    {
        String[] coordinates = point.split("\\,\\ ");
        return new Vec2(Integer.parseInt(coordinates[0]), -Integer.parseInt(coordinates[1]));
    }

}
