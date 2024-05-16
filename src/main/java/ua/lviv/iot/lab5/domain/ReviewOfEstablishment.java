package ua.lviv.iot.lab5.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "review_of_establishment", schema = "dbo")
public class ReviewOfEstablishment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "review")
    private String review;
    @ManyToOne
    @JoinColumn(name = "user_account_nickname", referencedColumnName = "nickname", nullable = false)
    private UserAccount userAccount;
    @ManyToMany(mappedBy = "reviews")
    private Set<Establishment> establishments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewOfEstablishment that = (ReviewOfEstablishment) o;
        return Objects.equals(id, that.id) && Objects.equals(review, that.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, review);
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Set<Establishment> getEstablishments() {
        return establishments;
    }

    public void setEstablishments(Set<Establishment> establishments) {
        this.establishments = establishments;
    }
}
