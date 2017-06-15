package com.haulmont.testtask.model;

public class Author {
    Long id;
    String name;
    String fname;
    String patronymic;

    public Author() {
    }

    public Author(Long id, String name, String fname, String patronymic) {
        this.id = id;
        this.name = name;
        this.fname = fname;
        this.patronymic = patronymic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
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
        if (!getName().equals(author.getName())) return false;
        if (!getFname().equals(author.getFname())) return false;
        return getPatronymic().equals(author.getPatronymic());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getFname().hashCode();
        result = 31 * result + getPatronymic().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fname='" + fname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                '}';
    }
}
