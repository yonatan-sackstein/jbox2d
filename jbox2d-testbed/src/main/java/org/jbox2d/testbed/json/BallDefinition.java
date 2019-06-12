package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.Ball;

public class BallDefinition {

    public String Center;
    public float Radius;
    public Boolean IsStatic;

    public Body Draw(World world, float scale)
    {
        Vec2 center = jsonReader.toVec2(Center).mul(scale);

        Radius = Radius * scale;

        Ball ball = new Ball(world, center, Radius, !IsStatic);
        return ball.body;
    }

}
