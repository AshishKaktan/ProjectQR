# ProjectQR

## Classes :
1. [Encrypt]()
1. [Decrypt]()
1. [QRDecode]()
1. [deleteQr]()
1. [readqr]()

## Functions :
### Encrypt
- `encrypt_text(String qrCodeText,String input,String Output)` 

		**qrCodeText** : Text to hide.
        **input** : Path of input image to hide text in.
        **Ouput** : Path of output Image.

### Decrypt
- `decrypt_text(String input)` 

        **input** : Path of input image to decrypt.

### QRDecode
- `decode(String input)` 
  
        **input** : Path of QR to decode.
### deleteQr
- `deleteqr(File file)` 
  
        **file** : Path of folder to delete all QR files in it.
        
### readqr
- `getfile(File file)` 
  
        **file** : Path of folder to all QR.
