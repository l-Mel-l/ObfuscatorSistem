import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        String result = "";
        try {
            String text = "";
            File file = new File("C:\\Users\\79623\\IdeaProjects\\ObfuscatorSistem\\src\\textFile.java");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = null;
            while ((line = reader.readLine()) != null) {
                text += line + ("\n");
            }
            System.out.println(text);  // Вывод с комментариями
            result = text.replaceAll("//.*", "").replaceAll("/\\*(.|\\n)*?\\*/", "");
            result = result.replaceAll("\\s*(?=[;{}()=,])|(?<=[;{}()=,])\\s*", "").replaceAll("/\\*(.|\\n)*?\\*/", "");

            // Изменение имен переменных
            result = obfuscateVariables(result);

            reader.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            FileWriter writer = new FileWriter("C:\\Users\\79623\\IdeaProjects\\ObfuscatorSistem\\src\\textFile.java", false);
            writer.write(result);
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Функция для изменения имен переменных
    private static String obfuscateVariables(String input) {
        Map<String, Integer> variableCountMap = new HashMap<>();
        Matcher matcher = Pattern.compile("\\b(int|String|boolean|double|float)\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\b").matcher(input);

        while (matcher.find()) {
            String type = matcher.group(1);
            String originalName = matcher.group(2);
            String obfuscatedName = generateUniqueName(originalName, variableCountMap);

            // Заменяем оригинальное имя на обфусцированное
            input = input.replace(originalName, obfuscatedName);
        }

        return input;
    }

    private static String generateUniqueName(String originalName, Map<String, Integer> variableCountMap) {
        if (!variableCountMap.containsKey(originalName)) {
            variableCountMap.put(originalName, 1);
        } else {
            int count = variableCountMap.get(originalName);
            count++;
            variableCountMap.put(originalName, count);
        }

        char baseLetter = originalName.charAt(0);
        String obfuscatedName = Character.toString(baseLetter);

        if (variableCountMap.get(originalName) > 1) {
            obfuscatedName += variableCountMap.get(originalName);
        }

        return obfuscatedName;
    }
}