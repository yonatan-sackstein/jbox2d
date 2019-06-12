package org.jbox2d.testbed.json;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.DistanceJointDef;

public class LineSpringDefinition {

    public String A;
    public String B;
    public String connectionA;
    public String connectionB;
    public int indexA;
    public int indexB;


    public void Draw(World world, float scale, float frequency, float dampingRatio,
                     Body[] CartsBodyArray,  Body[] BallsBodyArray,
                     Body[] WallsBodyArray, Body[] BlocksBodyArray, Body[] TrianglesBodyArray) {

        Body[] BodiesToConnect = new Body[2];

        String connectionToSwitch;
        int bodyInd;

        Vec2[] bodiesAnchors = new Vec2[2];

        for (int ii = 0; ii < 2; ii++) {

            if (ii == 0) {
                connectionToSwitch = connectionA;
                bodyInd = indexA;
            }
            else {
                connectionToSwitch = connectionB;
                bodyInd = indexB;
            }

            if (connectionToSwitch.equals("Carts")) {
                BodiesToConnect[ii] = CartsBodyArray[bodyInd];
            }
            else if (connectionToSwitch.equals("Balls")) {
                BodiesToConnect[ii] = BallsBodyArray[bodyInd];
            }
            else if (connectionToSwitch.equals("Walls")) {
                BodiesToConnect[ii] = WallsBodyArray[bodyInd];
            }
            else if (connectionToSwitch.equals("Blocks")) {
                BodiesToConnect[ii] = BlocksBodyArray[bodyInd];
            }
            else if (connectionToSwitch.equals("Triangles")) {
                BodiesToConnect[ii] = TrianglesBodyArray[bodyInd];
            }

            if (connectionToSwitch.equals("Walls")) {
                if (ii == 0) {
                    bodiesAnchors[ii] = jsonReader.toVec2(A).mul(scale);
                }
                else {
                    bodiesAnchors[ii] = jsonReader.toVec2(B).mul(scale);
                }
            }
            else {
                bodiesAnchors[ii] = BodiesToConnect[ii].getWorldCenter();
            }
        }

        DistanceJointDef jointDef = new DistanceJointDef();
        jointDef.initialize(BodiesToConnect[0], BodiesToConnect[1], bodiesAnchors[0], bodiesAnchors[1]);
        jointDef.frequencyHz = frequency;
        jointDef.dampingRatio = dampingRatio;
        world.createJoint(jointDef);
    }
}
