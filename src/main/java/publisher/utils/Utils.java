package publisher.utils;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.opencv.core.Mat;
import publisher.ProducerVideoMessages;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Provide general purpose methods for handling OpenCV-JavaFX data conversion.
 * Moreover, expose some "low level" methods for matching few JavaFX behavior.
 *
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @author <a href="http://max-z.de">Maximilian Zuleger</a>
 * @version 1.0 (2016-09-17)
 * @since 1.0
 */
public final class Utils {
    /**
     * This method is for Buffered Image to Byte[] conversion.
     * Calling Kafka producer by sending Byte[] and topic name.
     */
    public static void imageToProducer(BufferedImage image, int i, String topicName) throws IOException {
        //  ImageIO.write(image, "jpg",new File("C:\\Users\\Dell\\Desktop\\DataFilesForTest\\OpenCV\\image"+i+".jpg"));
        ByteArrayOutputStream bst = new ByteArrayOutputStream(1000);
        boolean result = ImageIO.write(image, "jpg", bst);
        if (!result) {
            System.out.println("error !");
        } else {
            ProducerVideoMessages.sendImages(bst.toByteArray(), topicName);
            System.out.println("test images : " + image);
            bst.flush();
        }
    }

    /**
     * This method has been modified .
     * This method serves two things :
     * 1) returing JavaFX which will be rendered by using ImageView .
     * 2) Calling sending Buffered image for converion in the form of Byte[] and then produce them to Kafka Topic.
     */
    public static Image mat2Image(Mat frame, int i, String topicName) {
        try {
            BufferedImage image = matToBufferedImage(frame, topicName);
            imageToProducer(image, i, topicName);
            return SwingFXUtils.toFXImage(image, null);

        } catch (Exception e) {
            System.err.println("Cannot convert the Mat object: " + e);
            return null;
        }
    }

    /**
     * Generic method for putting element running on a non-JavaFX thread on the
     * JavaFX thread, to properly update the UI
     *
     * @param property a {@link ObjectProperty}
     * @param value    the value to set for the given {@link ObjectProperty}
     */
    public static <T> void onFXThread(final ObjectProperty<T> property, final T value) {
        Platform.runLater(new Runnable() {
            public void run() {
                property.set(value);
            }
        });
    }

    /**
     * This method has been modified .
     * Conversion of Mat to buffered Image.
     */

    private static BufferedImage matToBufferedImage(Mat original, String topicName) throws IOException {

        BufferedImage image = null;
        int width = original.width(), height = original.height(), channels = original.channels();
        byte[] sourcePixels = new byte[width * height * channels];
        original.get(0, 0, sourcePixels);
        image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        //  printArray(sourcePixels);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
        return image;
    }
}