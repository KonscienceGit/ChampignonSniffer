package dataclasses;

public abstract class AstronomicalBody {
    public static final int BODY_STAR = 0;
    public static final int BODY_BELT = 1;
    public static final int BODY_PLANET = 2;

    public String _detailedScanContent = "";
    public String _bodyName = "";
    public int _bodyId = 0;
    public int _bodyType = 0;

    public void print(){
        System.out.println("This print is an empty method yet");
    }

    public static AstronomicalBody getClass(String line) {
        try {
            if (line.contains("StarType")){
            return new Star(line);
            }else if(line.contains("Belt Cluster")){
                System.out.println("return new BeltCluster(line);");
                return new BeltCluster();
            }else if(line.contains("PlanetClass")){
                return new Planet(line);
            } else {
                System.out.println("Unknown body type! : "+ line);
                return null;
            }
        } catch (NoSuchFieldException e) {
            System.out.println("Unknown body type! : "+ line);
            e.printStackTrace();
            return null;
        }
    }
}
