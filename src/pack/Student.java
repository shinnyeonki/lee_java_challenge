package pack;

class Student {
    private String studentID;
    private String name;
    private String gender;
    private String phoneNumber;
    private String address;
    private String department;
    private String hobbies;
    private String introduction;

    public Student(String studentID, String name, String gender, String phoneNumber, String address, String department, String hobbies, String introduction) {
        this.studentID = studentID;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.department = department;
        this.hobbies = hobbies;
        this.introduction = introduction;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getDepartment() {
        return department;
    }

    public String getHobbies() {
        return hobbies;
    }

    public String getIntroduction() {
        return introduction;
    }
}
