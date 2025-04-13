public class Config {
    public String port;
    public String webroot;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getWebroot() {
        return webroot;
    }

    public void setWebroot(String webroot) {
        this.webroot = webroot;
    }

    @Override
    public String toString() {
        return "Config{" +
                "port='" + port + '\'' +
                ", webroot='" + webroot + '\'' +
                '}';
    }
}
