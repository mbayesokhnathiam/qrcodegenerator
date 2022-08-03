package utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class QRCode {



    public static String readQRCode(String filePath, Map hintMap) {
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(filePath);

            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(fileInputStream))));

            Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hintMap);

            fileInputStream.close();

            return qrCodeResult.getText();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String readQRCode(String filePath) {

        String charset = "UTF-8"; // or "ISO-8859-1"
        Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        return readQRCode(filePath, hintMap);
    }
}
