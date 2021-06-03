package org.creativeturbogiants.crypto.ed25519;

import org.creativeturbogiants.crypto.java.sc_reduce;

import static org.creativeturbogiants.crypto.java.crypto_verify_32.crypto_verify_32;

/**
 * Added VRF support, so java code won't rely on native c++ jni.
 * https://github.com/signalapp/curve25519-java/pull/37
 */

public class sc_isreduced {

    static boolean sc_isreduced(byte[] s)
    {
        byte[] strict = new byte[64];

        System.arraycopy(s, 0, strict, 0, 32);

        sc_reduce.sc_reduce(strict);
        if (crypto_verify_32(strict, s) != 0)
            return false;
        return true;
    }
}
