package string;

public class StringTools {
    /**
     * 替换字符串为[指定字符串]从第begin位开始长度为length
     * @param src
     * @param begin
     * @param length
     * @return
     */
    public static String replaceTo(String src, String re, int begin, int length){
        if (src == null || src.trim().length() <= 0) {
            return "";
        }
        if(Math.abs(begin) > src.length()){
            return src;
        }
        begin = begin < 0 ? src.length() + begin : begin;
        length = length > src.length() - Math.abs(begin) ? src.length() - Math.abs(begin) : Math.abs(length);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++){
            stringBuilder.append(re);
        }
        StringBuilder s = new StringBuilder();
        s.append(src.substring(0, begin)).append(stringBuilder.toString()).append(src.substring(begin+length));
        return s.toString();
    }
    public static String  replaceTo(String src, String re, int begin){
        return replaceTo(src, re, begin, src.length());
    }

    /**
     * 驼峰格式字符串转换为指定格式字符串
     * @param param
     * @return
     */
    public static String camelToChar(String param,final char ch) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(ch);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 指定字符串转换为驼峰格式字符串
     * @param param
     * @return
     */
    public static String charToCamel(String param,final char ch) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == ch) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(replaceTo("123456789","*",5));
        String loginIn = camelToChar("loginIn", '-');
        String s = charToCamel(loginIn, '-');
        System.out.println(loginIn);
        System.out.println(s);
    }
}
