/*******************************************************************************
 * Copyright (c) 2013, Daniel Murphy All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met: * Redistributions of source code must retain the
 * above copyright notice, this list of conditions and the following disclaimer. * Redistributions
 * in binary form must reproduce the above copyright notice, this list of conditions and the
 * following disclaimer in the documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/
package org.jbox2d.testbed.framework.j2d;

import java.awt.BorderLayout;
import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.swing.*;

import com.mathworks.engine.MatlabEngine;
import org.jbox2d.testbed.framework.*;
import org.jbox2d.testbed.framework.AbstractTestbedController.MouseBehavior;
import org.jbox2d.testbed.framework.AbstractTestbedController.UpdateBehavior;

import static org.jbox2d.testbed.framework.j2d.TestbedSidePanel.setNewImageLabel;

/**
 * The entry point for the testbed application
 * 
 * @author Daniel Murphy
 */
public class TestbedMain {
  // private static final Logger log = LoggerFactory.getLogger(TestbedMain.class);

    public static JFileChooser chooser = new JFileChooser();
    public static MatlabEngine eng;
    public static String jsonPath;

    private static void InitializeEngine() {
      try{
          System.out.println("Initializing Matlab Engine ...");
          eng = MatlabEngine.startMatlab();
          System.out.println("Initialization Completed");
      } catch (InterruptedException e) {
          e.printStackTrace();
      } catch (ExecutionException e) {
          e.printStackTrace();
      }
  }

    private static void InitializeDetector() {
      // messages are printed from matlab
     try {

      // Change directory and evaluate function
      String matlabFunDir = "C:\\Users\\Danielle\\Desktop\\ObjectMapper";
      eng.eval("cd '" + matlabFunDir + "'");
      eng.feval(0, "intializeDetector");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

    private static String RunMatlab(String imagePath, boolean showDetection, boolean showMapping, double th) {
        // messages are printed from matlab
        String jsonPath = "";

        try {

            // Change directory and evaluate function
            String matlabFunDir = "C:\\Users\\Danielle\\Desktop\\ObjectMapper";
            eng.eval("cd '" + matlabFunDir + "'");

            Object result = eng.feval(1, "Detect_Map", imagePath, th, showDetection, showMapping);

            jsonPath = (String) result;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return jsonPath;
    }

    public static void main(String[] args) {
    // try {
    // UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
    // } catch (Exception e) {
    // log.warn("Could not set the look and feel to nimbus. "
    // + "Hopefully you're on a mac so the window isn't ugly as crap.");
    // }

//    InitializeEngine();
//    InitializeDetector();

    TestbedModel model = new TestbedModel();
    final AbstractTestbedController controller = new TestbedController(model,
        UpdateBehavior.UPDATE_CALLED, MouseBehavior.NORMAL);
    TestPanelJ2D panel = new TestPanelJ2D(model, controller);
    model.setPanel(panel);
    model.setDebugDraw(new DebugDrawJ2D(panel, true));
    TestList.populateModel(model);

    JFrame testbed = new JFrame();
    testbed.setTitle("JBox2D Testbed");
    testbed.setLayout(new BorderLayout());
    TestbedSidePanel side = new TestbedSidePanel(model, controller);

    testbed.add(panel, "Center");

    panel.add(chooser);
    String cd = System.getProperty("user.dir");
    File defaultDir = new File(cd + "\\Images");
    chooser.setCurrentDirectory(defaultDir);
    chooser.showOpenDialog(null);

    setNewJsonPath(false, false, 98);
    setNewImageLabel();
    jsonPath = "C:\\Users\\Danielle\\Downloads\\jbox2d\\Images\\outputExp.json";

    testbed.add(new JScrollPane(side), "East");
    testbed.pack();
    testbed.setVisible(true);
    testbed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    System.out.println(System.getProperty("java.home"));

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        controller.playTest(0);
        controller.start();
      }
    });
  }

    public static void setNewJsonPath(boolean showDetection, boolean showMapping, int threshold){
//        jsonPath = RunMatlab(getImagePath(), showDetection, showMapping, (double)threshold/100);
        jsonPath = "C:\\Users\\Danielle\\Downloads\\jbox2d\\Images\\outputExp.json";
  }

    public static String getImagePath(){
//        return chooser.getSelectedFile().getPath();
        return "C:\\Users\\Danielle\\Downloads\\jbox2d\\Images\\Real_301.jpg";
    }

    public static JFileChooser getChooser(){
        return chooser;
    }

    public static String getJsonPath(){
        return jsonPath;
    }
}
