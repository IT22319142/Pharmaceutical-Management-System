package pharmacy;
//@author thath

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class EmployeeAttendance extends javax.swing.JFrame 
{
//Creates new form EmployeeAttendance

    public EmployeeAttendance() 
    {
        initComponents();
        refreshAttendanceTable();

        //The method to populate the employeeID combo box
        getEmployeeIDs();

        employeeID.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int selectedMedID = Integer.parseInt(employeeID.getSelectedItem().toString());
                String employeeName = getEmployeeNameByEmpID(selectedMedID);
                emp_name.setText(employeeName);
            }
        });

        id.setText("Will Be Added Automatically");

        // Set startDate JDateChooser to today's date only
        day.setDate(new Date()); // Set the date to today's date
        day.setMinSelectableDate(new Date()); // Disabling past dates
        day.setMaxSelectableDate(new Date()); // Disabling future dates

        emp_name.setText("");
        
        // Create a ButtonGroup
        ButtonGroup attendanceGroup = new ButtonGroup();
        attendanceGroup.add(present);
        attendanceGroup.add(absent);
        
        // Set Present as the default selection
        present.setSelected(true);
        absent.setSelected(false);
    }

    private void getEmployeeIDs() 
    {
        try 
        {
            Statement st = DBconnection.createDBconnection().createStatement();
            ResultSet rst = st.executeQuery("SELECT emp_ID FROM employee");

            // Clear existing items in the combo box
            employeeID.removeAllItems();

            // Populate combo box with employee IDs
            while (rst.next()) 
            {
                // Store emp_IDs in the combo box
                String item = String.valueOf(rst.getInt("emp_ID"));
                employeeID.addItem(item);
            }

            // Close resources
            rst.close();
            st.close();
            DBconnection.createDBconnection().close();
        } 
        catch (SQLException ex) 
        {
            // Handle exceptions
            JOptionPane.showMessageDialog(null, "Error fetching Employee IDs: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isAttendanceMarkedToday(int employeeID) 
    {
        boolean attendanceMarked = false;
        
        try 
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String todayDate = sdf.format(new Date());

            String query = "SELECT * FROM attendance WHERE empID = " + employeeID + " AND date = '" + todayDate + "'";
            Statement st = DBconnection.createDBconnection().createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) 
            {
                attendanceMarked = true;
            }

            rs.close();
            st.close();
            DBconnection.createDBconnection().close();
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error checking attendance: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return attendanceMarked;
    }

    /*
    private void saveAttendance() 
    {
        try 
        {
            int selectedEmployeeID = Integer.parseInt(employeeID.getSelectedItem().toString());
            String attendanceStatus = present.isSelected() ? "Present" : "Absent";

            if (isAttendanceMarkedToday(selectedEmployeeID)) 
            {
                JOptionPane.showMessageDialog(null, "This Employee's today's attendance has already been marked!", "Duplicate Attendance", JOptionPane.ERROR_MESSAGE);
            } 
            else 
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String todayDate = sdf.format(new Date());

                Statement st = DBconnection.createDBconnection().createStatement();

                // Fetch the next available attendanceID from the database
                ResultSet rs = st.executeQuery("SELECT MAX(attendanceID) FROM attendance");
                int nextAttendanceID = 1;

                if (rs.next()) 
                {
                    nextAttendanceID = rs.getInt(1) + 1;
                }

                rs.close();

                // Insert attendance record into the database
                String empName = getEmployeeNameByEmpID(selectedEmployeeID);
                String insertQuery = "INSERT INTO attendance (attendanceID, date, empID, empName, attendance) VALUES (" + nextAttendanceID + ", '" + todayDate + "', " + selectedEmployeeID + ", '" + empName + "', '" + attendanceStatus + "')";

                int rowsAffected = st.executeUpdate(insertQuery);

                if (rowsAffected > 0) 
                {
                    JOptionPane.showMessageDialog(null, "Attendance marked successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    refreshAttendanceTable();
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "Failed to mark attendance!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                st.close();
            }
        } catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error saving attendance: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    */

    // Refresh the Attendance Table 
    private void refreshAttendanceTable() 
    {
        try 
        {
            Statement st = DBconnection.createDBconnection().createStatement();
            ResultSet rs = st.executeQuery(" SELECT * FROM attendance ");

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Attendance ID");
            model.addColumn("Date");
            model.addColumn("Employee ID");
            model.addColumn("Employee Name");
            model.addColumn("Attendance");

            while (rs.next()) 
            {
                Object[] row = 
                {
                    rs.getInt("attendanceID"),
                    rs.getString("date"),
                    rs.getInt("empID"),
                    rs.getString("empName"),
                    rs.getString("attendance")
                };
                model.addRow(row);
            }

            attendanceTable.setModel(model);
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error occurred while refreshing Table: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        BackBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Update_Btn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        Delete_Btn = new javax.swing.JButton();
        ClearBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        attendanceTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        day = new com.toedter.calendar.JDateChooser();
        employeeID = new javax.swing.JComboBox<>();
        present = new javax.swing.JRadioButton();
        absent = new javax.swing.JRadioButton();
        emp_name = new javax.swing.JTextField();
        logOutBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        search_Button = new javax.swing.JButton();
        searchTxt = new javax.swing.JTextField();
        ExitBtn = new javax.swing.JButton();
        LogoutBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 153, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Medicare Pharmaceuticals");

        jLabel3.setIcon(new javax.swing.ImageIcon("D:\\Pharmacy Management System\\Pharmacy\\Images\\attendance.png")); // NOI18N
        jLabel3.setText("jLabel3");

        BackBtn.setBackground(new java.awt.Color(51, 204, 0));
        BackBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BackBtn.setText("Back");
        BackBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackBtnActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 102));
        jLabel2.setText("Employee Attendance Tracking");

        Update_Btn.setBackground(new java.awt.Color(102, 204, 0));
        Update_Btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Update_Btn.setText("Update");
        Update_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_BtnActionPerformed(evt);
            }
        });

        addBtn.setBackground(new java.awt.Color(102, 204, 0));
        addBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        Delete_Btn.setBackground(new java.awt.Color(102, 204, 0));
        Delete_Btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Delete_Btn.setText("Delete");
        Delete_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_BtnActionPerformed(evt);
            }
        });

        ClearBtn.setBackground(new java.awt.Color(102, 204, 0));
        ClearBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ClearBtn.setText("Clear");
        ClearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearBtnActionPerformed(evt);
            }
        });

        attendanceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Attendance ID", "Date", "Employee ID", "Employee Name", "Attendance"
            }
        ));
        attendanceTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                attendanceTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(attendanceTable);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Attendance ID");

        id.setEditable(false);
        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Date");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Employee Name");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Employee ID");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Attendance");

        employeeID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        present.setText("Present");
        present.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presentActionPerformed(evt);
            }
        });

        absent.setText("Absent");

        logOutBtn.setBackground(new java.awt.Color(102, 204, 0));
        logOutBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        logOutBtn.setText("Logout");
        logOutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutBtnActionPerformed(evt);
            }
        });

        exitBtn.setBackground(new java.awt.Color(0, 153, 102));
        exitBtn.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        exitBtn.setText("x");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        search_Button.setBackground(new java.awt.Color(102, 204, 0));
        search_Button.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        search_Button.setText("Search");
        search_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_ButtonActionPerformed(evt);
            }
        });

        searchTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTxtActionPerformed(evt);
            }
        });

        ExitBtn.setBackground(new java.awt.Color(0, 153, 102));
        ExitBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ExitBtn.setText("X");
        ExitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitBtnActionPerformed(evt);
            }
        });

        LogoutBtn.setBackground(new java.awt.Color(102, 204, 0));
        LogoutBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LogoutBtn.setText("Logout");
        LogoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1033, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(276, 276, 276)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(Update_Btn)
                                    .addGap(77, 77, 77)
                                    .addComponent(Delete_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(79, 79, 79)
                                    .addComponent(ClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addGap(124, 124, 124)
                                            .addComponent(present, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(33, 33, 33)
                                            .addComponent(absent, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(emp_name, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(employeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(60, 60, 60)
                                                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(122, 122, 122)))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
                        .addGap(575, 575, 575)
                        .addComponent(logOutBtn)
                        .addGap(11, 11, 11)
                        .addComponent(exitBtn)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LogoutBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ExitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(699, 699, 699))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LogoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logOutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(employeeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emp_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(present)
                        .addComponent(absent))
                    .addComponent(jLabel8))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn)
                    .addComponent(Update_Btn)
                    .addComponent(Delete_Btn)
                    .addComponent(ClearBtn))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_Button))
                .addGap(18, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(BackBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1093, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(31, 31, 31)
                .addComponent(BackBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 989, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BackBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackBtnActionPerformed
        // Back Button -> navigates to the EmployeeDetails page
        this.dispose();
        EmployeeDetails obj = new EmployeeDetails();
        obj.show();
    }//GEN-LAST:event_BackBtnActionPerformed

    private void Update_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Update_BtnActionPerformed
        //Update Button
        try 
        {
            // Get the selected row index
            int selectedRow = attendanceTable.getSelectedRow();

            if (selectedRow == -1) 
            {
                JOptionPane.showMessageDialog(null, "Please select a row to update.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Get the values from the selected row in the table
            String attendanceID = attendanceTable.getValueAt(selectedRow, 0).toString(); // Assuming the first column is the attendanceID
            String attendanceStatus = getAttendance();
            
            // Get the date from the JDateChooser
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String selectedDate = sdf.format(day.getDate());

            // Update the attendance in the database
            Statement st = DBconnection.createDBconnection().createStatement();
            String updateQuery = "UPDATE attendance SET date = '" + selectedDate + "', empID = " + EmployeeIDofTheSelectedRow + ", attendance = '" + attendanceStatus + "' WHERE attendanceID = " + attendanceID;

            int rowsAffected = st.executeUpdate(updateQuery);

            if (rowsAffected > 0) 
            {
                JOptionPane.showMessageDialog(null, "Attendance record updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshAttendanceTable(); // Refresh the attendance table to reflect the changes
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Failed to update attendance record.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            st.close();
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error occurred while updating attendance: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_Update_BtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        try 
        {
            int selectedEmployeeID = Integer.parseInt(employeeID.getSelectedItem().toString());
            String attendanceStatus = getAttendance();

            if (isAttendanceMarkedToday(selectedEmployeeID)) 
            {
                JOptionPane.showMessageDialog(null, "This Employee's today's attendance has already been marked!", "Duplicate Attendance", JOptionPane.ERROR_MESSAGE);
                return; // Stop execution if attendance is already marked
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String todayDate = sdf.format(day.getDate());

            String empName = getEmployeeNameByEmpID(selectedEmployeeID);

            Statement st = DBconnection.createDBconnection().createStatement();

            // Fetch the next available attendanceID from the database
            ResultSet rs = st.executeQuery("SELECT MAX(attendanceID) FROM attendance");
            int nextAttendanceID = 1;

            if (rs.next()) 
            {
                nextAttendanceID = rs.getInt(1) + 1;
            }

            rs.close();

            // Insert attendance record into the database
            String insertQuery = "INSERT INTO attendance (attendanceID, date, empID, empName, attendance) VALUES (" + nextAttendanceID + ", '" + todayDate + "', " 
                    + selectedEmployeeID + ", '" + empName + "', '" + attendanceStatus + "')";
            int count = st.executeUpdate(insertQuery);

            if (count > 0) 
            {
                JOptionPane.showMessageDialog(null, "Attendance Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshAttendanceTable();
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Failed to add attendance!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            st.close();
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private String getEmployeeNameByEmpID(int EmployeeID) 
    {
        String emp_Name = null;
        try 
        {
            Statement st = DBconnection.createDBconnection().createStatement();
            ResultSet rst = st.executeQuery("SELECT f_name FROM employee WHERE emp_ID = " + EmployeeID);
            
            if (rst.next()) 
            {
                emp_Name = rst.getString("f_name");
            }
            rst.close();
            st.close();
            DBconnection.createDBconnection().close();
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error occurred while fetching the employee name: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return emp_Name;
    }

    private String getAttendance() 
    {
        if (present.isSelected()) 
        {
            return "1";
        } 
        else if (absent.isSelected()) 
        {
            return "0";
        } 
        else //When no option is selected
        {
            return "Did not marked yet";
        }
    }

    private void Delete_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_BtnActionPerformed
        //Delete Button
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to 'Delete' this Record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) 
        {
            try 
            {
                Statement st = pharmacy.DBconnection.createDBconnection().createStatement();
                String query = "DELETE FROM `attendance` WHERE `attendanceID` = " + Integer.valueOf(attendanceTable.getValueAt(attendanceTable.getSelectedRow(), 0).toString());

                //executeUpdate Method call to execute the DELETE query & returns the No of rows deleted
                int count = st.executeUpdate(query);
                //System.out.println(query);

                //Data Validations 
                if (count > 0) 
                {
                    JOptionPane.showMessageDialog(null, "Selected Attendance Details Deleted Successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Refresh the EmployeeDetailsTable after deleting
                    refreshAttendanceTable();
                }

                //Deselect previous selected rows
                attendanceTable.clearSelection();
                refreshAttendanceTable();
            } 
            catch (SQLException ex) 
            {
                JOptionPane.showMessageDialog(null, "Error occurred while Deleting Tuple: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } 
            catch (Exception e) 
            {

            }
        }
    }//GEN-LAST:event_Delete_BtnActionPerformed

    private void ClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearBtnActionPerformed
        id.setText("Will Be Added Automatically");
        day.setDate(Date.from(Instant.now()));

        // Clear the selected item in the combo box if an item is selected
        if (employeeID.getSelectedItem() != null) 
        {
            employeeID.setSelectedIndex(-1);
        }
        emp_name.setText("");

        present.setSelected(true);
        absent.setSelected(false);
    }//GEN-LAST:event_ClearBtnActionPerformed

    private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutBtnActionPerformed
        //Logout Button 
        this.dispose();
        Loginform obj = new Loginform();
        obj.show();
    }//GEN-LAST:event_logOutBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        //Exit Button
        System.exit(0);
    }//GEN-LAST:event_exitBtnActionPerformed

    private void search_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_ButtonActionPerformed
        String searchText = searchTxt.getText().trim();
        
        // Check if the search text is not empty
        if (!searchText.isEmpty())
        {
            try 
            {
                Statement st = DBconnection.createDBconnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM attendance WHERE (empName LIKE '%" + searchText + "%') || (empID LIKE '%" + searchText + "%') || (date LIKE '%" + searchText + "%')");

                // Create a table model to hold the search results
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Attendance ID");
                model.addColumn("Date");
                model.addColumn("Employee ID");
                model.addColumn("Employee Name");
                model.addColumn("Attendance");
                
                 // Add the search results to the table model
                while (rs.next()) 
                {
                    Object[] row = 
                    {
                        rs.getInt("attendanceID"),
                        rs.getString("date"),
                        rs.getString("empID"),
                        rs.getString("empName"),
                        rs.getString("attendance")
                    };
                    model.addRow(row);
                }
                
                // Set the table model to the JTable
                attendanceTable.setModel(model);
                
            } 
            catch (SQLException e) 
            {
                JOptionPane.showMessageDialog(null, "Error Occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } 
        else 
        {
            // If search text is empty, refresh the table with all suppliers
            refreshAttendanceTable();
        }
    }//GEN-LAST:event_search_ButtonActionPerformed

    private void searchTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTxtActionPerformed

    private void ExitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitBtnActionPerformed
        //Exit Button
        System.exit(0);
    }//GEN-LAST:event_ExitBtnActionPerformed

    private void LogoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutBtnActionPerformed
        //Logout Button
        this.dispose();
        Loginform obj = new Loginform();
        obj.show();
    }//GEN-LAST:event_LogoutBtnActionPerformed

    private int EmployeeIDofTheSelectedRow;
    
    private void attendanceTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_attendanceTableMouseClicked
        // Get the index of the selected row
        int selectedRow = attendanceTable.getSelectedRow();

        // Check if a row is selected
        if (selectedRow == -1) 
        {
            return;
        }

        // Get the table model
        TableModel model = attendanceTable.getModel();

        // Store the original Employee ID
        EmployeeIDofTheSelectedRow = Integer.parseInt(model.getValueAt(selectedRow, 2).toString());

        // Populate the input fields with data from the selected row
        id.setText(model.getValueAt(selectedRow, 0).toString()); // attendanceID

        // Parse date from string to Date object
        try 
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date selectedDate = dateFormat.parse(model.getValueAt(selectedRow, 1).toString());
            day.setDate(selectedDate); // date
        } 
        catch (ParseException ex) 
        {
            // Handle parsing exception
            ex.printStackTrace();
        }

        // Set selected Employee ID in the combo box
        String selectedEmpID = model.getValueAt(selectedRow, 2).toString();
        employeeID.setSelectedItem(selectedEmpID);

        // Populate employee name based on selected employee ID
        String employeeName = getEmployeeNameByEmpID(Integer.parseInt(selectedEmpID));
        emp_name.setText(employeeName);

        // Set the attendance status radio buttons
        String attendance = model.getValueAt(selectedRow, 4).toString();
        if (attendance.equalsIgnoreCase("Present")) 
        {
            present.setSelected(true);
            absent.setSelected(false);
        } 
        else if (attendance.equalsIgnoreCase("Absent")) 
        {
            present.setSelected(false);
            absent.setSelected(true);
        } 
        else 
        {
            present.setSelected(false);
            absent.setSelected(false);
        }
    }//GEN-LAST:event_attendanceTableMouseClicked

    private void presentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_presentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_presentActionPerformed

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmployeeAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeAttendance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackBtn;
    private javax.swing.JButton ClearBtn;
    private javax.swing.JButton Delete_Btn;
    private javax.swing.JButton ExitBtn;
    private javax.swing.JButton LogoutBtn;
    private javax.swing.JButton Update_Btn;
    private javax.swing.JRadioButton absent;
    private javax.swing.JButton addBtn;
    private javax.swing.JTable attendanceTable;
    private com.toedter.calendar.JDateChooser day;
    private javax.swing.JTextField emp_name;
    private javax.swing.JComboBox<String> employeeID;
    private javax.swing.JButton exitBtn;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton logOutBtn;
    private javax.swing.JRadioButton present;
    private javax.swing.JTextField searchTxt;
    private javax.swing.JButton search_Button;
    // End of variables declaration//GEN-END:variables
}
