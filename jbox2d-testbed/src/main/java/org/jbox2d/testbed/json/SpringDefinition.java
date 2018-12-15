package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.Rect;
import org.jbox2d.testbed.tests.Spring;

public class SpringDefinition {

    public String A;
    public String B;

    public void Draw(World world)
    {
        Vec2 a = jsonReader.toVec2(A);
        Vec2 b = jsonReader.toVec2(B);

        Spring.springFrequency = 0.3f;

        // TODO: The spring is not connected to other bodies, so we attach it to small blocks
        Rect rect1 = new Rect(world, a, new Vec2(1, 1));
        Rect rect2 = new Rect(world, b, new Vec2(1, 1));

        new Spring(world, rect1.body, rect2.body, a, b);

    }
}
