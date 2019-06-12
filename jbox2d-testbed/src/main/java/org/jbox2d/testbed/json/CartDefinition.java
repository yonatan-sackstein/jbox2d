package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
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

    public Body Draw(World world, float scale)
    {
        // TODO: another proportionate function with string as vector parameter
        Vec2 vert1 = jsonReader.toVec2(A).mul(scale);
        Vec2 vert2 = jsonReader.toVec2(B).mul(scale);
        Vec2 vert3 = jsonReader.toVec2(C).mul(scale);
        Vec2 vert4 = jsonReader.toVec2(D).mul(scale);

        Vec2 wheelCenter1 = jsonReader.toVec2(wheel1).mul(scale);
        Vec2 wheelCenter2 = jsonReader.toVec2(wheel2).mul(scale);

        radius = radius * scale;

        // TODO: Figure out how the proportionate function should affect the wheels radius

        Vec2[] wheelCenters = new Vec2[2];
        wheelCenters[0] = wheelCenter1;
        wheelCenters[1] = wheelCenter2;

        Vec2[] vertices = new Vec2[4];
        vertices[0] = vert1;
        vertices[1] = vert2;
        vertices[2] = vert3;
        vertices[3] = vert4;

        Cart cart = new Cart(world, vertices, wheelCenters, radius);
        return cart.body;
    }

}