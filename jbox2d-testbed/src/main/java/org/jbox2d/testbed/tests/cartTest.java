package org.jbox2d.testbed.tests;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.joints.DistanceJointDef;
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

            Vec2[] wallBoxVertices = new Vec2[4];
            wallBoxVertices[0] = new Vec2(5,10);
            wallBoxVertices[1] = new Vec2(10,10);
            wallBoxVertices[2] = new Vec2(10, 15);
            wallBoxVertices[3] = new Vec2(5, 15);
            PolygonShape wallBox = new PolygonShape();
            wallBox.set(wallBoxVertices, 4);
            groundBody.createFixture(wallBox, 0.0f);
        }

        // Creating cart
        float[] cartDim = {2f, 0.5f};
        float[] cart1Pos = {0.0f, 2.0f};
        float[] cart2Pos = {-3.0f, 2.0f};
        Cart cart1 = new Cart(getWorld(), cartDim, cart1Pos, 0.5f);
        Cart cart2 = new Cart(getWorld(), cartDim, cart2Pos, 0.5f);

        // Connecting cart to wall
        Vec2 wallSpringAnchor = new Vec2(7.5f, cart1.getOuterHalfDimentions().y);
        Spring cart_wall_spring = new Spring(getWorld(), cart1.body, groundBody, cart1.body.getWorldCenter(), wallSpringAnchor);

        getCamera().setCamera(new Vec2(1,1));
    }

    @Override
    public String getTestName() {
        return "CartTest";
    }
}

