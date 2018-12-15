package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.Ball;

public class BallDefinition {

    public String Center;
    public float Radius;
    public Boolean IsStatic;

    // TODO: Make definitionDraw class
    public void Draw(World world)
    {
        Vec2 center = jsonReader.toVec2(Center);

        new Ball(world, center, Radius, !IsStatic);
    }


}
