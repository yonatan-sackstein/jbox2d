package org.jbox2d.testbed.tests;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.joints.DistanceJointDef;
import org.jbox2d.dynamics.joints.PrismaticJointDef;
import org.jbox2d.dynamics.joints.RevoluteJointDef;
import org.jbox2d.common.MathUtils;

/**
 * @author Georgee Tsintsadze & Yonatan Sackstein
 * @version  1.0
 *
 * Class that creates a spring connection between two bodies in a given world
 */
public class Spring {

    // Physical constants of the spring
    public static float springFrequency = 0f;
    public static float springDampingRatio = 0;


    // *************************************************************************************
    // Spring Dimensions (used in the graphical implementation attempt,
    // which is commented bellow)

    private float handleLength = 1;
    private float springWidth = 0.7f; // Half width of the spring
    private float approximateSegmentLength = 0.4f;
    private static float lineWidth = 0.1f;
    /* Segment Length is the projection of one '/' part in the scheme bellow on the spring axis

    ____/\  /\  /\  /\  /\____
          \/  \/  \/  \/

    It is an approximation because a whole number of such segments needs
    to fit in the spring so the closest lower number of segments is
    calculated using the approximateSegmentLength
     */
    private float segmentRotationLimit = 1f;

    private static int springCategoryBits = 0x0002;
    // *************************************************************************************

