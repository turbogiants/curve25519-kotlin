package org.creativeturbogiants.crypto.curve25519

class NoSuchProviderException : RuntimeException {
    private val nested: Throwable?

    constructor(e: Throwable?) {
        nested = e
    }

    constructor(type: String?) : super(type) {
        nested = null
    }
}