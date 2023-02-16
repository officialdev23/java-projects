import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class InventorySystem extends Frame implements ActionListener {
  private Label lblProduct, lblQuantity, lblTransactions, lblWarehouse;
  private TextField txtProduct, txtQuantity;
  private Button btnAdd, btnSubtract, btnUpdate;
  private java.awt.List listInventory;
  private java.awt.List uniqueProductList;
  private Map<String, Integer> inventory = new HashMap<>();

  public InventorySystem() {
    setLayout(new GridLayout(5,2));

    lblProduct = new Label("Product");
    add(lblProduct);

    txtProduct = new TextField(20);
    add(txtProduct);

    lblQuantity = new Label("Quantity");
    add(lblQuantity);

    txtQuantity = new TextField(20);
    add(txtQuantity);

    btnAdd = new Button("Add");
    btnAdd.addActionListener(this);
    add(btnAdd);

    btnSubtract = new Button("Subtract");
    btnSubtract.addActionListener(this);
    add(btnSubtract);

    /*btnUpdate = new Button("Update");
    btnUpdate.addActionListener(this);
    add(btnUpdate);*/
    
    lblTransactions = new Label("Warehouse Transactions");
    add(lblTransactions);

    listInventory = new java.awt.List(10, false);
    add(listInventory);

    lblWarehouse = new Label("Warehouse Inventory");
    add(lblWarehouse);

    uniqueProductList = new java.awt.List(10);
    add(uniqueProductList);

    setTitle("Inventory System");
    setSize(900, 900);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnAdd) {
      String product = txtProduct.getText();
      int quantity = Integer.parseInt(txtQuantity.getText());
      //int quantity1 = Integer.parseInt(txtQuantity.getText());

      if (inventory.containsKey(product)) {
        quantity += inventory.get(product);
      }
      inventory.put(product, quantity);
      listInventory.add(product + " - " + quantity);
    } else if (e.getSource() == btnSubtract) {
      String product = txtProduct.getText();
      int quantity = Integer.parseInt(txtQuantity.getText());

      if (inventory.containsKey(product)) {
	int prev_qty = quantity;
        quantity = inventory.get(product) - quantity;
	
        if (quantity < 0) {
          //quantity = 1;
	  //quantity = prev_qty;
	  quantity = quantity + prev_qty;
	  txtQuantity.setText("Not enough quantity");
        }
        inventory.put(product, quantity);
        int index = listInventory.getSelectedIndex();
        listInventory.add(product + " - " + quantity, index);
      }
    } else if (e.getSource() == btnUpdate) {
      String product = txtProduct.getText();
      int quantity = Integer.parseInt(txtQuantity.getText());

      inventory.put(product, quantity);
      int index = listInventory.getSelectedIndex();
      listInventory.add(product + " - " + quantity, index);
    }

    if (e.getSource() == btnAdd) {
            uniqueProductList.removeAll();
            Set<Map.Entry<String, Integer>> entrySet = inventory.entrySet();
            for (Map.Entry<String, Integer> entry : entrySet) {
                uniqueProductList.add(entry.getKey() + " (" + entry.getValue() + ")");
            }
        }
   if (e.getSource() == btnSubtract) {
            uniqueProductList.removeAll();
            Set<Map.Entry<String, Integer>> entrySet = inventory.entrySet();
            for (Map.Entry<String, Integer> entry : entrySet) {
                uniqueProductList.add(entry.getKey() + " (" + entry.getValue() + ")");
            }
        }
  }

  public static void main(String[] args) {
    new InventorySystem();
  }
}
