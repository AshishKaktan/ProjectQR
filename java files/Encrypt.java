package Module;
import java.awt.Color; 
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Encrypt {

	static int size=126;
	static BufferedImage input_img,output_img;
	static int counter_x=1,counter_y=1;
    static Boolean[] Data_Matrix(String qrCodeText) throws WriterException, IOException {

		Hashtable hintMap = new Hashtable();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();

		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText,BarcodeFormat.QR_CODE, size, size, hintMap);

		int width = byteMatrix.getWidth(),count=0;
		Boolean[] data_matrix = new Boolean[width*width];

		for(int i=0;i<width;i++){
			for(int j=0;j<width;j++){
				data_matrix[count++] = byteMatrix.get(i,j);
			}
		}
		return data_matrix;
	}


	static String [] substring_divide (String qrCodeText){

		int length = qrCodeText.length();
		int no_of_qr = qrCodeText.length()/1200;
		
		if(qrCodeText.length()%1200!=0)
			no_of_qr+=1;

		String [] sub_string = new String[no_of_qr];
		
		for(int i=0;i<no_of_qr;i++){
			if((i!=no_of_qr-1))
				sub_string[i]=(qrCodeText.substring(i*1200,(i+1)*1200));
			else
				sub_string[i]=(qrCodeText.substring(i*1200,length));
		}

		return sub_string;
	}


	public static void encrypt_text(String qrCodeText,String input,String Output){
				
			try{

				BufferedImage input_img = ImageIO.read(new File(input));
				BufferedImage output_img = input_img;
				int width = input_img.getWidth();
				int height = input_img.getHeight();
				int count=0;
				Boolean is_over=false;
				String [] string_matrix = substring_divide(qrCodeText);
				Boolean [] bool_array = new Boolean[15876*string_matrix.length];
				Boolean [][] d_matrix = new Boolean [126][126];
				int total_qr = string_matrix.length;
				String total_bin = Integer.toBinaryString(0x100|total_qr).substring(3);

				for(int i=0;i<string_matrix.length;i++){
					Boolean [] data_matrix = Data_Matrix(string_matrix[i]);
					for(int j=0;j<data_matrix.length;j++){
						bool_array[count++]=data_matrix[j];
					}
				}
				
				count=0;
				
				// Setting no of qr in 1st and 2nd pixel of the image
					count=0;

					for(int j=0;j<2;j++){
						int pixel1 = output_img.getRGB(j,0);
						Color c2 = new Color(pixel1);
						int red1 = c2.getRed();
						int blue1 = c2.getBlue();
						int green1 = c2.getGreen();
						if(total_bin.charAt(count++)=='0')
							red1 = red1 & ~(1<<0);
						else
							red1 = red1 |1<<0;
						if(total_bin.charAt(count++)=='0')
							green1 = green1 & ~(1<<0);
						else
							green1 = green1 |1<<0;
						if(total_bin.charAt(count++)=='0')
							blue1 = blue1 & ~(1<<0);
						else
							blue1 = blue1 |1<<0;

						Color c3 = new Color(red1,green1,blue1);
						output_img.setRGB(j,0,c3.getRGB());
					}	
					
				
				count=0;
				for(int i=1;i<height;i++){
					for(int j=0;j<width;j++){
						int pixel = output_img.getRGB(j,i);
						Color c = new Color(pixel);
						int red = c.getRed();
						int blue = c.getBlue();
						int green = c.getGreen();

						if(!bool_array[count]){
							red =red & ~ (1<<0); 			// clearing 0th bit 
						}
						else
							red = red |(1<<0); 				// setting 0th bit
						
						if(!bool_array[count+1]){
							
							green =green & ~ (1<<0); 			// clearing 0th bit
						}
						else
							green =green |(1<<0); 				// setting 0th bit

						if(!bool_array[count+2]){
							blue = blue & ~ (1<<0); 			// clearing 0th bit
						}
						else
							blue =blue |(1<<0); 				// setting 0th bit

						Color c1 = new Color(red,green,blue);
						output_img.setRGB(j,i,c1.getRGB());


						if((count+3)==bool_array.length){
							//System.out.println(count+3);
							is_over=true;
							break;
						}

						count+=3;
					}
					if(is_over)
						break;
				}

				//System.out.println(bool_array.length);
			
				File save = new File(Output);
				ImageIO.write(output_img,"PNG",save);
				
			}
			catch(Exception e){
				System.out.println(e);
			}
	}

}	