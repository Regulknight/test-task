package com.haulmont.testtask.model;

public class Author {
    private Long id;
    private String fname;
    private String lname;
    private String patronymic;

    public Author() {
    }

    public Author(Long id, String name, String fname, String patronymic) {
        this.id = id;
        this.fname = name;
        this.lname = fname;
        this.patronymic = patronymic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;

        Author author = (Author) o;

        if (!getId().equals(author.getId())) return false;
        if (!getFname().equals(author.getFname())) return false;
        if (!getLname().equals(author.getLname())) return false;
        return getPatronymic() != null ? getPatronymic().equals(author.getPatronymic()) : author.getPatronymic() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getFname().hashCode();
        result = 31 * result + getLname().hashCode();
        result = 31 * result + (getPatronymic() != null ? getPatronymic().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                '}';
    }
}