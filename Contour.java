import java.util.List;
import java.awt.Point;
import java.util.ArrayList;

public class Contour{
	
	public static List<Point> getContour(Matrix mat){

		List<Point> contour=new ArrayList<Point>();
		//scan every row and column
		//boundary
		if(mat.getRow()==1 || mat.getCol()==1)
			onlyOne(mat,contour);
		else if(mat.getRow()>1 && mat.getCol()>1)
			hasMuch(mat,contour);
		return contour;
	}

	private static void onlyOne(Matrix mat,List<Point> list){
		for(int i=0;i<mat.getRow();++i){
			for(int j=0;j<mat.getCol();++j){
				if(mat.getElem(i,j)!=0)
					list.add(new Point(i,j));
			}
		}	
	}

	private static void hasMuch(Matrix mat,List<Point> contour){
		for(int i=0;i<mat.getRow();++i){
			//internal
			for(int j=0;j<mat.getCol();++j){
				if(mat.getElem(i,j)==0)
					continue;
				if(j==0){
					if(mat.getElem(i,j+1)==0){
						contour.add(new Point(i,j));
					}
					else if(i==0){
						if( mat.getElem(i+1,j)==0)
							contour.add(new Point(i,j));
					}
					else if(i==mat.getRow()-1){
						if(mat.getElem(i-1,j)==0)
							contour.add(new Point(i,j));
					}
				}
				else if(j==mat.getCol()-1){
					if(mat.getElem(i,j-1)==0){
						contour.add(new Point(i,j));
					}
					else if(i==0){
						if(mat.getElem(i+1,j)==0)
							contour.add(new Point(i,j));
						}
					else if(i==mat.getRow()-1){
						if(mat.getElem(i-1,j)==0)
							contour.add(new Point(i,j));
						}
				}
				else if(mat.getElem(i,j-1)==0 || mat.getElem(i,j+1)==0)
					contour.add(new Point(i,j));
				else if(i==0){
					if(mat.getElem(i+1,j)==0)
						contour.add(new Point(i,j));
					}
				else if(i==mat.getRow()-1){
					if(mat.getElem(i-1,j)==0)
						contour.add(new Point(i,j));
					}
				else if(mat.getElem(i-1,j)==0 || mat.getElem(i+1,j)==0)
					contour.add(new Point(i,j));
			}
		}
	}
}
