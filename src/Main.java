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
        tesseract.setLanguage("rus");
        tesseract.setTessVariable("tessedit_char_whitelist","0123456789АВЕКМНОРСТУХ");

        faceDetector.load("src/resources/haarcascade_russian_plate_number.xml");
        Mat image = Imgcodecs.imread("src/resources/bmw.jpg");

        // Detecting faces
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);


        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 0, 255), 4);
            rectCrop = new Rect(rect.x, rect.y, rect.width, rect.height);
        }

        String filename = "plate_number.jpg";

        Mat markedImage = new Mat(image,rectCrop);

        Imgcodecs.imwrite("src/resources/" + filename, markedImage);


        try {

            tesseract.setDatapath("Tess4J/tessdata");

            String text = tesseract.doOCR(new File("src/resources/plate_number.jpg"));

            System.out.print(text);
        }
        catch (TesseractException e) {
            System.out.print("Ошибка");
        }


        System.exit(1);
    }
}