package org.jbox2d.testbed.tests;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.joints.RevoluteJointDef;
import org.jbox2d.testbed.framework.TestbedTest;

public class PhysicalSimulator extends TestbedTest {
    private final boolean switchBodiesInJoint;

    public PhysicalSimulator(boolean switchBodiesInJoint) {
        this.switchBodiesInJoint = switchBodiesInJoint;
    }

    @Override
    public boolean isSaveLoadEnabled() {
        return true;
    }

    @Override
    public void initTest() {
        Body pendulum;
        Body ground;

        {
            CircleShape circleShape = new CircleShape();
            circleShape.m_radius = 1;
            Shape shape = circleShape;

            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyType.DYNAMIC;
            bodyDef.position.set(0, -5);
            bodyDef.allowSleep = false;
            pendulum = getWorld().createBody(bodyDef);
            pendulum.createFixture(shape, 1);
        }

        {
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyType.STATIC;
            ground = getWorld().createBody(bodyDef);
        }

        RevoluteJointDef jointDef = new RevoluteJointDef();

        if (switchBodiesInJoint)
            jointDef.initialize(pendulum, ground, new Vec2(0, 0));
        else
            jointDef.initialize(ground, pendulum, new Vec2(0, 0));

        pendulum.applyAngularImpulse(0);

        getWorld().createJoint(jointDef);
    }

    @Override
    public String getTestName() {
        return "Physical Simulator " + (switchBodiesInJoint ? "1" : "0");
    }
}

