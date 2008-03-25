package org.jbox2d.testbed.tests;

import org.jbox2d.collision.CircleDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.testbed.AbstractExample;
import org.jbox2d.testbed.TestbedMain;

public class CCDTest extends AbstractExample {
	private boolean firstTime;
	
	public CCDTest(TestbedMain _parent) {
		super(_parent);
		firstTime = true;
	}
	
	@Override
	public void create() {
		
		if (firstTime) {
			m_debugDraw.setCamera(0f, 20f, 20f);
			firstTime = false;
		}
		
		final float k_restitution = 1.0f;

		{
			BodyDef bd = new BodyDef();
			bd.position.set(0.0f, 20.0f);
			Body body = m_world.createStaticBody(bd);

			PolygonDef sd = new PolygonDef();
			sd.density = 0.0f;
			sd.restitution = k_restitution;

			sd.setAsBox(0.1f, 10.0f, new Vec2(-10.0f, 0.0f), 0.0f);
			body.createShape(sd);

			sd.setAsBox(0.1f, 10.0f, new Vec2(10.0f, 0.0f), 0.0f);
			body.createShape(sd);

			sd.setAsBox(0.1f, 10.0f,new Vec2(0.0f, -10.0f), 0.5f * 3.1415f);
			body.createShape(sd);

			sd.setAsBox(0.1f, 10.0f, new Vec2(0.0f, 10.0f), -0.5f * 3.1415f);
			body.createShape(sd);
		}

		{
			PolygonDef sd_bottom = new PolygonDef();
			sd_bottom.setAsBox( 1.5f, 0.15f );
			sd_bottom.density = 4.0f;

			PolygonDef sd_left = new PolygonDef();
			sd_left.setAsBox(0.15f, 2.7f, new Vec2(-1.45f, 2.35f), 0.2f);
			sd_left.density = 4.0f;

			PolygonDef sd_right = new PolygonDef();
			sd_right.setAsBox(0.15f, 2.7f, new Vec2(1.45f, 2.35f), -0.2f);
			sd_right.density = 4.0f;

			BodyDef bd = new BodyDef();
			bd.position.set( 0.0f, 15.0f );
			bd.isBullet = true;
			Body body = m_world.createDynamicBody(bd);
			body.createShape(sd_bottom);
			body.createShape(sd_left);
			body.createShape(sd_right);
			body.setMassFromShapes();
		}


		for (int i = 0; i < 10; ++i) {
			BodyDef bd = new BodyDef();
			bd.position.set(0.0f, 15.2f + i);
			bd.isBullet = true;
			Body body = m_world.createDynamicBody(bd);
			body.setAngularVelocity(parent.random(-50.0f, 50.0f));

			CircleDef sd = new CircleDef();
			sd.radius = 0.25f;
			sd.density = 1.0f;
			sd.restitution = 0.0f;
			body.createShape(sd);
			body.setMassFromShapes();
		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Continuous Collision Test";
	}

}
