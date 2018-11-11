package org.jbox2d.testbed.tests;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

class Cart {

    public float wheelRadiusScale = 0.25f; // The scale of the radius of the wheel in terms of cartDim[0]
    public float wheelsRelativePos = 0.67f; // The multiplier of the shift of the wheel holders from the cart body
    // center in in terms of cartDim[0]
    public int cartGroupIndex = -1; // bodies in the same group never collide
    Body body;

    private float[] bodyDimensions;
    private float wheelRadius;
    private float wheelMargin;

    Cart(World world, float[] cartDim, float[] cartPos, float angle) {

        wheelRadius = wheelRadiusScale * cartDim[0];
        wheelMargin = wheelRadiusScale * cartDim[0] / 2;
        bodyDimensions = cartDim;

        // ------------------------------------------------------------------------
        //      Creating cart body (main body and two wheelHolders, no wheels)
        // ------------------------------------------------------------------------

        BodyDef cartBodyDef = new BodyDef();;
        cartBodyDef.position.set(cartPos[0], cartPos[1]);
        cartBodyDef.type = BodyType.DYNAMIC;
        cartBodyDef.setAngle(angle);
        body = world.createBody(cartBodyDef);

        // Creating main cart body shape
        PolygonShape cartBodyBox = new PolygonShape();
        cartBodyBox.setAsBox(cartDim[0], cartDim[1]);
        FixtureDef cartBodyFixtureDef = new FixtureDef();
        cartBodyFixtureDef.setShape(cartBodyBox);
        cartBodyFixtureDef.setDensity(1.0f);
        cartBodyFixtureDef.filter.groupIndex = cartGroupIndex;
        body.createFixture(cartBodyFixtureDef);

        // -------------------------
        //      Creating Wheels
        // -------------------------

        CircleShape wheelShape = new CircleShape();
        wheelShape.m_radius = wheelRadius;

        // Creating Left Wheel
        Vec2 leftWheelHoldShift = new Vec2(-wheelsRelativePos * cartDim[0], -cartDim[1]-wheelMargin);
        Vec2 leftWheelCenter = body.getWorldPoint(leftWheelHoldShift);

        BodyDef leftWheelBodyDef = new BodyDef();
        leftWheelBodyDef.position.set(leftWheelCenter);
        leftWheelBodyDef.type = BodyType.DYNAMIC;
        Body leftWheel = world.createBody(leftWheelBodyDef);
        FixtureDef leftWheelFixtureDef = new FixtureDef();
        leftWheelFixtureDef.setShape(wheelShape);
        leftWheelFixtureDef.setDensity(1.0f);
        leftWheelFixtureDef.filter.groupIndex = cartGroupIndex;
        Fixture leftWheelFixture = leftWheel.createFixture(leftWheelFixtureDef);
        leftWheelFixture.setFriction(0);

        // Connecting left wheel to cart
        RevoluteJointDef leftWheelJointDef = new RevoluteJointDef();
        leftWheelJointDef.initialize(body, leftWheel, leftWheelCenter);
        world.createJoint(leftWheelJointDef);

        // Creating Right Wheel
        Vec2 rightWheelHoldShift = new Vec2(wheelsRelativePos * cartDim[0], -cartDim[1]-wheelMargin);
        Vec2 rightwheelCenter = body.getWorldPoint(rightWheelHoldShift);

        BodyDef rightWheelBodyDef = new BodyDef();
        rightWheelBodyDef.position.set(rightwheelCenter);
        rightWheelBodyDef.type = BodyType.DYNAMIC;
        Body rightWheel = world.createBody(rightWheelBodyDef);
        FixtureDef rightWheelFixtureDef = new FixtureDef();
        rightWheelFixtureDef.setShape(wheelShape);
        rightWheelFixtureDef.setDensity(1.0f);
        rightWheelFixtureDef.filter.groupIndex = cartGroupIndex;
        Fixture rightWheelFixture = rightWheel.createFixture(rightWheelFixtureDef);
        rightWheelFixture.setFriction(0);

        // Connecting right wheel to cart
        RevoluteJointDef rightWheelJointDef = new RevoluteJointDef();
        rightWheelJointDef.initialize(body, rightWheel, rightwheelCenter);
        world.createJoint(rightWheelJointDef);
    }

    public Vec2 getOuterHalfDimentions() {
        return new Vec2(bodyDimensions[0], bodyDimensions[1] + wheelRadius + wheelMargin);
    }
}
