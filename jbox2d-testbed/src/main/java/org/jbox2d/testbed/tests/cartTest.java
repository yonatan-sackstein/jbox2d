package org.jbox2d.testbed.tests;

import org.jbox2d.common.Vec2;
import org.jbox2d.testbed.framework.TestbedTest;

public class cartTest extends TestbedTest {

    @Override
    public void initTest() {

        // Creating rectangular ground (with right wall)
        Rect ground = new Rect(getWorld(), 0, -10, 50, 10, 0.1f, false);
        new Rect(getWorld(), 45, 9, 5, 5, 0.1f, false);
        new Rect(getWorld(), -30, -2, 1, 1, 0.1f, false);

        // Creating static triangle
        new StaticLine(getWorld(), true, new Vec2(-20,5),
                                                   new Vec2(-5,5),
                                                   new Vec2(-20, 15));
        // Creating carts
        Vec2 cartDim = new Vec2(2, 0.5f);
        Cart cart1 = new Cart(getWorld(), -10, 0, cartDim, 0);
        new Cart(getWorld(), -18, 20, cartDim);

        // Connecting cart to wall
        Spring.springFrequency = 0.3f;
        new Spring(getWorld(), cart1.body, groundBody, cart1.body.getWorldCenter(), new Vec2(-30, -1.8f));

        new Ball(getWorld(), 20, 30, 2, false);
        new Ball(getWorld(), 13.5f, 25, 2, false);
        new Ball(getWorld(), 19, 20, 2, false);
        new Ball(getWorld(), 14, 15, 2, false);
        new Ball(getWorld(), new Vec2(18, 40), 1);

        new Poly(getWorld(), true, new Vec2(0,20),
                                             new Vec2(10,20),
                                             new Vec2(0, 25));


        getCamera().setCamera(new Vec2(1,1), 8);


    }

    @Override
    public String getTestName() {
        return "CartTest";
    }
}

