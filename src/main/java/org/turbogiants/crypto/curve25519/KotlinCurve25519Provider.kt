package org.turbogiants.crypto.curve25519

class KotlinCurve25519Provider constructor(
        kceSha512Provider: KCESha512Provider = KCESha512Provider(),
        kceSecureRandomProvider: KCESecureRandomProvider = KCESecureRandomProvider()
) : BaseKotlinCurve25519Provider(kceSha512Provider, kceSecureRandomProvider) {

    override val isNative: Boolean
        get() = false
}

