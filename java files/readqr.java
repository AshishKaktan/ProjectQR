package Module;
import java.util.*;
import java.io.*;
import Module.QRDecode;
public class readqr {
	public static void getfile(final File folder) {
		QRDecode qd = new QRDecode();
		for (final File fileEntry : folder.listFiles()) {
			String path = fileEntry.getName();
			qd.decode("Qr/"+path);
		}
	}
}