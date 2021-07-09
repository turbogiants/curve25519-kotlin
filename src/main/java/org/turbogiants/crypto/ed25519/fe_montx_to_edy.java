package org.turbogiants.crypto.ed25519;

import org.turbogiants.crypto.java.*;

/**
 * Added VRF support, so java code won't rely on native c++ jni.
 * https://github.com/signalapp/curve25519-java/pull/37
 */

public class fe_montx_to_edy {

    /**
     * y = (u - 1) / (u + 1)
     * @param y
     * @param u
     */
    static void fe_montx_to_edy(int[] y, int[] u)
    {

        int[] one = new int[10], um1 = new int[10], up1 = new int[10];

        fe_1.fe_1(one);
        fe_sub.fe_sub(um1, u, one);
        fe_add.fe_add(up1, u, one);
        fe_invert.fe_invert(up1, up1);
        fe_mul.fe_mul(y, um1, up1);
    }

}
