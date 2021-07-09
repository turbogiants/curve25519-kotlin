package org.turbogiants.crypto.curve25519

import org.turbogiants.crypto.utils.byte

interface SecureRandomProvider {
    fun nextByte(output: byte)
    fun nextInt(maxValue: Int)
}
