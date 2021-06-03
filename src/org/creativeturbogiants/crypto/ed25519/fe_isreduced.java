package org.creativeturbogiants.crypto.ed25519;

import org.creativeturbogiants.crypto.java.fe_frombytes;
import org.creativeturbogiants.crypto.java.fe_tobytes;

import static org.creativeturbogiants.crypto.java.crypto_verify_32.crypto_verify_32;

/**
 * Added VRF support, so java code won't rely on native c++ jni.
 * https://github.com/signalapp/curve25519-java/pull/37
 */

public class fe_isreduced {

    /**
     *
     * @param s
     * @return true if fe_isrecuded
     *         false otherwise
     */
    static boolean fe_isreduced(byte[] s){
        int[] f = new int[10];
        byte[] strict = new byte[32];

        fe_frombytes.fe_frombytes(f, s);
        fe_tobytes.fe_tobytes(strict, f);
        if (crypto_verify_32(strict, s) != 0)
            return false;
        return true;
    }
}
