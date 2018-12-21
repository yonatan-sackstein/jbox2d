package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.StaticLine;

public class LineDefinition {

    public String A;
    public String B;

    public void Draw(World world, double proportionX, double proportionY)
    {
        Vec2 a = jsonReader.proportionate(jsonReader.toVec2(A), proportionX, proportionY);

        Vec2 b = jsonReader.proportionate(jsonReader.toVec2(B), proportionX, proportionY);

        new StaticLine(world, false, a, b);
    }
}
