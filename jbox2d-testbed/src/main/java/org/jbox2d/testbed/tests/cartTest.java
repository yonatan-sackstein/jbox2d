package org.jbox2d.testbed.tests;

import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;
import org.jbox2d.testbed.framework.TestbedTest;

public class cartTest extends TestbedTest {

    @Override
    public void initTest() {
        int cartGroupIndex = -1; // Parts of the cart group never collide


        {// Ground
            BodyDef groundBodyDef = new BodyDef();;
            groundBodyDef.position.set(0.0f, -10.0f);
            Body groundBody = getWorld().createBody(groundBodyDef);

            PolygonShape groundBox = new PolygonShape();
            groundBox.setAsBox(50.0f, 10.0f);
            groundBody.createFixture(groundBox, 0.0f);
        }

        {// Cart

            // Properties (will be arguments of the create cart function)

            float[] cartPos = {0.0f, 2.0f}; // x y position in the world
            float[] cartDim = {1, 0.5f}; // x y half dimensions of the main cart body box
            float wheelsRelativePos = 0.67f; // The multiplier of the shift of the wheel holders from the cart body
            // center in in terms of cartDim[0]
            float wheelMarginScale = 0.125f; // The scale of the margin of the wheel beneath the cart body in terms of
            // cartDim[0]
            float wheelRadius = 2*wheelMarginScale*cartDim[0];
            float wheelMargin = wheelMarginScale * cartDim[0];

            // ------------------------------------------------------------------------
            //      Creating cart body (main body and two wheelHolders, no wheels)
            // ------------------------------------------------------------------------

            BodyDef cartBodyDef = new BodyDef();;
            cartBodyDef.position.set(cartPos[0], cartPos[1]);
            cartBodyDef.type = BodyType.DYNAMIC;
            Body cart = getWorld().createBody(cartBodyDef);

            // Creating main cart body shape
            PolygonShape cartBodyBox = new PolygonShape();
            cartBodyBox.setAsBox(cartDim[0], cartDim[1]);
            FixtureDef cartBodyFixtureDef = new FixtureDef();
            cartBodyFixtureDef.setShape(cartBodyBox);
            cartBodyFixtureDef.setDensity(1.0f);
            cartBodyFixtureDef.filter.groupIndex = cartGroupIndex;
            cart.createFixture(cartBodyFixtureDef);

            // -------------------------
            //      Creating Wheels
            // -------------------------

            CircleShape wheelShape = new CircleShape();
            wheelShape.m_radius = wheelRadius;

            // Creating Left Wheel
            Vec2 leftWheelHoldShift = new Vec2(-wheelsRelativePos * cartDim[0], -cartDim[1]-wheelMargin);
            Vec2 leftWheelCenter = cart.getWorldPoint(leftWheelHoldShift);

            BodyDef leftWheelBodyDef = new BodyDef();
            leftWheelBodyDef.position.set(leftWheelCenter);
            leftWheelBodyDef.type = BodyType.DYNAMIC;
            Body leftWheel = getWorld().createBody(leftWheelBodyDef);
            FixtureDef leftWheelFixtureDef = new FixtureDef();
            leftWheelFixtureDef.setShape(wheelShape);
            leftWheelFixtureDef.setDensity(1.0f);
            leftWheelFixtureDef.filter.groupIndex = cartGroupIndex;
            leftWheel.createFixture(leftWheelFixtureDef);

            // Connecting left wheel to cart
            RevoluteJointDef leftWheelJointDef = new RevoluteJointDef();
            leftWheelJointDef.initialize(cart, leftWheel, leftWheelCenter);
            Joint leftWheelJoint = getWorld().createJoint(leftWheelJointDef);

            // Creating Right Wheel
            Vec2 rightWheelHoldShift = new Vec2(wheelsRelativePos * cartDim[0], -cartDim[1]-wheelMargin);
            Vec2 rightwheelCenter = cart.getWorldPoint(rightWheelHoldShift);

            BodyDef rightWheelBodyDef = new BodyDef();
            rightWheelBodyDef.position.set(rightwheelCenter);
            rightWheelBodyDef.type = BodyType.DYNAMIC;
            Body rightWheel = getWorld().createBody(rightWheelBodyDef);
            FixtureDef rightWheelFixtureDef = new FixtureDef();
            rightWheelFixtureDef.setShape(wheelShape);
            rightWheelFixtureDef.setDensity(1.0f);
            rightWheelFixtureDef.filter.groupIndex = cartGroupIndex;
            rightWheel.createFixture(rightWheelFixtureDef);

            // Connecting right wheel to cart
            RevoluteJointDef rightWheelJointDef = new RevoluteJointDef();
            rightWheelJointDef.initialize(cart, rightWheel, rightwheelCenter);
            Joint rightWheelJoint = getWorld().createJoint(rightWheelJointDef);
        }


        getCamera().setCamera(new Vec2(1,1));
    }

    @Override
    public String getTestName() {
        return "CartTest";
    }
}

