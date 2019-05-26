package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.Ball;

public class BallDefinition {

    public String Center;
    public float Radius;
    public Boolean IsStatic;

    public void Draw(World world, double proportionX, double proportionY)
    {
        Vec2 center = jsonReader.proportionate(jsonReader.toVec2(Center),proportionX, proportionY);

        new Ball(world, center, (float)(Radius/Math.pow(Math.pow(proportionX,2)+Math.pow(proportionY,2),0.5)), !IsStatic);
    }

}
