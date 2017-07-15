package Module;
import java.util.*;
import java.io.*;

public class deleteQr {
	public static void deleteqr(File file){
		String[] myFiles;
		if (file.isDirectory()) {
			myFiles = file.list();
			for (int i = 0; i < myFiles.length; i++) {
				File myFile = new File(file, myFiles[i]);
				myFile.delete();
			}
		}
	}
	public static void main(String [] args) {
		File file = new File("Qr\\");
		deleteqr(file);
	}
}