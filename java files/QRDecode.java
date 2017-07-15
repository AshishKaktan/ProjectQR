package Module;
import java.io.FileInputStream;
import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class QRDecode {

	public static void decode(String path) {
		Result result = null;
		BinaryBitmap binaryBitmap;

		try {

			binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(path)))));
			result = new MultiFormatReader().decode(binaryBitmap);
			System.out.print(result.getText());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}