package src;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.*;
import java.nio.*;


public class BezierPatch extends Object3D{
  private static final float ctrlpoints[][][] = new float[][][] {
    { { -1.5f, -1.5f, 0.0f }, { -0.5f, -1.5f, 3.0f },
      { 0.5f, -1.5f, 3.0f }, { 1.5f, -1.5f, 0.0f } },
    { { -1.5f, -0.5f, 1.0f }, { -0.5f, -0.5f, 3.0f },
      { 0.5f, -0.5f, 3.0f }, { 1.5f, -0.5f, 1.0f } },
    { { -1.5f, 0.5f, 2.0f }, { -0.5f, 0.5f, 3.0f },
      { 0.5f, 0.5f, 3.0f }, { 1.5f, 0.5f, 2.0f } },
    { { -1.5f, 1.5f, -1.0f }, { -0.5f, 1.5f, 2.0f },
      { 0.5f, 1.5f, 3.0f }, { 1.5f, 1.5f, 0.0f } } };
    // need float buffer instead of n-dimensional array above
  private FloatBuffer ctrlpointsBuf = 
    FloatBuffer.allocate(ctrlpoints.length*ctrlpoints[0].length
                         *ctrlpoints[0][0].length);
  {// SO copy 4x4x3 array above to float buffer
    for (int i = 0; i < ctrlpoints.length; i++) {
      // System.out.print(ctrlpoints.length+ " ");
      for (int j = 0; j < ctrlpoints[0].length; j++) {
        // System.out.println(ctrlpoints[0][0].length+" ");
        for (int k = 0; k < ctrlpoints[0][0].length; k++) {
          ctrlpointsBuf.put(ctrlpoints[i][j][k]);
          System.out.print(ctrlpoints[i][j][k] + " ");
        }
        System.out.println();
      }
    }
    // THEN rewind it before use
    ctrlpointsBuf.rewind();
  }

  public void init(GL2 gl, PMVMatrix mat, int programID){
    initCommon(mat, programID);
    gl.glEnable(GL2.GL_MAP2_VERTEX_3);
    gl.glEnable(GL2.GL_AUTO_NORMAL);
    gl.glEnable(GL2.GL_NORMALIZE);
  
  }

  public void display(GL2 gl, PMVMatrix mats, int programID){
    gl.glMap2f(GL2.GL_MAP2_VERTEX_3, 0, 1, 3, 4, 0, 1, 12, 4, ctrlpointsBuf);
    gl.glMapGrid2f(30, 0.0f, 1.0f, 30, 0.0f, 1.0f);
    gl.glEvalMesh2(GL2.GL_FILL, 0, 30, 0, 30);
  }
}