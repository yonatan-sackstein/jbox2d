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
     * @param world     The world in which the rectangle is created
     * @param rectVert  Vec2[] of each of the rectangle vertices
     * @param dynamic   boolean clarifying is the rectangle dynamic or not
     */
    public Rect(World world, Vec2[] rectVert, boolean dynamic) {

        float centerX = 0;
        float centerY = 0;
        for (int i=0; i< rectVert.length; i++) {
            centerX += rectVert[i].x;
            centerY += rectVert[i].y;
        }
        centerX = centerX / rectVert.length;
        centerY = centerY / rectVert.length;

        // Create shape
        PolygonShape rectangleShape = new PolygonShape();
        rectangleShape.set(rectVert, 4);

        // Create body
        BodyDef rectangleBodyDef = new BodyDef();
//        rectangleBodyDef.position.set(centerX, centerY);

        if (dynamic) {
            rectangleBodyDef.type = BodyType.DYNAMIC;
        }
        else {
            rectangleBodyDef.type = BodyType.STATIC;
            // TODO: The angle, hx and hy are set to random numbers
            DrawStaticX(world, new Vec2(centerX, centerY), 0, 10, 10);
        }

        body = world.createBody(rectangleBodyDef);
        // Create fixture
        body.createFixture(rectangleShape, 1);
    }

    // Draw X in the center of static objects
    private static void DrawStaticX(World world, Vec2 position, float angle, float hx, float hy) {
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

    private static Vec2 rotateVec(Vec2 a, Vec2 axis, float angle)
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
