package fr.doranco.primus_neon.admin;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadUtil.class);

	
	public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
		
		Path uploadPath = Paths.get(uploadDir);
		
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ex) {
			throw new IOException("Impossible d'enregistrer le fichier: " + fileName, ex);
		}
	}
	
	public static void cleanDir(String dir) {
		
		Path dirPath = Paths.get(dir);
		
		try {
			Files.list(dirPath).forEach(file -> {
				if (!Files.isDirectory(file)) {
					try {
					Files.delete(file);
					} catch (IOException ex) {
						LOGGER.error("Impossible de supprimer le fichier: " + file);
						System.out.println("Impossible de supprimer le fichier: " + file);
					}
				}
			});
		} catch (IOException ex) {
			LOGGER.error("Impossible de répertorier le dossier: " + dir);
			System.out.println("Impossible de répertorier le dossier: ");
		}
	}
	
	public static void removeDir(String dir) {
		cleanDir(dir);
		
		try {
			Files.delete(Paths.get(dir));
		} catch (IOException e) {
			LOGGER.error("Impossible de supprimer le dossier: " + dir);
		}
		
	}
}
