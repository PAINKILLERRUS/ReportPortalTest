package configuration;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YamlConfigLoader {

    private static final Pattern VAR_PATTERN = Pattern.compile("\\$\\{([^:}]+)(?::([^}]*))?}");

    /**
     * Загружает YAML-файл из classpath, заменяет переменные окружения и возвращает AppConfig.
     * @param yamlFilePath путь внутри resources (например, "app.yml")
     */
    public static AppConfig loadAppConfig(String yamlFilePath) throws IOException {
        Yaml yaml = new Yaml();
        try(InputStream inputStream = YamlConfigLoader.class.getClassLoader().getResourceAsStream(yamlFilePath)) {
            if (inputStream == null){
                throw new IllegalArgumentException("YAML file not found: " + yamlFilePath);
            }
            Map<String, Object> rawData = yaml.load(inputStream);
            Map<String, Object> appMap = (Map<String, Object>) rawData.get("app");
            if (appMap == null){
                throw new IllegalStateException("Missing 'app' section in YAML");
            }

            resolvePlaceholders(appMap);

            AppConfig config = new AppConfig();
            config.setLogin((String) appMap.get("login"));
            config.setPassword((String) appMap.get("password"));
            config.setBaseUrl((String) appMap.get("base-url"));
            config.setGrantType((String) appMap.get("grant_type"));
            return config;
        }
    }

    /**
     * Рекурсивно обходит Map, List и строки, заменяя плейсхолдеры.
     */
    @SuppressWarnings("unchecked")
    public static void resolvePlaceholders(Object obj){
        if (obj instanceof Map){
            Map<String,Object> map = (Map<String, Object>) obj;
            for (Map.Entry<String,Object> entry: map.entrySet()){
                Object value = entry.getValue();
                if (value instanceof String){
                    String resolved = resolveString((String)value);
                    entry.setValue(resolved);
                } else if (value instanceof Map || value instanceof List) {
                    resolvePlaceholders(value);// рекурсия для вложенных структур
                }
            }
        } else if (obj instanceof List) {
            List<Object> list = (List<Object>) obj;
            for (int i = 0; i < list.size(); i++) {
                Object item = list.get(i);
                if (item instanceof String) {
                    list.set(i, resolveString((String) item));
                } else if (item instanceof Map || item instanceof List) {
                    resolvePlaceholders(item);
                }
            }
        }
    }

    /**
     * Заменяет в строке все вхождения ${VAR:default} или ${VAR} на значение
     * переменной окружения или на default, если переменная не задана.
     */
    public static String resolveString(String str){
        if (str == null) return null;
        Matcher matcher = VAR_PATTERN.matcher(str);
        StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find()){
            String varName = matcher.group(1);
            String defaultValue  = matcher.group(2);
            String envValue = System.getenv(varName);
            String replacement = (envValue != null && !envValue.isEmpty()) ?envValue :(defaultValue !=null ? defaultValue: " ");
            matcher.appendReplacement(stringBuilder, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(stringBuilder);
        return stringBuilder.toString();
    }
}
