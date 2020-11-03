/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author breno
 */
public class FileUtils {

    /**
     * *
     * Utilitário para ler um ficheiro e retornar o conteúdo
     *
     * @param filePath
     * @param separator
     * @return
     */
    public static List<String[]> read(String filePath, String separator) {

        List<String[]> file = new ArrayList<>();
        BufferedReader reader = null;
        boolean firstLine = false;
        try {
            String line;
            reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                String[] element = line.split(separator);
                if (element[0].charAt(0) == '#') {
                    continue;
                } else {
                    if (firstLine) {
                        file.add(element);
                    }
                    firstLine = true;
                }
            }
            reader.close();
        } catch (IOException e) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, e);
        }
        return file;
    }

    /**
     * Método auxiliar de escrita em ficheiro.Requer que o conteúdo já esteja
     * formadado corretamente
     *
     * @param content :: conteúdo a escrever
     * @param filePath :: ficheiro de saída
     * @param header
     */
    public static void write(List<String> content, String filePath, String header) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(header);
			boolean firstLine = true;
            for (String line : content) {
				if(firstLine && header.equals("")) { 
				} else {
					writer.newLine();
				}
                writer.write(line);
				firstLine = false;
            }
            writer.close();
        } catch (IOException e) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void write(String content, String filePath, String header) {
        List<String> lista = new ArrayList<>();
        lista.add(content);
        write(lista, filePath, header);
    }
}
