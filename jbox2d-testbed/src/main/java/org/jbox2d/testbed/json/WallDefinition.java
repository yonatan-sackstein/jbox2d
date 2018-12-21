package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.Ball;
import org.jbox2d.testbed.tests.Rect;

import static java.lang.Math.cos;
import static java.lang.StrictMath.acos;
import static java.lang.StrictMath.sin;

public class WallDefinition {

    public String A;
    public String B;
    public String C;
    public String D;

    public void Draw(World world, double proportionX, double proportionY)
    {
        Vec2 a = jsonReader.proportionate( jsonReader.toVec2(A), proportionX, proportionY);;
        Vec2 b = jsonReader.proportionate( jsonReader.toVec2(B), proportionX, proportionY);;
        Vec2 c = jsonReader.proportionate( jsonReader.toVec2(C), proportionX, proportionY);;
        Vec2 d = jsonReader.proportionate( jsonReader.toVec2(D), proportionX, proportionY);;

        Vec2[] vertices = {a,b,c,d};

        new Rect(world, vertices);
    }
}
