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
    Body body;

    /**
     * Creates a rectangular shape in the given body or as new STATIC body.
     *
     * @param world         The world in which the rectangle is created
     * @param position      Center position
     * @param dimensions    Half of the height and width of the rectangle
     * @param vararg        Can be one or both, but no more than one from each of the following:
     *                      | float     angle   - the angle of the rectangle (radians)
     *                      | Body      bodyToAddTo - the body in which to create the rect shape
     */
    Rect(World world, Vec2 position, Vec2 dimensions, Object... vararg) {
        createRectangle(world, position, dimensions.x, dimensions.y, vararg);
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
        createRectangle(world, new Vec2(x, y), hx, hy, vararg);
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
        createRectangle(world, position, hx, hy, vararg);
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
        createRectangle(world, new Vec2(x, y), dimensions.x, dimensions.y, vararg);
    }


    /**
     * The creation of the rectangle itself, called by the Rect wrapping methods above
     */
    private void createRectangle(World world, Vec2 position, float hx, float hy, Object... vararg) {

        // Get values of vararg
        AngleBodyTypeInterpreter angle_BodyType = new AngleBodyTypeInterpreter(vararg);

        // Create shape
        PolygonShape rectangleShape = new PolygonShape();
        rectangleShape.setAsBox(hx, hy);

        // Create body
        BodyDef rectangleBodyDef = new BodyDef();
        rectangleBodyDef.setPosition(position);
        rectangleBodyDef.setAngle(angle_BodyType.angle);
        if (angle_BodyType.dynamic) {
            rectangleBodyDef.type = BodyType.DYNAMIC;
        }
        else {
            rectangleBodyDef.type = BodyType.STATIC;
        }

        body = world.createBody(rectangleBodyDef);
        // Create fixture
        body.createFixture(rectangleShape, 1);
    }
}
