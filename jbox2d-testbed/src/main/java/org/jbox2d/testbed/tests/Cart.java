package org.jbox2d.testbed.tests;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

/**
 * @author Georgee Tsintsadze & Yonatan Sackstein
 * @version  1.0
 *
 * Class that contains a rectangular body, and two circle bodies connected to it using revolve (revolute) joint
 */
public class Cart {

    public static float wheelRadiusScale = 0.25f; // The scale of the radius of the wheel in terms of cartDim[0]
    public static float wheelsRelativePos = 0.67f; // The multiplier of the shift of the wheel holders from the cart
    // body center in in terms of cartDim[0]
    private static int cartGroupIndex = -1; // bodies in the same group never collide

    Body body;

    private Vec2 bodyDimensions;
    private float wheelRadius;
    private float wheelMargin;

    // TODO: Reduce to one constructor (only the public one is needed)

    /**
     * Creates a cart body (main rectangle and two circles for wheels)
     * The relations that define the wheels position and scale are defined using the static Cart class variables:
     * wheelRadiusScale and wheelsRelativePos.
     *
     * @param world     The world in which the cart is created
     * @param position  Position of the main rectangle body
     * @param dimension Half width and half height of the main rectangle body
     * @param angle     Optional - The angle of the cart (radians)
     */
    public Cart(World world, Vec2 position, Vec2 dimension, float... angle) {
        createCart(world, position, dimension, angle);
    }

    /**
     * Creates a cart body (main rectangle and two circles for wheels)
     * The relations that define the wheels position and scale are defined using the static Cart class variables:
     * wheelRadiusScale and wheelsRelativePos.
     *
     * @param world     The world in which the cart is created
     * @param x         X position of the main rectangle body
     * @param y         Y position of the main rectangle body
     * @param hx        Half width of the main rectangle body
     * @param hy        Half height of the main rectangle body
     * @param angle     Optional - The angle of the cart (radians)
     */
    Cart(World world, float x, float y, float hx, float hy, float... angle) {
        createCart(world, new Vec2(x, y), new Vec2(hx, hy), angle);
    }

    /**
     * Creates a cart body (main rectangle and two circles for wheels)
     * The relations that define the wheels position and scale are defined using the static Cart class variables:
     * wheelRadiusScale and wheelsRelativePos.
     *
     * @param world     The world in which the cart is created
     * @param position  Position of the main rectangle body
     * @param hx        Half width of the main rectangle body
     * @param hy        Half height of the main rectangle body
     * @param angle     Optional - The angle of the cart (radians)
     */
    Cart(World world, Vec2 position, float hx, float hy, float... angle) {
        createCart(world, position, new Vec2(hx, hy), angle);
    }

    /**
     * Creates a cart body (main rectangle and two circles for wheels)
     * The relations that define the wheels position and scale are defined using the static Cart class variables:
     * wheelRadiusScale and wheelsRelativePos.
     *
     * @param world     The world in which the cart is created
     * @param x         X position of the main rectangle body
     * @param y         Y position of the main rectangle body
     * @param dimension Half width and half height of the main rectangle body
     * @param angle     Optional - The angle of the cart (radians)
     */
    Cart(World world, float x, float y, Vec2 dimension, float... angle) {
        createCart(world, new Vec2(x, y), dimension, angle);
    }

