package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.Rect;

public class BlockDefinition {

    public String Center;
    public double Height;
    public double Width;
    public float Angle;
    public boolean IsStatic;


    public void Draw(World world, double proportionX, double proportionY)
    {
        Vec2 center = jsonReader.proportionate(jsonReader.toVec2(Center), proportionX, proportionY);

        Vec2 dim = jsonReader.proportionate(new Vec2((int)Width/2, (int)Height/2), proportionX, proportionY);

        new Rect(world, center, dim, !IsStatic, Angle);
    }

}
