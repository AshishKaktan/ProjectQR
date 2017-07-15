# ProjectQR

## Classes :
1. [Encrypt](#encrypt)
1. [Decrypt](decrypt)
1. [QRDecode](qrdecode)
1. [deleteQr](deleteqr)
1. [readqr](readqr)

## Functions :
### Encrypt
- `encrypt_text(String qrCodeText,String input,String Output)` 

		qrCodeText : Text to hide.
        input : Path of input image to hide text in.
        Ouput : Path of output Image.

### Decrypt
- `decrypt_text(String input)` 

        input : Path of input image to decrypt.

### QRDecode
- `decode(String input)` 
  
        input : Path of QR to decode.
### deleteQr
- `deleteqr(File file)` 
  
        file : Path of folder to delete all QR files in it.
        
### readqr
- `getfile(File file)` 
  
        file : Path of folder to all QR.
