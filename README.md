
# curve25519-kotlin
  
A synthetic Kotlin implementation of Curve25519. A fast Elliptic-curve Diffie-Hellman function, including x25519 key pairs, shared secrets, x25519 signatures, and the XVEdDSA VRF signature algorithm backed by Java code. Based on Signal [curve25519-java](https://github.com/signalapp/curve25519-java).  

This implementation doesn't use a native C curve25519-donna and only relies on pure Java code of Signal. Thus, this is a downgrade version of Signal [curve25519-java](https://github.com/signalapp/curve25519-java) in terms of dynamic curve provider.  
  
### Obtaining an instance  
The caller initialize a singleton instance of Curve25519.  
  
```  
val cipher: Curve25519          = Curve25519.getInstance();  
```  
  
### Generating a Curve25519 keypair:  
  
```  
val keyPair: Curve25519KeyPair  = Curve25519.getInstance().generateKeyPair();  
```  
  
### Calculating a shared secret:  
  
```  
val cipher: Curve25519          = Curve25519.getInstance();  
val sharedSecret: ByteArray     = cipher.calculateAgreement(publicKey, privateKey);  
```  
  
### Calculating a signature:  
  
```  
val cipher: Curve25519          = Curve25519.getInstance();  
val signature: ByteArray        = cipher.calculateSignature(privateKey, message);  
```  
  
### Verifying a signature:  
  
```  
val cipher: Curve25519          = Curve25519.getInstance();  
val validSignature: Boolean     = cipher.verifySignature(publicKey, message, signature);  
```  
  
### Ported Implementation
Additional implementation and improvements originated from signal [pull request](https://github.com/signalapp/curve25519-java/pulls) were added in this codebase.  
| Implementation                                                      | Author                                                                                | Ported To |
|---------------------------------------------------------------------|---------------------------------------------------------------------------------------|-----------|
| Edwards-curve Digital Signature Algorithm                           | [Java VRF support by k-s-t-i · Pull Request #37](https://github.com/signalapp/curve25519-java/pull/37/commits/10f25dfa3cd6a5c4783b2b5a2f2f842fb0c72ca6) | from C to Java

  
## Todo  
- [ ] Add curve25519-donna C implementation [JNI]  **(Optional)**
  
## Credits
This implementation is derived from the original work of Open Whisper System Signal [curve25519-java](https://github.com/signalapp/curve25519-java)

## Paper
- [Curve25519: new Diffie-Hellman speed records](https://cr.yp.to/ecdh/curve25519-20060209.pdf) - Daniel J. Bernstein, 2006
- [Ed25519 signatures from Curve25519 keys](https://moderncrypto.org/mail-archive/curves/2014/000205.html) - Trevor Perrin, 2014
- [The XEdDSA and VXEdDSA Signature Schemes](https://signal.org/docs/specifications/xeddsa) - Trevor Perrin, 2016

## License  
  
Copyright 2015 Open Whisper Systems 
<br>
Copyright 2021 Creative Turbo Giants  
  
Licensed under the GPLv3: http://www.gnu.org/licenses/gpl-3.0.html
