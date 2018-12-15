package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.Rect;

import static java.lang.Math.cos;
import static java.lang.StrictMath.acos;
import static java.lang.StrictMath.sin;

public class WallDefinition {

    public String A;
    public String B;
    public float Width;

    public void Draw(World world)
    {
        Vec2 a = jsonReader.toVec2(A);
        Vec2 b = jsonReader.toVec2(B);

        double angle = acos(Vec2.dot(a, b) / (a.length() * b.length()));

        float height = b.sub(a).length();

        Vec2 widthVec = (new Vec2((float)cos(angle), -(float)sin(angle))).mul(Width);

        Vec2 c = b.add(widthVec);
        Vec2 dim = new Vec2(height/2, Width/2);

        Vec2 Center = new Vec2((a.x+b.x)/2, (a.y+b.y)/2);

        new Rect(world, Center, dim, angle);
    }
}
