package com.bermanfaat.formbuilder.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;

public class UtilBase64Image {

	@Value("${location.base.64}")
	private static String locationBase64;

	@Value("${modul.name}")
	private static String modulName;

	@Value("${company.name}")
	private static String companyName;

	// file di decode disini
	public static String decode(String value, String idData, boolean isRemove, String fieldName) {

		// Please jangan di hardcode, config aja di application.properties
		String updatelocatonBase64 = locationBase64 + File.separator + "attachments" + File.separator + companyName
				+ File.separator + modulName + File.separator + idData + File.separator + fieldName + File.separator;
		System.out.println(updatelocatonBase64);

		// clean directory kalo nge-PUT
		if (isRemove) {
			DirectoryUtil.removeAllFilesinDirectory(updatelocatonBase64, fieldName);
		} else {
			DirectoryUtil.createDirectory(updatelocatonBase64);
		}

		// idk value nya diapit '
		value = value.replace("'", "");

		// lakukan pemisahan gambar disini
		// urutan stringnya, name(0):tipefile(1):value(2)
		String[] fileValue = value.split(":");

		// supaya hasilnuya c:\bermanfaat\image.png
		String targetResult = updatelocatonBase64 + fileValue[0].replaceAll("\\s", "") + "." + fileValue[1];

		// hasilnya D:\bermanfaat\image\namafile.timemilis.jpg
		UtilBase64Image.decoder(fileValue[2], targetResult);
		return targetResult;
	}

	public static String encode(String location, String idData, String fieldName) {

		// Please jangan di hardcode, config aja di application.properties
		String updatelocatonBase64 = locationBase64 + File.separator + companyName + File.separator + modulName
				+ File.separator + idData + File.separator + fieldName + File.separator;

		// lokasi dapet dari db
		String base64Encode = UtilBase64Image.encoder(location);

		String[] temp = location.split("\\.");
		String fileType = temp[temp.length - 1];

		// mendapatkkan nama file, substring dari locationbase.leng sampai tipefile-1
		// (-1 buat titik nya)
		String fileName = location.substring(updatelocatonBase64.length(), location.length() - fileType.length() - 1);

		// D:\bermanfaat\image\namafile.timemilis.size.jpg
		// urutan stringnya, name(0):tipefile(1):value(2)
		String result = fileName + ":" + fileType + ":" + base64Encode;
		return result;
	}

	public static String encoder(String imagePath) {
		File file = new File(imagePath);
		Path source = Paths.get(imagePath);

		try (FileInputStream imageInFile = new FileInputStream(file)) {

			String base64Image = "";
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);
			base64Image = Base64.getEncoder().encodeToString(imageData);
			// get mimetype
			// String mimeType = Files.probeContentType(source);

			// String result = "data:"+mimeType+";base64,"+base64Image;

			String result = base64Image;

			return result;
		} catch (FileNotFoundException e) {
			System.out.println("image not found " + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the image" + ioe);
		}
		return null;
	}

	public static void decoder(String base64Image, String pathFile) {
		try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
			// convert base64 string into byte array
			byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
			imageOutFile.write(imageByteArray);
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("Image not found " + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the image " + ioe);
		}
	}
}
