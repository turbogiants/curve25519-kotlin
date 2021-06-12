package org.creativeturbogiants.crypto

import org.creativeturbogiants.crypto.curve25519.Curve25519
import org.creativeturbogiants.crypto.utils.bytesToHex

fun main(args: Array<String>) {

    val cipher = Curve25519.getInstance()

    val keyPair1 = cipher.generateKeyPair()

    val keyPair2 = cipher.generateKeyPair()

    val secret1 = cipher.calculateAgreement(keyPair1.publicKey, keyPair2.privateKey)

    val secret2 = cipher.calculateAgreement(keyPair2.publicKey, keyPair1.privateKey)

    println("---------------------------------------------------------------------------------------------")
    println("Alice")
    println("Public key          = ${bytesToHex(keyPair1.publicKey)}")
    println("Private key         = ${bytesToHex(keyPair1.privateKey)}")

    println("---------------------------------------------------------------------------------------------")
    println("Bob")
    println("public key          = ${bytesToHex(keyPair2.publicKey)}")
    println("private key         = ${bytesToHex(keyPair2.privateKey)}")

    println("---------------------------------------------------------------------------------------------")
    println("Alice shared key    = ${bytesToHex(secret1)}")
    println("Bob shared key      = ${bytesToHex(secret2)}")

    println("---------------------------------------------------------------------------------------------")
    val signature = cipher.calculateSignature(
            keyPair1.privateKey, "test".toByteArray()
    )

    println("Signature          : ${bytesToHex(signature)}")

    val isValidSignature = cipher.verifySignature(
            keyPair1.publicKey, "test".toByteArray(), signature
    )

    println("Valid              : $isValidSignature")

    val sig = bytesToHex(signature)
    println("                   : ${sig.length} bit symmetric cipher")
    println("                   : ${signature.size} bytes")

}


