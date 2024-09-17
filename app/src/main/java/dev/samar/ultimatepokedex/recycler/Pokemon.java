package dev.samar.ultimatepokedex.recycler;


public class Pokemon {

    private int id;
    private String name;
    private int imageId;
    private int typeId;
    private String detailId;

    public Pokemon(int id, String name, int imageId, int typeId, String detailId) {
        this.id = id;
        this.name = name;
        this.imageId = imageId;
        this.typeId = typeId;
        this.detailId = detailId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getDetailId() {
        return detailId;  // Corrected return statement
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }
}
