package com.mikellbobadilla.proyect;

public class Matricula {

  private Long alumno;
  private Long asignatura;
  private int year;
  private Integer nota;

  public Matricula(Long alumno, Long asignatura, int year, Integer nota) {
    this.alumno = alumno;
    this.asignatura = asignatura;
    this.year = year;
    this.nota = nota;
  }

  public Long getAlumno() {
    return alumno;
  }

  public void setAlumno(Long alumno) {
    this.alumno = alumno;
  }

  public Long getAsignatura() {
    return asignatura;
  }

  public void setAsignatura(Long asignatura) {
    this.asignatura = asignatura;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public Integer getNota() {
    return nota;
  }

  public void setNota(Integer nota) {
    this.nota = nota;
  }

  @Override
  public String toString() {
    return "Matriculas{" +
      "alumno=" + alumno +
      ", asignatura=" + asignatura +
      ", year=" + year +
      ", nota=" + nota +
      '}';
  }
}
