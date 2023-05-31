package uit.models;

import java.sql.Date;

public class customerData {
    private String name;
    private String phone;
    private String email;
    private Date birth;
    private String gender;
    public customerData(String name, String phone, String email, Date birth, String gender){
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birth = birth;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirth() {
        return birth;
    }
    public String getGender() {
        return gender;
    }
}
