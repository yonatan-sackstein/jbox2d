package org.jbox2d.testbed.tests;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

/**
 * @author Georgee Tsintsadze & Yonatan Sackstein
 * @version  1.0
 *
 * Class that contains a rectangle shaped body. Can be STATIC or DYNAMIC.
 */
public class RectBody {
    Body body;

    /**
     * Creates a rectangular body in the given world. Can be STATIC or DYNAMIC.
     *
     * @param world         The world in which the rectangle is created
     * @param position      Center position
     * @param dimensions    Half of the height and width of the rectangle
     * @param vararg        Can be one or both, but no more than one from each of the following:
     *                      | float     angle   - the angle of the rectangle (radians)
     *                      | boolean   dynamic - is STATIC or DYNAMIC
     */
    RectBody(World world, Vec2 position, Vec2 dimensions, Object... vararg) {
        createRectangle(world, position, dimensions.x, dimensions.y, vararg);
    }

    /**
     * Creates a rectangular body in the given world. Can be STATIC or DYNAMIC.
     *
     * @param world         The world in which the rectangle is created
     * @param x             x position
     * @param y             y position
     * @param hx            Half of the width of the rectangle
     * @param hy            Half of the height of the rectangle
     * @param vararg        Can be one or both, but no more than one from each of the following:
     *                      | float     angle   - the angle of the rectangle (radians)
     *                      | boolean   dynamic - is STATIC or DYNAMIC
     */
    RectBody(World world, float x, float y, float hx, float hy, Object... vararg) {
        createRectangle(world, new Vec2(x, y), hx, hy, vararg);
    }

    /**
     * Creates a rectangular body in the given world. Can be STATIC or DYNAMIC.
     *
     * @param world         The world in which the rectangle is created
     * @param position      Center position
     * @param hx            Half of the width of the rectangle
     * @param hy            Half of the height of the rectangle
     * @param vararg        Can be one or both, but no more than one from each of the following:
     *                      | float     angle   - the angle of the rectangle (radians)
     *                      | boolean   dynamic - is STATIC or DYNAMIC
     */
    RectBody(World world, Vec2 position, float hx, float hy, Object... vararg) {
        createRectangle(world, position, hx, hy, vararg);
    }

    /**
     * Creates a rectangular body in the given world. Can be STATIC or DYNAMIC.
     *
     * @param world         The world in which the rectangle is created
     * @param x             x position
     * @param y             y position
     * @param dimensions    Half of the height and width of the rectangle
     * @param vararg        Can be one or both, but no more than one from each of the following:
     *                      | float     angle   - the angle of the rectangle (radians)
     *                      | boolean   dynamic - is STATIC or DYNAMIC
     */
    RectBody(World world, float x, float y, Vec2 dimensions, Object... vararg) {
        createRectangle(world, new Vec2(x, y), dimensions.x, dimensions.y, vararg);
    }


    /**
     * The creation of the rectangle itself, called by the RectBody wrapping methods above
     */
    private void createRectangle(World world, Vec2 position, float hx, float hy, Object... vararg) {


        // ---------------------------------------------------------------------------------------
        //                                 Get values of vararg
        // ---------------------------------------------------------------------------------------

        // Deafult values
        float angle = 0;
        boolean dynamic = true;

        // Used to determine if a float or a boolean was passed twice
        boolean argumentWasBoolean = false;
        boolean argumentWasFloat = false;

        // If only one vararg passed
        if (vararg.length == 1) {
            if (vararg[0] instanceof Float) {
                angle = (Float) vararg[0];
            }
            else if (vararg[0] instanceof Boolean) {
                dynamic = (Boolean) vararg[0];
            }
            // If its not a float and not a boolean, throw an error
            else {
                throw new ClassCastException("vararg of RectBody can only be float or boolean");
            }
        }
        // If two variables passed
        else if (vararg.length == 2) {
            // Loop over the two variables
            for (int i = 0; i<=1; i++){
                if (vararg[i] instanceof Float) {
                    // If a float already appeared, trow an error that there are two floats
                    if (argumentWasFloat) {
                        throw new ClassCastException("vararg of RectBody can only be one float and one boolean" +
                                " but you provided two floats");
                    }
                    else {
                        angle = (Float) vararg[i];
                    }
                    // Remember that a float appeared
                    argumentWasFloat = true;
                }
                // If a boolean already appeared, trow an error that there are two booleans
                else if (vararg[i] instanceof Boolean) {
                    if (argumentWasBoolean) {
                        throw new ClassCastException("vararg of RectBody can only be one float and one boolean" +
                                " but you provided two booleans");
                    }
                    else {
                        dynamic = (Boolean) vararg[i];
                    }
                    // Remember that a boolean appeared
                    argumentWasBoolean = true;
                }
                // If there are more than two varargs, throw an error
                else {
                    throw new ClassCastException("vararg of RectBody can only be float or boolean");
                }
            }
        }

        // ---------------------------------------------------------------------------------------
        //                                    Create Rectangle
        // ---------------------------------------------------------------------------------------

        // Create body
        BodyDef rectangleBodyDef = new BodyDef();
        rectangleBodyDef.setPosition(position);
        rectangleBodyDef.setAngle(angle);
        if (dynamic) {
            rectangleBodyDef.type = BodyType.DYNAMIC;
        }
        else {
            rectangleBodyDef.type = BodyType.STATIC;
        }
        body = world.createBody(rectangleBodyDef);

        // Create shape
        PolygonShape rectangleShape = new PolygonShape();
        rectangleShape.setAsBox(hx, hy);

        // Create fixture
        body.createFixture(rectangleShape, 1);
    }
}
