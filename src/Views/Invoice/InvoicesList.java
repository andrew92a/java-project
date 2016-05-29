package Views.Invoice;

import Forms.Invoice.AddInvoiceForm;
import Models.Invoice.Invoice;
import org.javalite.activejdbc.FrozenException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InvoicesList extends JFrame {

    private JPanel panel1;
    private JTable invoicesTable;
    private JButton backButton;
    private JButton createButton;
    private JButton refreshButton;
    private InvoicesList instance;
    private InvoiceTableModel invoiceTableModel;

    /**
     * Create new view instance
     */
    public InvoicesList()
    {
        instance = this;
        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        backButton.addActionListener(onCancelButtonClick);
        createButton.addActionListener(onCreateButtonClick);
        refreshButton.addActionListener(onRefreshButtonClick);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        fillInvoiceTable();
        setVisible(true);
    }

    /**
     * Handle cancel button click
     */
    protected ActionListener onCancelButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            instance.dispose();
        }
    };

    /**
     * Handle create button click
     */
    protected ActionListener onCreateButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            AddInvoiceForm createForm = new AddInvoiceForm();
        }
    };

    /**
     * Handle refresh button click
     */
    protected ActionListener onRefreshButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            fillInvoiceTable();
        }
    };

    /**
     * Fill invoices table with data
     */
    private void fillInvoiceTable() {
        List<Invoice> invoices = Invoice.findAll();
        invoiceTableModel = new InvoiceTableModel(invoices);
        invoicesTable.setModel(invoiceTableModel);

        bindInvoiceTable();
    }

    private void bindInvoiceTable() {

        final InvoicesList list = this;
        try {
            invoicesTable.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        int row = invoicesTable.rowAtPoint(evt.getPoint());
                        int col = invoicesTable.columnAtPoint(evt.getPoint());
                        if (row >= 0 && col >= 0) {
                            int selected = invoicesTable.convertRowIndexToModel(row);
                            Invoice selectedInvoice = list.invoiceTableModel.getSelectedInvoice(selected);
                            InvoiceDetails detailsView = new InvoiceDetails(selectedInvoice);
                        }
                    }
                }
            });
        } catch (FrozenException e) {
            System.out.println(e.getMessage());
        }
    }
}
