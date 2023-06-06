/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Usuario;

import Clases.Conexion;
import Usuario.Usuario;
import Usuario.DtoUsuario;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author hawri
 */
public class ListaUsuarios extends javax.swing.JInternalFrame {

    Conexion cDt = new Conexion();
    DefaultTableModel tblModel;
    DtoUsuario dtoUsuario;
    Usuario selectedUser = null;
    InternalFrameListener internalFrameListener = new InternalFrameListener() {
        @Override
        public void internalFrameClosing(InternalFrameEvent e) {
            displayMessage("internalFrameClosing", e);
            initView();
        }

        @Override
        public void internalFrameOpened(InternalFrameEvent e) {
            displayMessage("internalFrameOpened", e);
        }

        @Override
        public void internalFrameClosed(InternalFrameEvent e) {
            initView();
        }

        @Override
        public void internalFrameIconified(InternalFrameEvent e) {
            displayMessage("internalFrameIconified", e);
        }

        @Override
        public void internalFrameDeiconified(InternalFrameEvent e) {
            displayMessage("internalFrameDeiconified", e);
        }

        @Override
        public void internalFrameActivated(InternalFrameEvent e) {
            displayMessage("internalFrameActivated", e);
        }

        @Override
        public void internalFrameDeactivated(InternalFrameEvent e) {
            displayMessage("internalFrameDeactivated", e);
        }

        void displayMessage(String prefix, InternalFrameEvent e) {
            System.out.println(prefix + ": " + e.getSource());
        }
    };

    /**
     * Creates new form Usuarios
     */
    public ListaUsuarios() {
        initComponents();
        dtoUsuario = new DtoUsuario();
        initView();
    }
    
    public void initView(){
        llenarTabla();
        selectedUser = null;
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    public void llenarTabla() {
        String titulos[] = {"Sel", "Nombres", "Apellidos", "Usuario", "Rol", "foto", "Creado", "Ultima actualización"};

        String registro[] = new String[5];
        tblModel = new DefaultTableModel(null, titulos);
        ButtonGroup bg = new ButtonGroup();
        try {
            List<Usuario> userList = dtoUsuario.getUsuarios();
            int itemList = 0;
            for (Usuario usuario : userList) {
                JRadioButton rbtn = new JRadioButton();
                rbtn.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        radioBtnSelected(evt, usuario);
                    }
                });
                Object[] user = {
                    rbtn,
                    usuario.getNombre(),
                    usuario.getApellido(),
                    usuario.getUsuario(),
                    usuario.getRolDetailById(usuario.getRol()),
                    usuario.getFoto(),
                    usuario.getCreated(),
                    usuario.getUpdated()
                };
                tblModel.addRow(user);
                bg.add((JRadioButton) tblModel.getValueAt(itemList, 0));
                itemList++;
            }

            tblUsuarios.setModel(tblModel);
            tblUsuarios.getColumn("Sel").setCellRenderer(new RadioButtonRenderer());
            tblUsuarios.getColumn("Sel").setCellEditor(new RadioButtonEditor(new JCheckBox()));
            lbRegistros.setText("Número de registros: " + userList.size());

        } catch (SQLException ex) {
            Logger.getLogger(ListaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void radioBtnSelected(java.awt.event.ActionEvent evt, Usuario cUser) {
        selectedUser = cUser;
        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(true);
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
        tblUsuarios = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnCrear = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        lbRegistros = new javax.swing.JLabel();

        setTitle("Lista usuarios");

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblUsuarios);

        btnCrear.setText("Crear nuevo");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar selección");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar selección");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCrear)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCrear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnEditar))
                .addContainerGap())
        );

        lbRegistros.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbRegistros, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        Container container = SwingUtilities.getAncestorOfClass(JDesktopPane.class, (Component) evt.getSource());
        if (container != null) {
            JDesktopPane dp = getDesktopPane();
            FormUsuario fUser = new FormUsuario(0, null);
            dp.add(fUser);
            fUser.setVisible(true);
            fUser.addInternalFrameListener(internalFrameListener);
            try {
                fUser.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(ListaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Container container = SwingUtilities.getAncestorOfClass(JDesktopPane.class, (Component) evt.getSource());
        if (container != null) {
            JDesktopPane dp = getDesktopPane();
            FormUsuario fUser = new FormUsuario(1, selectedUser);
            dp.add(fUser);
            fUser.setVisible(true);
            fUser.addInternalFrameListener(internalFrameListener);
            try {
                fUser.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(ListaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
                dtoUsuario.deleteUsuario(selectedUser);
                llenarTabla();
                JOptionPane.showMessageDialog(rootPane, "Usuario eliminado exitosamente","Exito",INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(FormUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbRegistros;
    private javax.swing.JTable tblUsuarios;
    // End of variables declaration//GEN-END:variables
}

class RadioButtonRenderer implements TableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value == null) {
            return null;
        }
        return (Component) value;
    }
}

class RadioButtonEditor extends DefaultCellEditor implements ItemListener {

    private JRadioButton button;

    public RadioButtonEditor(JCheckBox checkBox) {
        super(checkBox);
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value == null) {
            return null;
        }
        button = (JRadioButton) value;
        button.addItemListener(this);
        return (Component) value;
    }

    public Object getCellEditorValue() {
        button.removeItemListener(this);
        return button;
    }

    public void itemStateChanged(ItemEvent e) {
        super.fireEditingStopped();
    }
}
