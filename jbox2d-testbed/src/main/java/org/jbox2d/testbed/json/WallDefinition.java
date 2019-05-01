package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.Ball;
import org.jbox2d.testbed.tests.Rect;
import org.jbox2d.testbed.tests.StaticLine;

import static java.lang.Math.cos;
import static java.lang.StrictMath.acos;
import static java.lang.StrictMath.sin;

public class WallDefinition {

    public String A;
    public String B;
    public float Width;

    public void Draw(World world, double proportionX, double proportionY)
    {
        Vec2 a = jsonReader.proportionate(jsonReader.toVec2(A), proportionX, proportionY);
        Vec2 b = jsonReader.proportionate(jsonReader.toVec2(B), proportionX, proportionY);

        new StaticLine(world, false, 0, a, b);

//        float Length = b.sub(a).length();
//        double angle = Math.acos(Vec2.dot(b.sub(a), new Vec2(1,0))/Length);
//
//        double hypotenuse = Math.pow( Math.pow(a.x-b.x, 2) + Math.pow(a.y-b.y, 2),0.5);
//        double cx = a.x + Width/2 * Math.sin(angle)
//                + 0.5*Math.cos(angle)*hypotenuse;
//        double cy = a.y - Width/2 * Math.cos(angle)
//                + 0.5*Math.sin(angle)*hypotenuse;
//
//        Vec2 center = jsonReader.proportionate(new Vec2((float)cx, (float)cy), proportionX, proportionY);
//        float width = newWidth(Width, angle, proportionX, proportionY);
//        float length = newLength(Length, angle, proportionX, proportionY);
//
//        new Rect(world, center, new Vec2(width/2, length/2), false, -(float)(Math.PI/2-angle));
    }

    private float newWidth(float Width, double angle, double proportionX, double proportionY)
    {
        float x = (float)(Width*Math.sin(angle));
        float y = (float)(Width*Math.cos(angle));

        Vec2 newWidthVec = jsonReader.proportionate(new Vec2(x,y), proportionX, proportionY);

        return newWidthVec.length();
    }

    private float newLength(float Length, double angle, double proportionX, double proportionY)
    {
        float x = (float)(Length*Math.cos(angle));
        float y = (float)(Length*Math.sin(angle));

        Vec2 newLengthVec = jsonReader.proportionate(new Vec2(x,y), proportionX, proportionY);

        return newLengthVec.length();
    }
}
