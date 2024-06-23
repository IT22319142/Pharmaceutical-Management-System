package pharmacy;
//@author mashi

import com.sun.jdi.connect.spi.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.sql.PreparedStatement;
 
public class StocksDetails extends javax.swing.JFrame 
{
//Creates new form StocksDetails
     
    public StocksDetails() 
    {
        initComponents();
        refreshStockTable();
        
        //The method to populate the medicine ID combo box
        getMedicineIDs();
        addMedicineIDListener();
        
        stock_ID.setText("Will Be Added Automatically");
        quantity.setText("");
        medicine_name.setText("0");
        storage_Location.setText("");
        manufactured_date.setDate(new Date());
        expired_date.setDate(new Date());
        days_to_expire.setText("");
    }

    private void addMedicineIDListener() 
    {
        medicine_ID.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // When a new medicine ID is selected, populate the medicine name text field
                populateMedicineName();
                populateStorageLocation();
            }
        });
    }
        private void populateMedicineName() 
        {
        try 
        {
            int selectedMedicineID = Integer.parseInt((String) medicine_ID.getSelectedItem());

            // Query the database to retrieve the medicine name based on selected ID
            Statement st = DBconnection.createDBconnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT med_name FROM medicine WHERE med_ID = " + selectedMedicineID);

            if (rs.next()) 
            {
                // Populate the medicine name text field with the retrieved name
                String medicineName = rs.getString("med_name");
                medicine_name.setText(medicineName);
            }
            rs.close();
            st.close();
        } 
        catch (NumberFormatException | SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error retrieving medicine name: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void populateStorageLocation() 
    {
        try 
        {
            int selectedMedicineID = Integer.parseInt(medicine_ID.getSelectedItem().toString());
            String storageLocation = ""; // Initialize storage location

            // Fetch storage location from the database based on the selected medicine ID
            String query = "SELECT Storage_Location FROM medicine WHERE med_ID = ?";
            PreparedStatement prepSt = DBconnection.createDBconnection().prepareStatement(query);
            prepSt.setInt(1, selectedMedicineID);
            ResultSet rs = prepSt.executeQuery();

            if (rs.next())
            {
                storageLocation = rs.getString("Storage_Location");
            }

        // Set the storage location in the storage_Location text field
        storage_Location.setText(storageLocation);
    } 
        catch (NumberFormatException | SQLException e) 
        {
            // Handle exceptions
            JOptionPane.showMessageDialog(null, "Error fetching storage location: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
}    
        
    // Populate the medicine IDs combo box
       private void getMedicineIDs()
       {
           try
           {
               Statement st = DBconnection.createDBconnection().createStatement();
               ResultSet rst = st.executeQuery("SELECT med_ID FROM medicine");
               
               //Clear existing items in the combo box
               medicine_ID.removeAllItems();
               
               //Populate combo box with medicine IDs
               while (rst.next())
               {
                   //Store med_IDs in the combo box
                   String item = String.valueOf(rst.getInt("med_ID"));
                   medicine_ID.addItem(item);
               }
               
               //Close resources
               rst.close();
               st.close();
               DBconnection.createDBconnection().close();
           }
           
           catch (SQLException ex)
           {
               //Handle exceptions
               JOptionPane.showMessageDialog(null, "Error fetching Medicine IDs: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        logOutBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        addBtn1 = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        stock_table = new javax.swing.JTable();
        stockSearchBar = new javax.swing.JTextField();
        updateBtn1 = new javax.swing.JButton();
        stockID = new javax.swing.JLabel();
        stock_ID = new javax.swing.JTextField();
        medID4 = new javax.swing.JLabel();
        quantity = new javax.swing.JTextField();
        medID = new javax.swing.JLabel();
        medicine_ID = new javax.swing.JComboBox<>();
        medID1 = new javax.swing.JLabel();
        medicine_name = new javax.swing.JTextField();
        medID6 = new javax.swing.JLabel();
        storage_Location = new javax.swing.JTextField();
        medID7 = new javax.swing.JLabel();
        manufactured_date = new com.toedter.calendar.JDateChooser();
        medID8 = new javax.swing.JLabel();
        expired_date = new com.toedter.calendar.JDateChooser();
        medID9 = new javax.swing.JLabel();
        days_to_expire = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        BackBtn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        demoBtn = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 153, 102));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(0, 153, 102));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 102));
        jLabel3.setText("Stock Details");

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

        addBtn1.setBackground(new java.awt.Color(102, 204, 0));
        addBtn1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addBtn1.setText("Add");
        addBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtn1ActionPerformed(evt);
            }
        });

        updateBtn.setBackground(new java.awt.Color(102, 204, 0));
        updateBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        deleteBtn.setBackground(new java.awt.Color(102, 204, 0));
        deleteBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        clearBtn.setBackground(new java.awt.Color(102, 204, 0));
        clearBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel4.setText("Available Stock List");

        stock_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Stock ID", "Quantity", "Medicine ID", "Medicine Name", "Storage Location", "Manufactured Date", "Expiration Date", "Days To Expire"
            }
        ));
        stock_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stock_tableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(stock_table);

        stockSearchBar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                stockSearchBarKeyPressed(evt);
            }
        });

        updateBtn1.setBackground(new java.awt.Color(102, 204, 0));
        updateBtn1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        updateBtn1.setText("Search");
        updateBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtn1ActionPerformed(evt);
            }
        });

        stockID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        stockID.setText("Stock ID");

        stock_ID.setText("Will be generated automatically");
        stock_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stock_IDActionPerformed(evt);
            }
        });

        medID4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        medID4.setText("Quantity");

        medID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        medID.setText("Medicine ID");

        medicine_ID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        medID1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        medID1.setText("Medicine Name");

        medID6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        medID6.setText("Storage Location");

        storage_Location.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storage_LocationActionPerformed(evt);
            }
        });

        medID7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        medID7.setText("Manufactured Date");

        medID8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        medID8.setText("Expiration Date");

        medID9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        medID9.setText("Days To Expire");

        days_to_expire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                days_to_expireActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(494, 494, 494)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logOutBtn)
                .addGap(2, 2, 2)
                .addComponent(exitBtn)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(stockSearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(updateBtn1))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stockID)
                            .addComponent(medID4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(medID)
                            .addComponent(medID1)
                            .addComponent(addBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(quantity, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(stock_ID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                                        .addComponent(medicine_ID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(medicine_name, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(71, 71, 71)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(181, 181, 181)
                                        .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(medID6, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(medID7)
                                            .addComponent(medID8)
                                            .addComponent(medID9))
                                        .addGap(38, 38, 38)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(expired_date, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(manufactured_date, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(storage_Location, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(days_to_expire, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addComponent(updateBtn)))))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(391, 391, 391))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logOutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stockID)
                    .addComponent(stock_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(medID6)
                    .addComponent(storage_Location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(medID4)
                            .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(medID7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(medID)
                            .addComponent(medicine_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(medID8)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(manufactured_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(expired_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(medID1)
                            .addComponent(medicine_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(medID9))
                        .addGap(95, 95, 95)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addBtn1)
                            .addComponent(updateBtn)
                            .addComponent(deleteBtn)
                            .addComponent(clearBtn))
                        .addGap(35, 35, 35)
                        .addComponent(jLabel4)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stockSearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateBtn1))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(days_to_expire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Medicare Pharmaceuticals");

        jLabel2.setIcon(new javax.swing.ImageIcon("D:\\Pharmacy Management System\\Pharmacy\\Images\\stocks.png")); // NOI18N

        BackBtn.setBackground(new java.awt.Color(51, 204, 0));
        BackBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BackBtn.setText("Back");
        BackBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackBtnActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(51, 204, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Re Order");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        demoBtn.setBackground(new java.awt.Color(51, 204, 0));
        demoBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        demoBtn.setText("Demo");
        demoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demoBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addComponent(BackBtn)
                            .addComponent(demoBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BackBtn)
                .addGap(18, 18, 18)
                .addComponent(demoBtn)
                .addGap(127, 127, 127)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BackBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackBtnActionPerformed
        // Back Button -> navigates to the Dashboard
        this.dispose();
        MedicineInventory obj = new MedicineInventory();
        obj.show();
    }//GEN-LAST:event_BackBtnActionPerformed

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

    private void addBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtn1ActionPerformed
    String medicineName = medicine_name.getText();
    String storageLocation = storage_Location.getText();
    Date manufacturedDate = manufactured_date.getDate();
    Date expiredDate = expired_date.getDate();
    String daysToExpire = days_to_expire.getText();
    int quantity = 0; 
    int medicineID = 0;

    try 
    {
        quantity = Integer.parseInt(this.quantity.getText());

        medicineID = Integer.parseInt(medicine_ID.getSelectedItem().toString());

        Statement st = pharmacy.DBconnection.createDBconnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT MAX(stock_ID) FROM stock");
        int nextStockID = 1;

        populateMedicineName();
        populateStorageLocation();
        if (rs.next()) 
        {
            nextStockID = rs.getInt(1) + 1;
        }

        boolean isAlreadyExist = false; 
        ResultSet rsExist = st.executeQuery("SELECT COUNT(*) AS count FROM `medicine` WHERE `med_name` = '" + medicineName + "' AND `Storage_Location` = '" + storageLocation + "'");
        if (rsExist.next()) 
        {
            int countExist = rsExist.getInt("count");
            if (countExist > 0) 
            {
                isAlreadyExist = true;
            }
        }

        int count = 0;
        int med_updated = 0;
        if (!isAlreadyExist) 
        {
            med_updated = st.executeUpdate("INSERT INTO `medicine`(`med_name`, `qty`, `Storage_Location`, `dateAdded`) VALUES ('" + medicineName + "','" + quantity + "','" + storageLocation + "', CURDATE())");
            count = st.executeUpdate("INSERT INTO `stock`(`stock_ID`, `qty`, `medID`, `medName`, `storage_location`, `manf_date`, `exp_date`, `daysToExpire`) VALUES ('" + nextStockID + "','" + quantity + "','" + medicineID + "','" + medicineName + "','" + storageLocation + "','" + new SimpleDateFormat("yyyy-MM-dd").format(manufacturedDate) + "','" + new SimpleDateFormat("yyyy-MM-dd").format(expired_date.getDate()) + "','" + daysToExpire + "')");
        } 
        else 
        { 
            ResultSet qty = st.executeQuery("SELECT `qty` FROM `medicine` WHERE `med_name` = '" + medicineName + "' AND `Storage_Location` = '" + storageLocation + "'");
            int Newqty = 10;
            if (qty.next()) 
            {
                Newqty = qty.getInt("qty");
            }
            Newqty += quantity;

            JOptionPane.showMessageDialog(null, "Are you sure you want to 'Add' this stock item?", "Success", JOptionPane.INFORMATION_MESSAGE);
            count = st.executeUpdate("INSERT INTO `stock`(`stock_ID`, `qty`, `medID`, `medName`, `storage_location`, `manf_date`, `exp_date`, `daysToExpire`) VALUES ('" + nextStockID + "','" + quantity + "','" + medicineID + "','" + medicineName + "','" + storageLocation + "','" + new SimpleDateFormat("yyyy-MM-dd").format(manufacturedDate) + "','" + new SimpleDateFormat("yyyy-MM-dd").format(expired_date.getDate()) + "','" + daysToExpire + "')");
            count = st.executeUpdate("UPDATE `medicine` SET `qty`='"+Newqty+"' WHERE `med_name` = '"+medicineName+"' AND `Storage_Location` = '"+storageLocation+"'"); 
        }

        if (count > 0) 
        {
            JOptionPane.showMessageDialog(null, "New Stock Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            refreshStockTable();
        }
    //Validations of Error Handling
    } 
    catch (NumberFormatException e)
    {
        JOptionPane.showMessageDialog(null, "Invalid Input for Quantity or Medicine ID", "Error", JOptionPane.ERROR_MESSAGE);
    } 
    catch (SQLException e) 
    {
        JOptionPane.showMessageDialog(null, "Error occurred while adding stock: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_addBtn1ActionPerformed

    // Assuming you have a method to populate your stock table
    private void refreshStockTable()
     
     {
        try 
        {
            // Fetch updated data from the Database
            Statement st = DBconnection.createDBconnection().createStatement();
            ResultSet rs = st.executeQuery(" SELECT * FROM stock ");
           
            // Create a new DefaultTableModel with updated data
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("stock_ID");
            model.addColumn("qty");
            model.addColumn("medID");
            model.addColumn("medName");
            model.addColumn("storage_location");
            model.addColumn("manf_date");
            model.addColumn("exp_date");
            model.addColumn("daysToExpire");
            

            while (rs.next()) 
            {
                Object[] row = 
                {
                        rs.getInt("stock_ID"),
                        rs.getInt("qty"),
                        rs.getInt("medID"),
                        rs.getString("medName"),
                        rs.getString("storage_location"),
                        rs.getString("manf_date"),
                        rs.getString("exp_date"),
                        rs.getString("daysToExpire")
                        
                };
                model.addRow(row);
            }

            // Set the updated model to the medicineTable
            stock_table.setModel(model);
            
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error occurred while refreshing Table: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        try 
        {
            String med_Name, days_To_Expire, storageLocation, manufacturedDate, expiredDate;
            int Quantity, med_id;
            int Newqty = 0;
             int availableQTYinStock = 0;

            med_Name = medicine_name.getText();
            // Get medicine type from the combo box
            med_id = Integer.parseInt((String) medicine_ID.getSelectedItem());

            storageLocation = storage_Location.getText();

            populateMedicineName();
            populateStorageLocation();
            // Validating the date formatting
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            manufacturedDate = sdf.format(manufactured_date.getDate());

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            expiredDate = sdf1.format(expired_date.getDate());

            // User input validation
            Quantity = Integer.parseInt(quantity.getText());

            days_To_Expire = days_to_expire.getText();

            // Validations of Database Interaction
            try 
            {
                // Create a connection and a prepared statement
                Statement st = pharmacy.DBconnection.createDBconnection().createStatement();
                int x = Integer.parseInt(stock_ID.getText());

                String getBeforeValue = "select qty from stock where stock_ID ="+x+" ";


             java.sql.PreparedStatement prepSt4 = st.getConnection().prepareStatement(getBeforeValue);

            ResultSet result4 = prepSt4.executeQuery();

            if(result4.next())
            {
                availableQTYinStock = result4.getInt("qty");
            }
            // Validations related to SQL
            String query = "UPDATE stock SET qty = ?, medID = ?, medName = ?, storage_location = ?, manf_date = ?, exp_date = ?, daysToExpire = ? WHERE stock_ID = ?";
            
            // Use PreparedStatement to avoid SQL injection
            java.sql.PreparedStatement prepSt = st.getConnection().prepareStatement(query);

            prepSt.setInt(1, Quantity);
            prepSt.setInt(2, med_id);
            prepSt.setString(3, med_Name);
            prepSt.setString(4, storageLocation);
            prepSt.setString(5, manufacturedDate);
            prepSt.setString(6, expiredDate);
            prepSt.setString(7, days_To_Expire);
            prepSt.setInt(8, x);

            int count = prepSt.executeUpdate();

        if (count > 0) 
        {
        JOptionPane.showMessageDialog(null, "Selected Stock Details Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    
        String selectQuery1 = "Select qty from medicine where med_ID = "+med_id+"";
        
        int availableQTY = 0;
         java.sql.PreparedStatement prepSt2 = st.getConnection().prepareStatement(selectQuery1);
        
        ResultSet result = prepSt2.executeQuery();
        
        if(result.next())
        {
            availableQTY = result.getInt("qty");
        }
        int temp = (availableQTY-availableQTYinStock)+Quantity;
        
        
        String query1 = "UPDATE medicine SET qty = "+temp+" WHERE med_ID = "+med_id+" ";
    

        java.sql.PreparedStatement prepSt1 = st.getConnection().prepareStatement(query1);
    
        int count1 = prepSt1.executeUpdate();
    
        if (count1 > 0) 
        {
            refreshStockTable();
        }
        // Refresh the Stock Table after updating
        }
        else 
        {
                JOptionPane.showMessageDialog(null, "Failed to update Stock Details", "Error", JOptionPane.ERROR_MESSAGE);
        }
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error occurred while Updating the selected stock's details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        } 
        catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(null, "Invalid Input for Quantity or Medicine ID!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_updateBtnActionPerformed

    private void clearFormFields() 
    {
            // Clear all form fields and enable them for editing
            stock_ID.setText("Will Be Added Automatically");
            storage_Location.setText("");
            manufactured_date.setDate(new Date());
            expired_date.setDate(new Date());

            // Enable fields for editing
            stock_ID.setEnabled(true);
            medicine_name.setEnabled(true);
            medicine_ID.setEnabled(true);
            quantity.setEnabled(true);
            addBtn1.setEnabled(true);
   }
        
    
    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
         try {
        // Retrieve necessary information
        int med_id = Integer.parseInt((String) medicine_ID.getSelectedItem());
        int Quantity = Integer.parseInt(quantity.getText());
        int stock_id = Integer.parseInt(stock_ID.getText());
        int availableQTYinStock = 0;

        // Fetch current quantity in stock for the selected medicine
        String getBeforeValue = "SELECT qty FROM stock WHERE stock_ID = ?";
        PreparedStatement prepSt4 = pharmacy.DBconnection.createDBconnection().prepareStatement(getBeforeValue);
        prepSt4.setInt(1, stock_id);
        ResultSet result4 = prepSt4.executeQuery();
        
        if(result4.next()){
            availableQTYinStock = result4.getInt("qty");
        }
        
        // SQL query to delete the stock
        String deleteQuery = "DELETE FROM stock WHERE stock_ID = ?";
        PreparedStatement prepStDelete = pharmacy.DBconnection.createDBconnection().prepareStatement(deleteQuery);
        prepStDelete.setInt(1, stock_id);

        // Execute the delete query
        int deleteCount = prepStDelete.executeUpdate();

        // If stock is deleted successfully, update medicine quantity
        if (deleteCount > 0) {
            // Calculate the new quantity in medicine table
            int newQty = availableQTYinStock - Quantity;
            
            // Update medicine table with new quantity
            String updateMedicineQuery = "UPDATE medicine SET qty = qty - ? WHERE med_ID = ?";
            PreparedStatement prepStUpdateMedicine = pharmacy.DBconnection.createDBconnection().prepareStatement(updateMedicineQuery);
            prepStUpdateMedicine.setInt(1, Quantity);
            prepStUpdateMedicine.setInt(2, med_id);

            // Execute the update query for medicine table
            int updateMedicineCount = prepStUpdateMedicine.executeUpdate();

            // Refresh stock table after successful deletion
            if (updateMedicineCount > 0) {
                refreshStockTable();
                JOptionPane.showMessageDialog(null, "Stock deleted successfully and Medicine quantity updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update Medicine quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Failed to delete Stock.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid Input for Stock ID or Quantity!", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error occurred while deleting stock and updating medicine quantity: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
        
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
    // Clear all form fields and reset them to default values
    stock_ID.setText("Will Be Added Automatically");
    quantity.setText("");
    medicine_ID.setSelectedIndex(-1); // Clear the selection
    medicine_name.setText(""); // Clear the medicine name field
    storage_Location.setText(""); // Clear the storage location field
    manufactured_date.setDate(new Date());
    expired_date.setDate(new Date());
    days_to_expire.setText("");
    }//GEN-LAST:event_clearBtnActionPerformed

    private void updateBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateBtn1ActionPerformed

    private void stock_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stock_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stock_IDActionPerformed

    private void storage_LocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storage_LocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_storage_LocationActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Re Order Page Button
        ReOrdering obj = new ReOrdering();
        obj.show();
       // Close the Order Management Page
       this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void stock_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stock_tableMouseClicked
        //Get the index of the selected row
        int selectedRow = stock_table.getSelectedRow();
        
        //Establish the table model that represents the structure & data of the table
        TableModel model = stock_table.getModel(); 
        
        //Validations to ensure that User interacts with the selected row data
        //Set the First Name,Last Name,Date Of Birth,Gender,Email,Job Position,Joined Date,Salary & Contact Number text fields to the value from the selected row
        quantity.setText(model.getValueAt(selectedRow, 1).toString()); 
        
        // Set selected job role in the combo box
        String selectedMediID = model.getValueAt(selectedRow, 2).toString();
        DefaultComboBoxModel<String> comboBoxModel = (DefaultComboBoxModel<String>) medicine_ID.getModel();
        
        if (comboBoxModel != null) 
        {
            medicine_ID.setSelectedItem(selectedMediID);
        }
        
        medicine_name.setText(model.getValueAt(selectedRow, 3).toString());
        
        storage_Location.setText(model.getValueAt(selectedRow, 4).toString());
        
        //Set the Date Format for ManufacturedDate
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String manufacturedDate = (String) model.getValueAt(selectedRow, 5);
        //Set the Date Format for ExpiredDate
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String expiredDate = (String) model.getValueAt(selectedRow, 6);
        
         
        //Retrieve the Stock ID from the First Column(index 0)of the selected row & store it
        stock_ID.setText(model.getValueAt(selectedRow, 0).toString());
        
        try 
        {
          //Handle the parsing of the Birth Date
          Date manf_Date = sdf.parse(manufacturedDate); 
          manufactured_date.setDate(manf_Date);
          
          
          
          //Handle the parsing of the Joined Date
          Date exp_Date = sdf1.parse(expiredDate); 
          expired_date.setDate(exp_Date);
          
        
            // Disable Date of Birth and Start Date fields when user tries to update an existing data tuple
            manufactured_date.setEnabled(false);
            expired_date.setEnabled(false);
        } 
        catch (ParseException e) 
        {
            // Handle the parsing exception 
            e.printStackTrace();
        }
        

        days_to_expire.setText(model.getValueAt(selectedRow, 7).toString());
    }//GEN-LAST:event_stock_tableMouseClicked

    private void stockSearchBarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stockSearchBarKeyPressed
        // Get the text from the search bar
    String searchText = stockSearchBar.getText().trim();

    // Get the table model of your medicine table
    DefaultTableModel model = (DefaultTableModel) stock_table.getModel();

    // Create a row sorter for the table model
    TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
    
    // Set the row sorter to the table
    stock_table.setRowSorter(rowSorter);

    // Apply the filter to show rows that contain the search text
    if (searchText.length() == 0) {
        // If the search text is empty, show all rows
        rowSorter.setRowFilter(null);
    } else {
        // Otherwise, show rows that contain the search text
        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText)); // Case insensitive filter
    }
    }//GEN-LAST:event_stockSearchBarKeyPressed

    private void days_to_expireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_days_to_expireActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_days_to_expireActionPerformed

    private void demoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demoBtnActionPerformed
        quantity.setText("150");
        
        //medicine_name.setText("Panadol");
        storage_Location.setText("A602");
        //manufactured_date.setDate(new Date());
        //expired_date.setDate(new Date());
        days_to_expire.setText("365 days");
    }//GEN-LAST:event_demoBtnActionPerformed

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
            java.util.logging.Logger.getLogger(StocksDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StocksDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StocksDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StocksDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StocksDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackBtn;
    private javax.swing.JButton addBtn1;
    private javax.swing.JButton clearBtn;
    private javax.swing.JTextField days_to_expire;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton demoBtn;
    private javax.swing.JButton exitBtn;
    private com.toedter.calendar.JDateChooser expired_date;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton logOutBtn;
    private com.toedter.calendar.JDateChooser manufactured_date;
    private javax.swing.JLabel medID;
    private javax.swing.JLabel medID1;
    private javax.swing.JLabel medID4;
    private javax.swing.JLabel medID6;
    private javax.swing.JLabel medID7;
    private javax.swing.JLabel medID8;
    private javax.swing.JLabel medID9;
    private javax.swing.JComboBox<String> medicine_ID;
    private javax.swing.JTextField medicine_name;
    private javax.swing.JTextField quantity;
    private javax.swing.JLabel stockID;
    private javax.swing.JTextField stockSearchBar;
    private javax.swing.JTextField stock_ID;
    private javax.swing.JTable stock_table;
    private javax.swing.JTextField storage_Location;
    private javax.swing.JButton updateBtn;
    private javax.swing.JButton updateBtn1;
    // End of variables declaration//GEN-END:variables
}
