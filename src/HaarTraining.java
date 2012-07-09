import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import static com.googlecode.javacv.cpp.opencv_objdetect.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;

public class HaarTraining{
	
	public static final String XML_FILE = "haar.xml";
	
	public static void main(String[] args){
		
		IplImage img = cvLoadImage("test1.bmp");
		//cvShowImage("img", img);
		
		//Loader.load(opencv_objdetect.class); 
		detect(img);
	
	}	
	
	public static void detect(IplImage src){
		
	IplImage gray = cvCreateImage(cvGetSize(src), 8, 1);
		
		CvHaarClassifierCascade cascade = new CvHaarClassifierCascade(
            cvLoad(XML_FILE));
	
	CvMemStorage storage = CvMemStorage.create();
	
	cvCvtColor(src, gray, CV_BGR2GRAY );
	
	CvSeq sign = cvHaarDetectObjects(
			gray,
			cascade,
			storage,
			1.5,
			5,
			CV_HAAR_DO_CANNY_PRUNING);
	
	cvClearMemStorage(storage);
	
	int total_objects = sign.total();
	if(total_objects > 0){
		System.out.println("detected " + total_objects + " objects");
	}
	else{
		System.out.println("not detected");	
	}
	
	for(int i = 0; i < total_objects; i++){
		
		CvRect r = new CvRect(cvGetSeqElem(sign, i));
		cvRectangle (
				src,
				cvPoint(r.x(), r.y()),
				cvPoint(r.width() + r.x(), r.height() + r.y()),
				CvScalar.RED,
				2,
				CV_AA,
				0
				);
	}
	
	cvShowImage("Result", src);
	cvWaitKey(0);
	}			
	
		
}