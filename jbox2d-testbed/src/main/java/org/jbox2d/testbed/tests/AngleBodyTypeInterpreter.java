package org.jbox2d.testbed.tests;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

public class AngleBodyTypeInterpreter {

    // Default values
    float angle = 0;
    boolean dynamic = true;

    AngleBodyTypeInterpreter(Object... input) {
        // Used to determine if a float or a boolean was passed twice
        boolean argumentWasBoolean = false;
        boolean argumentWasFloat = false;

        // If only one vararg passed
        if (input.length == 1) {
            if (input[0] instanceof Float) {
                this.angle = (Float) input[0];
            }
            else if (input[0] instanceof Body) {
                this.dynamic = (Boolean) input[0];
            }
            // If its not a float and not a Body, throw an error
            else {
                throw new ClassCastException("vararg of Rect can only be float or boolean");
            }
        }
        // If two variables passed
        else if (input.length == 2) {
            // Loop over the two variables
            for (int i = 0; i<=1; i++){
                if (input[i] instanceof Float) {
                    // If a float already appeared, trow an error that there are two floats
                    if (argumentWasFloat) {
                        throw new ClassCastException("vararg of Rect can only be one float and one boolean" +
                                " but you provided two floats");
                    }
                    else {
                        this.angle = (Float) input[i];
                    }
                    // Remember that a float appeared
                    argumentWasFloat = true;
                }
                // If a boolean already appeared, trow an error that there are two booleans
                else if (input[i] instanceof Boolean) {
                    if (argumentWasBoolean) {
                        throw new ClassCastException("vararg of Rect can only be one float and one boolean" +
                                " but you provided two booleans");
                    }
                    else {
                        this.dynamic = (Boolean) input[i];
                    }
                    // Remember that a boolean appeared
                    argumentWasBoolean = true;
                }
                // If there are more than two varargs, throw an error
                else {
                    throw new ClassCastException("vararg of Rect can only be float or Body");
                }
            }
        }
    }
}
