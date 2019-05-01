package org.jbox2d.testbed.tests;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Rot;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

/**
 * @author Georgee Tsintsadze & Yonatan Sackstein
 * @version  1.0
 *
 * Class that creates a STATIC rectangular shape, in a given body or in a new one.
 */
public class Rect {
    public Body body;

    /**
     * Creates a rectangular shape in the given body or as new STATIC body.
     *
     * @param world         The world in which the rectangle is created
     * @param position      Center position
     * @param dimensions    Half of the height and width of the rectangle
     * @param dynamic       Optional - Ball is STATIC or DYNAMIC
     * @param vararg        Can be one or both, but no more than one from each of the following:
     *                      | float     angle   - the angle of the rectangle (radians)
     *                      | Body      bodyToAddTo - the body in which to create the rect shape
     */
    public Rect(World world, Vec2 position, Vec2 dimensions, boolean dynamic, Object... vararg) {
        createRectangle(world, position, dimensions.x, dimensions.y, dynamic, vararg);
    }

    /**
     * Creates a rectangular shape in the given body or as new STATIC body.
     *
     * @param world         The world in which the rectangle is created
     * @param vertices      The four vertices of the rectangle
     */
    public Rect(World world, Vec2[] vertices) {

        // Create shape
        PolygonShape rectangleShape = new PolygonShape();
        rectangleShape.set(vertices, vertices.length);

        // Create body
        BodyDef rectangleBodyDef = new BodyDef();
        rectangleBodyDef.type = BodyType.STATIC;

        body = world.createBody(rectangleBodyDef);
        // Create fixture
        body.createFixture(rectangleShape, 1);
    }

    /**
     * Creates a rectangular shape in the given body or as new STATIC body.
     *
     * @param world         The world in which the rectangle is created
     * @param x             x position
     * @param y             y position
     * @param hx            Half of the width of the rectangle
     * @param hy            Half of the height of the rectangle
     * @param vararg        Can be one or both, but no more than one from each of the following:
     *                      | float     angle   - the angle of the rectangle (radians)
     *                      | Body      bodyToAddTo - the body in which to create the rect shape
     */
    Rect(World world, float x, float y, float hx, float hy, Object... vararg) {
        createRectangle(world, new Vec2(x, y), hx, hy, true, vararg);
    }

    /**
     * Creates a rectangular shape in the given body or as new STATIC body.
     *
     * @param world         The world in which the rectangle is created
     * @param position      Center position
     * @param hx            Half of the width of the rectangle
     * @param hy            Half of the height of the rectangle
     * @param vararg        Can be one or both, but no more than one from each of the following:
     *                      | float     angle   - the angle of the rectangle (radians)
     *                      | Body      bodyToAddTo - the body in which to create the rect shape
     */
    Rect(World world, Vec2 position, float hx, float hy, Object... vararg) {
        createRectangle(world, position, hx, hy, true, vararg);
    }

    /**
     * Creates a rectangular shape in the given body or as new STATIC body.
     *
     * @param world         The world in which the rectangle is created
     * @param x             x position
     * @param y             y position
     * @param dimensions    Half of the height and width of the rectangle
     * @param vararg        Can be one or both, but no more than one from each of the following:
     *                      | float     angle   - the angle of the rectangle (radians)
     *                      | Body      bodyToAddTo - the body in which to create the rect shape
     */
    Rect(World world, float x, float y, Vec2 dimensions, Object... vararg) {
        createRectangle(world, new Vec2(x, y), dimensions.x, dimensions.y,true, vararg);
    }


    /**
     * The creation of the rectangle itself, called by the Rect wrapping methods above
     */
    private void createRectangle(World world, Vec2 position, float hx, float hy, boolean dynamic, Object... vararg) {

        // Get values of vararg
        AngleBodyTypeInterpreter angle_BodyType = new AngleBodyTypeInterpreter(vararg);

        // Create shape
        PolygonShape rectangleShape = new PolygonShape();
        rectangleShape.setAsBox(hx, hy);

        // Create body
        BodyDef rectangleBodyDef = new BodyDef();
        rectangleBodyDef.setPosition(position);
        rectangleBodyDef.setAngle(angle_BodyType.angle);

        if (dynamic) {
            rectangleBodyDef.type = BodyType.DYNAMIC;
        }
        else {
            rectangleBodyDef.type = BodyType.STATIC;
            DrawStaticX(world, position, angle_BodyType.angle, hx, hy);
        }

        body = world.createBody(rectangleBodyDef);
        // Create fixture
        body.createFixture(rectangleShape, 1);
    }

    // Draw X in the center of static objects
    public static void DrawStaticX(World world, Vec2 position, float angle, float hx, float hy) {
        float X_width = (float)0.5 * hx;
        float X_height = (float)0.5 * hy;

        Vec2 a = new Vec2(position.x - X_width/2, position.y - X_height/2); // WS corner
        Vec2 b = new Vec2(position.x + X_width/2, position.y + X_height/2); // NE corner

        Vec2 a_rot = rotateVec(a, position, angle);
        Vec2 b_rot = rotateVec(b, position, angle);

        new StaticLine(world, false, angle, a_rot, b_rot);

        a = new Vec2(position.x - X_width/2, position.y + X_height/2); // WN corner
        b = new Vec2(position.x + X_width/2, position.y - X_height/2); // SE corner
        a_rot = rotateVec(a, position, angle);
        b_rot = rotateVec(b, position, angle);

        new StaticLine(world, false, angle, a_rot, b_rot);
    }

    public static Vec2 rotateVec(Vec2 a, Vec2 axis, float angle)
    {
        // we have to recenter around (0,0) in order to rotate the vector
        a = a.sub(axis);

        float angleCos = (float)Math.cos(angle);
        float angleSin = (float)Math.sin(angle);

        Vec2 b = new Vec2( angleCos * a.x - angleSin * a.y,
                           angleSin * a.x + angleCos * a.y);

        return b.add(axis);
    }
}
