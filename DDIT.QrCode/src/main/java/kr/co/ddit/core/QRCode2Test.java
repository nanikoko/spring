package kr.co.ddit.core;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCode2Test {

	public static void main(String[] args) throws WriterException, IOException {

		// QrCode 생성될 파일 경로 및 파일명
		String filePath = "/Temp/Crunchify.com-QRCode2.png";

		// 큐알코드 인스턴스 생성
		QRCodeWriter qrCodeWriter = new QRCodeWriter(); // QR 코드
		// 바코드 인스턴스 생성
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter(); // 바코드

		// 생성될 큐알코드 경로 및 파일명
		OutputStream out = new FileOutputStream("/Temp/Crunchify.com-QRCode2.png");
		// 생성될 바크드 경로 및 파일명
		OutputStream TEST = new FileOutputStream("/Temp/Crunchify.com-QRCode2.JPG");

		// 생성될 코드값에 주입할 데이터
		String text = "http://192.168.34.56/";
		// 한글데이터가 있을 경우 캐릭터셋 설정을 한다.
		text = new String(text.getBytes("UTF-8"), "ISO-8859-1"); 
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 500, 200);

		// 
		BitMatrix bitMatrixs = multiFormatWriter.encode(text, BarcodeFormat.CODE_128, 500, 200);

		// zxing에서 스트림에 파일을 뿌릴수있도록 메소드를 지원함.rw
		MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
		MatrixToImageWriter.writeToStream(bitMatrixs, "JPG", TEST);

	}
}
