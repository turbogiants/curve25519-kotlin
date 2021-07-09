package org.turbogiants.crypto.curve25519

import org.turbogiants.crypto.utils.byte
import java.security.SecureRandom

class KCESecureRandomProvider : SecureRandomProvider {

    override fun nextByte(output: byte) {
        SecureRandom().nextBytes(output)
    }

    override fun nextInt(maxValue: Int) {
        SecureRandom().nextInt(maxValue)
    }
}