package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.Rect;
import org.jbox2d.testbed.tests.Spring;

public class SpringDefinition {

    public String A;
    public String B;

    public void Draw(World world, double proportionX, double proportionY)
    {
        Vec2 a =  jsonReader.proportionate(jsonReader.toVec2(A), proportionX, proportionY);
        Vec2 b =  jsonReader.proportionate(jsonReader.toVec2(B), proportionX, proportionY);;

        Spring.springFrequency = 0.3f;

        // TODO: The spring is not connected to other bodies, so we attach it to small blocks
        Rect rect1 = new Rect(world, a, new Vec2((float)0.1, (float)0.1), true);
        Rect rect2 = new Rect(world, b, new Vec2((float)0.1, (float)0.1), true);

        new Spring(world, rect1.body, rect2.body, a, b);

    }
}
