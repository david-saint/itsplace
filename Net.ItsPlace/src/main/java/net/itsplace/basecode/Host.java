package net.itsplace.basecode;

public enum Host {
	Image("http://itsplace.sungwon-it.com/img"),
    Movie("ds");
    private String url;
 
    Host(String url) {
        this.url = url;
    }
    public String getUrl() {
        return this.url;
    }
   
}
