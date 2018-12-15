package org.jbox2d.testbed.tests;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.framework.TestbedTest;
import org.jbox2d.testbed.json.Composition;
import org.jbox2d.testbed.json.jsonReader;

public class mainTest extends TestbedTest {

    @Override
    public void initTest() {

        World world = getWorld();
        Composition comp = jsonReader.fromJSON("Synthetic_78.json");
        jsonReader.CompositionDecoder(comp, world);

        // TODO: normalise the drawing to a constant camera
        getCamera().setCamera(new Vec2(500,-500), 0.5f);
    }

    @Override
    public String getTestName() {
        return "MainTest";
    }
}

