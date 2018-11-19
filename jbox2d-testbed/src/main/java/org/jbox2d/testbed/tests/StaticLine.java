package org.jbox2d.testbed.tests;

import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

/**
 * @author Georgee Tsintsadze & Yonatan Sackstein
 * @version  1.0
 *
 * Class that contains a body of a static chain line with the provided vertices.
 * The resulting body is only STATIC
 */
public class StaticLine {
    Body body;

    /**
     * Creates a body and a chain shape (and fixture for them).
     * PAY ATTENTION when providing vertices and if closing loop because chain cannot intersect itself!
     *
     * @param world     The world in which the ball is created
     * @param closeLoop If true, the last vertex will be connected to the first one
     * @param vertices  All the points of the line. Can be provided as an Vec2 array or as
     *                  multiple individual Vec2 arguments
     */
    StaticLine(World world, boolean closeLoop,  Vec2... vertices) {

        // Creating body
        BodyDef ballBodyDef = new BodyDef();
        body = world.createBody(ballBodyDef);

        // Creating shape
        ChainShape lineShape = new ChainShape();
        if (closeLoop) {
            lineShape.createLoop(vertices, vertices.length);
        }
        else {
            lineShape.createChain(vertices, vertices.length);
        }

        // Creating fixture
        body.createFixture(lineShape, 0);
    }
}
