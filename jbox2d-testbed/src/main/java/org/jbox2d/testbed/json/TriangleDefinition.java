package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.Poly;

public class TriangleDefinition {

    public String A;
    public String B;
    public String C;
    public boolean IsStatic;

    public void Draw(World world, double proportionX, double proportionY)
    {
        Vec2 a =  jsonReader.proportionate(jsonReader.toVec2(A), proportionX, proportionY);
        Vec2 b =  jsonReader.proportionate(jsonReader.toVec2(B), proportionX, proportionY);
        Vec2 c =  jsonReader.proportionate(jsonReader.toVec2(C), proportionX, proportionY);

        new Poly(world, !IsStatic, a, b, c);
    }

}
