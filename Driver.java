import java.util.*;
import java.io.*;
import Module.Encrypt;
import Module.Decrypt;
import Module.QRDecode;
import Module.readqr;
import Module.deleteQr;
class Driver {

	public static void main(String [] args) {
		Encrypt e = new Encrypt();
		Decrypt d = new Decrypt();
		QRDecode qd = new QRDecode();
		deleteQr dq = new deleteQr();
		readqr rd = new readqr();

		String qrtext = "Hello World!";  // text to hide

		String input = "TestImage/Alyson.jpg"; // target image
		String output = "Encrypted Image/encrypted.png";	// encrypted image
		final File qrfolder = new File("QR/");
		
		e.encrypt_text(qrtext, input, output); // parameters : text , input image , output image
		d.decrypt_text(output); // parameter : encrypted image path
		rd.getfile(qrfolder); // read QR code and decodes them.

		File del = new File("Qr\\");
		dq.deleteqr(del); // deleting QR after decoding.
	}
}