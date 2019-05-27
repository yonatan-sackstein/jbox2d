package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.Cart;

public class CartDefinition {

    public String A;
    public String B;
    public String C;
    public String D;
    public String wheel1;
    public String wheel2;
    public float radius;

    public void Draw(World world, double proportionX, double proportionY)
    {
        // TODO: another proportionate function with string as vector parameter
        Vec2 vert1 = jsonReader.proportionate(jsonReader.toVec2(A), proportionX, proportionY);
        Vec2 vert2 = jsonReader.proportionate(jsonReader.toVec2(B), proportionX, proportionY);
        Vec2 vert3 = jsonReader.proportionate(jsonReader.toVec2(C), proportionX, proportionY);
        Vec2 vert4 = jsonReader.proportionate(jsonReader.toVec2(D), proportionX, proportionY);

        Vec2 wheelCenter1 = jsonReader.proportionate(jsonReader.toVec2(wheel1), proportionX, proportionY);
        Vec2 wheelCenter2 = jsonReader.proportionate(jsonReader.toVec2(wheel2), proportionX, proportionY);

        // TODO: Figure out how the proportionate function should affect the wheels radius

        float wheelsRadius = (float)(radius/Math.pow(Math.pow(proportionX,2) + Math.pow(proportionY,2),0.5));

        Vec2[] wheelCenters = new Vec2[2];
        wheelCenters[0] = wheelCenter1;
        wheelCenters[1] = wheelCenter2;

        Vec2[] vertices = new Vec2[4];
        vertices[0] = vert1;
        vertices[1] = vert2;
        vertices[2] = vert3;
        vertices[3] = vert4;

        new Cart(world, vertices, wheelCenters, wheelsRadius);
    }

}