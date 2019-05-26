package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.Poly;
import org.jbox2d.testbed.tests.Rect;

public class BlockDefinition {

    public String A;
    public String B;
    public String C;
    public String D;
    public boolean IsStatic;

    public void Draw(World world, double proportionX, double proportionY)
    {
        Vec2 vert1 = jsonReader.proportionate(jsonReader.toVec2(A), proportionX, proportionY);
        Vec2 vert2 = jsonReader.proportionate(jsonReader.toVec2(B), proportionX, proportionY);
        Vec2 vert3 = jsonReader.proportionate(jsonReader.toVec2(C), proportionX, proportionY);
        Vec2 vert4 = jsonReader.proportionate(jsonReader.toVec2(D), proportionX, proportionY);

        new Poly(world, !IsStatic, vert1, vert2, vert3, vert4);
    }
}
