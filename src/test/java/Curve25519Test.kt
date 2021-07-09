package org.turbogiants.crypto

import junit.framework.TestCase
import org.turbogiants.crypto.curve25519.Curve25519
import org.turbogiants.crypto.curve25519.Curve25519.Companion.getInstance
import org.turbogiants.crypto.curve25519.KotlinCurve25519Provider
import org.turbogiants.crypto.curve25519.NoSuchProviderException
import org.turbogiants.crypto.curve25519.VrfSignatureVerificationFailedException
import org.turbogiants.crypto.ed25519.fe_isequal
import org.junit.Assert
import org.junit.Test
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import kotlin.experimental.xor


class Curve25519Test : TestCase() {

    private val cipher = Curve25519.getInstance()

    private val aliceKeyPair = cipher.generateKeyPair()
    private val bobKeyPair = cipher.generateKeyPair()

    @Test
    fun `test shared secret key`() {
        val aliceSecretKey = aliceSecretKey()
        val bobSecretKey = bobSecretKey()

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
            val aliceSecretKey = aliceSecretKey()
            val bobSecretKey = bobSecretKey()

            Assert.assertArrayEquals(aliceSecretKey, bobSecretKey)
        }
    }

    @Test
    fun `test key gen`() {
        val curveProvider = KotlinCurve25519Provider()

        val `in` = ByteArray(32)
        var out: ByteArray? = null

        `in`[0] = 123

        for (count in 0..999) {
            out = curveProvider.generatePublicKey(`in`)
            System.arraycopy(out, 0, `in`, 0, 32)
        }

        val result = byteArrayOf(
                0xa2.toByte(), 0x3c.toByte(), 0x84.toByte(), 0x09.toByte(), 0xf2.toByte(),
                0x93.toByte(), 0xb4.toByte(), 0x42.toByte(), 0x6a.toByte(), 0xf5.toByte(),
                0xe5.toByte(), 0xe7.toByte(), 0xca.toByte(), 0xee.toByte(), 0x22.toByte(),
                0xa0.toByte(), 0x01.toByte(), 0xc7.toByte(), 0x9a.toByte(), 0xca.toByte(),
                0x1a.toByte(), 0xf2.toByte(), 0xea.toByte(), 0xcb.toByte(), 0x4d.toByte(),
                0xdd.toByte(), 0xfa.toByte(), 0x05.toByte(), 0xf8.toByte(), 0xbc.toByte(),
                0x7f.toByte(), 0x37.toByte()
        )

        Assert.assertArrayEquals(out, result)
    }

    @Test
    fun testFeIsequal1() {
        val one = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        val zero = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        Assert.assertEquals(0, fe_isequal.fe_isequal(one, zero))
    }

    @Test
    fun testFeIsequal2() {
        val one = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1)
        val zero = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1)
        assertEquals(1, fe_isequal.fe_isequal(one, zero))
    }

    @Test
    fun testFeIsequal3() {
        val one = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        val zero = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        assertEquals(1, fe_isequal.fe_isequal(one, zero))
    }

    @Test
    fun testFeIsequal4() {
        val one = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        val zero = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1)
        assertEquals(0, fe_isequal.fe_isequal(one, zero))
    }

    @Throws(NoSuchAlgorithmException::class, InvalidKeyException::class, NoSuchProviderException::class)
    fun testLargeSignatures() {
        val (publicKey, privateKey) = Curve25519.getInstance().generateKeyPair()
        val message = ByteArray(1024 * 1024)
        val signature: ByteArray = Curve25519.getInstance().calculateSignature(privateKey, message)

        assertTrue(Curve25519.getInstance().verifySignature(publicKey, message, signature))

        signature[0] = signature[0] xor 0x01
        assertFalse(Curve25519.getInstance().verifySignature(publicKey, message, signature))
    }

    @Throws(NoSuchProviderException::class, IllegalArgumentException::class)
    fun testVRFSignatures() {
        val (publicKey, privateKey) = getInstance().generateKeyPair()
        val message1 = ByteArray(1024)
        val message2 = ByteArray(512)
        val signature1: ByteArray = getInstance().calculateVrfSignature(privateKey, message1)
        val signature2: ByteArray = getInstance().calculateVrfSignature(privateKey, message2)
        try {
            val vrf_out: ByteArray = getInstance().verifyVrfSignature(publicKey, message1, signature1)
        } catch (e: VrfSignatureVerificationFailedException) {
            throw AssertionError("Sig verification failed!")
        }
        try {
            val vrf_out: ByteArray = getInstance().verifyVrfSignature(publicKey, message1, signature2)
            throw AssertionError("Sig verification succeeded!")
        } catch (e: VrfSignatureVerificationFailedException) {
        }
    }

}