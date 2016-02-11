package plugin.splmetrics.algmetrics.nor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class RefinedClassesSearch extends SimpleFileVisitor<Path> {

	public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {
		// System.out.println(" Nome do arquivo: " + path.getFileName());
		String fileName = path.toString();
		String extensao = fileName.substring(fileName.lastIndexOf("."), fileName.length());

		String featureName = path.getParent().getFileName().toString();

		if (extensao.equals(".jak")) {
			try {
				FileReader fr = new FileReader(fileName);
				BufferedReader ler = new BufferedReader(fr);

				String currentLine = "";

				while ((currentLine = ler.readLine()) != null) {
					// System.out.println(currentLine);
					if (currentLine.contains("public class")) {
						String[] keys = currentLine.split(" ");

						if (keys[0].equals("public") && keys[1].equals("class")) {
							NoR.refinesObjs.add(new RefinedObject(keys[2], featureName));
						}
					}
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return FileVisitResult.CONTINUE;
	}

	public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes fileAttributes) {
		// System.out.println("Nome do diretório: " + path);
		return FileVisitResult.CONTINUE;
	}
}
