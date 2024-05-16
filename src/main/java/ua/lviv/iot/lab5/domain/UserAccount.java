package ua.lviv.iot.lab5.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user_account", schema = "dbo")
public class UserAccount {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "nickname")
    private String nickname;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "surname")
    private String surname;
    @OneToMany(mappedBy = "userAccount")
    private List<ReviewOfEstablishment> reviewOfEstablishments;
    @ManyToOne
    @JoinColumn(name = "credential_id", referencedColumnName = "id", nullable = false)
    private Credential credential;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return Objects.equals(nickname, that.nickname) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, name, surname);
    }

    public List<ReviewOfEstablishment> getReviewOfEstablishments() {
        return reviewOfEstablishments;
    }

    public void setReviewOfEstablishments(List<ReviewOfEstablishment> reviewOfEstablishments) {
        this.reviewOfEstablishments = reviewOfEstablishments;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }
}
