package com.xyafu.gtms.entity;

public class Student_subject {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_subject.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_subject.subject
     *
     * @mbggenerated
     */
    private String subject;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_subject.Stu_id
     *
     * @mbggenerated
     */
    private String stuId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_subject.Twant
     *
     * @mbggenerated
     */
    private String twant;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_subject.Cname
     *
     * @mbggenerated
     */
    private String cname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_subject.Y_N
     *
     * @mbggenerated
     */
    private Byte yN;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_subject.id
     *
     * @return the value of student_subject.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_subject.id
     *
     * @param id the value for student_subject.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_subject.subject
     *
     * @return the value of student_subject.subject
     *
     * @mbggenerated
     */
    public String getSubject() {
        return subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_subject.subject
     *
     * @param subject the value for student_subject.subject
     *
     * @mbggenerated
     */
    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_subject.Stu_id
     *
     * @return the value of student_subject.Stu_id
     *
     * @mbggenerated
     */
    public String getStuId() {
        return stuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_subject.Stu_id
     *
     * @param stuId the value for student_subject.Stu_id
     *
     * @mbggenerated
     */
    public void setStuId(String stuId) {
        this.stuId = stuId == null ? null : stuId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_subject.Twant
     *
     * @return the value of student_subject.Twant
     *
     * @mbggenerated
     */
    public String getTwant() {
        return twant;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_subject.Twant
     *
     * @param twant the value for student_subject.Twant
     *
     * @mbggenerated
     */
    public void setTwant(String twant) {
        this.twant = twant == null ? null : twant.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_subject.Cname
     *
     * @return the value of student_subject.Cname
     *
     * @mbggenerated
     */
    public String getCname() {
        return cname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_subject.Cname
     *
     * @param cname the value for student_subject.Cname
     *
     * @mbggenerated
     */
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_subject.Y_N
     *
     * @return the value of student_subject.Y_N
     *
     * @mbggenerated
     */
    public Byte getyN() {
        return yN;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_subject.Y_N
     *
     * @param yN the value for student_subject.Y_N
     *
     * @mbggenerated
     */
    public void setyN(Byte yN) {
        this.yN = yN;
    }

    @Override
    public String toString() {
        return "Student_subject{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", stuId='" + stuId + '\'' +
                ", twant='" + twant + '\'' +
                ", cname='" + cname + '\'' +
                ", yN=" + yN +
                '}';
    }
}