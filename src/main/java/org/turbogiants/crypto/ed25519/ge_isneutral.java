package org.turbogiants.crypto.ed25519;

import org.turbogiants.crypto.java.fe_0;
import org.turbogiants.crypto.java.ge_p3;

/**
 * Added VRF support, so java code won't rely on native c++ jni.
 * https://github.com/signalapp/curve25519-java/pull/37
 */

public class ge_isneutral {

    /**
     * Check if p neutral point
     * @param p
     * @return  1 if p neutral point
     *          0 otherwise
     */
    public static int ge_isneutral(ge_p3 p)
    {
        int[] zero = new int[10];
        fe_0.fe_0(zero);

        return (fe_isequal.fe_isequal(p.X, zero) & fe_isequal.fe_isequal(p.Y, p.Z));
    }
}
