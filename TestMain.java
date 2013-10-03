import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.awt.Point;

public class TestMain{
	private static int White=255*256*256+255*256+255;
	public static void main(String args[]){
		Scanner scan=new Scanner(System.in);
		System.out.println("input source:");
		String source=scan.next();
		System.out.println("input dest:");
		String dest=scan.next();
		BufferedImage bImage=null;
		try{
			 bImage=ImageIO.read(new FileInputStream(source));	
		}catch(FileNotFoundException e){
			System.out.println(e.toString());
		}catch(IOException e){
			System.out.println(e.toString());
		}

		BufferedImage bBinaryImage=new BufferedImage(bImage.getWidth(),bImage.getHeight(),BufferedImage.TYPE_BYTE_GRAY);
		Matrix m=new Matrix(bImage.getHeight(),bImage.getWidth(),0);
		
		for(int i=0;i<bImage.getWidth();++i){
			for(int j=0;j<bImage.getHeight();++j){
				Object rgb=bImage.getRaster().getDataElements(i,j,null);
				int red=bImage.getColorModel().getRed(rgb);
				int green=bImage.getColorModel().getGreen(rgb);
				int blue=bImage.getColorModel().getBlue(rgb);
				//System.out.println(red);
				//System.out.println(green);
				//System.out.println(blue);
				int value=200;
				if(red>value && green>value && blue>value)
					m.setElem(j,i,1);
				else
					m.setElem(j,i,0);
				bBinaryImage.setRGB(i,j,0);
			}
		}

		List<Point> list=Contour.getContour(m);
		for(int i=0;i<list.size();++i){
			bBinaryImage.setRGB((int)list.get(i).getY(),(int)list.get(i).getX(),White);
		}

		String format="jpg";
		try{
			ImageIO.write(bBinaryImage,format,new File(dest));
		}catch(IOException e){
			System.out.println(e.toString());
		}	
	}
}
