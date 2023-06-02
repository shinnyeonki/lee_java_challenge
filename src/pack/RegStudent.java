package pack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class RegStudent extends JFrame implements ActionListener {
    private JTextField nameTextField; // 이름
    private CheckboxGroup genderCheckboxGroup; // 성별
    private JTextField studentIDTextField; // 학번
    private JTextField phoneNumberTextField; // 전화번호
    private JTextField addressTextField; // 주소
    private Choice departmentChoice; // 학과
    private CheckboxGroup hobbiesCheckboxGroup; // 취미
    private TextArea introductionTextArea; // 자기소개

    private java.util.List<Student> students;

    public RegStudent() {
        //기본 타이틀
        setTitle("학생 등록");
        setSize(400, 600);
        setLocation(400, 100);
        getContentPane().setBackground(Color.YELLOW);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //메인 페널
        JPanel mainPanel = new JPanel(new GridLayout(0, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        JLabel nameLabel = new JLabel("성명");
        nameTextField = new JTextField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameTextField);

        JLabel genderLabel = new JLabel("성별");
        JPanel genderPanel = new JPanel();
        genderCheckboxGroup = new CheckboxGroup();
        Checkbox maleCheckbox = new Checkbox("남", genderCheckboxGroup, true);
        Checkbox femaleCheckbox = new Checkbox("여", genderCheckboxGroup, false);
        genderPanel.add(maleCheckbox);
        genderPanel.add(femaleCheckbox);
        inputPanel.add(genderLabel);
        inputPanel.add(genderPanel);

        JLabel studentIDLabel = new JLabel("학번");
        studentIDTextField = new JTextField();
        inputPanel.add(studentIDLabel);
        inputPanel.add(studentIDTextField);

        JLabel phoneNumberLabel = new JLabel("전화번호");
        phoneNumberTextField = new JTextField();
        inputPanel.add(phoneNumberLabel);
        inputPanel.add(phoneNumberTextField);

        JLabel addressLabel = new JLabel("주소");
        addressTextField = new JTextField();
        inputPanel.add(addressLabel);
        inputPanel.add(addressTextField);

        JLabel departmentLabel = new JLabel("학과");
        departmentChoice = new Choice();
        departmentChoice.add("학과 선택");
        departmentChoice.add("컴퓨터공학과");
        departmentChoice.add("전자공학과");
        departmentChoice.add("정보통신공학과");
        inputPanel.add(departmentLabel);
        inputPanel.add(departmentChoice);

        JLabel hobbiesLabel = new JLabel("취미");
        JPanel hobbiesPanel = new JPanel();
        hobbiesCheckboxGroup = new CheckboxGroup();
        Checkbox sportsCheckbox = new Checkbox("운동", hobbiesCheckboxGroup, true);
        Checkbox musicCheckbox = new Checkbox("음악감상", hobbiesCheckboxGroup, false);
        Checkbox movieCheckbox = new Checkbox("영화", hobbiesCheckboxGroup, false);
        Checkbox travelCheckbox = new Checkbox("여행", hobbiesCheckboxGroup, false);
        hobbiesPanel.add(sportsCheckbox);
        hobbiesPanel.add(musicCheckbox);
        hobbiesPanel.add(movieCheckbox);
        hobbiesPanel.add(travelCheckbox);
        inputPanel.add(hobbiesLabel);
        inputPanel.add(hobbiesPanel);

        JLabel introductionLabel = new JLabel("자기 소개");
        introductionTextArea = new TextArea();
        inputPanel.add(introductionLabel);
        inputPanel.add(introductionTextArea);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("저장");
        JButton exitButton = new JButton("종료");
        saveButton.addActionListener(this);
        exitButton.addActionListener(this);
        buttonPanel.add(saveButton);
        buttonPanel.add(exitButton);

        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);

        add(mainPanel);
        setVisible(true);

        students = new java.util.ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("저장")) {
            if (nameTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "이름을 입력하세요");
                return;
            }

            if (genderCheckboxGroup.getSelectedCheckbox() == null) {
                JOptionPane.showMessageDialog(this, "성별을 선택해 주세요");
                return;
            }

            if (studentIDTextField.getText().isEmpty() || studentIDTextField.getText().length() != 8) {
                JOptionPane.showMessageDialog(this, "학번이 틀렸습니다");
                return;
            }

            if (phoneNumberTextField.getText().isEmpty() || phoneNumberTextField.getText().length() != 13) {
                JOptionPane.showMessageDialog(this, "전화번호가 틀렸습니다");
                return;
            }

            if (addressTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "주소를 입력하세요");
                return;
            }

            if (departmentChoice.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "학과를 하나 선택하세요");
                return;
            }

            if (hobbiesCheckboxGroup.getSelectedCheckbox() == null) {
                JOptionPane.showMessageDialog(this, "취미를 두개 이상 선택하세요");
                return;
            }

            String studentID = studentIDTextField.getText();
            String name = nameTextField.getText();
            String gender = genderCheckboxGroup.getSelectedCheckbox().getLabel();
            String phoneNumber = phoneNumberTextField.getText();
            String address = addressTextField.getText();
            String department = departmentChoice.getSelectedItem();
            String hobbies = hobbiesCheckboxGroup.getSelectedCheckbox().getLabel();
            
            String introduction = introductionTextArea.getText();

            Student student = new Student(studentID, name, gender, phoneNumber, address, department, hobbies, introduction);
            students.add(student);

            clearFields();
        } else if (e.getActionCommand().equals("종료")) {
            showStudentList();
            System.exit(0);
        }
    }

    private void clearFields() {
        nameTextField.setText("");
        genderCheckboxGroup.setSelectedCheckbox(null);
        studentIDTextField.setText("");
        phoneNumberTextField.setText("");
        addressTextField.setText("");
        departmentChoice.select(0);
        hobbiesCheckboxGroup.getSelectedCheckbox().setState(false);
        introductionTextArea.setText("");
    }

    private void showStudentList() {
        String[] columnNames = {"학번", "이름", "성별", "전화번호", "주소", "학과", "취미", "자기소개"};
        Object[][] data = new Object[students.size()][8];

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            data[i][0] = student.getStudentID();
            data[i][1] = student.getName();
            data[i][2] = student.getGender();
            data[i][3] = student.getPhoneNumber();
            data[i][4] = student.getAddress();
            data[i][5] = student.getDepartment();
            data[i][6] = student.getHobbies();
            data[i][7] = student.getIntroduction();
        }

        JTable table = new JTable(data, columnNames);
        JOptionPane.showMessageDialog(this, new JScrollPane(table), "등록된 학생 목록", JOptionPane.PLAIN_MESSAGE);
    }
}

