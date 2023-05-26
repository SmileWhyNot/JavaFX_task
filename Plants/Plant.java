package Plants;

public class Plant {
    private String name; // название растения
    private String familyName; // семейство
    private String plantSort; // Род
    private String type; // Вид
    private double age; // Возраст

    public Plant(String name, String familyName, String plantSort, String type, double age){
        this.name = name;
        this.familyName = familyName;
        this.plantSort = plantSort;
        this.type = type;
        this.age = age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlantSort(String plantSort) {
        this.plantSort = plantSort;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getPlantSort() {
        return plantSort;
    }

    public String getType() {
        return type;
    }
}