    /**
     * Creates a distance joint with springFrequency springDampingRatio
     * class static variables which make it a spring.
     *
     * @param world     The world in which the ball is created
     * @param body1     First body to be connected
     * @param body2     Second body to be connected
     * @param anchor1   The connection point on the first body
     * @param anchor2   The connection point on the second body
     */
    public Spring(World world, Body body1, Body body2, Vec2 anchor1, Vec2 anchor2) {

        // Crate distance joint which provides the physical spring properties
        DistanceJointDef springJointDef = new DistanceJointDef();
        springJointDef.initialize(body1, body2, anchor1, anchor2);
        springJointDef.frequencyHz = springFrequency;
        springJointDef.dampingRatio = springDampingRatio;
        world.createJoint(springJointDef);

        // ------------------------------------------------------------------------
        //       Commented part is an attempt of visually creating a spring
        // ------------------------------------------------------------------------

//        float angle = MathUtils.atan2(anchor2.y-anchor1.y, anchor2.x-anchor1.x);
//
//        // Creating Handle connected to anchor1
//        Vec2 handle1EndVertex = new Vec2(anchor1.x + handleLength * MathUtils.cos(angle),
//                                   anchor1.y + handleLength * MathUtils.sin(angle));
//        Body lastBodyCreated = createDynamicLine(world, body1, anchor1, handle1EndVertex);
//
//        // Calculating vertices of all spring segments
//        Vec2 upperLineOrigin = new Vec2(handle1EndVertex.x - springWidth * MathUtils.sin(angle),
//                                        handle1EndVertex.y + springWidth * MathUtils.cos(angle));
//        Vec2 lowerLineOrigin = new Vec2(handle1EndVertex.x + springWidth * MathUtils.sin(angle),
//                                        handle1EndVertex.y - springWidth * MathUtils.cos(angle));
//
//        float effectiveSpringLength = MathUtils.distance(anchor1, anchor2) - 2 * handleLength;
//        float numberOfSegments = MathUtils.floor(effectiveSpringLength / approximateSegmentLength);
//        float segmentLength = effectiveSpringLength / numberOfSegments;
//
//        Body preLastBodyCreated = lastBodyCreated;
//        Vec2 previousVertex = handle1EndVertex;
//        Vec2 nextVertex, constrainAnchor;
//        Vec2 springVector = new Vec2(segmentLength * MathUtils.cos(angle), segmentLength * MathUtils.sin(angle));
//        int segmentIndex;
//
//        for (segmentIndex=1; segmentIndex<=numberOfSegments; segmentIndex++) {
//            if ((segmentIndex-1) % 4 == 0) {
//                preLastBodyCreated = lastBodyCreated;
//                nextVertex = new Vec2(upperLineOrigin.x + segmentIndex * springVector.x,
//                                      upperLineOrigin.y + segmentIndex * springVector.y);
//                lastBodyCreated = createDynamicLine(world, lastBodyCreated, previousVertex, nextVertex);
//                previousVertex = nextVertex;
//
//                constrainAnchor = new Vec2(handle1EndVertex.x + (segmentIndex-1) * springVector.x,
//                                           handle1EndVertex.y + (segmentIndex-1) * springVector.y);
//                PrismaticJointDef constrainJointDef = new PrismaticJointDef();
//                constrainJointDef.initialize(preLastBodyCreated, lastBodyCreated, constrainAnchor, springVector);
//                world.createJoint(constrainJointDef);
//            }
//            if ((segmentIndex-3) % 4 == 0) {
//                preLastBodyCreated = lastBodyCreated;
//                nextVertex = new Vec2(lowerLineOrigin.x + segmentIndex * springVector.x,
//                                      lowerLineOrigin.y + segmentIndex * springVector.y);
//                lastBodyCreated = createDynamicLine(world, lastBodyCreated, previousVertex, nextVertex);
//                previousVertex = nextVertex;
//
//                constrainAnchor = new Vec2(handle1EndVertex.x + (segmentIndex-1) * springVector.x,
//                                           handle1EndVertex.y + (segmentIndex-1) * springVector.y);
//                PrismaticJointDef constrainJointDef = new PrismaticJointDef();
//                constrainJointDef.initialize(preLastBodyCreated, lastBodyCreated, constrainAnchor, springVector);
//                world.createJoint(constrainJointDef);
//            }
//        }
//
//        // Creating last spring segment
//        Vec2 handle2EndVertex = new Vec2(anchor2.x - handleLength * MathUtils.cos(angle),
//                anchor2.y - handleLength * MathUtils.sin(angle));
//        lastBodyCreated = createDynamicLine(world, lastBodyCreated, previousVertex, handle2EndVertex);
//
//        constrainAnchor = new Vec2(handle1EndVertex.x + (segmentIndex-1) * springVector.x,
//                handle1EndVertex.y + (segmentIndex-1) * springVector.y);
//        PrismaticJointDef constrainJointDef = new PrismaticJointDef();
//        constrainJointDef.initialize(preLastBodyCreated, lastBodyCreated, constrainAnchor, springVector);
//        world.createJoint(constrainJointDef);
//
//        // Creating Handle connected to anchor2
//        lastBodyCreated = createDynamicLine(world, lastBodyCreated, handle2EndVertex, anchor2);
//
//        // Connect anchor2 handle to body2
//        RevoluteJointDef handle2JointDef = new RevoluteJointDef();
//        handle2JointDef.initialize(lastBodyCreated, body2, anchor2);
//        handle2JointDef.lowerAngle = -segmentRotationLimit;
//        handle2JointDef.upperAngle = segmentRotationLimit;
//        handle2JointDef.enableLimit = true;
//        world.createJoint(handle2JointDef);



    }




//    private Body createDynamicLine(World world, Body bodyToConnect, Vec2 start, Vec2 end) {
//        BodyDef lineBodyDef = new BodyDef();
//        lineBodyDef.type = BodyType.DYNAMIC;
//        Body lineBody = world.createBody(lineBodyDef);
//
//        Vec2[] lineVertices = new Vec2[3];
//        lineVertices[0] = start;
//        lineVertices[1] = new Vec2((start.x + end.x)/2 + lineWidth, (start.y + end.y)/2 + lineWidth);
//        lineVertices[2] = end;
//
//        PolygonShape lineShape = new PolygonShape();
//        lineShape.set(lineVertices, 3);
//        FixtureDef handle1FixtureDef = new FixtureDef();
//        handle1FixtureDef.setShape(lineShape);
//        handle1FixtureDef.setDensity(1);
//        handle1FixtureDef.friction = 0;
//        handle1FixtureDef.filter.categoryBits = springCategoryBits;
//        handle1FixtureDef.filter.maskBits = springCategoryBits;
//        lineBody.createFixture(handle1FixtureDef);
//        lineBody.setGravityScale(0);
//
//        RevoluteJointDef lineJointDef = new RevoluteJointDef();
//        lineJointDef.initialize(lineBody, bodyToConnect, start);
//        lineJointDef.lowerAngle = -segmentRotationLimit;
//        lineJointDef.upperAngle = segmentRotationLimit;
//        lineJointDef.enableLimit = true;
//        world.createJoint(lineJointDef);
//
//        return lineBody;
//    }



}
