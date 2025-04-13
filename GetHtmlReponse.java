public class GetHtmlReponse {
    public static final String response="<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Server Response</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1>\n" +
            "    This is a HTML file returned from my server\n" +
            "</h1>\n" +
            "</body>\n" +
            "</html>";
    public static String getResponse(){
        return response;
    }
}
