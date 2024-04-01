import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

public class Main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        CascadeClassifier faceDetector = new CascadeClassifier();
        faceDetector.load("src/resources/haarcascade_frontalface_alt.xml");

        Mat image = Imgcodecs.imread("src/resources/girl.jpg");

        // Detecting faces
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image,
                faceDetections);

        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(
                    image, new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width,
                            rect.y + rect.height),
                    new Scalar(0, 255, 0));
        }

        // Saving the output image
        String filename = "Ouput.jpg";

        Imgcodecs.imwrite("D:/" + filename, image);

        System.out.print("Face Detected");

        System.exit(0);
    }

    public static Mat loadImage(String imagePath) {
        Imgcodecs imageCodecs = new Imgcodecs();
        return imageCodecs.imread(imagePath);
    }
    public static void saveImage(Mat imageMatrix, String targetPath) {
        Imgcodecs imgcodecs = new Imgcodecs();
        imgcodecs.imwrite(targetPath, imageMatrix);
    }
}