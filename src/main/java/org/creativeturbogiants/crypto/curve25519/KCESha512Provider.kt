package org.creativeturbogiants.crypto.curve25519

import org.creativeturbogiants.crypto.java.Sha512
import org.creativeturbogiants.crypto.utils.byte
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class KCESha512Provider : Sha512 {

    companion object {
        private const val SHA512_SIZE = 64
    }

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

    override fun updateDigest(md: MessageDigest?, `in`: ByteArray?, length: Long) {
        md?.update(`in`, 0, length.toInt())
    }

    override fun finishDigest(out: ByteArray?, md: MessageDigest?) {
        val digest = md?.digest()

        if (digest == null || out == null) {
            return
        }

        System.arraycopy(digest, 0, out, 0, SHA512_SIZE)
    }

    override fun initDigest(): MessageDigest? {
        return try {
            MessageDigest.getInstance("SHA-512")
        } catch (e: NoSuchAlgorithmException) {
            throw AssertionError(e)
        }
    }
}