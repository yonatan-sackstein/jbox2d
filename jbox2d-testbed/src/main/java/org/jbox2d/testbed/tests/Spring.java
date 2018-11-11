package org.jbox2d.testbed.tests;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.joints.DistanceJointDef;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

public class Spring {
    public static float springFrequency = 1f;
    public static float springDampingRatio = 0f;

    Spring(World world, Body body1, Body body2, Vec2 anchor1, Vec2 anchor2) {
        DistanceJointDef springJointDef = new DistanceJointDef();
        springJointDef.initialize(body1, body2, anchor1, anchor2);
        springJointDef.frequencyHz = springFrequency;
        springJointDef.dampingRatio = springDampingRatio;
        world.createJoint(springJointDef);
    }
}
