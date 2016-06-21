package Views.Invoice;

import Forms.Invoice.AddInvoiceItem;
import Models.Invoice.Invoice;
import Models.Invoice.InvoiceItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InvoiceDetails extends JFrame
{
    private JPanel panel1;
    private JButton backButton;
    private JButton addItemButton;
    private JButton generateButton;
    private JTable invoiceItemsTable;
    private JButton refreshButton;

    private Invoice invoice;
    private InvoiceDetails instance;

    public InvoiceDetails(Invoice invoice)
    {
        this.invoice = invoice;
        instance = this;
        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        backButton.addActionListener(onBackButtonClick);
        addItemButton.addActionListener(onAddButtonClick);
        generateButton.addActionListener(onGenerateButtonClick);
        refreshButton.addActionListener(onRefreshButtonClick);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        fillInvoiceDetailsTable();
        setVisible(true);
    }

    /**
     * Handle add item button click
     */
    protected ActionListener onAddButtonClick = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            AddInvoiceItem addItemForm = new AddInvoiceItem(instance.invoice);
        }
    };

    /**
     * Handle create button click
     */
    protected ActionListener onBackButtonClick = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            instance.dispose();
        }
    };

    /**
     * Handle create button click
     */
    protected ActionListener onRefreshButtonClick = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            fillInvoiceDetailsTable();
        }
    };


    /**
     * Handle generate button click
     */
    protected ActionListener onGenerateButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e)
        {
            try
            {

                PDF.Invoice.generate(instance.invoice);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    };

    private void fillInvoiceDetailsTable() {
        List<InvoiceItem> items = this.invoice.getAll(InvoiceItem.class);
        InvoiceDetailsTableModel detailsTableModel = new InvoiceDetailsTableModel(items);

        invoiceItemsTable.setModel(detailsTableModel);
    }
}
