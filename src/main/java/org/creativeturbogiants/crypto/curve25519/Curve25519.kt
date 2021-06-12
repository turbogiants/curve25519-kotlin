@file:Suppress("unused")

package org.creativeturbogiants.crypto.curve25519

import org.creativeturbogiants.crypto.utils.byte

class Curve25519 private constructor(private val provider: Curve25519Provider) {

    companion object {

        private var INSTANCE: Curve25519? = null

        @Throws(NoSuchProviderException::class)
        fun getInstance(): Curve25519 {
            if (INSTANCE == null) {
                val kotlinCurve = KotlinCurve25519Provider()
                INSTANCE = Curve25519(kotlinCurve)
            }
            return INSTANCE as Curve25519
        }

    }

    fun isNative() = provider.isNative

    fun generateKeyPair(): Curve25519KeyPair {
        val privateKey  = provider.generatePrivateKey()
        val publicKey   = provider.generatePublicKey(privateKey)

        return Curve25519KeyPair(publicKey, privateKey)
    }

    fun calculateAgreement(publicKey: byte?, privateKey: byte?): byte {
        if (publicKey == null || privateKey == null) {
            throw IllegalArgumentException("Keys must not be null!")
        }

        if (publicKey.size != 32 || privateKey.size != 32) {
            throw IllegalArgumentException("Key must be 32 bytes!")
        }

        return provider.calculateAgreement(privateKey, publicKey)
    }

    fun calculateSignature(privateKey: byte?, message: byte) : byte {
        if (privateKey == null || privateKey.size != 32) {
            throw IllegalArgumentException("Invalid private key length")
        }

        val random = provider.getRandom(64)
        return provider.calculateSignature(random, privateKey, message)
    }

    fun verifySignature(publicKey: byte?, message: byte?, signature: byte?): Boolean {
        if (publicKey == null || publicKey.size != 32) {
            throw IllegalArgumentException("Invalid public key!")
        }

        return if (message == null || signature == null || signature.size != 64) {
            false
        } else provider.verifySignature(publicKey, message, signature)
    }

    fun calculateVrfSignature(privateKey: byte?, message: byte): byte {
        if (privateKey == null || privateKey.size != 32) {
            throw IllegalArgumentException("Invalid private key!")
        }

        val random = provider.getRandom(64)
        return provider.calculateVrfSignature(random, privateKey, message)
    }

    fun verifyVrfSignature(publicKey: byte?, message: byte?, signature: byte?): byte {
        if (publicKey == null || publicKey.size != 32) {
            throw IllegalArgumentException("Invalid public key!")
        }

        if (signature == null || message == null || signature.size != 96) {
            throw IllegalArgumentException("Invalid message or signature format")
        }

        return provider.verifyVrfSignature(publicKey, message, signature)
    }
}