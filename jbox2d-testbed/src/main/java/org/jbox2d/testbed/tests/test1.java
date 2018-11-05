package org.jbox2d.testbed.tests;

import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.joints.RevoluteJointDef;
import org.jbox2d.pooling.arrays.Vec2Array;
import org.jbox2d.testbed.framework.TestbedTest;

public class test1 extends TestbedTest {

    @Override
    public void initTest() {
        Body triangle;
        Body frame;
        Body ball;
        Body pendulum;
        Body ground;

        {
            Vec2[] vertices = new Vec2[3];
            vertices[0] = new Vec2(0.0f, 0.0f);
            vertices[1] = new Vec2(2.0f, 0.0f);
            vertices[2] = new Vec2(0.0f, 1.0f);

            PolygonShape polygon = new PolygonShape();
            polygon.set(vertices, 3);

            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyType.STATIC;
            triangle = getWorld().createBody(bodyDef);
            triangle.createFixture(polygon, 1);
        }

        {
            Vec2[] vertices = new Vec2[4];
            vertices[0] = new Vec2(-1,0);
            vertices[1] = new Vec2(5,0);
            vertices[2] = new Vec2(5, 2);
            vertices[3] = new Vec2(2.2f, 2);

            ChainShape chain = new ChainShape();
            chain.createChain(vertices, 4);

            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyType.STATIC;
            frame = getWorld().createBody(bodyDef);
            frame.createFixture(chain, 1);
        }

        {
            CircleShape ballShape = new CircleShape();
            ballShape.m_radius = 0.5f;
            Shape shape = ballShape;


            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyType.DYNAMIC;
            bodyDef.position.set(0.1f, 3);
            ball = getWorld().createBody(bodyDef);
            ball.createFixture(shape, 1);
        }

        {
            CircleShape circleShape = new CircleShape();
            circleShape.m_radius = 0.2f;
            Shape shape = circleShape;

            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyType.DYNAMIC;
            bodyDef.position.set(2.6f, 0.3f);
            pendulum = getWorld().createBody(bodyDef);
            pendulum.createFixture(shape, 1);
        }

        {
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyType.STATIC;
            bodyDef.position.set(2.6f, 2);
            ground = getWorld().createBody(bodyDef);
        }

        RevoluteJointDef jointDef = new RevoluteJointDef();

        jointDef.initialize(pendulum, ground, new Vec2(2.6f, 2));

        //pendulum.applyAngularImpulse(1000);

        getWorld().createJoint(jointDef);

        getCamera().setCamera(new Vec2(2,1));
    }

    @Override
    public String getTestName() {
        return "Test 1";
    }
}

