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
            groundBodyDef.position.set(0, -10);
            Body groundBody = getWorld().createBody(groundBodyDef);

            PolygonShape groundBox = new PolygonShape();
            groundBox.setAsBox(50, 10);
            groundBody.createFixture(groundBox, 0);

            Vec2[] wallBoxVertices = new Vec2[4];
            wallBoxVertices[0] = new Vec2(40,10);
            wallBoxVertices[1] = new Vec2(50,10);
            wallBoxVertices[2] = new Vec2(50, 20);
            wallBoxVertices[3] = new Vec2(40, 20);
            PolygonShape wallBox = new PolygonShape();
            wallBox.set(wallBoxVertices, 4);
            groundBody.createFixture(wallBox, 0);

            Vec2[] slopeGroundVertices = new Vec2[3];
            slopeGroundVertices[0] = new Vec2(-30,10);
            slopeGroundVertices[1] = new Vec2(-15,10);
            slopeGroundVertices[2] = new Vec2(-30, 20);
            PolygonShape slopeGround = new PolygonShape();
            slopeGround.set(slopeGroundVertices, 3);
            groundBody.createFixture(slopeGround, 0);

        }

        Vec2[] staticLineVertices = new Vec2[3];
        staticLineVertices[0] = new Vec2(1,1);
        staticLineVertices[1] = new Vec2(5,2);
        staticLineVertices[2] = new Vec2(10, 20);

        new StaticLine(getWorld(), true, new Vec2(1,1), new Vec2(5,10), new Vec2(8,1));

        RectBody rect1 = new RectBody(getWorld(), 10, 5, 2, 1);

        // Creating cart
        Vec2 cartDim = new Vec2(2, 0.5f);
        Cart cart1 = new Cart(getWorld(), 0, 10, cartDim, 0);
        Cart cart2 = new Cart(getWorld(), -28, 20, cartDim);

        // Connecting cart to wall
        Spring.springFrequency = 0.5f;
        Vec2 wallSpringAnchor = new Vec2(45, cart1.getOuterHalfDimensions().y);

        Spring cart_wall_spring = new Spring(getWorld(), cart1.body, groundBody,
                cart1.body.getWorldCenter(), wallSpringAnchor);

        Ball ball1 = new Ball(getWorld(), 0, 20, 2, false);
        Ball ball2 = new Ball(getWorld(), new Vec2(1, 5), 1);


        getCamera().setCamera(new Vec2(1,1), 8);


    }

    @Override
    public String getTestName() {
        return "CartTest";
    }
}

