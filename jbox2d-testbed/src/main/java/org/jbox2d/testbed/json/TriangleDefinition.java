package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.Ball;
import org.jbox2d.testbed.tests.Poly;

public class TriangleDefinition {

    public String A;
    public String B;
    public String C;
    public boolean IsStatic;

    public Body Draw(World world, float scale)
    {
        Vec2 a =  jsonReader.toVec2(A).mul(scale);
        Vec2 b =  jsonReader.toVec2(B).mul(scale);
        Vec2 c =  jsonReader.toVec2(C).mul(scale);

        // TODO: set dynamic to !IsStatic
        Poly triangle = new Poly(world, false, a, b, c);
        return triangle.body;
    }

}
