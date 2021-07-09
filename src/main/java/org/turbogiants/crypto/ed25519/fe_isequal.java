package org.turbogiants.crypto.ed25519;

import org.turbogiants.crypto.java.fe_sub;

import static org.turbogiants.crypto.java.fe_isnonzero.fe_isnonzero;

/**
 * Added VRF support, so java code won't rely on native c++ jni.
 * https://github.com/signalapp/curve25519-java/pull/37
 */

public class fe_isequal {

    /**
     *
     * @param f
     * @param g
     * @return 1 if f==g
     *         0 if f!= g
     */

    public static int fe_isequal(int[] f, int[] g)
    {
        int[] h = new int[10];
        fe_sub.fe_sub(h, f, g);
        return (1 ^ (1 & (fe_isnonzero(h) >> 8)));
    }
}
