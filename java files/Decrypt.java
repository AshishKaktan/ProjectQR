package Module;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Decrypt{

		public static void decrypt_text(String input){
			try{
				BufferedImage img = ImageIO.read(new File(input));
				String no_of_qr="";
				for(int j=0;j<2;j++){					// getting number of qr from first 2 pixels
					int pixel1 = img.getRGB(j,0);
					Color c2 = new Color(pixel1);
					int r = c2.getRed();
					int g = c2.getGreen();
					int b = c2.getBlue();
					
					if((r&1)==1)
						no_of_qr+="1";
					else
						no_of_qr+="0";
					if((g&1)==1)
						no_of_qr+="1";
					else
						no_of_qr+="0";
					if((b&1)==1)
						no_of_qr+="1";
					else
						no_of_qr+="0";
				}

				int total_qr = Integer.parseInt(no_of_qr,2);

				Boolean [] bool_array = new Boolean[15876*total_qr];
				Boolean [][]d_matrix = new Boolean[126][126];
				Boolean is_over=false;
				
				int width = img.getWidth();
				int height = img.getHeight();
				int count=0;
				for(int i=1;i<height;i++){
					for(int j=0;j<width;j++){
						int pixel = img.getRGB(j,i);
						Color c1 = new Color(pixel);
						int r = c1.getRed();
						int g = c1.getGreen();
						int b = c1.getBlue();
						
						if((r&1)==1)
							bool_array[count]=true;
						else
							bool_array[count]=false;
						if((g&1)==1)
							bool_array[count+1]=true;
						else
							bool_array[count+1]=false;
						if((b&1)==1)
							bool_array[count+2]=true;
						else
							bool_array[count+2]=false;

						if(count+3==(15876*total_qr)){
							is_over=true;
							break;
						} 
						count+=3;
					}
					if(is_over)
						break;
				}


				count=0;

				for(int z=0;z<total_qr;z++){
					 for(int i=0;i<126;i++){
					 	for(int j=0;j<126;j++){
					 		d_matrix[i][j]=(bool_array[count++]);
					 	}
					}

					BufferedImage image = new BufferedImage(126, 126,
					BufferedImage.TYPE_INT_RGB);
					image.createGraphics();

					Graphics2D graphics = (Graphics2D) image.getGraphics();
					graphics.setColor(Color.WHITE);
					graphics.fillRect(0, 0, 126, 126);
						// Paint and save the image using the ByteMatrix
					graphics.setColor(Color.BLACK);

					for (int l = 0; l < 126; l++) {
						for (int m = 0; m < 126; m++) {
							if (d_matrix[l][m]) {
								graphics.fillRect(l, m, 1, 1);
							}
						}
					}
						
					File file = new File("QR/"+Integer.toString(z)+"Qr.png");		// d_matrix read
					ImageIO.write(image, "PNG", file);
				}
			}catch(Exception e){}

		}
}