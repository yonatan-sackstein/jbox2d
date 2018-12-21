package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.Poly;

public class TriangleDefinition {

    public String A;
    public String B;
    public String C;
    public float Angle;

    public TriangleDefinition(String s, String s1, String s2, float i) {
        A = s;
        B = s1;
        C = s2;
        Angle = i;
    }

    public void Draw(World world, double proportionX, double proportionY)
    {
        Vec2 a =  jsonReader.proportionate(jsonReader.toVec2(A), proportionX, proportionY);;
        Vec2 b =  jsonReader.proportionate(jsonReader.toVec2(B), proportionX, proportionY);;
        Vec2 c =  jsonReader.proportionate(jsonReader.toVec2(C), proportionX, proportionY);;
        Boolean isStatic = true;// TODO: is necessary ?

        new Poly(world, isStatic, Angle, a, b, c);
    }

}
