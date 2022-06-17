package com.xjt.javase.collections.list.domain;

public class Skill implements Cloneable{
    private String skillName;

    public Skill() {
    }

    public Skill(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skillName='" + skillName + '\'' +
                '}';
    }

    @Override
    public Skill clone() throws CloneNotSupportedException {
        return (Skill) super.clone();
    }
}
