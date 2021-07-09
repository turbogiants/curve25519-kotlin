package org.turbogiants.crypto

import org.turbogiants.crypto.curve25519.Curve25519
import org.turbogiants.crypto.utils.bytesToHex

fun main(args: Array<String>) {

    val cipher = Curve25519.getInstance()

    val keyPair1 = cipher.generateKeyPair()

    val keyPair2 = cipher.generateKeyPair()

    val aliceSecret = cipher.calculateAgreement(keyPair1.publicKey, keyPair2.privateKey)

    val bobSecret = cipher.calculateAgreement(keyPair2.publicKey, keyPair1.privateKey)


    println("Public key          = ${bytesToHex(keyPair1.publicKey)}")
    println("Private key         = ${bytesToHex(keyPair1.privateKey)}")

    println("---------------------------------------------------------------------------------------------")
    println("Bob")
    println("public key          = ${bytesToHex(keyPair2.publicKey)}")
    println("private key         = ${bytesToHex(keyPair2.privateKey)}")

    println("---------------------------------------------------------------------------------------------")
    println("Alice shared key    = ${bytesToHex(aliceSecret)}")
    println("Bob shared key      = ${bytesToHex(bobSecret)}")

    println("---------------------------------------------------------------------------------------------")

    val message = "Hi, Bob!".toByteArray()

    val signature = cipher.calculateSignature(keyPair1.privateKey, message)

    println("Signature          : ${bytesToHex(signature)}")

    val isValidSignature = cipher.verifySignature(keyPair1.publicKey, message, signature)

    println("Valid              : $isValidSignature")

    val sig = bytesToHex(signature)
    println("\t\t\t\t: ${sig.length} bit symmetric cipher")
    println("                   : ${signature.size} bytes")

}


