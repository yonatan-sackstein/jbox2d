package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.Poly;
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

        float squareSize = 0.1f;

        Poly poly1 = new Poly(world,true,
                a,
                new Vec2(a.x - squareSize, a.y ),
                new Vec2(a.x , a.y - squareSize),
                new Vec2(a.x - squareSize, a.y - squareSize));

        Poly poly2 = new Poly(world,true,
                b,
                new Vec2(b.x + squareSize, b.y ),
                new Vec2(a.x , b.y + squareSize),
                new Vec2(b.x + squareSize, b.y + squareSize));

        new Spring(world, poly1.body, poly2.body, a, b);

    }
}
