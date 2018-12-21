package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.tests.Cart;
import org.jbox2d.testbed.framework.TestbedTest;

import java.awt.*;

public class CartDefinition {

    public String BodyCenter;
    public int BodyWidth;
    public int BodyHeight;
    public float Angle;

    public void Draw(World world, double proportionX, double proportionY)
    {
        Vec2 bodyCenter = jsonReader.proportionate(jsonReader.toVec2(BodyCenter), proportionX, proportionY);
        Vec2 cartDim = jsonReader.proportionate(new Vec2(BodyWidth, BodyHeight), proportionX, proportionY);

        new Cart(world, bodyCenter, cartDim, Angle);
    }

}
