import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class WebServer {
    public static void main(String[] args) {
        Config config=new Config();
        System.out.println("Starting WebServer");
        JsonParser jsonParser=new JsonParser();
        try {
            config=jsonParser.parse("config.json");
            ServerSocket serverSocket=new ServerSocket(Integer.parseInt(config.getPort()),0, InetAddress.getByName("0.0.0.0"));
            while(true){
                Socket socket=serverSocket.accept();
                System.out.println("Incoming IP:"+socket.getInetAddress().toString());
                InputStream incomingRequest=socket.getInputStream();
                OutputStream responseStream=socket.getOutputStream();
                String response=getServerResponse("json");
                responseStream.write(response.getBytes());
                incomingRequest.close();
                responseStream.close();
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Exception while parsing:"+e.getMessage());
        }

    }
    public static String getServerResponse(String type){
        JsonParser jsonParser=new JsonParser();
        final String CRLF = "\r\n";
        String data = "";
        if("json".equalsIgnoreCase(type))
        {
            data=jsonParser.writeJson(GetJsonResponse.getMap());
        }
        if("html".equalsIgnoreCase(type))
            data=GetHtmlReponse.getResponse();
        Map<String, Object> fullResponse = new LinkedHashMap<>();
        fullResponse.put("status", 200);
        fullResponse.put("message", "Success");
        fullResponse.put("data", data);

// Serialize to JSON string
        String responseBody = jsonParser.writeJson(fullResponse);

        String response = "HTTP/1.1 200 OK" + CRLF +
                "Content-Type: application/json" + CRLF +
                "Content-Length: " + responseBody.getBytes().length + CRLF +
                "Connection: close" + CRLF +
                CRLF +
                responseBody + CRLF;
        return response;
    }
}
