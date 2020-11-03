package lapr.project.utils;

/**
 * *
 * Parsers adicionais para leitura de ficheiro. Quando um ficheiro contém uma
 * String vazia para um campo entende-se que o valor na base de dados é null.
 * Quando um inteiro ou double não são convertíveis, não gera-se uma excessão,
 * mas define-se o campo como null.
 *
 * @author breno
 */
public class Parsers {

    /**
     * *
     * Retorna null para uma string representando um inteiro que não possa ser
     * convertida.
     *
     * @param string
     * @return
     */
    public static Integer parseInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * *
     * Retorna null para uma string representando um double que não possa ser
     * convertida.
     *
     * @param string
     * @return
     */
    public static Double parseDouble(String string) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * *
     * Se a string for vazia, retorna null de forma a identificar campos vazios
     * na base de dados.
     *
     * @param string
     * @return
     */
    public static String parseString(String string) {
		if (string == null) 
			return null;
        if (string.isEmpty()) {
            return null;
        }
        return string;
    }

}
