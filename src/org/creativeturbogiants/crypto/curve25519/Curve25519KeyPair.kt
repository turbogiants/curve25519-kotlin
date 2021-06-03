package org.creativeturbogiants.crypto.curve25519

import org.creativeturbogiants.crypto.utils.byte

data class Curve25519KeyPair(val publicKey: byte, val privateKey: byte) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Curve25519KeyPair

        if (!publicKey.contentEquals(other.publicKey)) return false
        if (!privateKey.contentEquals(other.privateKey)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = publicKey.contentHashCode()
        result = 31 * result + privateKey.contentHashCode()
        return result
    }
}