package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.Rect;

public class BlockDefinition {

    public String Center;
    public double Height;
    public double Width;
    public float Angle;
    public Boolean IsStatic; // TODO: not used


    public void Draw(World world)
    {
        Vec2 center = jsonReader.toVec2(Center);
        Vec2 dim = new Vec2((int)Width/2, (int)Height/2);

        new Rect(world, center, dim, Angle);
    }

}
