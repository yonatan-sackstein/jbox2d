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
 * Class that creates a STATIC or DYNAMIC polygon from given vertices
 */
public class Poly {
    Body body;

    /**
     * Creates a polygon body with specified vertices
     *
     * @param world     The world in which the ball is created
     * @param dynamic    If true, the body will be DYNAMIC, else it will be STATIC
     * @param vertices  All the points of the line. Can be provided as an Vec2 array or as
     *                  multiple individual Vec2 arguments
     */
    Poly(World world, boolean dynamic, Vec2... vertices) {

        // Creating body
        BodyDef polyBodyDef = new BodyDef();
        if (dynamic) {
            polyBodyDef.type = BodyType.DYNAMIC;
        }
        else {
            polyBodyDef.type = BodyType.STATIC;
        }
        body = world.createBody(polyBodyDef);

        // Creating shape
        PolygonShape polyShape = new PolygonShape();
        polyShape.set(vertices, vertices.length);

        // Creating fixture
        body.createFixture(polyShape, 1);
    }
}
