package org.jbox2d.testbed.json;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.cartTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class jsonReader {

    public static Composition fromJSON() {
        Gson gson = new Gson();

        FileReader fr = null;
        try {
            fr = new FileReader("Synthetic_1.json");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(cartTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        JsonReader reader = new JsonReader(fr);

        Composition comp = gson.fromJson(reader, Composition.class);

        return comp;
    }

    public static void CompositionDecoder(Composition comp, World world)
    {
        for (CartDefinition def : comp.Carts){
            def.Draw(world);
        }

        /*for (BallDefinition def : comp.Balls){
            def.Draw();
        }

        for (WallDefinition def : comp.Walls){
            def.Draw();
        }

        for (BlockDefinition def : comp.Blocks){
            def.Draw();
        }

        for (LineDefinition def : comp.Lines){
            def.Draw();
        }

        for (SpringDefinition def : comp.Springs){
            def.Draw();
        }

        for (TriangleDefinition def : comp.Triangles){
            def.Draw();
        }*/

    }



    public static Vec2 toVec2(String point)
    {
        String[] coordinates = point.split("\\,\\ ");
        return new Vec2(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
    }
    
}
