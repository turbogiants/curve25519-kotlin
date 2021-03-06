
# curve25519-kotlin
  
A synthetic Kotlin implementation of Curve25519. A fast Elliptic Curve - Diffie-Hellman function, including x25519 key pairs, shared secrets, x25519 signatures, and the XVEdDSA VRF signature algorithm backed by Java code. Based on Signal [curve25519-java](https://github.com/signalapp/curve25519-java).  

This implementation doesn't use a native C curve25519-donna and only relies on pure Java code of Signal. Thus, this is a downgrade version of Signal [curve25519-java](https://github.com/signalapp/curve25519-java) in terms of dynamic curve provider.  
  
### Obtaining an instance  
The caller initialize a singleton instance of Curve25519.  
   
<img src="previews/curve25519.png" alt="">
 
### Generating a Curve25519 keypair:  
  
<img src="previews/keypair.png" alt="">
  
### Calculating a shared secret:  
  
<img src="previews/shared_secret.png" alt="">
  
### Calculating a signature:  
  
<img src="previews/calculate_signature.png" alt="">
  
### Verifying a signature:  
  
<img src="previews/verify_signature.png" alt="">
  
### Ported Implementation
Additional implementation and improvements originated from this [pull request](https://github.com/signalapp/curve25519-java/pulls) were added in this codebase.  
| Implementation                                                      | Author                                                                                | Ported To |
|---------------------------------------------------------------------|---------------------------------------------------------------------------------------|-----------|
| Edwards-curve Digital Signature Algorithm                           | [Java VRF support by k-s-t-i · Pull Request #37](https://github.com/signalapp/curve25519-java/pull/37/commits/10f25dfa3cd6a5c4783b2b5a2f2f842fb0c72ca6) | from C to Java

  
## Todo  
- [ ] Add curve25519-donna C implementation [JNI]  **(Optional)**
- [ ] Add option to use BouncyCastle's Curve25519 DJB implementation.
- [ ] Add native C jni implmentation of signal.

## Credits
This implementation is derived from the original work of Open Whisper System Signal [curve25519-java](https://github.com/signalapp/curve25519-java)

## Works
- [Curve25519: new Diffie-Hellman speed records](https://cr.yp.to/ecdh/curve25519-20060209.pdf) - Daniel J. Bernstein, 2006
- [Ed25519 signatures from Curve25519 keys](https://moderncrypto.org/mail-archive/curves/2014/000205.html) - Trevor Perrin, 2014
- [The XEdDSA and VXEdDSA Signature Schemes](https://signal.org/docs/specifications/xeddsa) - Trevor Perrin, 2016

## License  

``` 
Copyright (C) 2015 Open Whisper Systems
Copyright (C) 2021 Creative Turbo Giants

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or 
any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
```
Licensed under the GPLv3: http://www.gnu.org/licenses/gpl-3.0.html
