package org.xiaozan.qrcode;

import java.io.File;
import java.nio.file.Path;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeEvents {

	public static void main(String[] args) throws Exception {
		// 长宽计算方式：67 + 12 * ( vendor -1)
		int width = 100;
		int height = 100;
		String format = "png";
		String content = "我喜欢你";
		// 定义二维码参数
		Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");//设置字符集编码
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//一般用M容错级别
		hints.put(EncodeHintType.MARGIN, 2);
		
		// 生成二维码
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		Path file = new File("D:/DATA/qr.png").toPath();
		MatrixToImageWriter.writeToPath(bitMatrix, format, file);

	}

}