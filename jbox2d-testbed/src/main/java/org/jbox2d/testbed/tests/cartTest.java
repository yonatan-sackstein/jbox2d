package org.jbox2d.testbed.tests;

import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.framework.TestbedTest;
import org.jbox2d.testbed.json.Composition;
import org.jbox2d.testbed.json.TriangleDefinition;
import org.jbox2d.testbed.json.jsonReader;

public class cartTest extends TestbedTest {

    @Override
    public void initTest() {

        World world = getWorld();

        // Creating rectangular ground (with right wall)
        Rect ground = new Rect(world, 0, -10, 50, 10, 0.1f, false);
        new Rect(world, 45, 9, 5, 5, 0.1f, false);
        new Rect(world, -30, -2, 1, 1, 0.1f, false);

        // Creating static triangle
        new StaticLine(world, true, 0, new Vec2(-20,5),
                                                        new Vec2(-5,5),
                                                        new Vec2(-20, 15));
        // Creating carts
        Vec2 cartDim = new Vec2(2, 0.5f);
        Cart cart1 = new Cart(world, -10, 0, cartDim, 0);
        Cart cart2 = new Cart(world, -18, 20, cartDim);

        // Connecting cart to wall
        Spring.springFrequency = 0.3f;
        Spring spring1 = new Spring(world, cart1.body, groundBody, cart1.body.getWorldCenter(), new Vec2(-30, -1.8f));

        new Ball(world, 20, 30, 2, false);
        new Ball(world, 13.5f, 25, 2, false);
        new Ball(world, 19, 20, 2, false);
        new Ball(world, 14, 15, 2, false);
        new Ball(world, new Vec2(18, 40), 1);

        new Poly(world, true, 0, new Vec2(0,20),
                                             new Vec2(10,20),
                                             new Vec2(0, 25));

        getCamera().setCamera(new Vec2(500,-500), 0.5f);
    }

    @Override
    public String getTestName() {
        return "CartTest";
    }
}

