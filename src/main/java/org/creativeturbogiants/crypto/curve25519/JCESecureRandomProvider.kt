package org.creativeturbogiants.crypto.curve25519

import org.creativeturbogiants.crypto.utils.byte
import java.security.SecureRandom

class JCESecureRandomProvider : SecureRandomProvider {

    override fun nextByte(output: byte) {
        SecureRandom().nextBytes(output)
    }

    override fun nextInt(maxValue: Int) {
        SecureRandom().nextInt(maxValue)
    }
}