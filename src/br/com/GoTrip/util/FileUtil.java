package br.com.GoTrip.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import br.com.GoTrip.exception.GoTripException;

public class FileUtil {

	public static final String UPLOAD_TEMP = "C:/temp";
	public static final String UPLOAD_DIR = "C:/ImagemProjeto";
	public static final String DOWNLOAD_FILE = "C:/ImagemProjeto";

	public static File save(String fileName, InputStream inputStream) throws GoTripException {
		String extension = fileName.substring(fileName.lastIndexOf("."));
		String uniqueName = UUID.randomUUID().toString() + extension;
		File temp = new File(UPLOAD_TEMP, uniqueName);

		Path dest = Paths.get(temp.getAbsolutePath());

		try {
			Files.copy(inputStream, dest);
		} catch (IOException e) {
			throw new GoTripException(e);
		}
		return temp;
	}

	public static File getFile(String fileName) {
		return new File(DOWNLOAD_FILE, fileName);
	}

	
	
	public void moverImagem(String fileName) throws GoTripException {

		File temp = new File(UPLOAD_TEMP, fileName);
		File perm = new File(UPLOAD_DIR, fileName);

		Path orig = Paths.get(temp.getAbsolutePath());
		Path dest = Paths.get(perm.getAbsolutePath());

		try {
			Files.move(orig, dest, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new GoTripException(e);
		}

	}

}
