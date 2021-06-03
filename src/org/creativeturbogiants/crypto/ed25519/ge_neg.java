package org.creativeturbogiants.crypto.ed25519;

import org.creativeturbogiants.crypto.java.ge_p3;

import static org.creativeturbogiants.crypto.java.fe_copy.fe_copy;
import static org.creativeturbogiants.crypto.java.fe_neg.fe_neg;

/**
 * Added VRF support, so java code won't rely on native c++ jni.
 * https://github.com/signalapp/curve25519-java/pull/37
 */

public class ge_neg {

    /**
     * return r = -p
     * @param r
     * @param p
     */
    public static void ge_neg(ge_p3 r, ge_p3 p)
    {
        fe_neg(r.X, p.X);
        fe_copy(r.Y, p.Y);
        fe_copy(r.Z, p.Z);
        fe_neg(r.T, p.T);
    }
}
