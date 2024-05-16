package ua.lviv.iot.lab5.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "establishment", schema = "dbo")
public class Establishment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "rating")
    private Double rating;
    @ManyToOne
    @JoinColumn(name = "type_of_establishment_id", referencedColumnName = "id", nullable = false)
    private TypeOfEstablishment typeOfEstablishment;
    @ManyToOne
    @JoinColumn(name = "street_id", referencedColumnName = "id", nullable = false)
    private Street street;
    @ManyToOne
    @JoinColumn(name = "Information_about_owner_id", referencedColumnName = "id", nullable = false)
    private InformationAboutOwner informationAboutOwner;
    @ManyToMany
    @JoinTable(name = "establishment_review_of_establishment", joinColumns = @JoinColumn(name = "establishment_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "review_of_establishment_id", referencedColumnName = "id", nullable = false))
    private Set<ReviewOfEstablishment> reviews;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Establishment that = (Establishment) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating);
    }

    public TypeOfEstablishment getTypeOfEstablishment() {
        return typeOfEstablishment;
    }

    public void setTypeOfEstablishment(TypeOfEstablishment typeOfEstablishment) {
        this.typeOfEstablishment = typeOfEstablishment;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public InformationAboutOwner getInformationAboutOwner() {
        return informationAboutOwner;
    }

    public void setInformationAboutOwner(InformationAboutOwner informationAboutOwner) {
        this.informationAboutOwner = informationAboutOwner;
    }

    public Set<ReviewOfEstablishment> getReviews() {
        return reviews;
    }

    public void setReviews(Set<ReviewOfEstablishment> reviews) {
        this.reviews = reviews;
    }
}
