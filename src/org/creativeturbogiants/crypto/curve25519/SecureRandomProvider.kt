package org.creativeturbogiants.crypto.curve25519

import org.creativeturbogiants.crypto.utils.byte

interface SecureRandomProvider {
    fun nextByte(output: byte)
    fun nextInt(maxValue: Int)
}
