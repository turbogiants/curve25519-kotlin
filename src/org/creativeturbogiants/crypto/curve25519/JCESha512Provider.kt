package org.creativeturbogiants.crypto.curve25519

import org.creativeturbogiants.crypto.java.Sha512
import org.creativeturbogiants.crypto.utils.byte
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class JCESha512Provider : Sha512 {

    override fun calculateDigest(out: byte, `in`: byte, length: Long) {
        try {
            val messageDigest = MessageDigest.getInstance("SHA-512")
            messageDigest.update(`in`, 0, length.toInt())
            val digest = messageDigest.digest()
            System.arraycopy(digest, 0, out, 0, digest.size)
        } catch (e: NoSuchAlgorithmException) {
            throw AssertionError(e)
        }
    }
}