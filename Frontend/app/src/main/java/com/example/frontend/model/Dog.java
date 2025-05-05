package com.example.frontend.model;

import java.util.Objects;


public class Dog {

    private int id;
    private String name;
    private String imageLink;
    private String adoptionStatus;
    private String adoptedBy;

    public Dog() {
    }

    public Dog(int id, String name, String imageLink, String adoptionStatus, String adoptedBy) {
        this.id = id;
        this.name = name;
        this.imageLink = imageLink;
        this.adoptionStatus = adoptionStatus;
        this.adoptedBy = adoptedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getAdoptionStatus() {
        return adoptionStatus;
    }

    public void setAdoptionStatus(String adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

    public String getAdoptedBy() {
        return adoptedBy;
    }

    public void setAdoptedBy(String adoptedBy) {
        this.adoptedBy = adoptedBy;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.imageLink);
        hash = 79 * hash + Objects.hashCode(this.adoptionStatus);
        hash = 79 * hash + Objects.hashCode(this.adoptedBy);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dog other = (Dog) obj;
        if (this.name != other.name) {
            return false;
        }
        if (!Objects.equals(this.imageLink, other.imageLink)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", imageLink='").append(imageLink).append('\'');
        sb.append(", adoptionStatus='").append(adoptionStatus).append('\'');
        sb.append(", adoptedBy=").append(adoptedBy);
        sb.append('}');
        return sb.toString();
    }
}
