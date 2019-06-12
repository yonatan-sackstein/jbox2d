package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.Poly;
import org.jbox2d.testbed.tests.Rect;

public class BlockDefinition {

    public String A;
    public String B;
    public String C;
    public String D;
    public boolean IsStatic;

    public Body Draw(World world, float scale)
    {
        Vec2 vert1 = jsonReader.toVec2(A).mul(scale);
        Vec2 vert2 = jsonReader.toVec2(B).mul(scale);
        Vec2 vert3 = jsonReader.toVec2(C).mul(scale);
        Vec2 vert4 = jsonReader.toVec2(D).mul(scale);

        Poly rect = new Poly(world, !IsStatic, vert1, vert2, vert3, vert4);
        return rect.body;
    }
}
