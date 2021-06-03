package org.creativeturbogiants.crypto.ed25519;

import org.creativeturbogiants.crypto.java.*;

/**
 * Added VRF support, so java code won't rely on native c++ jni.
 * https://github.com/signalapp/curve25519-java/pull/37
 */

public class fe_mont_rhs {

    static void fe_mont_rhs(int[] v2, int[] u) {
        int[] A = new int[10], one = new int[10];
        int[] u2 = new int[10], Au = new int[10], inner = new int[10];

        fe_1.fe_1(one);
        fe_0.fe_0(A);
        A[0] = 486662;

        fe_sq.fe_sq(u2, u);
        fe_mul.fe_mul(Au, A, u);
        fe_add.fe_add(inner, u2, Au);
        fe_add.fe_add(inner, inner, one);
        fe_mul.fe_mul(v2, u, inner);
    }

}
