import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);


        Rect rectCrop = null;
        Tesseract tesseract = new Tesseract();
        CascadeClassifier faceDetector = new CascadeClassifier();


        faceDetector.load("src/resources/haarcascade_russian_plate_number.xml");
        Mat image = Imgcodecs.imread("src/resources/bmw.jpg");
        CascadeClassifier faceDetector = new CascadeClassifier();
        faceDetector.load("src/resources/haarcascade_frontalface_alt.xml");

        Mat image = Imgcodecs.imread("src/resources/girl.jpg")

        // Detecting faces
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);



        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 0, 255), 4);
            rectCrop = new Rect(rect.x, rect.y, rect.width, rect.height);
        }

        String filename = "plate_numbe.jpg";

        Mat markedImage = new Mat(image,rectCrop);

        Imgcodecs.imwrite("C:\\Users\\Nikita\\IdeaProjects\\openCV\\src\\resources/" + filename, markedImage);


        try {

            tesseract.setDatapath("D:/Tess4J/tessdata");

            String text = tesseract.doOCR(new File("src/resources/Ouput.jpg"));

            System.out.print(text);
        }
        catch (TesseractException e) {
            e.printStackTrace();
        }

        System.exit(1);
    }
}