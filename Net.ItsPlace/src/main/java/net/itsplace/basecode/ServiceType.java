package net.itsplace.basecode;

public enum ServiceType {
	Normal("N"), Stamp("S"), Premier("P"); //가맹점 서비스종
	
    private String type;
 
	ServiceType(String type) {
        this.type = type;
    }
    public String getServiceType() {
        return this.type;
    }
}
