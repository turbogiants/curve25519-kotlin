package org.creativeturbogiants.crypto.java

import org.creativeturbogiants.crypto.utils.byte

interface Sha512 {
    fun calculateDigest(out: byte, `in`: byte, length: Long)
}