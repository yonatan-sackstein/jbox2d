package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.StaticLine;

public class LineDefinition {

    public String A;
    public String B;

    public void Draw(World world)
    {
        Vec2 a = jsonReader.toVec2(A);
        Vec2 b = jsonReader.toVec2(B);

        new StaticLine(world, false, a, b);
    }
}
