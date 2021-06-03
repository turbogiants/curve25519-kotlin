package org.creativeturbogiants.crypto

import org.creativeturbogiants.crypto.curve25519.Curve25519
import org.junit.Assert
import org.junit.Test

class Curve25519Test {

    private val cipher = Curve25519.getInstance()

    private val aliceKeyPair    = cipher.generateKeyPair()
    private val bobKeyPair      = cipher.generateKeyPair()

    @Test
    fun `test shared secret key`() {
        val aliceSecretKey      = aliceSecretKey()
        val bobSecretKey        = bobSecretKey()

        Assert.assertArrayEquals(aliceSecretKey, bobSecretKey)
    }

    @Test
    fun `test signature`() {
        val message = "hello world".toByteArray()

        val signature = cipher.calculateSignature(aliceKeyPair.privateKey, message)

        Assert.assertTrue(cipher.verifySignature(aliceKeyPair.publicKey, message, signature))
    }

    private fun aliceSecretKey(): ByteArray {
        return cipher.calculateAgreement(aliceKeyPair.publicKey, bobKeyPair.privateKey)
    }

    private fun bobSecretKey(): ByteArray {
        return cipher.calculateAgreement(bobKeyPair.publicKey, aliceKeyPair.privateKey)
    }

    @Test
    fun `test random agreement`() {
        for (i in 0..100) {
            val aliceSecretKey      = aliceSecretKey()
            val bobSecretKey        = bobSecretKey()

            Assert.assertArrayEquals(aliceSecretKey, bobSecretKey)
        }
    }
}