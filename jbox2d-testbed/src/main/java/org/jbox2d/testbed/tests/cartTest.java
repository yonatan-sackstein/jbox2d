package org.jbox2d.testbed.tests;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.testbed.framework.TestbedTest;

public class cartTest extends TestbedTest {

    @Override
    public void initTest() {

        {// Ground
            BodyDef groundBodyDef = new BodyDef();;
            groundBodyDef.position.set(0.0f, -10.0f);
            Body groundBody = getWorld().createBody(groundBodyDef);

            PolygonShape groundBox = new PolygonShape();
            groundBox.setAsBox(50.0f, 10.0f);
            groundBody.createFixture(groundBox, 0.0f);
        }

        float[] cartDim = {2f, 0.5f};
        float[] cartPos = {0.0f, 2.0f};
        Cart cart1 = new Cart(getWorld(), cartDim, cartPos, 0.5f);

        getCamera().setCamera(new Vec2(1,1));
    }

    @Override
    public String getTestName() {
        return "CartTest";
    }
}

