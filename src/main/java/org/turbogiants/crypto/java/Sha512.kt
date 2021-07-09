package org.turbogiants.crypto.java

import org.turbogiants.crypto.utils.byte

interface Sha512 {
    fun calculateDigest(out: byte, `in`: byte, length: Long)
}