    void createCart(World world, Vec2 cartPos, Vec2 cartDim, float... inputAngle) {

        // Take zero angle as default if no value given
        float angle = (inputAngle.length > 0) ? inputAngle[0] : 0;

        // Increment group index to differ between each cart created.
        // (all bodies created with same group index will not collide,
        // here those are the rectangle and two wheels)
        cartGroupIndex += 1;

        // Define wheels dimensions according to static Cart class variables
        wheelRadius = wheelRadiusScale * cartDim.x;
        wheelMargin = wheelRadiusScale * cartDim.x / 2;

        // Save the dimensions of the cart to be used in getOuterHalfDimensions()
        bodyDimensions = cartDim;

        // ------------------------------------------------------------------------
        //      Creating cart body (main body and two wheelHolders, no wheels)
        // ------------------------------------------------------------------------

        // Creating main cart body body
        BodyDef cartBodyDef = new BodyDef();
        cartBodyDef.position.set(cartPos.x, cartPos.y);
        cartBodyDef.type = BodyType.DYNAMIC;
        cartBodyDef.setAngle(angle);
        body = world.createBody(cartBodyDef);

        // Creating main cart body shape
        PolygonShape cartBodyBox = new PolygonShape();
        cartBodyBox.setAsBox(cartDim.x, cartDim.y);

        // Creating main cart body fixture
        FixtureDef cartBodyFixtureDef = new FixtureDef();
        cartBodyFixtureDef.setShape(cartBodyBox);
        cartBodyFixtureDef.setDensity(1);
        cartBodyFixtureDef.filter.groupIndex = cartGroupIndex;
        body.createFixture(cartBodyFixtureDef);

        // ------------------------------------------------------------------------
        //                            Creating wheels
        // ------------------------------------------------------------------------

        // Creating shape (to be used in both wheels)
        CircleShape wheelShape = new CircleShape();
        wheelShape.m_radius = wheelRadius;

        // ---------- Left wheel ----------

        // Position parameters
        Vec2 leftWheelHoldShift = new Vec2(-wheelsRelativePos * cartDim.x, -cartDim.y-wheelMargin);
        Vec2 leftWheelCenter = body.getWorldPoint(leftWheelHoldShift);

        // Creating body
        BodyDef leftWheelBodyDef = new BodyDef();
        leftWheelBodyDef.position.set(leftWheelCenter);
        leftWheelBodyDef.type = BodyType.DYNAMIC;
        Body leftWheel = world.createBody(leftWheelBodyDef);

        // Creating fixture
        FixtureDef leftWheelFixtureDef = new FixtureDef();
        leftWheelFixtureDef.setShape(wheelShape);
        leftWheelFixtureDef.setDensity(1.0f);
        leftWheelFixtureDef.filter.groupIndex = cartGroupIndex;
        leftWheel.createFixture(leftWheelFixtureDef);

        // Connecting left wheel to cart
        RevoluteJointDef leftWheelJointDef = new RevoluteJointDef();
        leftWheelJointDef.initialize(body, leftWheel, leftWheelCenter);
        world.createJoint(leftWheelJointDef);

        // ---------- Right wheel ----------

        // Position parameters
        Vec2 rightWheelHoldShift = new Vec2(wheelsRelativePos * cartDim.x, -cartDim.y-wheelMargin);
        Vec2 rightwheelCenter = body.getWorldPoint(rightWheelHoldShift);

        // Creating body
        BodyDef rightWheelBodyDef = new BodyDef();
        rightWheelBodyDef.position.set(rightwheelCenter);
        rightWheelBodyDef.type = BodyType.DYNAMIC;
        Body rightWheel = world.createBody(rightWheelBodyDef);

        // Creating fixture
        FixtureDef rightWheelFixtureDef = new FixtureDef();
        rightWheelFixtureDef.setShape(wheelShape);
        rightWheelFixtureDef.setDensity(1.0f);
        rightWheelFixtureDef.filter.groupIndex = cartGroupIndex;
        rightWheel.createFixture(rightWheelFixtureDef);

        // Connecting right wheel to cart
        RevoluteJointDef rightWheelJointDef = new RevoluteJointDef();
        rightWheelJointDef.initialize(body, rightWheel, rightwheelCenter);
        world.createJoint(rightWheelJointDef);
    }

    /**
     * Gets the half width and half height of the whole cart (including wheels)
     *
     * @return Half width and half height
     */
    public Vec2 getOuterHalfDimensions() {
        return bodyDimensions.addLocal(0, wheelRadius + wheelMargin);
    }
}
