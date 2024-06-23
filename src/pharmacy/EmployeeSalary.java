package pharmacy;
// @author thath

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class EmployeeSalary extends javax.swing.JFrame 
{
// Creates new form EmployeeSalary
    
    //Flag to prevent infinite recursion
    private boolean ignoreMonthChangeEvent = false;
    
    public EmployeeSalary() 
    {
        initComponents();
        refreshSalaryTable();

        //The method to populate the employeeID combo box
        getEmployeeIDs();
        
        employID.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try 
                {
                    // Get the selected employee ID from the combo box
                    int selectedEmpID = Integer.parseInt(employID.getSelectedItem().toString());

                    // Retrieve the employee's name and daily rate based on the selected employee ID
                    String employee_Name = getEmployeeNameByEmployeeID(selectedEmpID);
                    int employeeDailyRate = getEmployeeDailyRateByEmployeeID(selectedEmpID);

                    // Display the employee's name and daily rate in respective text fields or labels
                    employeeName.setText(employee_Name);
                    daily_rate.setText(String.valueOf(employeeDailyRate));
                } 
                catch (NumberFormatException ex) 
                {
                    // Handle number format exception if selectedEmpID is invalid
                    ex.printStackTrace();
                }
            }
        });
       
        id.setText("Will Be Added Automatically");
        employeeName.setText("");
        daily_rate.setText("0");
        Year.setText("");
        net_salary.setText("");
        Attendance.setText("0");
    }
    
    private void getEmployeeIDs() 
    {
        try
        {
            Statement st = DBconnection.createDBconnection().createStatement();
            ResultSet rst = st.executeQuery("SELECT emp_ID FROM employee");

            // Clear existing items in the combo box
            employID.removeAllItems();

            // Populate combo box with employee IDs
            while (rst.next()) 
            {
                // Store emp_IDs in the combo box
                String item = String.valueOf(rst.getInt("emp_ID"));
                employID.addItem(item);
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

    private String getEmployeeNameByEmployeeID(int employeeID) 
    {
        String employeeName = null;
        try 
        {
            Statement st = DBconnection.createDBconnection().createStatement();
            ResultSet rst = st.executeQuery("SELECT f_name FROM employee WHERE emp_ID = " + employeeID);
            if (rst.next()) 
            {
                employeeName = rst.getString("f_name");
            }
            rst.close();
            st.close();
            DBconnection.createDBconnection().close();
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error occurred while fetching the employee name: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return employeeName;
    }

    private int getEmployeeDailyRateByEmployeeID(int employeeID) 
    {
        int dailyRate = 0;

        try 
        {
            Statement st = DBconnection.createDBconnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT dailyRate FROM employee WHERE emp_ID = " + employeeID);

            if (rs.next()) 
            {
                dailyRate = rs.getInt("dailyRate");
            }

            rs.close();
            st.close();
            DBconnection.createDBconnection().close();
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error occurred while fetching the employee's daily rate: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return dailyRate;
    }
    // Refresh the employee_salary_Table after updating/deleting the selected salary details
    private void refreshSalaryTable() 
    {
        try 
        {
            Statement st = DBconnection.createDBconnection().createStatement();
            ResultSet rs = st.executeQuery(" SELECT * FROM salary ");

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Salary ID");
            model.addColumn("Employee ID");
            model.addColumn("Employee Name");
            model.addColumn("Daily Rate");
            model.addColumn("Year");
            model.addColumn("Month");
            model.addColumn("Attendance");
            model.addColumn("Net Salary");

            while (rs.next()) 
            {
                Object[] row = 
                {
                    rs.getInt("salaryID"),
                    rs.getInt("empID"),
                    null, // Placeholder for employee name
                    rs.getInt("dailyRate"),
                    rs.getInt("year"),
                    rs.getString("Month"),
                    rs.getInt("attendance"),
                    rs.getInt("netSalary")
                };
                String employeeName = rs.getString("empName");
                System.out.println("Retrieved employee name: " + employeeName);
                row[2] = employeeName; // Assign the retrieved employee name
                model.addRow(row);
                System.out.println("Row added to table.");
            }
            employee_Salary_Table.setModel(model);
            rs.close();
            st.close();
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while refreshing Table: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
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
        logOutBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        net_salary = new javax.swing.JTextField();
        daily_rate = new javax.swing.JTextField();
        Attendance = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        employeeName = new javax.swing.JTextField();
        employID = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        employee_Salary_Table = new javax.swing.JTable();
        searchTxt = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        UpdateBtn = new javax.swing.JButton();
        AddBtn = new javax.swing.JButton();
        DeleteBtn = new javax.swing.JButton();
        ClearBtn = new javax.swing.JButton();
        updateBtn2 = new javax.swing.JButton();
        month = new com.toedter.calendar.JMonthChooser();
        jLabel11 = new javax.swing.JLabel();
        Year = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 153, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Medicare Pharmaceuticals");

        jLabel3.setIcon(new javax.swing.ImageIcon("D:\\Pharmacy Management System\\Pharmacy\\Images\\salary.png")); // NOI18N
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
        jLabel2.setText("Employee Salary Details");

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

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Employee ID");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Salary ID");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Employee Name");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Month");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Attendance");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Daily Rate");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Net Salary");

        id.setEditable(false);

        employID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        employID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employIDActionPerformed(evt);
            }
        });

        employee_Salary_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Salary ID", "Employee ID", "Employee Name", "Daily Rate", "Year", "Month", "Attendance", "Net Salary"
            }
        ));
        employee_Salary_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employee_Salary_TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(employee_Salary_Table);

        searchButton.setBackground(new java.awt.Color(102, 204, 0));
        searchButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        UpdateBtn.setBackground(new java.awt.Color(102, 204, 0));
        UpdateBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        UpdateBtn.setText("Update");
        UpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBtnActionPerformed(evt);
            }
        });

        AddBtn.setBackground(new java.awt.Color(102, 204, 0));
        AddBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        AddBtn.setText("Add");
        AddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBtnActionPerformed(evt);
            }
        });

        DeleteBtn.setBackground(new java.awt.Color(102, 204, 0));
        DeleteBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        DeleteBtn.setText("Delete");
        DeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtnActionPerformed(evt);
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

        updateBtn2.setBackground(new java.awt.Color(102, 204, 0));
        updateBtn2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        updateBtn2.setText("Generate Report");
        updateBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtn2ActionPerformed(evt);
            }
        });

        month.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                monthPropertyChange(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Year");

        Year.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                YearKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel12.setText("Salary Table");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(logOutBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitBtn)
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(employID, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(employeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(daily_rate, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(net_salary, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                .addComponent(Attendance, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                .addComponent(month, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(Year, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(422, 422, 422)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 989, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(153, 153, 153)
                                .addComponent(AddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101)
                                .addComponent(UpdateBtn)
                                .addGap(107, 107, 107)
                                .addComponent(DeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(119, 119, 119)
                                .addComponent(ClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 46, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateBtn2)
                        .addGap(69, 69, 69))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logOutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(employID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(employeeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(Attendance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(net_salary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(daily_rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddBtn)
                    .addComponent(UpdateBtn)
                    .addComponent(DeleteBtn)
                    .addComponent(ClearBtn))
                .addGap(36, 36, 36)
                .addComponent(jLabel12)
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton)
                    .addComponent(updateBtn2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(BackBtn)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1034, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(40, 40, 40)
                .addComponent(BackBtn)
                .addContainerGap(516, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1271, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String searchText = searchTxt.getText().trim();
        
        // Check if the search text is not empty
        if (!searchText.isEmpty())
        {
            try 
            {
                Statement st = DBconnection.createDBconnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM salary WHERE empName LIKE '%" + searchText + "%'");

                // Create a table model to hold the search results
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Salary ID");
                model.addColumn("Employee ID");
                model.addColumn("Employee Name");
                model.addColumn("Daily Rate");
                model.addColumn("Year");
                model.addColumn("Month");
                model.addColumn("Attendance");
                model.addColumn("Net Salary");
                
                 // Add the search results to the table model
                while (rs.next()) 
                {
                    Object[] row = 
                    {
                        rs.getInt("salaryID"),
                        rs.getInt("empID"),
                        rs.getString("empName"),
                        rs.getInt("dailyRate"),
                        rs.getInt("year"),
                        rs.getInt("Month"),
                        rs.getInt("attendance"),
                        rs.getInt("netSalary")
                    };
                    model.addRow(row);
                }
                
                // Set the table model to the JTable
                employee_Salary_Table.setModel(model);
                
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
            refreshSalaryTable();
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void UpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBtnActionPerformed
        //Update Button
        try 
        {
            String Employee_Name;
            int Employee_ID, AttendanceCount, selectedYear, Daily_Rate, Net_Salary;

            Employee_Name = employeeName.getText();
            Employee_ID = Integer.parseInt((String) employID.getSelectedItem());
            Daily_Rate = Integer.parseInt(daily_rate.getText());
            selectedYear = Integer.parseInt(Year.getText());
            AttendanceCount = getAttendanceCountForMonth(Employee_ID, month.getMonth() + 1, selectedYear); // Assuming 'month' is a JMonthChooser
            Net_Salary = AttendanceCount * Daily_Rate;

            // Create the SQL query for updating the salary record
            String query = "UPDATE salary SET empID = ?, empName = ?, dailyRate = ?, year = ?, Month = ?, attendance = ?, netSalary = ? WHERE salaryID = ?";

            // Establish database connection and prepare the statement
            java.sql.Connection connection = pharmacy.DBconnection.createDBconnection();
            java.sql.PreparedStatement prepSt = connection.prepareStatement(query);

            prepSt.setInt(1, Employee_ID);
            prepSt.setString(2, Employee_Name);
            prepSt.setInt(3, Daily_Rate);
            prepSt.setInt(4, selectedYear);
            prepSt.setInt(5, month.getMonth() + 1); // Assuming 'month' is a JMonthChooser
            prepSt.setInt(6, AttendanceCount);
            prepSt.setInt(7, Net_Salary);
            prepSt.setInt(8, Integer.parseInt(id.getText()));

            // Execute the update statement
            int rowCount = prepSt.executeUpdate();

            // Check if update was successful
            if (rowCount > 0) 
            {
                JOptionPane.showMessageDialog(null, "Salary Details Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshSalaryTable();
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Failed to update Salary Details", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Close the connection and prepared statement
            prepSt.close();
            connection.close();
        } 
        catch (NumberFormatException ex) 
        {
            JOptionPane.showMessageDialog(null, "Invalid Input for Employee ID, Year, or Daily Rate", "Error", JOptionPane.ERROR_MESSAGE);
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error occurred while updating Salary Details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_UpdateBtnActionPerformed

    private void AddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBtnActionPerformed
        try 
        {
            String employee_Name;
            int employee_ID, AttendanceCount, SelectedYear, selectedMonth, Daily_Rate, Net_Salary;

            // Validate year input
            if (Year.getText().isEmpty()) 
            {
                JOptionPane.showMessageDialog(null, "Year cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Fetch other necessary inputs
            employee_ID = Integer.parseInt(employID.getSelectedItem().toString());
            Daily_Rate = Integer.parseInt(daily_rate.getText());
            SelectedYear = Integer.parseInt(Year.getText());

            // Get the selected month from JMonthChooser
            selectedMonth = month.getMonth() + 1; // Month is 0-based
            
            // Checking if salary details already exist for the selected month and year
            if (checkWhetherSalaryDetailsAlreadyExist(employee_ID, selectedMonth, SelectedYear)) 
            {
                JOptionPane.showMessageDialog(null, "For this Employee, Salary Details of the selected Month and Year are already added to the database", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            //Get Attendance count for the selected month
            AttendanceCount = getAttendanceCountForMonth(employee_ID, selectedMonth, SelectedYear);

            // Calculate the Monthly salary
            Net_Salary = AttendanceCount * Daily_Rate;

            // Get the employee name (if not retrieved already)
            employee_Name = employeeName.getText();

            //Validations of Database Interaction
            try 
            {
                // Fetch the next available Salary ID from the Database
                int nextSalaryID = 1;
                Statement st = pharmacy.DBconnection.createDBconnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT MAX(salaryID) FROM salary");
                
                if (rs.next()) 
                {
                    nextSalaryID = rs.getInt(1) + 1;
                }

                // Prepare and execute SQL insert statement
                String query = "INSERT INTO `salary`(`salaryID`, `empID`, `empName`, `dailyRate`, `year`, `Month`, `attendance`, `netSalary`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                java.sql.PreparedStatement prepSt = st.getConnection().prepareStatement(query);
                prepSt.setInt(1, nextSalaryID);
                prepSt.setInt(2, employee_ID);
                prepSt.setString(3, employee_Name);
                prepSt.setInt(4, Daily_Rate);
                prepSt.setInt(5, SelectedYear);
                prepSt.setInt(6, selectedMonth);
                prepSt.setInt(7, AttendanceCount);
                prepSt.setInt(8, Net_Salary);

                // Execute update
                int count = prepSt.executeUpdate();
                if (count > 0) 
                {
                    JOptionPane.showMessageDialog(null, "Salary Details Added to the Database Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    refreshSalaryTable();
                }

                // Close resources
                prepSt.close();
                rs.close();
                st.close();
            } 
            catch (SQLException ex) 
            {
                JOptionPane.showMessageDialog(null, "SQL Error...!" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } 
            catch (NumberFormatException ex) 
            {
                JOptionPane.showMessageDialog(null, "Invalid Number Format...!" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } 
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_AddBtnActionPerformed

    // Method to get attendance count for a specific month and year
    private int getAttendanceCountForMonth(int employeeID, int month, int year) 
    {
        int attendanceCount = 0;
        try 
        {
            // Constructing the SQL query
            String query = "SELECT COUNT(*) FROM attendance WHERE empID = ? AND MONTH(date) = ? AND YEAR(date) = ? AND attendance = '1'";

            // Debug
            System.out.println("Generated SQL query: " + query);

            java.sql.PreparedStatement prepSt = DBconnection.createDBconnection().prepareStatement(query);

            prepSt.setInt(1, employeeID);
            prepSt.setInt(2, month);
            prepSt.setInt(3, year);

            //Print the parameters being set
            System.out.println("Employee ID: " + employeeID + ", Month: " + month + ", Year: " + year);

            // Execute the query
            ResultSet rs = prepSt.executeQuery();
 
            if (rs.next()) 
            {
                attendanceCount = rs.getInt(1);
            }
            rs.close();
            prepSt.close();
        } 
        catch (SQLException ex) 
        {
            // Error Handling: Display error message and print stack trace
            JOptionPane.showMessageDialog(null, "Error occurred while fetching attendance count: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        return attendanceCount;
    }

    //Check if salary details already exist for a specific employee, month, and year
    private boolean checkWhetherSalaryDetailsAlreadyExist(int employeeID, int month, int year) 
    {
        boolean exist = false;
        
        try 
        {
            String query = "SELECT COUNT(*) FROM salary WHERE empID = ? AND `Month` = ? AND `year` = ?";
            
            java.sql.PreparedStatement prepSt = DBconnection.createDBconnection().prepareStatement(query);
            
            prepSt.setInt(1, employeeID);
            prepSt.setInt(2, month);
            prepSt.setInt(3, year);

            ResultSet rs = prepSt.executeQuery();
            if (rs.next()) 
            {
                int count = rs.getInt(1);
                exist = count > 0;
            }
            rs.close();
            prepSt.close();
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error occurred while checking salary details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return exist;
    }



    private void DeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtnActionPerformed
        //Delete Button
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to 'Delete' this Record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) 
        {
            try 
            {
                Statement st = pharmacy.DBconnection.createDBconnection().createStatement();
                String query = "DELETE FROM `salary` WHERE `salaryID` = " + Integer.valueOf(employee_Salary_Table.getValueAt(employee_Salary_Table.getSelectedRow(), 0).toString());

                //executeUpdate Method call to execute the DELETE query & returns the No of rows deleted
                int count = st.executeUpdate(query);
                //System.out.println(query);

                //Data Validations 
                if (count > 0) 
                {
                    JOptionPane.showMessageDialog(null, "Selected Employee's Salary Details Deleted Successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Refresh the EmployeeDetailsTable after deleting
                    refreshSalaryTable();
                }

                //Deselect previous selected rows
                employee_Salary_Table.clearSelection();
                refreshSalaryTable();
            } 
            catch (SQLException ex) 
            {
                JOptionPane.showMessageDialog(null, "Error occurred while Deleting Tuple: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } 
            catch (Exception e) 
            {

            }
        }
    }//GEN-LAST:event_DeleteBtnActionPerformed

    private void ClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearBtnActionPerformed
        id.setText("Will Be Added Automatically");

        if (employID.getSelectedItem() != null) 
        {
            employID.setSelectedIndex(-1);
        }
        employeeName.setText("");
        daily_rate.setText("0");
        Year.setText("");
        Attendance.setText("");
        net_salary.setText("0");
        
        Calendar now = Calendar.getInstance();
        int currentMonthIndex = now.get(Calendar.MONTH);
        month.setMonth(currentMonthIndex);
    }//GEN-LAST:event_ClearBtnActionPerformed

    private void updateBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtn2ActionPerformed
        //Reports Page Button
        EmployeeSalaryReport obj = new EmployeeSalaryReport();
       obj.show();
       // Close the Employee Salary Page
       this.dispose();
    }//GEN-LAST:event_updateBtn2ActionPerformed

    private void BackBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackBtnActionPerformed
        // Back Button -> navigates to the Employee Management Page
        this.dispose();
        EmployeeDetails obj = new EmployeeDetails();
        obj.show();
    }//GEN-LAST:event_BackBtnActionPerformed

    private void monthPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_monthPropertyChange
      
    }//GEN-LAST:event_monthPropertyChange

    private void employIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employIDActionPerformed

    }//GEN-LAST:event_employIDActionPerformed

    private int EmployeeIDofTheSelectedRow;
    
    private void employee_Salary_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employee_Salary_TableMouseClicked
        //Get the index of the selected row
        int selectedRow = employee_Salary_Table.getSelectedRow();

        // Check if a row is selected
        if (selectedRow == -1) 
        {
            return;
        }

        // Get the table model
        TableModel model = employee_Salary_Table.getModel();

        // Store the original Employee ID
        EmployeeIDofTheSelectedRow = Integer.parseInt(model.getValueAt(selectedRow, 1).toString());

        // Populate the input fields with data from the selected row
        id.setText(model.getValueAt(selectedRow, 0).toString()); // salaryID
        employeeName.setText(model.getValueAt(selectedRow, 2).toString()); 
        daily_rate.setText(model.getValueAt(selectedRow, 3).toString());
        Year.setText(model.getValueAt(selectedRow, 4).toString());
        month.setMonth(Integer.parseInt(model.getValueAt(selectedRow, 5).toString()) - 1);
        Attendance.setText(model.getValueAt(selectedRow, 6).toString());
        net_salary.setText(model.getValueAt(selectedRow, 7).toString());
        
        // Set selected Employee ID in the combo box
        String selectedEmpID = model.getValueAt(selectedRow, 1).toString();
        employID.setSelectedItem(selectedEmpID);

        // Populate employee name based on selected employee ID
        String employee_Name = getEmployeeNameByEmployeeID(Integer.parseInt(selectedEmpID));
        employeeName.setText(employee_Name);
    }//GEN-LAST:event_employee_Salary_TableMouseClicked

    private void YearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_YearKeyPressed
        // Ensures that the user enters only numerical values for the year
        char c = evt.getKeyChar();

        // Validating the user inputs for the contact number
        if (!Character.isDigit(c) || Year.getText().length() >= 4)
        {
            evt.consume();
            // Consume the event to prevent the input
            JOptionPane.showMessageDialog(null, "Enter up to 4 numerical values for the Year", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_YearKeyPressed

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
            java.util.logging.Logger.getLogger(EmployeeSalary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeSalary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeSalary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeSalary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeSalary().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBtn;
    private javax.swing.JTextField Attendance;
    private javax.swing.JButton BackBtn;
    private javax.swing.JButton ClearBtn;
    private javax.swing.JButton DeleteBtn;
    private javax.swing.JButton UpdateBtn;
    private javax.swing.JTextField Year;
    private javax.swing.JTextField daily_rate;
    private javax.swing.JComboBox<String> employID;
    private javax.swing.JTextField employeeName;
    private javax.swing.JTable employee_Salary_Table;
    private javax.swing.JButton exitBtn;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton logOutBtn;
    private com.toedter.calendar.JMonthChooser month;
    private javax.swing.JTextField net_salary;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTxt;
    private javax.swing.JButton updateBtn2;
    // End of variables declaration//GEN-END:variables
}
